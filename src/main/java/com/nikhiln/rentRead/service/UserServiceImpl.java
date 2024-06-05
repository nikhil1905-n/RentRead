package com.nikhiln.rentRead.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nikhiln.rentRead.dto.UserRequestDto;
import com.nikhiln.rentRead.dto.UserResponseDto;
import com.nikhiln.rentRead.entity.Role;
import com.nikhiln.rentRead.entity.User;
import com.nikhiln.rentRead.exception.AlreadyExistsException;
import com.nikhiln.rentRead.exception.NotFoundException;
import com.nikhiln.rentRead.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserResponseDto> getAllUsers() {

        // List<User> users = userRepository.findAll();
        // return users.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());

        // OR

        return userRepository.findAll().stream().map(user -> {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getId());
            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setFirstName(user.getFirstName());
            userResponseDto.setLastName(user.getLastName());
            userResponseDto.setRoles(user.getRoles());
            return userResponseDto;
        }).collect(Collectors.toList());
        
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        
        // User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with ID " + userId + " Not Found"));

        // OR

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            throw new NotFoundException("User with ID " + userId + " Not Found");
        }

        return modelMapper.map(user, UserResponseDto.class);

    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {

        Optional<User> existingUser = userRepository.findByEmail(userRequestDto.getEmail());

        if(existingUser.isPresent()) {
            throw new AlreadyExistsException("User already exists with this email.");
        }

        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());

        Set<Role> roles = new HashSet<>();
        if(userRequestDto.getRoles() != null && !userRequestDto.getRoles().isEmpty()) {
            roles.addAll(userRequestDto.getRoles());
        }
        else {
            roles.add(Role.USER);
        }
        user.setRoles(roles);
        
        userRepository.save(user);

        return modelMapper.map(user, UserResponseDto.class);

    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with ID " + userId + " Not Found"));

        if(userRequestDto.getEmail() != null) {
            user.setEmail(userRequestDto.getEmail());
        }

        if(userRequestDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }
        
        if(userRequestDto.getFirstName() != null) {
            user.setFirstName(userRequestDto.getFirstName());
        }
        
        if(userRequestDto.getLastName() != null) {
            user.setLastName(userRequestDto.getLastName());
        }

        Set<Role> roles = new HashSet<>();
        if(userRequestDto.getRoles() != null) {
            roles.addAll(userRequestDto.getRoles());
        }
        user.setRoles(roles);

        userRepository.save(user);

        return modelMapper.map(user, UserResponseDto.class);

    }

    @Override
    public void deleteUser(Long userId) {

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            throw new NotFoundException("User with ID " + userId + " Not Found");
        }

        userRepository.deleteById(userId);

    }
    
}
