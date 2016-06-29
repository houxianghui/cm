/*
 * @# GetNotWriteNoteStuffAction.java 2009-2-26 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;


public class GetNotWriteNoteStuffAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if(CheckUtil.isEmptry(act)){
			return getNotWriteList(mapping, form, request);
		}
		if(act.equals("nf")){
			return getNotFullList(mapping,form,request);
		}
		throw new MessageException("无法处理的类型");
	}

	private ActionForward getNotFullList(ActionMapping mapping, BaseForm form, HttpServletRequest request)throws Exception {
		String date = request.getParameter("date");
		if(CheckUtil.isEmptry(date)){
			date  = DateUtil.getDTStr();
		}
		GetNotWriteNoteStuffForm f = (GetNotWriteNoteStuffForm)form;
		((GetNotWriteNoteStuffForm)form).setDate(date);
		request.setAttribute("checkbox", SingleDicMap.getCheckBox("ids", SingleDic.DEPART,f.getIds()));
		GetNotWriteNoteStuffBO gbo = (GetNotWriteNoteStuffBO)bo;
		request.setAttribute("nf",gbo.getNotFull((GetNotWriteNoteStuffForm)form));
		
		return mapping.findForward("nf");
	}

	public ActionForward getNotWriteList(ActionMapping mapping, BaseForm form, HttpServletRequest request)
			throws Exception {
		String date = request.getParameter("date");
		if(CheckUtil.isEmptry(date)){
			date  = DateUtil.getDTStr();
		}
		GetNotWriteNoteStuffForm f = (GetNotWriteNoteStuffForm)form;
		((GetNotWriteNoteStuffForm)form).setDate(date);
		request.setAttribute("checkbox", SingleDicMap.getCheckBox("ids", SingleDic.DEPART,f.getIds()));
		if(date!=null){
			request.setAttribute("stuff",bo.queryForList(form));
		}else{
			request.setAttribute("stuff",null);
		}
		
		return mapping.findForward("list");
	}

}
