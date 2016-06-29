package com.abc.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceReturn {
	public static final Boolean	SUCCESS         = true;
	public static final Boolean FAILURE         = false;
	
	public static final String  RESULT          = "result";
	public static final String  MESSAGE         = "message";
	
	public static final String  FIELD1    = "field1";
	public static final String  FIELD2    = "field2";
	public static final String  FIELD3    = "field3";
	public static final String  FIELD4    = "field4";
	public static final String  FIELD5    = "field5";
	public static final String  FIELD6    = "field6";
	public static final String  FIELD7    = "field7";
	public static final String  FIELD8    = "field8";
	public static final String  FIELD9    = "field9";
	public static final String  FIELD10   = "field10";

	public static final String  FIELD11   = "field11";
	public static final String  FIELD12   = "field12";
	public static final String  FIELD13   = "field13";
	public static final String  FIELD14   = "field14";
	public static final String  FIELD15   = "field15";
	public static final String  FIELD16   = "field16";
	
    public static final String DATACOUNT ="datacount";
	
    private String datanum;
    
	private Boolean            success;
	private String             errmsg;
	private Map<String,Object> resultMap = new HashMap<String,Object>();
	
	
	
	public void setDatanum(String datanum) {
		this.datanum = datanum;
	}

	public String getDatanum() {
		return datanum;
	}

	private String posworkattr; //wmz add 2010-08-06
	
	public String getPosworkattr() {
		return posworkattr;
	}

	public void setPosworkattr(String posworkattr) {
		this.posworkattr = posworkattr;
	}
	private String attr[];
	
	public String[] getAttr() {
		return attr;
	}

	public void setAttr(String[] attr) {
		this.attr = attr;
	}
	
	public ServiceReturn(Boolean result, String errmsg){
		this.success = result;
		this.errmsg  = errmsg;
	}
	
	public void put(String resultMapKey, Object resultMapValue){
		this.resultMap.put(resultMapKey, resultMapValue);
	}
	
	public Object get(String resultMapKey){
		return this.resultMap.get(resultMapKey);
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
}
