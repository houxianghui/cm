/*
 * @(#) CheckUtil.java V(1.0) 2007-4-18 houxh
 * 
 * Copyright  (c)  2007 	Huateng. All Right Reserver. 
 */
package com.eis.util;

import com.eis.exception.MessageException;

public class CheckUtil {

	
	/**
	 * @author houxh 2007-12-21
	 * 检查给定的字符串是否为空
	 * 
	 * @param s
	 * @return 
	 */
	public static boolean isEmptry(String s){
		if(s == null || s.trim().length() == 0 || "null".equalsIgnoreCase(s)){
			return true;
		}
		return false;
	}
	
	public static void rejectEmpty(String s,String memo)throws MessageException{
		if(isEmptry(s)){
			throw new MessageException(memo+"不允许为空");
		}
	}
} 
