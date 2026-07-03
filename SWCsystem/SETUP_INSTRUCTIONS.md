# SWC System - Login and Registration Setup

## Prerequisites
1. **XAMPP** installed and running
2. **Java 17** or higher
3. **Maven** installed

## Database Setup

### 1. Start XAMPP Services
- Start **Apache** and **MySQL** services in XAMPP Control Panel
- Open phpMyAdmin by going to `http://localhost/phpmyadmin`

### 2. Create Database
```sql
CREATE DATABASE swcsystem_db;
```

### 3. Database Configuration
The application is configured to connect to:
- **Host:** localhost
- **Port:** 3306
- **Database:** swcsystem_db
- **Username:** root
- **Password:** (empty - default XAMPP setup)

## Running the Application

### 1. Build the Project
```bash
mvn clean install
```

### 2. Run the Application
```bash
mvn spring-boot:run
```

### 3. Access the Application
- Open your browser and go to: `http://localhost:8080`
- You will be redirected to the login page

## Features

### Registration
- Navigate to `/register` to create a new account
- Required fields: Username, Email, Password
- Optional fields: First Name, Last Name
- Password must be at least 6 characters
- Username and email must be unique

### Login
- Navigate to `/login` to access your account
- Use either username or email to login
- After successful login, you'll be redirected to the dashboard

### Dashboard
- View user information
- Access to system features (Products, Categories, Analytics, Settings)
- Logout functionality

## Security Features
- Password encryption using BCrypt
- Session management
- CSRF protection (disabled for development)
- User authentication and authorization

## Database Tables
The application will automatically create the following table:
- **users** - Stores user information including credentials

## Troubleshooting

### Database Connection Issues
1. Ensure XAMPP MySQL is running
2. Check if database `swcsystem_db` exists
3. Verify MySQL credentials in `application.properties`

### Application Won't Start
1. Check if port 8080 is available
2. Ensure Java 17+ is installed
3. Run `mvn clean install` to rebuild

### Login Issues
1. Verify user exists in database
2. Check password encryption
3. Ensure username/email is correct

## Default Configuration
- **Application Port:** 8080
- **Database:** MySQL (XAMPP)
- **Session Management:** In-memory
- **Password Encoding:** BCrypt





