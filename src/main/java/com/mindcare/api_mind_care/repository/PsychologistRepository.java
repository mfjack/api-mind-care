package com.mindcare.api_mind_care.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindcare.api_mind_care.domain.Psychologist;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    Optional<Psychologist> findByRegistrationNumber(Long registrationNumber);

    boolean existsByEmail(String email);

    boolean existsByRegistrationNumber(Long registrationNumber);
}
