package com.mindcare.api_mind_care.dto;

import java.time.LocalDateTime;

import com.mindcare.api_mind_care.domain.Role;

public class PsychologistResponseDTO {
    private Long id;
    private String email;
    private Role role;
    private String specialization;
    private int registrationNumber;
    private LocalDateTime createdAt;

    public PsychologistResponseDTO() {
    }

    public PsychologistResponseDTO(Long id, String name, String email, com.mindcare.api_mind_care.domain.Role role,
            String specialization, int registrationNumber,
            LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.specialization = specialization;
        this.registrationNumber = registrationNumber;
        this.createdAt = createdAt;
    }

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
