package com.hospital.util;

/**
 * Validation utility class for input validation
 * Demonstrates encapsulation and reusable utility functions
 */
public class Validator {

    // Validate email format
    public static boolean isValidEmail(String email) throws ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email cannot be empty");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            throw new ValidationException("Invalid email format: " + email);
        }
        return true;
    }

    // Validate phone number
    public static boolean isValidPhoneNumber(String phoneNumber) throws ValidationException {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new ValidationException("Phone number cannot be empty");
        }
        String phoneRegex = "^[0-9]{10,15}$";
        if (!phoneNumber.matches(phoneRegex)) {
            throw new ValidationException("Invalid phone number format. Must be 10-15 digits: " + phoneNumber);
        }
        return true;
    }

    // Validate name
    public static boolean isValidName(String name) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new ValidationException("Name must be between 2 and 50 characters");
        }
        return true;
    }

    // Validate age
    public static boolean isValidAge(int age) throws ValidationException {
        if (age < 0 || age > 150) {
            throw new ValidationException("Invalid age. Must be between 0 and 150");
        }
        return true;
    }

    // Validate ID
    public static boolean isValidID(int id) throws ValidationException {
        if (id <= 0) {
            throw new ValidationException("ID must be a positive integer");
        }
        return true;
    }

    // Validate salary
    public static boolean isValidSalary(double salary) throws ValidationException {
        if (salary < 0) {
            throw new ValidationException("Salary cannot be negative");
        }
        return true;
    }

    // Validate blood group
    public static boolean isValidBloodGroup(String bloodGroup) throws ValidationException {
        if (bloodGroup == null || bloodGroup.trim().isEmpty()) {
            throw new ValidationException("Blood group cannot be empty");
        }
        String[] validGroups = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        boolean isValid = false;
        for (String group : validGroups) {
            if (group.equals(bloodGroup)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new ValidationException("Invalid blood group. Valid groups are: O+, O-, A+, A-, B+, B-, AB+, AB-");
        }
        return true;
    }

    // Validate date format (YYYY-MM-DD)
    public static boolean isValidDateFormat(String date) throws ValidationException {
        if (date == null || date.trim().isEmpty()) {
            throw new ValidationException("Date cannot be empty");
        }
        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!date.matches(dateRegex)) {
            throw new ValidationException("Invalid date format. Use YYYY-MM-DD: " + date);
        }
        return true;
    }
}
