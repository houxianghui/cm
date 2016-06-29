package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

public enum RiskStatus {
	up("��������"),down("�����½�"),stable("����ƽ��"),close("�ر�");
	private String desc;
	RiskStatus(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public static List<LabelValueBean> toCollection(){
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		RiskStatus[] l = RiskStatus.values();
		for(RiskStatus s:l){
			LabelValueBean lv = new LabelValueBean();
			lv.setLabel(s.getDesc());
			lv.setValue(s.toString());
			result.add(lv);
		}
		return result;
	}
}
