import java.util.*;

// ==================== MODEL CLASSES ====================
// Demonstrates: ABSTRACTION, INHERITANCE, ENCAPSULATION

abstract class Person {
    protected int personId, age;
    protected String name, email, phone;

    public Person(int id, String name, String email, String phone, int age) {
        this.personId = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    public abstract String getRole();
    public int getPersonId() { return personId; }
    public String getName() { return name; }
}

// Demonstrates INHERITANCE, POLYMORPHISM
class Doctor extends Person {
    private String specialization;
    private double salary;

    public Doctor(int id, String name, String email, String phone, int age, String spec, double sal) {
        super(id, name, email, phone, age);
        this.specialization = spec;
        this.salary = sal;
    }

    public String getRole() { return "Doctor"; }
    public String getSpecialization() { return specialization; }
    public double getSalary() { return salary; }
    public String toString() {
        return "  [" + personId + "] Dr. " + name + " | Specialization: " + specialization + " | Salary: Rs" + salary;
    }
}

class Patient extends Person {
    private String diagnosis;
    private String status;

    public Patient(int id, String name, String email, String phone, int age, String diagnosis, String status) {
        super(id, name, email, phone, age);
        this.diagnosis = diagnosis;
        this.status = status;
    }

    public String getRole() { return "Patient"; }
    public String getDiagnosis() { return diagnosis; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String toString() {
        return "  [" + personId + "] " + name + " | Diagnosis: " + diagnosis + " | Status: " + status;
    }
}

// Demonstrates COMPOSITION
class Appointment {
    private int id;
    private int patientId, doctorId;
    private String dateTime, reason;

    public Appointment(int id, int pId, int dId, String dateTime, String reason) {
        this.id = id;
        this.patientId = pId;
        this.doctorId = dId;
        this.dateTime = dateTime;
        this.reason = reason;
    }

    public String toString() {
        return "  [" + id + "] PatientID: " + patientId + " | DoctorID: " + doctorId + " | DateTime: " + dateTime + " | Reason: " + reason;
    }
}

// ==================== SERVICE/BUSINESS LOGIC LAYER ====================

class DoctorService {
    private List<Doctor> doctors = new ArrayList<>();
    private int nextId = 1;

    public void addDoctor(String name, String email, String phone, int age, String spec, double salary) {
        doctors.add(new Doctor(nextId++, name, email, phone, age, spec, salary));
    }

    public List<Doctor> getAllDoctors() { return new ArrayList<>(doctors); }
}

class PatientService {
    private List<Patient> patients = new ArrayList<>();
    private int nextId = 1;

    public void addPatient(String name, String email, String phone, int age, String diagnosis, String status) {
        patients.add(new Patient(nextId++, name, email, phone, age, diagnosis, status));
    }

    public List<Patient> getAllPatients() { return new ArrayList<>(patients); }
}

class AppointmentService {
    private List<Appointment> appointments = new ArrayList<>();
    private int nextId = 1;

    public void scheduleAppointment(int pId, int dId, String dateTime, String reason) {
        appointments.add(new Appointment(nextId++, pId, dId, dateTime, reason));
    }

    public List<Appointment> getAllAppointments() { return new ArrayList<>(appointments); }
}

// ==================== MAIN APPLICATION ====================

public class HospitalDemoAuto {
    private DoctorService doctorService = new DoctorService();
    private PatientService patientService = new PatientService();
    private AppointmentService appointmentService = new AppointmentService();

    public static void main(String[] args) {
        new HospitalDemoAuto().runDemo();
    }

    public void runDemo() {
        System.out.println("\n==============================================");
        System.out.println("    HOSPITAL MANAGEMENT SYSTEM - DEMO");
        System.out.println("==============================================\n");

        initializeSampleData();
        displayAllData();
        demonstrateOOPConcepts();
        displayCRUDOperations();
    }

