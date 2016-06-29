/*
 * @# ProjectDistributeForm.java 2008-11-21 houxh
 *
 */

package com.projectmaintain;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.util.DateUtil;

public class ProjectDistributeForm extends BaseForm {
	
	private String id_field;
    private String name_field;
    
	public String getId_field() {
		return id_field;
	}

	public void setId_field(String id_field) {
		this.id_field = id_field;
	}

	public String getName_field() {
		return name_field;
	}

	public void setName_field(String name_field) {
		this.name_field = name_field;
	}

	private String day;

	private String endDate;

	private String endDate_f;
	private String endDate_t;
	
	private String finalEndDate;

	private Integer id;

	private String isDone;

	private String memo;

	private Integer percent;

	private String project_f;

	private String projectId;

	private String reason;

	private String startDate;

	private String status;

	private Collection stepCollection;

	private String step;

	private Collection stuff;

	private String title;

	private Collection titleCollection;

	private String type;

	private String userId;

	private String userName;
	private String taskName;
	private Integer mileStoneId;
	private String moduleId;
	private Integer actionId;
	private String[] ids;
	public String getDay() {
		return day;
	}
	
	public String getEndDate_calc(){
		if(endDate_f != null){
			return DateUtil.getDateAfter(endDate_f, Integer.parseInt(day)); 
		}
		return "";
	}

	public String getEndDate() {
		return endDate;
	}

	public String getEndDate_f() {
		return endDate_f;
	}

	public String getFinalEndDate() {
		return finalEndDate;
	}

	public Integer getId() {
		return id;
	}

	public String getIsDone() {
		return isDone;
	}

	public String getMemo() {
		return memo;
	}

	public Integer getPercent() {
		return percent;
	}

	public String getProject_f() {
		return project_f;
	}

	public String getProjectId() {
		return projectId;
	}

	public String getReason() {
		return reason;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getStatus() {
		return status;
	}

	public Collection getStepCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.PROJECT_STEP);
	}

	public String getStep() {
		return step;
	}

	public Collection getStuff() {
		return ReDefSDicMap
				.getOptionCollection(RedefSDicCodes.STUFF_COLLECTION);
	}

	public String getTitle() {
		return title;
	}

	public Collection getTitleCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.TITLE);
	}

	public String getType() {
		return type;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setDay(String string) {
		day = string;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate == null ? null : endDate.trim();
	}

	public void setEndDate_f(String string) {
		endDate_f = string;
	}

	public void setFinalEndDate(String finalEndDate) {
		this.finalEndDate = finalEndDate == null ? null : finalEndDate.trim();
	}

	public void setId(int i) {
		id = i;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsDone(String isDone) {
		this.isDone = isDone == null ? null : isDone.trim();
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public void setProject_f(String string) {
		project_f = string;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId == null ? null : projectId.trim();
	}

	public void setReason(String string) {
		reason = string;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate == null ? null : startDate.trim();
	}

	public void setStatus(String string) {
		status = string;
	}

	public void setStep(String step) {
		this.step = step == null ? null : step.trim();
	}

	public void setStuff(Collection collection) {
		stuff = collection;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitleCollection(Collection titleCollection) {
		this.titleCollection = titleCollection;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public void setUserName(String string) {
		userName = string;
	}

	public Integer getMileStoneId() {
		return mileStoneId;
	}

	public void setMileStoneId(Integer mileStoneId) {
		this.mileStoneId = mileStoneId;
	}

	public String getEndDate_t() {
		return endDate_t;
	}

	public void setEndDate_t(String endDate_t) {
		this.endDate_t = endDate_t;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

}
