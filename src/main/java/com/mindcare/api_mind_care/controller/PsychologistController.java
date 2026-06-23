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

import com.mindcare.api_mind_care.dto.PsychologistCreateRequestDTO;
import com.mindcare.api_mind_care.dto.PsychologistResponseDTO;
import com.mindcare.api_mind_care.service.PsychologistService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/psychologists")
public class PsychologistController {
    @Autowired

    private PsychologistService psychologistService;

    @PostMapping
    public ResponseEntity<PsychologistResponseDTO> createPsychologist(
            @Valid @RequestBody PsychologistCreateRequestDTO psychologistCreateRequestDTO) {
        PsychologistResponseDTO responseDTO = psychologistService.createPsychologist(psychologistCreateRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsychologistResponseDTO> findById(@PathVariable Long id) {
        PsychologistResponseDTO responseDTO = psychologistService.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<PsychologistResponseDTO> findByRegistrationNumber(@PathVariable int registrationNumber) {
        PsychologistResponseDTO responseDTO = psychologistService.findByRegistrationNumber(registrationNumber);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/psychologists")
    public ResponseEntity<List<PsychologistResponseDTO>> findAll() {
        List<PsychologistResponseDTO> responseDTO = psychologistService.findAll();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
