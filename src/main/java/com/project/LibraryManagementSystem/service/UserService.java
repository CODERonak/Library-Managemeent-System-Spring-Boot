package com.project.LibraryManagementSystem.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.dto.auth.*;
import com.project.LibraryManagementSystem.exceptions.custom.*;
import com.project.LibraryManagementSystem.mappers.UserMapper;
import com.project.LibraryManagementSystem.model.entity.User;
import com.project.LibraryManagementSystem.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }

        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    public LoginResponse login(LoginRequest request) {
        User existingUser = userRepository.findByEmail(request.getEmail()).orElse(null);

        // This throws an error if the username or password does not exist or is wrong
        if (existingUser == null ||
                !passwordEncoder.matches(request.getPassword(), existingUser.getPassword())) {

            throw new InvalidCredentialsException("Invalid username or password");
        }

        return new LoginResponse("Login successful");
    }

}
