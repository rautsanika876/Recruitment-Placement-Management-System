
package com.rpms;

import java.sql.SQLException;
import java.time.LocalDate;

public class InterviewService {

    private final InterviewDAO interviewDAO = new InterviewDAO();

    public void scheduleInterview(Interview interview)
            throws SQLException {

        if (interview.getApplicationId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid application ID.");
        }

        if (interview.getRoundNumber() <= 0) {
            throw new IllegalArgumentException(
                    "Round number must be greater than zero.");
        }

        if (interview.getRoundType() == null
                || interview.getRoundType().isBlank()) {
            throw new IllegalArgumentException(
                    "Round type is required.");
        }

        if (interview.getScheduledDate() == null
                || interview.getScheduledDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Interview date cannot be in the past.");
        }

        if (interview.getMode() == null
                || interview.getMode().isBlank()) {
            throw new IllegalArgumentException(
                    "Interview mode is required.");
        }

        interviewDAO.add(interview);
    }
}
