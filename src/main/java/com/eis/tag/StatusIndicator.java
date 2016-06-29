package com.eis.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.blue.enums.Status;

public class StatusIndicator extends TagSupport {
	private String status;
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try{
			if(Status.valueOf(status) == Status.U){
				out.print("<span class=\"warning\">"+Status.valueOf(status).getDesc()+"</span>");
			}else{
				out.print(Status.valueOf(status).getDesc());
			}
			return (EVAL_BODY_INCLUDE);
		}catch(IOException e){
			throw new JspException(e);
		}
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
}
