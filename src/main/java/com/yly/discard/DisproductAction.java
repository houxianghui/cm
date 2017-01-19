/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.discard;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.nested.bean.NestedDefineTei;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.ValidateUtil;
import com.ibm.icu.text.DecimalFormat;


public class DisproductAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("list".equals(act)){		//query active projects
			return List(form,mapping,request,user);
		}
	

		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	

	
	
	public ActionForward List(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (pageNo == null && requery == null) {			
			return mapping.findForward("list");
	    }
		DisproductForm f = (DisproductForm)form;
		((DisproductBO)bo).querySamIdValidate(f);
		setPageResult(request, ((DisproductBO)bo).queryForList(f));
		return mapping.findForward("list");
	}



}
