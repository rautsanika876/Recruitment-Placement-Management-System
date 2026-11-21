# Recruitment & Placement Management System (RPMS)

This starter project is based on the three supplied project specifications.

## Project parts
- `login.html` through `reports.html`: 12-page static HTML/CSS prototype.
- `css/style.css`: shared responsive styling.
- `js/app.js`: small prototype search/reset helpers.
- `database/database_schema.sql`: MySQL schema plus a small demo dataset.
- `database/reports.sql`: sample reporting queries.
- `java/`: starter Java console backend using JDBC, a Candidate model, DAO and eligibility service.

## 1. Run the UI prototype
1. Extract the ZIP.
2. Open the `Recruitment_Placement_Management_System` folder in VS Code.
3. Double-click `login.html`, or use VS Code Live Server if installed.
4. Click Login. This is a demo link, not a real authentication system.
5. Navigate using the left menu.

**Important:** The HTML/CSS prototype is deliberately static. Its forms show demo messages and do not save to MySQL, matching the supplied UI specification. The Java backend is a separate starter component.

## 2. Create the MySQL database
1. Start MySQL Server and open MySQL Workbench.
2. Open `database/database_schema.sql`.
3. Execute the whole script. It creates `rpms_db`, tables, and starter sample data.
4. Open `database/reports.sql` and run individual report queries after the schema is created.

The supplied database design expects a larger sample dataset (30+ candidates and more transactions); the included rows are only starter examples.

## 3. Configure Java / JDBC
Requirements: JDK 17+, Maven, MySQL Server.
1. Open the `java` folder as a Maven project in an IDE or VS Code with Java extensions.
2. Set environment variables if your MySQL credentials differ:
   - `RPMS_DB_URL` (default `jdbc:mysql://localhost:3306/rpms_db?useSSL=false&serverTimezone=UTC`)
   - `RPMS_DB_USER` (default `root`)
   - `RPMS_DB_PASSWORD` (default blank)
3. Build with `mvn package`.
4. Run `com.rpms.Main` from your IDE, or run it with the Maven-built classpath.
5. Use the console menu to list or add candidates.

Do not use real personal data in demo databases. For a real deployment, configure secure passwords and add authentication/authorization.

## 4. Current scope and honest limitations
This package is a complete UI prototype and a starter Java/MySQL backend, not yet a fully integrated production application. The console backend currently demonstrates candidate listing/creation and basic eligibility checks. Company, job, application, interview, offer, placement, login/roles, history, and reports are represented in the UI/database design but need their Java service/DAO operations implemented to become fully functional. The HTML screens are not connected to Java. Do not present the demo UI as a live database-connected website.

## 5. Suggested build order
1. Verify UI pages and navigation.
2. Create database and inspect tables.
3. Run Java candidate console.
4. Add DAO/service methods for companies and jobs.
5. Implement application eligibility and duplicate-application prevention.
6. Add interview feedback, offer, joining and placement transactions.
7. Add role-based login and audit history.
8. Add more realistic sample data, screenshots and final report.

## Modules
Admin, Recruiter, Placement Officer; Candidates; Companies; Jobs; Applications; Eligibility; Interviews; Feedback; Offers; Joining/Placements; Reports; Activity Logs.

## Viva one-line explanation
"The Recruitment & Placement Management System is a Java and MySQL based application that manages candidates, company job openings, eligibility, applications, interviews, offers and placement records. HTML and CSS are used for a separate static UI prototype."
