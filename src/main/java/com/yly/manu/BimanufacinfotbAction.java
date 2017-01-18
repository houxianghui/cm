/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.manu;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;



public class BimanufacinfotbAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("c".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("new");
			}else{
				return add(form,mapping,request,user);
			}
			
		}
		if("list".equals(act)){		//query active projects
			return queryList(form,mapping,request,user);
		}else 

		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	public ActionForward add(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Bimanufacinfotb vo = new Bimanufacinfotb();
		BimanufacinfotbForm f = (BimanufacinfotbForm)form;
		copyProperties(vo,f);
		vo.setCurrDate(DateUtil.getDTStr());
		vo.setOperId(user.getUserID());
		((BimanufacinfotbBO)bo).insert(vo);		
		return forwardSuccessPage(request,mapping,"保存成功","Manuinfo.do?act=list");
		
	}
	
	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		String Unitid = request.getParameter("Unitid");
		if (pageNo == null && requery == null && Unitid == null) {			
			return mapping.findForward("list");
	    }
		BimanufacinfotbForm f = (BimanufacinfotbForm)form;
		setPageResult(request, ((BimanufacinfotbBO)bo).queryList(f));
		return mapping.findForward("list");
	}

}
