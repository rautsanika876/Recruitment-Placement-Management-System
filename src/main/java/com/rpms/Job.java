package com.rpms;

import java.math.BigDecimal;
import java.sql.Date;

public class Job {

    private int jobId;
    private String jobCode;
    private int companyId;
    private String jobTitle;
    private String employmentType;
    private String location;
    private int openings;
    private BigDecimal minPackageLpa;
    private BigDecimal maxPackageLpa;
    private BigDecimal minPercentage;
    private Integer minPassingYear;
    private Integer maxPassingYear;
    private int maxBacklogs;
    private BigDecimal experienceMinYears;
    private String qualificationRule;
    private Date postedDate;
    private Date applicationDeadline;
    private String status;

    public Job(String jobCode, int companyId, String jobTitle,
               String employmentType, String location, int openings,
               BigDecimal minPackageLpa, BigDecimal maxPackageLpa,
               BigDecimal minPercentage, Integer minPassingYear,
               Integer maxPassingYear, int maxBacklogs,
               BigDecimal experienceMinYears, String qualificationRule,
               Date postedDate, Date applicationDeadline) {

        this.jobCode = jobCode;
        this.companyId = companyId;
        this.jobTitle = jobTitle;
        this.employmentType = employmentType;
        this.location = location;
        this.openings = openings;
        this.minPackageLpa = minPackageLpa;
        this.maxPackageLpa = maxPackageLpa;
        this.minPercentage = minPercentage;
        this.minPassingYear = minPassingYear;
        this.maxPassingYear = maxPassingYear;
        this.maxBacklogs = maxBacklogs;
        this.experienceMinYears = experienceMinYears;
        this.qualificationRule = qualificationRule;
        this.postedDate = postedDate;
        this.applicationDeadline = applicationDeadline;
        this.status = "OPEN";
    }

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public String getJobCode() { return jobCode; }
    public int getCompanyId() { return companyId; }
    public String getJobTitle() { return jobTitle; }
    public String getEmploymentType() { return employmentType; }
    public String getLocation() { return location; }
    public int getOpenings() { return openings; }
    public BigDecimal getMinPackageLpa() { return minPackageLpa; }
    public BigDecimal getMaxPackageLpa() { return maxPackageLpa; }
    public BigDecimal getMinPercentage() { return minPercentage; }
    public Integer getMinPassingYear() { return minPassingYear; }
    public Integer getMaxPassingYear() { return maxPassingYear; }
    public int getMaxBacklogs() { return maxBacklogs; }
    public BigDecimal getExperienceMinYears() { return experienceMinYears; }
    public String getQualificationRule() { return qualificationRule; }
    public Date getPostedDate() { return postedDate; }
    public Date getApplicationDeadline() { return applicationDeadline; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}