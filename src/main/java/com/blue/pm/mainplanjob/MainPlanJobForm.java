package com.blue.pm.mainplanjob;

import com.eis.base.BaseForm;

public class MainPlanJobForm extends BaseForm {
	private String projectId;

	
	private String userId;

	
	private String isDoen;

	
	private String inputDate;

	
	public String getProjectId() {
		return projectId;
	}

	
	public void setProjectId(String projectId) {
		this.projectId = projectId == null ? null : projectId.trim();
	}

	
	public String getUserId() {
		return userId;
	}

	
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	
	public String getIsDoen() {
		return isDoen;
	}

	
	public void setIsDoen(String isDoen) {
		this.isDoen = isDoen == null ? null : isDoen.trim();
	}

	
	public String getInputDate() {
		return inputDate;
	}

	
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate == null ? null : inputDate.trim();
	}
}
