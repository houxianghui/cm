package com.blue.enums;

public enum Conditions {
	eq("="),
	ne("!="),
	bw(">="),
	bn(">"),
	ew("<="),
	en("<"),
	cn(" like "),
	nc(" not like "),
	nu(" is null"),
	nn(" is not null ");
	private Conditions(String value){
		this.value = value;
	}
	private String value;
	public String getValue(){
		return this.value;
	}
}
