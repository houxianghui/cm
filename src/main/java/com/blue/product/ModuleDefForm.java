package com.blue.product;

import com.eis.base.BaseForm;

public class ModuleDefForm extends BaseForm {
	 
    private String moduleId;

    
    private String name;

    
    private String managerId;

    
    private String participate;

    
    private String version;

    
    private String productCode;

    
    public String getModuleId() {
        return moduleId;
    }

    
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
    public String getManagerId() {
        return managerId;
    }

    
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    
    public String getParticipate() {
        return participate;
    }

    
    public void setParticipate(String participate) {
        this.participate = participate == null ? null : participate.trim();
    }

    
    public String getVersion() {
        return version;
    }

    
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    
    public String getProductCode() {
        return productCode;
    }

    
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }
}
