package com._tm93243.hms.prescription_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
@Data
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;
    
    @Column(nullable = false)
    private Long appointmentId;
    
    @Column(nullable = false)
    private Long patientId;
    
    @Column(nullable = false)
    private Long doctorId;
    
    @Column(nullable = false)
    private String medication;
    
    @Column(nullable = false)
    private String dosage;
    
    @Column(nullable = false)
    private Integer days;
    
    private String instructions;
    
    @CreationTimestamp
    private LocalDateTime issuedAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}