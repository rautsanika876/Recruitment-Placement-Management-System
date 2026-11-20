
package com.rpms;

import java.sql.SQLException;

public class FeedbackService {

    private final FeedbackDAO feedbackDAO = new FeedbackDAO();

    public void submitFeedback(Feedback feedback)
            throws SQLException {

        if (feedback.getInterviewId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid interview ID.");
        }

        if (feedback.getTechnicalRating() < 1
                || feedback.getTechnicalRating() > 5
                || feedback.getCommunicationRating() < 1
                || feedback.getCommunicationRating() > 5
                || feedback.getProblemSolvingRating() < 1
                || feedback.getProblemSolvingRating() > 5
                || feedback.getOverallRating() < 1
                || feedback.getOverallRating() > 5) {

            throw new IllegalArgumentException(
                    "Ratings must be between 1 and 5.");
        }

        if (feedback.getRecommendation() == null
                || feedback.getRecommendation().isBlank()) {
            throw new IllegalArgumentException(
                    "Recommendation is required.");
        }

        if (feedback.getSubmittedBy() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid user ID.");
        }

        feedbackDAO.add(feedback);
    }
}
