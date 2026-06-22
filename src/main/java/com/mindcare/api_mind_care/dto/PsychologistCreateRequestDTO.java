package com.mindcare.api_mind_care.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PsychologistCreateRequestDTO {
    @NotBlank(message = "Specialization is required")
    private String specialization;
    @NotNull(message = "Registration number is required")
    private int registrationNumber;

    public PsychologistCreateRequestDTO() {
    }

    public PsychologistCreateRequestDTO(String specialization, int registrationNumber) {
        this.specialization = specialization;
        this.registrationNumber = registrationNumber;
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
}
