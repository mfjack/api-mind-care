package com.mindcare.api_mind_care.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindcare.api_mind_care.domain.Psychologist;
import com.mindcare.api_mind_care.dto.PsychologistCreateRequestDTO;
import com.mindcare.api_mind_care.dto.PsychologistResponseDTO;
import com.mindcare.api_mind_care.repository.PsychologistRepository;

@Service
public class PsychologistService {
    @Autowired
    private PsychologistRepository psychologistRepository;

    public PsychologistResponseDTO createPsychologist(PsychologistCreateRequestDTO psychologistCreateRequestDTO) {
        Psychologist existingPsychologist = psychologistRepository
                .findByRegistrationNumber(psychologistCreateRequestDTO.getRegistrationNumber());

        if (existingPsychologist != null) {
            throw new IllegalArgumentException("Registration number already in use");
        }

        Psychologist newPsychologist = new Psychologist();
        newPsychologist.setEmail(psychologistCreateRequestDTO.getEmail());
        newPsychologist.setPassword(psychologistCreateRequestDTO.getPassword());
        newPsychologist.setRole(psychologistCreateRequestDTO.getRole());
        newPsychologist.setSpecialization(psychologistCreateRequestDTO.getSpecialization());
        newPsychologist.setRegistrationNumber(psychologistCreateRequestDTO.getRegistrationNumber());

        Psychologist savedPsychologist = psychologistRepository.save(newPsychologist);

        return new PsychologistResponseDTO(
                savedPsychologist.getId(),
                savedPsychologist.getEmail(),
                savedPsychologist.getRole(),
                savedPsychologist.getSpecialization(),
                savedPsychologist.getRegistrationNumber(),
                savedPsychologist.getCreatedAt());
    }

    public PsychologistResponseDTO findById(Long id) {
        Psychologist psychologist = psychologistRepository.findById(id).orElse(null);

        if (psychologist == null) {
            throw new IllegalArgumentException("Psychologist not found");
        }

        return new PsychologistResponseDTO(
                psychologist.getId(),
                psychologist.getEmail(),
                psychologist.getRole(),
                psychologist.getSpecialization(),
                psychologist.getRegistrationNumber(),
                psychologist.getCreatedAt());
    }

    public PsychologistResponseDTO findByRegistrationNumber(int registrationNumber) {
        Psychologist psychologist = psychologistRepository.findByRegistrationNumber(registrationNumber);

        if (psychologist == null) {
            throw new IllegalArgumentException("Psychologist not found");
        }

        return new PsychologistResponseDTO(
                psychologist.getId(),
                psychologist.getEmail(),
                psychologist.getRole(),
                psychologist.getSpecialization(),
                psychologist.getRegistrationNumber(),
                psychologist.getCreatedAt());
    }

    public List<PsychologistResponseDTO> findAll() {
        List<Psychologist> psychologists = psychologistRepository.findAll();

        return psychologists.stream()
                .map(psychologist -> new PsychologistResponseDTO(
                        psychologist.getId(),
                        psychologist.getEmail(),
                        psychologist.getRole(),
                        psychologist.getSpecialization(),
                        psychologist.getRegistrationNumber(),
                        psychologist.getCreatedAt()))
                .toList();
    }
}
