 package com.yly.conf;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;
import com.eis.util.StringUtil;



public class CallfuncconfAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("c".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("new");
			}else{
				return addApply(form,mapping,request,user);
			}
			
		}
		if("list".equals(act)){		//query active projects
			return queryList(form,mapping,request,user);
		}
	

		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Callfuncconf vo = new Callfuncconf();
		CallfuncconfForm f = (CallfuncconfForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getDTStr());
		vo.setCallerId(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("callfuncconf")),6));
		((CallfuncconfBO)bo).insert(vo);		
		return forwardSuccessPage(request,mapping,"保存成功","Callfuncconf.do?act=list");
		
	}
	
	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (pageNo == null && requery == null) {			
			return mapping.findForward("list");
	    }
		CallfuncconfForm f = (CallfuncconfForm)form;
		setPageResult(request, ((CallfuncconfBO)bo).queryForList(f));
		return mapping.findForward("list");
	}



}
