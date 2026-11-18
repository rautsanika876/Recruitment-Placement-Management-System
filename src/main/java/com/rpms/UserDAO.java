
package com.rpms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // ADD USER
    public void add(User user) throws SQLException {

        String sql = "INSERT INTO users " +
                "(username, password_hash, full_name, email, role_id, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getRoleId());
            ps.setString(6, user.getStatus());

            ps.executeUpdate();

            System.out.println("User registered successfully!");
        }
    }

    // FIND USER BY USERNAME
    public User findByUsername(String username) throws SQLException {

        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    User user = new User(
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getInt("role_id")
                    );

                    user.setUserId(rs.getInt("user_id"));
                    user.setStatus(rs.getString("status"));

                    return user;
                }
            }
        }

        return null;
    }

    // UPDATE LAST LOGIN
    public void updateLastLogin(int userId) throws SQLException {

        String sql = "UPDATE users SET last_login_at = CURRENT_TIMESTAMP " +
                "WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ps.executeUpdate();
        }
    }

    // CHECK WHETHER USERS EXIST
    public boolean hasAnyUsers() throws SQLException {

        String sql = "SELECT COUNT(*) FROM users";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }

        return false;
    }
}
