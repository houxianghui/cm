package com.blue.version.versionhis;

import java.util.List;

import com.blue.project.ProjectList;
import com.blue.version.subsysversion.SubSysVersion;
import com.eis.base.BaseForm;

public class VersionHisForm extends BaseForm {
	
    private String versionId;

    private String versionId_f;
    private String planReleaseDate;

    
    private String isReleased;

    
    private String releaseDate;

    
    private String isChecked;

    
    private String inputUser;

    
    private String inputDate;
    private String memo;
    
    private List<String> projectList;
    private List<SubSysVersion> subSysList;
    
    public String getVersionId() {
        return versionId;
    }

    
    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }

    
    public String getPlanReleaseDate() {
        return planReleaseDate;
    }

    
    public void setPlanReleaseDate(String planReleaseDate) {
        this.planReleaseDate = planReleaseDate == null ? null : planReleaseDate.trim();
    }

    
    public String getIsReleased() {
        return isReleased;
    }

    
    public void setIsReleased(String isReleased) {
        this.isReleased = isReleased == null ? null : isReleased.trim();
    }

    
    public String getReleaseDate() {
        return releaseDate;
    }

    
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate == null ? null : releaseDate.trim();
    }

    
    public String getIsChecked() {
        return isChecked;
    }

    
    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked == null ? null : isChecked.trim();
    }

    
    public String getInputUser() {
        return inputUser;
    }

    
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    
    public String getInputDate() {
        return inputDate;
    }

    
    public void setInputDate(String inputDate) {
        this.inputDate = inputDate == null ? null : inputDate.trim();
    }


	public String getVersionId_f() {
		return versionId_f;
	}


	public void setVersionId_f(String versionId_f) {
		this.versionId_f = versionId_f;
	}


	public List<String> getProjectList() {
		return projectList;
	}


	public void setProjectList(List<String> projectList) {
		this.projectList = projectList;
	}


	public List<SubSysVersion> getSubSysList() {
		return subSysList;
	}


	public void setSubSysList(List<SubSysVersion> subSysList) {
		this.subSysList = subSysList;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}
}
