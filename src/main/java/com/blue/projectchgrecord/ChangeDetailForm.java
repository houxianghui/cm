package com.blue.projectchgrecord;

import java.util.Collection;

import org.apache.struts.util.LabelValueBean;

import com.eis.base.BaseForm;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class ChangeDetailForm extends BaseForm {
	 
    private Long detailId;

    
    private Long id;

    
    private String changeContent;

    
    private String type;
    private Collection<LabelValueBean> types;
    
    private String memo;

    
    public Long getDetailId() {
        return detailId;
    }

    
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getChangeContent() {
        return changeContent;
    }

    
    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent == null ? null : changeContent.trim();
    }

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    
    public String getMemo() {
        return memo;
    }

    
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }


	public Collection<LabelValueBean> getTypes() {
		return SingleDicMap.getOptionCollection(SingleDic.DB_CHG_ACT);
	}


	public void setTypes(Collection<LabelValueBean> types) {
		this.types = types;
	}
}
