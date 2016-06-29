package com.blue.report.projectplan;

import java.util.List;

public class StepPlan {
	private String step;
	private String startDate;
	private String endDate;
	private double workLoad;
	private String manager;
	private List<String> users;
	public String getStartDate() {
		return startDate==null?"":startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate==null?"":endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public double getWorkLoad() {
		return workLoad;
	}
	public void setWorkLoad(double workLoad) {
		this.workLoad = workLoad;
	}
	public String getManager() {
		return manager==null?"":manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public String getStep() {
		return step==null?"":step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	
}
