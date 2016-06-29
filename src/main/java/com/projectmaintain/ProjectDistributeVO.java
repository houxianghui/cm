/*
 * @# ProjectDistributeVO.java 2008-11-21 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.blue.enums.YesOrNo;
import com.blue.project.ProjectList;
import com.eis.base.BaseVO;


public class ProjectDistributeVO extends BaseVO{
	private int id;
	private String type;
	private String projectId;
	private String userName;
	private String step;
	private String userId;
	private String startDate;
	private String endDate;
	private String isDone;
	private String memo;
	private String reason;
	private String ready;
	private String taskName;
	private int notifyDay;
	
	private double score;
	
	private int reminingDays;
	private String hasLink;
	private ProjectList project;
	
	private Integer actionId;
	private String moduleId;
	/**
	 * @return
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @return
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @return
	 */
	public String getStartDate() {
		return startDate;
	}


	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param string
	 */
	public void setEndDate(String string) {
		endDate = string;
	}

	/**
	 * @param string
	 */
	public void setProjectId(String string) {
		projectId = string;
	}

	/**
	 * @param string
	 */
	public void setStartDate(String string) {
		startDate = string;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string) {
		userId = string;
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param string
	 */
	public void setUserName(String string) {
		userName = string;
	}

	/**
	 * @return
	 */
	public String getIsDone() {
		return isDone;
	}

	/**
	 * @param string
	 */
	public void setIsDone(String string) {
		isDone = string;
	}

	/**
	 * @return
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param string
	 */
	public void setMemo(String string) {
		memo = string;
	}

	/**
	 * @return
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param string
	 */
	public void setReason(String string) {
		reason = string;
	}

	/**
	 * @return
	 */
	public String getReady() {
		return ready;
	}

	/**
	 * @param string
	 */
	public void setReady(String string) {
		ready = string;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}



	/**
	 * @return
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param d
	 */
	public void setScore(double d) {
		score = d;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNotifyDay() {
		return notifyDay;
	}

	public void setNotifyDay(int notifyDay) {
		this.notifyDay = notifyDay;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getHasLink() {
		return hasLink;
	}

	public void setHasLink(String hasLink) {
		this.hasLink = hasLink;
	}

	public int getReminingDays() {
		return reminingDays;
	}

	public void setReminingDays(int reminingDays) {
		this.reminingDays = reminingDays;
	}

	public ProjectList getProject() {
		return project;
	}

	public void setProject(ProjectList project) {
		this.project = project;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}
