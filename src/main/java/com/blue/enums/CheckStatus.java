package com.blue.enums;

public enum CheckStatus {
	Y("通过"),
	N("未处理"),
	R("拒绝");
	
	private String desc;
	private CheckStatus(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}
}
