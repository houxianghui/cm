package com.blue.version.batchchg;

import com.eis.base.BaseForm;

public class BatchChgForm extends BaseForm {
	  
    private Long id;

    
    private String versionId;

    
    private String subSys;

    
    private String batchName;

    
    private String chgType;

    
    private String triggerType;

    
    private String preStep;

    
    private String nextStep;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getVersionId() {
        return versionId;
    }

    
    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }

    
    public String getSubSys() {
        return subSys;
    }

    
    public void setSubSys(String subSys) {
        this.subSys = subSys == null ? null : subSys.trim();
    }

    
    public String getBatchName() {
        return batchName;
    }

    
    public void setBatchName(String batchName) {
        this.batchName = batchName == null ? null : batchName.trim();
    }

    
    public String getChgType() {
        return chgType;
    }

    
    public void setChgType(String chgType) {
        this.chgType = chgType == null ? null : chgType.trim();
    }

    
    public String getTriggerType() {
        return triggerType;
    }

    
    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType == null ? null : triggerType.trim();
    }

    
    public String getPreStep() {
        return preStep;
    }

    
    public void setPreStep(String preStep) {
        this.preStep = preStep == null ? null : preStep.trim();
    }

    
    public String getNextStep() {
        return nextStep;
    }

    
    public void setNextStep(String nextStep) {
        this.nextStep = nextStep == null ? null : nextStep.trim();
    }
}
