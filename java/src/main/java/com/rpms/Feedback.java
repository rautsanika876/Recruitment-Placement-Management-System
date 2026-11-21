
package com.rpms;

public class Feedback {

    private int feedbackId;
    private int interviewId;
    private int technicalRating;
    private int communicationRating;
    private int problemSolvingRating;
    private int overallRating;
    private String recommendation;
    private String remarks;
    private int submittedBy;

    public Feedback(int interviewId,
                    int technicalRating,
                    int communicationRating,
                    int problemSolvingRating,
                    int overallRating,
                    String recommendation,
                    String remarks,
                    int submittedBy) {

        this.interviewId = interviewId;
        this.technicalRating = technicalRating;
        this.communicationRating = communicationRating;
        this.problemSolvingRating = problemSolvingRating;
        this.overallRating = overallRating;
        this.recommendation = recommendation;
        this.remarks = remarks;
        this.submittedBy = submittedBy;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public int getTechnicalRating() {
        return technicalRating;
    }

    public int getCommunicationRating() {
        return communicationRating;
    }

    public int getProblemSolvingRating() {
        return problemSolvingRating;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public String getRemarks() {
        return remarks;
    }

    public int getSubmittedBy() {
        return submittedBy;
    }
}
