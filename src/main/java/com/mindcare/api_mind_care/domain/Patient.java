package com.mindcare.api_mind_care.domain;

import jakarta.persistence.*;

@Entity
public class Patient extends User {

    public Patient() {
        super();
    }

    public Patient(String name, String email, String password, Role role) {
        super(name, email, password, role);
    }

}