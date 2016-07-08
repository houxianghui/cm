/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.stor;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
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
import com.eis.base.PageObject;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.StringUtil;
import com.eis.util.ValidateUtil;
import com.ibm.icu.text.DecimalFormat;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappBO;
import com.yly.para.Applytypeinfo;
import com.yly.para.ApplytypeinfoBO;
import com.yly.para.ApplytypeinfoForm;



public class StoAppInfoAction extends IbatisBaseAction {
	public ApplytypeinfoBO applytypeinfoBO;
	public IssueappBO issueappBO;

	public IssueappBO getIssueappBO() {
		return issueappBO;
	}

	public void setIssueappBO(IssueappBO issueappBO) {
		this.issueappBO = issueappBO;
	}

	public ApplytypeinfoBO getApplytypeinfoBO() {
		return applytypeinfoBO;
	}

	public void setApplytypeinfoBO(ApplytypeinfoBO applytypeinfoBO) {
		this.applytypeinfoBO = applytypeinfoBO;
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
        }else if("u".equals(act)){		//query active projects
			return update(form,mapping,request,user);
		}else if("list".equals(act)){		//query active projects
			return queryPressAppList(form,mapping,request,user);
		}else if("down".equals(act)){		//query active projects
			return download(request,response);
		}else if("listresult".equals(act)){		//query active projects
			return queryListResult(form,mapping,request,user);
		}else if("pick".equals(act)){		//query active projects
			return pickStor(form,mapping,request,user);
		}else if("ql".equals(act)){		//query active projects
			return queryList(form,mapping,request,user);
		}else if("r".equals(act)){		//query active projects
			return view(form,mapping,request,user);
		}else if("makeUpList".equals(act)){		//query active projects
			return makeUpList(form,mapping,request,user);
		}


		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Stoappinfo vo = new Stoappinfo();
		StoAppInfoForm f = (StoAppInfoForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getTimeStr());
		vo.setCurrPeriodAmt(vo.getPurchaseAmt());
        //获得批次号 
		vo.setFormNo(vo.getManufacId().substring(0, 1)+StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("StoAppInfo")),15));
		((StoAppInfoBO)bo).insert(vo);		
		return forwardSuccessPage(request,mapping,"保存成功","StoApp.do?act=c");
		
	}

	
	public ActionForward initEdit(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{

		Stoappinfo vo = ((StoAppInfoBO)bo).queryForObject(request.getParameter("formNo"));
		copyProperties(form,vo);
		return mapping.findForward("edit");
	}
	
	public ActionForward update(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Stoappinfo vo = new Stoappinfo();
		copyProperties(vo,form);
		vo.setOperId(user.getUserID());		
		vo.setCurrDate(DateUtil.getDTStr());
		((StoAppInfoBO)bo).update(vo);
		return forwardSuccessPage(request,mapping,"更新成功","StoApp.do?act=c");
	}
	public ActionForward queryPressAppList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		String formNo = request.getParameter("formNo");
		if (pageNo == null && requery == null && formNo == null) {			
			return mapping.findForward("list");
	    }
		StoAppInfoForm f = (StoAppInfoForm)form;
		setPageResult(request, ((StoAppInfoBO)bo).getAppList(f));
		return mapping.findForward("list");
	}
	public ActionForward queryListResult(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		StoAppInfoForm f = (StoAppInfoForm)form;
		f.setPhiTypeId(request.getParameter("phiTypeId"));
		f.setCurrPeriodAmt(Long.parseLong(request.getParameter("currPeriodAmt")));
		f.setProdId(request.getParameter("prodId"));
		f.setTaskCtrlNo(request.getParameter("taskCtrlNo"));
		Applytypeinfo apply = new Applytypeinfo();
		apply.setApplyTypeId(request.getParameter("appTypeId"));
		apply=(Applytypeinfo)applytypeinfoBO.queryForObject(apply);
		f.setIsPki(apply.getIsPki()==null?"0":apply.getIsPki());
		f.setIsHTCard(apply.getIsHLCard());	
		List list= ((StoAppInfoBO)bo).getAppList(f);
		if(list==null ||list.size()==0)
			throw new MessageException("不存在可以操作的记录!");
		else 
			setPageResult(request,list);
		return mapping.findForward("listresult");
	}	
	public ActionForward makeUpList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		List list=null;
		list=((StoAppInfoBO)bo).getExList(f);
		if(list==null || list.size()==0)
			throw new MessageException("不存在满足补办数量的原料!");
		setPageResult(request, list);
		if(f.getAppNo()==null){
			Issueapp vo = insertIssueApp(user);
			f.setAppNo(vo.getAppNo());
		}
		return mapping.findForward("makeupList");
	}

	private Issueapp insertIssueApp(UserContext user) throws Exception {
		Issueapp vo = new Issueapp();
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getTimeStr());
		vo.setAppNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("IssueApp")),16));
		issueappBO.insert(vo);
		return vo;
	}	
	private ActionForward download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String formNo = request.getParameter("formNo");
		Stoappinfo p = new Stoappinfo();
		p.setFormNo(formNo);
		List presscardList = ((StoAppInfoBO)bo).queryForList(p);
		response.setContentType("application/octet-stream");
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			formNo = new String(formNo.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			formNo = URLEncoder.encode(formNo, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+formNo+".txt");
		OutputStream out = response.getOutputStream();
		for(int i=0;i<presscardList.size();i++){
			Stoappinfo tmp=(Stoappinfo)presscardList.get(i);
			byte[] b= tmp.getFormNo().getBytes();
			out.write(b);
			out.write("\n".getBytes());
		}
		out.close();
		return null;
	}
	
	public ActionForward pickStor(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		Stoappinfo vo = ((StoAppInfoBO)bo).queryForObject(f.getFormNo());	
		vo.setCurrPeriodAmt(vo.getCurrPeriodAmt()-f.getCurrPeriodAmt());
		
		((StoAppInfoBO)bo).update(vo);		
		
		
		
		return forwardSuccessPage(request,mapping,"保存成功","StoApp.do?act=c");
		
	}
	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		f.setCurrPeriodAmt(Long.parseLong(request.getParameter("taskAmt")));
		setPageResult(request, ((StoAppInfoBO)bo).getExList(f));
		return mapping.findForward("ql");
	}
	public ActionForward view(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Stoappinfo vo = ((StoAppInfoBO)bo).queryForObject(request.getParameter("formNo"));
		copyProperties(form,vo);
		return mapping.findForward("view");
	}
}
