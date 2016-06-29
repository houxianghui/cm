package com.blue.project.reviewrecord;

import com.eis.base.BaseForm;

public class ReviewRecordForm extends BaseForm{
    
    private Long id;

    
    private String projectId;

    
    private String title;

    
    private String content;

    
    private String userId;

    
    private String inputDate;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getProjectId() {
        return projectId;
    }

    
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    
    public String getInputDate() {
        return inputDate;
    }

    
    public void setInputDate(String inputDate) {
        this.inputDate = inputDate == null ? null : inputDate.trim();
    }
}