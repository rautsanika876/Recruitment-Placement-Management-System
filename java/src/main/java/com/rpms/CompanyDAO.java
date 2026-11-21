
package com.rpms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    // Add new company
    public void add(Company c) throws SQLException {

        String sql = "INSERT INTO companies " +
                "(company_code, company_name, industry, website, location, " +
                "company_type, hr_name, hr_email, hr_mobile) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCompanyCode());
            ps.setString(2, c.getCompanyName());
            ps.setString(3, c.getIndustry());
            ps.setString(4, c.getWebsite());
            ps.setString(5, c.getLocation());
            ps.setString(6, c.getCompanyType());
            ps.setString(7, c.getHrName());
            ps.setString(8, c.getHrEmail());
            ps.setString(9, c.getHrMobile());

            ps.executeUpdate();
        }
    }

    // Display all companies
    public List<Company> findAll() throws SQLException {

        List<Company> result = new ArrayList<>();

        String sql = "SELECT * FROM companies ORDER BY company_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Company c = new Company(
                        rs.getString("company_code"),
                        rs.getString("company_name"),
                        rs.getString("industry"),
                        rs.getString("website"),
                        rs.getString("location"),
                        rs.getString("company_type"),
                        rs.getString("hr_name"),
                        rs.getString("hr_email"),
                        rs.getString("hr_mobile")
                );

                c.setCompanyId(rs.getInt("company_id"));
                result.add(c);
            }
        }

        return result;
    }
}
