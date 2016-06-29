package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * 
 * @author blue
 *	MARKET �г�
	CUST �ͻ�
	TECH ����
	MANAGE ����
	PEOPLE ��Ա
 */
public enum RiskType {
	MARKET("�г�"),
	CUST("�ͻ�"),
	TECH("����"),
	MANAGE("����"),
	PEOPLE("��Ա");
	
	private String desc;
	
	RiskType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
	public static List<LabelValueBean> toCollection(){
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		RiskType[] l = RiskType.values();
		for(RiskType s:l){
			LabelValueBean lv = new LabelValueBean();
			lv.setLabel(s.getDesc());
			lv.setValue(s.toString());
			result.add(lv);
		}
		return result;
	}
}
