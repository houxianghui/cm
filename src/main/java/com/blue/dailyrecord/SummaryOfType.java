package com.blue.dailyrecord;

import com.blue.enums.ProjectType;

public class SummaryOfType {
	private ProjectType type;
	private double planCost;
	private double checkedCost;
	private double inputCost;
	
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
	public ProjectType getType() {
		return type;
	}
	public void setType(ProjectType type) {
		this.type = type;
	}
}
