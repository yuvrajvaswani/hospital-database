package com.hospital.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = false)
    private Integer age;
    
    @Column(nullable = false, unique = true)
    private String medicalRecordNumber;
    
    @Column(nullable = false)
    private String bloodGroup;
    
    @Column(columnDefinition = "TEXT")
    private String diagnosis;
    
    @Column(nullable = false)
    private String status; // "Admitted", "Discharged", etc.
    
    // Constructor for testing
    public Patient(String name, String email, String phone, Integer age, 
                   String medicalRecordNumber, String bloodGroup, String diagnosis, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.medicalRecordNumber = medicalRecordNumber;
        this.bloodGroup = bloodGroup;
        this.diagnosis = diagnosis;
        this.status = status;
    }
}
