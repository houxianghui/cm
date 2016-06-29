package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

public enum ProjectType {
	P("新需求类",false,true),
	E("功能增强类",false,true),
	F("缺陷修正类",false,true),
	D("数据移植类",false,true),
	B("参数变更类",false,false),
	T("任务类",false,false),
	S("售前支持类",true,false),
	A("实施支持类",true,false),
	KH("客户化类",true,true),
	MT("运维支持类",true,false),
	Q("质量管理类",false,false),
	M("管理类",false,false),
	K("内部培训",false,false),
	WP("外部培训",true,false);
	/**
	 * @param desc
	 * @param needProjectId 是否由录入员录入项目编号
	 * @param hasScale
	 */
	ProjectType(String desc,boolean needProjectId,boolean hasScale){
		this.desc = desc;
		this.needProjectId = needProjectId;
		this.hasScale = hasScale;
	}
	private String desc;
	private boolean needProjectId;
	private boolean hasScale;
	
	public String getDesc(){
		return desc;
	}
	
	public boolean needProjectId(){
		return needProjectId;
	}
	public static List<LabelValueBean> toCollection(){
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		ProjectType[] l = ProjectType.values();
		for(ProjectType s:l){
			LabelValueBean lv = new LabelValueBean();
			lv.setLabel(s.getDesc());
			lv.setValue(s.toString());
			result.add(lv);
		}
		return result;
	}

	public boolean isHasScale() {
		return hasScale;
	}
}
