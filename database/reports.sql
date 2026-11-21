USE rpms_db;

-- 1. Candidate count by primary technology
SELECT primary_technology, COUNT(*) AS candidate_count
FROM candidates GROUP BY primary_technology ORDER BY candidate_count DESC;

-- 2. Open jobs by company
SELECT c.company_name, j.job_code, j.job_title, j.openings, j.location
FROM jobs j JOIN companies c ON c.company_id=j.company_id
WHERE j.status='OPEN' ORDER BY c.company_name;

-- 3. Applications by status
SELECT application_status, COUNT(*) AS total
FROM applications GROUP BY application_status;

-- 4. Company-wise joined placements
SELECT c.company_name, COUNT(p.placement_id) AS placed_count
FROM placements p JOIN companies c ON c.company_id=p.company_id
WHERE p.joining_status='JOINED'
GROUP BY c.company_id,c.company_name ORDER BY placed_count DESC;

-- 5. Pending interviews
SELECT i.interview_id, ca.full_name, co.company_name, j.job_title, i.scheduled_date, i.scheduled_time
FROM interview_rounds i
JOIN applications a ON a.application_id=i.application_id
JOIN candidates ca ON ca.candidate_id=a.candidate_id
JOIN jobs j ON j.job_id=a.job_id
JOIN companies co ON co.company_id=j.company_id
WHERE i.status='SCHEDULED' ORDER BY i.scheduled_date,i.scheduled_time;

-- 6. Recruitment funnel
SELECT
 (SELECT COUNT(*) FROM applications) AS applications,
 (SELECT COUNT(*) FROM applications WHERE application_status='SHORTLISTED') AS shortlisted,
 (SELECT COUNT(*) FROM applications WHERE application_status='INTERVIEWED') AS interviewed,
 (SELECT COUNT(*) FROM applications WHERE application_status='SELECTED') AS selected,
 (SELECT COUNT(*) FROM placements WHERE joining_status='JOINED') AS joined;

-- 7. Job aging
SELECT job_code,job_title, DATEDIFF(CURRENT_DATE,posted_date) AS days_open
FROM jobs WHERE status='OPEN' ORDER BY days_open DESC;

-- 8. Technology-wise placements
SELECT c.primary_technology,
       COUNT(p.placement_id) AS placed_count
FROM placements p
JOIN candidates c ON c.candidate_id = p.candidate_id
WHERE p.joining_status = 'JOINED'
GROUP BY c.primary_technology;

-- 9. Recruiter performance
SELECT u.full_name,
       COUNT(a.application_id) AS total_applications
FROM users u
JOIN applications a
ON a.assigned_recruiter_id = u.user_id
GROUP BY u.user_id, u.full_name;

-- 10. Offer-to-joining conversion
SELECT
    COUNT(DISTINCT o.offer_id) AS total_offers,
    COUNT(DISTINCT p.placement_id) AS joined_candidates
FROM offers o
LEFT JOIN placements p
ON p.offer_id = o.offer_id
AND p.joining_status = 'JOINED';
