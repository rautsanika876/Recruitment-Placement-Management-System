
package com.rpms;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Offer {

    private int offerId;
    private String offerCode;
    private int applicationId;
    private String designation;
    private BigDecimal offeredPackageLpa;
    private LocalDate offerDate;
    private LocalDate expectedJoiningDate;
    private String offerStatus;
    private String remarks;

    public Offer(String offerCode, int applicationId,
                 String designation, BigDecimal offeredPackageLpa,
                 LocalDate offerDate, LocalDate expectedJoiningDate,
                 String remarks) {

        this.offerCode = offerCode;
        this.applicationId = applicationId;
        this.designation = designation;
        this.offeredPackageLpa = offeredPackageLpa;
        this.offerDate = offerDate;
        this.expectedJoiningDate = expectedJoiningDate;
        this.offerStatus = "OFFERED";
        this.remarks = remarks;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public String getDesignation() {
        return designation;
    }

    public BigDecimal getOfferedPackageLpa() {
        return offeredPackageLpa;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public LocalDate getExpectedJoiningDate() {
        return expectedJoiningDate;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public String getRemarks() {
        return remarks;
    }
}
