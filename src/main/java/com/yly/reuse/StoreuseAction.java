/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.yly.reuse;



import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.StringUtil;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductBO;
import com.yly.exstore.StoproductForm;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappBO;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoBO;



public class StoreuseAction extends IbatisBaseAction {
	private LsinfoBO lsinfoBO;
	private StoproductBO stoproductBO;
	private IssueappBO issueappBO;
	public IssueappBO getIssueappBO() {
		return issueappBO;
	}




	public void setIssueappBO(IssueappBO issueappBO) {
		this.issueappBO = issueappBO;
	}




	public StoproductBO getStoproductBO() {
		return stoproductBO;
	}




	public void setStoproductBO(StoproductBO stoproductBO) {
		this.stoproductBO = stoproductBO;
	}




	public LsinfoBO getLsinfoBO() {
		return lsinfoBO;
	}




	public void setLsinfoBO(LsinfoBO lsinfoBO) {
		this.lsinfoBO = lsinfoBO;
	}




	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("cardlist".equals(act)){		//query active projects
			return CardList(form,mapping,request,user);
		}else if("exlist".equals(act)){		//query active projects
			return exList(form,mapping,request,user);
		}else if("back_init".equals(act)){		//query active projects
			return Back_init(form,mapping,request,user);
		}else if("back".equals(act)){		//query active projects
			return Back(form,mapping,request,user);
		}else if("r".equals(act)){		//query active projects
			return retrive(form,mapping,request,user);
		}else if("show".equals(act)){		//query active projects
			return show(form,mapping,request,user);
		}else if("reEx".equals(act)){		//query active projects
			return reEx(form,mapping,request,user);
		}else if("cardDown".equals(act)){		//query active projects
			return cardDown(form,request,response);
		}

		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	

	
	
