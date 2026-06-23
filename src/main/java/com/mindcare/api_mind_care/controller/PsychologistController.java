package com.mindcare.api_mind_care.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindcare.api_mind_care.dto.PsychologistRequestDTO;
import com.mindcare.api_mind_care.dto.PsychologistResponseDTO;
import com.mindcare.api_mind_care.service.PsychologistService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/psychologists")
public class PsychologistController {
    private final PsychologistService psychologistService;

    public PsychologistController(PsychologistService psychologistService) {
        this.psychologistService = psychologistService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PsychologistResponseDTO create(
            @Valid @RequestBody PsychologistRequestDTO dto) {
        return psychologistService.create(dto);
    }

    @GetMapping("/{id}")
    public PsychologistResponseDTO findById(@PathVariable Long id) {
        return psychologistService.findById(id);
    }

    @GetMapping("/registration/{registrationNumber}")
    public PsychologistResponseDTO findByRegistrationNumber(@PathVariable Long registrationNumber) {
        return psychologistService.findByRegistrationNumber(registrationNumber);
    }

    @GetMapping
    public List<PsychologistResponseDTO> findAll() {
        return psychologistService.findAll();
    }
}
