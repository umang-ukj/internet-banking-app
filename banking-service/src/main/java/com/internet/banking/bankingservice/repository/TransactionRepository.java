package com.internet.banking.bankingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internet.banking.bankingservice.dto.TransactionDto;
import com.internet.banking.bankingservice.model.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
