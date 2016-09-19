package com.yly.issue;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.KeyVDatagram;
import com.eis.util.StringUtil;
import com.yly.drools.FunDrools;
import com.yly.drools.Func;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductBO;
import com.yly.func.CallFunc;
import com.yly.func.Para;
import com.yly.func.ParaTools;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoBO;
import com.yly.para.Applytypeinfo;
import com.yly.para.ApplytypeinfoBO;
import com.yly.pki.Secpkitb;
import com.yly.reuse.StoreuseBO;
import com.yly.stor.StoAppInfoBO;
import com.yly.stor.StoAppInfoForm;
import com.yly.stor.Stoappinfo;



public class MWsIssueAction extends IbatisBaseAction {
	static final short DONE=3;
	static final short READY=1;
	private IssueappBO issueappBO;
	private StoAppInfoBO stoAppBO;	
	private LsinfoBO lsinfoBO;
	private StoproductBO stoproductBO;
	private StoreuseBO storeuseBO;
	private IssuetaskCtrlBO issuetaskctrlBO;
	private IssueAppInfoReport issueAppInfoReport;
	private ApplytypeinfoBO applytypeinfoBO;
	public ApplytypeinfoBO getApplytypeinfoBO() {
		return applytypeinfoBO;
	}

	public void setApplytypeinfoBO(ApplytypeinfoBO applytypeinfoBO) {
		this.applytypeinfoBO = applytypeinfoBO;
	}

	public IssueAppInfoReport getIssueAppInfoReport() {
		return issueAppInfoReport;
	}

	public void setIssueAppInfoReport(IssueAppInfoReport issueAppInfoReport) {
		this.issueAppInfoReport = issueAppInfoReport;
	}

	public IssuetaskCtrlBO getIssuetaskctrlBO() {
		return issuetaskctrlBO;
	}

