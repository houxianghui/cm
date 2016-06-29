package com.blue.taxi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blue.budget.detail.BudgetDetail;
import com.blue.budget.detail.BudgetDetailDAO;
import com.blue.budget.detail.BudgetDetailExample;
import com.blue.expenses.ExpensesInfo;
import com.blue.expenses.ExpensesInfoBO;
import com.blue.travel.TravelInfo;
import com.blue.travel.TravelInfoBO;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;


public class TaxiInfoAction extends IbatisBaseAction {
	@Autowired
	private TaxiInfoBO taxiInfoBO;
	@Autowired
	private TravelInfoBO travelInfoBO;
	@Autowired
	private ExpensesInfoBO expensesInfoBO;
	@Autowired
	private BudgetDetailDAO budgetDetailDAO;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("list".equalsIgnoreCase(act)){
			return list(form,mapping,request,response,user);
		}else if("c".equalsIgnoreCase(act)){
			return mapping.findForward("new");
		}else if("u".equalsIgnoreCase(act)){
			return editInfo(form,mapping,request,response,user);
		}else if("v".equalsIgnoreCase(act)){
			return viewInfo(form,mapping,request,response,user);
		}else if("add".equals(act)){
			return add(form,mapping,request,response,user);
		}else if("delete".equals(act)){
			return delete(form,request,mapping,response);
		}else if("edit".equals(act)){
			return edit(form,mapping,request,response,user);
		}else if("getSelectedStaff".equalsIgnoreCase(act)){
			getSelectedStaff(request,response,user);
			return null;
		}else if("update".equals(act)){
			return updateTaxiInfo(form,request,mapping,user,response);
		}
		return null;
	}

	
	public ActionForward updateTaxiInfo(BaseForm form ,HttpServletRequest request,ActionMapping mapping,UserContext user,HttpServletResponse response)throws Exception{
		if(((TaxiInfoForm)form).getExpensesId()==0){
			return null;
		}
		TaxiInfo vo = new TaxiInfo();
		copyProperties(vo,form);
		try{
			taxiInfoBO.updateToUp(form, vo);
		}catch(Exception ex){
			writeMessage(ex.getMessage(),response);
			return null;
		}
		if(!CheckUtil.isEmptry(request.getParameter("oper"))){
			writeSuccessMsg(response);
			return null;
		}else{
			return null;
			}
	}

	private void getSelectedStaff(HttpServletRequest request,HttpServletResponse response,UserContext user)throws Exception{
		List<TaxiInfo> l = taxiInfoBO.getSelectedStaff(request,true,user);
		List<TaxiInfoForm> formList = new ArrayList<TaxiInfoForm>();
		for(TaxiInfo p:l){
			TaxiInfoForm form = new TaxiInfoForm();
			copyProperties(form, p);
			formList.add(form);
		}
		if(!CheckUtil.isEmptry(request.getParameter("oper"))){
			writeMessage(makeDisInfo(formList), response);
		}else{
			writeList(request, response, formList);
		}
	}
	private String makeDisInfo(List<TaxiInfoForm> formList) {
		StringBuffer sb = new StringBuffer();
		for(TaxiInfoForm f : formList){
			sb.append("<br>");
		}
		return sb.toString();
	}
	
	private ActionForward delete(BaseForm form ,HttpServletRequest request,ActionMapping mapping,HttpServletResponse response)throws Exception {
		taxiInfoBO.deleteToUp(form);
		if(!CheckUtil.isEmptry(request.getParameter("oper"))){
			writeSuccessMsg(response);
			return null;
		}else{
			return forwardSuccessPage(request, mapping, "删除成功","TaxiInfo.do?act=list");
			}
	}

	private ActionForward list(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TaxiInfo vo = new TaxiInfo();
		setPageResult(request, taxiInfoBO.queryForList(vo));
		return mapping.findForward("list");
	}


	private ActionForward add(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TaxiInfo vo = new TaxiInfo();
		copyProperties(vo, form); 
		taxiInfoBO.insert(vo);
		return forwardSuccessPage(request, mapping, "添加成功","TaxiInfo.do?act=list");
	}
	
	private ActionForward edit(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TaxiInfo vo = new TaxiInfo();
		copyProperties(vo, form); 
		taxiInfoBO.update(vo);
		return forwardSuccessPage(request, mapping, "修改成功","TaxiInfo.do?act=list");
	}
	
	private ActionForward editInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TaxiInfo vo = new TaxiInfo();
		vo = (TaxiInfo)taxiInfoBO.queryForObject(((TaxiInfoForm)form).getTaxiId());
		copyProperties(form,vo); 
		return mapping.findForward("new");
	}
	
	private ActionForward viewInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TaxiInfo vo = new TaxiInfo();
		vo.setExpensesId(((TaxiInfoForm)form).getExpensesId());
		setPageResult(request, taxiInfoBO.queryForList(vo));
		TravelInfo tinfo = (TravelInfo)travelInfoBO.queryForObject(((TaxiInfoForm)form).getExpensesId());
		String dept = "";
		if(tinfo==null){
			ExpensesInfo einfo = (ExpensesInfo)expensesInfoBO.queryForObject(((TaxiInfoForm)form).getExpensesId());
			dept = einfo.getDept();
		}else
			dept = tinfo.getDept();
		request.setAttribute("totalAmt", taxiInfoBO.getTotal(String.valueOf(((TaxiInfoForm)form).getExpensesId())));
		request.setAttribute("dept", dept);
		return mapping.findForward("view");
	}

}
