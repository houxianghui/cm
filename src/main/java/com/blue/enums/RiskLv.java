package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * 	L  提示
	M 一般
	H 严重
 * @author blue
 *
 */
public enum RiskLv {
	L("提示"),
	M("一般"),
	H("严重");
	private String desc;
	RiskLv(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
	public static List<LabelValueBean> toCollection(){
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		RiskLv[] l = RiskLv.values();
		for(RiskLv s:l){
			LabelValueBean lv = new LabelValueBean();
			lv.setLabel(s.getDesc());
			lv.setValue(s.toString());
			result.add(lv);
		}
		return result;
	}
}
