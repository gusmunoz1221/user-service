# Hotel Service 🏨

Microservice for hotel management as part of a hotel booking system.

---

## 📌 About
This service is responsible for managing hotel data, including:
- Hotel name
- Location
- Description
- Other related information

It is designed as an independent microservice that integrates with other services such as **User Service**, **Rating Service**, and **Booking Service**.

---

## ✨ Features
- Create, read, update, and delete (CRUD) operations for hotels.
- Validation with DTOs and Bean Validation annotations.
- Structured response handling with DTOs.
- Database persistence via Spring Data JPA.

---

## 🛠️ Tech Stack
- **Java 21+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **Hibernate Validator**
- **PostgreSQL** (or the DB you’re using)
- **Maven**

---

## 🚀 Endpoints (basic)
| Method | Endpoint         | Description          |
|--------|-----------------|----------------------|
| POST   | `/hotels`       | Create a new hotel   |
| GET    | `/hotels/{id}`  | Get hotel by ID      |
| GET    | `/hotels`       | Get all hotels       |
| PUT    | `/hotels/{id}`  | Update hotel details |
| DELETE | `/hotels/{id}`  | Delete a hotel       |

---

## ⚡ How to Run
1. Clone the repository  
   ```bash
   git clone https://github.com/gusmunoz1221/hotel-service.git
   ```bash
   git clone https://github.com/your-username/hotel-service.git
