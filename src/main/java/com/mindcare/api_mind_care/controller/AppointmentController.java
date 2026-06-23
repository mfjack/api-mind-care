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

import com.mindcare.api_mind_care.dto.AppointmentRequestDTO;
import com.mindcare.api_mind_care.dto.AppointmentResponseDTO;
import com.mindcare.api_mind_care.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponseDTO create(
            @Valid @RequestBody AppointmentRequestDTO dto) {
        return appointmentService.create(dto);
    }

    @GetMapping("/{id}")
    public AppointmentResponseDTO findById(@PathVariable Long id) {
        return appointmentService.findById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentResponseDTO> findByPatientId(@PathVariable Long patientId) {
        return appointmentService.findByPatientId(patientId);
    }

    @GetMapping("/psychologist/{psychologistId}")
    public List<AppointmentResponseDTO> findByPsychologistId(@PathVariable Long psychologistId) {
        return appointmentService.findByPsychologistId(psychologistId);
    }

    @GetMapping
    public List<AppointmentResponseDTO> findAll() {
        return appointmentService.findAll();
    }

    @PostMapping("/{id}/confirm")
    public AppointmentResponseDTO confirmAppointment(@PathVariable Long id) {
        return appointmentService.confirm(id);
    }

    @PostMapping("/{id}/cancel")
    public AppointmentResponseDTO cancelAppointment(@PathVariable Long id) {
        return appointmentService.cancel(id);
    }
}
