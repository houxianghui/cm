package com.blue.otherdaily;

import java.math.BigDecimal;
import java.util.Collection;

import org.apache.struts.util.LabelValueBean;

import com.blue.enums.DailyType;
import com.eis.base.BaseForm;

public class OtherDailyForm extends BaseForm {
	
    private Long id;

    
    private String type;

    private Collection<LabelValueBean> typeCollection;
    private String workDate;

    
    private BigDecimal cost;

    
    private String info;

    
    private String memo;

    
    private String inputUser;

    
    private String inputDate;
    
    private String startDate;
    private String endDate;
    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    
    public String getWorkDate() {
        return workDate;
    }

    
    public void setWorkDate(String workDate) {
        this.workDate = workDate == null ? null : workDate.trim();
    }

    
    public BigDecimal getCost() {
        return cost;
    }

    
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    
    public String getInfo() {
        return info;
    }

    
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    
    public String getMemo() {
        return memo;
    }

    
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    
    public String getInputUser() {
        return inputUser;
    }

    
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    
    public String getInputDate() {
        return inputDate;
    }

    
    public void setInputDate(String inputDate) {
        this.inputDate = inputDate == null ? null : inputDate.trim();
    }


	public Collection<LabelValueBean> getTypeCollection() {
		return DailyType.toCollection();
	}


	public void setTypeCollection(Collection<LabelValueBean> typeCollection) {
		this.typeCollection = typeCollection;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
