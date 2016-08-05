/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.issue;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
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
import com.ibm.icu.text.DecimalFormat;
import com.yly.info.BiunitinfoBO;
import com.yly.info.Biunitinfotb;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoBO;
import com.yly.StreamGobbler.StreamGobbler;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductBO;
import com.yly.exstore.StoproductForm;
import com.yly.stor.StoAppInfoBO;
import com.yly.stor.StoAppInfoForm;
import com.yly.stor.Stoappinfo;



public class IssueappAction extends IbatisBaseAction {
	private BiunitinfoBO biunitinfoBO;
	private IssuetaskBO issuetaskBO;
	private StoproductBO stoproductBO;
	private StoAppInfoBO stoAppBO;
	private LsinfoBO lsinfoBO;

	public StoAppInfoBO getStoAppBO() {
		return stoAppBO;
	}

	public LsinfoBO getLsinfoBO() {
		return lsinfoBO;
	}

	public void setLsinfoBO(LsinfoBO lsinfoBO) {
		this.lsinfoBO = lsinfoBO;
	}

	public void setStoAppBO(StoAppInfoBO stoAppBO) {
		this.stoAppBO = stoAppBO;
	}

	public StoproductBO getStoproductBO() {
		return stoproductBO;
	}

	public void setStoproductBO(StoproductBO stoproductBO) {
		this.stoproductBO = stoproductBO;
	}

	public IssuetaskBO getIssuetaskBO() {
		return issuetaskBO;
	}

	public void setIssuetaskBO(IssuetaskBO issuetaskBO) {
		this.issuetaskBO = issuetaskBO;
	}

	public BiunitinfoBO getBiunitinfoBO() {
		return biunitinfoBO;
	}

