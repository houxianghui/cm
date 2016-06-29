package com.blue.version.paramchg;

import com.eis.base.BaseForm;

public class ParamChgForm extends BaseForm {
	
    private Long id;

    
    private String subSys;

    
    private String parentMenu;

    
    private String subMenu;

    
    private String memo;

    
    private String chgType;

    
    private String originl;

    
    private String target;

    
    private String versionId;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getSubSys() {
        return subSys;
    }

    
    public void setSubSys(String subSys) {
        this.subSys = subSys == null ? null : subSys.trim();
    }

    
    public String getParentMenu() {
        return parentMenu;
    }

    
    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu == null ? null : parentMenu.trim();
    }

    
    public String getSubMenu() {
        return subMenu;
    }

    
    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu == null ? null : subMenu.trim();
    }

    
    public String getMemo() {
        return memo;
    }

    
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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

    
    public String getVersionId() {
        return versionId;
    }

    
    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }
}
