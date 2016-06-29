package com.blue.product;

import com.eis.base.BaseForm;

public class ProductDefForm extends BaseForm {
	 
    private String productCode;

    
    private String name;

    
    private String managerId;

    
    private String latestVersion;

    
    private String lastPrdDate;

    
    private String nextPrdDate;

    
    private String memo;

    
    public String getProductCode() {
        return productCode;
    }

    
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
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

    
    public String getLatestVersion() {
        return latestVersion;
    }

    
    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion == null ? null : latestVersion.trim();
    }

    
    public String getLastPrdDate() {
        return lastPrdDate;
    }

    
    public void setLastPrdDate(String lastPrdDate) {
        this.lastPrdDate = lastPrdDate == null ? null : lastPrdDate.trim();
    }

    
    public String getNextPrdDate() {
        return nextPrdDate;
    }

    
    public void setNextPrdDate(String nextPrdDate) {
        this.nextPrdDate = nextPrdDate == null ? null : nextPrdDate.trim();
    }

    
    public String getMemo() {
        return memo;
    }

    
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}
