# User Service 👤

Microservice for user management as part of a hotel booking system.

---

## 📌 About
This service is responsible for managing user data, including:
- User name
- Email (with strict validation)
- Description

It provides basic CRUD functionality and ensures security constraints such as **unique email addresses**.

---

## ✨ Features
- Create, read, update, and delete (CRUD) operations for users.
- DTO-based validation using Bean Validation (`@NotBlank`, `@Size`, `@Pattern` for strict email validation).
- Unique constraint enforcement for email.
- Mapper for DTO ↔ Entity conversion.
- Structured error handling via a **GlobalExceptionHandler** that returns a unified `ApiResponse`.

---

## 🛠️ Tech Stack
- **Java 21+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **Hibernate Validator**
- **MySQL**
- **Maven**

---

## 🚀 Endpoints (basic)
| Method | Endpoint        | Description          |
|--------|----------------|----------------------|
| POST   | `/users`       | Create a new user    |
| GET    | `/users/{id}`  | Get user by ID       |
| GET    | `/users`       | Get all users        |
| PUT    | `/users/{id}`  | Update user details  |
| DELETE | `/users/{id}`  | Delete a user        |

---

## ⚡ How to Run
1. Clone the repository  
   ```bash
   git clone https://github.com/gusmunoz1221/user-service.git
