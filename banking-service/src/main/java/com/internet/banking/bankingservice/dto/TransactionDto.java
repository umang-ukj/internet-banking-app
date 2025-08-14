package com.internet.banking.bankingservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {

    private Long id;
    private BigDecimal amount;
    private BankAccountDto bankAccount;
    private String referenceNumber;

}