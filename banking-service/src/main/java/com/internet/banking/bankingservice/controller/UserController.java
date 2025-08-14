package com.internet.banking.bankingservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internet.banking.bankingservice.dto.UserDto;
import com.internet.banking.bankingservice.service.UserService;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{identification}")
    public ResponseEntity<UserDto> readUser(@PathVariable("identification") String identification) {
        return ResponseEntity.ok(userService.readUser(identification));
    }

    @GetMapping
    public ResponseEntity<List> readUser(Pageable pageable) {
        return ResponseEntity.ok(userService.readUsers(pageable));
    }
}
