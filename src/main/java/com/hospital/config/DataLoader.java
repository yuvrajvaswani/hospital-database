package com.hospital.config;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {
    
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    
    public DataLoader(DoctorRepository doctorRepository, PatientRepository patientRepository, 
                      AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (doctorRepository.count() == 0) {
            // Load sample doctors
            Doctor doctor1 = new Doctor(null, "Dr. Rajesh Kumar", "rajesh@hospital.com", 
                                       "9876543210", 45, "Cardiology", 150000.0, true);
            Doctor doctor2 = new Doctor(null, "Dr. Priya Singh", "priya@hospital.com", 
                                       "9876543211", 38, "Neurology", 140000.0, true);
            Doctor doctor3 = new Doctor(null, "Dr. Arjun Patel", "arjun@hospital.com", 
                                       "9876543212", 42, "Orthopedics", 145000.0, true);
            
            doctorRepository.save(doctor1);
            doctorRepository.save(doctor2);
            doctorRepository.save(doctor3);
            
            System.out.println("Sample doctors loaded!");
        }
        
        if (patientRepository.count() == 0) {
            // Load sample patients
            Patient patient1 = new Patient(null, "Amit Verma", "amit@email.com", "9988776655", 35,
                                          "MRN001", "O+", "Hypertension", "Admitted");
            Patient patient2 = new Patient(null, "Isha Gupta", "isha@email.com", "9988776656", 28,
                                          "MRN002", "A+", "Diabetes", "Admitted");
            Patient patient3 = new Patient(null, "Rahul Singh", "rahul@email.com", "9988776657", 52,
                                          "MRN003", "B+", "Arthritis", "Discharged");
            
            patientRepository.save(patient1);
            patientRepository.save(patient2);
            patientRepository.save(patient3);
            
            System.out.println("Sample patients loaded!");
        }
        
        if (appointmentRepository.count() == 0) {
            // Load sample appointments
            Doctor doctor = doctorRepository.findAll().get(0);
            Patient patient = patientRepository.findAll().get(0);
            
            Appointment appointment1 = new Appointment(null, patient, doctor,
                                                      LocalDateTime.now().plusDays(1),
                                                      "Heart Checkup", "Scheduled");
            appointmentRepository.save(appointment1);
            
            System.out.println("Sample appointments loaded!");
        }
    }
}
