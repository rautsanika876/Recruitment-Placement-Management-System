package com.rpms;

import java.sql.SQLException;
import java.time.LocalDate;

public class ApplicationService {

    private CandidateDAO candidateDAO = new CandidateDAO();
    private JobDAO jobDAO = new JobDAO();
    private ApplicationDAO applicationDAO = new ApplicationDAO();
    private EligibilityService eligibilityService = new EligibilityService();

    public void apply(String applicationCode, int candidateId,
                      int jobId, String source) throws SQLException {

        // Find Candidate
        Candidate candidate = candidateDAO.findById(candidateId);

        if (candidate == null) {
            System.out.println("Candidate not found!");
            return;
        }

        if (!"ACTIVE".equalsIgnoreCase(candidate.getProfileStatus())) {
            System.out.println("Candidate profile is inactive!");
            return;
        }

        // Find Job
        Job job = jobDAO.findById(jobId);

        if (job == null) {
            System.out.println("Job not found!");
            return;
        }

        if (!"OPEN".equalsIgnoreCase(job.getStatus())) {
            System.out.println("This job is not open!");
            return;
        }

        if (job.getApplicationDeadline() != null &&
                job.getApplicationDeadline().toLocalDate()
                        .isBefore(LocalDate.now())) {
            System.out.println("Application deadline has passed!");
            return;
        }

        // Eligibility Criteria
        int minYear = job.getMinPassingYear() == null
                ? 0 : job.getMinPassingYear();

        int maxYear = job.getMaxPassingYear() == null
                ? 9999 : job.getMaxPassingYear();

        double minPercentage = job.getMinPercentage() == null
                ? 0 : job.getMinPercentage().doubleValue();

        String eligibility = eligibilityService.check(
                candidate,
                job.getQualificationRule(),
                minYear,
                maxYear,
                minPercentage,
                job.getMaxBacklogs(),
                null
        );

        String eligibilityStatus =
                eligibility.equals("ELIGIBLE")
                        ? "ELIGIBLE" : "NOT_ELIGIBLE";

        // Create Application
        Application application = new Application(
                applicationCode,
                candidateId,
                jobId,
                source
        );

        application.setEligibilityStatus(eligibilityStatus);
        application.setEligibilityReason(eligibility);

        // Save Application
        applicationDAO.add(application);

        System.out.println("Eligibility Result: " + eligibility);
    }
}