package com.mindcare.api_mind_care.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindcare.api_mind_care.domain.User;
import com.mindcare.api_mind_care.dto.UserCreateRequestDTO;
import com.mindcare.api_mind_care.dto.UserResponseDTO;
import com.mindcare.api_mind_care.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO register(UserCreateRequestDTO userCreateRequestDTO) {
        User existingUser = userRepository.findByEmail(userCreateRequestDTO.getEmail());

        if (existingUser != null) {
            throw new IllegalArgumentException("Email already in use");
        }

        User newUser = new User();
        newUser.setName(userCreateRequestDTO.getName());
        newUser.setEmail(userCreateRequestDTO.getEmail());
        newUser.setPassword(userCreateRequestDTO.getPassword());
        newUser.setRole(userCreateRequestDTO.getRole());

        User savedUser = userRepository.save(newUser);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getCreatedAt());
    }

    public UserResponseDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt());
    }

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt());
    }
}
