
package com.rpms;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Placement {

    private int placementId;
    private int candidateId;
    private int companyId;
    private int jobId;
    private int offerId;
    private String designation;
    private BigDecimal finalPackageLpa;
    private LocalDate expectedJoiningDate;
    private LocalDate actualJoiningDate;
    private String joiningStatus;
    private String placementStatus;

    public Placement(int candidateId, int companyId,
                     int jobId, int offerId,
                     String designation,
                     BigDecimal finalPackageLpa,
                     LocalDate expectedJoiningDate,
                     LocalDate actualJoiningDate) {

        this.candidateId = candidateId;
        this.companyId = companyId;
        this.jobId = jobId;
        this.offerId = offerId;
        this.designation = designation;
        this.finalPackageLpa = finalPackageLpa;
        this.expectedJoiningDate = expectedJoiningDate;
        this.actualJoiningDate = actualJoiningDate;
        this.joiningStatus = "PENDING";
        this.placementStatus = "ACTIVE";
    }

    public int getPlacementId() {
        return placementId;
    }

    public void setPlacementId(int placementId) {
        this.placementId = placementId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getJobId() {
        return jobId;
    }

    public int getOfferId() {
        return offerId;
    }

    public String getDesignation() {
        return designation;
    }

    public BigDecimal getFinalPackageLpa() {
        return finalPackageLpa;
    }

    public LocalDate getExpectedJoiningDate() {
        return expectedJoiningDate;
    }

    public LocalDate getActualJoiningDate() {
        return actualJoiningDate;
    }

    public String getJoiningStatus() {
        return joiningStatus;
    }

    public String getPlacementStatus() {
        return placementStatus;
    }
}
