╔═════════════╗
║ Scenario :  ║
╚═════════════╝
A growing IT company wants to replace its outdated Excel-based employee record-keeping system with a centralized web-based Employee Management System. The system should allow HR, Managers, and Employees to interact securely, ensuring data integrity and access control. The company wants a scalable solution that integrates with Oracle for persistence and follows industry best practices.

📌 Use Cases & Features
User Authentication & Role-Based Access Control (RBAC)
Admin (HR Manager): Can manage all employees, assign roles, and perform CRUD operations.
Manager: Can view and manage their team members.
Employee: Can only view/update their own profile.
🔹 Implementation Details:
✅ Use Spring Security + JWT for authentication
✅ Implement role-based access control (RBAC)

Employee Management (CRUD Operations)
✅ Create Employee – HR/Admin can add new employees with details like name, department, salary, and role.
✅ Read Employee – Employees can view their profiles, managers can view their team, and HR/Admin can access all records.
✅ Update Employee – Employees can update personal details, managers can update team members, and HR/Admin can modify all records.
✅ Delete Employee – Only Admin can remove employees.

🔹 Implementation Details:
✅ Use Spring Boot REST APIs for CRUD operations
✅ Integrate Oracle database with JPA/Hibernate
✅ Validate inputs using Spring Boot validation

Search & Filtering
✅ Search employees by name, department, role, or ID
✅ Filter employees based on salary range, join date, and active status
✅ Paginate results for large datasets

🔹 Implementation Details:
✅ Use Spring Data JPA with custom queries
✅ Implement pagination & sorting

Department Management
✅ Maintain a list of departments (e.g., HR, IT, Sales, Finance)
✅ Assign employees to departments
✅ Track headcount and average salary per department

🔹 Implementation Details:
✅ Create Department Entity and establish One-to-Many relationship with Employee

Attendance & Leave Management (Optional - Advanced Feature)
✅ Employees can request leaves
✅ Managers can approve/reject leave requests
✅ Track leave balance & history

🔹 Implementation Details:
✅ Maintain Leave Request Entity
✅ Implement Workflow for approvals

Exception Handling & Logging
✅ Handle invalid requests (e.g., deleting a non-existent employee)
✅ Log API calls for monitoring & debugging

🔹 Implementation Details:
✅ Use @ControllerAdvice for global exception handling
✅ Implement SLF4J + Logback for structured logging

Unit & Integration Testing
✅ Write JUnit & Mockito tests for services and controllers
✅ Validate API responses and business logic

🔹 Implementation Details:
✅ Use MockMvc for testing REST APIs
✅ Write repository tests using Oracle test containers


*** Database Details ***

📌 Table: employees (Employee Details)

Stores all employee-related information.

╔═══════════════╦═══════════════╦════════════════════════════════════════════════════════════╗
║ Column   Name ║  Data   Type  ║                         Constraints                        ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ id            ║ NUMBER(10)    ║ PRIMARY KEY, AUTO_INCREMENT                                ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ first_name    ║ VARCHAR2(100) ║ NOT NULL                                                   ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ last_name     ║ VARCHAR2(100) ║ NOT NULL                                                   ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ email         ║ VARCHAR2(150) ║ UNIQUE, NOT NULL                                           ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ phone_number  ║ VARCHAR2(15)  ║ UNIQUE, NOT NULL                                           ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ salary        ║ NUMBER(10,2)  ║ CHECK (salary >= 0)                                        ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ hire_date     ║ DATE          ║ NOT NULL                                                   ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ department_id ║ NUMBER(10)    ║ FOREIGN KEY (departments.id)                               ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ manager_id    ║ NUMBER(10)    ║ FOREIGN KEY (employees.id) (Self-Referencing for Managers) ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ role_id       ║ NUMBER(10)    ║ FOREIGN KEY (roles.id)                                     ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ created_at    ║ TIMESTAMP     ║ DEFAULT CURRENT_TIMESTAMP                                  ║
╠═══════════════╬═══════════════╬════════════════════════════════════════════════════════════╣
║ updated_at    ║ TIMESTAMP     ║ DEFAULT CURRENT_TIMESTAMP ON UPDATE                        ║
╚═══════════════╩═══════════════╩════════════════════════════════════════════════════════════╝

📌 Table: departments (Department Details)

Stores department details.

