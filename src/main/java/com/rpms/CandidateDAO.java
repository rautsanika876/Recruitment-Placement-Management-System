package com.rpms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {

    // Add Candidate
    public void add(Candidate c) throws SQLException {

        String sql = "INSERT INTO candidates(candidate_code,full_name,email,mobile,qualification," +
                "passing_year,percentage,backlog_count,primary_technology,profile_status) " +
                "VALUES(?,?,?,?,?,?,?,?,?,'ACTIVE')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCandidateCode());
            ps.setString(2, c.getFullName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getMobile());
            ps.setString(5, c.getQualification());
            ps.setInt(6, c.getPassingYear());
            ps.setDouble(7, c.getPercentage());
            ps.setInt(8, c.getBacklogCount());
            ps.setString(9, c.getPrimaryTechnology());

            ps.executeUpdate();
            System.out.println("Candidate added successfully!");
        }
    }

    // List All Candidates
    public List<Candidate> findAll() throws SQLException {

        List<Candidate> result = new ArrayList<>();

        String sql = "SELECT * FROM candidates ORDER BY candidate_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(mapCandidate(rs));
            }
        }

        return result;
    }

    // Find Candidate by ID
    public Candidate findById(int candidateId) throws SQLException {

        String sql = "SELECT * FROM candidates WHERE candidate_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, candidateId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapCandidate(rs);
                }
            }
        }

        return null;
    }

    // Convert Database Record into Candidate Object
    private Candidate mapCandidate(ResultSet rs) throws SQLException {

        Candidate c = new Candidate(
                rs.getString("candidate_code"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getString("mobile"),
                rs.getString("qualification"),
                rs.getInt("passing_year"),
                rs.getDouble("percentage"),
                rs.getInt("backlog_count"),
                rs.getString("primary_technology")
        );

        c.setCandidateId(rs.getInt("candidate_id"));
        c.setProfileStatus(rs.getString("profile_status"));

        return c;
    }
}