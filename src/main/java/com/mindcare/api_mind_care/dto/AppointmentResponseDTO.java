package com.mindcare.api_mind_care.dto;

import java.time.LocalDateTime;

import com.mindcare.api_mind_care.domain.AppointmentStatus;

public record AppointmentResponseDTO(
                Long id,
                Long patientId,
                Long psychologistId,
                LocalDateTime appointmentDateTime,
                Integer durationInMinutes,
                AppointmentStatus status,
                String notes) {
}
