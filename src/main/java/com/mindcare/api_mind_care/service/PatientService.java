package com.mindcare.api_mind_care.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindcare.api_mind_care.domain.Patient;
import com.mindcare.api_mind_care.dto.PatientCreateRequestDTO;
import com.mindcare.api_mind_care.dto.PatientResponseDTO;
import com.mindcare.api_mind_care.repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public PatientResponseDTO createPatient(PatientCreateRequestDTO patientRequestDTO) {
        Patient newPatient = new Patient();
        newPatient.setEmail(patientRequestDTO.getEmail());
        newPatient.setPassword(patientRequestDTO.getPassword());
        newPatient.setRole(patientRequestDTO.getRole());

        Patient savedPatient = patientRepository.save(newPatient);
        return new PatientResponseDTO(
                savedPatient.getId(),
                savedPatient.getEmail(),
                savedPatient.getRole(),
                savedPatient.getCreatedAt());
    }

    public PatientResponseDTO findById(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);

        if (patient == null) {
            throw new IllegalArgumentException("Patient not found");
        }

        return new PatientResponseDTO(
                patient.getId(),
                patient.getEmail(),
                patient.getRole(),
                patient.getCreatedAt());
    }

    public List<PatientResponseDTO> findAll() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(patient -> new PatientResponseDTO(
                        patient.getId(),
                        patient.getEmail(),
                        patient.getRole(),
                        patient.getCreatedAt()))
                .toList();
    }
}
