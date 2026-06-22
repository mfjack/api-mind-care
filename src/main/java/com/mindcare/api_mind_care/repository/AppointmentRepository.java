package com.mindcare.api_mind_care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindcare.api_mind_care.domain.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPsychologistId(Long psychologistId);

    List<Appointment> findByPatientId(Long patientId);
}
