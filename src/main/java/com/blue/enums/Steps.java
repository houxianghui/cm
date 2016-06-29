package com.blue.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * @author blue
 *	A("ȫ��",100),
	R("�������",0),
	G("��Ҫ���",1),
	X("��ϸ���",2),
	D("�����׶�",3),
	S("���ɲ���",4),
	U("�û�����",5),
	P("Ͷ��",6);
 */
public enum Steps {
	A("ȫ��",1000),
	L("��Ŀ����",0),
	C("��Ŀ�߻������",1),
	R("����׶�",5),
	G("��ƽ׶�",10),
	D("�����׶�",30),
	S("���ɲ���",40),
	U("�û�����",50),
	F("��Ʒ��ǰ",70),
	E("�ͻ�ʵʩ",80),
	Y("������ά",90),
	J("����֧��",100),
	Z("֪ʶ��ѵ",110),
	B("���ù���",120),
	H("��������",130),
	P("Ͷ��",60);
	
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
