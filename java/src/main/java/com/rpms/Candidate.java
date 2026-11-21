package com.rpms;

public class Candidate {

    private int candidateId;
    private String candidateCode;
    private String fullName;
    private String email;
    private String mobile;
    private String qualification;
    private int passingYear;
    private double percentage;
    private int backlogCount;
    private String primaryTechnology;
    private String profileStatus;

    public Candidate(String candidateCode, String fullName, String email,
                     String mobile, String qualification, int passingYear,
                     double percentage, int backlogCount,
                     String primaryTechnology) {

        this.candidateCode = candidateCode;
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.qualification = qualification;
        this.passingYear = passingYear;
        this.percentage = percentage;
        this.backlogCount = backlogCount;
        this.primaryTechnology = primaryTechnology;
        this.profileStatus = "ACTIVE";
    }

    public int getCandidateId() { return candidateId; }
    public void setCandidateId(int candidateId) { this.candidateId = candidateId; }

    public String getCandidateCode() { return candidateCode; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getQualification() { return qualification; }
    public int getPassingYear() { return passingYear; }
    public double getPercentage() { return percentage; }
    public int getBacklogCount() { return backlogCount; }
    public String getPrimaryTechnology() { return primaryTechnology; }
    public String getProfileStatus() { return profileStatus; }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }
}