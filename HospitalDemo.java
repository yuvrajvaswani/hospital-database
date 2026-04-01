import java.util.*;

// Abstract Person class - demonstrates ABSTRACTION
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

// Doctor class - demonstrates INHERITANCE and POLYMORPHISM
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
        return "Doctor [ID=" + personId + ", Name=" + name + ", Specialization=" + specialization + ", Salary=" + salary + "]";
    }
}

// Patient class - demonstrates INHERITANCE
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

    public String toString() {
        return "Patient [ID=" + personId + ", Name=" + name + ", Diagnosis=" + diagnosis + ", Status=" + status + "]";
    }
}

// Appointment class - demonstrates COMPOSITION
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

    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public String getDateTime() { return dateTime; }
    public String getReason() { return reason; }

    public String toString() {
        return "Appointment [ID=" + id + ", Patient=" + patientId + ", Doctor=" + doctorId + ", DateTime=" + dateTime + ", Reason=" + reason + "]";
    }
}

// Service classes - demonstrates BUSINESS LOGIC LAYER
class DoctorService {
    private List<Doctor> doctors = new ArrayList<>();
    private int nextId = 1;

    public void addDoctor(String name, String email, String phone, int age, String spec, double salary) {
        doctors.add(new Doctor(nextId++, name, email, phone, age, spec, salary));
    }

    public List<Doctor> getAllDoctors() { return new ArrayList<>(doctors); }
    public Doctor getDoctor(int id) { 
        for (Doctor d : doctors) if (d.getPersonId() == id) return d;
        return null;
    }
}

class PatientService {
    private List<Patient> patients = new ArrayList<>();
    private int nextId = 1;

    public void addPatient(String name, String email, String phone, int age, String diagnosis, String status) {
        patients.add(new Patient(nextId++, name, email, phone, age, diagnosis, status));
    }

    public List<Patient> getAllPatients() { return new ArrayList<>(patients); }
    public Patient getPatient(int id) { 
        for (Patient p : patients) if (p.getPersonId() == id) return p;
        return null;
    }
}

class AppointmentService {
    private List<Appointment> appointments = new ArrayList<>();
    private int nextId = 1;

    public void scheduleAppointment(int pId, int dId, String dateTime, String reason) {
        appointments.add(new Appointment(nextId++, pId, dId, dateTime, reason));
    }

    public List<Appointment> getAllAppointments() { return new ArrayList<>(appointments); }
}

// Main Demo Application
public class HospitalDemo {
    private DoctorService doctorService = new DoctorService();
    private PatientService patientService = new PatientService();
    private AppointmentService appointmentService = new AppointmentService();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new HospitalDemo().run();
    }

    public void run() {
        initializeSampleData();
        mainMenu();
    }

    private void initializeSampleData() {
        System.out.println("=== Initializing Sample Data ===\n");
        
        doctorService.addDoctor("Dr. Rajesh Kumar", "rajesh@hospital.com", "9876543210", 45, "Cardiology", 150000);
        doctorService.addDoctor("Dr. Priya Singh", "priya@hospital.com", "9876543211", 38, "Neurology", 140000);
        doctorService.addDoctor("Dr. Arjun Patel", "arjun@hospital.com", "9876543212", 42, "Orthopedics", 145000);
        
        patientService.addPatient("Amit Verma", "amit@email.com", "9988776655", 35, "Hypertension", "Admitted");
        patientService.addPatient("Isha Gupta", "isha@email.com", "9988776656", 28, "Diabetes", "Admitted");
        patientService.addPatient("Rahul Singh", "rahul@email.com", "9988776657", 52, "Arthritis", "Discharged");
        
        appointmentService.scheduleAppointment(1, 1, "2026-04-01 09:00", "Heart Checkup");
        appointmentService.scheduleAppointment(2, 2, "2026-04-02 10:30", "Brain Scan");
        
        System.out.println("Sample data loaded successfully!\n");
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("  HOSPITAL MANAGEMENT SYSTEM (DEMO)");
            System.out.println("========================================");
            System.out.println("1. View All Doctors");
            System.out.println("2. View All Patients");
            System.out.println("3. View All Appointments");
            System.out.println("4. Add Doctor");
            System.out.println("5. Add Patient");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: viewDoctors(); break;
                    case 2: viewPatients(); break;
                    case 3: viewAppointments(); break;
                    case 4: addNewDoctor(); break;
                    case 5: addNewPatient(); break;
                    case 6: 
                        System.out.println("\nThank you for using Hospital Management System!");
                        return;
                    default: System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    private void viewDoctors() {
        System.out.println("\n===== ALL DOCTORS =====");
        for (Doctor doc : doctorService.getAllDoctors()) {
            System.out.println(doc);
        }
    }

    private void viewPatients() {
        System.out.println("\n===== ALL PATIENTS =====");
        for (Patient pat : patientService.getAllPatients()) {
            System.out.println(pat);
        }
    }

    private void viewAppointments() {
        System.out.println("\n===== ALL APPOINTMENTS =====");
        for (Appointment apt : appointmentService.getAllAppointments()) {
            System.out.println(apt);
        }
    }

    private void addNewDoctor() {
        System.out.println("\n===== ADD NEW DOCTOR =====");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter specialization: ");
        String spec = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());
        
        doctorService.addDoctor(name, email, phone, age, spec, salary);
        System.out.println("Doctor added successfully!");
    }

    private void addNewPatient() {
        System.out.println("\n===== ADD NEW PATIENT =====");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Enter status (Admitted/Discharged): ");
        String status = scanner.nextLine();
        
        patientService.addPatient(name, email, phone, age, diagnosis, status);
        System.out.println("Patient added successfully!");
    }
}
