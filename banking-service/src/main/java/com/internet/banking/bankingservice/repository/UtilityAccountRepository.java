package com.internet.banking.bankingservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internet.banking.bankingservice.model.UtilityAccountEntity;

public interface UtilityAccountRepository extends JpaRepository<UtilityAccountEntity, Long> {
    Optional<UtilityAccountEntity> findByProviderName(String provider);
}