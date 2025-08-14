package com.internet.banking.bankingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.internet.banking.bankingservice.dto.UserDto;
import com.internet.banking.bankingservice.mapper.UserMapper;
import com.internet.banking.bankingservice.model.UserEntity;
import com.internet.banking.bankingservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserMapper userMapper = new UserMapper();

    private final UserRepository userRepository;

    public UserDto readUser(String identification) {
        UserEntity userEntity = userRepository.findByIdentificationNumber(identification).get();
        return userMapper.convertToDto(userEntity);
    }

    public List<UserDto> readUsers(Pageable pageable) {
       return userMapper.convertToDtoList(userRepository.findAll(pageable).getContent());
    }
}