package com.yly.repair;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.KeyVDatagram;
import com.yly.drools.FunDrools;
import com.yly.drools.Func;
import com.yly.exstore.StoproductBO;
import com.yly.func.CallFunc;
import com.yly.func.Para;
import com.yly.func.ParaTools;




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

	private void operSysPort(String prodId,String oper) throws Exception, MessageException {
		Func func=new Func();
		Para para=new Para();
		if(oper.equals("open")){
			func.setFunc("openSystemPort");
		}else{
			func.setFunc("closeSystemPort");
		}
		if(prodId.equals("4"))//Ä£¿é
			para.setCardtype(1);
		else para.setCardtype(0);
		int result=CallFunc.callId(func, para);
		if(result!=0){
			throw new MessageException("Çë¼ì²é¶ÁÐ´Æ÷");
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
		operSysPort(f.getProdId(),"open");
		//int result=CallFunc.callId(func, para);
		int result=0;
		para.setCardcsn("66666000000000000011");
		if(result==0){
			f.setCardcsn(para.getCardcsn());
			res = "{\"csn\":\""+f.getCardcsn()+"\"}";
		}else{
			res = "{\"error\":\"´íÎó´úÂë"+result+"\"}";
		}
		writeAjaxResponse(response, res);
	}


	private void setFunc(RepairForm f, Func func) {
		func.setManufacId(f.getManufacId());
		func.setProdId(f.getProdId());
		
	}

	
	public ActionForward repair(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		RepairForm f = (RepairForm)form;	
		initRepairForm(f);
		Func func=new Func();
		func.setOperAct("RP");
		Para para=new Para();
		setFunc(f,func);
		funDrools.getFunc(func);
		String[] paras=func.getPara().split(",");
		ParaTools.setRepairPara(para, paras, f);
	    //int result=CallFunc.callId(func, para);
		int result=0;
		if(result==0){
			operSysPort(f.getProdId(),"close");
			return forwardSuccessPage(request,mapping,"ÐÞ¸´³É¹¦","Repair.do?act=repairInit");
		}else{
			request.setAttribute("samCSN",f.getCardcsn());
			request.setAttribute("manufacId",f.getManufacId());
			request.setAttribute("prodId",f.getProdId());
			String badSamId=stoproductBO.getMaxBadCard();
			request.setAttribute("samId",badSamId);
			return popConfirmClosePage(request, mapping, "Ó¡Ë¢¿¨ºÅ"+f.getCardcsn()+"´íÎó¿¨ºÅ"+badSamId+"ÊÇ·ñ±ê¼ÇÎª»µ¿¨,´íÎó´úÂë"+func.getFunc()+result,"Repair.do?act=repairInit");
		}
	}
	private void initRepairForm(RepairForm f){
		f.setAuthkey(keyVDatagram.getMainKeyMap(f.getAuthkey()));
	}
}
