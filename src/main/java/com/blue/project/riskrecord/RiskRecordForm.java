package com.blue.project.riskrecord;

import java.util.Collection;

import org.apache.struts.util.LabelValueBean;

import com.blue.enums.RiskLv;
import com.blue.enums.RiskStatus;
import com.blue.enums.RiskType;
import com.eis.base.BaseForm;

public class RiskRecordForm extends BaseForm{
    
    private Long id;

    
    private String projectId;

    
    private String riskName;

    
    private String riskType;
    private Collection<LabelValueBean> riskTypeCollection;
    
    private String riskLv;
    private Collection<LabelValueBean> riskLvCollection;
    
    private String riskStatus;
    private Collection<LabelValueBean> riskStatusCollection;
    
    private String solution;
    private String userId;
    
    
    private String inputDate;

    
    private String memo;
    private String soluteDate;
    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getProjectId() {
        return projectId;
    }

    
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    
    public String getRiskName() {
        return riskName;
    }

    
    public void setRiskName(String riskName) {
        this.riskName = riskName == null ? null : riskName.trim();
    }

    
    public String getRiskType() {
        return riskType;
    }

    
    public void setRiskType(String riskType) {
        this.riskType = riskType == null ? null : riskType.trim();
    }

    
    public String getRiskLv() {
        return riskLv;
    }

    
    public void setRiskLv(String riskLv) {
        this.riskLv = riskLv == null ? null : riskLv.trim();
    }

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    
    public String getInputDate() {
        return inputDate;
    }

    
    public void setInputDate(String inputDate) {
        this.inputDate = inputDate == null ? null : inputDate.trim();
    }

    
    public String getMemo() {
        return memo;
    }

    
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }


	public Collection<LabelValueBean> getRiskTypeCollection() {
		return RiskType.toCollection();
	}


	public void setRiskTypeCollection(Collection<LabelValueBean> riskTypeCollection) {
		this.riskTypeCollection = riskTypeCollection;
	}


	public Collection<LabelValueBean> getRiskLvCollection() {
		return RiskLv.toCollection();
	}


	public void setRiskLvCollection(Collection<LabelValueBean> riskLvCollection) {
		this.riskLvCollection = riskLvCollection;
	}


	public String getRiskStatus() {
		return riskStatus;
	}


	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}


	public Collection<LabelValueBean> getRiskStatusCollection() {
		return RiskStatus.toCollection();
	}


	public void setRiskStatusCollection(Collection<LabelValueBean> riskStatusCollection) {
		this.riskStatusCollection = riskStatusCollection;
	}


	public String getSolution() {
		return solution;
	}


	public void setSolution(String solution) {
		this.solution = solution;
	}


	public String getSoluteDate() {
		return soluteDate;
	}


	public void setSoluteDate(String soluteDate) {
		this.soluteDate = soluteDate;
	}
}