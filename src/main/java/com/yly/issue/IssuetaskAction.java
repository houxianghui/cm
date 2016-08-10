/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.issue;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;

import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.StringUtil;
import com.yly.para.Applytypeinfo;
import com.yly.para.ApplytypeinfoBO;





public class IssuetaskAction extends IbatisBaseAction {
	private IssuetaskBO issuetaskBO;
	private ApplytypeinfoBO applytypeinfoBO;
	public ApplytypeinfoBO getApplytypeinfoBO() {
		return applytypeinfoBO;
	}

	public void setApplytypeinfoBO(ApplytypeinfoBO applytypeinfoBO) {
		this.applytypeinfoBO = applytypeinfoBO;
	}

	public IssuetaskBO getIssuetaskBO() {
		return issuetaskBO;
	}

	public void setIssuetaskBO(IssuetaskBO issuetaskBO) {
		this.issuetaskBO = issuetaskBO;
	}


	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("c".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("new");
			}
			else{
				return addApply(form,mapping,request,user);
			}
			
		}else if("d".equals(act)){
			return delete(mapping, form, request, user);  
		}else {		//query active projects
			return queryList(form,mapping,request,user);
		}
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssuetaskForm f = (IssuetaskForm)form;
		((IssuetaskBO)bo).validateNum(f);
		Issuetask vo = new Issuetask();
		Applytypeinfo apply = new Applytypeinfo();
		apply.setApplyTypeId(String.valueOf(f.getAppTypeId()));
		apply =(Applytypeinfo)applytypeinfoBO.queryForObject(apply);
		copyProperties(vo,f);
		if(!CheckUtil.isEmptry(f.getOrigSamId()))
			vo.setRemarks(f.getOrigSamId().trim());
		vo.setTaskNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("IssueTask")),16));
		vo.setW2Sign(Short.valueOf(apply.getIsV2()));
		vo.setAuthSign(Short.valueOf(apply.getIsIsamSign()==null?"0":apply.getIsIsamSign()));
		vo.setW2limit(Integer.parseInt(apply.getIsV2Sign()));
		vo.setIsHTCard(Integer.parseInt(apply.getIsHLCard()));
		vo.setIsPki(Integer.parseInt(apply.getIsPki()));
		vo.setZeroExauthFlag(Integer.parseInt(apply.getIsIsamTestAllO()==null?"0":apply.getIsIsamTestAllO()));
		((IssuetaskBO)bo).insert(vo);
		return forwardSuccessPage(request,mapping,"保存成功","Issueapp.do?act=u&appNo="+vo.getAppNo());
		
	}
	public ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception {

	    ((IssuetaskBO)bo).transDelete(((IssuetaskForm)form).getTaskNo());
	    return forwardSuccessPage(request,mapping,"删除成功","Issueapp.do?act=u&appNo="+((IssuetaskForm)form).getAppNo());
	   

	}

	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		String appNo = request.getParameter("appNo");		
		if (pageNo == null && requery == null && appNo == null) {			
			return mapping.findForward("list");
	    }
		setPageResult(request,  ((IssuetaskBO)bo).queryList(appNo));
		return mapping.findForward("list");
	}

}
