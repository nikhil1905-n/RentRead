package com.nikhiln.rentRead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhiln.rentRead.dto.UserRequestDto;
import com.nikhiln.rentRead.dto.UserResponseDto;
import com.nikhiln.rentRead.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(userService.registerUser(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(UserRequestDto userRequestDto) {
        // Authentication handled by Spring Security
        return ResponseEntity.ok("Logged in successfully.");
    }
    
}
