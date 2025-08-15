package com.internet.banking.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.internet.banking.userservice.dto.UserDto;
import com.internet.banking.userservice.dto.UserUpdateRequest;
import com.internet.banking.userservice.mapper.UserMapper;
import com.internet.banking.userservice.model.Status;
import com.internet.banking.userservice.model.UserEntity;
import com.internet.banking.userservice.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private UserMapper userMapper = new UserMapper();

    public UserDto createUser(UserDto user) {

		/*
		 * List<UserRepresentation> userRepresentations =
		 * keycloakUserService.readUserByEmail(user.getEmail()); if
		 * (userRepresentations.size() > 0) { throw new
		 * RuntimeException("This email already registered as a user. Please check and retry."
		 * ); }
		 */
    	
    	// Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("This email is already registered. Please check and retry.");
        }

        // Generate identification & status
        user.setStatus(Status.PENDING);
        user.setIdentification(UUID.randomUUID().toString());

		/*
		 * UserRepresentation userRepresentation = new UserRepresentation();
		 * userRepresentation.setEmail(user.getEmail());
		 * userRepresentation.setEmailVerified(false);
		 * userRepresentation.setEnabled(false);
		 * userRepresentation.setUsername(user.getEmail());
		 */

        UserEntity savedUser = userRepository.save(userMapper.convertToEntity(user));

        log.info("User created with email {}", user.getEmail());
        return userMapper.convertToDto(savedUser);
    }

    public List<UserDto> readUsers(Pageable pageable) {
        Page<UserEntity> allUsersInDb = userRepository.findAll(pageable);
        List<UserDto> users = userMapper.convertToDtoList(allUsersInDb.getContent());
		/*
		 * users.forEach(user -> { UserRepresentation userRepresentation =
		 * keycloakUserService.readUser(user.getAuthId()); user.setId(user.getId());
		 * user.setEmail(userRepresentation.getEmail());
		 * user.setIdentification(user.getIdentification()); });
		 */
        users.forEach(user -> {
            user.setId(user.getId());
            user.setEmail(user.getEmail());
            user.setIdentification(user.getIdentification());
        });

        return users;
    }

    public UserDto readUser(Long userId) {
        return userRepository.findById(userId)
            .map(userMapper::convertToDto)
            .orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));
    }

    public UserDto updateUser(Long id, UserUpdateRequest userUpdateRequest) {
		/*
		 * UserEntity userEntity =
		 * userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		 * 
		 * if (userUpdateRequest.getStatus() == Status.APPROVED) { UserRepresentation
		 * userRepresentation = keycloakUserService.readUser(userEntity.getAuthId());
		 * userRepresentation.setEnabled(true);
		 * userRepresentation.setEmailVerified(true);
		 * keycloakUserService.updateUser(userRepresentation); }
		 * 
		 * userEntity.setStatus(userUpdateRequest.getStatus()); return
		 * userMapper.convertToDto(userRepository.save(userEntity));
		 */
    	UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        userEntity.setStatus(userUpdateRequest.getStatus());

        // removed Keycloak calls for simplification — we just update DB
        return userMapper.convertToDto(userRepository.save(userEntity));
    }

}