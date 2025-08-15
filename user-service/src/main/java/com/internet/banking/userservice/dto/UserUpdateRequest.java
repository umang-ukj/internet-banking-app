package com.internet.banking.userservice.dto;

import com.internet.banking.userservice.model.Status;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private Status status;
}