	public void setBiunitinfoBO(BiunitinfoBO biunitbo) {
		this.biunitinfoBO = biunitbo;
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
			
		}else if("ex_new".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("ex_new");
			}
			else{
				return addExApply(form,mapping,request,user);
			}
			
		}else if("exchange_new".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("exchange_new");
			}
			else{
				return addExchange(form,mapping,request,user);
			}
			
		}else if("makeup_new".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("makeup_new");
			}
			else{
				return addMakeup(form,mapping,request,user);
			}
			
		}else if("storeuse_new".equals(act)){		//add apply
			if (null == request.getParameter("step")) { 
				return mapping.findForward("storeuse_new");
			}
			else{
				return addStoreuse(form,mapping,request,user);
			}
			
		}else if ("u".equals(act)) { 								//修改申请资料信息 
            String step = request.getParameter("step");
            if (null == step) { 									//初始化阶段，查询明细信息并跳转到修改页面 
                return initEdit(form,mapping, request); 
            } else 												//用户已提交修改后的数据，执行数据保存 
                return update(form,mapping, request, user);
        }else if("addtask".equals(act)){		//query active projects
			return addTask(form,mapping,request,user);
		}else if("popList".equals(act)){		//query active projects
			return popList(form,mapping,request);
		}else if("show".equals(act)){		//query active projects
			return show(form,mapping,request,user);
		}else if("wq".equals(act)){		//query active projects
			return w_queryList(form,mapping,request,user);
		}else if("single_assign".equals(act)){		//query active projects
			return single_assign(form,mapping,request,user);
		}else if("batch_assign".equals(act)){		//query active projects
			return batch_assign(form,mapping,request,user);
		}else if("exlist".equals(act)){		//query active projects
			return exList(form,mapping,request,user);
		}else if("partDown".equals(act)){		//原料出库
			return partDown(form,mapping,request,user);
		}else if("back".equals(act)){		//query active projects
			return back(form,mapping,request,user);
		}else if("exmaintain".equals(act)){		//query active projects
			return exMainTain(form,mapping,request,user);
		}else if("makeupList".equals(act)){		//query active projects
			return makeUpList(form,mapping,request,user);
		}else if("makeupMainTain".equals(act)){		//query active projects
			return makeupMainTain(form,mapping,request,user);
		}else if("exchangeList".equals(act)){		//query active projects
			return exchangeList(form,mapping,request,user);
		}else if("exchangeMainTain".equals(act)){		//query active projects
			return exchangeMainTain(form,mapping,request,user);
		}else if("storeuseList".equals(act)){		//query active projects
			return storeuseList(form,mapping,request,user);
		}else if("storeuseMainTain".equals(act)){		//query active projects
			return storeuseMainTain(form,mapping,request,user);
		}else if("down".equals(act)){		//query active projects
			down(form,mapping,request,response,user);
			return null;
		}else {		//query active projects
			return queryList(form,mapping,request,user);
		}
	}
	public ActionForward makeUpList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (CheckUtil.isEmptry(pageNo)  && requery == null ) {			
			return mapping.findForward("makeuplist");
	    }
		IssueappForm f = (IssueappForm)form;
		setPageResult(request, ((IssueappBO)bo).getMakeUpList(f));
		return mapping.findForward("makeuplist");
	}
	public ActionForward exchangeList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (CheckUtil.isEmptry(pageNo)  && requery == null ) {			
			return mapping.findForward("exchangeList");
	    }
		IssueappForm f = (IssueappForm)form;
		setPageResult(request, ((IssueappBO)bo).getExChangeList(f));
		return mapping.findForward("exchangeList");
	}
	public ActionForward storeuseList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (CheckUtil.isEmptry(pageNo)  && requery == null ) {			
			return mapping.findForward("storeuselist");
	    }
		IssueappForm f = (IssueappForm)form;
		setPageResult(request, ((IssueappBO)bo).getAppListByOperType(f));
		return mapping.findForward("storeuselist");
	}
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Issueapp vo = new Issueapp();
		IssueappForm f = (IssueappForm)form;
		if(f.getOperationType().equals("25")){
			throw new MessageException("请选择'修复发行'菜单操作!");
		}
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getTimeStr());
		vo.setAppNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("applyinfotb")),16));
		((IssueappBO)bo).insert(vo);
		return forwardSuccessPage(request,mapping,"保存成功","Issueapp.do?act=u&appNo="+vo.getAppNo());
		
	}
	public ActionForward addTask(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		//setPageResult(request, ((IssueappBO)bo).getAppList(f));
		return mapping.findForward("addTask");
	}
	public ActionForward initEdit(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		Issueapp vo = ((IssueappBO)bo).queryForObject(request.getParameter("appNo"));
		copyProperties(form,vo);
		setPageResult(request, issuetaskBO.queryList(vo.getAppNo()));
        request.setAttribute("pageResultIssuetask", request.getAttribute("pageResult"));
        return mapping.findForward("edit");
  	
	}
	public ActionForward update(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Issueapp vo = new Issueapp();
		copyProperties(vo,(IssueappForm)form);
		((IssueappBO)bo).validate((IssueappForm)form);
		vo.setOperId(user.getUserID());		
		vo.setCurrDate(DateUtil.getTimeStr());
		((IssueappBO)bo).update(vo);
		return forwardSuccessPage(request,mapping,"提交成功","Issueapp.do?act=list");
	}

	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (CheckUtil.isEmptry(pageNo)  && requery == null ) {			
			return mapping.findForward("list");
	    }
		IssueappForm f = (IssueappForm)form;
		setPageResult(request, ((IssueappBO)bo).getAppList(f));
		return mapping.findForward("list");
	}
	public ActionForward exList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (CheckUtil.isEmptry(pageNo)  && requery == null ) {			
			return mapping.findForward("exlist");
	    }
		IssueappForm f = (IssueappForm)form;
		setPageResult(request, ((IssueappBO)bo).getExList(f));
		return mapping.findForward("exlist");
	}


	public void down(BaseForm form,ActionMapping mapping,HttpServletRequest request,HttpServletResponse response,UserContext user)throws Exception{
 	    String url="E:\\work\\code\\design-eclipse\\projectmanage-1.0\\MFC\\IAP.exe";
		Process proc = Runtime.getRuntime().exec(url);  
		String res="";
		writeAjaxResponse(response, res);
	}
	//原料出库\补办
	public ActionForward partDown(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		StoAppInfoForm sf = new StoAppInfoForm();
		sf.setCurrPeriodAmt(f.getTaskAmt().longValue());
		sf.setProdId("3");
		setPageResult(request, stoAppBO.getExList(sf));
		return mapping.findForward("exdown");	
	}

				
	public ActionForward back(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f= (IssueappForm)form;
		
		
		Issueapp vo = new Issueapp();
		vo=((IssueappBO)bo).queryForObject(f.getAppNo());
		f.setOAappNo(f.getAppNo()); //关联出库单号
		
		
		Lsinfo ls= new Lsinfo();
		ls.setAppNo(f.getAppNo());
		ls.setOperationType(f.getOperationType().shortValue());
		ls=lsinfoBO.queryLastObject(ls);
		
		StoAppInfoForm sf=new StoAppInfoForm();
		sf.setOperationType((long)92);
		sf.setFormNo(ls.getFormNo());
		List<Stoappinfo> backlist=stoAppBO.getAppList(sf);
			
		if(backlist!=null && backlist.size()>0){
			long tot=0;
			for(Stoappinfo backapp:backlist){
				tot=tot+backapp.getPurchaseAmt();
			}
			if(vo.getTaskAmt()<(tot+f.getTaskAmt())){
				throw new MessageException("冲回数量超过原出库单总数");
			}
		}
		
		Stoappinfo backnew = new Stoappinfo();
		Stoappinfo out = stoAppBO.queryForObject(ls.getFormNo());	
		
		copyProperties(backnew, out);
		backnew.setOperId(user.getUserID());		
		backnew.setCurrDate(DateUtil.getTimeStr());
		backnew.setOperationType((short)92); //冲回
		backnew.setPurchaseAmt((long)f.getTaskAmt());
		backnew.setCurrPeriodAmt((long)f.getTaskAmt());
		backnew.setFormNo("CH"+StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("StoAppInfo")),14));
		
		Lsinfo lsnew= new Lsinfo();
		copyProperties(lsnew, backnew);		
		lsnew.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("Lsinfo")),20));
		lsnew.setAppNo(f.getAppNo());
		lsnew.setFormNo(backnew.getFormNo());
		
		stoAppBO.tranInsert(backnew,lsnew);
		
		return forwardSuccessPage(request,mapping,"提交成功","Issueapp.do?act=exlist");
	}
	public ActionForward popList(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		IssueappForm f = (IssueappForm)form;
		if(f.getOperationType()==13)//更换入库
			f.setOperationType(33);//坏卡出库
		setPageResult(request, ((IssueappBO)bo).getAppListByOperType(f));
		return mapping.findForward("popList");
	}
	
	private void formatIssuetask(Stoproduct prodvo,IssueappForm f){
		f.setProdId(prodvo.getProdId());
		f.setKeyType(prodvo.getKeyType());
		f.setPhiTypeId(prodvo.getPhiTypeId());
		f.setAppTypeId(Integer.parseInt(prodvo.getAppTypeId()));
		f.setBinFileVer(prodvo.getBinFileVer());
		f.setAuthSign(prodvo.getAuthSign());
		f.setW2Sign(prodvo.getW2Sign());
	}
	public ActionForward show(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		Stoproduct prodvo = new Stoproduct();
		prodvo.setSamId(f.getOrigSamId());
		prodvo = stoproductBO.queryObjectBySamId(prodvo.getSamId());
		if(prodvo==null){
			throw new MessageException("此SAM号找不到原发行记录");
		}
		Issueapp vo = ((IssueappBO)bo).queryForObject(f.getAppNo());
		copyProperties(form,vo);
		formatIssuetask(prodvo,f);
		setPageResult(request, issuetaskBO.queryList(f.getAppNo()));
        request.setAttribute("pageResultIssuetask", request.getAttribute("pageResult"));

		return mapping.findForward("show");	
	}
	public ActionForward w_queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (CheckUtil.isEmptry(pageNo)  && requery == null ) {			
			return mapping.findForward("w_queryList");
	    }
		IssueappForm f = (IssueappForm)form;
		setPageResult(request, ((IssueappBO)bo).getW_QueryList(f));
		return mapping.findForward("w_queryList");
	}
	public ActionForward single_assign(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		if(needPickBatch(f)){
			return new ActionRedirect("StoApp.do?act=listresult&prodId="+f.getProdId()+"&phiTypeId="+f.getPhiTypeId()+"&appTypeId="+f.getAppTypeId()+"&currPeriodAmt="+f.getIssueAmt()+"&taskCtrlNo="+f.getTaskCtrlNo()+""); 
		}else{
			return new ActionRedirect("Mwsissuetb.do?act=add&taskCtrlNo="+f.getTaskCtrlNo()+"&workSheetAmt="+f.getIssueAmt()+"&batchId="+String.valueOf(f.getOperationType())+"&pressCardScale=''&purchaseAmt=0");
		}
	}
	public ActionForward batch_assign(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		String reqChecked[]=f.getCx();
		String taskCtrlNo="";
		int tot=0;
		int issued=0;
		String prod_phy="";
		for(int i=0;i<reqChecked.length;i++){
			String task[]=reqChecked[i].split(",");
			if(prod_phy=="")
				prod_phy=task[3]+"_"+task[1]+"_"+task[5]+"_"+task[6];
			else if(!prod_phy.equals(task[3]+"_"+task[1]+"_"+task[5]+"_"+task[6]))
				throw new MessageException("多选记录的产品类型、产品速率、业务类型、应用类型必须一致");
			taskCtrlNo+=(task[0]+",");
			tot+=Integer.parseInt(task[2]);
			f.setProdId(task[3]);
			f.setPhiTypeId(task[1]);
			f.setOperationType(Integer.parseInt(task[5]));
			issued+=Integer.parseInt(task[4]);
			f.setAppTypeId(Integer.parseInt(task[6]));
		}
		int issueAmt=tot-issued;
		if(needPickBatch(f)){
			return new ActionRedirect("StoApp.do?act=listresult&prodId="+f.getProdId()+"&phiTypeId="+f.getPhiTypeId()+"&appTypeId="+f.getAppTypeId()+"&currPeriodAmt="+issueAmt+"&taskCtrlNo="+taskCtrlNo+""); 
		}else{
			return new ActionRedirect("Mwsissuetb.do?act=add&taskCtrlNo="+taskCtrlNo+"&workSheetAmt="+issueAmt+"&batchId="+String.valueOf(f.getOperationType())+"&pressCardScale=''&purchaseAmt=0");
		}
	}
	/*原料发行,同号同属性,同号不同属性,对于isam,esam,psam,小模块进行批次的选择*/
	private boolean needPickBatch(IssueappForm f){
		if(f.getOperationType()==21 || f.getOperationType()==24||f.getOperationType()==43 || f.getOperationType()==53){
    		if(!f.getProdId().equals("5"))
    			return true;
    		else return false;
		}else 
			return false;
	}
	public ActionForward addExApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Issueapp vo = new Issueapp();
		IssueappForm f = (IssueappForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getTimeStr());
		vo.setAppNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("applyinfotb")),16));
		String url=getExTypeUrl(f);
		((IssueappBO)bo).insert(vo);
		return new ActionRedirect(url+"&appNo="+vo.getAppNo()+"&OAappNo="+vo.getOAappNo()+"&taskAmt="+f.getTaskAmt()+"&taskAmtLeft="+f.getTaskAmt()+"&operationType="+f.getOperationType());

	}

	private String getExTypeUrl(IssueappForm f)throws Exception{
		int operType=f.getOperationType();
		String url="";
		if(operType==31 ||operType==33){
			url="Stoproduct.do?act=ql";
		}else if(operType==32 ||operType==34){
			url="StoApp.do?act=ql";
		}else if(operType==35){
			url="Storeuse.do?act=exlist&unitId="+f.getUnitId();
		}else{
			throw new MessageException("业务类型有误!");
		}
		return url;
	}
	public ActionForward addExchange(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		Issueapp vo = new Issueapp();
		IssueappForm f = (IssueappForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getTimeStr());
		vo.setAppNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("applyinfotb")),16));
		String url=getExChangeUrl(f);
		((IssueappBO)bo).insert(vo);
		return forwardSuccessPage(request,mapping,"保存成功",url+"&appNo="+vo.getAppNo()+"&OAappNo="+vo.getOAappNo()+"&taskAmt="+f.getTaskAmt()+"&unitId="+f.getUnitId()+"&operationType="+f.getOperationType());

	}
	private String getExChangeUrl(IssueappForm f)throws Exception{
		int operType=f.getOperationType();
		String url="";
		url="Stoproduct.do?act=exchange";
//		if(operType==43){
//			url="Stoproduct.do?act=ql";
//		}else if(operType==41 ||operType==42){
//			url="StoApp.do?act=ql";
//		}else{
//			throw new MessageException("业务类型有误!");
//		}
		return url;
	}	
	public ActionForward addMakeup(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{		
		Issueapp vo = new Issueapp();
		IssueappForm f = (IssueappForm)form;
		copyProperties(vo,f);
		int opertype=f.getOperationType();
		if(opertype!=53){
			return new ActionRedirect("StoApp.do?act=makeUpList&appNo="+vo.getAppNo()+"&currPeriodAmt="+f.getTaskAmt()+"&operationType="+f.getOperationType());
		}else{
			vo.setOperId(user.getUserID());
			vo.setCurrDate(DateUtil.getTimeStr());
			vo.setAppNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("applyinfotb")),16));
			((IssueappBO)bo).insert(vo);
			return forwardSuccessPage(request,mapping,"保存成功","Issueapp.do?act=u&appNo="+vo.getAppNo()+"&OAappNo="+vo.getOAappNo()+"&taskAmt="+f.getTaskAmt()+"&operationType="+f.getOperationType());
		}
	}
	public ActionForward addStoreuse(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{		
		Issueapp vo = new Issueapp();
		IssueappForm f = (IssueappForm)form;
		copyProperties(vo,f);
		vo.setOperId(user.getUserID());
		vo.setCurrDate(DateUtil.getTimeStr());
		vo.setAppNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("applyinfotb")),16));
		((IssueappBO)bo).insert(vo);
		return forwardSuccessPage(request,mapping,"保存成功","Storeuse.do?act=back_init&appNo="+vo.getAppNo()+"&taskAmt="+vo.getTaskAmt());
	}	

	public ActionForward exMainTain(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		String url=getExTypeUrl(f);
		Issueapp vo=((IssueappBO)bo).queryForObject(f.getAppNo());
		Lsinfo ls = new Lsinfo();
		ls.setAppNo(f.getAppNo());
		ls.setOperationType(vo.getOperationType());
		int finishAmt=lsinfoBO.countByExample(ls);
		int lastAmt=vo.getTaskAmt().shortValue()-finishAmt;
		return new ActionRedirect(url+"&appNo="+vo.getAppNo()+"&OAappNo="+vo.getOAappNo()+"&taskAmt="+vo.getTaskAmt()+"&taskAmtLeft="+lastAmt+"&operationType="+vo.getOperationType());

	}
	public ActionForward makeupMainTain(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		Issueapp vo=((IssueappBO)bo).queryForObject(f.getAppNo());
		if(f.getOperationType()!=53){
			return new ActionRedirect("StoApp.do?act=makeUpList&appNo="+vo.getAppNo()+"&OAappNo="+vo.getOAappNo()+"&currPeriodAmt="+vo.getTaskAmt()+"&operationType="+f.getOperationType());
		}else{
			Lsinfo ls = new Lsinfo();
			ls.setAppNo(f.getAppNo());
			ls.setOperationType(vo.getOperationType());
			int finishAmt=lsinfoBO.countByExample(ls);
			int lastAmt=vo.getTaskAmt().shortValue()-finishAmt;
			copyProperties(f, vo);
			f.setTaskAmt(lastAmt);
			return initEdit(form,mapping, request); 
		}

	}
	public ActionForward exchangeMainTain(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		Issueapp vo=((IssueappBO)bo).queryForObject(f.getAppNo());
		return new ActionRedirect(getExChangeUrl(f)+"&appNo="+vo.getAppNo()+"&OAappNo="+vo.getOAappNo()+"&taskAmt="+vo.getTaskAmt()+"&unitId="+vo.getUnitId()+"&operationType="+vo.getOperationType());


	}
	public ActionForward storeuseMainTain(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		Issueapp vo=((IssueappBO)bo).queryForObject(f.getAppNo());
		Lsinfo ls = new Lsinfo();
		ls.setAppNo(f.getAppNo());
		setPageResult(request,lsinfoBO.queryForList(ls));
		return new ActionRedirect("Storeuse.do?act=back_init&appNo="+vo.getAppNo());
	}
}
