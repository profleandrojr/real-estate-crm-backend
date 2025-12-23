# Real Estate CRM - Backend API

A high-performance, spatial-aware REST API built for modern Real Estate management. This application handles property listings, geolocation data (PostGIS), and lead management pipelines.

## 🚀 Tech Stack

- **Language:** Java 21 (LTS)
- **Framework:** Spring Boot 4.0.1
- **Database:** PostgreSQL 14+ with **PostGIS** extension
- **ORM:** Spring Data JPA + Hibernate Spatial
- **Security:** Spring Security (Stateless/JWT ready)
- **Build Tool:** Maven

## 🛠️ Prerequisites

Before running the application, ensure you have the following installed:

- Java 21 SDK
- PostgreSQL 14 or higher
- PostGIS extension for PostgreSQL

## ⚙️ Database Setup

The application requires a **PostgreSQL** database with the **PostGIS** extension enabled.

1.  **Create Database:**

    ```sql
    CREATE DATABASE real_estate_crm_db;
    ```

2.  **Enable PostGIS (Crucial):**
    Connect to the specific database and run:

    ```sql
    CREATE EXTENSION postgis;
    ```

3.  **Configuration:**
    Update `src/main/resources/application.properties` with your credentials:
    ```properties
    spring.datasource.username=your_postgres_user
    spring.datasource.password=your_postgres_password
    ```

## 🏃‍♂️ How to Run

1.  **Clone the repository:**

    ```bash
    git clone [https://github.com/profleandrojr/real-estate-crm-backend.git](https://github.com/profleandrojr/real-estate-crm-backend.git)
    cd real-estate-crm-backend
    ```

2.  **Run with Maven Wrapper:**
    ```bash
    ./mvnw spring-boot:run
    ```

The server will start on `http://localhost:8080`.

## 🔌 API Endpoints (Vertical Slice)

### Properties (Listings)

#### 1. Create a Listing

Note: this listing is fictional, but inspired in the american president Donald Trump (I'm not a fun, but I know how real estate marketing is in NY).

- **URL:** `POST /api/listings`
- **Headers:** `Content-Type: application/json`
- **Body:**
  ```json
  {
    "title": "Modern Loft in Manhattan",
    "description": "Spacious open-concept loft.",
    "price": 1250000.0,
    "address": "55 W 46th St, New York, NY",
    "bedrooms": 1,
    "bathrooms": 2,
    "areaSquareMeters": 95.5,
    "latitude": 40.7562,
    "longitude": -73.98
  }
  ```

#### 2. Get All Listings

- **URL:** `GET /api/listings`
- **Response:** Returns an array of listings with latitude/longitude.

## 🔒 Security

- **Current State:** Dev Mode.
- **Configuration:** `SecurityConfig.java` permits all traffic to `/api/**` for development.
- **Future:** Will be upgraded to Stateless JWT Authentication.

## 📂 Project Structure

```text
com.realestate.crm_backend
├── config       # Security & App Configuration
├── controller   # REST API Layer
├── dto          # Data Transfer Objects (Java Records)
├── entity       # Database Models (JPA Entities)
├── repository   # Data Access Layer
└── service      # Business Logic & Geospatial Conversion
```
