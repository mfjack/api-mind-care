package com.mindcare.api_mind_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindcare.api_mind_care.domain.Psychologist;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    Psychologist findByRegistrationNumber(int registrationNumber);
}
