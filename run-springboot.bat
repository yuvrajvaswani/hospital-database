@echo off
echo ================================================
echo Hospital Management System - Spring Boot Setup
echo ================================================
echo.

REM Check if Maven is installed
echo Checking Maven installation...
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven from: https://maven.apache.org
    echo And add it to PATH
    pause
    exit /b 1
)

echo Maven found!
echo.

REM Check if pom.xml.old exists and pom-springboot.xml exists
if exist pom.xml.old (
    echo Removing old pom.xml
    del pom.xml
    ren pom.xml.old pom.xml.old.bak
)

if exist pom-springboot.xml (
    echo Renaming pom-springboot.xml to pom.xml
    ren pom-springboot.xml pom.xml
) else (
    echo ERROR: pom-springboot.xml not found
    pause
    exit /b 1
)

echo.
echo Building project...
mvn clean install -DskipTests

if errorlevel 1 (
    echo BUILD FAILED! Check error messages above.
    pause
    exit /b 1
)

echo.
echo ================================================
echo Build successful!
echo Starting Spring Boot application...
echo ================================================
echo.
echo Application will be available at: http://localhost:8080
echo.
echo Running on port 8080...
echo Press Ctrl+C to stop
echo.

mvn spring-boot:run

pause
