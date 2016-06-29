package com.eis.html;

public class HtmlLabel implements HtmlBase {
	private String s ;
	public HtmlLabel(String s) {
		this.s = s;
	}

	public String getElement() {
		return s;
	}

}
