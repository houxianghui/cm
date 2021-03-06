package com.yly.repair;

 
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
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.KeyVDatagram;
import com.yly.drools.FunDrools;
import com.yly.drools.Func;
import com.yly.exstore.StoproductBO;
import com.yly.func.CallFunc;
import com.yly.func.Para;
import com.yly.func.ParaTools;
import com.yly.issue.MWsIssuetbForm;




public class RepairAction extends IbatisBaseAction {
	private StoproductBO stoproductBO;
	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	
	public StoproductBO getStoproductBO() {
		return stoproductBO;
	}




	public void setStoproductBO(StoproductBO stoproductBO) {
		this.stoproductBO = stoproductBO;
	}
	@Autowired
	FunDrools funDrools;
	@Autowired
	KeyVDatagram keyVDatagram;
	@Override

	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("repairInit".equals(act)){		//query active projects
			return repairInit(form,mapping,request);
		}else if("repair".equals(act)){		//query active projects
			return repair(form,mapping,request,user);
		}else if("read".equals(act)){		//query active projects 
			read(form,mapping,request,response);
			return null;
		}else return null;
	}
	



	public ActionForward repairInit(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		return mapping.findForward("new");
	}

	private void operSysPort(RepairForm f,String oper) throws Exception, MessageException {
		Func func=new Func();
		Para para=new Para();
		if(oper.equals("open")){
			func.setFunc("openSystemPort");
		}else{
			func.setFunc("closeSystemPort");
		}
		if(f.getProdId().equals("4"))//ģ��
			para.setCardtype(1);
		else para.setCardtype(0);
		para.setPhiTypeId(f.getPhiTypeId());
		int result=CallFunc.callId(func, para);
		if(result!=0){
			throw new MessageException("��д������ʧ��!");
		}
	}
	
	
	public void processSysPort(HttpServletResponse response,
			RepairForm f,String oper) throws Exception, MessageException {
		
		int result;
		Func func=new Func();
		Para para=new Para();
		if(oper.equals("open")){
			func.setFunc("openSystemPort");
		}else{
			func.setFunc("closeSystemPort");
		}
		if(f.getProdId().equals("4"))//ģ��
			para.setCardtype(1);
		else para.setCardtype(0);
		para.setPhiTypeId(f.getPhiTypeId());
		result=CallFunc.callId(func, para);
		if(result!=0 ){
			String res = "{\"error\":\"��д��"+oper+"ʧ��"+result+"\"}";
			writeAjaxResponse(response, res);
		}
	
		
	}
	public void read(BaseForm form,ActionMapping mapping,HttpServletRequest request,HttpServletResponse response)throws Exception{
		RepairForm f = (RepairForm)form;
		Func func=new Func();
		func.setOperAct("RC");
		Para para=new Para();
		String res="";
		setFunc(f,func);
		funDrools.getFunc(func);	
		String[] paras=func.getPara().split(",");
		ParaTools.setRepairPara(para, paras, f);
		processSysPort(response, f,"open");
		int result=CallFunc.callId(func, para);
		if(result==0){
			String cardCsn=para.getCardcsn();
			f.setCardcsn(cardCsn);
			res = "{\"csn\":\""+cardCsn+"\"}";
		}else{
			res = "{\"error\":\"�������"+SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(result))+"\"}";
		}
		processSysPort(response, f,"close");
		writeAjaxResponse(response, res);
	}


	private void setFunc(RepairForm f, Func func) {
		func.setManufacId(f.getManufacId());
		func.setProdId(f.getProdId());
	}

	
	public ActionForward repair(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		RepairForm f = (RepairForm)form;	
		try{
			operSysPort(f,"open");
			initRepairForm(f);
			for(int i=1;i<3;i++){
				Func func=new Func();
				if(i==1)
					func.setOperAct("W");
				else func.setOperAct("RP");
				Para para=new Para();
				setFunc(f,func);
				funDrools.getFunc(func);
				String[] paras=func.getPara().split(",");
				ParaTools.setRepairPara(para, paras, f);
			    int result=CallFunc.callId(func, para);
			    if(result==0){
			    	if(i==1)
			    		continue;
			    }else{
			    	request.setAttribute("samCSN",f.getCardcsn());
					request.setAttribute("manufacId",f.getManufacId());
					request.setAttribute("prodId",f.getProdId());
					request.setAttribute("OAappNo", "");
					request.setAttribute("phiTypeId", f.getPhiTypeId());
					request.setAttribute("batchId", "");
					request.setAttribute("applyAttr","");
					request.setAttribute("OAappNo","");
					request.setAttribute("operationType", f.getOperationType());
					request.setAttribute("formNo", "");
					request.setAttribute("unitId", "");
					String badSamId=stoproductBO.getMaxBadCard();
					request.setAttribute("samId",badSamId);
					operSysPort(f,"close");
					return popConfirmClosePage(request, mapping, "ӡˢ����"+f.getCardcsn()+"���󿨺�"+badSamId+"�Ƿ���Ϊ����,�������"+func.getFunc()+SingleDicMap.getDicItemVal(SingleDic.ERRORCODE, String.valueOf(result)),"Repair.do?act=repairInit");
			    }
			}
		}catch(Exception e){
			operSysPort(f,"close");
			throw e;
		}

		operSysPort(f,"close");
		return forwardSuccessPage(request,mapping,"�޸��ɹ�","Repair.do?act=repairInit");
	}
	private void initRepairForm(RepairForm f){
		f.setSJL05IP(6666);        
		f.setSJL05PORT("192.168.1.9");      
		String KEY;
		if(CheckUtil.isEmptry(f.getManufacId())){
			KEY= "BMAC_KEY";
		}else{
			if(f.getManufacId().equals("1"))
				KEY="WQ_KEY";
			else
				KEY="ALLF_KEY";
			
		}
		f.setOldTranskey(keyVDatagram.getMainKeyMap(f.getAuthkey()));
		f.setNewTranskey(keyVDatagram.getMainKeyMap(KEY));
		f.setAuthkey(keyVDatagram.getMainKeyMap(KEY));
	}
}
