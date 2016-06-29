package com.blue.enums;

public enum DetailType {
	code(0),
	database(1),
	param(2),
	batch(3),
	report(4),
	data(5),
	step(3);
	private int index;
	DetailType(int i){
		index = i;
	}
	public int getIndex(){
		return index;
	}
	
}
