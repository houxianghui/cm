package com.yly.exstore;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappBO;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoBO;
import com.yly.reuse.Storeuse;
import com.yly.reuse.StoreuseBO;
import com.yly.reuse.StoreuseForm;
 





public class StoproductAction extends IbatisBaseAction {
	private LsinfoBO lsinfoBO;
	private StoreuseBO storeuseBO;
	private IssueappBO issueappBO;
	public IssueappBO getIssueappBO() {
		return issueappBO;
	}
	public void setIssueappBO(IssueappBO issueappBO) {
		this.issueappBO = issueappBO;
	}


	public StoreuseBO getStoreuseBO() {
		return storeuseBO;
	}

	public void setStoreuseBO(StoreuseBO storeuseBO) {
		this.storeuseBO = storeuseBO;
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
		}else if("exStore".equals(act)){		//出库
			return exStore(form,mapping,request,user);
		}else if("disCard".equals(act)){		//query active projects
			return disCard(form,mapping,request,user);
		}else if("disCardwlist".equals(act)){		//query active projects
			return disCard_wlist(form,mapping,request,user);
		}else if("u".equals(act)){		//query active projects
			return update(form,mapping,request,user);
		}else if("r".equals(act)){		//query active projects
			return retrive(form,mapping,request,user);
		}else if("cardlist".equals(act)){		//query active projects
			return CardList(form,mapping,request,user);
		}else if("cardDown".equals(act)){		//query active projects
			return cardDown(form,request,response);
		}else if("exchange".equals(act)){		//query active projects
			return exChange(form,mapping,request,user);
		}else if("back".equals(act)){		//query active projects
			return back(form,mapping,request,user);
		}else if("show".equals(act)){		//query active projects
			return show(form,mapping,request,user);
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
		String url=request.getParameter("url");
		return forwardSuccessPage(request,mapping,"更新成功",url);
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

	public ActionForward CardList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (pageNo == null && requery == null) {			
			return mapping.findForward("cardlist");
	    }
		StoproductForm f = (StoproductForm)form;
		setPageResult(request, ((StoproductBO)bo).queryForList(f));
		return mapping.findForward("cardlist");
	}
	public ActionForward retrive(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoproductForm sf = (StoproductForm)form;
		Stoproduct vo = new Stoproduct();
		vo.setSamCSN(sf.getSamCSN());
		vo.setSamId(sf.getSamId());
		vo = (Stoproduct)((StoproductBO)bo).queryForObject(vo);
		copyProperties(form, vo);
		return mapping.findForward("cardview");
	}
	private ActionForward cardDown(BaseForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		StoproductForm sf = (StoproductForm)form;
		((StoproductBO)bo).querySamIdValidate(sf);
		String scale="";
		List cardList = ((StoproductBO)bo).queryForListAsc(sf);
		response.setContentType("application/octet-stream");
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			scale = new String((sf.getSamId_min()+"_"+sf.getSamId_max()).getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			scale = URLEncoder.encode((sf.getSamId_min()+"_"+sf.getSamId_max()), "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+scale+".txt");
		OutputStream out = response.getOutputStream();
		for(int i=0;i<cardList.size();i++){
			Stoproduct tmp=(Stoproduct)cardList.get(i);
			byte[] b= (tmp.getSamId()+","+tmp.getSamCSN()).getBytes();
			out.write(b);
			out.write("\n".getBytes());
		}
		out.close();
		return null;
	}
	public ActionForward exChange(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoproductForm sf = (StoproductForm)form;
		if(sf.getOperationType()==41){ //模块换损,esam产品 
			sf.setProdId("3");
		}else if(sf.getOperationType()==42){//pos原料换损,模块产品 
			sf.setProdId("4");
		}
		Lsinfo vo=new Lsinfo();
		vo.setAppNo(sf.getAppNo());
		setPageResult(request,lsinfoBO.queryForList(vo));
		return mapping.findForward("exchange");
	}
	public ActionForward back(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoproductForm sf = (StoproductForm)form;
		sf.setWkState((short)13);
		sf.setWkStateChgDate(DateUtil.getTimeStr());
		sf.setIOState((short)3);
		sf.setIOStateChgDate(DateUtil.getTimeStr());
		sf.setDetectTime(DateUtil.getTimeStr());
		if(CheckUtil.isEmptry(sf.getSamId()))
			sf.setSamId("0");
		if(CheckUtil.isEmptry(sf.getSamCSN()))
			sf.setSamCSN("0");
		Lsinfo vo=new Lsinfo();
		vo.setAppNo(sf.getAppNo());
		vo.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("Lsinfo")),20));
		vo.setCurrDate(DateUtil.getTimeStr());
		vo.setOperId(user.getUserID());
		Stoproduct sto = new Stoproduct();
		copyProperties(sto, sf);
		copyProperties(vo, sf);		
		((StoproductBO)bo).transUpdateSto(sto, vo);
		Lsinfo queryvo=new Lsinfo();
		queryvo.setAppNo(sf.getAppNo());
		queryvo.setOperationType(sf.getOperationType().shortValue());	
		List<Lsinfo> l=lsinfoBO.queryForList(queryvo);
		if(l!=null &&l.size()>0){
			setPageResult(request,l);
		}
		return mapping.findForward("exchange");
	}
	public ActionForward show(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		StoproductForm f = (StoproductForm)form;
		StoproductForm copyf=new StoproductForm();
		copyProperties(copyf, f);
		Stoproduct prodvo =((StoproductBO)bo).queryObjectBySamId(f.getSamId());
		if(prodvo==null){
			Storeuse storeuse = (Storeuse)storeuseBO.queryObjectBySamId(f.getSamId());
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
}
