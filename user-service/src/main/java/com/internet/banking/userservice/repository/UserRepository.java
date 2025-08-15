package com.internet.banking.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internet.banking.userservice.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	boolean existsByEmail(String email);

}
