package com.blue.version.databasechg;

import java.util.Collection;

import org.apache.struts.util.LabelValueBean;

import com.eis.base.BaseForm;

public class DatabaseChgForm extends BaseForm {
	
    private Long id;

    
    private String versionId;

    
    private String tableName;

    
    private String columnName;

    
    private String subSys;

    
    private String chgType;

    
    private String originl;

    
    private String target;
    
    
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

    
    public String getTableName() {
        return tableName;
    }

    
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    
    public String getColumnName() {
        return columnName;
    }

    
    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
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
}
