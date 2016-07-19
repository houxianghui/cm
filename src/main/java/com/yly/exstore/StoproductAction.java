/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.exstore;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.StringUtil;
import com.eis.util.ValidateUtil;
import com.ibm.icu.text.DecimalFormat;
import com.yly.issue.Issueapp;
import com.yly.issue.Issuetaskctrl;
import com.yly.ls.Lsinfo;




public class StoproductAction extends IbatisBaseAction {


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
        }else if("list".equals(act)){		//query active projects
			return queryList(form,mapping,request,user);
		}else if("ql".equals(act)){		//query active projects
			return list(form,mapping,request,user);
		}else if("qlex".equals(act)){		//query active projects
			return qlEx(form,mapping,request,user);
		}else if("exStore".equals(act)){		//query active projects
			return exStore(form,mapping,request,user);
		}else if("disCard".equals(act)){		//query active projects
			return disCard(form,mapping,request,user);
		}else if("disCardwlist".equals(act)){		//query active projects
			return disCard_wlist(form,mapping,request,user);
		}else if("u".equals(act)){		//query active projects
			return update(form,mapping,request,user);
		}


		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Stoproduct vo = new Stoproduct();
		StoproductForm f = (StoproductForm)form;
		copyProperties(vo,f);
        //获得批次号 
		((StoproductBO)bo).insert(vo);		
		return forwardSuccessPage(request,mapping,"保存成功","Stoproduct.do?act=c");
		
	}    
	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{

		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		String formNo = request.getParameter("formNo");
		if (pageNo == null && requery == null && formNo == null) {			
			return mapping.findForward("list");
	    }
		StoproductForm f = (StoproductForm)form;
		setPageResult(request, ((StoproductBO)bo).queryForList(f));
		return mapping.findForward("list");
	}	
	public ActionForward list(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoproductForm f = (StoproductForm)form;
		if(request.getParameter("requery")==null){
			f.setOAappNo_f(f.getOAappNo());
			f.setOAappNo(((StoproductBO)bo).changeOAappNo(f.getOAappNo_f()));
		}
		List<Stoproduct> prodList=null;
		prodList= ((StoproductBO)bo).queryForList(f);
		if(prodList!=null && prodList.size()>0){   
			setPageResult(request, prodList);
		}else{
			throw new MessageException("没有可以操作的记录");
		}
	
		return mapping.findForward("ql");
	}	
	public ActionForward disCard_wlist(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{

		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (pageNo == null && requery == null ) {			
			return mapping.findForward("discard_wlist");
	    }
		StoproductForm f = (StoproductForm)form;
		setPageResult(request, ((StoproductBO)bo).queryForListAsc(f));
		return mapping.findForward("discard_wlist");
	}	   
	public ActionForward qlEx(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoproductForm f = (StoproductForm)form;
		List<Stoproduct> prodList= ((StoproductBO)bo).queryForList(f);
		List<Lsinfo> lsList= new ArrayList<Lsinfo>();
		List<Stoproduct> issueCard = new ArrayList<Stoproduct>();
		if(prodList!=null && prodList.size()>0){
			for(Stoproduct vo:prodList){
				StoproductForm sf= new StoproductForm();
				sf.setSamCSN(vo.getSamCSN());
				sf.setSamId(vo.getSamId());
				setExProdAndLsList(user, f, f.getOperationType(), issueCard, lsList, sf);
			}
		}else{
			throw new MessageException("没有可以出库的记录");
		}
		return setValueAndgetReturn(mapping, request, f, prodList.size(), issueCard,
				lsList);
	}	
	public ActionForward exStore(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoproductForm f = (StoproductForm)form;
		int opertype=f.getOperationType();
		String reqChecked[]=f.getCx();
		if(f.getTaskAmtLeft()<reqChecked.length){
			throw new MessageException("出库数量超过出库单剩余数量!");
		}
		List<Stoproduct> issueCard = new ArrayList<Stoproduct>();
		List<Lsinfo> lsList= new ArrayList<Lsinfo>();
		for(int i=0;i<reqChecked.length;i++){
			String issue[]=reqChecked[i].split(",");
			StoproductForm sf=new StoproductForm();
			sf.setSamCSN(issue[1]);
			sf.setSamId(issue[0]);
			setExProdAndLsList(user, f, opertype, issueCard, lsList, sf);
		}
		return setValueAndgetReturn(mapping, request, f, reqChecked.length, issueCard,
				lsList);
	}

	private ActionForward setValueAndgetReturn(ActionMapping mapping,
			HttpServletRequest request, StoproductForm f, int num,
			List<Stoproduct> issueCard, List<Lsinfo> lsList) throws Exception {
		f.setTaskAmtLeft(f.getTaskAmtLeft()-num);
		Issueapp app=new Issueapp();
		app.setAppNo(f.getAppNo());
		if(f.getTaskAmtLeft()==0){
			app.setFormState((short)3);//完成
			((StoproductBO)bo).transUpdate(app,issueCard,lsList);
			return forwardSuccessPage(request,mapping,"出库完成","Issueapp.do?act=exlist");
		}else {
			app.setFormState((short)0);//暂存
			((StoproductBO)bo).transUpdate(app,issueCard,lsList);
			setPageResult(request, ((StoproductBO)bo).queryForList(f));
			return mapping.findForward("ql");
		}
	}

	private void setExProdAndLsList(UserContext user, StoproductForm f,
			int opertype, List<Stoproduct> issueCard, List<Lsinfo> lsList,
			StoproductForm sf) throws Exception,
			MessageException {
		
		
		Stoproduct vo = ((StoproductBO)bo).queryForObjByKey(sf);
		Lsinfo lsvo = new Lsinfo();
		lsvo.setAppNo(f.getAppNo());
		lsvo.setCurrDate(DateUtil.getTimeStr());
		lsvo.setOperId(user.getUserID());
		lsvo.setOperationType((short)opertype);
		
		if (vo!= null) {
		    vo.setIOState((short)2);//出库
		    vo.setIOStateChgDate(DateUtil.getTimeStr());
		    vo.setReuseTime((short)(vo.getReuseTime()==null?1:vo.getReuseTime()+1));	 	
			lsvo.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("Lsinfo")),20));
			lsvo.setSamCSN(vo.getSamCSN());
			lsvo.setSamId(vo.getSamId());
		 }else{
				throw new MessageException(sf.getSamId()+"该产品异常,请联系选择其他产品!");
		 }
		issueCard.add(vo);
		lsList.add(lsvo);
	}		
	public ActionForward update(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Stoproduct vo = new Stoproduct();
		StoproductForm f = (StoproductForm)form;
		copyProperties(vo,f);
		((StoproductBO)bo).update(vo);
		return forwardSuccessPage(request,mapping,"更新成功","Repair.do?act=repairInit");
	}

	public ActionForward disCard(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoproductForm f = (StoproductForm)form;
		int opertype=f.getOperationType();
		String reqChecked[]=f.getCx();
		List<Stoproduct> issueCard = new ArrayList<Stoproduct>();
		List<Lsinfo> lsList= new ArrayList<Lsinfo>();
		for(int i=0;i<reqChecked.length;i++){
			String issue[]=reqChecked[i].split(",");
			StoproductForm sf=new StoproductForm();
			sf.setSamCSN(issue[1]);
			sf.setSamId(issue[0]);
			
			
			List l=((StoproductBO)bo).queryForListAsc(sf);	

			Stoproduct vo = new Stoproduct();

			Lsinfo lsvo = new Lsinfo();
			lsvo.setAppNo(f.getAppNo());
			lsvo.setCurrDate(DateUtil.getTimeStr());
			lsvo.setOperId(user.getUserID());
			lsvo.setOperationType((short)opertype);
			
			if (l != null) {
			    Iterator iter = l.iterator();
			    while (iter.hasNext()) {//只选择发行日期最早的进行作废
			    	
			    	vo = (Stoproduct)iter.next();			    	
			    	vo.setWkState((short)16);//报废
			    	vo.setWkStateChgDate(DateUtil.getTimeStr());
			    	
					lsvo.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("Lsinfo")),20));
					lsvo.setSamCSN(vo.getSamCSN());
					lsvo.setSamId(vo.getSamId());

			    	break;
			    }
			 }else{
					throw new MessageException(sf.getSamId()+"该产品异常,请联系系统管理员!");
			 }
			issueCard.add(vo);
			lsList.add(lsvo);
			
		}
		((StoproductBO)bo).transMoveInfo(issueCard,lsList);
		return forwardSuccessPage(request,mapping,"作废成功","Stoproduct.do?act=disCardwlist");

	}


}
