package com.internet.banking.fundtransferservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internet.banking.fundtransferservice.model.FundTransferEntity;

public interface FundTransferRepository extends JpaRepository<FundTransferEntity, Long> {

}
