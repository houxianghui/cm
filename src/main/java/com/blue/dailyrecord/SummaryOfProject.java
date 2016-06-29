package com.blue.dailyrecord;

public class SummaryOfProject {
	private String projectId;
	private String projectName;
	private double planCost;
	private double checkedCost;
	private double inputCost;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public double getPlanCost() {
		return planCost;
	}
	public void setPlanCost(double planCost) {
		this.planCost = planCost;
	}
	public double getCheckedCost() {
		return checkedCost;
	}
	public void setCheckedCost(double checkedCost) {
		this.checkedCost = checkedCost;
	}
	public double getInputCost() {
		return inputCost;
	}
	public void setInputCost(double inputCost) {
		this.inputCost = inputCost;
	}
}
