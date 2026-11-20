
package com.rpms;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class OfferService {

    private final OfferDAO offerDAO = new OfferDAO();

    public void createOffer(Offer offer) throws SQLException {

        if (offer.getOfferCode() == null ||
                offer.getOfferCode().isBlank()) {
            throw new IllegalArgumentException("Offer code is required.");
        }

        if (offer.getApplicationId() <= 0) {
            throw new IllegalArgumentException("Invalid application ID.");
        }

        if (offer.getDesignation() == null ||
                offer.getDesignation().isBlank()) {
            throw new IllegalArgumentException("Designation is required.");
        }

        BigDecimal packageLpa = offer.getOfferedPackageLpa();

        if (packageLpa == null ||
                packageLpa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Offered package must be greater than zero.");
        }

        if (offer.getOfferDate() == null ||
                offer.getOfferDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Offer date cannot be in the future.");
        }

        if (offer.getExpectedJoiningDate() != null &&
                offer.getExpectedJoiningDate()
                        .isBefore(offer.getOfferDate())) {
            throw new IllegalArgumentException(
                    "Joining date cannot be before offer date.");
        }

        offerDAO.add(offer);
    }
}
