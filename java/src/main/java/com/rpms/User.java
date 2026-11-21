package com.rpms;

public class User {

    private int userId;
    private String username;
    private String passwordHash;
    private String fullName;
    private String email;
    private int roleId;
    private String status;

    // Constructor
    public User(String username, String passwordHash,
                String fullName, String email, int roleId) {

        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.email = email;
        this.roleId = roleId;
        this.status = "ACTIVE";
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        switch (roleId) {
            case 1:
                return "ADMIN";
            case 2:
                return "RECRUITER";
            case 3:
                return "PLACEMENT_OFFICER";
            default:
                return "UNKNOWN";
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}