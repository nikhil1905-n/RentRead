package com.nikhiln.rentRead.service;

import java.util.List;

import com.nikhiln.rentRead.dto.UserRequestDto;
import com.nikhiln.rentRead.dto.UserResponseDto;

public interface UserService {

    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long userId);
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);
    void deleteUser(Long userId);
    
}
