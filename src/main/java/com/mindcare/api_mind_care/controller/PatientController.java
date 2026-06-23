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

import com.mindcare.api_mind_care.dto.PatientRequestDTO;
import com.mindcare.api_mind_care.dto.PatientResponseDTO;
import com.mindcare.api_mind_care.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponseDTO create(@Valid @RequestBody PatientRequestDTO dto) {
        return patientService.create(dto);
    }

    @GetMapping("/{id}")
    public PatientResponseDTO findById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @GetMapping("/all")
    public List<PatientResponseDTO> findAll() {
        return patientService.findAll();
    }
}