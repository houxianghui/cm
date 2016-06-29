package com.blue.expenses;

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
import com.blue.expenses.detail.ExpensesDetail;
import com.blue.expenses.detail.ExpensesDetailBO;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;
import com.eis.util.SimpleMoneyFormat;


public class ExpensesInfoAction extends IbatisBaseAction {
	@Autowired
	private ExpensesInfoBO expensesInfoBO;
	@Autowired
	private ExpensesDetailBO expensesDetailBO;
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
			return delete(form,request,mapping);
		}else if("report".equals(act)){
			return report(form,mapping,request,response,user);
		}
		return null;
	}

	
	private ActionForward delete(BaseForm form, HttpServletRequest request,ActionMapping mapping)throws Exception {
		expensesInfoBO.deleteToUp(form);
		return forwardSuccessPage(request, mapping, "É¾³ý³É¹¦","ExpensesInfo.do?act=list");
	}

	private ActionForward report(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		List<ExpensesInfo> eilist = expensesInfoBO.queryForReport(form,user);
		double totAmt=0;
		for(ExpensesInfo e:eilist){
			totAmt+=e.getTotalAmt();
		}
		request.setAttribute("list", eilist);
		request.setAttribute("totAmt", totAmt);
		return mapping.findForward("report");
	}
	
	private ActionForward list(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		setPageResult(request, expensesInfoBO.queryForList(form,user));
		return mapping.findForward("list");
	}

	
	private ActionForward add(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		ExpensesInfo vo = new ExpensesInfo();
		copyProperties(vo, form);
		vo.setCurrUser(user.getUserID());
		expensesInfoBO.updateToUp(vo);
		copyProperties(form, vo);
		return mapping.findForward("new");
	}
	
	
	private ActionForward editInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		ExpensesInfo vo = new ExpensesInfo();
		vo = (ExpensesInfo)expensesInfoBO.queryForObject(((ExpensesInfoForm)form).getExpensesId());
		copyProperties(form,vo); 
		return mapping.findForward("new");
	}
	
	private ActionForward viewInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		ExpensesDetail evo = new ExpensesDetail();
		evo.setExpensesId(((ExpensesInfoForm)form).getExpensesId());
		List<ExpensesDetail> edlist= expensesDetailBO.queryForList(evo);
		setPageResult(request, edlist);
		ExpensesInfo vo = new ExpensesInfo();
		vo = (ExpensesInfo)expensesInfoBO.queryForObject(((ExpensesInfoForm)form).getExpensesId());
		copyProperties(form,vo);
		int invoiceno = 0;
		for (ExpensesDetail expensesDetail : edlist) {
			invoiceno += expensesDetail.getInvoiceno();
		}
		request.setAttribute("invoiceno", invoiceno);
		request.setAttribute("totalAmt", vo.getTotalAmt());
		request.setAttribute("chineseAmt",SimpleMoneyFormat.getInstance().format(vo.getTotalAmt()));
		return mapping.findForward("view");
	}
	


}
