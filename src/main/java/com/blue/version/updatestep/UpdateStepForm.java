package com.blue.version.updatestep;

import com.eis.base.BaseForm;

public class UpdateStepForm extends BaseForm {
	  
    private Long id;

    
    private Integer step;

    
    private String memo;

    
    private String versionId;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public Integer getStep() {
        return step;
    }

    
    public void setStep(Integer step) {
        this.step = step;
    }

    
    public String getMemo() {
        return memo;
    }

    
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    
    public String getVersionId() {
        return versionId;
    }

    
    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }
}
