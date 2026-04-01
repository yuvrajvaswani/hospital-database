// Standalone Hospital Management System - Simplified Demo Version
// No external dependencies - uses only Java standard library

import java.util.*;

// ==================== MODEL CLASSES ====================

abstract class Person {
    protected int personId;
    protected String firstName, lastName, email, phoneNumber, address, dateOfBirth;

    public Person(int personId, String firstName, String lastName, String email, 
                  String phoneNumber, String address, String dateOfBirth) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public abstract String getRole();
    public abstract double getAnnualSalary();

    public String getFullName() { return firstName + " " + lastName; }
    public int getPersonId() { return personId; }

    @Override
    public String toString() {
        return "Person{" + "id=" + personId + ", name='" + getFullName() + '\'' +
                ", email='" + email + '\'' + ", role='" + getRole() + '\'' + '}';
    }
}

class Doctor extends Person {
    private String specialization;
    private double salary;
    private boolean isAvailable;

    public Doctor(int id, String fn, String ln, String email, String phone, String addr, String dob,
                  String spec, double sal) {
        super(id, fn, ln, email, phone, addr, dob);
        this.specialization = spec;
        this.salary = sal;
        this.isAvailable = true;
    }

    @Override
    public String getRole() { return "Doctor"; }

    @Override
    public double getAnnualSalary() { return salary; }

    public String getSpecialization() { return specialization; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Doctor{id=" + personId + ", name='" + getFullName() + "', specialization='" + 
               specialization + "', salary=" + salary + ", available=" + isAvailable + "}";
    }
}

class Patient extends Person {
    private String medicalRecordNumber;
    private String bloodGroup;
    private String diagnosis;
    private String status;

    public Patient(int id, String fn, String ln, String email, String phone, String addr, String dob,
                   String mrn, String blood, String diagnosis, String status) {
        super(id, fn, ln, email, phone, addr, dob);
        this.medicalRecordNumber = mrn;
        this.bloodGroup = blood;
        this.diagnosis = diagnosis;
        this.status = status;
    }

    @Override
    public String getRole() { return "Patient"; }

    @Override
    public double getAnnualSalary() { return 0; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getMedicalRecordNumber() { return medicalRecordNumber; }
    public String getBloodGroup() { return bloodGroup; }

    @Override
    public String toString() {
        return "Patient{id=" + personId + ", name='" + getFullName() + "', MRN='" + 
               medicalRecordNumber + "', blood='" + bloodGroup + "', diagnosis='" + 
               diagnosis + "', status='" + status + "'}";
    }
}

class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String dateTime;
    private String reason;
    private String status;

    public Appointment(int id, int pId, int dId, String dateTime, String reason, String status) {
        this.appointmentId = id;
        this.patientId = pId;
        this.doctorId = dId;
        this.dateTime = dateTime;
        this.reason = reason;
        this.status = status;
    }

    public int getAppointmentId() { return appointmentId; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public String getDateTime() { return dateTime; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Appointment{id=" + appointmentId + ", patient=" + patientId + ", doctor=" + 
               doctorId + ", dateTime='" + dateTime + "', reason='" + reason + "', status='" + status + "'}";
    }
}

// ==================== SERVICES ====================

class DoctorService {
    private List<Doctor> doctors = new ArrayList<>();
    private int nextId = 1;

    public void addDoctor(String fn, String ln, String email, String phone, String addr, String dob,
                          String spec, double salary) {
        doctors.add(new Doctor(nextId++, fn, ln, email, phone, addr, dob, spec, salary));
    }

    public List<Doctor> getAllDoctors() { return new ArrayList<>(doctors); }

    public Doctor getDoctorById(int id) {
        return doctors.stream().filter(d -> d.getPersonId() == id).findFirst().orElse(null);
    }

    public List<Doctor> getAvailableDoctors() {
        List<Doctor> available = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.isAvailable()) available.add(d);
        }
        return available;
    }

    public List<Doctor> getDoctorsBySpecialization(String spec) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.getSpecialization().equalsIgnoreCase(spec)) result.add(d);
        }
        return result;
    }

    public boolean deleteDoctor(int id) {
        return doctors.removeIf(d -> d.getPersonId() == id);
    }
}

class PatientService {
    private List<Patient> patients = new ArrayList<>();
    private int nextId = 1;

    public void addPatient(String fn, String ln, String email, String phone, String addr, String dob,
                           String mrn, String blood, String diagnosis) {
        patients.add(new Patient(nextId++, fn, ln, email, phone, addr, dob, mrn, blood, diagnosis, "admitted"));
    }

    public List<Patient> getAllPatients() { return new ArrayList<>(patients); }

    public Patient getPatientById(int id) {
        return patients.stream().filter(p -> p.getPersonId() == id).findFirst().orElse(null);
    }

    public List<Patient> getPatientsByStatus(String status) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : patients) {
            if (p.getStatus().equalsIgnoreCase(status)) result.add(p);
        }
        return result;
    }

    public boolean deletePatient(int id) {
        return patients.removeIf(p -> p.getPersonId() == id);
    }
}

class AppointmentService {
    private List<Appointment> appointments = new ArrayList<>();
    private int nextId = 1;

    public void scheduleAppointment(int patientId, int doctorId, String dateTime, String reason) {
        appointments.add(new Appointment(nextId++, patientId, doctorId, dateTime, reason, "scheduled"));
    }

    public List<Appointment> getAllAppointments() { return new ArrayList<>(appointments); }