╔═══════════════╦═══════════════╦═════════════════════════════╗
║ Column   Name ║  Data   Type  ║         Constraints         ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ id            ║ NUMBER(10)    ║ PRIMARY KEY, AUTO_INCREMENT ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ name          ║ VARCHAR2(100) ║ UNIQUE, NOT NULL            ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ created_at    ║ TIMESTAMP     ║ DEFAULT CURRENT_TIMESTAMP   ║
╠═══════════════╬═══════════════╬═════════════════════════════╣

📌 Table: roles (User Roles - Admin, Manager, Employee)

Defines roles for users.

╔═══════════════╦══════════════╦═════════════════════════════╗
║ Column   Name ║  Data   Type ║         Constraints         ║
╠═══════════════╬══════════════╬═════════════════════════════╣
║ id            ║ NUMBER(10)   ║ PRIMARY KEY, AUTO_INCREMENT ║
╠═══════════════╬══════════════╬═════════════════════════════╣
║ role_name     ║ VARCHAR2(50) ║ UNIQUE, NOT NULL            ║
╠═══════════════╬══════════════╬═════════════════════════════╣
║ created_at    ║ TIMESTAMP    ║ DEFAULT CURRENT_TIMESTAMP   ║
╠═══════════════╬══════════════╬═════════════════════════════╣
Predefined Values:

1. ADMIN
2. MANAGER
3. EMPLOYEE

📌 Table: users (Login Credentials)

Stores authentication details for employees.

╔═══════════════╦═══════════════╦═════════════════════════════╗
║ Column   Name ║  Data   Type  ║         Constraints         ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ id            ║ NUMBER(10)    ║ PRIMARY KEY, AUTO_INCREMENT ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ employee_id   ║ NUMBER(10)    ║ FOREIGN KEY (employees.id)  ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ username      ║ VARCHAR2(100) ║ UNIQUE, NOT NULL            ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ password      ║ VARCHAR2(255) ║ NOT NULL                    ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ role_id       ║ NUMBER(10)    ║ FOREIGN KEY (roles.id)      ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ created_at    ║ TIMESTAMP     ║ DEFAULT CURRENT_TIMESTAMP   ║
╚═══════════════╩═══════════════╩═════════════════════════════╝

📌 TABLE: LEAVE_REQUESTS (EMPLOYEE LEAVE MANAGEMENT)

Stores employee leave requests.

╔═══════════════╦══════════════╦══════════════════════════════════════════════════════════════════════╗
║ Column   Name ║  Data   Type ║                              Constraints                             ║
╠═══════════════╬══════════════╬══════════════════════════════════════════════════════════════════════╣
║ id            ║ NUMBER(10)   ║ PRIMARY KEY, AUTO_INCREMENT                                          ║
╠═══════════════╬══════════════╬══════════════════════════════════════════════════════════════════════╣
║ employee_id   ║ NUMBER(10)   ║ FOREIGN KEY (employees.id)                                           ║
╠═══════════════╬══════════════╬══════════════════════════════════════════════════════════════════════╣
║ leave_type    ║ VARCHAR2(50) ║ CHECK (leave_type IN ('Sick Leave', 'Casual Leave', 'Paid   Leave')) ║
╠═══════════════╬══════════════╬══════════════════════════════════════════════════════════════════════╣
║ start_date    ║ DATE         ║ NOT NULL                                                             ║
╠═══════════════╬══════════════╬══════════════════════════════════════════════════════════════════════╣
║ end_date      ║ DATE         ║ NOT NULL                                                             ║
╠═══════════════╬══════════════╬══════════════════════════════════════════════════════════════════════╣
║ status        ║ VARCHAR2(50) ║ CHECK (status IN ('Pending', 'Approved', 'Rejected'))                ║
╠═══════════════╬══════════════╬══════════════════════════════════════════════════════════════════════╣
║ created_at    ║ TIMESTAMP    ║ DEFAULT CURRENT_TIMESTAMP                                            ║
╚═══════════════╩══════════════╩══════════════════════════════════════════════════════════════════════╝

📌 Table: audit_logs (System Logs)

Stores all system-level activities for auditing.

╔═══════════════╦═══════════════╦═════════════════════════════╗
║ Column   Name ║  Data   Type  ║         Constraints         ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ id            ║ NUMBER(10)    ║ PRIMARY KEY, AUTO_INCREMENT ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ employee_id   ║ NUMBER(10)    ║ FOREIGN KEY (employees.id)  ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ action        ║ VARCHAR2(255) ║ NOT NULL                    ║
╠═══════════════╬═══════════════╬═════════════════════════════╣
║ timestamp     ║ TIMESTAMP     ║ DEFAULT CURRENT_TIMESTAMP   ║
╚═══════════════╩═══════════════╩═════════════════════════════╝