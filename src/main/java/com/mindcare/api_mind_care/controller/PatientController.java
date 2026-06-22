package com.mindcare.api_mind_care.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindcare.api_mind_care.dto.PatientCreateRequestDTO;
import com.mindcare.api_mind_care.dto.PatientResponseDTO;
import com.mindcare.api_mind_care.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Valid @RequestBody PatientCreateRequestDTO patientCreateRequestDTO) {
        PatientResponseDTO responseDTO = patientService.createPatient(patientCreateRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> findById(@PathVariable Long id) {
        PatientResponseDTO responseDTO = patientService.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> findAll() {
        List<PatientResponseDTO> responseDTOs = patientService.findAll();
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }
}