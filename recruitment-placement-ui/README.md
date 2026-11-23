# Recruitment & Placement Management System — UI Prototype

This folder contains a static HTML/CSS front-end prototype for the Kiran Academy RPMS internship project.

## Open the prototype
1. Extract `recruitment-placement-ui.zip`.
2. Copy the complete `recruitment-placement-ui` folder into the root of your existing `Recruitment_Placement_Management_System` project.
3. Open `recruitment-placement-ui/login.html` in a browser.
4. Use any valid email and password, choose a role, and click **Sign In**. The demo username shown on the login page is `recruiter@demo.com`.

## Included pages
- Login and dashboard
- Candidates, candidate form and candidate details
- Companies and job openings
- Job form, applications and interviews
- Placement process and reports

## Important
- This is a static UI prototype. It does not contain Java, PHP, a backend, database connection, API, authentication service, or persistent form storage.
- All names, contact details, employers, jobs and metrics are fictional sample data.
- Forms use browser-side required-field validation. Submitting prototype forms displays a message and returns to the related listing; no data is saved.
- Shared styling is in `css/style.css`. The Kiran Academy wordmark is text-only, using orange-red and navy-blue serif lettering; no logo image is used.
- All page navigation and assets use relative paths. Your existing Java/MySQL backend and database are not modified.


## Database folder
The database folder and SQL schema are not included in this UI-only package because the existing Java/MySQL project files were not provided here. Keep your current database unchanged. To package the real database folder with this UI, provide your existing database folder or SQL export.


## Admin Menu (17 options)
Open `admin-menu.html` for all 17 console Admin Menu options. The sidebar on each page links to every module. Added Submit Application, Schedule Interview, Interview Feedback, Offers and Placements screens. This is a static UI prototype with sample data and demo form messages; it is not connected automatically to Java/MySQL. Connect the screens to your backend to persist records.


## Role-based menu visibility
- After signing in as **Admin**, the Admin Menu displays all 17 options.
- After signing in as **Recruiter**, only recruiter-related navigation options are displayed.
- After signing in as **Placement Officer**, only placement-related navigation options are displayed.
- The selected role is kept for the current browser session and cleared on logout.
- This is front-end demo access control only. Real security must also be enforced by the Java/backend server.


## Role-based UI dashboards
The login screen provides Admin, Recruiter, and Placement Officer roles. After sign-in, the dashboard displays role-specific feature cards and the sidebar is filtered to the same role. Admin has access to all existing UI modules; Recruiter sees recruitment and hiring modules; Placement Officer sees placement-related modules. Numeric terminal-menu numbering has been removed from the UI. This remains a static front-end prototype: authentication, permissions, and data operations are not enforced by a backend.
