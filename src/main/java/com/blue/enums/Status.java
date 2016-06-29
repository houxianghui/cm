package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * U δ����
 * S �ѷ���
 * P ��ͣ
 * F ���
 * D ɾ��
 * A ȫ��
 * @author blue
 *
 */
public enum Status {
	A("ȫ��"),
	/**
	 * δ����
	 */
	U("δ����"),
	/**
	 * �ѷ���
	 */
	S("�ѷ���"),
	/**
	 * ��ͣ
	 */
	P("��ͣ"),
	/**
	 *��� 
	 */
	F("��� "),
	/**
	 * ɾ��
	 */
	D("ɾ��");
	private Status(String desc){
		this.desc = desc;
	}
	private String desc;
	
	public String getDesc(){
		return this.desc;
	}
	public static List<LabelValueBean> toCollection(){
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		Status[] l = Status.values();
		for(Status s:l){
			LabelValueBean lv = new LabelValueBean();
			lv.setLabel(s.getDesc());
			lv.setValue(s.toString());
			result.add(lv);
		}
		return result;
	}
}
