package com.blue.report.weekly;

public class Weekly {
	private String projectName;
	private String step;
	private String dating;
	private String workLoad;
	private String users;
	private String status;
	private String memo;
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getDating() {
		return dating;
	}
	public void setDating(String dating) {
		this.dating = dating;
	}
	public String getWorkLoad() {
		return workLoad;
	}
	public void setWorkLoad(String workLoad) {
		this.workLoad = workLoad;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo==null?"":memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
