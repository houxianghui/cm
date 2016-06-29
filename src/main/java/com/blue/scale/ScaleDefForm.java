package com.blue.scale;

import com.eis.base.BaseForm;

public class ScaleDefForm extends BaseForm {
	
    private Integer scaleId;

    
    private String scaleDesc;

    
    private String scale;

    
    public Integer getScaleId() {
        return scaleId;
    }

    
    public void setScaleId(Integer scaleId) {
        this.scaleId = scaleId;
    }

    
    public String getScaleDesc() {
        return scaleDesc;
    }

    
    public void setScaleDesc(String scaleDesc) {
        this.scaleDesc = scaleDesc == null ? null : scaleDesc.trim();
    }

    
    public String getScale() {
        return scale;
    }

    
    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }
}
