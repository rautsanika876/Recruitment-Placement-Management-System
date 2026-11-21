package com.rpms;

public class EligibilityService {

    public String check(
            Candidate c,
            String requiredQualification,
            int minPassingYear,
            int maxPassingYear,
            double minPercentage,
            int maxBacklogs,
            String requiredTechnology) {

        if (c == null) {
            return "NOT_ELIGIBLE: candidate not found";
        }

        if (c.getPercentage() < minPercentage) {
            return "NOT_ELIGIBLE: percentage below cutoff";
        }

        if (c.getPassingYear() < minPassingYear ||
                c.getPassingYear() > maxPassingYear) {
            return "NOT_ELIGIBLE: passing year outside allowed range";
        }

        if (c.getBacklogCount() > maxBacklogs) {
            return "NOT_ELIGIBLE: too many backlogs";
        }

        if (requiredQualification != null &&
                !requiredQualification.isBlank() &&
                (c.getQualification() == null ||
                !c.getQualification().equalsIgnoreCase(requiredQualification))) {

            return "NOT_ELIGIBLE: qualification does not match";
        }

        if (requiredTechnology != null &&
                !requiredTechnology.isBlank() &&
                (c.getPrimaryTechnology() == null ||
                !c.getPrimaryTechnology().equalsIgnoreCase(requiredTechnology))) {

            return "NOT_ELIGIBLE: required technology does not match";
        }

        return "ELIGIBLE";
    }
}