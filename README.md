# Recruitment & Placement Management System (RPMS)

## 1. Project Overview

The Recruitment & Placement Management System (RPMS) is a Java and MySQL-based application developed to manage recruitment and placement activities in a college.

The system provides role-based login for Admin, Recruiter, and Placement Officer. It helps manage candidates, companies, job openings, applications, and placement-related information.

## 2. Technologies Used

- Java (JDK 17)
- JDBC
- MySQL
- Maven
- VS Code

## 3. User Roles

### Admin
- Manage candidates
- Manage companies
- Manage job openings
- Manage applications
- Access recruitment and placement information

### Recruiter
- Manage recruitment-related information
- View candidates and job openings
- Manage applications

### Placement Officer
- Manage placement-related activities
- View candidate and application information
- Monitor placement information

## 4. Project Modules

- User Login and Role Management
- Candidate Management
- Company Management
- Job Management
- Application Management
- Eligibility Checking
- Placement Management

## 5. Project Structure

```text
Recruitment_Placement_Management_System
│
├── README.md
├── database
│   └── database_schema.sql
│
└── java
    ├── pom.xml
    └── src
        └── main
            └── java
                └── com
                    └── rpms
                        ├── Main.java
                        ├── User.java
                        ├── UserDAO.java
                        └── ...
```

## 6. Requirements

Install the following software before running the project:

- JDK 17 or later
- Apache Maven
- MySQL Server
- MySQL Workbench
- Visual Studio Code (optional)

## 7. Database Setup

1. Start MySQL Server.
2. Open MySQL Workbench.
3. Open the provided database SQL file.
4. Execute the SQL script.
5. Ensure the database `rpms_db` is created successfully.

The database stores user accounts, candidates, companies, jobs, and application-related information.

## 8. Database Configuration

The application uses the following environment variables for database connection:

- `RPMS_DB_URL`
- `RPMS_DB_USER`
- `RPMS_DB_PASSWORD`

Default database URL:

```text
jdbc:mysql://localhost:3306/rpms_db?useSSL=false&serverTimezone=UTC
```

Configure the database username and password according to your local MySQL installation.

Do not upload personal database passwords or `.env` files to GitHub.

## 9. How to Run the Project

### Step 1: Open the Project

Open the `Recruitment_Placement_Management_System` folder in VS Code.

### Step 2: Open Java Project Folder

Open the terminal and navigate to the `java` folder.

### Step 3: Compile the Project

```bash
mvn clean compile
```

### Step 4: Run the Application

```bash
mvn exec:java "-Dexec.mainClass=com.rpms.Main"
```

### Step 5: Login

Select the appropriate login role and enter the username and password.

After successful login, the system displays the menu according to the user's role.

## 10. Demo Login Credentials

The following demo accounts are used for evaluation.

### Admin
- Login Role: `1`
- Username: `admin`
- Password: Provided separately for evaluation

### Recruiter
- Login Role: `2`
- Username: `recruiter`
- Password: Provided separately for evaluation

### Placement Officer
- Login Role: `3`
- Username: `placementofficer`
- Password: Provided separately for evaluation

### Login Instructions

1. Run the application.
2. Select the login role:
   - `1` for Admin
   - `2` for Recruiter
   - `3` for Placement Officer
3. Enter the username.
4. Enter the corresponding password.
5. Access the role-specific menu.

Note: Demo accounts must exist in the database before login. Demo passwords will be shared separately with the evaluator.

## 11. Application Interface

The current application uses a menu-driven Java console interface.

Users interact with the system through the terminal by selecting menu options and entering the required information.

## 12. Project Scope

RPMS demonstrates the use of Java, JDBC, and MySQL for managing recruitment and placement information.

The application includes role-based login and menu-driven operations for recruitment and placement management.

## 13. Future Enhancements

- Graphical User Interface (GUI)
- Web-based application
- Resume upload
- Email notifications
- Advanced placement reports
- Improved dashboard and analytics

## 14. Conclusion

The Recruitment & Placement Management System provides a centralized approach to managing recruitment and placement activities in a college.

It helps organize candidate information, company details, job openings, applications, and placement-related records using Java and MySQL.