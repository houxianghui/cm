package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

public enum ChangeType {
	requireChange("������"),
	designChange("��Ʊ��"),
	solutionChange("�������"),
	caseChange("�������"),
	planChange("�ƻ����");
	
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
