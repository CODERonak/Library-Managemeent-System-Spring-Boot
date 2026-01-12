

---

# 📚 Spring Boot Library Management System

A **Library Management System backend** built with **Spring Boot**, following **layered architecture** and **session-based authentication**.

---

## 🎯 Project Goals

* Build a **secure, scalable backend** using Spring Boot
* Enforce **clean architecture and separation of concerns**
* Ensure **data correctness under concurrent access**
* Optimize performance using **indexing and pagination**
* Apply **industry-standard security practices** with Spring Security

---

## 🧱 Key Principles

* Single Responsibility Principle (SRP)
* DTOs at system boundaries
* Service-layer business logic
* Repository abstraction with JPA
* Centralized exception handling

---

## 🛠 Tech Stack

* **Java 21+**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Spring Security (Session-Based)**
* **Hibernate**
* **PostgreSQL / MySQL**
* **BCrypt Password Hashing**
* **Spring Validation**
* **Spring Actuator**

---

## 🔐 Security Model

* **Stateful session-based authentication**
* **Spring Security–managed sessions**
* **BCrypt password hashing**
* **Role-Based Access Control (RBAC)**

### 👥 User Roles

* `ADMIN`
* `LIBRARIAN`
* `MEMBER`

### 🔒 Authorization Rules

| Endpoint Pattern | Access                  |
| ---------------- | ----------------------- |
| `/auth/**`       | Public                  |
| `/authors/**`    | ADMIN, LIBRARIAN        |
| `/categories/**` | ADMIN, LIBRARIAN        |
| `/books/**`      | Read: All, Write: ADMIN |
| `/borrow/**`     | MEMBER                  |

---

## 🔑 Authentication Endpoints

| Method | Endpoint         | Description            | Access        |
| ------ | ---------------- | ---------------------- | ------------- |
| POST   | `/auth/register` | Register new user      | Public        |
| POST   | `/auth/login`    | Login (session-based)  | Public        |
| POST   | `/auth/logout`   | Logout current session | Authenticated |

---

## 🧩 Application Phases

---

### 🟢 Phase 0 – System Initialization

* Spring Boot project setup
* Database configuration
* Layered package structure
* Health check verification

**Status**

* ✅ Application starts correctly
* ✅ Database connectivity verified

---

### 🟢 Phase 1 – Authentication & Authorization

#### User Entity

* Email (unique)
* BCrypt-hashed password
* Role-based access
* Enabled flag
* Creation timestamp

---

### 🟢 Phase 2 – Master Data Management

---

## 👤 Author Endpoints

| Method | Endpoint        | Description      | Access |
| ------ | --------------- | ---------------- | ------ |
| POST   | `/authors`      | Create author    | ADMIN  |
| PUT    | `/authors/{id}` | Update author    | ADMIN  |
| DELETE | `/authors/{id}` | Delete author    | ADMIN  |
| GET    | `/authors`      | List all authors | Public |
| GET    | `/authors/{id}` | Get author by ID | Public |

---

## 🏷 Category Endpoints

| Method | Endpoint           | Description     | Access |
| ------ | ------------------ | --------------- | ------ |
| POST   | `/categories`      | Create category | ADMIN  |
| PUT    | `/categories/{id}` | Update category | ADMIN  |
| DELETE | `/categories/{id}` | Delete category | ADMIN  |
| GET    | `/categories`      | List categories | Public |

---

### 🟢 Phase 3 – Inventory Management (Books)

---

## 📚 Book Endpoints

| Method | Endpoint        | Description              | Access |
| ------ | --------------- | ------------------------ | ------ |
| POST   | `/books`        | Add new book             | ADMIN  |
| PUT    | `/books/{id}`   | Update book              | ADMIN  |
| DELETE | `/books/{id}`   | Delete book              | ADMIN  |
| GET    | `/books`        | Paginated book list      | Public |
| GET    | `/books/search` | Search by title/category | Public |
| GET    | `/books/{id}`   | Get book details         | Public |

### 🔎 Key Features

* ISBN uniqueness constraint
* Indexed title search
* Pagination support
* Optimistic locking via `@Version`

---

### 🟢 Phase 4 – Borrowing & Returning Logic

---

## 🔄 Borrowing Endpoints

| Method | Endpoint                    | Description                | Access |
| ------ | --------------------------- | -------------------------- | ------ |
| POST   | `/borrow/{bookId}`          | Borrow a book              | MEMBER |
| POST   | `/borrow/return/{borrowId}` | Return a borrowed book     | MEMBER |
| GET    | `/borrow/my`                | View user's borrow history | MEMBER |

### ⚙️ Concurrency Strategy

* `@Transactional` service methods
* Optimistic locking to prevent lost updates
* Fail-fast validations

**Guarantees**

* ✅ ACID-compliant operations
* ✅ Race-condition-safe borrowing

---

## ⚙️ Observability & Monitoring

---

## 📊 Actuator Endpoints

| Endpoint            | Description               |
| ------------------- | ------------------------- |
| `/actuator/health`  | Application health        |
| `/actuator/metrics` | JVM & application metrics |

---
