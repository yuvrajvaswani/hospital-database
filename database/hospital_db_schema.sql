-- Hospital Management System Database Schema
-- MySQL Database Script

-- Create Database
CREATE DATABASE IF NOT EXISTS hospital_management_system;
USE hospital_management_system;

-- ==================== DOCTORS TABLE ====================
CREATE TABLE IF NOT EXISTS doctors (
    person_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(255),
    date_of_birth DATE,
    specialization VARCHAR(100) NOT NULL,
    license_number VARCHAR(50) NOT NULL UNIQUE,
    years_of_experience INT,
    salary DECIMAL(10, 2),
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_specialization (specialization),
    INDEX idx_availability (is_available),
    CONSTRAINT CHECK (salary >= 0),
    CONSTRAINT CHECK (years_of_experience >= 0)
);

-- ==================== PATIENTS TABLE ====================
CREATE TABLE IF NOT EXISTS patients (
    person_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(255),
    date_of_birth DATE,
    medical_record_number VARCHAR(50) NOT NULL UNIQUE,
    blood_group VARCHAR(5) NOT NULL,
    admission_date DATE,
    discharge_date DATE,
    diagnosis VARCHAR(255),
    treatment_plan TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'new',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_status (status),
    INDEX idx_blood_group (blood_group),
    CONSTRAINT CHECK (blood_group IN ('O+', 'O-', 'A+', 'A-', 'B+', 'B-', 'AB+', 'AB-')),
    CONSTRAINT CHECK (status IN ('new', 'admitted', 'discharged', 'in-recovery'))
);

-- ==================== NURSES TABLE ====================
CREATE TABLE IF NOT EXISTS nurses (
    person_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(255),
    date_of_birth DATE,
    registration_number VARCHAR(50) NOT NULL UNIQUE,
    department VARCHAR(100),
    years_of_experience INT,
    salary DECIMAL(10, 2),
    shift VARCHAR(20) DEFAULT 'morning',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_department (department),
    INDEX idx_shift (shift),
    CONSTRAINT CHECK (salary >= 0),
    CONSTRAINT CHECK (years_of_experience >= 0),
    CONSTRAINT CHECK (shift IN ('morning', 'evening', 'night'))
);

-- ==================== APPOINTMENTS TABLE ====================
CREATE TABLE IF NOT EXISTS appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status VARCHAR(20) DEFAULT 'scheduled',
    reason VARCHAR(255),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(person_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(person_id) ON DELETE CASCADE,
    INDEX idx_patient (patient_id),
    INDEX idx_doctor (doctor_id),
    INDEX idx_appointment_date (appointment_date),
    INDEX idx_status (status),
    CONSTRAINT CHECK (status IN ('scheduled', 'completed', 'cancelled', 'no-show'))
);

-- ==================== SAMPLE DATA ====================

-- Insert Sample Doctors
INSERT INTO doctors (first_name, last_name, email, phone_number, address, date_of_birth, specialization, license_number, years_of_experience, salary, is_available) VALUES
('Rajesh', 'Kumar', 'rajesh.kumar@hospital.com', '9876543210', '123 Medical Lane, City', '1980-05-15', 'Cardiology', 'MED001', 15, 150000.00, TRUE),
('Priya', 'Singh', 'priya.singh@hospital.com', '9876543211', '124 Medical Lane, City', '1985-08-20', 'Neurology', 'MED002', 12, 140000.00, TRUE),
('Arjun', 'Patel', 'arjun.patel@hospital.com', '9876543212', '125 Medical Lane, City', '1982-03-10', 'Orthopedics', 'MED003', 18, 145000.00, FALSE),
('Neha', 'Sharma', 'neha.sharma@hospital.com', '9876543213', '126 Medical Lane, City', '1987-11-25', 'Pediatrics', 'MED004', 10, 120000.00, TRUE),
('Vikram', 'Desai', 'vikram.desai@hospital.com', '9876543214', '127 Medical Lane, City', '1979-07-05', 'General Surgery', 'MED005', 20, 160000.00, TRUE);

