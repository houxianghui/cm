/*********************************************************
 * File:BaseForm.java
 * 
 * @version 1.0
 * 
 * Date     2005-8-31
 * @author   辛勇
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.base;

import java.util.Collection;

import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

import com.blue.enums.Steps;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

/**
 * 说明：所有FormBean的父类
 * 
 */

public abstract class BaseForm extends ValidatorForm {

    /**
     * 构造函数
     */
    public BaseForm() {
        super();        
    }
    
    private String act = null;
    

	/**
	 * @return
	 */
	public String getAct() {
		return act;
	}

	/**
	 * @param string
	 */
	public void setAct(String string) {
		act = string;
	}
	
	public Collection<LabelValueBean> getDbChgTypeCollection(){
		return SingleDicMap.getOptionCollection(SingleDic.DB_CHG_ACT);
	}
	public Collection<LabelValueBean> getSubSysCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.SUB_SYS);
	}
	public Collection<LabelValueBean> getChgTypeCollection(){
		return SingleDicMap.getOptionCollection(SingleDic.CHG_TYPE);
	}
	public Collection<LabelValueBean> getPmCollection(){
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.PM);
	}
	public Collection<LabelValueBean> getStaffCollection(){
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.STUFF_COLLECTION);
	}
	public Collection<LabelValueBean> getYesOrNo(){
		return SingleDicMap.getOptionCollection(SingleDic.YES_OR_NO);
	}
	public Collection getStepCollection() {
		return Steps.toCollection();
	}
}
