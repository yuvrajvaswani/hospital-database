@echo off
REM Hospital Management System - Quick Setup Script for Windows
REM This script automates the database creation and project setup

echo.
echo ========================================
echo   HOSPITAL MANAGEMENT SYSTEM
echo   Auto Setup Script
echo ========================================
echo.

REM Check if MySQL is installed
echo Checking MySQL installation...
mysql --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: MySQL is not installed or not in PATH
    echo Please install MySQL and add it to system PATH
    pause
    exit /b 1
)
echo ✓ MySQL found

REM Check if Maven is installed
echo Checking Maven installation...
mvn --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and add it to system PATH
    pause
    exit /b 1
)
echo ✓ Maven found

REM Check if Java is installed
echo Checking Java installation...
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java JDK 11+ and add it to system PATH
    pause
    exit /b 1
)
echo ✓ Java found

echo.
echo ========================================
echo   Step 1: Setting up Database
echo ========================================
echo.

set /p PASS="Enter MySQL root password: "

REM Create database from SQL script
echo Creating database from SQL script...
mysql -u root -p%PASS% < database\hospital_db_schema.sql
if errorlevel 1 (
    echo ERROR: Failed to create database
    echo Please check your MySQL password and try again
    pause
    exit /b 1
)
echo ✓ Database created successfully

echo.
echo ========================================
echo   Step 2: Building Project
echo ========================================
echo.

echo Cleaning and compiling project...
call mvn clean compile
if errorlevel 1 (
    echo ERROR: Maven build failed
    pause
    exit /b 1
)
echo ✓ Project compiled successfully

echo.
echo ========================================
echo   Step 3: Packaging Application
echo ========================================
echo.

echo Building JAR file...
call mvn package -DskipTests
if errorlevel 1 (
    echo ERROR: Maven package failed
    pause
    exit /b 1
)
echo ✓ JAR file created successfully

echo.
echo ========================================
echo   Setup Complete!
echo ========================================
echo.
echo Project is ready to run.
echo.
echo To run the application, choose one of the following:
echo.
echo Option 1 - Using Maven:
echo   mvn exec:java -Dexec.mainClass="com.hospital.HospitalManagementApp"
echo.
echo Option 2 - Using JAR file:
echo   java -jar target\HospitalManagementSystem-all.jar
echo.
echo ========================================
echo.
pause
