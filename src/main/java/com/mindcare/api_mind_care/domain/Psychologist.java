package com.mindcare.api_mind_care.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;

@Entity
@Table(name = "psychologists")
public class Psychologist extends User {

    @Column(length = 200)
    private String specialization;

    @Column(unique = true)
    private int registrationNumber;

    public Psychologist() {
    }

    public Psychologist(String name, String phone, Role role, String specialization, int registrationNumber) {
        super(name, phone, role);
        this.specialization = specialization;
        this.registrationNumber = registrationNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
