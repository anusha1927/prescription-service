package com._tm93243.hms.prescription_service.service;

import com._tm93243.hms.prescription_service.dto.PrescriptionRequest;
import com._tm93243.hms.prescription_service.dto.PrescriptionResponse;
import com._tm93243.hms.prescription_service.model.Prescription;
import com._tm93243.hms.prescription_service.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Transactional
    public PrescriptionResponse createPrescription(PrescriptionRequest request) {
        validatePrescriptionRequest(request);

        Prescription prescription = new Prescription();
        prescription.setAppointmentId(request.getAppointmentId());
        prescription.setPatientId(request.getPatientId());
        prescription.setDoctorId(request.getDoctorId());
        prescription.setMedication(request.getMedication());
        prescription.setDosage(request.getDosage());
        prescription.setDays(request.getDays());
        prescription.setInstructions(request.getInstructions());

        return mapToResponse(prescriptionRepository.save(prescription));
    }

    @Transactional(readOnly = true)
    public PrescriptionResponse getPrescription(Long id) {
        return prescriptionRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    @Transactional(readOnly = true)
    public List<PrescriptionResponse> getPrescriptionsByAppointment(Long appointmentId) {
        return prescriptionRepository.findByAppointmentId(appointmentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PrescriptionResponse> getPatientPrescriptions(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private void validatePrescriptionRequest(PrescriptionRequest request) {
        if (request.getAppointmentId() == null) {
            throw new RuntimeException("Appointment ID is required");
        }
        if (request.getPatientId() == null) {
            throw new RuntimeException("Patient ID is required");
        }
        if (request.getDoctorId() == null) {
            throw new RuntimeException("Doctor ID is required");
        }
        if (request.getMedication() == null || request.getMedication().trim().isEmpty()) {
            throw new RuntimeException("Medication is required");
        }
        if (request.getDosage() == null || request.getDosage().trim().isEmpty()) {
            throw new RuntimeException("Dosage is required");
        }
        if (request.getDays() == null || request.getDays() <= 0) {
            throw new RuntimeException("Valid number of days is required");
        }
    }

    private PrescriptionResponse mapToResponse(Prescription prescription) {
        PrescriptionResponse response = new PrescriptionResponse();
        response.setPrescriptionId(prescription.getPrescriptionId());
        response.setAppointmentId(prescription.getAppointmentId());
        response.setPatientId(prescription.getPatientId());
        response.setDoctorId(prescription.getDoctorId());
        response.setMedication(prescription.getMedication());
        response.setDosage(prescription.getDosage());
        response.setDays(prescription.getDays());
        response.setInstructions(prescription.getInstructions());
        response.setIssuedAt(prescription.getIssuedAt());
        response.setUpdatedAt(prescription.getUpdatedAt());
        return response;
    }
}