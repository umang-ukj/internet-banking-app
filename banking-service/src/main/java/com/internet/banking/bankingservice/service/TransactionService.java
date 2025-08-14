package com.internet.banking.bankingservice.service;

import org.springframework.stereotype.Service;

import com.internet.banking.bankingservice.dto.BankAccountDto;
import com.internet.banking.bankingservice.dto.UtilityAccountDto;
import com.internet.banking.bankingservice.dto.request.FundTransferRequest;
import com.internet.banking.bankingservice.dto.request.UtilityPaymentRequest;
import com.internet.banking.bankingservice.dto.response.FundTransferResponse;
import com.internet.banking.bankingservice.dto.response.UtilityPaymentResponse;
import com.internet.banking.bankingservice.model.BankAccountEntity;
import com.internet.banking.bankingservice.model.TransactionEntity;
import com.internet.banking.bankingservice.model.TransactionType;
import com.internet.banking.bankingservice.repository.BankAccountRepository;
import com.internet.banking.bankingservice.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final AccountService accountService;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) {

        BankAccountDto fromBankAccount = accountService.readBankAccount(fundTransferRequest.getFromAccount());
        BankAccountDto toBankAccount = accountService.readBankAccount(fundTransferRequest.getToAccount());

        //validating account balances
        validateBalance(fromBankAccount, fundTransferRequest.getAmount());

        String transactionId = internalFundTransfer(fromBankAccount, toBankAccount, fundTransferRequest.getAmount());
        return FundTransferResponse.builder().message("Transaction successfully completed").transactionId(transactionId).build();

    }

    public UtilityPaymentResponse utilPayment(UtilityPaymentRequest utilityPaymentRequest) {

        String transactionId = UUID.randomUUID().toString();

        BankAccountDto fromBankAccount = accountService.readBankAccount(utilityPaymentRequest.getAccount());

        //validating account balances
        validateBalance(fromBankAccount, utilityPaymentRequest.getAmount());

       // UtilityAccountDto utilityAccount = accountService.readUtilityAccount(utilityPaymentRequest.getProviderId());

        BankAccountEntity fromAccount = bankAccountRepository.findByNumber(fromBankAccount.getNumber()).get();

        //we can call third party API to process UTIL payment from payment provider from here.

        fromAccount.setActualBalance(fromAccount.getActualBalance().subtract(utilityPaymentRequest.getAmount()));
        fromAccount.setAvailableBalance(fromAccount.getActualBalance().subtract(utilityPaymentRequest.getAmount()));

        transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.UTILITY_PAYMENT)
                .account(fromAccount)
                .transactionId(transactionId)
                .referenceNumber(utilityPaymentRequest.getReferenceNumber())
                .amount(utilityPaymentRequest.getAmount().negate()).build());

        return UtilityPaymentResponse.builder().message("Utility payment successfully completed")
                .transactionId(transactionId).build();

    }

    private void validateBalance(BankAccountDto bankAccount, BigDecimal amount) {
        if (bankAccount.getActualBalance().compareTo(BigDecimal.ZERO) < 0 || bankAccount.getActualBalance().compareTo(amount) < 0) {
            throw new RuntimeException();
        }
    }

    public String internalFundTransfer(BankAccountDto fromBankAccount, BankAccountDto toBankAccount, BigDecimal amount) {

        String transactionId = UUID.randomUUID().toString();

        BankAccountEntity fromBankAccountEntity = bankAccountRepository.findByNumber(fromBankAccount.getNumber()).get();
        BankAccountEntity toBankAccountEntity = bankAccountRepository.findByNumber(toBankAccount.getNumber()).get();

        fromBankAccountEntity.setActualBalance(fromBankAccountEntity.getActualBalance().subtract(amount));
        fromBankAccountEntity.setAvailableBalance(fromBankAccountEntity.getActualBalance().subtract(amount));
        bankAccountRepository.save(fromBankAccountEntity);

        transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.FUND_TRANSFER)
                .referenceNumber(toBankAccountEntity.getNumber())
                .transactionId(transactionId)
                .account(fromBankAccountEntity).amount(amount.negate()).build());

        toBankAccountEntity.setActualBalance(toBankAccountEntity.getActualBalance().add(amount));
        toBankAccountEntity.setAvailableBalance(toBankAccountEntity.getActualBalance().add(amount));
        bankAccountRepository.save(toBankAccountEntity);

        transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.FUND_TRANSFER)
                .referenceNumber(toBankAccountEntity.getNumber())
                .transactionId(transactionId)
                .account(toBankAccountEntity).amount(amount).build());

        return transactionId;

    }


}
