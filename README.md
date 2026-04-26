# Ecommerce Spring Boot Application

This is a full-stack ecommerce backend application built using Spring Boot, Thymeleaf, MySQL, and Docker.

## 🚀 Features

* User authentication (login/signup)
* Product management
* Cart functionality
* Order processing
* Email notifications
* Pagination and filtering

## 🛠 Tech Stack

* Java 17
* Spring Boot 3
* Spring Data JPA (Hibernate)
* Thymeleaf
* MySQL
* Docker & Docker Compose

## 🐳 Run with Docker

```bash
docker-compose up --build
```

App will be available at:
http://localhost:8080

## ⚙️ Configuration

Environment variables:

* DB_URL
* DB_USERNAME
* DB_PASSWORD

## 📦 Project Structure

* `controller/` → Handles HTTP requests
* `service/` → Business logic
* `repository/` → Database layer
* `entity/` → JPA entities
* `templates/` → Thymeleaf views

---
