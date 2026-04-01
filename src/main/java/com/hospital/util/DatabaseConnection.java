package com.hospital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection utility class
 * Demonstrates singleton pattern for database connection management
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_management_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root"; // Change as needed
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    // Private constructor for singleton pattern
    private DatabaseConnection() {
        try {
            Class.forName(DB_DRIVER);
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
            throw new RuntimeException("Failed to load MySQL driver", e);
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    // Singleton pattern - getInstance
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Get connection method
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Error reconnecting to database: " + e.getMessage());
        }
        return connection;
    }

    // Close connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed!");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
