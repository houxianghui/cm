package com.blue.report.weekly;

import com.eis.base.BaseForm;

public class WeeklyReportForm extends BaseForm {
	private String date;
	private String[] ids;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
}
