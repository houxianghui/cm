package com.blue.enums;

public enum YesOrNo {
	Y("ÊÇ"),N("·ñ");
	private String desc;
	private YesOrNo(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
}
