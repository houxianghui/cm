package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

public enum ChangeType {
	requireChange("需求变更"),
	designChange("设计变更"),
	solutionChange("方案变更"),
	caseChange("案例变更"),
	planChange("计划变更");
	
	private String desc;
	
	private ChangeType(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}
	public static List<LabelValueBean> toCollection(){
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		ChangeType[] l = ChangeType.values();
		for(ChangeType s:l){
			LabelValueBean lv = new LabelValueBean();
			lv.setLabel(s.getDesc());
			lv.setValue(s.toString());
			result.add(lv);
		}
		return result;
	}
}