	public void setIssuetaskctrlBO(IssuetaskCtrlBO issuetaskctrlBO) {
		this.issuetaskctrlBO = issuetaskctrlBO;
	}

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
	@Autowired
	KeyVDatagram keyVDatagram;
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
		}else if("singleIssue".equals(act)){		//query active projects
			return singleIssue(form,mapping,request,user);
		}else if("closePort".equals(act)){		//query active projects 
			return closePort(form,mapping,request,user);
		}else if("E".equals(act)){		//query active projects 
			exam(form,mapping,request,response);
			return null;
		}else if("R".equals(act)){		//query active projects 
			read(form,mapping,request,response);
			return null;
		}else if("down".equals(act)){		//query active projects 
			down(form,mapping,request,response);
			return null;
		}else if("repair".equals(act)){		//query active projects
			return repair(form,mapping,request,user);
		}else if("staticsDown".equals(act)){		//query active projects
			return staticsdown(request,response,form,user); 
		}else if("statics".equals(act)){		//query active projects
			return mapping.findForward("statics");
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
					vos[i].setBatchId(request.getParameter("batchId"));
				}else{
					vos[i].setBatchId(SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, request.getParameter("batchId")));   //不需挑选原料批次的显示业务类型
					vos[i].setPressCardScale("");
					vos[i].setManufacId("");
				}
				taskctrlvos[i]=issuetaskctrlBO.queryForObject(taskCtrlNo[i]);
				vos[i].setOAappNo(taskctrlvos[i].getOAappNo());
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
		MWsIssuetbForm f = (MWsIssuetbForm)form;
		if (CheckUtil.isEmptry(pageNo)  && requery == null ) {			
			f.setFormState_f((short)1);
	    }
		setPageResult(request, ((MWsIssueBO)bo).queryForWList(f,user));
		return mapping.findForward("list");
	}
	public ActionForward closePort(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
//		MWsIssuetbForm f = (MWsIssuetbForm)form;
//		operSysPort(f.getProdId(),"close"); 
//		Mwsissuetb vo=((MWsIssueBO)bo).queryIssueTaskCtrl(f.getFormNo());
//		copyProperties(form, vo);
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
		if(result!=0){
			throw new MessageException("请检查读写器");
		}
	}
	public ActionForward issue(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		MWsIssuetbForm f = (MWsIssuetbForm)form;	
		Mwsissuetb vo=((MWsIssueBO)bo).queryIssueTaskCtrl(f.getFormNo());
		copyProperties(f,vo);
		operSysPort(vo.getProdId(),"open");
		((MWsIssueBO)bo).initMwsissueToPara(f);
		Func func=new Func();
		Para para=new Para();
		Stoproduct prod=new Stoproduct();
		((MWsIssueBO)bo).setFunc(f, func);
		Lsinfo lsvo = ((MWsIssueBO)bo).setLsInfo(user, vo);
		for(int i=1;i<4;i++){
			((MWsIssueBO)bo).setOperAct(vo,func,i);//读卡\洗卡\发行
			funDrools.getFunc(func);
			String[] paras=func.getPara().split(",");
			ParaTools.setPara(para, paras, f);
			int result=CallFunc.callId(func, para);
			if(result==0){
				if(i==1){
					if(func.getOperAct().equals("RC")){
						if(CheckUtil.isEmptry(para.getCardcsn())){
							para.setCardcsn("0");
						}
						f.setCardcsn(para.getCardcsn());
						if(vo.getProdId().equals("4")){
							Stoappinfo sto=stoAppBO.queryForObject(f.getBatchId());
							Stoappinfo part_sto=stoAppBO.queryForObject(sto.getRsvd().trim());
							func.setManufacId(part_sto.getManufacId());
						}
					}else if(func.getOperAct().equals("R")){
						f.setSamId(para.getSamId());						
						prod = stoproductBO.queryObjectBySamId(f.getSamId());
						if(prod==null){	
							prod =(Stoproduct)storeuseBO.queryForObject(f.getSamId());
							if(prod==null){
								operSysPort(vo.getProdId(),"close");
								throw new MessageException("无法找到原sam卡号!");
							}
						}
						if(vo.getProdId().equals("4")){
							if(!CheckUtil.isEmptry(f.getPartManufacId()))
								func.setManufacId(f.getPartManufacId());
							else if(!CheckUtil.isEmptry(prod.getBatchIdParts())){
								Stoappinfo part_sto=stoAppBO.queryForObject(prod.getBatchIdParts());
								if(part_sto!=null)
									func.setManufacId(part_sto.getManufacId());
								else {
									operSysPort(vo.getProdId(),"close");
									throw new MessageException("无法找到配件批次号的厂商信息!"+prod.getBatchIdParts());
								}
							}else{
								operSysPort(vo.getProdId(),"close");
								throw new MessageException("无法找到配件厂商信息!");
							}
						}else{
							if(!CheckUtil.isEmptry(prod.getManufacId())){
								func.setManufacId(prod.getManufacId());
							}else{
								operSysPort(vo.getProdId(),"close");
								throw new MessageException("无法找到厂商信息!");
							}
						}

						vo.setManufacId(prod.getManufacId());
						f.setCardcsn(prod.getSamCSN());
						prod.setWkState((short)14);//注销
						prod.setWkStateChgDate(DateUtil.getTimeStr());
					}
					lsvo.setSamCSN(f.getCardcsn());
				}else if (i==3){
					Stoproduct sto = ((MWsIssueBO)bo).setSto(vo, lsvo);
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
					Secpkitb sec=null;
					if(!CheckUtil.isEmptry(para.getRetpki())&&para.getRetpki().length()==256){
						sec=new Secpkitb();
						sec.setSamId(sto.getSamId());
						sec.setSamCSN(sto.getSamCSN());
						sec.setCurrPeriod(DateUtil.getCurrDate());
						sec.setIssueTime(DateUtil.getCurrDate());
						sec.setPubExponent("");
						sec.setPubKey(para.getRetpki());
						sec.setKeyType((short)(sto.getKeyType()));
					}
					((MWsIssueBO)bo).transSixTb(vo,sto,lsvo,issuapp,sec,prod);
				}
			}else{
				if(!CheckUtil.isEmptry(lsvo.getSamCSN())){
					lsvo.setErrorCode((short)result);
					lsinfoBO.insert(lsvo);
					request.setAttribute("samCSN", lsvo.getSamCSN());
					request.setAttribute("prodId", lsvo.getSamCSN());
					request.setAttribute("manufacId", lsvo.getSamCSN());	
					String badSamId=stoproductBO.getMaxBadCard();
					request.setAttribute("samId",badSamId);
					operSysPort(vo.getProdId(),"close");
					return popConfirmClosePage(request, mapping, "印刷卡号"+lsvo.getSamCSN()+"错误卡号"+badSamId+"是否标记为坏卡,错误代码"+func.getFunc()+result,"Mwsissuetb.do?act=issueInit&formNo="+f.getFormNo());
				}else {
					operSysPort(vo.getProdId(),"close");
					throw new MessageException("无法获取印刷卡号!");
				}
				
			}
		}
		operSysPort(vo.getProdId(),"close");
		setPageResult(request, lsinfoBO.queryForListByFormNo(vo.getFormNo()));
        request.setAttribute("pageResultLsInfo", request.getAttribute("pageResult"));
		copyProperties(form, vo);
		return mapping.findForward("issue");
	}

	
	public void exam(BaseForm form,ActionMapping mapping,HttpServletRequest request,HttpServletResponse response)throws Exception{
		MWsIssuetbForm f = new MWsIssuetbForm();
		f.setOperationType(Short.valueOf(request.getParameter("operationType")));
		f.setProdId(request.getParameter("prodId"));
		f.setManufacId(request.getParameter("manufacId"));
		f.setApplyAttr(request.getParameter("applyAttr"));
		Mwsissuetb vo= new Mwsissuetb();
		copyProperties(vo, f);
		operSysPort(vo.getProdId(),"open");
		((MWsIssueBO)bo).initMwsissueToPara(f);
		Func func=new Func();
		((MWsIssueBO)bo).setFunc(f,func);
		String res="";
		Stoproduct prod = new Stoproduct();
		Lsinfo lsvo = new Lsinfo();
		Para para=new Para();
		for(int i=1;i<3;i++){
			if(i==1)
				func.setOperAct("R");
			else if(i==2)
				func.setOperAct("RV");
			funDrools.getFunc(func);
			String[] paras=func.getPara().split(",");
			ParaTools.setPara(para, paras, f);
		    int result=CallFunc.callId(func, para);
			if(result!=0){
				res = "{\"error\":\"错误代码"+func.getFunc()+result+"\"}";
				writeAjaxResponse(response, res);
				break;
			}else{
				if(i==1){
					prod = stoproductBO.queryObjectBySamId(para.getSamId());
					lsvo.setSamCSN(prod.getSamCSN());
					lsvo.setSamId(prod.getSamId());
					lsvo.setFormNo(f.getFormNo());
					lsvo = lsinfoBO.queryLastObject(lsvo);
					lsvo.setDetectSign((short)1);	
					prod.setDetectSign((short)1);				
					prod.setDetectTime(DateUtil.getTimeStr());
					res = "{\"flowNo\":\""+lsvo.getFlowNo()+"\",\"detectSign\":\""+lsvo.getDetectSign()+"\",\"msg\":\"samId_"+para.getSamId()+";samCSN_"+prod.getSamCSN();
					if(!vo.getProdId().equals("4")){		
						res =  res+"\"}";  
						stoproductBO.transLsUpdate(prod,lsvo);
						writeAjaxResponse(response, res);
						break;
					}else{
						continue;
					} 
				}else{
					String mflag=para.getModelflag()==1?"脱机模块":"联机模块";
					res = res+";ver_"+para.getVersion()+";moduleflag_"+mflag+"\"}";
					lsvo.setDetectSign((short)1);	
					prod.setDetectSign((short)1);		
					stoproductBO.transLsUpdate(prod,lsvo);
					writeAjaxResponse(response, res);
				}
			}
		}
		operSysPort(vo.getProdId(),"close");
	}
	public void read(BaseForm form,ActionMapping mapping,HttpServletRequest request,HttpServletResponse response)throws Exception{
		MWsIssuetbForm f = new MWsIssuetbForm();
		f.setProdId(request.getParameter("prodId"));
		f.setOperationType(Short.valueOf(request.getParameter("operationType")));
		((MWsIssueBO)bo).initMwsissueToPara(f);
		Func func=new Func();
		func.setProdId(f.getProdId());
		Para para=new Para();
		String res="";
		operSysPort(f.getProdId(),"open");
		for(int i=1;i<3;i++){
			if(i==1)
				func.setOperAct("R");
			else if(i==2)
				func.setOperAct("RV");
			funDrools.getFunc(func);
			String[] paras=func.getPara().split(",");
			ParaTools.setPara(para, paras, f);
			int result=CallFunc.callId(func, para);
			if(result!=0){
				res = "{\"error\":\"错误代码"+func.getFunc()+result+"\"}";
				writeAjaxResponse(response, res);
				break;
			}else{
				if(i==1){
					res = "{\"origSamId\":\""+para.getSamId()+"";
					if(!f.getProdId().equals("4")){		
						res =  res+"\"}";
						writeAjaxResponse(response, res);
						break;
					}else{//模块修复需要读出卡号和模块版本
						continue;
					} 
				}else{
					res = res+"\",\"module\":\""+para.getVersion()+"\"}";
					writeAjaxResponse(response, res);
				}
			
			}
		}
		operSysPort(f.getProdId(),"close");
	}
	public ActionForward repair(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String requery = request.getParameter("requery");
		if (requery == null ) {			
			return mapping.findForward("repair");
	    }
		MWsIssuetbForm f = (MWsIssuetbForm)form;
		Stoproduct prodvo = new Stoproduct();
		prodvo.setSamId(f.getOrigSamId());
		prodvo = stoproductBO.queryObjectBySamId(prodvo.getSamId());
		if(prodvo==null){
			throw new MessageException("此SAM号找不到原发行记录");
		}
 		copyProperties(f, prodvo);
 		f.setCardcsn(prodvo.getSamCSN());
		f.setAppTypeId(Integer.parseInt(prodvo.getAppTypeId()));
		return mapping.findForward("show");	
	}
	
	
	public ActionForward singleIssue(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		MWsIssuetbForm f = (MWsIssuetbForm)form;	
		f.setSamId(f.getOrigSamId());
		f.setApplyAttr(String.valueOf(f.getAppTypeId()));
		Mwsissuetb vo = new Mwsissuetb();
 		copyProperties(vo,f);
		operSysPort(vo.getProdId(),"open");
		Lsinfo lsvo = ((MWsIssueBO)bo).setLsInfo(user, vo);
		lsvo.setSamId(f.getSamId());
		lsvo.setSamCSN(f.getCardcsn());
		lsvo.setOperationType(f.getOperationType());
		lsvo.setSamCSNOld(f.getCardcsn());
		lsvo.setSamIdOld(f.getSamId());
		((MWsIssueBO)bo).initMwsissueToPara(f);
		initIssueParaByApplyType(f);
		Func func=new Func();
		Para para=new Para();
		((MWsIssueBO)bo).setFunc(f, func);
		for(int i=2;i<4;i++){
			((MWsIssueBO)bo).setOperAct(vo,func,i);//洗卡\发行
			funDrools.getFunc(func);
			String[] paras=func.getPara().split(",");
			ParaTools.setPara(para, paras, f);
		    int result=CallFunc.callId(func, para);
			if(result==0){
				 if (i==3){
					 Secpkitb sec = null;
					 if(!CheckUtil.isEmptry(para.getRetpki())&&para.getRetpki().length()==256){
						 sec = new Secpkitb();
						 sec.setSamId(lsvo.getSamId());
						 sec.setSamCSN(lsvo.getSamCSN());
						 sec.setCurrPeriod(DateUtil.getCurrDate());
						 sec.setIssueTime(DateUtil.getCurrDate());
						 sec.setPubExponent("");
						 sec.setPubKey(para.getRetpki());
						 sec.setKeyType((short)(f.getKeyType()));
					 }
					 Stoproduct sto=new Stoproduct();
					 sto.setSamId(lsvo.getSamId());
					 sto.setSamCSN(lsvo.getSamCSN());
					 sto=stoproductBO.queryForObject(sto);
					 if(sto==null){
						 sto= new Stoproduct();
						 copyProperties(sto, f);
						 sto.setSamCSN(f.getCardcsn());
						 sto.setKeyType((short)f.getKeyType());
						 sto.setAppTypeId(String.valueOf(f.getAppTypeId()));
						 sto.setCardPhyStat((short)1);//好卡
					 }
					 sto.setWkState((short)12);
					 sto.setWkStateChgDate(DateUtil.getTimeStr());
					 sto.setIssueTime(DateUtil.getTimeStr());
					 sto.setIOState((short)2);
					 sto.setIOStateChgDate(DateUtil.getTimeStr());
					((MWsIssueBO)bo).transRepairTb(sto,lsvo,sec);
				}
			}else{
				lsvo.setErrorCode((short)result);
				lsinfoBO.insert(lsvo);
				request.setAttribute("samCSN", lsvo.getSamCSN());
				request.setAttribute("prodId", lsvo.getSamCSN());
				request.setAttribute("manufacId", lsvo.getSamCSN());	
				String badSamId=stoproductBO.getMaxBadReturnCard();
				request.setAttribute("samId",badSamId);
				operSysPort(vo.getProdId(),"close");
				return popConfirmClosePage(request, mapping, "印刷卡号"+lsvo.getSamCSN()+"错误卡号"+badSamId+"是否标记为坏卡,错误代码"+func.getFunc()+result,"Mwsissuetb.do?act=repair");

			}
		}
		operSysPort(vo.getProdId(),"close");
		return forwardSuccessPage(request,mapping,"修复成功","Mwsissuetb.do?act=repair");
	}
	public void down(BaseForm form,ActionMapping mapping,HttpServletRequest request,HttpServletResponse response)throws Exception{
 	    String url="E:\\work\\code\\design-eclipse\\projectmanage-1.0\\MFC\\IAP.exe";
		Process proc = Runtime.getRuntime().exec(url);  
		String res="";
		writeAjaxResponse(response, res);
	}

	private ActionForward staticsdown(HttpServletRequest request,HttpServletResponse response,  BaseForm form,UserContext user) throws Exception{
		MWsIssuetbForm f = (MWsIssuetbForm)form;
		Mwsissuetb vo = new Mwsissuetb();
		vo.setBeginDate_f(f.getBeginDate_f());
		vo.setEndDate_f(f.getEndDate_f());
		issueAppInfoReport.createExcel(vo, false);
		response.setContentType("application/octet-stream");
		String filename = issueAppInfoReport.getEt().getSheetName()+".xls";
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		issueAppInfoReport.getEt().write(out);
		out.close();
		return null;
	}
	private MWsIssuetbForm initIssueParaByApplyType(MWsIssuetbForm f)throws Exception{
		Applytypeinfo apply = new Applytypeinfo();
		apply.setApplyTypeId(f.getApplyAttr());
		apply =(Applytypeinfo)applytypeinfoBO.queryForObject(apply);
		f.setW2Sign(Short.valueOf(apply.getIsV2()));
		f.setAuthSign(Short.valueOf(apply.getIsIsamSign()==null?"0":apply.getIsIsamSign()));
		f.setW2Limits(Integer.parseInt(apply.getIsV2Sign()));
		f.setIsHTCard(Integer.parseInt(apply.getIsHLCard()));
		f.setIsPki(Integer.parseInt(apply.getIsPki()));
		f.setZeroExauthFlag(Integer.parseInt(apply.getIsIsamTestAllO()==null?"0":apply.getIsIsamTestAllO()));
		return f;
	}

}
