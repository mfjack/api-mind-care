package com.mindcare.api_mind_care.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PsychologistRequestDTO(
        @NotBlank(message = "Name is required") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String name,

        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,

        @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters") String password,

        @NotBlank(message = "Phone is required") @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone format (E.164)") String phone,

        @NotBlank(message = "Specialization is required") @Size(min = 3, max = 100, message = "Specialization must be between 3 and 100 characters") String specialization,

        @NotNull(message = "Registration number is required") @Positive(message = "Registration number must be positive") Long registrationNumber,

        @Size(max = 1000, message = "Bio must not exceed 1000 characters") String bio,

        @NotNull(message = "Session duration is required") @Positive Integer sessionDuration,

        @NotNull(message = "Session price is required") @Positive Double sessionPrice) {
}
