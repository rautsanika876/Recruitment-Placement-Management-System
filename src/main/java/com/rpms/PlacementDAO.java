
package com.rpms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class PlacementDAO {

    public void add(Placement placement) throws SQLException {

        String sql = "INSERT INTO placements (" +
                "candidate_id, company_id, job_id, offer_id, " +
                "designation, final_package_lpa, " +
                "expected_joining_date, actual_joining_date, " +
                "joining_status, placement_status" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, placement.getCandidateId());
            ps.setInt(2, placement.getCompanyId());
            ps.setInt(3, placement.getJobId());
            ps.setInt(4, placement.getOfferId());
            ps.setString(5, placement.getDesignation());
            ps.setBigDecimal(6, placement.getFinalPackageLpa());

            if (placement.getExpectedJoiningDate() != null) {
                ps.setDate(7, java.sql.Date.valueOf(
                        placement.getExpectedJoiningDate()));
            } else {
                ps.setNull(7, Types.DATE);
            }

            if (placement.getActualJoiningDate() != null) {
                ps.setDate(8, java.sql.Date.valueOf(
                        placement.getActualJoiningDate()));
            } else {
                ps.setNull(8, Types.DATE);
            }

            ps.setString(9, placement.getJoiningStatus());
            ps.setString(10, placement.getPlacementStatus());

            ps.executeUpdate();

            System.out.println("Placement added successfully!");
        }
    }

    public void findAll() throws SQLException {

        String sql = "SELECT * FROM placements ORDER BY placement_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.printf(
                    "%nPlacement ID: %d%n" +
                    "Candidate ID: %d%n" +
                    "Company ID: %d%n" +
                    "Job ID: %d%n" +
                    "Offer ID: %d%n" +
                    "Designation: %s%n" +
                    "Package: %s LPA%n" +
                    "Expected Joining: %s%n" +
                    "Actual Joining: %s%n" +
                    "Joining Status: %s%n" +
                    "Placement Status: %s%n",
                    rs.getInt("placement_id"),
                    rs.getInt("candidate_id"),
                    rs.getInt("company_id"),
                    rs.getInt("job_id"),
                    rs.getInt("offer_id"),
                    rs.getString("designation"),
                    rs.getBigDecimal("final_package_lpa"),
                    rs.getDate("expected_joining_date"),
                    rs.getDate("actual_joining_date"),
                    rs.getString("joining_status"),
                    rs.getString("placement_status")
                );
            }

            if (!found) {
                System.out.println("No placements found.");
            }
        }
    }
}
