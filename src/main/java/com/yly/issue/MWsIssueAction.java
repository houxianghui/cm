package com.yly.issue;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import org.springframework.beans.factory.annotation.Autowired;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.StringUtil;
import com.yly.conf.CallfuncconfBO;
import com.yly.drools.FunDrools;
import com.yly.drools.Func;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductBO;
import com.yly.func.CallFunc;
import com.yly.func.Para;
import com.yly.func.ParaTools;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoBO;
import com.yly.reuse.StoreuseBO;
import com.yly.stor.StoAppInfoBO;
import com.yly.stor.Stoappinfo;



public class MWsIssueAction extends IbatisBaseAction {
	static final short DONE=3;
	static final short READY=1;
	static final String OLDTRANSKEY="1";
	static final String NEWTRANSKEY="1";
	private IssueappBO issueappBO;
	private StoAppInfoBO stoAppBO;	
	private LsinfoBO lsinfoBO;
	private CallfuncconfBO callfuncconfBO;
	private StoproductBO stoproductBO;
	private StoreuseBO storeuseBO;
	public StoreuseBO getStoreuseBO() {
		return storeuseBO;
	}

	public void setStoreuseBO(StoreuseBO storeuseBO) {
		this.storeuseBO = storeuseBO;
	}

	public StoproductBO getStoproductBO() {
		return stoproductBO;
	}

	public void setStoproductBO(StoproductBO stoproductBO) {
		this.stoproductBO = stoproductBO;
	}

	public CallfuncconfBO getCallfuncconfBO() {
		return callfuncconfBO;
	}

	public void setCallfuncconfBO(CallfuncconfBO callfuncconfBO) {
		this.callfuncconfBO = callfuncconfBO;
	}

	public LsinfoBO getLsinfoBO() {
		return lsinfoBO;
	}

	public void setLsinfoBO(LsinfoBO lsinfoBO) {
		this.lsinfoBO = lsinfoBO;
	}

	public StoAppInfoBO getStoAppBO() {
		return stoAppBO;
	}

	public void setStoAppBO(StoAppInfoBO stoAppBO) {
		this.stoAppBO = stoAppBO;
	}

	public IssueappBO getIssueappBO() {
		return issueappBO;
	}

