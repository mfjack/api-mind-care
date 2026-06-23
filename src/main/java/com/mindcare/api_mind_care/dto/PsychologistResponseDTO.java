package com.mindcare.api_mind_care.dto;

public record PsychologistResponseDTO(
                Long id,
                String name,
                String email,
                String phone,
                String specialization,
                Long registrationNumber,
                String bio,
                Integer sessionDuration,
                Double sessionPrice) {
}
