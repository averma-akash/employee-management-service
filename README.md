# Employee Management System 🚀

## 📌 Project Overview
The **Employee Management System** is a Spring Boot-based web application designed to manage employee records efficiently. It supports **user authentication, role-based access control (RBAC), employee CRUD operations, and leave management** while integrating with an **Oracle database**.

## 🔥 Features
✅ **User Authentication & Role-Based Access Control** (Admin, Manager, Employee)  
✅ **Employee Management (CRUD Operations)**  
✅ **Department & Role Management**  
✅ **Leave Request & Approval Workflow**  
✅ **Exception Handling & Logging** (SLF4J + Logback)  
✅ **Unit & Integration Testing** (JUnit, Mockito, MockMvc)  

## 🏗️ Tech Stack
- **Backend:** Java 17, Spring Boot 3, Spring Security, JWT, Hibernate  
- **Database:** Oracle (JPA + Hibernate)  
- **Testing:** JUnit, Mockito  
- **Build Tool:** Maven  
- **Containerization:** Docker (Optional)  

## 📜 Database Schema

### 📌 **Employee Table**
| Column Name   | Data Type      | Constraints |
|--------------|--------------|-------------|
| `id`         | NUMBER(10)    | PRIMARY KEY, AUTO_INCREMENT |
| `first_name` | VARCHAR2(100) | NOT NULL |
| `last_name`  | VARCHAR2(100) | NOT NULL |
| `email`      | VARCHAR2(150) | UNIQUE, NOT NULL |
| `phone_number` | VARCHAR2(15) | UNIQUE, NOT NULL |
| `salary`     | NUMBER(10,2)  | CHECK (salary >= 0) |
| `hire_date`  | DATE          | NOT NULL |
| `department_id` | NUMBER(10) | FOREIGN KEY (departments.id) |
| `manager_id` | NUMBER(10) | FOREIGN KEY (employees.id) |
| `role_id` | NUMBER(10) | FOREIGN KEY (roles.id) |
| `created_at` | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |

### 📌 **Departments Table**
| Column Name   | Data Type      | Constraints |
|--------------|--------------|-------------|
| `id`         | NUMBER(10)    | PRIMARY KEY, AUTO_INCREMENT |
| `name`       | VARCHAR2(100) | UNIQUE, NOT NULL |
| `created_at` | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP |

### 📌 **Roles Table**
| Column Name   | Data Type      | Constraints |
|--------------|--------------|-------------|
| `id`         | NUMBER(10)    | PRIMARY KEY, AUTO_INCREMENT |
| `role_name`  | VARCHAR2(50)  | UNIQUE, NOT NULL |
| `created_at` | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP |

📌 **Predefined Roles:**
- **ADMIN**
- **MANAGER**
- **EMPLOYEE**

### 📌 **Users Table (Login Credentials)**
| Column Name   | Data Type      | Constraints |
|--------------|--------------|-------------|
| `id`         | NUMBER(10)    | PRIMARY KEY, AUTO_INCREMENT |
| `employee_id` | NUMBER(10) | FOREIGN KEY (employees.id) |
| `username`   | VARCHAR2(100) | UNIQUE, NOT NULL |
| `password`   | VARCHAR2(255) | NOT NULL |
| `role_id`    | NUMBER(10) | FOREIGN KEY (roles.id) |
| `created_at` | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |

### 📌 **Leave Requests Table**
| Column Name   | Data Type      | Constraints |
|--------------|--------------|-------------|
| `id`         | NUMBER(10)    | PRIMARY KEY, AUTO_INCREMENT |
| `employee_id` | NUMBER(10) | FOREIGN KEY (employees.id) |
| `leave_type` | VARCHAR2(50) | CHECK (leave_type IN ('Sick Leave', 'Casual Leave', 'Paid Leave')) |
| `start_date` | DATE          | NOT NULL |
| `end_date`   | DATE          | NOT NULL |
| `status`     | VARCHAR2(50)  | CHECK (status IN ('Pending', 'Approved', 'Rejected')) |
| `created_at` | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP |

### 📌 **Audit Logs Table**
| Column Name   | Data Type      | Constraints |
|--------------|--------------|-------------|
| `id`         | NUMBER(10)    | PRIMARY KEY, AUTO_INCREMENT |
| `employee_id` | NUMBER(10) | FOREIGN KEY (employees.id) |
| `action`     | VARCHAR2(255) | NOT NULL |
| `timestamp`  | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP |

---

## 🔄 API Endpoints

### 📌 1️⃣ Authentication & Authorization APIs (Spring Security + JWT)
| Method | Endpoint            | Description | Access Control |
|--------|---------------------|-------------|---------------|
| POST   | `/api/auth/register` | Register a new user | ADMIN |
| POST   | `/api/auth/login` | Authenticate & generate JWT Token | PUBLIC |
| POST   | `/api/auth/logout` | Invalidate JWT Token | LOGGED_IN_USERS |

### 📌 2️⃣ Employee Management APIs
| Method | Endpoint | Description | Access Control |
|--------|---------|-------------|---------------|
| POST   | `/api/employees` | Add a new employee | ADMIN |
| GET    | `/api/employees` | Get all employees (Paginated) | ADMIN, MANAGER |
| GET    | `/api/employees/{id}` | Get employee by ID | ADMIN, MANAGER, EMPLOYEE(SELF) |
| PUT    | `/api/employees/{id}` | Update employee details | ADMIN, EMPLOYEE(SELF) |
| DELETE | `/api/employees/{id}` | Delete an employee | ADMIN |
| GET    | `/api/employees/search` | Search employees by name, department, etc. | ADMIN, MANAGER |

### 📌 3️⃣ Department Management APIs
| Method | Endpoint | Description | Access Control |
|--------|---------|-------------|---------------|
| POST   | `/api/departments` | Create a new department | ADMIN |
| GET    | `/api/departments` | Get all departments | ADMIN, MANAGER |
| GET    | `/api/departments/{id}` | Get department by ID | ADMIN, MANAGER |
| PUT    | `/api/departments/{id}` | Update department details | ADMIN |
| DELETE | `/api/departments/{id}` | Delete department | ADMIN |

### 📌 4️⃣ Role Management APIs
| Method | Endpoint | Description | Access Control |
|--------|---------|-------------|---------------|
| GET    | `/api/roles` | Get all roles | ADMIN, MANAGER |
| GET    | `/api/roles/{id}` | Get role by ID | ADMIN, MANAGER |

### 📌 5️⃣ Leave Management APIs
| Method | Endpoint | Description | Access Control |
|--------|---------|-------------|---------------|
| POST   | `/api/leaves` | Request a leave | EMPLOYEE |
| GET    | `/api/leaves` | Get all leave requests | ADMIN, MANAGER |
| GET    | `/api/leaves/{id}` | Get leave request by ID | ADMIN, MANAGER, EMPLOYEE(SELF) |
| PUT    | `/api/leaves/{id}/approve` | Approve leave request | MANAGER |
| PUT    | `/api/leaves/{id}/reject` | Reject leave request | MANAGER |

### 📌 6️⃣ Audit Log APIs
| Method | Endpoint | Description | Access Control |
|--------|---------|-------------|---------------|
| GET    | `/api/logs` | Get all system logs | ADMIN |
| GET    | `/api/logs/{id}` | Get log entry by ID | ADMIN |

---

## 🛠️ Setup & Installation

### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/averma-akash/employee-management-service.git
cd employee-management-system
