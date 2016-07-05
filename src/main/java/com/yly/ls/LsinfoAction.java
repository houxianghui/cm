 package com.yly.ls;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductBO;
import com.yly.exstore.StoproductForm;


public class LsinfoAction extends IbatisBaseAction {
	private StoproductBO stoproductBO;

	public StoproductBO getStoproductBO() {
		return stoproductBO;
	}

	public void setStoproductBO(StoproductBO stoproductBO) {
		this.stoproductBO = stoproductBO;
	}

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
		if("E".equals(act)){		//query active projects
			return exam(form,mapping,request,user);
		}

		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Lsinfo vo = new Lsinfo();
		LsinfoForm f = (LsinfoForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getDTStr());
		((LsinfoBO)bo).insert(vo);		
		return forwardSuccessPage(request,mapping,"保存成功","Applytypeinfo.do?act=list");
		
	}
	public ActionForward exam(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Lsinfo vo = new Lsinfo();
		LsinfoForm f = (LsinfoForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getDTStr());
		Stoproduct prod = new Stoproduct();
		StoproductForm sf= new StoproductForm();
		copyProperties(sf,vo);
		prod = stoproductBO.queryForObjByKey(sf);
		((LsinfoBO)bo).insert(vo); 
		return forwardSuccessPage(request,mapping,"保存成功","Applytypeinfo.do?act=list");
		
	}
	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		LsinfoForm f = (LsinfoForm)form;
		Lsinfo vo= new Lsinfo();
		copyProperties(vo, f);
		setPageResult(request, ((LsinfoBO)bo).queryForList(vo));
		return mapping.findForward("list");
	}



}
