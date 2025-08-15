package com.internet.banking.userservice.dto;

import com.internet.banking.userservice.model.Status;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String email;

    private String identification;

    private Status status;
}