package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * 	L  ��ʾ
	M һ��
	H ����
 * @author blue
 *
 */
public enum RiskLv {
	L("��ʾ"),
	M("һ��"),
	H("����");
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
