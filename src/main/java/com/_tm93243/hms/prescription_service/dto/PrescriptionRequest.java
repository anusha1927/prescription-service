package com._tm93243.hms.prescription_service.dto;

import lombok.Data;

@Data
public class PrescriptionRequest {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private String medication;
    private String dosage;
    private Integer days;
    private String instructions;
}