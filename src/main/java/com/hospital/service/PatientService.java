package com.hospital.service;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository patientRepository;
    
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }
    
    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Patient not found with id: " + id));
        
        patient.setName(patientDetails.getName());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhone(patientDetails.getPhone());
        patient.setAge(patientDetails.getAge());
        patient.setMedicalRecordNumber(patientDetails.getMedicalRecordNumber());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        patient.setDiagnosis(patientDetails.getDiagnosis());
        patient.setStatus(patientDetails.getStatus());
        
        return patientRepository.save(patient);
    }
    
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
    
    public List<Patient> getPatientsByStatus(String status) {
        return patientRepository.findByStatus(status);
    }
}
