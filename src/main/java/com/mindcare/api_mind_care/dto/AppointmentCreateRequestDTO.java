package com.mindcare.api_mind_care.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class AppointmentCreateRequestDTO {
    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Psychologist ID is required")
    private Long psychologistId;

    @NotNull(message = "Appointment date is required")
    @Future(message = "Appointment date must be in the future")
    private LocalDateTime appointmentDate;

    @NotNull(message = "Duration in minutes is required")
    @Max(value = 50, message = "Duration cannot exceed 50 minutes")
    private Integer durationInMinutes;

    private String notes;

    public AppointmentCreateRequestDTO(Long patientId, Long psychologistId, LocalDateTime appointmentDate,
            Integer durationInMinutes, String notes) {
        this.patientId = patientId;
        this.psychologistId = psychologistId;
        this.appointmentDate = appointmentDate;
        this.durationInMinutes = durationInMinutes;
        this.notes = notes;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPsychologistId() {
        return psychologistId;
    }

    public void setPsychologistId(Long psychologistId) {
        this.psychologistId = psychologistId;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
