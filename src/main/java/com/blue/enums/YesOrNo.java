package com.blue.enums;

public enum YesOrNo {
	Y("��"),N("��");
	private String desc;
	private YesOrNo(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
}