	public void setIssueappBO(IssueappBO issueappBO) {
		this.issueappBO = issueappBO;
	}

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	
	@Autowired
	FunDrools funDrools;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("add".equals(act)){		//add 		
			return addApply(form,mapping,request,user);
		} else if("list".equals(act)){		//query active projects
			return queryList(form,mapping,request,user);
		}else if("issueInit".equals(act)){		//query active projects
			return issueInit(form,mapping,request,user);
		}else if("issue".equals(act)){		//query active projects
			return issue(form,mapping,request,user);
		}else if("closePort".equals(act)){		//query active projects 
			return closePort(form,mapping,request,user);
		}else if("E".equals(act)){		//query active projects 
			exam(form,mapping,request,response);
			return null;
		}else return null;
	}
	
	public ActionForward addApply(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String issueTotAmt=request.getParameter("workSheetAmt");
		Stoappinfo sto = new Stoappinfo();
		boolean isPickStor;			
		sto=stoAppBO.queryForObject(request.getParameter("batchId"));
		if(sto!=null){
			isPickStor=true;
			if(sto.getCurrPeriodAmt()<Long.parseLong(issueTotAmt)){
				throw new MessageException("当前批次可用数量不足!");
			}			
		}else{
			isPickStor=false;
		}
		String taskCtrlNo[]=request.getParameter("taskCtrlNo").split(",");
		MWsIssuetbForm mf = new MWsIssuetbForm();
		Mwsissuetb vos[]=new Mwsissuetb[taskCtrlNo.length];
		Issuetaskctrl taskctrlvos[]=new Issuetaskctrl[taskCtrlNo.length];
		for(int i=0;i<taskCtrlNo.length;i++){
			vos[i]=new Mwsissuetb();
			taskctrlvos[i]=new Issuetaskctrl();
			if(CheckUtil.isEmptry(taskCtrlNo[i])){
				break;
			}else{
				IssueappForm f = new IssueappForm();
				f.setTaskCtrlNo(taskCtrlNo[i]);
				mf.setTaskCtrlNo(taskCtrlNo[i]);
				List issuelist=issueappBO.getW_QueryList(f);
				Iterator iter_1 = issuelist.iterator();
			    while (iter_1.hasNext()) {
			    	f = (IssueappForm)iter_1.next();
			    }
				List mwsIssuevoList=((MWsIssueBO)bo).queryForListByExample(mf);
				if(taskCtrlNo.length==1){
					vos[i].setWorkSheetAmt(Long.parseLong(issueTotAmt));
				}else{
					vos[i].setWorkSheetAmt(f.getIssueAmt()-f.getIssueDoneAmt());
				}
				if(mwsIssuevoList==null||mwsIssuevoList.size()==0){
					vos[i].setWsSnr((short)1);
					vos[i].setSamId(f.getSamIdBegin());
					vos[i].setSamIdBegin(f.getSamIdBegin());

					if(f.getSamIdBegin().equals(f.getSamIdEnd())){
						vos[i].setSamIdEnd(vos[i].getSamIdBegin());

					}else{
					    long samId=Long.parseLong(f.getSamIdBegin())+vos[i].getWorkSheetAmt()-1;
					    if(String.valueOf(samId).length()!=12){
					    	vos[i].setSamIdEnd(StringUtil.addZeroB(String.valueOf(samId), 12-String.valueOf(samId).length()));
					    }else{
					    	vos[i].setSamIdEnd(String.valueOf(samId));
					    }
					}
				}else{
					long tot=0;
					short count=0;
					Iterator iter = mwsIssuevoList.iterator();
				    while (iter.hasNext()) {
				    	Mwsissuetb stoMws = (Mwsissuetb)iter.next();
				    	tot+=stoMws.getWorkSheetAmt();
				    	count++;
				    }
				    vos[i].setWsSnr(++count);
					if(f.getSamIdBegin()==f.getSamIdEnd()){
						  vos[i].setSamId(String.valueOf(f.getSamIdBegin()));
					}else{
					    long samId=Long.parseLong(f.getSamIdBegin())+tot;
					    vos[i].setSamId(String.valueOf(samId));
					    vos[i].setSamIdBegin(vos[i].getSamId());
					    long samIdEnd=samId+vos[i].getWorkSheetAmt()-1;
					    if(String.valueOf(samIdEnd).length()!=12){
						    vos[i].setSamIdEnd(StringUtil.addZeroB(String.valueOf(samIdEnd), 12-String.valueOf(samIdEnd).length()));
					    }else{
						    vos[i].setSamIdEnd(String.valueOf(samIdEnd));
					    }
					    	
					}

				}
				vos[i].setTaskCtrlNo(taskCtrlNo[i]);
				vos[i].setApplyAttr(String.valueOf(f.getAppTypeId()));
				vos[i].setAuthSign(f.getAuthSign());
				vos[i].setProdId(f.getProdId());			
				vos[i].setBatchId(request.getParameter("batchId"));
				vos[i].setBinFileVer(f.getBinFileVer());
				vos[i].setFormNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("MWsIssueTb")),16));
				vos[i].setFormState(READY);
				vos[i].setFormTime(DateUtil.getTimeStr());
				vos[i].setIssueDoneAmt((long)0);
				vos[i].setIssueOperID("");
				vos[i].setKeyType(f.getKeyType());
				vos[i].setPhiTypeId(f.getPhiTypeId());
				vos[i].setOperationType(f.getOperationType().shortValue());
				vos[i].setW2limit(f.getW2limit());
				vos[i].setIsHTCard(f.getIsHTCard());
				vos[i].setIsPki(f.getIsPki());
				vos[i].setZeroExauthFlag(f.getZeroExauthFlag());
				if(isPickStor){
					vos[i].setPressCardScale(sto.getPressCardScale()==null?"":sto.getPressCardScale().trim());
					vos[i].setManufacId(sto.getManufacId());
				}else{
					vos[i].setPressCardScale("");
					vos[i].setManufacId("");
				}
				taskctrlvos[i].setTaskCtrlNo(taskCtrlNo[i]);
				taskctrlvos[i].setIssueDoneAmt(f.getIssueDoneAmt()+vos[i].getWorkSheetAmt());
				if(taskctrlvos[i].getIssueDoneAmt()>=f.getIssueAmt()){
					taskctrlvos[i].setTaskState(DONE);
				}else taskctrlvos[i].setTaskState(READY);
			}
		}
		long lastAmt=0;
		if(isPickStor){
			lastAmt=(sto.getCurrPeriodAmt()-Long.parseLong(issueTotAmt));
		}
		((MWsIssueBO)bo).transOperate(isPickStor,lastAmt,request.getParameter("batchId"),vos,taskctrlvos);
		return forwardSuccessPage(request,mapping,"分配成功","Issueapp.do?act=wq");
	}
	public ActionForward addTask(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		IssueappForm f = (IssueappForm)form;
		//setPageResult(request, ((IssueappBO)bo).getAppList(f));
		return mapping.findForward("addTask");
	}

	public ActionForward update(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		Issueapp vo = new Issueapp();
		copyProperties(vo,(IssueappForm)form);
		((IssueappBO)bo).validate((IssueappForm)form);
		vo.setOperId(user.getUserID());		
		vo.setCurrDate(DateUtil.getDTStr());
		((IssueappBO)bo).update(vo);
		return forwardSuccessPage(request,mapping,"提交成功","Issueapp.do?act=list");
	}

	public ActionForward queryList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (CheckUtil.isEmptry(pageNo)  && requery == null ) {			
			return mapping.findForward("list");
	    }
		MWsIssuetbForm f = (MWsIssuetbForm)form;
		setPageResult(request, ((MWsIssueBO)bo).queryForListByExample(f));
		return mapping.findForward("list");
	}
	public ActionForward closePort(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		MWsIssuetbForm f = (MWsIssuetbForm)form;
		operSysPort(f.getProdId(),"close"); 
		Mwsissuetb vo=((MWsIssueBO)bo).queryIssueTaskCtrl(f.getFormNo());
		return mapping.findForward("list");
	}
	public ActionForward popList(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		IssueappForm f = (IssueappForm)form;
		setPageResult(request, ((IssueappBO)bo).getAppList(f));
		return mapping.findForward("popList");
	}
	public ActionForward issueInit(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		MWsIssuetbForm f = (MWsIssuetbForm)form;
		Mwsissuetb vo=((MWsIssueBO)bo).queryForObject(f.getFormNo());
		if(!CheckUtil.isEmptry(vo.getIssueOperID()) && !vo.getIssueOperID().equals(user.getUserID())){
			throw new MessageException("当前任务已被锁定");
		}else if(vo.getFormState()==3){
			throw new MessageException("当前任务已完成");
		}else{
			vo.setIssueOperID(user.getUserID());
			vo.setFormState((short)2);
			((MWsIssueBO)bo).updateBySelective(vo);
		}
		setPageResult(request, lsinfoBO.queryForListByFormNo(vo.getFormNo()));
        request.setAttribute("pageResultLsInfo", request.getAttribute("pageResult"));
		copyProperties(form, vo);
		operSysPort(vo.getProdId(),"open");
		return mapping.findForward("issue");
	}

	private void operSysPort(String prodId,String oper) throws Exception, MessageException {
		Func func=new Func();
		Para para=new Para();
		if(oper.equals("open")){
			func.setFunc("openSystemPort");
		}else{
			func.setFunc("closeSystemPort");
		}
		if(prodId.equals("4"))//模块
			para.setCardtype(1);
		else para.setCardtype(0);
		int result=CallFunc.callId(func, para);
	}
	public ActionForward issue(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		MWsIssuetbForm f = (MWsIssuetbForm)form;	
		Mwsissuetb vo=((MWsIssueBO)bo).queryIssueTaskCtrl(f.getFormNo());
		copyProperties(f,vo);
		initMwsissueToPara(f);
		Func func=new Func();
		Para para=new Para();
		setFunc(vo, func);
		Lsinfo lsvo = setLsInfo(user, vo);
		for(int i=1;i<4;i++){
			setOperAct(vo,func,i);//读卡\洗卡\发行
			funDrools.getFunc(func);
			String[] paras=func.getPara().split(",");
			ParaTools.setPara(para, paras, f);
			int result=CallFunc.callId(func, para);
		//	int result=0;para.setCardcsn("111111");
			if(result==0){
				if(i==1){
					if(func.getOperAct().equals("RC")){
						f.setCardcsn(para.getCardcsn());
					}else if(func.getOperAct().equals("R")){
						f.setSamId(para.getSamId());
						Stoproduct prod = new Stoproduct();
						prod = stoproductBO.queryForObject(f.getSamId());
						if(prod==null){
							prod =(Stoproduct)storeuseBO.queryForObject(f.getSamId());
							if(prod==null){
								throw new MessageException("无法找到原sam印刷卡号!");
							}
						}
						f.setCardcsn(prod.getSamCSN());
					}
					lsvo.setSamCSN(f.getCardcsn());
				}else if (i==3){
					Stoproduct sto = setSto(vo, lsvo);
					Issueapp issuapp = null;
					if(vo.getSamIdEnd().compareTo(vo.getSamId())>0){ 
						int cardno=Integer.parseInt(vo.getSamId().substring(7))+1;
						vo.setSamId(vo.getSamIdEnd().substring(0,7)+StringUtil.addZero(String.valueOf(cardno), 5));
		 			}else{
						vo.setSamId(vo.getSamIdEnd());
					}
					int done=(vo.getIssueDoneAmt()).intValue();
					vo.setIssueDoneAmt((long)(++done));
					if(vo.getIssueDoneAmt().longValue()==vo.getWorkSheetAmt().longValue()){
						vo.setFormState(DONE);						
						int cnt = stoproductBO.countIssueByExample(sto);	
						issuapp = issueappBO.queryForObject(lsvo.getAppNo());
						if(cnt+1==issuapp.getTaskAmt().intValue()){
							issuapp.setFormState((short)3);
						}
					}
					((MWsIssueBO)bo).transFourTb(vo,sto,lsvo,issuapp);
				}
			}else{
				lsvo.setErrorCode((short)result);
				lsinfoBO.insert(lsvo);
				if(!CheckUtil.isEmptry(lsvo.getSamCSN())){
					request.setAttribute("samCSN", lsvo.getSamCSN());
					return popConfirmClosePage(request, mapping, lsvo.getSamCSN()+"是否标记为坏卡错误代码"+func.getFunc()+result,"");

				}
				break;
			}
		}
		setPageResult(request, lsinfoBO.queryForListByFormNo(vo.getFormNo()));
        request.setAttribute("pageResultLsInfo", request.getAttribute("pageResult"));
		copyProperties(form, vo);
		return mapping.findForward("issue");
	}

	private Stoproduct setSto(Mwsissuetb vo, Lsinfo lsvo) throws Exception {
		Stoproduct sto= new Stoproduct();
		copyProperties(sto, vo);
		sto.setAppTypeId(vo.getApplyAttr());
		sto.setIssueTime(DateUtil.getTimeStr());
		sto.setDetectSign((short)0);
		sto.setSamCSN(lsvo.getSamCSN());
		sto.setOAappNo(vo.getAppNo());
		sto.setWkState((short)12);//已发行
		sto.setCardPhyStat((short)1);//好卡
		sto.setSamId(lsvo.getSamId());
		sto.setOAappNo(lsvo.getAppNo());
		sto.setIOState((short)1);
		return sto;
	}

	private Lsinfo setLsInfo(UserContext user, Mwsissuetb vo) throws Exception {
		Lsinfo lsvo= new Lsinfo();
		copyProperties(lsvo, vo);
		lsvo.setCurrDate(DateUtil.getTimeStr());
		lsvo.setDetectSign((short)0);
		lsvo.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("LsInfo")),16));
		lsvo.setOperId(user.getUserID());
		lsvo.setErrorCode((short)0);
		lsvo.setSamCSNOld("");
		lsvo.setSamIdOld("");
		lsvo.setSamCSN("");
		return lsvo;
	}

	private void initMwsissueToPara(MWsIssuetbForm f) {
		f.setAuthSign(0);
		f.setW2Sign(0);
		f.setW2Limits(0);
		f.setIsPki(0);
		f.setIsHTCard(0);
		f.setZeroExauthFlag(0);
		short operType=f.getOperationType();
		if(operType==21||operType==24){
			f.setOldTranskey("OLDTRANSKEY");
			f.setNewTranskey("NEWTRANSKEY");    
		}else if(operType==22 || operType==23||operType==25||operType==26){
			f.setOldTranskey("NEWTRANSKEY");
			f.setNewTranskey("NEWTRANSKEY");    
		}
		f.setSJL05IP(0);        
		f.setSJL05PORT("");               
		f.setFivePara("10100");       
		f.setEf15("");           
		f.setEf16("");           
		f.setEf17("");           
		f.setRetpki("");         
		f.setInpki("");          
		f.setMotEf17("");        
		f.setSamId("");          
		f.setModelflag(0);      
		f.setVersion("");        
		f.setAuthkey("");        
		f.setResult("");         
		f.setCardcsn(""); 
		f.setCardtype(0);
	}

	private void setFunc(Mwsissuetb vo, Func func) {
		func.setApplyAttr(vo.getApplyAttr());
		func.setManufacId(vo.getManufacId());
		func.setProdId(vo.getProdId());
	}
	private void setOperAct(Mwsissuetb vo, Func func,int step) {
		short operType=vo.getOperationType();
		if(operType==21||operType==24){
			if(step==1){
				func.setOperAct("RC");
			}else if(step==2){
				func.setOperAct("W");
			}else if(step==3){
				func.setOperAct("I");
			}
			
		}else if(operType==22 || operType==23||operType==25||operType==26){	
			if(step==1){
				func.setOperAct("R");
			}else if(step==2){
				func.setOperAct("W");
			}else if(step==3){
				func.setOperAct("I");
			}
		}
	}
	
	public void exam(BaseForm form,ActionMapping mapping,HttpServletRequest request,HttpServletResponse response)throws Exception{
		MWsIssuetbForm f = new MWsIssuetbForm();
		f.setOperationType(Short.valueOf(request.getParameter("operationType")));
		f.setProdId(request.getParameter("prodId"));
		f.setManufacId(request.getParameter("manufacId"));
		f.setApplyAttr(request.getParameter("applyAttr"));
		Mwsissuetb vo= new Mwsissuetb();
		copyProperties(vo, f);
		initMwsissueToPara(f);
		Func func=new Func();
		Para para=new Para();
		setFunc(vo,func);
		String res="";
		Stoproduct prod = new Stoproduct();
		Lsinfo lsvo = new Lsinfo();
		for(int i=1;i<3;i++){
			if(i==1)
				func.setOperAct("R");
			else if(i==2)
				func.setOperAct("RV");
			funDrools.getFunc(func);
			String[] paras=func.getPara().split(",");
			ParaTools.setPara(para, paras, f);
		//	int result=CallFunc.callId(func, para);
			int result=0;
			para.setSamId("000000100003");
			if(result!=0){
				throw new MessageException("请联系系统维护人员!错误代码"+func.getFunc()+result);
			}else{
				if(i==1){
					prod = stoproductBO.queryForObject(para.getSamId());
					lsvo.setSamCSN(prod.getSamCSN());
					lsvo.setSamId(prod.getSamId());
					lsvo = lsinfoBO.queryLastObject(lsvo);
					lsvo.setDetectSign((short)1);		
					prod.setDetectSign((short)1);
					prod.setDetectTime(DateUtil.getTimeStr());
					res = "{\"flowNo\":\""+lsvo.getFlowNo()+"\",\"detectSign\":\""+lsvo.getDetectSign()+"\",\"msg\":\"samId"+para.getSamId()+"samCSN"+prod.getSamCSN();
					if(!vo.getProdId().equals("4")){		
						res =  res+"\"}";
						stoproductBO.transLsUpdate(prod,lsvo);
						writeAjaxResponse(response, res);
						break;
					}else{
						continue;
					} 
				}else{
					res = res+"ver"+para.getVersion()+"\"}";
					stoproductBO.transLsUpdate(prod,lsvo);
					writeAjaxResponse(response, res);
				}
			
			}
		}
	}
}
