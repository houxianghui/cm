/*
 * @# GetNotWriteNoteStuffForm.java 2009-2-26 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.eis.base.BaseForm;


public class GetNotWriteNoteStuffForm extends BaseForm {
	private String date;
	private String toDate;
	private String title;
	
	private String ids[];
	/**
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param string
	 */
	public void setDate(String string) {
		date = string;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
