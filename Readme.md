

---

# 📚 Spring Boot Library Management System

A **Library Management System backend** built with **Spring Boot**, following **layered architecture** and **session-based authentication**.

---

## 🎯 Project Goals

* Build a **secure backend** using Spring Boot
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
| `/borrow/**`     | MEMBER, ADMIN           |

---

## 🔑 Authentication Endpoints

| Method | Endpoint         | Description            | Access        |
| ------ | ---------------- | ---------------------- | ------------- |
| POST   | `/auth/register` | Register new user      | Public        |
| POST   | `/auth/login`    | Login (session-based)  | Public        |

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
* Creation timestamp

---

### 🟢 Phase 2 – Master Data Management

---

## 👤 Author Endpoints

| Method | Endpoint               | Description      | Access |
| ------ | -----------------------| ---------------- | ------ |
| POST   | `/authors/add`         | Create author    | ADMIN  |
| PUT    | `/authors/update/{id}` | Update author    | ADMIN  |
| DELETE | `/authors/delete/{id}` | Delete author    | ADMIN  |
| GET    | `/authors/list`        | List all authors | Public |
| GET    | `/authors/get/{id}`    | Get author by ID | Public |

---

## 🏷 Category Endpoints

| Method | Endpoint                  | Description     | Access |
| ------ | ------------------------- | --------------- | ------ |
| POST   | `/categories/add`         | Create category | ADMIN  |
| PUT    | `/categories/update/{id}` | Update category | ADMIN  |
| DELETE | `/categories/delete/{id}` | Delete category | ADMIN  |
| GET    | `/categories`             | List categories | Public |

---

### 🟢 Phase 3 – Inventory Management (Books)

---

## 📚 Book Endpoints

| Method | Endpoint                | Description              | Access |
| ------ | ----------------------- | ------------------------ | ------ |
| POST   | `/books/add`            | Add new book             | ADMIN  |
| PUT    | `/books/update/{id}`    | Update book              | ADMIN  |
| DELETE | `/books/delete/{id}`    | Delete book              | ADMIN  |
| GET    | `/books/all`            | Paginated book list      | Public |
| GET    | `/books/search/{title}` | Search by title/category | Public |
| GET    | `/books/get/{id}`       | Get book details         | Public |

### 🔎 Key Features

* ISBN uniqueness constraint
* Indexed title search
* Pagination support
* Optimistic locking via `@Version`

---

### 🟢 Phase 4 – Borrowing & Returning Logic

---

## 🔄 Borrowing Endpoints

| Method | Endpoint                        | Description                | Access |
| ------ | ------------------------------- | -------------------------- | ------ |
| POST   | `/borrow`                       | Borrow a book              | MEMBER |
| POST   | `/borrow/return/{borrowerId}`   | Return a borrowed book     | MEMBER |
| GET    | `/borrow/history/borrowerId`    | View user's borrow history | ADMIN  |


## 📊 Actuator Endpoints

| Endpoint            | Description               |
| ------------------- | ------------------------- |
| `/actuator/health`  | Application health        |
| `/actuator/metrics` | JVM & application metrics |

---
