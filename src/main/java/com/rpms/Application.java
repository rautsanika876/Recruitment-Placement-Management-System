package com.rpms;

public class Application {

    private int applicationId;
    private String applicationCode;
    private int candidateId;
    private int jobId;
    private String eligibilityStatus;
    private String eligibilityReason;
    private String applicationStatus;
    private String source;
    private Integer assignedRecruiterId;

    public Application(String applicationCode, int candidateId,
                       int jobId, String source) {

        this.applicationCode = applicationCode;
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.source = source;
        this.eligibilityStatus = "PENDING";
        this.applicationStatus = "APPLIED";
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public int getJobId() {
        return jobId;
    }

    public String getEligibilityStatus() {
        return eligibilityStatus;
    }

    public void setEligibilityStatus(String eligibilityStatus) {
        this.eligibilityStatus = eligibilityStatus;
    }

    public String getEligibilityReason() {
        return eligibilityReason;
    }

    public void setEligibilityReason(String eligibilityReason) {
        this.eligibilityReason = eligibilityReason;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public String getSource() {
        return source;
    }

    public Integer getAssignedRecruiterId() {
        return assignedRecruiterId;
    }
}