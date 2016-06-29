package com.blue.version.codechg;

import org.apache.struts.upload.FormFile;

import com.eis.base.BaseForm;

public class CodeChgForm extends BaseForm {
	private FormFile file;
	private String memo;
	private String versionId;
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
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
