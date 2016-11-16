/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.stor;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;

import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;

import com.eis.exception.MessageException;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;

import com.eis.util.DateUtil;
import com.eis.util.StringUtil;

import com.yly.exstore.Stoproduct;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappBO;
import com.yly.ls.Lsinfo;
import com.yly.para.Applytypeinfo;
import com.yly.para.ApplytypeinfoBO;




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
	public StoAppInfoReport stoAppInfoReport;
	public StoAppInfoReport getStoAppInfoReport() {
		return stoAppInfoReport;
	}
	public void setStoAppInfoReport(StoAppInfoReport stoAppInfoReport) {
		this.stoAppInfoReport = stoAppInfoReport;
	}
	public PosExStoreReport posExStoreReport;
	public PosExStoreReport getPosExStoreReport() {
		return posExStoreReport;
	}
	public void setPosExStoreReport(PosExStoreReport posExStoreReport) {
		this.posExStoreReport = posExStoreReport;
	}
	public StockReport stockReport;
	public StockReport getStockReport() {
		return stockReport;
	}

	public void setStockReport(StockReport stockReport) {
		this.stockReport = stockReport;
	}
	public StockBalReport stockBalReport;

	public StockBalReport getStockBalReport() {
		return stockBalReport;
	}
	public void setStockBalReport(StockBalReport stockBalReport) {
		this.stockBalReport = stockBalReport;
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
			return querList(form,mapping,request,user);
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
		}else if("exCardCSN".equals(act)){		//query active projects
			return exCardCSN(form,mapping,request,user);
		}else if("exbackList".equals(act)){		//query active projects
			return exbackList(form,mapping,request,user);
		}else if("popList".equals(act)){		//query active projects
			return popList(form,mapping,request,user);
		}else if("popList".equals(act)){		//query active projects
			return popList(form,mapping,request,user);
		}else if("staticsDown".equals(act)){		//query active projects
			return staticsdown(request,response,form,user); 
		}else if("statics".equals(act)){		//query active projects
			return mapping.findForward("statics");
		}else if("posExstaticsDown".equals(act)){		//query active projects
			return posExstaticsDown(request,response,form,user);
		}else if("posExstatics".equals(act)){		//query active projects
			return mapping.findForward("posExstatics");
		}else if("back".equals(act)){		//query active projects
			return back(form,mapping,request,user);
		}else if("backlist".equals(act)){		//query active projects
			return backList(form,mapping,request,user);
		}else if("stockstatics".equals(act)){		//query active projects
			return mapping.findForward("stockReport");
		}else if("stockDown".equals(act)){		//query active projects
			return stockDown(request,response,form,user); 
		}else if("stockBalstatics".equals(act)){		//query active projects
			return mapping.findForward("stockBalReport");
		}else if("stockBalDown".equals(act)){		//query active projects
			return stockBalDown(request,response,form,user); 
		}


		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Stoappinfo vo = new Stoappinfo();
		StoAppInfoForm f = (StoAppInfoForm)form;
		Stoappinfo partvo =null;
		Issueapp app=null;
		if(f.getProdId().equals("4") && f.getRsvd()!=""){
			partvo = ((StoAppInfoBO)bo).queryForObject(f.getRsvd());
			partvo.setCurrPeriodAmt(partvo.getCurrPeriodAmt()-f.getPurchaseAmt());
			if(partvo.getCurrPeriodAmt()<0)
				throw new MessageException("配件数量不足以用于此批次入库申请!");
		}
		if(f.getOperationType()==13){
			app=issueappBO.queryForObject(f.getExFormNo());
			app.setFormState((short)4);//已更换
		}
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getTimeStr());
		vo.setCurrPeriodAmt(vo.getPurchaseAmt());
        //获得批次号 
		vo.setFormNo((ReDefSDicMap.getDicItemLogicID(RedefSDicCodes.MAUN_ID, vo.getManufacId()).trim())+StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("StoAppInfo")),14));
		((StoAppInfoBO)bo).tranMain(vo,partvo,app);		
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
	public ActionForward querList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
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
	public ActionForward backList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String requery = request.getParameter("requery");
		if (requery == null ) {			
			return mapping.findForward("backList");
	    }
		StoAppInfoForm f = (StoAppInfoForm)form;
		f.setOperationType_f((long)94);//入库冲回操作
		setPageResult(request, ((StoAppInfoBO)bo).getAppList(f));
		return mapping.findForward("backList");
	}
	public ActionForward queryListResult(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		StoAppInfoForm f = (StoAppInfoForm)form;
		f.setCurrPeriodAmt(Long.parseLong(request.getParameter("currPeriodAmt")));
		f.setProdId(request.getParameter("prodId"));
		if(f.getProdId().equals("4")){
			f.setPhiTypeId("");//模块速率不设置
		//	f.setOperationType_f((long)92);//发行小模块必须是冲回过的记录
		}else{
			f.setPhiTypeId(request.getParameter("phiTypeId"));
		}
		f.setTaskCtrlNo(request.getParameter("taskCtrlNo"));
		Applytypeinfo apply = new Applytypeinfo();
		apply.setApplyTypeId(request.getParameter("appTypeId"));
		apply=(Applytypeinfo)applytypeinfoBO.queryForObject(apply);
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
			throw new MessageException("不存在满足操作数量的原料!");
		setPageResult(request, list);
		return mapping.findForward("ql");
	}
	public ActionForward exbackList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String requery = request.getParameter("requery");
		if (requery == null ) {			
			return mapping.findForward("exbackList");
	    }
		StoAppInfoForm f = (StoAppInfoForm)form;
		f.setOperationType_f((long)92);//出库冲回操作
		List list=((StoAppInfoBO)bo).getAppList(f);
		setPageResult(request, list);
		return mapping.findForward("exbackList");
	}
	public ActionForward popList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		f.setProdId("3");//esam冲回
		List list=((StoAppInfoBO)bo).getAppList(f);
		if(list==null ||list.size()<1){
			throw new MessageException("没有可以使用的配件!");
		}
		setPageResult(request, list);
		return mapping.findForward("exbackPopList");
	}
	public ActionForward exCardCSN(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		Issueapp vo = new Issueapp();
		vo=issueappBO.queryForObject(f.getAppNo());
		Stoappinfo sto=new Stoappinfo();
		sto=((StoAppInfoBO)bo).queryForObject(f.getFormNo());
		if(vo.getTaskAmt()>sto.getCurrPeriodAmt()){
			throw new MessageException("当前批次可用数量不足!");
		}else{
			sto.setCurrPeriodAmt(sto.getCurrPeriodAmt()-vo.getTaskAmt());  //更新批次剩余可用数量
		}
		vo.setOperId(user.getUserID());		
		vo.setCurrDate(DateUtil.getTimeStr());
		Lsinfo lsvo = new Lsinfo();
		copyProperties(lsvo, vo);
		lsvo.setFormNo(sto.getFormNo());  //出库批次
		lsvo.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("Lsinfo")),20));
		lsvo.setProdId(sto.getProdId());
		Enumeration<String> e =request.getParameterNames();
		List<Stoproduct> prodlist = new ArrayList<Stoproduct>();
		List<Lsinfo> lslist = new ArrayList<Lsinfo>();	
		lslist.add(lsvo);
		Pattern iPattern = Pattern.compile("ex_\\d+$");
		int tot=0;
 		while(e.hasMoreElements()){
			String s = e.nextElement();
			Matcher m = iPattern.matcher(s);
			if(m.find()){
				
				Stoproduct t= new Stoproduct();
				t.setSamCSN(request.getParameter(s));
				t.setSamId("0");
				t.setBatchId(sto.getFormNo());
				t.setProdId(sto.getProdId());
				t.setUnitPrice(sto.getUnitPrice());
				t.setManufacId(sto.getManufacId());
				t.setOAappNo(vo.getOAappNo());
				t.setWkState((short)11);
				t.setIOState((short)2);
				t.setUnitId(vo.getUnitId());
				t.setReuseTime((short)1);
				t.setUnitPrice(sto.getUnitPrice());
				t.setPhiTypeId(sto.getPhiTypeId());
				t.setCardPhyStat((short)1);
				t.setIOStateChgDate(DateUtil.getTimeStr());
				t.setWkStateChgDate(DateUtil.getTimeStr());
				t.setBatchIdParts(sto.getRsvd());
				prodlist.add(t);
				
				Lsinfo ls= new Lsinfo();
				ls.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("Lsinfo")),20));
				copyProperties(ls, t);
				ls.setAppNo(f.getAppNo());  
				ls.setFormNo(t.getBatchId());
				ls.setOperationType(vo.getOperationType());
				ls.setOperId(user.getUserID());
				ls.setCurrDate(DateUtil.getTimeStr());
				lslist.add(ls);
				tot++;
			}
 		}
		if(tot>0 && tot!=f.getCurrPeriodAmt()){
			throw new MessageException("出库数量与出库卡号不一致!");
		}
		vo.setFormState((short)3);//任务完成
		((StoAppInfoBO)bo).tranUpdate(vo,sto,lslist,prodlist);
		String url=getUrl(vo);
		return forwardSuccessPage(request,mapping,"操作成功","Issueapp.do?act="+url);
		
		
	}
	private String getUrl(Issueapp vo){
		String url="";
		if(vo.getOperationType()==41 ||vo.getOperationType()==42){
			url="exchangeList";
		}else if(vo.getOperationType()==51 ||vo.getOperationType()==52){
			url="makeupList";
		}else url="exlist";
		return url;
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
		if(request.getParameter("taskAmt")!=null){
			f.setCurrPeriodAmt(Long.parseLong(request.getParameter("taskAmt")));
		}
		setPageResult(request, ((StoAppInfoBO)bo).getExList(f));
		return mapping.findForward("ql");
	}
	public ActionForward view(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Stoappinfo vo = ((StoAppInfoBO)bo).queryForObject(request.getParameter("formNo"));
		copyProperties(form,vo);
		return mapping.findForward("view");
	}
	private ActionForward stockDown(HttpServletRequest request,HttpServletResponse response,  BaseForm form,UserContext user) throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		Stoappinfo vo = new Stoappinfo();
		vo.setBeginDate_f(f.getBeginDate_f());
		vo.setEndDate_f(f.getEndDate_f());
		List<String> l=new ArrayList<String>();
		l.add("出入库账本-ISAM");
		l.add("出入库账本-PSAM");
		l.add("出入库账本-ESAM");
		l.add("出入库账本-小模块");
		stockReport.createExcelSheet(vo, false,l);
		response.setContentType("application/octet-stream");
		String filename = "出入库账本.xls";
		
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		stockReport.getEt().write(out);
		out.close();
		return null;
	}
	private ActionForward stockBalDown(HttpServletRequest request,HttpServletResponse response,  BaseForm form,UserContext user) throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		Stoappinfo vo = new Stoappinfo();
		vo.setBeginDate_f(f.getBeginDate_f());
		vo.setEndDate_f(f.getEndDate_f());
		stockBalReport.createExcel(vo, false);
		response.setContentType("application/octet-stream");
		String filename = stockBalReport.getEt().getSheetName()+".xls";
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		stockBalReport.getEt().write(out);
		out.close();
		return null;
	}
	private ActionForward staticsdown(HttpServletRequest request,HttpServletResponse response,  BaseForm form,UserContext user) throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		Stoappinfo vo = new Stoappinfo();
		vo.setBeginDate_f(f.getBeginDate_f());
		vo.setEndDate_f(f.getEndDate_f());
		stoAppInfoReport.createExcel(vo, false);
		response.setContentType("application/octet-stream");
		String filename = stoAppInfoReport.getEt().getSheetName()+".xls";
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		stoAppInfoReport.getEt().write(out);
		out.close();
		return null;
	}
	private ActionForward posExstaticsDown(HttpServletRequest request,HttpServletResponse response,  BaseForm form,UserContext user) throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		Stoappinfo vo = new Stoappinfo();
		vo.setBeginDate_f(f.getBeginDate_f());
		vo.setEndDate_f(f.getEndDate_f());
		posExStoreReport.createExcel(vo, false);
		response.setContentType("application/octet-stream");
		String filename = posExStoreReport.getEt().getSheetName()+".xls";
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		posExStoreReport.getEt().write(out);
		out.close();
		return null;
	}	
	public ActionForward back(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoAppInfoForm f = (StoAppInfoForm)form;
		Stoappinfo vo = new Stoappinfo();
		vo=((StoAppInfoBO)bo).queryForObject(f.getFormNo());
		if(vo.getCurrPeriodAmt()<f.getPurchaseAmt()){
			throw new MessageException("可用数量不足，无法冲回！");
		}else{
			vo.setCurrPeriodAmt(vo.getCurrPeriodAmt()-f.getPurchaseAmt());
		}
				
		Stoappinfo backnew = new Stoappinfo();
		Stoappinfo out = ((StoAppInfoBO)bo).queryForObject(f.getFormNo());	
	
		copyProperties(backnew, out);
		backnew.setOperId(user.getUserID());		
		backnew.setCurrDate(DateUtil.getTimeStr());
		backnew.setOperationType((short)94); //入库冲回
		backnew.setPurchaseAmt((long)f.getPurchaseAmt());
		backnew.setCurrPeriodAmt((long)0);
		backnew.setFormNo("CH"+StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("StoAppInfo")),14));
		
		Lsinfo lsnew= new Lsinfo();
		copyProperties(lsnew, backnew);		
		lsnew.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("Lsinfo")),20));
		lsnew.setAppNo(f.getFormNo());
		lsnew.setFormNo(backnew.getFormNo());
		
		((StoAppInfoBO)bo).tranBackTb(vo,backnew,lsnew);
		
		return forwardSuccessPage(request,mapping,"冲回成功","StoApp.do?act=list");
	}
}
