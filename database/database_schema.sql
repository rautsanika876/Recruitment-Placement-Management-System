CREATE DATABASE IF NOT EXISTS rpms_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE rpms_db;

CREATE TABLE roles (
 role_id INT PRIMARY KEY AUTO_INCREMENT,
 role_code VARCHAR(30) NOT NULL UNIQUE,
 role_name VARCHAR(80) NOT NULL,
 is_active TINYINT(1) DEFAULT 1,
 created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE users (
 user_id INT PRIMARY KEY AUTO_INCREMENT,
 username VARCHAR(80) NOT NULL UNIQUE,
 password_hash VARCHAR(255) NOT NULL,
 full_name VARCHAR(120) NOT NULL,
 email VARCHAR(150) UNIQUE,
 role_id INT NOT NULL,
 status VARCHAR(20) DEFAULT 'ACTIVE',
 last_login_at DATETIME NULL,
 created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY(role_id) REFERENCES roles(role_id)
);
CREATE TABLE candidates (
 candidate_id INT PRIMARY KEY AUTO_INCREMENT,
 candidate_code VARCHAR(30) NOT NULL UNIQUE,
 full_name VARCHAR(150) NOT NULL,
 email VARCHAR(150) NOT NULL UNIQUE,
 mobile VARCHAR(20) NOT NULL UNIQUE,
 date_of_birth DATE NULL,
 gender VARCHAR(20) NULL,
 qualification VARCHAR(80) NOT NULL,
 college VARCHAR(180),
 university VARCHAR(180),
 passing_year SMALLINT NOT NULL,
 percentage DECIMAL(5,2),
 cgpa DECIMAL(4,2),
 backlog_count INT NOT NULL DEFAULT 0,
 primary_technology VARCHAR(80),
 current_location VARCHAR(100),
 preferred_location VARCHAR(100),
 profile_status VARCHAR(20) DEFAULT 'ACTIVE',
 placement_status VARCHAR(20) DEFAULT 'NOT_PLACED',
 created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 CHECK (percentage IS NULL OR (percentage >= 0 AND percentage <= 100)),
 CHECK (backlog_count >= 0)
);
CREATE TABLE skills (
 skill_id INT PRIMARY KEY AUTO_INCREMENT,
 skill_name VARCHAR(100) NOT NULL UNIQUE,
 category VARCHAR(60),
 is_active TINYINT(1) DEFAULT 1
);
CREATE TABLE candidate_skills (
 candidate_skill_id INT PRIMARY KEY AUTO_INCREMENT,
 candidate_id INT NOT NULL,
 skill_id INT NOT NULL,
 proficiency_level VARCHAR(20),
 years_experience DECIMAL(4,1) DEFAULT 0,
 is_primary TINYINT(1) DEFAULT 0,
 UNIQUE(candidate_id,skill_id),
 FOREIGN KEY(candidate_id) REFERENCES candidates(candidate_id),
 FOREIGN KEY(skill_id) REFERENCES skills(skill_id)
);
CREATE TABLE companies (
 company_id INT PRIMARY KEY AUTO_INCREMENT,
 company_code VARCHAR(30) NOT NULL UNIQUE,
 company_name VARCHAR(180) NOT NULL,
 industry VARCHAR(100),
 website VARCHAR(200),
 location VARCHAR(120),
 company_type VARCHAR(30),
 hr_name VARCHAR(120),
 hr_email VARCHAR(150),
 hr_mobile VARCHAR(20),
 status VARCHAR(20) DEFAULT 'ACTIVE',
 created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE jobs (
 job_id INT PRIMARY KEY AUTO_INCREMENT,
 job_code VARCHAR(40) NOT NULL UNIQUE,
 company_id INT NOT NULL,
 job_title VARCHAR(150) NOT NULL,
 employment_type VARCHAR(30) DEFAULT 'FULL_TIME',
 location VARCHAR(120) NOT NULL,
 openings INT NOT NULL,
 min_package_lpa DECIMAL(6,2),
 max_package_lpa DECIMAL(6,2),
 min_percentage DECIMAL(5,2),
 min_passing_year SMALLINT,
 max_passing_year SMALLINT,
 max_backlogs INT DEFAULT 0,
 experience_min_years DECIMAL(4,1) DEFAULT 0,
 qualification_rule VARCHAR(255),
 posted_date DATE NOT NULL,
 application_deadline DATE NOT NULL,
 status VARCHAR(20) DEFAULT 'OPEN',
 created_by INT NULL,
 created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY(company_id) REFERENCES companies(company_id),
 FOREIGN KEY(created_by) REFERENCES users(user_id),
 CHECK(openings > 0),
 CHECK(min_package_lpa IS NULL OR max_package_lpa IS NULL OR min_package_lpa <= max_package_lpa),
 CHECK(max_backlogs >= 0),
 CHECK(application_deadline >= posted_date)
);
CREATE TABLE job_skills (
 job_skill_id INT PRIMARY KEY AUTO_INCREMENT,
 job_id INT NOT NULL,
 skill_id INT NOT NULL,
 minimum_level VARCHAR(20),
 mandatory_flag TINYINT(1) DEFAULT 1,
 UNIQUE(job_id,skill_id),
 FOREIGN KEY(job_id) REFERENCES jobs(job_id),
 FOREIGN KEY(skill_id) REFERENCES skills(skill_id)
);
CREATE TABLE applications (
 application_id INT PRIMARY KEY AUTO_INCREMENT,
 application_code VARCHAR(40) NOT NULL UNIQUE,
 candidate_id INT NOT NULL,
 job_id INT NOT NULL,
 applied_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 eligibility_status VARCHAR(20) DEFAULT 'PENDING',
 eligibility_reason VARCHAR(500),
 application_status VARCHAR(30) DEFAULT 'APPLIED',
 source VARCHAR(50),
 assigned_recruiter_id INT NULL,
 updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 UNIQUE(candidate_id,job_id),
 FOREIGN KEY(candidate_id) REFERENCES candidates(candidate_id),
 FOREIGN KEY(job_id) REFERENCES jobs(job_id),
 FOREIGN KEY(assigned_recruiter_id) REFERENCES users(user_id)
);
CREATE TABLE application_history (
 history_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 application_id INT NOT NULL,
 old_status VARCHAR(30),
 new_status VARCHAR(30) NOT NULL,
 remarks VARCHAR(500),
 changed_by INT NULL,
 changed_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY(application_id) REFERENCES applications(application_id),
 FOREIGN KEY(changed_by) REFERENCES users(user_id)
);
CREATE TABLE interview_rounds (
 interview_id INT PRIMARY KEY AUTO_INCREMENT,
 application_id INT NOT NULL,
 round_number INT NOT NULL,
 round_type VARCHAR(50) NOT NULL,
 scheduled_date DATE NOT NULL,
 scheduled_time TIME,
 mode VARCHAR(20) NOT NULL,
 interviewer_name VARCHAR(120),
 meeting_or_location VARCHAR(255),
 status VARCHAR(20) DEFAULT 'SCHEDULED',
 created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 UNIQUE(application_id,round_number),
 FOREIGN KEY(application_id) REFERENCES applications(application_id)
);
CREATE TABLE interview_feedback (
 feedback_id INT PRIMARY KEY AUTO_INCREMENT,
 interview_id INT NOT NULL UNIQUE,
 technical_rating DECIMAL(3,1),
 communication_rating DECIMAL(3,1),
 problem_solving_rating DECIMAL(3,1),
 overall_rating DECIMAL(3,1),
 recommendation VARCHAR(30) NOT NULL,
 remarks TEXT,
 submitted_by INT NULL,
 submitted_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY(interview_id) REFERENCES interview_rounds(interview_id),
 FOREIGN KEY(submitted_by) REFERENCES users(user_id)
);
CREATE TABLE offers (
 offer_id INT PRIMARY KEY AUTO_INCREMENT,
 offer_code VARCHAR(40) NOT NULL UNIQUE,
 application_id INT NOT NULL UNIQUE,
 designation VARCHAR(120) NOT NULL,
 offered_package_lpa DECIMAL(6,2) NOT NULL,
 offer_date DATE NOT NULL,
 expected_joining_date DATE,
 offer_status VARCHAR(20) DEFAULT 'OFFERED',
 remarks VARCHAR(500),
 created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY(application_id) REFERENCES applications(application_id)
);
CREATE TABLE placements (
 placement_id INT PRIMARY KEY AUTO_INCREMENT,
 candidate_id INT NOT NULL,
 company_id INT NOT NULL,
 job_id INT NOT NULL,
 offer_id INT NOT NULL UNIQUE,
 designation VARCHAR(120) NOT NULL,
 final_package_lpa DECIMAL(6,2) NOT NULL,
 expected_joining_date DATE,
 actual_joining_date DATE,
 joining_status VARCHAR(20) DEFAULT 'PENDING',
 placement_status VARCHAR(20) DEFAULT 'ACTIVE',
 created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY(candidate_id) REFERENCES candidates(candidate_id),
 FOREIGN KEY(company_id) REFERENCES companies(company_id),
 FOREIGN KEY(job_id) REFERENCES jobs(job_id),
 FOREIGN KEY(offer_id) REFERENCES offers(offer_id)
);
CREATE TABLE activity_logs (
 log_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id INT NULL,
 module_name VARCHAR(60) NOT NULL,
 action_name VARCHAR(80) NOT NULL,
 record_id VARCHAR(60),
 description VARCHAR(500),
 logged_at DATETIME DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY(user_id) REFERENCES users(user_id)
);

INSERT INTO roles(role_code,role_name) VALUES
('ADMIN','Admin'),('RECRUITER','Recruiter'),('PLACEMENT_OFFICER','Placement Officer');
INSERT INTO skills(skill_name,category) VALUES
('Java','Programming'),('Python','Programming'),('SQL','Database'),('HTML','Web'),('CSS','Web'),
('JavaScript','Web'),('JDBC','Java'),('Testing','Quality'),('Excel','Analytics'),('Communication','Soft Skill');
INSERT INTO companies(company_code,company_name,industry,location,company_type,status) VALUES
('COM-001','Infosys','IT Services','Pune','MNC','ACTIVE'),
('COM-002','TechNova','Software','Mumbai','MID_SIZE','ACTIVE'),
('COM-003','CloudWorks','Cloud Services','Pune','STARTUP','ACTIVE');
INSERT INTO candidates(candidate_code,full_name,email,mobile,qualification,college,passing_year,percentage,backlog_count,primary_technology,current_location,preferred_location)
VALUES
('CAN-2026-0001','Rahul Patil','rahul.patil@example.com','9000000001','BE','Sample Engineering College',2026,68.00,0,'Java','Pune','Pune'),
('CAN-2026-0002','Priya Shah','priya.shah@example.com','9000000002','BTech','Sample Engineering College',2026,76.50,0,'Python','Pune','Mumbai'),
('CAN-2026-0003','Aman Jadhav','aman.jadhav@example.com','9000000003','MCA','Sample Institute',2025,61.00,1,'Testing','Mumbai','Pune');
INSERT INTO jobs(job_code,company_id,job_title,employment_type,location,openings,min_package_lpa,max_package_lpa,min_percentage,min_passing_year,max_passing_year,max_backlogs,qualification_rule,posted_date,application_deadline,status)
VALUES
('JOB-2026-001',1,'Associate Java Developer','FULL_TIME','Pune',15,4.50,6.00,60,2025,2027,0,'BE/BTech/MCA',CURRENT_DATE,CURRENT_DATE + INTERVAL 30 DAY,'OPEN'),
('JOB-2026-002',2,'Python Developer','FULL_TIME','Mumbai',8,3.00,5.00,65,2025,2027,0,'BE/BTech/MCA',CURRENT_DATE,CURRENT_DATE + INTERVAL 30 DAY,'OPEN');
INSERT INTO applications(application_code,candidate_id,job_id,eligibility_status,application_status,source)
VALUES ('APP-2026-0001',1,1,'ELIGIBLE','SHORTLISTED','INTERNAL'),
       ('APP-2026-0002',2,2,'ELIGIBLE','APPLIED','PORTAL');
