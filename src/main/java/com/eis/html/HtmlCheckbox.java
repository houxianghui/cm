package com.eis.html;

public class HtmlCheckbox implements HtmlBase {
	private String id;
	private boolean checked;
	private String label;
	public HtmlCheckbox(String id,boolean checked) {
		this.id = id;
		this.checked = checked;
	}
	public HtmlCheckbox(String id,boolean checked,String label) {
		this.id = id;
		this.checked = checked;
		this.label = label;
	}
	
	public String getElement() {
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='checkbox' id='"+id+"' ");
		if(checked){
			sb.append("checked");
		}
		sb.append(">");
		if(label != null ){
			sb.append(label);
		}
		return sb.toString();
	}

}
