package com.eis.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.eis.util.DateUtil;

public class IndicatorTag extends TagSupport {
	private String date;//要比较的日期
	private boolean exclude;//是否不做检查
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try{
			if(exclude){
				out.print("<span class=\"icon-indicator ui-icon-green\"></span>");
			}else{
				String today = DateUtil.getDTStr();
				long days = DateUtil.getDays(today,date);
				if(days <= 3 && days >= 0){
					out.print("<span class=\"icon-indicator ui-icon-yellow\"></span>");
				}else if(days<0){
					out.print("<span class=\"icon-indicator ui-icon-red\"></span>");
				}else{
					out.print("<span class=\"icon-indicator ui-icon-green\"></span>");
				}
			}
			return (EVAL_BODY_INCLUDE);
		}catch(IOException e){
			throw new JspException(e);
		}
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isExclude() {
		return exclude;
	}
	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}
}
