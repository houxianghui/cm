/*
 * @# Button.java 2009-9-9 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.eis.html;


public class Button implements HtmlBase {
	public Button(){}
	public Button(String value){
		this.value = value;
	}
	public Button(String value,String id,String name){
		this.value = value;
		this.id = id;
		this.name =name;
	}
	private String name="";
	private String id;
	private String value;
	private String action;
	public String getElement() {
		StringBuffer sb = new StringBuffer("<input type=\"button\" name=\"");
		sb.append(name);
		sb.append("\" value=\"");
		sb.append(value);
		sb.append("\" onClick=\"");
		sb.append(action);
		sb.append("\" id='");
		sb.append(id);
		sb.append("' class=\"Button\">&nbsp;");
		return sb.toString();
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
