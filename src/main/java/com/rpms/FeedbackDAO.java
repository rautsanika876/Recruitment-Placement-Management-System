
package com.rpms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackDAO {

    // Add Interview Feedback
    public void add(Feedback feedback) throws SQLException {

        String sql = "INSERT INTO interview_feedback (" +
                "interview_id, technical_rating, " +
                "communication_rating, problem_solving_rating, " +
                "overall_rating, recommendation, remarks, submitted_by" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, feedback.getInterviewId());
            ps.setInt(2, feedback.getTechnicalRating());
            ps.setInt(3, feedback.getCommunicationRating());
            ps.setInt(4, feedback.getProblemSolvingRating());
            ps.setInt(5, feedback.getOverallRating());
            ps.setString(6, feedback.getRecommendation());
            ps.setString(7, feedback.getRemarks());
            ps.setInt(8, feedback.getSubmittedBy());

            ps.executeUpdate();

            System.out.println("Interview feedback saved successfully!");
        }
    }

    // List All Feedback
    public void findAll() throws SQLException {

        String sql = "SELECT * FROM interview_feedback " +
                     "ORDER BY submitted_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.printf(
                    "%nFeedback ID: %d%n" +
                    "Interview ID: %d%n" +
                    "Technical Rating: %d/5%n" +
                    "Communication Rating: %d/5%n" +
                    "Problem Solving Rating: %d/5%n" +
                    "Overall Rating: %d/5%n" +
                    "Recommendation: %s%n" +
                    "Remarks: %s%n" +
                    "Submitted By User ID: %d%n",
                    rs.getInt("feedback_id"),
                    rs.getInt("interview_id"),
                    rs.getInt("technical_rating"),
                    rs.getInt("communication_rating"),
                    rs.getInt("problem_solving_rating"),
                    rs.getInt("overall_rating"),
                    rs.getString("recommendation"),
                    rs.getString("remarks"),
                    rs.getInt("submitted_by")
                );
            }

            if (!found) {
                System.out.println("No feedback found.");
            }
        }
    }
}
