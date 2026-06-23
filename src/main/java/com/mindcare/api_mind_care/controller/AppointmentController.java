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

import com.mindcare.api_mind_care.dto.AppointmentCreateRequestDTO;
import com.mindcare.api_mind_care.dto.AppointmentResponseDTO;
import com.mindcare.api_mind_care.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired

    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(
            @Valid @RequestBody AppointmentCreateRequestDTO appointmentCreateRequestDTO) {
        AppointmentResponseDTO responseDTO = appointmentService.createAppointment(appointmentCreateRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable Long id) {
        AppointmentResponseDTO responseDTO = appointmentService.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findByPatientId(@PathVariable Long patientId) {
        List<AppointmentResponseDTO> responseDTO = appointmentService.findByPatientId(patientId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/psychologist/{psychologistId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findByPsychologistId(@PathVariable Long psychologistId) {
        List<AppointmentResponseDTO> responseDTO = appointmentService.findByPsychologistId(psychologistId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> findAll() {
        List<AppointmentResponseDTO> responseDTO = appointmentService.findAll();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<AppointmentResponseDTO> confirmAppointment(@PathVariable Long id) {
        AppointmentResponseDTO responseDTO = appointmentService.confirmAppointment(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponseDTO> cancelAppointment(@PathVariable Long id) {
        AppointmentResponseDTO responseDTO = appointmentService.cancelAppointment(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
