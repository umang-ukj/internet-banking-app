package com.internet.banking.bankingservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internet.banking.bankingservice.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByIdentificationNumber(String identificationNumber);
}
