package com.mindcare.api_mind_care.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AppointmentRequestDTO(
        @NotNull Long patientId,
        @NotNull Long psychologistId,
        @NotNull @Future LocalDateTime appointmentDateTime,
        @NotNull @Positive Integer durationInMinutes,
        @Size(max = 500, message = "Notes must not exceed 500 characters") String notes) {
}
