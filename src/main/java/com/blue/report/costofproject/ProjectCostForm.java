package com.blue.report.costofproject;

import com.eis.base.BaseForm;

public class ProjectCostForm extends BaseForm {
	private String startDate;
	private String endDate;
	private String[] ids;
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
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
}
