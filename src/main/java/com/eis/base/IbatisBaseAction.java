/*
 * @# IbatisBaseAction.java 2008-4-10 houxh
 *
 * Copyright  (c)  2008 	Huateng. All Right Reserv
 */
 
package com.eis.base;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.support.PagedListHolder;

import com.abc.logic.IbatisBO;
import com.eis.config.SysConfig;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;


public abstract class IbatisBaseAction extends BaseAction {
	protected IbatisBO bo;

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public abstract ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception;

	public void setBo(IbatisBO bo){
		this.bo = bo;
	}
	public IbatisBO getBo(){
		return bo;
	}
	
	public void writeList(HttpServletRequest request,HttpServletResponse response,List l)throws Exception{
		String rows = request.getParameter("rows");
		if(CheckUtil.isEmptry(rows)){
			rows = "10";
		}
		String page = request.getParameter("page");
		if(CheckUtil.isEmptry(page)){
			page = "1";
		}
		int pages = (int)Math.ceil((l.size()*1.0/Integer.parseInt(rows)));
		PagedListHolder list = new PagedListHolder();
		list.setSource(l);
		list.setPageSize(Integer.parseInt(rows));
		list.setPage(Integer.parseInt(page)-1);
		JSONObject o = new JSONObject();
		o.put("page", page);
		o.put("total",pages);
		o.put("rows", JSONArray.fromObject(list.getPageList()));
		o.put("records", l.size());
		writeAjaxResponse(response, o.toString());
	}
	public void writeListOnly(HttpServletRequest request,HttpServletResponse response,List l)throws Exception{
		writeAjaxResponse(response, JSONArray.fromObject(l).toString());
	}
	public void writeAjaxResponse(HttpServletResponse response,String s)throws Exception{
		PrintWriter pw = null;
		try{
			pw = response.getWriter();
			pw.write(s);
			pw.flush();
		}finally{
			if(pw != null){
				pw.close();
			}
		}
	}
	public void setPageResult(HttpServletRequest request, List resultList) {
		PageObject page = new PageObject();		
		PagedListHolder list = new PagedListHolder();		
		String pageNo = request.getParameter("pageNO");
		list.setSource(resultList);
		list.setPageSize(Integer.parseInt(SysConfig.getProperty("rowsPerPage")));
				
		if (CheckUtil.isEmptry(pageNo)) {
			list.setPage(0);
		} else {
			list.setPage(Integer.parseInt(pageNo)-1);
		}		
		
		page.setCurPage(list.getPage()+1);
		page.setTotalPage(list.getPageCount());
		page.setTotalRecord(list.getNrOfElements());
		page.setMaxRecords(list.getNrOfElements());
		page.setStartPosition(list.getFirstElementOnPage()+1);
		
		page.setList(list.getPageList());
		request.setAttribute("pageResult", page);
	}

	protected void writeMessage(String msg, HttpServletResponse response) throws Exception {
		String s = "{\"msg\":\""+msg+"\"}";
		writeAjaxResponse(response, s);
	}
	protected void writeSuccessMsg(HttpServletResponse response)throws Exception{
		writeMessage("success", response);
	}
	protected String makeSelect(Map<String,Object> m){
		StringBuffer sb = new StringBuffer("<select>");
		Set<String> set = m.keySet();
		for(String s : set){
			sb.append("<option value='"+s+"'>");
			sb.append(m.get(s));
			sb.append("</option>");
		}
		sb.append("</select>");
		return sb.toString();
	}
	protected String makeOptions(Map<String,Object> m,String selected){
		StringBuffer sb = new StringBuffer("<option value=''>--</option>");
		Set<String> set = m.keySet();
		for(String s : set){
			sb.append("<option value='"+s+"'");
			if(s.equals(selected)){
				sb.append(" selected='selected'");
			}
			sb.append(">");
			sb.append(m.get(s));
			sb.append("</option>");
		}
		return sb.toString();
	}
	protected String makeCheckbox(Map<String,Object> m,Map<String,String> selected,String name){
		StringBuffer sb = new StringBuffer();
		for(String s:m.keySet()){
			sb.append("<input type='checkbox' value='");
			sb.append(s);
			sb.append("' ");
			if(selected.get(s)!=null){
				sb.append("checked='checked'");
			}
			sb.append(" name='");
			sb.append(name);
			sb.append("'>");
			sb.append(m.get(s));
			sb.append("</input>");
		}
		return sb.toString();
	}
}
