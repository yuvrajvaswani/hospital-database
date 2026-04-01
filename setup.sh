#!/bin/bash
# Hospital Management System - Quick Setup Script for Linux/macOS
# This script automates the database creation and project setup

echo ""
echo "========================================"
echo "  HOSPITAL MANAGEMENT SYSTEM"
echo "  Auto Setup Script"
echo "========================================"
echo ""

# Check if MySQL is installed
echo "Checking MySQL installation..."
if ! command -v mysql &> /dev/null; then
    echo "ERROR: MySQL is not installed"
    echo "Please install MySQL first"
    exit 1
fi
echo "✓ MySQL found"

# Check if Maven is installed
echo "Checking Maven installation..."
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed"
    echo "Please install Maven first"
    exit 1
fi
echo "✓ Maven found"

# Check if Java is installed
echo "Checking Java installation..."
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed"
    echo "Please install Java JDK 11+ first"
    exit 1
fi
echo "✓ Java found"

echo ""
echo "========================================"
echo "  Step 1: Setting up Database"
echo "========================================"
echo ""

read -sp "Enter MySQL root password: " PASS
echo ""

# Create database from SQL script
echo "Creating database from SQL script..."
mysql -u root -p"$PASS" < database/hospital_db_schema.sql
if [ $? -ne 0 ]; then
    echo "ERROR: Failed to create database"
    echo "Please check your MySQL password and try again"
    exit 1
fi
echo "✓ Database created successfully"

echo ""
echo "========================================"
echo "  Step 2: Building Project"
echo "========================================"
echo ""

echo "Cleaning and compiling project..."
mvn clean compile
if [ $? -ne 0 ]; then
    echo "ERROR: Maven build failed"
    exit 1
fi
echo "✓ Project compiled successfully"

echo ""
echo "========================================"
echo "  Step 3: Packaging Application"
echo "========================================"
echo ""

echo "Building JAR file..."
mvn package -DskipTests
if [ $? -ne 0 ]; then
    echo "ERROR: Maven package failed"
    exit 1
fi
echo "✓ JAR file created successfully"

echo ""
echo "========================================"
echo "  Setup Complete!"
echo "========================================"
echo ""
echo "Project is ready to run."
echo ""
echo "To run the application, choose one of the following:"
echo ""
echo "Option 1 - Using Maven:"
echo "  mvn exec:java -Dexec.mainClass=\"com.hospital.HospitalManagementApp\""
echo ""
echo "Option 2 - Using JAR file:"
echo "  java -jar target/HospitalManagementSystem-all.jar"
echo ""
echo "========================================"
echo ""
