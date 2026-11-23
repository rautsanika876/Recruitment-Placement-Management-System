# Data Dictionary (summary)
| Table | Purpose | Primary Key |
|---|---|---|
| roles | System role master | role_id |
| users | Authorized system users | user_id |
| candidates | Candidate and academic profile | candidate_id |
| skills | Skill master | skill_id |
| candidate_skills | Candidate-skill mapping | candidate_skill_id |
| companies | Employer details | company_id |
| jobs | Job openings and eligibility criteria | job_id |
| job_skills | Job-skill mapping | job_skill_id |
| applications | Candidate application per job | application_id |
| application_history | Application status audit trail | history_id |
| interview_rounds | Scheduled interview rounds | interview_id |
| interview_feedback | Interview ratings and recommendation | feedback_id |
| offers | Offer details and status | offer_id |
| placements | Joining and placement outcome | placement_id |
| activity_logs | Important user/system activity | log_id |

Relationships include Company 1-to-many Jobs; Candidate 1-to-many Applications; Job 1-to-many Applications; Application 1-to-many Interview Rounds; Interview Round 1-to-0/1 Feedback; Application 1-to-0/1 Offer; Offer 1-to-0/1 Placement.