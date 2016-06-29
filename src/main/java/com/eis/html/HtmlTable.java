package com.eis.html;

import java.util.ArrayList;
import java.util.List;
 
public class HtmlTable {
	private String id;
	private List<HtmlTable> trList = new ArrayList<HtmlTable>();
	public void addTr(HtmlTR tr){
		trList.add(tr);
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<table id='"+id+"'>");
		for(HtmlTable ht:trList){
			sb.append(ht.toString());
		}
		sb.append("</table>");
		return sb.toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
