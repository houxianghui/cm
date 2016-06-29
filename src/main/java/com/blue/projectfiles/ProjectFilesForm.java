package com.blue.projectfiles;

import org.apache.struts.upload.FormFile;

import com.eis.base.BaseForm;

public class ProjectFilesForm extends BaseForm {
	private int id;
    private String projectId;
    private String versionId;
    private FormFile file;
    private String memo;
    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
}
