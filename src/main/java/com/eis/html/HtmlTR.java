package com.eis.html;

import java.util.ArrayList;
import java.util.List;

public class HtmlTR extends HtmlTable {
	private List<HtmlTable> tdList = new ArrayList<HtmlTable>();
	
	public void addTD(HtmlTD td){
		tdList.add(td);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		for(HtmlTable ht:tdList){
			sb.append(ht.toString());
		}
		sb.append("</tr>");
		return sb.toString();
	}
}
