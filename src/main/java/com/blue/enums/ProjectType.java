package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

public enum ProjectType {
	P("��������",false,true),
	E("������ǿ��",false,true),
	F("ȱ��������",false,true),
	D("������ֲ��",false,true),
	B("���������",false,false),
	T("������",false,false),
	S("��ǰ֧����",true,false),
	A("ʵʩ֧����",true,false),
	KH("�ͻ�����",true,true),
	MT("��ά֧����",true,false),
	Q("����������",false,false),
	M("������",false,false),
	K("�ڲ���ѵ",false,false),
	WP("�ⲿ��ѵ",true,false);
	/**
	 * @param desc
	 * @param needProjectId �Ƿ���¼��Ա¼����Ŀ���
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
