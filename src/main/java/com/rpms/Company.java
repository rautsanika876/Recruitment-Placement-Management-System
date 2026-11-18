
package com.rpms;

public class Company {
    private int companyId;
    private String companyCode;
    private String companyName;
    private String industry;
    private String website;
    private String location;
    private String companyType;
    private String hrName;
    private String hrEmail;
    private String hrMobile;
    private String status;

    public Company(String companyCode, String companyName,
                   String industry, String website, String location,
                   String companyType, String hrName,
                   String hrEmail, String hrMobile) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.industry = industry;
        this.website = website;
        this.location = location;
        this.companyType = companyType;
        this.hrName = hrName;
        this.hrEmail = hrEmail;
        this.hrMobile = hrMobile;
        this.status = "ACTIVE";
    }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public String getCompanyCode() { return companyCode; }
    public String getCompanyName() { return companyName; }
    public String getIndustry() { return industry; }
    public String getWebsite() { return website; }
    public String getLocation() { return location; }
    public String getCompanyType() { return companyType; }
    public String getHrName() { return hrName; }
    public String getHrEmail() { return hrEmail; }
    public String getHrMobile() { return hrMobile; }
    public String getStatus() { return status; }
}
