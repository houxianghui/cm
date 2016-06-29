package com.blue.version.reportchg;

import com.eis.base.BaseForm;

public class ReportChgForm extends BaseForm {
	
    private Long id;

    
    private String reportName;

    
    private String subSys;

    
    private String chgType;

    
    private String originl;

    
    private String target;

    
    private String location;

    
    private String versionId;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getReportName() {
        return reportName;
    }

    
    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    
    public String getSubSys() {
        return subSys;
    }

    
    public void setSubSys(String subSys) {
        this.subSys = subSys == null ? null : subSys.trim();
    }

    
    public String getChgType() {
        return chgType;
    }

    
    public void setChgType(String chgType) {
        this.chgType = chgType == null ? null : chgType.trim();
    }

    
    public String getOriginl() {
        return originl;
    }

    
    public void setOriginl(String originl) {
        this.originl = originl == null ? null : originl.trim();
    }

    
    public String getTarget() {
        return target;
    }

    
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    
    public String getLocation() {
        return location;
    }

    
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    
    public String getVersionId() {
        return versionId;
    }

    
    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }
}
