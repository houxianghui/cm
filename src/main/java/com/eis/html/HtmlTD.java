package com.eis.html;

public class HtmlTD extends HtmlTable {
	private HtmlBase element;
	
	private int rowSpan;
	public HtmlTD(HtmlBase element) {
		this.element = element;
	}
	public HtmlTD(String s){
		this.element = new HtmlLabel(s);
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<td");
		if(rowSpan != 0){
			sb.append(" rowspan='"+rowSpan+"' ");
		}
		sb.append(">");
		sb.append(element.getElement());
		sb.append("</td>");
		return sb.toString();
	}
	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}
}
