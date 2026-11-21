
package com.rpms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class InterviewDAO {

    // Schedule New Interview
    public void add(Interview interview) throws SQLException {

        String sql = "INSERT INTO interview_rounds (" +
                "application_id, round_number, round_type, " +
                "scheduled_date, scheduled_time, mode, " +
                "interviewer_name, meeting_or_location, status" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, interview.getApplicationId());
            ps.setInt(2, interview.getRoundNumber());
            ps.setString(3, interview.getRoundType());
            ps.setDate(4, java.sql.Date.valueOf(
                    interview.getScheduledDate()));

            if (interview.getScheduledTime() != null) {
                ps.setTime(5, java.sql.Time.valueOf(
                        interview.getScheduledTime()));
            } else {
                ps.setNull(5, Types.TIME);
            }

            ps.setString(6, interview.getMode());
            ps.setString(7, interview.getInterviewerName());
            ps.setString(8, interview.getMeetingOrLocation());
            ps.setString(9, interview.getStatus());

            ps.executeUpdate();

            System.out.println("Interview scheduled successfully!");
        }
    }

    // List All Interviews
    public void findAll() throws SQLException {

        String sql = "SELECT * FROM interview_rounds " +
                     "ORDER BY scheduled_date, scheduled_time";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.printf(
                    "%nInterview ID: %d%n" +
                    "Application ID: %d%n" +
                    "Round: %d - %s%n" +
                    "Date: %s%n" +
                    "Time: %s%n" +
                    "Mode: %s%n" +
                    "Interviewer: %s%n" +
                    "Location: %s%n" +
                    "Status: %s%n",
                    rs.getInt("interview_id"),
                    rs.getInt("application_id"),
                    rs.getInt("round_number"),
                    rs.getString("round_type"),
                    rs.getDate("scheduled_date"),
                    rs.getTime("scheduled_time"),
                    rs.getString("mode"),
                    rs.getString("interviewer_name"),
                    rs.getString("meeting_or_location"),
                    rs.getString("status")
                );
            }

            if (!found) {
                System.out.println("No interviews found.");
            }
        }
    }
}
