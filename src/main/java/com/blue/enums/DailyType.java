package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * ����
�¼�
����
����
ɥ��
���
����
����
��ѵ
��ǰ
����
 * @author blue
 *
 */
public enum DailyType {
	nianXiu("����"),
	shiJia("�¼�"),
	bingJia("����"),
	chanJia("����"),
	sangJia("ɥ��"),
	hunJia("���"),
	tiaoXiu("����"),
	kongXian("����"),
	guanLi("����"),
	chanPin("��Ʒ");
	
	
	private String desc;
	
	DailyType(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}
	public static List<LabelValueBean> toCollection(){
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		DailyType[] l = DailyType.values();
		for(DailyType s:l){
			LabelValueBean lv = new LabelValueBean();
			lv.setLabel(s.getDesc());
			lv.setValue(s.toString());
			result.add(lv);
		}
		return result;
	}
	
}
