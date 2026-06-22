package com.mindcare.api_mind_care.dto;

import com.mindcare.api_mind_care.domain.Role;

public class PatientCreateRequestDTO extends UserCreateRequestDTO {

    public PatientCreateRequestDTO() {
        super();
    }

    public PatientCreateRequestDTO(String email, String password, Role role) {
        super(email, password, role);
    }
}
