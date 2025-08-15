package com.internet.banking.fundtransferservice.model.dto;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class FundTransferDto {
    private Long id;
    private String transactionReference;
    private String status;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
}