	public ActionForward CardList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (pageNo == null && requery == null) {			
			return mapping.findForward("cardlist");
	    }
		StoreuseForm f = (StoreuseForm)form;
		setPageResult(request, ((StoreuseBO)bo).queryForList(f));
		return mapping.findForward("cardlist");
	}
	public ActionForward exList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoreuseForm f = (StoreuseForm)form;
		List<Storeuse> prodList=null;
		prodList= ((StoreuseBO)bo).queryForList(f);
		if(prodList!=null && prodList.size()>0){   
			setPageResult(request, prodList);
		}else{
			throw new MessageException("没有可以操作的记录");
		}
		
		return mapping.findForward("exlist");
	}
	public ActionForward Back_init(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoreuseForm sf = (StoreuseForm)form;
		Lsinfo vo=new Lsinfo();
		vo.setAppNo(sf.getAppNo());
		setPageResult(request,lsinfoBO.queryForList(vo));
		return mapping.findForward("back");
	}
	public ActionForward Back(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoreuseForm sf = (StoreuseForm)form;
		StoproductForm stf = new StoproductForm();
		if(CheckUtil.isEmptry(sf.getSamId()) ||sf.getSamId().equals("0")){
			sf.setSamId(stoproductBO.getMaxBadReturnCard());
		}
		if(CheckUtil.isEmptry(sf.getSamCSN()) ||sf.getSamCSN().equals("0")){
			sf.setSamCSN("0");
		}
		stf.setSamId(sf.getSamId());
		stf.setSamCSN(sf.getSamCSN());
		Lsinfo lsinfo =  new Lsinfo();
		copyProperties(lsinfo, sf);
		lsinfo.setCurrDate(DateUtil.getTimeStr());
		lsinfo.setOperId(user.getUserID());
		lsinfo.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("LsInfo")),20));
		lsinfo.setDetectSign(sf.getDetectSign());
		
		Stoproduct sto = stoproductBO.queryForObjByKey(stf);
		Storeuse storeuse = new Storeuse();

		if(sto==null){
			sto=new Stoproduct();
			copyProperties(sto, sf);
			sto.setWkStateChgDate(DateUtil.getTimeStr());
			sto.setIOState((short)3);
			sto.setIOStateChgDate(DateUtil.getTimeStr());
			sto.setDetectTime(DateUtil.getTimeStr());
			sto.setDetectSign(sf.getDetectSign());
			sto.setCardPhyStat(sf.getCardPhyStat());
		}
		if(sf.getDetectSign()==2 ||sf.getCardPhyStat()==11){
			sto.setWkStateChgDate(DateUtil.getTimeStr());
			sto.setIOState((short)3);
			sto.setIOStateChgDate(DateUtil.getTimeStr());
			sto.setDetectTime(DateUtil.getTimeStr());
			sto.setDetectSign(sf.getDetectSign());
			sto.setCardPhyStat(sf.getCardPhyStat());
			storeuse=null;
		}else{
			copyProperties(storeuse, sto);
			storeuse.setWkStateChgDate(DateUtil.getTimeStr());
			storeuse.setIOState((short)3);
			storeuse.setIOStateChgDate(DateUtil.getTimeStr());
			storeuse.setDetectTime(DateUtil.getTimeStr());
			storeuse.setDetectSign(sf.getDetectSign());
			storeuse.setCardPhyStat(sf.getCardPhyStat());	
		}

		Lsinfo vo =  new Lsinfo();
		vo.setAppNo(sf.getAppNo());
		List<Lsinfo> l=lsinfoBO.queryForList(vo);
		if(l!=null &&l.size()>0){
			setPageResult(request,l);
			if(l.size()==sf.getTaskAmt()){
				Issueapp app=issueappBO.queryForObject(sf.getAppNo());
				app.setFormState((short)3);
				((StoreuseBO)bo).transUpdateTB(app,sto,storeuse,lsinfo);
				return forwardSuccessPage(request,mapping,"退回成功","Issueapp.do?act=storeuse_new");
			}
		}
		((StoreuseBO)bo).transUpdateTB(null,sto,storeuse,lsinfo);
		return forwardSuccessPage(request,mapping,"退回成功","Storeuse.do?act=back_init&appNo="+sf.getAppNo());
	}
	public ActionForward retrive(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoreuseForm sf = (StoreuseForm)form;
		Storeuse vo = getReuseObjByForm(sf);
		copyProperties(form, vo);
		return mapping.findForward("cardview");
	}
	public ActionForward show(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoreuseForm f = (StoreuseForm)form;
		StoreuseForm copyf=new StoreuseForm();
		copyProperties(copyf, f);
		Stoproduct prodvo =stoproductBO.queryObjectBySamId(f.getSamId());
		if(prodvo==null){
			Storeuse storeuse = (Storeuse)((StoreuseBO)bo).queryObjectBySamId(f.getSamId());
			if(storeuse==null){
				throw new MessageException("此SAM号找不到原记录");
			}else{
				throw new MessageException("此SAM号已经回库,印刷卡号为"+storeuse.getSamCSN());
			}
		}
		short detectFlag=f.getDetectSign();
 		copyProperties(f, prodvo);
 		f.setTaskAmt(copyf.getTaskAmt());
 		f.setAppNo(copyf.getAppNo());
 		f.setDetectSign(copyf.getDetectSign());
		Lsinfo vo =  new Lsinfo();
		vo.setAppNo(copyf.getAppNo());
		List<Lsinfo> l=lsinfoBO.queryForList(vo);
		setPageResult(request,l);
		return mapping.findForward("show");	
	}
	
	
	public ActionForward reEx(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoreuseForm f = (StoreuseForm)form;
		int opertype=f.getOperationType();
		String reqChecked[]=f.getCx();
		List<Storeuse> storeuseCard = new ArrayList<Storeuse>();
		List<Lsinfo> lsList= new ArrayList<Lsinfo>();
		for(int i=0;i<reqChecked.length;i++){
			String issue[]=reqChecked[i].split(",");
			StoreuseForm sf=new StoreuseForm();
			sf.setSamCSN(issue[1]);
			sf.setSamId(issue[0]);
			setExProdAndLsList(user, f, opertype, storeuseCard, lsList, sf);
		}
		return setValueAndgetReturn(mapping, request, f, reqChecked.length, storeuseCard,
				lsList);
	}
	private void setExProdAndLsList(UserContext user, StoreuseForm f,
			int opertype, List<Storeuse> issueCard, List<Lsinfo> lsList,
			StoreuseForm sf) throws Exception,
			MessageException {
		Storeuse vo = getReuseObjByForm(sf);
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
			lsvo.setProdId(vo.getProdId());
		 }else{
				throw new MessageException(sf.getSamId()+"该产品异常,请联系选择其他产品!");
		 }
		issueCard.add(vo);
		lsList.add(lsvo);
	}




	public Storeuse getReuseObjByForm(StoreuseForm sf) throws Exception {
		Storeuse vo = new Storeuse();
		vo.setSamCSN(sf.getSamCSN());
		vo.setSamId(sf.getSamId());
		vo = (Storeuse)((StoreuseBO)bo).queryForObject(vo);
		return vo;
	}		

	private ActionForward setValueAndgetReturn(ActionMapping mapping,
			HttpServletRequest request, StoreuseForm f, int num,
			List<Storeuse> issueCard, List<Lsinfo> lsList) throws Exception {
		f.setTaskAmtLeft(f.getTaskAmtLeft()-num);
		Issueapp app=new Issueapp();
		app.setAppNo(f.getAppNo());
		if(f.getTaskAmtLeft()==0){
			app.setFormState((short)3);//完成
			((StoreuseBO)bo).transReExTb(app,issueCard,lsList);
			return forwardSuccessPage(request,mapping,"出库完成","Issueapp.do?act=exlist");
		}else {
			app.setFormState((short)0);//暂存
			((StoreuseBO)bo).transReExTb(app,issueCard,lsList);
			setPageResult(request, ((StoreuseBO)bo).queryForList(f));
			return mapping.findForward("exlist");
		}
	}
	private ActionForward cardDown(BaseForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		StoreuseForm sf = (StoreuseForm)form;
		((StoreuseBO)bo).querySamIdValidate(sf);
		String scale="";
		List cardList = ((StoreuseBO)bo).queryForList(sf);
		response.setContentType("application/octet-stream");
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			scale = new String((sf.getSamId_min()+"_"+sf.getSamId_max()).getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			scale = URLEncoder.encode((sf.getSamId_min()+"_"+sf.getSamId_max()), "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+scale+".txt");
		OutputStream out = response.getOutputStream();
		for(int i=0;i<cardList.size();i++){
			Storeuse tmp=(Storeuse)cardList.get(i);
			byte[] b= (tmp.getSamId()+","+tmp.getSamCSN()).getBytes();
			out.write(b);
			out.write("\n".getBytes());
		}
		out.close();
		return null;
	}
}
