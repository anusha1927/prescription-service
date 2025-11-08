package com._tm93243.hms.prescription_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PrescriptionResponse {
    private Long prescriptionId;
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private String medication;
    private String dosage;
    private Integer days;
    private String instructions;
    private LocalDateTime issuedAt;
    private LocalDateTime updatedAt;
}