package com.internet.banking.fundtransferservice.model;

import lombok.Data;
@Data
public class FundTransferResponse {
    private String message;
    private String transactionId;
}