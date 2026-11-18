package com.rpms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    // Add New Job
    public void add(Job j) throws SQLException {

        String sql = "INSERT INTO jobs (" +
                "job_code, company_id, job_title, employment_type, location, " +
                "openings, min_package_lpa, max_package_lpa, min_percentage, " +
                "min_passing_year, max_passing_year, max_backlogs, " +
                "experience_min_years, qualification_rule, posted_date, " +
                "application_deadline) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, j.getJobCode());
            ps.setInt(2, j.getCompanyId());
            ps.setString(3, j.getJobTitle());
            ps.setString(4, j.getEmploymentType());
            ps.setString(5, j.getLocation());
            ps.setInt(6, j.getOpenings());
            ps.setBigDecimal(7, j.getMinPackageLpa());
            ps.setBigDecimal(8, j.getMaxPackageLpa());
            ps.setBigDecimal(9, j.getMinPercentage());
            ps.setObject(10, j.getMinPassingYear());
            ps.setObject(11, j.getMaxPassingYear());
            ps.setInt(12, j.getMaxBacklogs());
            ps.setBigDecimal(13, j.getExperienceMinYears());
            ps.setString(14, j.getQualificationRule());
            ps.setDate(15, j.getPostedDate());
            ps.setDate(16, j.getApplicationDeadline());

            ps.executeUpdate();
        }
    }

    // Display All Jobs
    public List<Job> findAll() throws SQLException {

        List<Job> result = new ArrayList<>();

        String sql = "SELECT * FROM jobs ORDER BY job_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(mapJob(rs));
            }
        }

        return result;
    }

    // Find Job by ID
    public Job findById(int jobId) throws SQLException {

        String sql = "SELECT * FROM jobs WHERE job_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapJob(rs);
                }
            }
        }

        return null;
    }

    // Convert Database Record into Job Object
    private Job mapJob(ResultSet rs) throws SQLException {

        Job j = new Job(
                rs.getString("job_code"),
                rs.getInt("company_id"),
                rs.getString("job_title"),
                rs.getString("employment_type"),
                rs.getString("location"),
                rs.getInt("openings"),
                rs.getBigDecimal("min_package_lpa"),
                rs.getBigDecimal("max_package_lpa"),
                rs.getBigDecimal("min_percentage"),
                rs.getObject("min_passing_year", Integer.class),
                rs.getObject("max_passing_year", Integer.class),
                rs.getInt("max_backlogs"),
                rs.getBigDecimal("experience_min_years"),
                rs.getString("qualification_rule"),
                rs.getDate("posted_date"),
                rs.getDate("application_deadline")
        );

        j.setJobId(rs.getInt("job_id"));
        j.setStatus(rs.getString("status"));

        return j;
    }
}