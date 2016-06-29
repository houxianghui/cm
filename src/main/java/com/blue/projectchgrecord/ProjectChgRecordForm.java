package com.blue.projectchgrecord;

import java.util.Collection;

import org.apache.struts.util.LabelValueBean;

import com.eis.base.BaseForm;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class ProjectChgRecordForm extends BaseForm {
	 
    private Long id;

    
    private String fireDate;

    
    private String finishDate;

    
    private String changeType;

    
    private String fireUser;

    
    private String versionAfter;

    
    private String operUser;

    
    private String targetVersion;

    
    private String changeSeq;

    
    private String memo;

    
    private String baseLine;

    private String module;
    
    private Collection<LabelValueBean> modules;
    
    private Collection<LabelValueBean> changeTypes;
    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getFireDate() {
        return fireDate;
    }

    
    public void setFireDate(String fireDate) {
        this.fireDate = fireDate == null ? null : fireDate.trim();
    }

    
    public String getFinishDate() {
        return finishDate;
    }

    
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate == null ? null : finishDate.trim();
    }

    
    public String getChangeType() {
        return changeType;
    }

    
    public void setChangeType(String changeType) {
        this.changeType = changeType == null ? null : changeType.trim();
    }

    
    public String getFireUser() {
        return fireUser;
    }

    
    public void setFireUser(String fireUser) {
        this.fireUser = fireUser == null ? null : fireUser.trim();
    }

    
    public String getVersionAfter() {
        return versionAfter;
    }

    
    public void setVersionAfter(String versionAfter) {
        this.versionAfter = versionAfter == null ? null : versionAfter.trim();
    }

    
    public String getOperUser() {
        return operUser;
    }

    
    public void setOperUser(String operUser) {
        this.operUser = operUser == null ? null : operUser.trim();
    }

    
    public String getTargetVersion() {
        return targetVersion;
    }

    
    public void setTargetVersion(String targetVersion) {
        this.targetVersion = targetVersion == null ? null : targetVersion.trim();
    }

    
    public String getChangeSeq() {
        return changeSeq;
    }

    
    public void setChangeSeq(String changeSeq) {
        this.changeSeq = changeSeq == null ? null : changeSeq.trim();
    }

    
    public String getMemo() {
        return memo;
    }

    
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    
    public String getBaseLine() {
        return baseLine;
    }

    
    public void setBaseLine(String baseLine) {
        this.baseLine = baseLine == null ? null : baseLine.trim();
    }


	public String getModule() {
		return module;
	}


	public void setModule(String module) {
		this.module = module;
	}


	public Collection<LabelValueBean> getModules() {
		return SingleDicMap.getOptionCollection(SingleDic.DB_CHG_MODULE);
	}


	public void setModules(Collection<LabelValueBean> modules) {
		this.modules = modules;
	}


	public Collection<LabelValueBean> getChangeTypes() {
		return SingleDicMap.getOptionCollection(SingleDic.DB_CHG_TYPE);
	}


	public void setChangeTypes(Collection<LabelValueBean> changeTypes) {
		this.changeTypes = changeTypes;
	}
}
