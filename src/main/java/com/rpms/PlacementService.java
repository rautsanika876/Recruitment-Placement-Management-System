
package com.rpms;

import java.math.BigDecimal;
import java.sql.SQLException;

public class PlacementService {

    private final PlacementDAO placementDAO = new PlacementDAO();

    public void createPlacement(Placement placement)
            throws SQLException {

        if (placement.getCandidateId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid Candidate ID.");
        }

        if (placement.getCompanyId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid Company ID.");
        }

        if (placement.getJobId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid Job ID.");
        }

        if (placement.getOfferId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid Offer ID.");
        }

        if (placement.getDesignation() == null ||
                placement.getDesignation().isBlank()) {
            throw new IllegalArgumentException(
                    "Designation is required.");
        }

        BigDecimal packageLpa = placement.getFinalPackageLpa();

        if (packageLpa == null ||
                packageLpa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Package must be greater than zero.");
        }

        placementDAO.add(placement);
    }
}
