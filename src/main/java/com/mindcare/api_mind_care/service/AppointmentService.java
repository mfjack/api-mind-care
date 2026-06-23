package com.mindcare.api_mind_care.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mindcare.api_mind_care.domain.Appointment;
import com.mindcare.api_mind_care.domain.AppointmentStatus;
import com.mindcare.api_mind_care.domain.Patient;
import com.mindcare.api_mind_care.domain.Psychologist;
import com.mindcare.api_mind_care.dto.AppointmentRequestDTO;
import com.mindcare.api_mind_care.dto.AppointmentResponseDTO;
import com.mindcare.api_mind_care.repository.AppointmentRepository;
import com.mindcare.api_mind_care.repository.PatientRepository;
import com.mindcare.api_mind_care.repository.PsychologistRepository;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final PsychologistRepository psychologistRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
            PsychologistRepository psychologistRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.psychologistRepository = psychologistRepository;
    }

    public AppointmentResponseDTO create(AppointmentRequestDTO dto) {
        Patient patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        Psychologist psychologist = psychologistRepository.findById(dto.psychologistId())
                .orElseThrow(() -> new IllegalArgumentException("Psychologist not found"));

        LocalDateTime appointmentEnd = dto.appointmentDateTime().plusMinutes(dto.durationInMinutes());

        appointmentRepository.findByPsychologistId(dto.psychologistId())
                .stream()
                .filter(existing -> existing.getStatus() != AppointmentStatus.CANCELLED)
                .forEach(existing -> {
                    LocalDateTime existingEnd = existing.getAppointmentDateTime()
                            .plusMinutes(existing.getDurationInMinutes());

                    if (dto.appointmentDateTime().isBefore(existingEnd) &&
                            appointmentEnd.isAfter(existing.getAppointmentDateTime())) {
                        throw new IllegalStateException(
                                "Psychologist is not available between " +
                                        dto.appointmentDateTime() + " and " + appointmentEnd);
                    }
                });

        Appointment appointment = new Appointment(
                patient,
                psychologist,
                dto.appointmentDateTime(),
                dto.durationInMinutes(),
                AppointmentStatus.SCHEDULED,
                dto.notes());

        Appointment saved = appointmentRepository.save(appointment);
        return toResponse(saved);
    }

    public AppointmentResponseDTO confirm(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        appointment.confirm();
        Appointment updated = appointmentRepository.save(appointment);
        return toResponse(updated);
    }

    public AppointmentResponseDTO cancel(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        appointment.cancel();
        Appointment updated = appointmentRepository.save(appointment);
        return toResponse(updated);
    }

    public AppointmentResponseDTO findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        return toResponse(appointment);
    }

    public List<AppointmentResponseDTO> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<AppointmentResponseDTO> findByPsychologistId(Long psychologistId) {
        return appointmentRepository.findByPsychologistId(psychologistId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<AppointmentResponseDTO> findAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AppointmentResponseDTO toResponse(Appointment appointment) {
        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getPatient().getId(),
                appointment.getPsychologist().getId(),
                appointment.getAppointmentDateTime(),
                appointment.getDurationInMinutes(),
                appointment.getStatus(),
                appointment.getNotes());
    }
}