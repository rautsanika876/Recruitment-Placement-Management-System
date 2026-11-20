
package com.rpms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO {

    private void printReport(String title, String sql) {
        System.out.println("\n========== " + title + " ==========");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int count = 0;

            while (rs.next()) {
                count++;
                int columns = rs.getMetaData().getColumnCount();

                for (int i = 1; i <= columns; i++) {
                    System.out.print(
                        rs.getMetaData().getColumnLabel(i)
                        + ": " + rs.getString(i) + " | "
                    );
                }
                System.out.println();
            }

            if (count == 0) {
                System.out.println("No records found.");
            }

        } catch (SQLException e) {
            System.out.println("Report Error: " + e.getMessage());
        }
    }

    // 1. Candidate count by technology
    public void candidateCountByTechnology() {
        String sql =
            "SELECT primary_technology, COUNT(*) AS total_candidates " +
            "FROM candidates " +
            "GROUP BY primary_technology";

        printReport("Candidate Count By Technology", sql);
    }

    // 2. Open jobs by company
    public void openJobsByCompany() {
        String sql =
            "SELECT c.company_name, COUNT(j.job_id) AS open_jobs " +
            "FROM companies c " +
            "LEFT JOIN jobs j ON c.company_id = j.company_id " +
            "AND j.status = 'OPEN' " +
            "GROUP BY c.company_id, c.company_name";

        printReport("Open Jobs By Company", sql);
    }

    // 3. Applications by status
    public void applicationsByStatus() {
        String sql =
            "SELECT application_status, COUNT(*) AS total " +
            "FROM applications " +
            "GROUP BY application_status";

        printReport("Applications By Status", sql);
    }

    
public void recruitmentFunnel() {
    String sql =
        "SELECT stage, total_candidates FROM (" +

        "SELECT 1 AS stage_order, " +
        "'Applied' AS stage, " +
        "COUNT(DISTINCT application_id) AS total_candidates " +
        "FROM applications " +

        "UNION ALL " +

        "SELECT 2, 'Shortlisted', " +
        "COUNT(DISTINCT application_id) " +
        "FROM applications " +
        "WHERE application_status IN " +
        "('SHORTLISTED', 'INTERVIEW', 'SELECTED', 'OFFERED', 'JOINED') " +

        "UNION ALL " +

        "SELECT 3, 'Interview', " +
        "COUNT(DISTINCT application_id) " +
        "FROM interview_rounds " +

        "UNION ALL " +

        "SELECT 4, 'Selected', " +
        "COUNT(DISTINCT application_id) " +
        "FROM applications " +
        "WHERE application_status IN ('SELECTED', 'OFFERED', 'JOINED') " +

        "UNION ALL " +

        "SELECT 5, 'Offered', " +
        "COUNT(DISTINCT application_id) " +
        "FROM offers " +

        "UNION ALL " +

        "SELECT 6, 'Joined', " +
        "COUNT(DISTINCT o.application_id) " +
        "FROM placements p " +
        "JOIN offers o ON p.offer_id = o.offer_id " +
        "WHERE p.joining_status = 'JOINED' " +

        ") AS funnel ORDER BY stage_order";

    printReport("Recruitment Funnel", sql);
}


    // 5. Company-wise placements
    public void companyWisePlacements() {
        String sql =
            "SELECT c.company_name, COUNT(p.placement_id) AS placed_count " +
            "FROM placements p " +
            "JOIN companies c ON p.company_id = c.company_id " +
            "WHERE p.joining_status = 'JOINED' " +
            "GROUP BY c.company_id, c.company_name";

        printReport("Company Wise Placements", sql);
    }

    // 6. Technology-wise placements
    public void technologyWisePlacements() {
        String sql =
            "SELECT c.primary_technology, COUNT(p.placement_id) AS total_placements " +
            "FROM placements p " +
            "JOIN candidates c ON p.candidate_id = c.candidate_id " +
            "GROUP BY c.primary_technology";

        printReport("Technology Wise Placements", sql);
    }

    // 7. Recruiter performance
    public void recruiterPerformance() {
        String sql =
            "SELECT assigned_recruiter_id, COUNT(*) AS total_applications " +
            "FROM applications " +
            "WHERE assigned_recruiter_id IS NOT NULL " +
            "GROUP BY assigned_recruiter_id";

        printReport("Recruiter Performance", sql);
    }

    // 8. Pending interviews
    public void pendingInterviews() {
        String sql =
            "SELECT interview_id, application_id, round_type, " +
            "scheduled_date, scheduled_time, status " +
            "FROM interview_rounds " +
            "WHERE status = 'SCHEDULED' " +
            "ORDER BY scheduled_date";

        printReport("Pending Interviews", sql);
    }

    // 9. Offer to joining conversion
    public void offerToJoiningConversion() {
        String sql =
            "SELECT COUNT(DISTINCT o.offer_id) AS total_offers, " +
            "COUNT(DISTINCT p.offer_id) AS joined_candidates " +
            "FROM offers o " +
            "LEFT JOIN placements p ON o.offer_id = p.offer_id " +
            "AND p.joining_status = 'JOINED'";

        printReport("Offer To Joining Conversion", sql);
    }

    // 10. Job aging
    public void jobAging() {
        String sql =
            "SELECT job_id, job_title, posted_date, " +
            "DATEDIFF(CURDATE(), posted_date) AS days_open " +
            "FROM jobs " +
            "WHERE status = 'OPEN' " +
            "ORDER BY days_open DESC";

        printReport("Job Aging", sql);
    }

    // Run all reports
    public void showAllReports() {
        candidateCountByTechnology();
        openJobsByCompany();
        applicationsByStatus();
        recruitmentFunnel();
        companyWisePlacements();
        technologyWisePlacements();
        recruiterPerformance();
        pendingInterviews();
        offerToJoiningConversion();
        jobAging();
    }
}
