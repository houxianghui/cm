package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * 年休
事假
病假
产假
丧假
婚假
调休
空闲
培训
售前
管理
 * @author blue
 *
 */
public enum DailyType {
	nianXiu("年休"),
	shiJia("事假"),
	bingJia("病假"),
	chanJia("产假"),
	sangJia("丧假"),
	hunJia("婚假"),
	tiaoXiu("调休"),
	kongXian("空闲"),
	guanLi("管理"),
	chanPin("产品");
	
	
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
