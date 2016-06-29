package com.blue.budget.detail;



import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;


public class BudgetDetailAction extends IbatisBaseAction {
	@Autowired
	private BudgetDetailBO budgetDetailBO;
	
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("report".equalsIgnoreCase(act)){
			return report(form,mapping,request,response,user);
		}
		if("dept".equalsIgnoreCase(act)){
			return dept(form,mapping,request,response,user);
		}
		if("project".equalsIgnoreCase(act)){
			return project(form,mapping,request,response,user);
		}
		
		return null;
	}
	
	private ActionForward report(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		List<BudgetDetail> relist = budgetDetailBO.queryForReport(form, user);
		double totBal=0;
		double totAmt=0;
		for (BudgetDetail budgetDetail : relist) {
			totBal+=budgetDetail.getFeeBal();
			totAmt+=budgetDetail.getFeeAmt();
		}
		double totOTB = totAmt - totBal;
		request.setAttribute("list",relist);
		request.setAttribute("totBal",totBal);
		request.setAttribute("totAmt",totAmt);
		request.setAttribute("totOTB",totOTB);
		return mapping.findForward("report");
	}
	
	private ActionForward dept(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		List<BudgetDetail> relist = budgetDetailBO.selectByReportDept(form, user);
		double totBal=0;
		double totAmt=0;
		for (BudgetDetail budgetDetail : relist) {
			totBal+=budgetDetail.getFeeBal();
			totAmt+=budgetDetail.getFeeAmt();
		}
		double totOTB = totAmt - totBal;
		request.setAttribute("list",relist);
		request.setAttribute("totBal",totBal);
		request.setAttribute("totAmt",totAmt);
		request.setAttribute("totOTB",totOTB);
		return mapping.findForward("dept");
	}
	
	private ActionForward project(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		List<BudgetDetail> relist = budgetDetailBO.selectByReportProject(form, user);
		double totBal=0;
		double totAmt=0;
		for (BudgetDetail budgetDetail : relist) {
			totBal+=budgetDetail.getFeeBal();
			totAmt+=budgetDetail.getFeeAmt();
		}
		double totOTB = totAmt - totBal;
		request.setAttribute("list",relist);
		request.setAttribute("totBal",totBal);
		request.setAttribute("totAmt",totAmt);
		request.setAttribute("totOTB",totOTB);
		return mapping.findForward("project");
	}


}
