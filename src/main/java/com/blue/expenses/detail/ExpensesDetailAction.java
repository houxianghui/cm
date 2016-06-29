package com.blue.expenses.detail;



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
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;


public class ExpensesDetailAction extends IbatisBaseAction {
	@Autowired
	private ExpensesDetailBO expensesDetailBO;
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
			return updateExpensesDetail(form,request,mapping,user,response);
		}
		return null;
	}

	
	public ActionForward updateExpensesDetail(BaseForm form ,HttpServletRequest request,ActionMapping mapping,UserContext user,HttpServletResponse response)throws Exception{
		if(((ExpensesDetailForm)form).getExpensesId()==null||((ExpensesDetailForm)form).getExpensesId()==0){
			return null;
		}
		ExpensesDetail vo = new ExpensesDetail();
		copyProperties(vo,form);
		try{
			expensesDetailBO.updateExpensesDetail( vo, form);
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
		List<ExpensesDetail> l = expensesDetailBO.getSelectedStaff(request,true,user);
		List<ExpensesDetailForm> formList = new ArrayList<ExpensesDetailForm>();
		for(ExpensesDetail p:l){
			ExpensesDetailForm form = new ExpensesDetailForm();
			copyProperties(form, p);
			formList.add(form);
		}
		if(!CheckUtil.isEmptry(request.getParameter("oper"))){
			writeMessage(makeDisInfo(formList), response);
		}else{
			writeList(request, response, formList);
		}
	}
	private String makeDisInfo(List<ExpensesDetailForm> formList) {
		StringBuffer sb = new StringBuffer();
		for(ExpensesDetailForm f : formList){
			sb.append("<br>");
		}
		return sb.toString();
	}
	
	
	private ActionForward delete(BaseForm form ,HttpServletRequest request,ActionMapping mapping,HttpServletResponse response)throws Exception {
		expensesDetailBO.deleteToUp(form);
		if(!CheckUtil.isEmptry(request.getParameter("oper"))){
			writeSuccessMsg(response);
			return null;
		}else{
			return forwardSuccessPage(request, mapping, "删除成功","ExpensesDetail.do?act=list");
			}
	}

	private ActionForward list(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		ExpensesDetail vo = new ExpensesDetail();
		setPageResult(request, expensesDetailBO.queryForList(vo));
		return mapping.findForward("list");
	}


	private ActionForward add(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		ExpensesDetail vo = new ExpensesDetail();
		copyProperties(vo, form); 
		expensesDetailBO.insert(vo);
		return forwardSuccessPage(request, mapping, "添加成功","ExpensesDetail.do?act=list");
	}
	
	private ActionForward edit(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		ExpensesDetail vo = new ExpensesDetail();
		copyProperties(vo, form); 
		expensesDetailBO.update(vo);
		return forwardSuccessPage(request, mapping, "修改成功","ExpensesDetail.do?act=list");
	}
	
	private ActionForward editInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		ExpensesDetail vo = new ExpensesDetail();
		vo = (ExpensesDetail)expensesDetailBO.queryForObject(((ExpensesDetailForm)form).getEdetailId());
		copyProperties(form,vo); 
		return mapping.findForward("new");
	}
	
	private ActionForward viewInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		ExpensesDetail vo = new ExpensesDetail();
		setPageResult(request, expensesDetailBO.queryForList(vo));
		request.setAttribute("dept", request.getParameter("dept"));
		return mapping.findForward("view");
	}

}
