package com.blue.version.subsysversion;

import java.util.Collection;

import org.apache.struts.util.LabelValueBean;

import com.eis.base.BaseForm;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class SubSysVersionForm extends BaseForm {
	
    private Long id;

    
    private String versionId;

    
    private String sysName;

    
    private String preVersion;

    
    private String nextVersion;

    
    
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

    
    public String getSysName() {
        return sysName;
    }

    
    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    
    public String getPreVersion() {
        return preVersion;
    }

    
    public void setPreVersion(String preVersion) {
        this.preVersion = preVersion == null ? null : preVersion.trim();
    }

    
    public String getNextVersion() {
        return nextVersion;
    }

    
    public void setNextVersion(String nextVersion) {
        this.nextVersion = nextVersion == null ? null : nextVersion.trim();
    }
	
}
