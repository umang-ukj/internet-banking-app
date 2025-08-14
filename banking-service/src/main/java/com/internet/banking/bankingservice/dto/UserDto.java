package com.internet.banking.bankingservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String identificationNumber;
    private List<BankAccountDto> bankAccounts;

}