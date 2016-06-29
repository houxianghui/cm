/*********************************************************
 * File:BaseAction.java
 *
 * ����Action��ĸ��࣬ͳһʵ�ֻỰ״̬��֤
 * @version 1.0
 *
 * Date     2005-8-2
 * @author   ����
 *
 * Copyright (C) 2005 Huateng
 * all rights reserved.
 *
 ********************************************************/

package com.eis.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.struts.ActionSupport;

import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.SysLog;
import com.eis.factory.*;
import com.eis.config.SysConfig;
import com.eis.exception.*;

/**
 * ˵����Actionʵ����չ��
 *
 */

public abstract class BaseAction extends ActionSupport {
	/**
	 * ���캯��
	 */
	public BaseAction() {
		super();

	}

	/**
	 * Action���
	 * ʵ��session��Ч���ж�
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {



		UserContext userContext =
			(UserContext) request.getSession().getAttribute("user");

		if (null == userContext) {

			return mapping.findForward("relogin");

		} else { //�����û�������

			try {

				return process(mapping, (BaseForm)form, request, response, userContext);

			}catch(OpenWinException e){
				request.setAttribute("message",e.getMessage());
				return mapping.findForward("error_msg");
			}catch (MessageException e) {

				return forwardError(request,mapping,e.getMessage());

			}catch (BaseException e) {
		

				//���ݴ������ѯ������ʾ��Ϣ
				request.setAttribute("error",e);

				//ת�򵽴�����ʾҳ��

				return mapping.findForward("base_error");

			} catch (Exception e) {
				e.printStackTrace();
				SysLog.error(e.toString());
				e.printStackTrace();
				Throwable t = e.getCause();
				Throwable c = t;
				if(t == null){
					request.setAttribute("message",e.getMessage());
				}else{
					while(t != null){
						c = t;
						t = t.getCause();
					}
					request.setAttribute("message",c.getMessage());
				}
				return mapping.findForward("default_error");

			} catch (Throwable t) {
				t.printStackTrace();
				t.printStackTrace();

				return mapping.findForward("default_error");
			}

		}

	}



	/**
	 * ת��
	 * @param ActionMapping
	 * @param pageName
	 * @return ActionForward
	 *
	 */
	public ActionForward forward(ActionMapping mapping, String pageName) {
		return (mapping.findForward(pageName));
	}

	/**
	 * �ض���
	 * @param mapping
	 * @param pageName
	 * @return ActionForward
	 *
	 */
	public ActionForward forward(String pageName) {
		return (new ActionForward(pageName));
	}

	/**
	 * �ض���
	 * @param mapping
	 * @param pageName
	 * @return ActionForward
	 *
	 */
	public ActionForward redirect(ActionMapping mapping, String pageName) {
		return new ActionRedirect(mapping.findForward(pageName));
	}

	/**
	 * �ض���
	 * @param mapping
	 * @param path - ��Դ���·��
	 * @return ActionForward
	 *
	 */
	public ActionForward redirect(String path) {
		return new ActionRedirect(path);
	}



	/**
	 * ת�򵽳ɹ���Ϣ��ʾҳ��
	 * @param request
	 * @param mapping
	 * @param msg
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardSuccessPage(
		HttpServletRequest request,
		ActionMapping mapping,
		String msg) {

		request.setAttribute("message", msg);

		return mapping.findForward("default_success");
	}

	/**
	 * ת�򵽳ɹ���Ϣ��ʾ,��ָ������ҳ��
	 * @param request
	 * @param mapping
	 * @param msg
	 * @param backUrl
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardSuccessPage(
		HttpServletRequest request,
		ActionMapping mapping,
		String msg,
		String backURL) {

		request.setAttribute("message", msg);

		request.setAttribute("backurl", backURL);

		return mapping.findForward("default_success");
	}
	public ActionForward popClosePage(
			HttpServletRequest request,
			ActionMapping mapping,
			String msg,
			String backURL) {

			request.setAttribute("message", msg);

			request.setAttribute("backurl", backURL);

			return mapping.findForward("popClose");
		}
	public ActionForward popConfirmClosePage(
			HttpServletRequest request,
			ActionMapping mapping,
			String msg,
			String backURL) {

			request.setAttribute("message", msg);

			request.setAttribute("backurl", backURL);

			return mapping.findForward("popConfirmClose");
		}	
	/**
	 * ת�򵽴�����Ϣ��ʾҳ��
	 * @param request
	 * @param mapping
	 * @param message
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardError(
		HttpServletRequest request,
		ActionMapping mapping,
		String message) {

		request.setAttribute("message", message);

		return (mapping.findForward("default_error"));
	}

	/**
	 * ת�򵽴�����Ϣ��ʾҳ��,��ָ������ҳ��
	 * @param request
	 * @param mapping
	 * @param msg
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardError(
		HttpServletRequest request,
		ActionMapping mapping,
		String msg,
		String backURL) {

		request.setAttribute("message", msg);
		request.setAttribute("backurl", backURL);
		return (mapping.findForward("default_error"));
	}


	/**
	 * ת�򵽴�����Ϣ��ʾҳ��,��ʾ����Ϣ��رմ���
	 * @param request
	 * @param mapping
	 * @param message
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardErrorClose(
		HttpServletRequest request,
		ActionMapping mapping,
		String message) {

		request.setAttribute("message", message);

		return (mapping.findForward("error_close"));
	}


	/**
	 * ����bean ID���ʵ������
	 * @param name - bean ID
	 * @return bean��ʵ������
	 *
	 */
	public Object getBean(String name) throws Exception {		
		if( this.getWebApplicationContext() ==null){
			return BeanFactory.getBean(name);
		}
		return this.getWebApplicationContext().getBean(name);
	}

	/**
	 * ����������֮��������ݴ���
	 * @param dest - Ŀ�����
	 * @param origin - ԭ����
	 *
	 */
	public void copyProperties(Object dest,Object origin) throws Exception {
		new BeanUtilsBean().copyProperties(dest,origin);
	}


	/**
	 * ��HashMap�����еļ�ֵ���Ƶ�һ��������
	 *
	 * @param map - ���Դ���Ե�HashMap����
	 * @param dest - Ŀ�����
	 *
	 */
	public void copyProperties(HashMap map,Object dest) throws Exception {
		new BeanUtilsBean().populate(dest,map);
	}
	public void down(HttpServletRequest request,HttpServletResponse response,String chName,String path)throws Exception{
		String fileName = null;
		if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox") != -1){
			fileName = new String(chName.getBytes("utf-8"),"iso8859-1");
		}else{
			fileName = URLEncoder.encode(chName, "UTF-8");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		OutputStream os = null;
		FileInputStream fis = null;
		try{
			os = response.getOutputStream();
			fis = new FileInputStream(path+File.separator+chName);
			byte[] b = new byte[1000];
			int i = 0;
			while((i=fis.read(b))!=-1){
				os.write(b,0,i);
			}
			
		}finally{
			if(os != null){
				os.flush();
				os.close();
			}
			if(fis != null){
				fis.close();
			}
		}
	}
	public String getClasspath(){
		return getServletContext().getRealPath("/");
	}
	public String getResourcePath(){
		return getClasspath()+"/resource";
	}
	//-----------------���·�����Ҫ����----------------------

	/**
	 * ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return ActionForward
	 * @throws Exception
	 *
	 */
	public abstract ActionForward process(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception;
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
}
