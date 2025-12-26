# Real Estate CRM - Modular Monolith Backend

A production-grade Spring Boot application designed to manage real estate operations, agent commissions, and lead tracking using a modular monolithic architecture.

# 🏗 Architecture & Modules

### The system is divided into logical modules to ensure high cohesion and low coupling:

    - Identity Module: Manages Agent profiles, licensing, and base commission rates.

    - Inventory Module: Handles property listings and links them to listing agents.

    - CRM Module: Tracks buyer leads and assigns them to selling agents.

    - Commission Module: The "Handshake" engine that calculates payouts and office cuts across modules.

    - Office Module: Manages global brokerage settings and fee structures.

# 🚀 Tech Stack

    - Java 21 & Spring Boot 4.0.1

    - Spring Data JPA: Persistence layer with Hibernate 7.

    - PostgreSQL: Primary relational database.

    - Hibernate Spatial: Support for geographic and location-based data.

    - Jakarta Validation: Robust server-side data integrity.

    - Spring Security: Role-based access control (CORS enabled for Frontend).

## 🛠 Setup & Installation

### Prerequisites

    - JDK 21

    - Maven 3+

    - PostgreSQL running on localhost:5432

## Environment Configuration

Update src/main/resources/application.properties with your credentials:

### Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/real_estate_crm_db
spring.datasource.username=your_username
spring.datasource.password=your_password

### Running the App

Bash

./mvnw clean install
./mvnw spring-boot:run

## 📡 API Endpoints

### Method Endpoint Description

GET /api/agents Retrieve all registered agents.

POST /api/commissions/process Process a sale and calculate agent/office splits.

GET /api/commissions/agent-report/{id} Get net earnings and deal count for a specific agent.

## API Documentation:

Once the server is running, documentation is available via Swagger UI at http://localhost:8080/swagger-ui/index.html.

🧪 Validation & Error Handling

## The API includes a Global Exception Handler that returns structured JSON for:

    400 Bad Request: Validation failures (e.g., negative commission rates).

    404 Not Found: Missing entities.

    DataIntegrityViolation: Database constraint breaches.

<div align="center">
  <i>"Non satis est sapiens esse; audendum est."</i>
  <br><br>
  <b>CODA EST POESIS</b>
</div>
