package com.mindcare.api_mind_care.dto;

import java.time.LocalDateTime;

import com.mindcare.api_mind_care.domain.Status;

public class AppointmentResponseDTO {
    private Long id;
    private Long patientId;
    private Long psychologistId;
    private LocalDateTime appointmentDate;
    private Integer durationInMinutes;
    private Status status;
    private String notes;
    private LocalDateTime createdAt;

    public AppointmentResponseDTO() {
    }

    public AppointmentResponseDTO(Long id, Long patientId, Long psychologistId, LocalDateTime appointmentDate,
            Integer durationInMinutes, Status status, String notes, LocalDateTime createdAt) {
        this.id = id;
        this.patientId = patientId;
        this.psychologistId = psychologistId;
        this.appointmentDate = appointmentDate;
        this.durationInMinutes = durationInMinutes;
        this.status = status;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
