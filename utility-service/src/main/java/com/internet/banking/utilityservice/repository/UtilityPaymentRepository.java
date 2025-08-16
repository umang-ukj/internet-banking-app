package com.internet.banking.utilityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internet.banking.utilityservice.model.UtilityPaymentEntity;

public interface UtilityPaymentRepository extends JpaRepository<UtilityPaymentEntity, Long>{

}
