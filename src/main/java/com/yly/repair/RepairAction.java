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
import com.yly.func.CallFunc;
import com.yly.func.Para;
import com.yly.func.ParaTools;




public class RepairAction extends IbatisBaseAction {

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
		if(prodId.equals("4"))//模块
			para.setCardtype(1);
		else para.setCardtype(0);
		int result=CallFunc.callId(func, para);
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
		// int result=CallFunc.callId(func, para);
			int result=0;
			para.setCardcsn("66666000000000000011");
		if(result==0){
			f.setCardcsn(para.getCardcsn());
			res = "{\"csn\":\""+f.getCardcsn()+"\"}";
		}else{
			res = "{\"error\":\"错误代码"+result+"\"}";
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
	//	int result=CallFunc.callId(func, para);
		int result=25742;
		if(result==0){
			operSysPort(f.getProdId(),"close");
			return forwardSuccessPage(request,mapping,"修复成功","Repair.do?act=repairInit");
		}else{
			request.setAttribute("samCSN",f.getCardcsn());
			request.setAttribute("manufacId",f.getManufacId());
			request.setAttribute("prodId",f.getProdId());
			return popConfirmClosePage(request, mapping, "是否标记为坏卡,错误代码"+func.getFunc()+result,"Repair.do?act=repairInit");
		}
	}
	private void initRepairForm(RepairForm f){
		f.setAuthkey(keyVDatagram.getMainKeyMap(f.getAuthkey()));
	}
}
