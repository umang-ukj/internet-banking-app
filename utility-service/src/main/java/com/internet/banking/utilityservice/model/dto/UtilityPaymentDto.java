package com.internet.banking.utilityservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;

import com.internet.banking.utilityservice.model.TransactionStatus;

@Data
public class UtilityPaymentDto {
    private Long providerId;
    private BigDecimal amount;
    private String referenceNumber;
    private String account;
    private TransactionStatus status;
}