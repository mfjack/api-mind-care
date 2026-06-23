package com.mindcare.api_mind_care.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient extends User {

    public Patient() {
    }

    public Patient(String name, String email, String password, String phone) {
        super(name, email, password, phone);
    }
}