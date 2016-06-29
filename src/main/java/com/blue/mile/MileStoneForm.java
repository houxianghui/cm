package com.blue.mile;

import com.eis.base.BaseForm;

public class MileStoneForm extends BaseForm {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long mileStoneId;

    
    private String projectId;

    
    private String stoneName;

    
    private String endDate;

    
    private String memo;

    
    private String inputUser;

    
    private String inputDate;

    public String getProjectId() {
        return projectId;
    }

    
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    
    public String getStoneName() {
        return stoneName;
    }

    
    public void setStoneName(String stoneName) {
        this.stoneName = stoneName == null ? null : stoneName.trim();
    }

    
    public String getEndDate() {
        return endDate;
    }

    
    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
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


	public Long getMileStoneId() {
		return mileStoneId;
	}


	public void setMileStoneId(Long mileStoneId) {
		this.mileStoneId = mileStoneId;
	}
}
