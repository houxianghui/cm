package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * @author blue
 *	A("全部",100),
	R("需求分析",0),
	G("概要设计",1),
	X("详细设计",2),
	D("开发阶段",3),
	S("集成测试",4),
	U("用户测试",5),
	P("投产",6);
 */
public enum Steps {
	A("全部",1000),
	L("项目立项",0),
	C("项目策划与管理",1),
	R("需求阶段",5),
	G("设计阶段",10),
	D("开发阶段",30),
	S("集成测试",40),
	U("用户测试",50),
	F("产品售前",70),
	E("客户实施",80),
	Y("生产运维",90),
	J("技术支持",100),
	Z("知识培训",110),
	B("配置管理",120),
	H("会议讨论",130),
	P("投产",60);
	
	private String desc;
	private int idx;
	private Steps(String desc,int idx){
		this.desc = desc;
		this.idx = idx;
	}
	public String getDesc(){
		return desc;
	}
	public int getIndex(){
		return idx;
	}
	public static List<LabelValueBean> toCollection(){
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		Steps[] l = Steps.values();
		for(Steps s:l){
			LabelValueBean lv = new LabelValueBean();
			lv.setLabel(s.getDesc());
			lv.setValue(s.toString());
			result.add(lv);
		}
		return result;
	}
}
