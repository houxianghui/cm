package com.blue.enums;

public enum CheckStatus {
	Y("ͨ��"),
	N("δ����"),
	R("�ܾ�");
	
	private String desc;
	private CheckStatus(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}
}
