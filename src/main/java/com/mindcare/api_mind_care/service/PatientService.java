package com.mindcare.api_mind_care.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindcare.api_mind_care.domain.Patient;
import com.mindcare.api_mind_care.dto.PatientRequestDTO;
import com.mindcare.api_mind_care.dto.PatientResponseDTO;
import com.mindcare.api_mind_care.repository.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PatientResponseDTO create(PatientRequestDTO dto) {
        if (patientRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email already in use");
        }

        Patient newPatient = new Patient(
                dto.name(),
                dto.email(),
                passwordEncoder.encode(dto.password()),
                dto.phone());

        Patient savedPatient = patientRepository.save(newPatient);
        return toResponse(savedPatient);
    }

    public PatientResponseDTO findById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        return toResponse(patient);
    }

    public List<PatientResponseDTO> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private PatientResponseDTO toResponse(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone());
    }
}
