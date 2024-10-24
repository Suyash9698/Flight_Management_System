# Flight Management System

## Project Overview

The **Flight Management System** is a Java-based application developed to facilitate flight booking and management. It consolidates real-time data from airline carriers, allowing customers to book, view, update, and cancel flights with ease. Administrators can manage all flight, schedule, and route data in one system, making it an efficient solution for both travelers and airlines.

---

## Features

### Customer Features:

- **Account Creation:** Customers can create accounts to manage their bookings.
- **Login and Authentication:** Secure login for customers.
- **View Flights:** Check available flights, schedules, and routes.
- **Book Flights:** Make a flight reservation.
- **Modify or Cancel Booking:** Update or cancel existing reservations.
- **View Booking History:** Access past bookings.

### Administrator Features:

- **Admin Login:** Administrators can securely log in to manage the system.
- **Manage Flights:** Add, view, modify, or delete flight information.
- **Manage Schedules:** Administer flight schedules and avoid conflicts.
- **Manage Routes:** Set and update flight routes between different airports.

---

## Project Scope

### In-Scope:

- Customer and Administrator access with distinct privileges.
- Full customer functionality for managing flight bookings.
- Administrative features for flight, schedule, and route management.

### Out-of-Scope:

- Boarding pass generation.
- Third-party integrations like SMS and email notifications.
- Payment processing functionality.

---

## Technologies Used

- **Java**
- **Spring Framework (for Backend)**
- **MySQL (Database)**
- **JDBC**
- **HTML/CSS/JavaScript (for Frontend)**

---

## Modules Implemented

1. **User Authentication and Registration:**
   - User account creation, login, email verification, and password reset.

2. **Flight Booking System:**
   - Flight booking interface with flight selection, time slots, and source/destination airports.

3. **Schedule Management:**
   - Manage flight schedules and prevent double bookings.

4. **Passenger Information Management:**
   - Store and manage passenger data securely.

5. **Admin Dashboard:**
   - A complete dashboard for administrators to manage users, flights, schedules, and routes.

---

## Installation and Setup

### Prerequisites:

- Java Development Kit (JDK) 8+
- MySQL Database
- Maven or Gradle for project dependencies

### Steps to Run the Project:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-github-username/flight-management-system.git
   cd flight-management-system
   ```

## Configure the Database:

Run the provided SQL script to create the necessary tables:

```sql
CREATE DATABASE flight_management;

USE flight_management;

CREATE TABLE users (
    userId BIGINT PRIMARY KEY AUTO_INCREMENT,
    userType VARCHAR(50),
    userName VARCHAR(100),
    userPassword VARCHAR(100),
    userPhone BIGINT,
    userEmail VARCHAR(100)
);

-- Add other necessary tables

```
## Configure the Application:

Update the `application.properties` file located in `src/main/resources` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flight_management
spring.datasource.username=root
spring.datasource.password=your_password

```
## Build and Run the Application:

### Using Maven:

```bash
mvn spring-boot:run
```

### Using Gradle:

```bash
gradle bootRun
```
## Access the Application:
```
http://localhost:8080

```
## Project Timeline

### Milestone 1: User Authentication and Flight Availability
- Completed user authentication and registration (Weeks 1-3).

### Milestone 2: Booking System
- Developed and integrated flight booking system (Weeks 4-5).

### Milestone 3: Schedule Management and Passenger Information
- Implemented schedule management and passenger data security (Weeks 6-7).

### Milestone 4: Admin Dashboard and Final Integration
- Completed admin dashboard and final feature integrations (Weeks 8-10).

## Future Enhancements
- **Payment Integration:** Add payment gateways for booking flights.
- **Boarding Pass Generation:** Automatically generate boarding passes after booking.
- **Email/SMS Notifications:** Integrate email or SMS notifications for booking confirmations.


