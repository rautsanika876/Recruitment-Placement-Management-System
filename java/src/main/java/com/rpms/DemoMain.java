
package com.rpms;

import java.util.Scanner;

public class DemoMain {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n==============================================");
            System.out.println(" RECRUITMENT & PLACEMENT MANAGEMENT SYSTEM");
            System.out.println("==============================================");
            System.out.println("1. Admin");
            System.out.println("2. Recruiter");
            System.out.println("3. Placement Officer");
            System.out.println("4. Student");
            System.out.println("0. Exit");
            System.out.print("Select user role: ");

            choice = readChoice();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    recruiterMenu();
                    break;
                case 3:
                    placementOfficerMenu();
                    break;
                case 4:
                    studentMenu();
                    break;
                case 0:
                    System.out.println("Demo closed.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }

    private static void adminMenu() {

        int choice;

        do {
            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. View All Candidates");
            System.out.println("2. Register New Candidate");
            System.out.println("3. View All Companies");
            System.out.println("4. Register New Company");
            System.out.println("5. View Available Jobs");
            System.out.println("6. Create New Job Opening");
            System.out.println("7. Submit Job Application");
            System.out.println("8. List Applications");
            System.out.println("9. Schedule Interview");
            System.out.println("10. List Interviews");
            System.out.println("11. Add Interview Feedback");
            System.out.println("12. List Interview Feedback");
            System.out.println("13. Add Offer");
            System.out.println("14. List Offers");
            System.out.println("15. Add Placement");
            System.out.println("16. List Placements");
            System.out.println("17. Generate Recruitment Reports");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            choice = readChoice();

            switch (choice) {
                case 1: viewCandidates(); break;
                case 2: demo("Register New Candidate"); break;
                case 3: viewCompanies(); break;
                case 4: demo("Register New Company"); break;
                case 5: viewJobs(); break;
                case 6: demo("Create New Job Opening"); break;
                case 7: demo("Submit Job Application"); break;
                case 8: viewApplications(); break;
                case 9: demo("Schedule Interview"); break;
                case 10: viewInterviews(); break;
                case 11: demo("Add Interview Feedback"); break;
                case 12: viewFeedback(); break;
                case 13: demo("Add Offer"); break;
                case 14: viewOffers(); break;
                case 15: demo("Add Placement"); break;
                case 16: viewPlacements(); break;
                case 17: viewReports(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private static void recruiterMenu() {

        int choice;

        do {
            System.out.println("\n======== RECRUITER MENU ========");
            System.out.println("1. View All Companies");
            System.out.println("2. Register New Company");
            System.out.println("3. View Available Jobs");
            System.out.println("4. Create New Job Opening");
            System.out.println("5. Submit Job Application");
            System.out.println("6. List Applications");
            System.out.println("7. Schedule Interview");
            System.out.println("8. List Interviews");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            choice = readChoice();

            switch (choice) {
                case 1: viewCompanies(); break;
                case 2: demo("Register New Company"); break;
                case 3: viewJobs(); break;
                case 4: demo("Create New Job Opening"); break;
                case 5: demo("Submit Job Application"); break;
                case 6: viewApplications(); break;
                case 7: demo("Schedule Interview"); break;
                case 8: viewInterviews(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private static void placementOfficerMenu() {

        int choice;

        do {
            System.out.println("\n===== PLACEMENT OFFICER MENU =====");
            System.out.println("1. View All Candidates");
            System.out.println("2. Register New Candidate");
            System.out.println("3. List Applications");
            System.out.println("4. List Interviews");
            System.out.println("5. Add Interview Feedback");
            System.out.println("6. List Interview Feedback");
            System.out.println("7. Add Offer");
            System.out.println("8. List Offers");
            System.out.println("9. Add Placement");
            System.out.println("10. List Placements");
            System.out.println("11. Generate Recruitment Reports");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            choice = readChoice();

            switch (choice) {
                case 1: viewCandidates(); break;
                case 2: demo("Register New Candidate"); break;
                case 3: viewApplications(); break;
                case 4: viewInterviews(); break;
                case 5: demo("Add Interview Feedback"); break;
                case 6: viewFeedback(); break;
                case 7: demo("Add Offer"); break;
                case 8: viewOffers(); break;
                case 9: demo("Add Placement"); break;
                case 10: viewPlacements(); break;
                case 11: viewReports(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private static void studentMenu() {

        int choice;

        do {
            System.out.println("\n========== STUDENT MENU ==========");
            System.out.println("1. View Available Jobs");
            System.out.println("2. Apply for Job");
            System.out.println("3. View Application Status");
            System.out.println("4. View Student Profile");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            choice = readChoice();

            switch (choice) {
                case 1: viewJobs(); break;
                case 2: demo("Apply for Job"); break;
                case 3: demo("View Application Status"); break;
                case 4: demo("View Student Profile"); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private static void viewCandidates() {
        System.out.println("\n========== SAMPLE CANDIDATES ==========");
        System.out.println("101 | CAND-101 | Demo Student One | Java | ACTIVE");
        System.out.println("102 | CAND-102 | Demo Student Two | Python | ACTIVE");
        System.out.println("103 | CAND-103 | Demo Student Three | Web | ACTIVE");
    }

    private static void viewCompanies() {
        System.out.println("\n========== SAMPLE COMPANIES ==========");
        System.out.println("1 | COMP-001 | Demo Technologies | IT | ACTIVE");
        System.out.println("2 | COMP-002 | Sample Software Ltd. | Software | ACTIVE");
    }

    private static void viewJobs() {
        System.out.println("\n========== SAMPLE JOBS ==========");
        System.out.println("101 | JOB-101 | Java Developer | Company ID: 1 | Pune | Openings: 5 | OPEN");
        System.out.println("102 | JOB-102 | Web Developer | Company ID: 2 | Mumbai | Openings: 3 | OPEN");
    }

    private static void viewApplications() {
        System.out.println("\n========== SAMPLE APPLICATIONS ==========");
        System.out.println("1 | APP-101 | Candidate ID: 101 | Job ID: 101 | APPLIED");
        System.out.println("2 | APP-102 | Candidate ID: 102 | Job ID: 102 | UNDER REVIEW");
    }

    private static void viewInterviews() {
        System.out.println("\n========== SAMPLE INTERVIEWS ==========");
        System.out.println("1 | Application ID: 1 | Technical | ONLINE | SCHEDULED");
        System.out.println("2 | Application ID: 2 | HR | OFFLINE | SCHEDULED");
    }

    private static void viewFeedback() {
        System.out.println("\n========== SAMPLE FEEDBACK ==========");
        System.out.println("Interview ID: 1 | Rating: 4/5 | Recommendation: SELECTED");
        System.out.println("Interview ID: 2 | Rating: 3/5 | Recommendation: HOLD");
    }

    private static void viewOffers() {
        System.out.println("\n========== SAMPLE OFFERS ==========");
        System.out.println("1 | Application ID: 1 | Java Developer | 4.0 LPA");
        System.out.println("2 | Application ID: 2 | Web Developer | 3.5 LPA");
    }

    private static void viewPlacements() {
        System.out.println("\n========== SAMPLE PLACEMENTS ==========");
        System.out.println("1 | Candidate ID: 101 | Company ID: 1 | 4.0 LPA | PLACED");
    }

    private static void viewReports() {
        System.out.println("\n========== SAMPLE RPMS REPORTS ==========");
        System.out.println("Total Candidates: 3");
        System.out.println("Total Companies: 2");
        System.out.println("Total Jobs: 2");
        System.out.println("Total Applications: 2");
        System.out.println("Total Placements: 1");
    }

    private static void demo(String feature) {
        System.out.println("\nDemo feature selected: " + feature);
        System.out.println("This is a preview. Database is not connected.");
    }

    private static int readChoice() {
        String input = sc.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