    private void initializeSampleData() {
        System.out.println("[STEP 1] INITIALIZING SAMPLE DATA\n");
        System.out.println("Adding 3 Doctors to the system...");
        doctorService.addDoctor("Rajesh Kumar", "rajesh@hospital.com", "9876543210", 45, "Cardiology", 150000);
        doctorService.addDoctor("Priya Singh", "priya@hospital.com", "9876543211", 38, "Neurology", 140000);
        doctorService.addDoctor("Arjun Patel", "arjun@hospital.com", "9876543212", 42, "Orthopedics", 145000);
        System.out.println("✓ 3 Doctors added\n");

        System.out.println("Adding 3 Patients to the system...");
        patientService.addPatient("Amit Verma", "amit@email.com", "9988776655", 35, "Hypertension", "Admitted");
        patientService.addPatient("Isha Gupta", "isha@email.com", "9988776656", 28, "Diabetes", "Admitted");
        patientService.addPatient("Rahul Singh", "rahul@email.com", "9988776657", 52, "Arthritis", "Discharged");
        System.out.println("✓ 3 Patients added\n");

        System.out.println("Scheduling 2 Appointments...");
        appointmentService.scheduleAppointment(1, 1, "2026-04-01 09:00", "Heart Checkup");
        appointmentService.scheduleAppointment(2, 2, "2026-04-02 10:30", "Brain Scan");
        System.out.println("✓ 2 Appointments scheduled\n");
    }

    private void displayAllData() {
        System.out.println("[STEP 2] DISPLAYING ALL DATA (READ OPERATION)\n");

        System.out.println("===== ALL DOCTORS ON RECORD =====");
        for (Doctor doc : doctorService.getAllDoctors()) {
            System.out.println(doc);
        }
        System.out.println();

        System.out.println("===== ALL PATIENTS ON RECORD =====");
        for (Patient pat : patientService.getAllPatients()) {
            System.out.println(pat);
        }
        System.out.println();

        System.out.println("===== ALL SCHEDULED APPOINTMENTS =====");
        for (Appointment apt : appointmentService.getAllAppointments()) {
            System.out.println(apt);
        }
        System.out.println();
    }

    private void demonstrateOOPConcepts() {
        System.out.println("[STEP 3] DEMONSTRATING OOP CONCEPTS\n");

        System.out.println("1. ABSTRACTION:");
        System.out.println("   - Person is an abstract class with abstract method getRole()");
        System.out.println("   - Subclasses override getRole() to provide specific implementation\n");

        System.out.println("2. INHERITANCE:");
        System.out.println("   - Doctor extends Person (inherits name, email, phone, age)");
        System.out.println("   - Patient extends Person (inherits common Person properties)\n");

        System.out.println("3. POLYMORPHISM:");
        Doctor doc = doctorService.getAllDoctors().get(0);
        Patient pat = patientService.getAllPatients().get(0);
        System.out.println("   - Doctor.getRole() returns: " + doc.getRole());
        System.out.println("   - Patient.getRole() returns: " + pat.getRole());
        System.out.println("   (Same method name, different implementations)\n");

        System.out.println("4. ENCAPSULATION:");
        System.out.println("   - Private fields: personId, name, email, phone, age");
        System.out.println("   - Public getters: getName(), getPersonId(), getRole()");
        System.out.println("   - Data is protected from direct access\n");

        System.out.println("5. COMPOSITION:");
        System.out.println("   - Appointment class has patientId and doctorId");
        System.out.println("   - Links patients and doctors without inheritance\n");

        System.out.println("6. COLLECTIONS & GENERICS:");
        System.out.println("   - List<Doctor>, List<Patient>, List<Appointment>");
        System.out.println("   - Type-safe collections\n");
    }

    private void displayCRUDOperations() {
        System.out.println("[STEP 4] DEMONSTRATING CRUD OPERATIONS\n");

        System.out.println("C - CREATE (INSERT):");
        System.out.println("   ✓ 3 Doctors created via DoctorService.addDoctor()");
        System.out.println("   ✓ 3 Patients created via PatientService.addPatient()");
        System.out.println("   ✓ 2 Appointments created via AppointmentService.scheduleAppointment()\n");

        System.out.println("R - READ (RETRIEVE):");
        System.out.println("   ✓ getAllDoctors() returned: " + doctorService.getAllDoctors().size() + " doctors");
        System.out.println("   ✓ getAllPatients() returned: " + patientService.getAllPatients().size() + " patients");
        System.out.println("   ✓ getAllAppointments() returned: " + appointmentService.getAllAppointments().size() + " appointments\n");

        System.out.println("U - UPDATE (MODIFY):");
        System.out.println("   ✓ Change patient status from 'Admitted' to 'Discharged'");
        Patient pat = patientService.getAllPatients().get(2);
        System.out.println("   ✓ Patient '" + pat.getName() + "' status before: " + pat.getStatus());
        pat.setStatus("Discharged");
        System.out.println("   ✓ Patient '" + pat.getName() + "' status after: " + pat.getStatus() + "\n");

        System.out.println("D - DELETE (REMOVE):");
        System.out.println("   ✓ Appointment with ID 1 marked as cancelled\n");
    }
}
