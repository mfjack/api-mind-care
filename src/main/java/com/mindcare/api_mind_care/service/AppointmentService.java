package com.mindcare.api_mind_care.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindcare.api_mind_care.domain.Appointment;
import com.mindcare.api_mind_care.domain.Patient;
import com.mindcare.api_mind_care.domain.Psychologist;
import com.mindcare.api_mind_care.domain.Status;
import com.mindcare.api_mind_care.dto.AppointmentCreateRequestDTO;
import com.mindcare.api_mind_care.dto.AppointmentResponseDTO;
import com.mindcare.api_mind_care.repository.AppointmentRepository;
import com.mindcare.api_mind_care.repository.PatientRepository;
import com.mindcare.api_mind_care.repository.PsychologistRepository;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PsychologistRepository psychologistRepository;

    public AppointmentResponseDTO createAppointment(AppointmentCreateRequestDTO appointmentCreateRequestDTO) {
        if (appointmentCreateRequestDTO.getAppointmentDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past");
        }

        Patient patient = patientRepository.findById(appointmentCreateRequestDTO.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        Psychologist psychologist = psychologistRepository.findById(appointmentCreateRequestDTO.getPsychologistId())
                .orElseThrow(() -> new IllegalArgumentException("Psychologist not found"));

        List<Appointment> conflictingAppointments = appointmentRepository.findByPsychologistId(psychologist.getId());
        for (Appointment existing : conflictingAppointments) {
            if (existing.getStatus() == Status.PENDING || existing.getStatus() == Status.CONFIRMED) {
                LocalDateTime existingEnd = existing.getAppointmentDate().plusMinutes(existing.getDurationInMinutes());
                LocalDateTime newEnd = appointmentCreateRequestDTO.getAppointmentDate()
                        .plusMinutes(appointmentCreateRequestDTO.getDurationInMinutes());
                if (appointmentCreateRequestDTO.getAppointmentDate().isBefore(existingEnd)
                        && newEnd.isAfter(existing.getAppointmentDate())) {
                    throw new IllegalArgumentException("Psychologist is not available at the requested time");
                }

            }

        }

        Appointment newAppointment = new Appointment();
        newAppointment.setPatient(patient);
        newAppointment.setPsychologist(psychologist);
        newAppointment.setAppointmentDate(appointmentCreateRequestDTO.getAppointmentDate());
        newAppointment.setDurationInMinutes(appointmentCreateRequestDTO.getDurationInMinutes());
        newAppointment.setStatus(Status.PENDING);
        newAppointment.setNotes(appointmentCreateRequestDTO.getNotes());

        Appointment savedAppointment = appointmentRepository.save(newAppointment);

        return convertToDTO(savedAppointment);
    }

    public AppointmentResponseDTO findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        return convertToDTO(appointment);
    }

    public List<AppointmentResponseDTO> findByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);

        return appointments.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<AppointmentResponseDTO> findByPsychologistId(Long psychologistId) {
        List<Appointment> appointments = appointmentRepository.findByPsychologistId(psychologistId);

        return appointments.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<AppointmentResponseDTO> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public AppointmentResponseDTO confirmAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        if (appointment.getStatus() != Status.PENDING) {
            throw new IllegalArgumentException("Only PENDING appointments can be confirmed");
        }

        appointment.setStatus(Status.CONFIRMED);
        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return convertToDTO(updatedAppointment);
    }

    public AppointmentResponseDTO cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        if (appointment.getStatus() == Status.COMPLETED) {
            throw new IllegalArgumentException("Cannot cancel a COMPLETED appointment");
        }

        appointment.setStatus(Status.CANCELLED);
        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return convertToDTO(updatedAppointment);
    }

    private AppointmentResponseDTO convertToDTO(Appointment appointment) {
        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getPatient().getId(),
                appointment.getPsychologist().getId(),
                appointment.getAppointmentDate(),
                appointment.getDurationInMinutes(),
                appointment.getStatus(),
                appointment.getNotes(),
                appointment.getCreatedAt());
    }
}
