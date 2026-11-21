
package com.rpms;

import java.time.LocalDate;
import java.time.LocalTime;

public class Interview {

    private int interviewId;
    private int applicationId;
    private int roundNumber;
    private String roundType;
    private LocalDate scheduledDate;
    private LocalTime scheduledTime;
    private String mode;
    private String interviewerName;
    private String meetingOrLocation;
    private String status;

    public Interview() {
    }

    public Interview(int applicationId, int roundNumber,
                     String roundType, LocalDate scheduledDate,
                     LocalTime scheduledTime, String mode,
                     String interviewerName, String meetingOrLocation) {

        this.applicationId = applicationId;
        this.roundNumber = roundNumber;
        this.roundType = roundType;
        this.scheduledDate = scheduledDate;
        this.scheduledTime = scheduledTime;
        this.mode = mode;
        this.interviewerName = interviewerName;
        this.meetingOrLocation = meetingOrLocation;
        this.status = "SCHEDULED";
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getRoundType() {
        return roundType;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }

    public String getMode() {
        return mode;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public String getMeetingOrLocation() {
        return meetingOrLocation;
    }

    public String getStatus() {
        return status;
    }
}
