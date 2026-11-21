
package com.rpms;

import java.sql.SQLException;
import java.sql.Date;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CandidateDAO candidateDAO = new CandidateDAO();
        CompanyDAO companyDAO = new CompanyDAO();
        JobDAO jobDAO = new JobDAO();
        ApplicationDAO applicationDAO = new ApplicationDAO();
        ApplicationService applicationService = new ApplicationService();

        InterviewDAO interviewDAO = new InterviewDAO();
        InterviewService interviewService = new InterviewService();

        FeedbackDAO feedbackDAO = new FeedbackDAO();
        FeedbackService feedbackService = new FeedbackService();

        OfferDAO offerDAO = new OfferDAO();
        OfferService offerService = new OfferService();

        PlacementDAO placementDAO = new PlacementDAO();
        PlacementService placementService = new PlacementService();

        // REPORT DAO
        ReportDAO reportDAO = new ReportDAO();

        UserDAO userDAO = new UserDAO();
        LoginService loginService = new LoginService();

        try {

            // FIRST ADMIN SETUP
            if (!userDAO.hasAnyUsers()) {

                System.out.println("\n=== First Admin Setup ===");

                System.out.print("Enter your RPMS username: ");
                String username = sc.nextLine().trim();

                System.out.print("Enter your password:: ");
                String password = sc.nextLine();

                System.out.print("Full name: ");
                String fullName = sc.nextLine().trim();

                System.out.print("Email: ");
                String email = sc.nextLine().trim();

                loginService.registerFirstAdmin(
                        username, password, fullName, email
                );
            }

            // LOGIN
            User loggedInUser = null;

            while (loggedInUser == null) {

                System.out.println("\n=== RPMS Login ===");

                System.out.print("Enter your RPMS username: ");
                String username = sc.nextLine().trim();

                System.out.print("Password: ");
                String password = sc.nextLine();

                loggedInUser = loginService.login(username, password);
            }

            // MAIN MENU
            while (true) {

                System.out.println(
                    "\n=== Recruitment & Placement Management System ==="
                );

                System.out.println(
                    "Logged in as: " + loggedInUser.getFullName()
                );

                System.out.println("1. View All Candidates");
                System.out.println("2. Register New Candidate");
                System.out.println("3. View All Companies");
                System.out.println("4. Register New Company");
                System.out.println("5. View Available Jobs");
                System.out.println("6. Create New Job Opening");
                System.out.println("7. submit Job Application");
                System.out.println("8. List applications");
                System.out.println("9. Schedule interview");
                System.out.println("10. List interviews");
                System.out.println("11. Add interview feedback");
                System.out.println("12. List interview feedback");
                System.out.println("13. Add offer");
                System.out.println("14. List offers");
                System.out.println("15. Add placement");
                System.out.println("16. List placements");
                System.out.println("17. Generate Recruitment Reports");
                System.out.println("18. Exit");

                System.out.print("Choose: ");

                String choice = sc.nextLine().trim();

                try {

                    // LIST CANDIDATES
                    if ("1".equals(choice)) {

                        List<Candidate> candidates =
                                candidateDAO.findAll();

                        if (candidates.isEmpty()) {
                            System.out.println("No candidates found.");
                        }

                        for (Candidate c : candidates) {
                            System.out.printf(
                                "%d | %s | %s | %s | %s%n",
                                c.getCandidateId(),
                                c.getCandidateCode(),
                                c.getFullName(),
                                c.getPrimaryTechnology(),
                                c.getProfileStatus()
                            );
                        }
                    }

                    // ADD CANDIDATE
                    else if ("2".equals(choice)) {

                        System.out.print("Candidate code: ");
                        String code = sc.nextLine().trim();

                        System.out.print("Full name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Email: ");
                        String email = sc.nextLine().trim();

                        System.out.print("Mobile (10 digits): ");
                        String mobile = sc.nextLine().trim();

                        System.out.print("Qualification: ");
                        String qual = sc.nextLine().trim();

                        System.out.print("Passing year: ");
                        int year = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Percentage (0-100): ");
                        double pct = Double.parseDouble(
                                sc.nextLine().trim());

                        System.out.print("Backlog count: ");
                        int backlogs = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Primary technology: ");
                        String tech = sc.nextLine().trim();

                        if (code.isBlank() || name.isBlank()
                                || !email.contains("@")
                                || !mobile.matches("\\d{10}")
                                || pct < 0 || pct > 100
                                || backlogs < 0) {

                            System.out.println("Invalid details.");
                            continue;
                        }

                        candidateDAO.add(new Candidate(
                            code, name, email, mobile, qual,
                            year, pct, backlogs, tech
                        ));

                        System.out.println(
                            "Candidate saved successfully."
                        );
                    }

                    // LIST COMPANIES
                    else if ("3".equals(choice)) {

                        List<Company> companies =
                                companyDAO.findAll();

                        if (companies.isEmpty()) {
                            System.out.println("No companies found.");
                        }

                        for (Company c : companies) {
                            System.out.printf(
                                "%d | %s | %s | %s | %s%n",
                                c.getCompanyId(),
                                c.getCompanyCode(),
                                c.getCompanyName(),
                                c.getIndustry(),
                                c.getStatus()
                            );
                        }
                    }

                    // ADD COMPANY
                    else if ("4".equals(choice)) {

                        System.out.print("Company code: ");
                        String code = sc.nextLine().trim();

                        System.out.print("Company name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Industry: ");
                        String industry = sc.nextLine().trim();

                        System.out.print("Website: ");
                        String website = sc.nextLine().trim();

                        System.out.print("Location: ");
                        String location = sc.nextLine().trim();

                        System.out.print("Company type: ");
                        String type = sc.nextLine().trim();

                        System.out.print("HR name: ");
                        String hrName = sc.nextLine().trim();

                        System.out.print("HR email: ");
                        String hrEmail = sc.nextLine().trim();

                        System.out.print("HR mobile: ");
                        String hrMobile = sc.nextLine().trim();

                        if (code.isBlank() || name.isBlank()) {
                            System.out.println(
                                "Company code and name are required."
                            );
                            continue;
                        }

                        companyDAO.add(new Company(
                            code, name, industry, website, location,
                            type, hrName, hrEmail, hrMobile
                        ));

                        System.out.println(
                            "Company saved successfully."
                        );
                    }

                    // LIST JOBS
                    else if ("5".equals(choice)) {

                        List<Job> jobs = jobDAO.findAll();

                        if (jobs.isEmpty()) {
                            System.out.println("No jobs found.");
                        }

                        for (Job j : jobs) {
                            System.out.printf(
                                "%d | %s | %s | Company ID: %d | %s | Openings: %d | %s%n",
                                j.getJobId(),
                                j.getJobCode(),
                                j.getJobTitle(),
                                j.getCompanyId(),
                                j.getLocation(),
                                j.getOpenings(),
                                j.getStatus()
                            );
                        }
                    }

                    // ADD JOB
                    else if ("6".equals(choice)) {

                        System.out.print("Job code: ");
                        String code = sc.nextLine().trim();

                        System.out.print("Company ID: ");
                        int companyId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Job title: ");
                        String title = sc.nextLine().trim();

                        System.out.print("Employment type (FULL_TIME): ");
                        String type = sc.nextLine().trim();

                        if (type.isBlank()) type = "FULL_TIME";

                        System.out.print("Location: ");
                        String location = sc.nextLine().trim();

                        System.out.print("Number of openings: ");
                        int openings = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print(
                            "Minimum package LPA (blank if none): "
                        );
                        BigDecimal minPackage =
                                optionalDecimal(sc.nextLine());

                        System.out.print(
                            "Maximum package LPA (blank if none): "
                        );
                        BigDecimal maxPackage =
                                optionalDecimal(sc.nextLine());

                        System.out.print(
                            "Minimum percentage (blank if none): "
                        );
                        BigDecimal percentage =
                                optionalDecimal(sc.nextLine());

                        System.out.print(
                            "Minimum passing year (blank if none): "
                        );
                        Integer minYear =
                                optionalInteger(sc.nextLine());

                        System.out.print(
                            "Maximum passing year (blank if none): "
                        );
                        Integer maxYear =
                                optionalInteger(sc.nextLine());

                        System.out.print(
                            "Maximum backlogs (blank for 0): "
                        );
                        String backlogInput =
                                sc.nextLine().trim();

                        int backlogs = backlogInput.isBlank()
                                ? 0 : Integer.parseInt(backlogInput);

                        System.out.print(
                            "Minimum experience years (blank for 0): "
                        );
                        String expInput =
                                sc.nextLine().trim();

                        BigDecimal experience = expInput.isBlank()
                                ? BigDecimal.ZERO
                                : new BigDecimal(expInput);

                        System.out.print(
                            "Qualification rule (blank if none): "
                        );
                        String qualification =
                                sc.nextLine().trim();

                        System.out.print(
                            "Posted date (YYYY-MM-DD): "
                        );
                        Date postedDate =
                                Date.valueOf(sc.nextLine().trim());

                        System.out.print(
                            "Application deadline (YYYY-MM-DD): "
                        );
                        Date deadline =
                                Date.valueOf(sc.nextLine().trim());

                        if (code.isBlank() || title.isBlank()
                                || location.isBlank()
                                || companyId <= 0 || openings <= 0
                                || backlogs < 0
                                || (percentage != null &&
                                    (percentage.compareTo(BigDecimal.ZERO) < 0 ||
                                     percentage.compareTo(
                                        new BigDecimal("100")) > 0))
                                || (minPackage != null &&
                                    maxPackage != null &&
                                    minPackage.compareTo(maxPackage) > 0)
                                || (minYear != null &&
                                    maxYear != null &&
                                    minYear > maxYear)
                                || experience.compareTo(
                                    BigDecimal.ZERO) < 0
                                || deadline.before(postedDate)) {

                            System.out.println(
                                "Invalid job details."
                            );
                            continue;
                        }

                        Job job = new Job(
                            code, companyId, title, type, location,
                            openings, minPackage, maxPackage,
                            percentage, minYear, maxYear,
                            backlogs, experience,
                            qualification.isBlank()
                                ? null : qualification,
                            postedDate, deadline
                        );

                        jobDAO.add(job);

                        System.out.println("Job saved successfully.");
                    }

                    // ADD APPLICATION
                    else if ("7".equals(choice)) {

                        System.out.print("Application code: ");
                        String code = sc.nextLine().trim();

                        System.out.print("Candidate ID: ");
                        int candidateId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Job ID: ");
                        int jobId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print(
                            "Application source (e.g. COLLEGE): "
                        );
                        String source = sc.nextLine().trim();

                        if (code.isBlank() || candidateId <= 0
                                || jobId <= 0 || source.isBlank()) {

                            System.out.println(
                                "Invalid application details."
                            );
                            continue;
                        }

                        applicationService.apply(
                            code, candidateId, jobId, source
                        );
                    }

                    // LIST APPLICATIONS
                    else if ("8".equals(choice)) {

                        applicationDAO.findAll();
                    }

                    // SCHEDULE INTERVIEW
                    else if ("9".equals(choice)) {

                        System.out.print("Application ID: ");
                        int applicationId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Round number: ");
                        int roundNumber = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print(
                            "Round type (TECHNICAL/HR): "
                        );
                        String roundType =
                                sc.nextLine().trim();

                        System.out.print(
                            "Interview date (YYYY-MM-DD): "
                        );
                        LocalDate date =
                                LocalDate.parse(sc.nextLine().trim());

                        System.out.print(
                            "Interview time (HH:MM): "
                        );
                        LocalTime time =
                                LocalTime.parse(sc.nextLine().trim());

                        System.out.print(
                            "Mode (ONLINE/OFFLINE): "
                        );
                        String mode =
                                sc.nextLine().trim();

                        System.out.print("Interviewer name: ");
                        String interviewer =
                                sc.nextLine().trim();

                        System.out.print(
                            "Meeting link or location: "
                        );
                        String location =
                                sc.nextLine().trim();

                        Interview interview = new Interview(
                            applicationId,
                            roundNumber,
                            roundType,
                            date,
                            time,
                            mode,
                            interviewer,
                            location
                        );

                        interviewService.scheduleInterview(interview);
                    }

                    // LIST INTERVIEWS
                    else if ("10".equals(choice)) {

                        interviewDAO.findAll();
                    }

                    // ADD INTERVIEW FEEDBACK
                    else if ("11".equals(choice)) {

                        System.out.print("Interview ID: ");
                        int interviewId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print(
                            "Technical rating (1-5): "
                        );
                        int technical = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print(
                            "Communication rating (1-5): "
                        );
                        int communication = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print(
                            "Problem solving rating (1-5): "
                        );
                        int problemSolving = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print(
                            "Overall rating (1-5): "
                        );
                        int overall = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print(
                            "Recommendation (SELECTED/REJECTED/HOLD): "
                        );
                        String recommendation =
                                sc.nextLine().trim();

                        System.out.print("Remarks: ");
                        String remarks = sc.nextLine().trim();

                        Feedback feedback = new Feedback(
                            interviewId,
                            technical,
                            communication,
                            problemSolving,
                            overall,
                            recommendation,
                            remarks,
                            loggedInUser.getUserId()
                        );

                        feedbackService.submitFeedback(feedback);
                    }

                    // LIST FEEDBACK
                    else if ("12".equals(choice)) {

                        feedbackDAO.findAll();
                    }

                    // ADD OFFER
                    else if ("13".equals(choice)) {

                        System.out.print("Offer code: ");
                        String offerCode = sc.nextLine().trim();

                        System.out.print("Application ID: ");
                        int applicationId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Designation: ");
                        String designation = sc.nextLine().trim();

                        System.out.print("Offered package LPA: ");
                        BigDecimal packageLpa =
                                new BigDecimal(sc.nextLine().trim());

                        System.out.print("Offer date (YYYY-MM-DD): ");
                        LocalDate offerDate =
                                LocalDate.parse(sc.nextLine().trim());

                        System.out.print(
                            "Expected joining date (YYYY-MM-DD, blank if unknown): "
                        );
                        String joiningInput = sc.nextLine().trim();

                        LocalDate joiningDate = joiningInput.isBlank()
                                ? null : LocalDate.parse(joiningInput);

                        System.out.print("Remarks: ");
                        String remarks = sc.nextLine().trim();

                        Offer offer = new Offer(
                            offerCode,
                            applicationId,
                            designation,
                            packageLpa,
                            offerDate,
                            joiningDate,
                            remarks
                        );

                        offerService.createOffer(offer);
                    }

                    // LIST OFFERS
                    else if ("14".equals(choice)) {

                        offerDAO.findAll();
                    }

                    // ADD PLACEMENT
                    else if ("15".equals(choice)) {

                        System.out.print("Candidate ID: ");
                        int candidateId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Company ID: ");
                        int companyId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Job ID: ");
                        int jobId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Offer ID: ");
                        int offerId = Integer.parseInt(
                                sc.nextLine().trim());

                        System.out.print("Designation: ");
                        String designation = sc.nextLine().trim();

                        System.out.print("Final package LPA: ");
                        BigDecimal packageLpa =
                                new BigDecimal(sc.nextLine().trim());

                        System.out.print(
                            "Expected joining date (YYYY-MM-DD, blank if unknown): "
                        );
                        String expectedInput = sc.nextLine().trim();

                        LocalDate expectedDate = expectedInput.isBlank()
                                ? null : LocalDate.parse(expectedInput);

                        System.out.print(
                            "Actual joining date (YYYY-MM-DD, blank if not joined): "
                        );
                        String actualInput = sc.nextLine().trim();

                        LocalDate actualDate = actualInput.isBlank()
                                ? null : LocalDate.parse(actualInput);

                        Placement placement = new Placement(
                                candidateId,
                                companyId,
                                jobId,
                                offerId,
                                designation,
                                packageLpa,
                                expectedDate,
                                actualDate
                        );

                        placementService.createPlacement(placement);
                    }

                    // LIST PLACEMENTS
                    else if ("16".equals(choice)) {

                        placementDAO.findAll();
                    }

                    // VIEW REPORTS
                    else if ("17".equals(choice)) {

                        System.out.println("\n=== RPMS REPORTS ===");
                        reportDAO.showAllReports();
                    }

                    // EXIT
                    else if ("18".equals(choice)) {

                        System.out.println("Goodbye.");
                        break;
                    }

                    else {
                        System.out.println(
                            "Choose between 1 and 18."
                        );
                    }

                } catch (NumberFormatException ex) {

                    System.out.println(
                        "Please enter valid numeric details."
                    );

                } catch (IllegalArgumentException ex) {

                    System.out.println(
                        "Invalid date or input format. " +
                        "Use YYYY-MM-DD for dates and HH:MM for time."
                    );

                } catch (SQLException ex) {

                    System.out.println(
                        "Database operation failed: " +
                        ex.getMessage()
                    );
                }
            }

        } catch (Exception ex) {

            System.out.println(
                "Login or setup failed: " + ex.getMessage()
            );

        } finally {
            sc.close();
        }
    }

    private static BigDecimal optionalDecimal(String value) {
        return value.trim().isBlank()
                ? null : new BigDecimal(value.trim());
    }

    private static Integer optionalInteger(String value) {
        return value.trim().isBlank()
                ? null : Integer.parseInt(value.trim());
    }
}
