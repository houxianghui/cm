package com.blue.travel;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.cache.SingleDicMap;

public class TravelInfoForm extends BaseForm {
    
	
	private String depart_f;
	private String project_f;
	private Collection depart_f_options;
	private String results;
	private Collection allowance_options;
	
	private Collection city_options;
	
	public Collection getCity_options() {
		return  SingleDicMap.getOptionCollection("7004");
	}

	public void setCity_options(Collection city_options) {
		this.city_options = city_options;
	}

	public Collection getAllowance_options() {
		return  SingleDicMap.getOptionCollection("7002");
	}

	public void setAllowance_options(Collection allowance_options) {
		this.allowance_options = allowance_options;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public Collection getDepart_f_options() {
		return  SingleDicMap.getOptionCollection("9990");
	}

	public void setDepart_f_options(Collection collection) {
		depart_f_options = collection;
	}
	
	
	public String getDepart_f() {
		return depart_f;
	}

	public void setDepart_f(String depart_f) {
		this.depart_f = depart_f;
	}

	public String getProject_f() {
		return project_f;
	}

	public void setProject_f(String project_f) {
		this.project_f = project_f;
	}
	
	/**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.EXPENSES_ID
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Long expensesId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.PROJECT_NO
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private String projectNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.PROJECT_NAME
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private String projectName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.ALLOWANCE_PERDAY
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Double allowancePerday;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.ALLOWANCE_DAYS
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Integer allowanceDays;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.ALLOWANCE_TOTAL
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Double allowanceTotal;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.ALLOWANCE_PERDAY2
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Double allowancePerday2;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.ALLOWANCE_DAYS2
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Integer allowanceDays2;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.ALLOWANCE_TOTAL2
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Double allowanceTotal2;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.LODGING_INVOICENO
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Integer lodgingInvoiceno;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.LODGING_AMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Double lodgingAmt;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.TAXI_INVOICENO
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Integer taxiInvoiceno;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.TAXI_AMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Double taxiAmt;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.IMPREST_PERSON
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private String imprestPerson;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.IMPREST_AMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Double imprestAmt;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.IMPREST_EXAMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private Double imprestExamt;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.DEPT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private String dept;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.TRAVEL_PERSON
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private String travelPerson;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.TRAVEL_REASON
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private String travelReason;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.CURR_USER
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private String currUser;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column travel_info.CURR_STAT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    private String currStat;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.EXPENSES_ID
     *
     * @return the value of travel_info.EXPENSES_ID
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Long getExpensesId() {
        return expensesId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.EXPENSES_ID
     *
     * @param expensesId the value for travel_info.EXPENSES_ID
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setExpensesId(Long expensesId) {
        this.expensesId = expensesId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.PROJECT_NO
     *
     * @return the value of travel_info.PROJECT_NO
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public String getProjectNo() {
        return projectNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.PROJECT_NO
     *
     * @param projectNo the value for travel_info.PROJECT_NO
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.PROJECT_NAME
     *
     * @return the value of travel_info.PROJECT_NAME
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.PROJECT_NAME
     *
     * @param projectName the value for travel_info.PROJECT_NAME
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.ALLOWANCE_PERDAY
     *
     * @return the value of travel_info.ALLOWANCE_PERDAY
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Double getAllowancePerday() {
        return allowancePerday;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.ALLOWANCE_PERDAY
     *
     * @param allowancePerday the value for travel_info.ALLOWANCE_PERDAY
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setAllowancePerday(Double allowancePerday) {
        this.allowancePerday = allowancePerday;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.ALLOWANCE_DAYS
     *
     * @return the value of travel_info.ALLOWANCE_DAYS
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Integer getAllowanceDays() {
        return allowanceDays;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.ALLOWANCE_DAYS
     *
     * @param allowanceDays the value for travel_info.ALLOWANCE_DAYS
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setAllowanceDays(Integer allowanceDays) {
        this.allowanceDays = allowanceDays;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.ALLOWANCE_TOTAL
     *
     * @return the value of travel_info.ALLOWANCE_TOTAL
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Double getAllowanceTotal() {
        return allowanceTotal;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.ALLOWANCE_TOTAL
     *
     * @param allowanceTotal the value for travel_info.ALLOWANCE_TOTAL
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setAllowanceTotal(Double allowanceTotal) {
        this.allowanceTotal = allowanceTotal;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.ALLOWANCE_PERDAY2
     *
     * @return the value of travel_info.ALLOWANCE_PERDAY2
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Double getAllowancePerday2() {
        return allowancePerday2;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.ALLOWANCE_PERDAY2
     *
     * @param allowancePerday2 the value for travel_info.ALLOWANCE_PERDAY2
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setAllowancePerday2(Double allowancePerday2) {
        this.allowancePerday2 = allowancePerday2;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.ALLOWANCE_DAYS2
     *
     * @return the value of travel_info.ALLOWANCE_DAYS2
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Integer getAllowanceDays2() {
        return allowanceDays2;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.ALLOWANCE_DAYS2
     *
     * @param allowanceDays2 the value for travel_info.ALLOWANCE_DAYS2
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setAllowanceDays2(Integer allowanceDays2) {
        this.allowanceDays2 = allowanceDays2;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.ALLOWANCE_TOTAL2
     *
     * @return the value of travel_info.ALLOWANCE_TOTAL2
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Double getAllowanceTotal2() {
        return allowanceTotal2;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.ALLOWANCE_TOTAL2
     *
     * @param allowanceTotal2 the value for travel_info.ALLOWANCE_TOTAL2
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setAllowanceTotal2(Double allowanceTotal2) {
        this.allowanceTotal2 = allowanceTotal2;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.LODGING_INVOICENO
     *
     * @return the value of travel_info.LODGING_INVOICENO
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Integer getLodgingInvoiceno() {
        return lodgingInvoiceno;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.LODGING_INVOICENO
     *
     * @param lodgingInvoiceno the value for travel_info.LODGING_INVOICENO
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setLodgingInvoiceno(Integer lodgingInvoiceno) {
        this.lodgingInvoiceno = lodgingInvoiceno;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.LODGING_AMT
     *
     * @return the value of travel_info.LODGING_AMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Double getLodgingAmt() {
        return lodgingAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.LODGING_AMT
     *
     * @param lodgingAmt the value for travel_info.LODGING_AMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setLodgingAmt(Double lodgingAmt) {
        this.lodgingAmt = lodgingAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.TAXI_INVOICENO
     *
     * @return the value of travel_info.TAXI_INVOICENO
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Integer getTaxiInvoiceno() {
        return taxiInvoiceno;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.TAXI_INVOICENO
     *
     * @param taxiInvoiceno the value for travel_info.TAXI_INVOICENO
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setTaxiInvoiceno(Integer taxiInvoiceno) {
        this.taxiInvoiceno = taxiInvoiceno;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.TAXI_AMT
     *
     * @return the value of travel_info.TAXI_AMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Double getTaxiAmt() {
        return taxiAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.TAXI_AMT
     *
     * @param taxiAmt the value for travel_info.TAXI_AMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setTaxiAmt(Double taxiAmt) {
        this.taxiAmt = taxiAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.IMPREST_PERSON
     *
     * @return the value of travel_info.IMPREST_PERSON
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public String getImprestPerson() {
        return imprestPerson;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.IMPREST_PERSON
     *
     * @param imprestPerson the value for travel_info.IMPREST_PERSON
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setImprestPerson(String imprestPerson) {
        this.imprestPerson = imprestPerson == null ? null : imprestPerson.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.IMPREST_AMT
     *
     * @return the value of travel_info.IMPREST_AMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Double getImprestAmt() {
        return imprestAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.IMPREST_AMT
     *
     * @param imprestAmt the value for travel_info.IMPREST_AMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setImprestAmt(Double imprestAmt) {
        this.imprestAmt = imprestAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.IMPREST_EXAMT
     *
     * @return the value of travel_info.IMPREST_EXAMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public Double getImprestExamt() {
        return imprestExamt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.IMPREST_EXAMT
     *
     * @param imprestExamt the value for travel_info.IMPREST_EXAMT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setImprestExamt(Double imprestExamt) {
        this.imprestExamt = imprestExamt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.DEPT
     *
     * @return the value of travel_info.DEPT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public String getDept() {
        return dept;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.DEPT
     *
     * @param dept the value for travel_info.DEPT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.TRAVEL_PERSON
     *
     * @return the value of travel_info.TRAVEL_PERSON
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public String getTravelPerson() {
        return travelPerson;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.TRAVEL_PERSON
     *
     * @param travelPerson the value for travel_info.TRAVEL_PERSON
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setTravelPerson(String travelPerson) {
        this.travelPerson = travelPerson == null ? null : travelPerson.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.TRAVEL_REASON
     *
     * @return the value of travel_info.TRAVEL_REASON
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public String getTravelReason() {
        return travelReason;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.TRAVEL_REASON
     *
     * @param travelReason the value for travel_info.TRAVEL_REASON
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setTravelReason(String travelReason) {
        this.travelReason = travelReason == null ? null : travelReason.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.CURR_USER
     *
     * @return the value of travel_info.CURR_USER
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public String getCurrUser() {
        return currUser;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.CURR_USER
     *
     * @param currUser the value for travel_info.CURR_USER
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setCurrUser(String currUser) {
        this.currUser = currUser == null ? null : currUser.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column travel_info.CURR_STAT
     *
     * @return the value of travel_info.CURR_STAT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public String getCurrStat() {
        return currStat;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column travel_info.CURR_STAT
     *
     * @param currStat the value for travel_info.CURR_STAT
     *
     * @ibatorgenerated Tue Mar 25 10:54:02 CST 2014
     */
    public void setCurrStat(String currStat) {
        this.currStat = currStat == null ? null : currStat.trim();
    }
    private String regDate;

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
    private Double travelAmt;

	public Double getTravelAmt() {
		return travelAmt;
	}

	public void setTravelAmt(Double travelAmt) {
		this.travelAmt = travelAmt;
	}
    private Double totalAmt;

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
    
	private String regDate_from;
	private String regDate_to;

	public String getRegDate_from() {
		return regDate_from;
	}

	public void setRegDate_from(String regDate_from) {
		this.regDate_from = regDate_from;
	}

	public String getRegDate_to() {
		return regDate_to;
	}

	public void setRegDate_to(String regDate_to) {
		this.regDate_to = regDate_to;
	}
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	private String dateFrom;
	private String dateTo;
	private String city;


	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}