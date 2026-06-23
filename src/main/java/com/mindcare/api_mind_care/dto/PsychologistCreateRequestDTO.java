package com.mindcare.api_mind_care.dto;

import com.mindcare.api_mind_care.domain.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Herda email, password, role de UserCreateRequestDTO
 * Adiciona campos específicos do Psychologist
 */
public class PsychologistCreateRequestDTO extends UserCreateRequestDTO {
    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotNull(message = "Registration number is required")
    private int registrationNumber;

    public PsychologistCreateRequestDTO() {
        super();
    }

    public PsychologistCreateRequestDTO(String name, String email, String password, Role role,
            String specialization, int registrationNumber) {
        super(name, email, password, role);
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
