package com.yly.issue;

import java.io.File;
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
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.config.SysConfig;
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
import com.yly.reuse.Storeuse;
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
		}else if("pagelist".equals(act)){		//query active projects
			return pageList(form,mapping,request,user);
		}
		else if("issueInit".equals(act)){		//query active projects
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
				throw new MessageException("��ǰ���ο�����������!");
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
					    if(String.valueOf(samId).length()!=12){
						    vos[i].setSamId(StringUtil.addZeroB(String.valueOf(samId), 12-String.valueOf(samId).length()));
					    }else{
						    vos[i].setSamId(String.valueOf(samId));
					    }
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
				vos[i].setW2Limit(f.getW2Limit());
				vos[i].setW2Sign(f.getW2Sign());
				vos[i].setIsHTCard(f.getIsHTCard());
				vos[i].setIsPki(f.getIsPki());
				vos[i].setZeroExauthFlag(f.getZeroExauthFlag());
				if(isPickStor){
					vos[i].setPressCardScale(sto.getPressCardScale()==null?"":sto.getPressCardScale().trim());
					vos[i].setManufacId(sto.getManufacId());
					vos[i].setBatchId(request.getParameter("batchId"));
				}else{
					vos[i].setBatchId(SingleDicMap.getDicItemVal(SingleDic.OPERATIONTYPE, request.getParameter("batchId")));   //������ѡԭ�����ε���ʾҵ������
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
		return forwardSuccessPage(request,mapping,"����ɹ�","Issueapp.do?act=wq");
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
		return forwardSuccessPage(request,mapping,"�ύ�ɹ�","Issueapp.do?act=list");
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
		return mapping.findForward("list");
	}
	public ActionForward issueInit(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		MWsIssuetbForm f = (MWsIssuetbForm)form;
		Mwsissuetb vo=((MWsIssueBO)bo).queryForObject(f.getFormNo());
		if(!CheckUtil.isEmptry(vo.getIssueOperID()) && !vo.getIssueOperID().equals(user.getUserID())){
			throw new MessageException("��ǰ�����ѱ�����");
		}else if(vo.getFormState()==3){
			throw new MessageException("��ǰ���������");
		}else{
			vo.setIssueOperID(user.getUserID());
			vo.setFormState((short)2);
			((MWsIssueBO)bo).updateBySelective(vo);
		}
		setPageResult(request, lsinfoBO.queryForListByFormNo(vo.getFormNo()));
        request.setAttribute("pageResultLsInfo", request.getAttribute("pageResult"));
		copyProperties(form, vo);
		if(vo.getProdId().equals("4"))
			((MWsIssuetbForm)form).setAuthkey("ZJB_KEY");
		else{
			if(f.getManufacId().equals("1"))
				((MWsIssuetbForm)form).setAuthkey("WQ_KEY");
			else
				((MWsIssuetbForm)form).setAuthkey("ALLF_KEY");
		}	
		return mapping.findForward("issue");
	}
	public ActionForward pageList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		MWsIssuetbForm f = (MWsIssuetbForm)form;
		Mwsissuetb vo=((MWsIssueBO)bo).queryForObject(f.getFormNo());
		String pageNo = request.getParameter("pageNO");		
		String requery = request.getParameter("requery");
		if (CheckUtil.isEmptry(pageNo)  && requery == null ) {			
			f.setFormState_f((short)1);
	    }		
		setPageResult(request, lsinfoBO.queryForListByFormNo(vo.getFormNo()));
        request.setAttribute("pageResultLsInfo", request.getAttribute("pageResult"));
		copyProperties(form, vo);
		return mapping.findForward("issue");
	}
	private void operSysPort(String prodId,String oper,String phiTypeId) throws Exception, MessageException {
		Func func=new Func();
		Para para=new Para();
		if(oper.equals("open")){
			func.setFunc("openSystemPort");
		}else{
			func.setFunc("closeSystemPort");
		}
		if(prodId.equals("4"))//ģ��
			para.setCardtype(1);
		else para.setCardtype(0);
		para.setPhiTypeId(phiTypeId);
		int result=CallFunc.callId(func, para);
		if(result!=0){
			throw new MessageException("����������ʧ��!"); 
		}
		
	}
	public ActionForward issue(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		boolean flag=true;
		MWsIssuetbForm f = (MWsIssuetbForm)form;
		int operType=f.getOperationType();
		String partManuId="";
		String authKey="";
		if(operType==21||operType==24||operType==25||operType==43||operType==53){
			flag=false;//��Ҫ��ѡԭ�ϵķ���ҵ��
			if(CheckUtil.isEmptry(f.getAuthkey())){
				throw new MessageException("��ҵ�����ͱ���ѡ��������Կ");
			}else authKey=f.getAuthkey();
		} 
		if(!CheckUtil.isEmptry(f.getPartManufacId())){
			partManuId=f.getPartManufacId();
		} 
		Mwsissuetb vo=((MWsIssueBO)bo).queryIssueTaskCtrl(f.getFormNo());
		copyProperties(f,vo);
		f.setAuthkey(authKey);
		Func func=new Func();
		Para para=new Para();
		if(f.getApplyAttr().equals("201") ||f.getApplyAttr().equals("202")){
			func.setProdId("4");//ģ��
		}else{
			func.setProdId(f.getProdId());
		}
		try{
			operSysPort(func.getProdId(),"open",vo.getPhiTypeId());
			((MWsIssueBO)bo).initMwsissueToPara(f);

			Stoproduct prod=new Stoproduct();
			((MWsIssueBO)bo).setFunc(f, func);
			Lsinfo lsvo = ((MWsIssueBO)bo).setLsInfo(user, vo);
			for(int i=1;i<4;i++){
				((MWsIssueBO)bo).setOperAct(vo,func,i);//����\ϴ��\����
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
							if(f.getOperationType()==26 && !f.getSamId().equals(para.getSamId())){
								throw new MessageException("��ҵ�����ͱ���Ϊͬ���ط�!��ǰ��ƬΪ"+para.getSamId());
							}
							f.setSamId(para.getSamId());		
							lsvo.setSamIdOld(para.getSamId());
							String alert="";
							if(f.getOperationType()==22 ||f.getOperationType()==26){
								Storeuse reuseprodvo =storeuseBO.queryObjectBySamId(f.getSamId());
								if(reuseprodvo==null||reuseprodvo.equals(null)){
									alert="�ÿ�Ƭ�ڻ��տ���δ�ҵ�,��ȷ�����˻�:����";
									prod = null;
								}else{
									prod =new Stoproduct();
									copyProperties(prod,reuseprodvo);
								}
							}else{
								prod = stoproductBO.queryObjectBySamId(f.getSamId());
								alert="�ÿ�Ƭ�ڳ�Ʒ����δ�ҵ�,��ȷ��:����";
							}
							if(prod==null||prod.equals(null)){
								throw new MessageException(alert+f.getSamId());
							}
							if(vo.getProdId().equals("4")){
								if(!CheckUtil.isEmptry(partManuId))
									func.setManufacId(partManuId);
								else if(!CheckUtil.isEmptry(prod.getBatchIdParts())){
									Stoappinfo part_sto=stoAppBO.queryForObject(prod.getBatchIdParts());
									if(part_sto!=null)
										func.setManufacId(part_sto.getManufacId());
									else {
										throw new MessageException("�޷��ҵ�������κŵĳ�����Ϣ!"+prod.getBatchIdParts());
									}
								}else{
									throw new MessageException("�޷��ҵ����������Ϣ!");
								}
							}else{
								if(!CheckUtil.isEmptry(prod.getManufacId())){
									func.setManufacId(prod.getManufacId());
								}else{
									throw new MessageException("�޷��ҵ�������Ϣ!");
								}
							}

							vo.setManufacId(prod.getManufacId());
							f.setCardcsn(prod.getSamCSN());
							lsvo.setSamCSNOld(f.getCardcsn());
							prod.setWkState((short)14);//ע��
							prod.setWkStateChgDate(DateUtil.getTimeStr());
						}
						lsvo.setSamCSN(f.getCardcsn());
					}else if (i==3){
						Stoproduct sto =((MWsIssueBO)bo).setSto(vo,lsvo,flag,prod);
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
						lsvo.setErrorCode(result);
						lsvo.setErrorDesc(func.getFunc());
						lsinfoBO.insert(lsvo);
						request.setAttribute("samCSN", lsvo.getSamCSN());
						request.setAttribute("prodId", lsvo.getProdId());
						request.setAttribute("manufacId", func.getManufacId());	
						request.setAttribute("OAappNo", f.getOAappNo());
						request.setAttribute("appNo", f.getAppNo());
						request.setAttribute("phiTypeId", f.getPhiTypeId());
						request.setAttribute("batchId", f.getBatchId());
						request.setAttribute("applyAttr", f.getApplyAttr());
						request.setAttribute("operationType", f.getOperationType());
						request.setAttribute("formNo", f.getFormNo());
						request.setAttribute("unitId", f.getUnitId());
						String badSamId=stoproductBO.getMaxBadCard();
						request.setAttribute("samId",badSamId);
						operSysPort(func.getProdId(),"close","0");
						return popConfirmClosePage(request, mapping, "ӡˢ����"+lsvo.getSamCSN()+"���󿨺�"+badSamId+"�Ƿ���Ϊ����,�������"+func.getFunc()+SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(result)),"Mwsissuetb.do?act=issueInit&formNo="+f.getFormNo());
					}else {
						throw new MessageException("�޷���ȡӡˢ����!");
					}
					
				}
			}
		}catch(Exception e){
			operSysPort(func.getProdId(),"close","0");
			throw e;
  		}
		operSysPort(func.getProdId(),"close","0");
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
		f.setPhiTypeId(request.getParameter("phiTypeId"));
		f.setBinFileVer(request.getParameter("binFileVer"));
		Mwsissuetb vo= new Mwsissuetb();
		copyProperties(vo, f);
		Func func=new Func();
		if(f.getApplyAttr().equals("201") ||f.getApplyAttr().equals("202")){
			func.setProdId("4");
		}else{
			func.setProdId(f.getProdId());
		}
		processSysPort(response,"open",func.getProdId(),f.getPhiTypeId());
		((MWsIssueBO)bo).initMwsissueToPara(f);
		((MWsIssueBO)bo).setFunc(f,func);
		String res="";
		Stoproduct prod = new Stoproduct();
		Lsinfo lsvo = new Lsinfo();
		Para para=new Para();
		for(int i=1;i<4;i++){
			if(i==1)
				func.setOperAct("R");
			else if(i==2)
				func.setOperAct("RV");
			else if(i==3)
				func.setOperAct("TV");
			funDrools.getFunc(func);
			String[] paras=func.getPara().split(",");
			ParaTools.setPara(para, paras, f);
		    int result=CallFunc.callId(func, para);
			if(result!=0){
				if(i==1){
					res = "{\"error\":\"�������"+func.getFunc()+SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(result))+"\"}";
				}else if(i==2){
					res=res+",\"error\":\"�������"+func.getFunc()+SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(result))+"\"}";
					lsvo.setErrorCode(4003);//�汾�Ҳ���
					lsvo.setErrorDesc("ģ��汾δ����");
					lsvo.setDetectSign((short)2);	
					prod.setDetectSign((short)2);
					stoproductBO.transLsUpdate(prod,lsvo);
				}else{
//					String resultStr=para.getResult();
//					String errorType="";
//					String errorDs[]={"��������","�۷Ѳ���","��Կ����","��ʼ��"};
//					if(resultStr.equals("9999"))
//						errorType="��֧�ִ˼��";
//					else{
//						for(int j=0;j<4;j++){
//							if(!resultStr.substring(j, j+1).equals("1")){
//								errorType=errorDs[j];
//								break;
//							}else continue;
//						}
//					}
					res = res+",\"error\":\"ģ����ʧ��\"}";
					lsvo.setErrorCode(4002);//ģ�������
					lsvo.setErrorDesc("ģ����ʧ��");
					lsvo.setDetectSign((short)2);	
					prod.setDetectSign((short)2);
					stoproductBO.transLsUpdate(prod,lsvo);
				}
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
					if(!prod.getAppTypeId().equals("301") &&!prod.getAppTypeId().equals("302")&&!prod.getAppTypeId().equals("201")&&!prod.getAppTypeId().equals("202")){		
						res =  res+"\"}";  
						stoproductBO.transLsUpdate(prod,lsvo);
						writeAjaxResponse(response, res);
						break;
					}else{
						continue;
					} 
				}else if(i==2){
					String mflag=para.getModelflag()==1?"�ѻ�ģ��":"����ģ��";
					if(para.getModelflag()!=1){//����
						para.setVersion(StringUtil.getLastSpaceByFix(para.getVersion()));
					}
					para.setVersion(StringUtil.trim(para.getVersion()));
					res = res+";ver_"+para.getVersion()+";moduleflag_"+mflag+"\"";
					if(!para.getVersion().equals(ReDefSDicMap.getDicItemVal(RedefSDicCodes.MODULEVERSION, f.getBinFileVer()))){
						res = res+",\"error\":\"ģ��汾����"+para.getVersion()+"\"}";
						lsvo.setErrorCode(4001);//�汾����
						lsvo.setErrorDesc("ģ��汾����"+para.getVersion());
						lsvo.setDetectSign((short)2);	
						prod.setDetectSign((short)2);
						stoproductBO.transLsUpdate(prod,lsvo);
						writeAjaxResponse(response, res);
						break;
					}else {
						if(testModule(para.getVersion())){
							continue;
						}else{
							res=res+"}";  
							lsvo.setErrorCode(0);//�ɹ�
							lsvo.setDetectSign((short)1);	
							prod.setDetectSign((short)1);
							stoproductBO.transLsUpdate(prod,lsvo);
							writeAjaxResponse(response, res);
							break;
						}

					}
				}else {
						res=res+"}";  
						lsvo.setErrorCode(0);//�ɹ�
						lsvo.setDetectSign((short)1);	
						prod.setDetectSign((short)1);
						stoproductBO.transLsUpdate(prod,lsvo);
						writeAjaxResponse(response, res);
				}
				
			}
		}
		processSysPort(response,"close",func.getProdId(),f.getPhiTypeId());
	}
	public void read(BaseForm form,ActionMapping mapping,HttpServletRequest request,HttpServletResponse response)throws Exception{
		MWsIssuetbForm f = new MWsIssuetbForm();
		f.setProdId(request.getParameter("prodId"));
		f.setOperationType(Short.valueOf(request.getParameter("operationType")));
		f.setPhiTypeId(request.getParameter("phiTypeId"));
		((MWsIssueBO)bo).initMwsissueToPara(f);
		Func func=new Func();
		func.setProdId(f.getProdId());
		Para para=new Para();
		String res="";
		processSysPort(response,"open",func.getProdId(),f.getPhiTypeId());		
		for(int i=1;i<4;i++){
			if(i==1)
				func.setOperAct("R");
			else if(i==2)
				func.setOperAct("RV");
			else if(i==3)
				func.setOperAct("TV");
			funDrools.getFunc(func);
			String[] paras=func.getPara().split(",");
			ParaTools.setPara(para, paras, f);
			int result=CallFunc.callId(func, para);
			if(result!=0){
				if(i==1){
					res = "{\"error\":\"�������"+func.getFunc()+SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(result))+"\"}";
				}else if(i==2){
					res = res+",\"error\":\"�������"+func.getFunc()+SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(result))+"\"}";
				}else{
//					String resultStr=para.getResult();
//					String errorType="";
//					String errorDs[]={"��������","�۷Ѳ���","��Կ����","��ʼ��"};
//					if(resultStr.equals("9999"))
//						errorType="��֧�ִ˼��";
//					else{
//						for(int j=0;j<4;j++){
//							if(!resultStr.substring(j, j+1).equals("1")){
//								errorType=errorDs[j];
//								break;
//							}else continue;
//						}
//					}
					res = res+",\"error\":\"ģ����ʧ��\"}";
				}
				writeAjaxResponse(response, res);
				break;
			}else{
				if(i==1){
					res = "{\"origSamId\":\""+para.getSamId()+"";
					if(!f.getProdId().equals("4")){		
						res =  res+"\"}";
						writeAjaxResponse(response, res);
						break;
					}else{//ģ���޸���Ҫ�������ź�ģ��汾
						continue;
					} 
				}else if(i==2){
					if(para.getModelflag()!=1){
						para.setVersion(StringUtil.getLastSpaceByFix(para.getVersion()));
					}
					res = res+"\",\"module\":\""+para.getVersion()+"\"";
					if(testModule(para.getVersion())){
						continue;
					}else{
						res=res+"}";  
						writeAjaxResponse(response, res);
						break;
					}
				}else{
					res=res+"}";  
					writeAjaxResponse(response, res);
				}
			
			}
		}
		processSysPort(response,"close",func.getProdId(),f.getPhiTypeId());
	}

	public void processSysPort(HttpServletResponse response,
			String oper,String prodId,String phiTypeId) throws Exception, MessageException {
		
		int result;
		Func func=new Func();
		Para para=new Para();
		if(oper.equals("open")){
			func.setFunc("openSystemPort");
		}else{
			func.setFunc("closeSystemPort");
		}
		if(prodId.equals("4"))//ģ��
			para.setCardtype(1);
		else para.setCardtype(0);
		para.setPhiTypeId(phiTypeId);
		result=CallFunc.callId(func, para);
		if(result!=0 ){
			String res = "{\"error\":\"��д��"+oper+"ʧ��"+result+"\"}";
			writeAjaxResponse(response, res);
		}
	
		
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
		if(prodvo==null||prodvo.equals(null)){
			throw new MessageException("��SAM���Ҳ���ԭ���м�¼");
		}
 		copyProperties(f, prodvo);
 		f.setCardcsn(prodvo.getSamCSN());
		if(f.getAppTypeId()==null || f.getAppTypeId()<100){
			throw new MessageException("��SAM��Ӧ�����Ͳ�����,�������޸�����");
		}
		f.setAppTypeId(Integer.parseInt(prodvo.getAppTypeId()));
		return mapping.findForward("show");	
	}
	
	
	public ActionForward singleIssue(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		MWsIssuetbForm f = (MWsIssuetbForm)form;	
		f.setSamId(f.getOrigSamId());
		Stoproduct sto=stoproductBO.queryObjectBySamId(f.getSamId());
		if(sto==null ||CheckUtil.isEmptry(sto.getSamCSN())){
			throw new MessageException("��SAM���Ҳ���ԭ���м�¼");
		}
 		if(CheckUtil.isEmptry(f.getPhiTypeId())){
			throw new MessageException("��SAM��ͨ�����ʲ�����,�������޸�����");
		}
		f.setApplyAttr(String.valueOf(f.getAppTypeId()));
		Mwsissuetb vo = new Mwsissuetb();
 		copyProperties(vo,f);
		Func func=new Func();
		Para para=new Para();
 		if(f.getApplyAttr().equals("201") ||f.getApplyAttr().equals("202")){
			func.setProdId("4");//ģ��
		}else{
			func.setProdId(f.getProdId());
		}
 		try{
 			operSysPort(func.getProdId(),"open",f.getPhiTypeId());
			Lsinfo lsvo = ((MWsIssueBO)bo).setLsInfo(user, vo);
			lsvo.setSamId(f.getSamId());
			lsvo.setSamCSN(f.getCardcsn());
			lsvo.setOperationType(f.getOperationType());
			lsvo.setSamCSNOld(f.getCardcsn());
			lsvo.setSamIdOld(f.getSamId());
			((MWsIssueBO)bo).initMwsissueToPara(f);
			initIssueParaByApplyType(f);

			((MWsIssueBO)bo).setFunc(f, func);
			if(func.getProdId().equals("4")){
				if(!CheckUtil.isEmptry(f.getPartManufacId()))
					func.setManufacId(f.getPartManufacId());
				else if(!CheckUtil.isEmptry(sto.getBatchIdParts())){
					Stoappinfo part_sto=stoAppBO.queryForObject(sto.getBatchIdParts());
					if(part_sto!=null)
						func.setManufacId(part_sto.getManufacId());
					else {
						throw new MessageException("�޷��ҵ�������κŵĳ�����Ϣ!"+sto.getBatchIdParts());
					}
				}else{
					throw new MessageException("�޷��ҵ����������Ϣ!");
				}
			}else{
				if(!CheckUtil.isEmptry(sto.getManufacId())){
					func.setManufacId(sto.getManufacId());
				}else{
					throw new MessageException("�޷��ҵ�������Ϣ!");
				}
			}
			for(int i=2;i<4;i++){
				((MWsIssueBO)bo).setOperAct(vo,func,i);//ϴ��\����
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
						 sto.setSamId(lsvo.getSamId());
						 sto.setSamCSN(lsvo.getSamCSN());
						 sto.setWkState((short)12);
						 sto.setWkStateChgDate(DateUtil.getTimeStr());
						 sto.setIssueTime(DateUtil.getTimeStr());
						 sto.setIOState((short)2);
						 sto.setIOStateChgDate(DateUtil.getTimeStr());
						((MWsIssueBO)bo).transRepairTb(sto,lsvo,sec);
					}
				}else{
					lsvo.setErrorCode(result);
					lsvo.setErrorDesc(func.getFunc());
					lsinfoBO.insert(lsvo);
					request.setAttribute("samCSN", lsvo.getSamCSN());
					request.setAttribute("prodId", lsvo.getProdId());
					request.setAttribute("manufacId", func.getManufacId());	
					request.setAttribute("OAappNo", f.getOAappNo());
					request.setAttribute("appNo","");
					request.setAttribute("phiTypeId", f.getPhiTypeId());
					request.setAttribute("batchId", f.getBatchId());
					request.setAttribute("applyAttr", f.getApplyAttr());
					request.setAttribute("operationType", f.getOperationType());
					request.setAttribute("formNo", "");
					request.setAttribute("unitId", f.getUnitId());
					request.setAttribute("samId",lsvo.getSamId());
					operSysPort(func.getProdId(),"close","0");
					return popConfirmClosePage(request, mapping, "ӡˢ����"+lsvo.getSamCSN()+"���п���"+lsvo.getSamId()+"�Ƿ���Ϊ����,�������"+func.getFunc()+SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(result)),"Mwsissuetb.do?act=repair");
	
				}
			}
			
 		}catch(Exception e){
 			operSysPort(func.getProdId(),"close","0");
 			throw e;
 		}
 		operSysPort(func.getProdId(),"close","0");
		if(!vo.getApplyAttr().equals("301") &&!vo.getApplyAttr().equals("302")&&!vo.getApplyAttr().equals("201")&&!vo.getApplyAttr().equals("202")){
			return forwardSuccessPage(request,mapping,"�޸��ɹ�","Mwsissuetb.do?act=repair");
		}else{
			throw new MessageException("�޸��ɹ�");
		}
	}
	public void down(BaseForm form,ActionMapping mapping,HttpServletRequest request,HttpServletResponse response)throws Exception{
		String url=SysConfig.getProperty("IAP.URL")+File.separator+"IAP.exe";
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
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox�����
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
		f.setAuthSign(Short.valueOf(CheckUtil.isEmptry(apply.getIsIsamSign())?"0":apply.getIsIsamSign()));
		f.setW2Limit(Integer.parseInt(apply.getIsV2Sign()));
		f.setIsHTCard(Integer.parseInt(apply.getIsHLCard()));
		f.setIsPki(Integer.parseInt(apply.getIsPki()));
		f.setZeroExauthFlag(Integer.parseInt(CheckUtil.isEmptry(apply.getIsIsamTestAllO())?"0":apply.getIsIsamTestAllO()));
		return f;
	}
	private boolean testModule(String version){
		boolean flag=false;
		if (version.startsWith("С����"))
			flag=true;
		else if (version.startsWith("ͣ����"))
			flag=true;
		else if (version.startsWith("PR����"))
			flag=true;
		return flag;
	}

}
