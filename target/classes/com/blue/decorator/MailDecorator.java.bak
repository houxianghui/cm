package com.blue.decorator;

import java.util.ArrayList;
import java.util.List;

public class MailDecorator implements StringDecorator {
	private List<T> l = new ArrayList<T>();

	public String decorate() {
		StringBuffer sb = new StringBuffer();
		for(T s: l){
			sb.append(s.key);
			sb.append(":[");
			sb.append(s.value);
			sb.append("]\r\n");
		}
		return sb.toString();
	}

	public void add(String key,String value){
		l.add(new T(key,value));
	}
	class T{
		String key;
		String value;
		T(String key,String value){
			this.key = key;
			this.value = value;
		}
	}
}
