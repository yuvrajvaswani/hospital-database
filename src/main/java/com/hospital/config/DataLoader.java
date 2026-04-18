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
            doctorRepository.save(new Doctor(null, "Dr. Rajesh Kumar",   "rajesh@hospital.com",  "9876543210", 45, "Cardiology",      1500000.0, true));
            doctorRepository.save(new Doctor(null, "Dr. Priya Singh",    "priya@hospital.com",   "9876543211", 38, "Neurology",       1400000.0, true));
            doctorRepository.save(new Doctor(null, "Dr. Arjun Patel",    "arjun@hospital.com",   "9876543212", 42, "Orthopedics",     1450000.0, true));
            doctorRepository.save(new Doctor(null, "Dr. Meena Sharma",   "meena@hospital.com",   "9876543213", 36, "Pediatrics",      1350000.0, true));
            doctorRepository.save(new Doctor(null, "Dr. Suresh Nair",    "suresh@hospital.com",  "9876543214", 50, "General Surgery", 1600000.0, false));
            doctorRepository.save(new Doctor(null, "Dr. Kavita Reddy",   "kavita@hospital.com",  "9876543215", 40, "Dermatology",     1300000.0, true));
            doctorRepository.save(new Doctor(null, "Dr. Vikram Joshi",   "vikram@hospital.com",  "9876543216", 47, "Oncology",        1700000.0, true));
            doctorRepository.save(new Doctor(null, "Dr. Ananya Iyer",    "ananya@hospital.com",  "9876543217", 34, "Ophthalmology",   1250000.0, true));
            System.out.println("Sample doctors loaded!");
        }

        if (patientRepository.count() == 0) {
            patientRepository.save(new Patient(null, "Amit Verma",       "amit@email.com",    "9988776655", 35, "MRN001", "O+",  "Hypertension",          "Admitted"));
            patientRepository.save(new Patient(null, "Isha Gupta",       "isha@email.com",    "9988776656", 28, "MRN002", "A+",  "Type 2 Diabetes",        "Admitted"));
            patientRepository.save(new Patient(null, "Rahul Singh",      "rahul@email.com",   "9988776657", 52, "MRN003", "B+",  "Knee Arthritis",         "Discharged"));
            patientRepository.save(new Patient(null, "Sneha Desai",      "sneha@email.com",   "9988776658", 40, "MRN004", "AB+", "Migraine",               "In Treatment"));
            patientRepository.save(new Patient(null, "Karan Mehta",      "karan@email.com",   "9988776659", 29, "MRN005", "O-",  "Fractured Femur",        "Admitted"));
            patientRepository.save(new Patient(null, "Pooja Nair",       "pooja@email.com",   "9988776660", 45, "MRN006", "B-",  "Thyroid Disorder",       "In Treatment"));
            patientRepository.save(new Patient(null, "Deepak Rao",       "deepak@email.com",  "9988776661", 63, "MRN007", "A-",  "Coronary Artery Disease","Admitted"));
            patientRepository.save(new Patient(null, "Anita Pillai",     "anita@email.com",   "9988776662", 22, "MRN008", "AB-", "Appendicitis",           "Discharged"));
            patientRepository.save(new Patient(null, "Rajiv Chandra",    "rajiv@email.com",   "9988776663", 55, "MRN009", "O+",  "Lung Infection",         "Admitted"));
            patientRepository.save(new Patient(null, "Sunita Bose",      "sunita@email.com",  "9988776664", 38, "MRN010", "A+",  "Eczema",                 "In Treatment"));
            System.out.println("Sample patients loaded!");
        }

        if (appointmentRepository.count() == 0) {
            java.util.List<Doctor>  docs = doctorRepository.findAll();
            java.util.List<Patient> pats = patientRepository.findAll();
            LocalDateTime base = LocalDateTime.now();

            // Spread appointments across last 6 months and coming days for realistic trend
            appointmentRepository.save(new Appointment(null, pats.get(0), docs.get(0), base.minusMonths(5).withDayOfMonth(3),  "Cardiac Evaluation",        "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(1), docs.get(1), base.minusMonths(5).withDayOfMonth(10), "Neurology Follow-up",       "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(2), docs.get(2), base.minusMonths(4).withDayOfMonth(5),  "Post-surgery Checkup",      "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(3), docs.get(1), base.minusMonths(4).withDayOfMonth(18), "Migraine Treatment",        "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(4), docs.get(2), base.minusMonths(3).withDayOfMonth(7),  "Fracture Review",           "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(5), docs.get(5), base.minusMonths(3).withDayOfMonth(14), "Thyroid Consultation",      "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(6), docs.get(0), base.minusMonths(2).withDayOfMonth(2),  "ECG & Stress Test",         "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(7), docs.get(4), base.minusMonths(2).withDayOfMonth(9),  "Appendix Surgery Review",   "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(8), docs.get(6), base.minusMonths(1).withDayOfMonth(6),  "Oncology Consultation",     "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(9), docs.get(5), base.minusMonths(1).withDayOfMonth(20), "Skin Allergy Treatment",    "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(0), docs.get(0), base.minusDays(10),                     "Blood Pressure Follow-up",  "Completed"));
            appointmentRepository.save(new Appointment(null, pats.get(1), docs.get(3), base.minusDays(7),                      "Pediatric Diabetes Care",   "Scheduled"));
            appointmentRepository.save(new Appointment(null, pats.get(6), docs.get(0), base.plusDays(1),                       "Cardiology Checkup",        "Scheduled"));
            appointmentRepository.save(new Appointment(null, pats.get(4), docs.get(2), base.plusDays(2),                       "Physiotherapy Session",     "Scheduled"));
            appointmentRepository.save(new Appointment(null, pats.get(8), docs.get(7), base.plusDays(3),                       "Eye Examination",           "Scheduled"));
            appointmentRepository.save(new Appointment(null, pats.get(3), docs.get(1), base.plusDays(4),                       "MRI Follow-up",             "Scheduled"));
            System.out.println("Sample appointments loaded!");
        }
    }
}
