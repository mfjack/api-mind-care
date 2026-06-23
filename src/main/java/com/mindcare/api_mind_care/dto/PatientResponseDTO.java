package com.mindcare.api_mind_care.dto;

public record PatientResponseDTO(
        Long id,
        String name,
        String email,
        String phone) {
}
