package com.blue.project.changerecord;

import java.util.Collection;

import org.apache.struts.util.LabelValueBean;

import com.blue.enums.ChangeLevel;
import com.blue.enums.ChangeType;
import com.eis.base.BaseForm;

public class ChangeRecordForm extends BaseForm {
	 
    private Integer recordId;

    
    private String projectId;

    
    private Integer changeId;

    
    private String changeTitle;

    
    private String changeType;

    
    private String changeLevel;

    
    private String reason;

    
    private String content;

    
    private String userId;

    
    private String changeDate;

    private Collection<LabelValueBean> changeTypeCollection;
    private Collection<LabelValueBean> changeLevelCollection;
    
    public Integer getRecordId() {
        return recordId;
    }

    
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    
    public String getProjectId() {
        return projectId;
    }

    
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    
    public Integer getChangeId() {
        return changeId;
    }

    
    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }

    
    public String getChangeTitle() {
        return changeTitle;
    }

    
    public void setChangeTitle(String changeTitle) {
        this.changeTitle = changeTitle == null ? null : changeTitle.trim();
    }

    
    public String getChangeType() {
        return changeType;
    }

    
    public void setChangeType(String changeType) {
        this.changeType = changeType == null ? null : changeType.trim();
    }

    
    public String getChangeLevel() {
        return changeLevel;
    }

    
    public void setChangeLevel(String changeLevel) {
        this.changeLevel = changeLevel == null ? null : changeLevel.trim();
    }

    
    public String getReason() {
        return reason;
    }

    
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    
    public String getContent() {
        return content;
    }

    
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    
    public String getChangeDate() {
        return changeDate;
    }

    
    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate == null ? null : changeDate.trim();
    }


	public Collection<LabelValueBean> getChangeTypeCollection() {
		return ChangeType.toCollection();
	}


	public void setChangeTypeCollection(Collection<LabelValueBean> changeTypeCollection) {
		this.changeTypeCollection = changeTypeCollection;
	}


	public Collection<LabelValueBean> getChangeLevelCollection() {
		return ChangeLevel.toCollection();
	}


	public void setChangeLevelCollection(Collection<LabelValueBean> changeLevelCollection) {
		this.changeLevelCollection = changeLevelCollection;
	}
}
