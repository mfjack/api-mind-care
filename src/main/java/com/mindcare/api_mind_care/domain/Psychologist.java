package com.mindcare.api_mind_care.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Psychologist extends User {

    @Column(length = 200)
    private String specialization;

    @Column(unique = true)
    private Long registrationNumber;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false)
    private Integer sessionDuration;

    @Column(nullable = false)
    private Double sessionPrice;

    public Psychologist() {
    }

    public Psychologist(String name, String email, String password, String phone, String specialization,
            Long registrationNumber, String bio, Integer sessionDuration, Double sessionPrice) {
        super(name, email, password, phone);
        this.specialization = specialization;
        this.registrationNumber = registrationNumber;
        this.bio = bio;
        this.sessionDuration = sessionDuration;
        this.sessionPrice = sessionPrice;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBio() {
        return bio;
    }

    public Integer getSessionDuration() {
        return sessionDuration;
    }

    public Double getSessionPrice() {
        return sessionPrice;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setSessionDuration(Integer sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public void setSessionPrice(Double sessionPrice) {
        this.sessionPrice = sessionPrice;
    }
}