    public List<Appointment> getAppointmentsByPatient(int patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatientId() == patientId) result.add(a);
        }
        return result;
    }

    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getDoctorId() == doctorId) result.add(a);
        }
        return result;
    }

    public boolean cancelAppointment(int id) {
        Appointment apt = appointments.stream().filter(a -> a.getAppointmentId() == id).findFirst().orElse(null);
        if (apt != null) {
            apt.setStatus("cancelled");
            return true;
        }
        return false;
    }
}

// ==================== CONSOLE UI ====================

public class HospitalManagementSystemDemo {
    private DoctorService doctorService = new DoctorService();
    private PatientService patientService = new PatientService();
    private AppointmentService appointmentService = new AppointmentService();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new HospitalManagementSystemDemo().start();
    }

    public void start() {
        initializeSampleData();
        mainMenu();
    }

    private void initializeSampleData() {
        // Add sample doctors
        doctorService.addDoctor("Rajesh", "Kumar", "rajesh@hospital.com", "9876543210", 
                               "123 Medical Lane", "1980-05-15", "Cardiology", 150000);
        doctorService.addDoctor("Priya", "Singh", "priya@hospital.com", "9876543211", 
                               "124 Medical Lane", "1985-08-20", "Neurology", 140000);
        doctorService.addDoctor("Arjun", "Patel", "arjun@hospital.com", "9876543212", 
                               "125 Medical Lane", "1982-03-10", "Orthopedics", 145000);

        // Add sample patients
        patientService.addPatient("Amit", "Verma", "amit@email.com", "9988776655", 
                                 "10 Central Road", "1975-02-14", "MRN001", "O+", "Hypertension");
        patientService.addPatient("Isha", "Gupta", "isha@email.com", "9988776656", 
                                 "11 Central Road", "1990-06-22", "MRN002", "A+", "Diabetes");

        // Add sample appointments
        appointmentService.scheduleAppointment(1, 1, "2026-04-01 09:00", "Cardiology Checkup");
        appointmentService.scheduleAppointment(2, 2, "2026-04-02 10:30", "Neurology Consultation");
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   HOSPITAL MANAGEMENT SYSTEM (DEMO)");
            System.out.println("========================================");
            System.out.println("1. Doctor Management");
            System.out.println("2. Patient Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1: doctorMenu(); break;
                case 2: patientMenu(); break;
                case 3: appointmentMenu(); break;
                case 4: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private void doctorMenu() {
        while (true) {
            System.out.println("\n===== DOCTOR MANAGEMENT =====");
            System.out.println("1. View All Doctors");
            System.out.println("2. View Doctor by ID");
            System.out.println("3. View Available Doctors");
            System.out.println("4. Search by Specialization");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    System.out.println("\n===== ALL DOCTORS =====");
                    for (Doctor d : doctorService.getAllDoctors()) {
                        System.out.println(d);
                    }
                    break;
                case 2:
                    System.out.print("Enter Doctor ID: ");
                    Doctor d = doctorService.getDoctorById(getIntInput());
                    System.out.println(d != null ? d : "Doctor not found");
                    break;
                case 3:
                    System.out.println("\n===== AVAILABLE DOCTORS =====");
                    for (Doctor doc : doctorService.getAvailableDoctors()) {
                        System.out.println(doc);
                    }
                    break;
                case 4:
                    System.out.print("Enter Specialization: ");
                    String spec = scanner.nextLine();
                    System.out.println("\n===== " + spec.toUpperCase() + " DOCTORS =====");
                    for (Doctor doc : doctorService.getDoctorsBySpecialization(spec)) {
                        System.out.println(doc);
                    }
                    break;
                case 5: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private void patientMenu() {
        while (true) {
            System.out.println("\n===== PATIENT MANAGEMENT =====");
            System.out.println("1. View All Patients");
            System.out.println("2. View Patient by ID");
            System.out.println("3. View Patients by Status");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    System.out.println("\n===== ALL PATIENTS =====");
                    for (Patient p : patientService.getAllPatients()) {
                        System.out.println(p);
                    }
                    break;
                case 2:
                    System.out.print("Enter Patient ID: ");
                    Patient p = patientService.getPatientById(getIntInput());
                    System.out.println(p != null ? p : "Patient not found");
                    break;
                case 3:
                    System.out.print("Enter Status (admitted/discharged): ");
                    String status = scanner.nextLine();
                    System.out.println("\n===== PATIENTS - " + status.toUpperCase() + " =====");
                    for (Patient patient : patientService.getPatientsByStatus(status)) {
                        System.out.println(patient);
                    }
                    break;
                case 4: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private void appointmentMenu() {
        while (true) {
            System.out.println("\n===== APPOINTMENT MANAGEMENT =====");
            System.out.println("1. View All Appointments");
            System.out.println("2. View Appointments by Patient");
            System.out.println("3. View Appointments by Doctor");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    System.out.println("\n===== ALL APPOINTMENTS =====");
                    for (Appointment a : appointmentService.getAllAppointments()) {
                        System.out.println(a);
                    }
                    break;
                case 2:
                    System.out.print("Enter Patient ID: ");
                    System.out.println("\n===== PATIENT'S APPOINTMENTS =====");
                    for (Appointment a : appointmentService.getAppointmentsByPatient(getIntInput())) {
                        System.out.println(a);
                    }
                    break;
                case 3:
                    System.out.print("Enter Doctor ID: ");
                    System.out.println("\n===== DOCTOR'S APPOINTMENTS =====");
                    for (Appointment a : appointmentService.getAppointmentsByDoctor(getIntInput())) {
                        System.out.println(a);
                    }
                    break;
                case 4: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return -1;
        }
    }
}
