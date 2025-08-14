package com.internet.banking.bankingservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internet.banking.bankingservice.model.BankAccountEntity;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    Optional<BankAccountEntity> findByNumber(String accountNumber);
}
