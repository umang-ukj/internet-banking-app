package com.internet.banking.bankingservice.dto;

import lombok.Data;

import java.math.BigDecimal;

import com.internet.banking.bankingservice.model.AccountStatus;
import com.internet.banking.bankingservice.model.AccountType;

@Data
public class BankAccountDto {

    private Long id;
    private String number;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;
    private UserDto user;

}