-- Insert Sample Patients
INSERT INTO patients (first_name, last_name, email, phone_number, address, date_of_birth, medical_record_number, blood_group, admission_date, diagnosis, status) VALUES
('Amit', 'Verma', 'amit.verma@email.com', '9988776655', '10 Central Road, City', '1975-02-14', 'MRN001', 'O+', '2026-03-15', 'Hypertension', 'admitted'),
('Isha', 'Gupta', 'isha.gupta@email.com', '9988776656', '11 Central Road, City', '1990-06-22', 'MRN002', 'A+', '2026-03-20', 'Diabetes Management', 'admitted'),
('Rohit', 'Bhat', 'rohit.bhat@email.com', '9988776657', '12 Central Road, City', '1992-09-30', 'MRN003', 'B+', '2026-03-10', 'Fractured Arm', 'admitted'),
('Ananya', 'Nair', 'ananya.nair@email.com', '9988776658', '13 Central Road, City', '1988-12-08', 'MRN004', 'AB-', '2026-02-28', 'Post-Surgery Recovery', 'in-recovery'),
('Suresh', 'Iyer', 'suresh.iyer@email.com', '9988776659', '14 Central Road, City', '1970-01-19', 'MRN005', 'O-', '2026-03-18', 'Chest Pain Investigation', 'admitted');

-- Insert Sample Appointments
INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status, reason) VALUES
(1, 1, '2026-04-01', '09:00:00', 'scheduled', 'Cardiology Checkup'),
(2, 4, '2026-04-02', '10:30:00', 'scheduled', 'Pediatric Consultation'),
(3, 3, '2026-04-03', '14:00:00', 'scheduled', 'Orthopedic Examination'),
(4, 2, '2026-04-04', '11:00:00', 'scheduled', 'Neurology Follow-up'),
(5, 1, '2026-04-05', '15:30:00', 'scheduled', 'Cardiac Assessment'),
(1, 5, '2026-03-25', '10:00:00', 'completed', 'General Surgery Consultation'),
(2, 4, '2026-03-20', '09:30:00', 'completed', 'Initial Checkup');

-- Create Indexes for better query performance
CREATE INDEX idx_doctor_email ON doctors(email);
CREATE INDEX idx_patient_mrn ON patients(medical_record_number);
CREATE INDEX idx_appointment_datetime ON appointments(appointment_date, appointment_time);

-- View for Active Patients
CREATE VIEW active_patients AS
SELECT person_id, first_name, last_name, medical_record_number, blood_group, diagnosis, status
FROM patients
WHERE status IN ('admitted', 'in-recovery')
ORDER BY admission_date DESC;

-- View for Doctor Information
CREATE VIEW doctor_information AS
SELECT person_id, CONCAT(first_name, ' ', last_name) AS doctor_name, 
       specialization, license_number, years_of_experience, salary, is_available
FROM doctors
ORDER BY first_name;

-- Stored Procedure to get upcoming appointments
DELIMITER $$
CREATE PROCEDURE get_upcoming_appointments(IN appointment_date_param DATE)
BEGIN
    SELECT a.appointment_id, 
           CONCAT(p.first_name, ' ', p.last_name) AS patient_name,
           CONCAT(d.first_name, ' ', d.last_name) AS doctor_name,
           a.appointment_time,
           a.reason
    FROM appointments a
    JOIN patients p ON a.patient_id = p.person_id
    JOIN doctors d ON a.doctor_id = d.person_id
    WHERE a.appointment_date = appointment_date_param 
    AND a.status = 'scheduled'
    ORDER BY a.appointment_time;
END$$
DELIMITER ;

-- Stored Procedure to get patient admission history
DELIMITER $$
CREATE PROCEDURE get_patient_admission_history(IN patient_id_param INT)
BEGIN
    SELECT person_id, CONCAT(first_name, ' ', last_name) AS patient_name,
           medical_record_number, blood_group, admission_date, discharge_date, 
           diagnosis, status
    FROM patients
    WHERE person_id = patient_id_param;
END$$
DELIMITER ;
