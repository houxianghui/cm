package com.blue.version.datachg;

import com.eis.base.BaseForm;

public class DataChgForm extends BaseForm {
	
    private Long id;

    
    private String shellName;

    
    private String memo;

    
    private String versionId;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getShellName() {
        return shellName;
    }

    
    public void setShellName(String shellName) {
        this.shellName = shellName == null ? null : shellName.trim();
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
