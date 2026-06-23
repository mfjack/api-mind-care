package com.mindcare.api_mind_care.service;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindcare.api_mind_care.domain.Psychologist;
import com.mindcare.api_mind_care.dto.PsychologistRequestDTO;
import com.mindcare.api_mind_care.dto.PsychologistResponseDTO;
import com.mindcare.api_mind_care.repository.PsychologistRepository;

@Service
public class PsychologistService {
    private final PsychologistRepository psychologistRepository;
    private final PasswordEncoder passwordEncoder;

    public PsychologistService(PsychologistRepository psychologistRepository, PasswordEncoder passwordEncoder) {
        this.psychologistRepository = psychologistRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PsychologistResponseDTO create(PsychologistRequestDTO dto) {
        if (psychologistRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (psychologistRepository.existsByRegistrationNumber(dto.registrationNumber())) {
            throw new IllegalArgumentException("Registration number already in use");
        }

        Psychologist newPsychologist = new Psychologist(
                dto.name(),
                dto.email(),
                passwordEncoder.encode(dto.password()),
                dto.phone(),
                dto.specialization(),
                dto.registrationNumber(),
                dto.bio(),
                dto.sessionDuration(),
                dto.sessionPrice());

        Psychologist savedPsychologist = psychologistRepository.save(newPsychologist);

        return toResponse(savedPsychologist);
    }

    public PsychologistResponseDTO findById(Long id) {
        Psychologist psychologist = psychologistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Psychologist not found"));
        return toResponse(psychologist);
    }

    public PsychologistResponseDTO findByRegistrationNumber(Long registrationNumber) {
        Psychologist psychologist = psychologistRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new IllegalArgumentException("Psychologist not found"));
        return toResponse(psychologist);
    }

    public List<PsychologistResponseDTO> findAll() {
        return psychologistRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private PsychologistResponseDTO toResponse(Psychologist psychologist) {
        return new PsychologistResponseDTO(
                psychologist.getId(),
                psychologist.getName(),
                psychologist.getEmail(),
                psychologist.getPhone(),
                psychologist.getSpecialization(),
                psychologist.getRegistrationNumber(),
                psychologist.getBio(),
                psychologist.getSessionDuration(),
                psychologist.getSessionPrice());
    }
}
