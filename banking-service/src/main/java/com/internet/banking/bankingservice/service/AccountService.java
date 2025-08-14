package com.internet.banking.bankingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.internet.banking.bankingservice.dto.BankAccountDto;
import com.internet.banking.bankingservice.dto.UtilityAccountDto;
import com.internet.banking.bankingservice.mapper.BankAccountMapper;
import com.internet.banking.bankingservice.mapper.UtilityAccountMapper;
import com.internet.banking.bankingservice.model.BankAccountEntity;
import com.internet.banking.bankingservice.model.UtilityAccountEntity;
import com.internet.banking.bankingservice.repository.BankAccountRepository;
import com.internet.banking.bankingservice.repository.UtilityAccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private BankAccountMapper bankAccountMapper = new BankAccountMapper();
    private UtilityAccountMapper utilityAccountMapper = new UtilityAccountMapper();

    private final BankAccountRepository bankAccountRepository;
    private final UtilityAccountRepository utilityAccountRepository;

    public BankAccountDto readBankAccount(String accountNumber) {
        BankAccountEntity entity = bankAccountRepository.findByNumber(accountNumber).get();
        return bankAccountMapper.convertToDto(entity);
    }

    public UtilityAccountDto readUtilityAccount(String provider) {
        UtilityAccountEntity utilityAccountEntity = utilityAccountRepository.findByProviderName(provider).get();
        return utilityAccountMapper.convertToDto(utilityAccountEntity);
    }

    public UtilityAccountDto readUtilityAccount(Long id){
        return utilityAccountMapper.convertToDto(utilityAccountRepository.findById(id).get());
    }

}