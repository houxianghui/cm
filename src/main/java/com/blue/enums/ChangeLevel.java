package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

public enum ChangeLevel {
	hard("复杂级"),
	normal("中等级"),
	easy("一般级");
	
	private String desc;
	
	private ChangeLevel(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}
	public static List<LabelValueBean> toCollection(){
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		ChangeLevel[] l = ChangeLevel.values();
		for(ChangeLevel s:l){
			LabelValueBean lv = new LabelValueBean();
			lv.setLabel(s.getDesc());
			lv.setValue(s.toString());
			result.add(lv);
		}
		return result;
	}
}
