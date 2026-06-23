package com.mindcare.api_mind_care.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "psychologist_id", nullable = false)
    private Psychologist psychologist;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Column(length = 500)
    private String notes;

    public Appointment() {
    }

    public Appointment(Patient patient, Psychologist psychologist, LocalDateTime appointmentDateTime,
            Integer durationInMinutes, AppointmentStatus status, String notes) {
        this.patient = patient;
        this.psychologist = psychologist;
        this.appointmentDateTime = appointmentDateTime;
        this.durationInMinutes = durationInMinutes;
        this.status = status;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Psychologist getPsychologist() {
        return psychologist;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public void confirm() {
        if (this.status != AppointmentStatus.SCHEDULED) {
            throw new IllegalStateException("Only SCHEDULED appointments can be confirmed");
        }
        this.status = AppointmentStatus.CONFIRMED;
    }

    public void cancel() {
        if (this.status == AppointmentStatus.CANCELLED) {
            throw new IllegalStateException("Appointment already cancelled");
        }
        this.status = AppointmentStatus.CANCELLED;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
