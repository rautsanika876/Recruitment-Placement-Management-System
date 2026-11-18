package com.rpms;

import java.sql.*;

public class ApplicationDAO {

    // Add Application
    public void add(Application a) throws SQLException {

        String sql = "INSERT INTO applications " +
                "(application_code, candidate_id, job_id, " +
                "eligibility_status, eligibility_reason, " +
                "application_status, source) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getApplicationCode());
            ps.setInt(2, a.getCandidateId());
            ps.setInt(3, a.getJobId());
            ps.setString(4, a.getEligibilityStatus());
            ps.setString(5, a.getEligibilityReason());
            ps.setString(6, a.getApplicationStatus());
            ps.setString(7, a.getSource());

            ps.executeUpdate();

            System.out.println("Application added successfully!");
        }
    }

    // List Applications
    public void findAll() throws SQLException {

        String sql = "SELECT * FROM applications ORDER BY application_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== Applications ===");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println(
                        "ID: " + rs.getInt("application_id") +
                        " | Code: " + rs.getString("application_code") +
                        " | Candidate ID: " + rs.getInt("candidate_id") +
                        " | Job ID: " + rs.getInt("job_id") +
                        " | Eligibility: " + rs.getString("eligibility_status") +
                        " | Reason: " + rs.getString("eligibility_reason") +
                        " | Status: " + rs.getString("application_status")
                );
            }

            if (!found) {
                System.out.println("No applications found.");
            }
        }
    }
}