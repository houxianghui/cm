package com.eis.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.eis.util.CheckUtil;

public class LinkIndicator extends TagSupport {
	private String hasLink;
	private String projectId;
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try{
			if(CheckUtil.isEmptry(hasLink) || hasLink.equals("N")){
				out.print("");
			}else{
				out.print("<a href=\"ProjectFiles.do?act=list&projectId="+projectId+"\"><span class=\"icon-other ui-icon-mylink\"></span></a>");
			}
			return (EVAL_BODY_INCLUDE);
		}catch(IOException e){
			throw new JspException(e);
		}
	}
	public String getHasLink() {
		return hasLink;
	}

	public void setHasLink(String hasLink) {
		this.hasLink = hasLink;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
}
