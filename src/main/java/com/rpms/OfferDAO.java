
package com.rpms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class OfferDAO {

    // Add New Offer
    public void add(Offer offer) throws SQLException {

        String sql = "INSERT INTO offers (" +
                "offer_code, application_id, designation, " +
                "offered_package_lpa, offer_date, " +
                "expected_joining_date, offer_status, remarks" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, offer.getOfferCode());
            ps.setInt(2, offer.getApplicationId());
            ps.setString(3, offer.getDesignation());
            ps.setBigDecimal(4, offer.getOfferedPackageLpa());
            ps.setDate(5, java.sql.Date.valueOf(offer.getOfferDate()));

            if (offer.getExpectedJoiningDate() != null) {
                ps.setDate(6, java.sql.Date.valueOf(
                        offer.getExpectedJoiningDate()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            ps.setString(7, offer.getOfferStatus());
            ps.setString(8, offer.getRemarks());

            ps.executeUpdate();

            System.out.println("Offer added successfully!");
        }
    }

    // List All Offers
    public void findAll() throws SQLException {

        String sql = "SELECT * FROM offers ORDER BY offer_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.printf(
                    "%nOffer ID: %d%n" +
                    "Offer Code: %s%n" +
                    "Application ID: %d%n" +
                    "Designation: %s%n" +
                    "Package: %s LPA%n" +
                    "Offer Date: %s%n" +
                    "Expected Joining: %s%n" +
                    "Status: %s%n" +
                    "Remarks: %s%n",
                    rs.getInt("offer_id"),
                    rs.getString("offer_code"),
                    rs.getInt("application_id"),
                    rs.getString("designation"),
                    rs.getBigDecimal("offered_package_lpa"),
                    rs.getDate("offer_date"),
                    rs.getDate("expected_joining_date"),
                    rs.getString("offer_status"),
                    rs.getString("remarks")
                );
            }

            if (!found) {
                System.out.println("No offers found.");
            }
        }
    }
}
