package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * U 未分配
 * S 已分配
 * P 暂停
 * F 完成
 * D 删除
 * A 全部
 * @author blue
 *
 */
public enum Status {
	A("全部"),
	/**
	 * 未分配
	 */
	U("未分配"),
	/**
	 * 已分配
	 */
	S("已分配"),
	/**
	 * 暂停
	 */
	P("暂停"),
	/**
	 *完成 
	 */
	F("完成 "),
	/**
	 * 删除
	 */
	D("删除");
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
