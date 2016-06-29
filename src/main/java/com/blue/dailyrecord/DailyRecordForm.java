package com.blue.dailyrecord;

import java.math.BigDecimal;
import java.util.Collection;

import org.apache.struts.upload.FormFile;

import com.blue.enums.Steps;
import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;

public class DailyRecordForm extends BaseForm {
	private Long recordId;

	private Integer id;

	private String projectId;

	private String step;
	
	private Collection steps;
	
	private String workDate;

	private BigDecimal taskCost;

	private String userId;

	private String inputDate;

	private String workMemo;

	private String workIssue;
	
	private String startDate;
	private String endDate;
	
	private FormFile file;
	
	private String reason;
	private String[] departIds;
	
	public Collection getStaff() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.STUFF_COLLECTION);
	}

	
	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId == null ? null : projectId.trim();
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step == null ? null : step.trim();
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate == null ? null : workDate.trim();
	}

	public BigDecimal getTaskCost() {
		return taskCost;
	}

	public void setTaskCost(BigDecimal taskCost) {
		this.taskCost = taskCost;
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

	public String getWorkMemo() {
		return workMemo;
	}

	public void setWorkMemo(String workMemo) {
		this.workMemo = workMemo == null ? null : workMemo.trim();
	}

	public String getWorkIssue() {
		return workIssue;
	}

	public void setWorkIssue(String workIssue) {
		this.workIssue = workIssue == null ? null : workIssue.trim();
	}

	public Collection getSteps() {
		return Steps.toCollection();
	}

	public void setSteps(Collection steps) {
		this.steps = steps;
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


	public FormFile getFile() {
		return file;
	}


	public void setFile(FormFile file) {
		this.file = file;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String[] getDepartIds() {
		return departIds;
	}


	public void setDepartIds(String[] departIds) {
		this.departIds = departIds;
	}
}
