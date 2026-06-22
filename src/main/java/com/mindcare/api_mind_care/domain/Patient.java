package com.mindcare.api_mind_care.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient extends User {

    public Patient() {
        super();
    }

    public Patient(String email, String password, Role role) {
        super(email, password, role);
    }

}