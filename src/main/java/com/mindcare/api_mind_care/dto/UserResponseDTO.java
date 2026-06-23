package com.mindcare.api_mind_care.dto;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phone) {
}