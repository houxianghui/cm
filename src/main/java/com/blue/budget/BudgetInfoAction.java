package com.blue.budget;



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

import com.blue.budget.detail.BudgetDetailBO;
import com.blue.project.ProjectList;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.projectmaintain.ProjectMaintainBO;
import com.projectmaintain.ProjectMaintainForm;


public class BudgetInfoAction extends IbatisBaseAction {
	@Autowired
	private BudgetInfoBO budgetInfoBO;
	@Autowired
	private BudgetDetailBO budgetDetailBO;
	@Autowired
	private ProjectMaintainBO projectMaintainBO;
	
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("list".equalsIgnoreCase(act)){
			return list(form,mapping,request,response,user);
		}else if("report".equalsIgnoreCase(act)){
			return report(form,mapping,request,response,user);
		}else if("c".equalsIgnoreCase(act)){
			return newInfo(form,mapping,request,response,user);
		}else if("u".equalsIgnoreCase(act)){
			return editInfo(form,mapping,request,response,user);
		}else if("v".equalsIgnoreCase(act)){
			return viewInfo(form,mapping,request,response,user);
		}else if("d".equals(act)){
			return delete(form,request,mapping,response);
		}else if("delete".equals(act)){
			return delete(form,request,mapping,response);
		}else if("edit".equals(act)){
			return edit(form,mapping,request,response,user);
		}else if("getSelectedStaff".equalsIgnoreCase(act)){
			getSelectedStaff(request,response,user);
			return null;
		}else if("update".equals(act)){
			return updateBudgetInfo(form,request,mapping,user,response);
		}
		
		return null;
	}
	
	
	public ActionForward updateBudgetInfo(BaseForm form ,HttpServletRequest request,ActionMapping mapping,UserContext user,HttpServletResponse response)throws Exception{
		BudgetInfo vo = new BudgetInfo();
		if(user.getAttribute("budgetInfo")==null){
			return null;
		}
		String[] budgetInfo = ((String)user.getAttribute("budgetInfo")).split(",");
		copyProperties(vo,form);
		vo.setDepart(budgetInfo[0]);
		vo.setBudgetKind(budgetInfo[1]);
		//vo.setFeeKind(budgetInfo[2]);
		vo.setYear(budgetInfo[3]);
		vo.setRegDate(DateUtil.getDTStr());
		vo.setUserId(user.getUserID());
		if(!CheckUtil.isEmptry(vo.getProjectNo())){
			ProjectList pvo = projectMaintainBO.queryForObject(vo.getProjectNo());
			vo.setProjectName(pvo.getProjectName());
		}else{
			vo.setProjectName("");
		}
		try{
			if(vo.getBudgetId()==null||vo.getBudgetId()==0){
				if(budgetInfoBO.queryAllList(vo, user).size()!=0){
					throw new MessageException("年份+费用类型+预算类型+部门+项目必须唯一");
				}
				long BudgetId = KeyGenerator.getNextKey("BUDGET_INFO");
				vo.setBudgetId(BudgetId);
				budgetInfoBO.insert(vo,user);
			}else{
				BudgetInfo bi = (BudgetInfo)budgetInfoBO.queryForObject(vo.getBudgetId());
				if(!bi.getFeeKind().equals(vo.getFeeKind())){
					String s = "{\"msg\":\"不能修改费用类型\"}";
					writeAjaxResponse(response, s);
				}
				budgetInfoBO.update(vo);
			}
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
		String budgetInfo = request.getParameter("depart")==null?"":request.getParameter("depart")+",";
		budgetInfo += request.getParameter("budgetKind")==null?"":request.getParameter("budgetKind")+",";
		budgetInfo += request.getParameter("feeKind")==null?"":request.getParameter("feeKind")+",";
		budgetInfo += request.getParameter("year")==null?"":request.getParameter("year");
		user.setAttribute("budgetInfo",budgetInfo );
		List<BudgetInfo> l = budgetInfoBO.getSelectedStaff(request,true,user);
		List<BudgetInfoForm> formList = new ArrayList<BudgetInfoForm>();
		for(BudgetInfo p:l){
			BudgetInfoForm form = new BudgetInfoForm();
			copyProperties(form, p);
			formList.add(form);
		}
		if(!CheckUtil.isEmptry(request.getParameter("oper"))){
			writeMessage(makeDisInfo(formList), response);
		}else{
			writeList(request, response, formList);
		}
	}
	private String makeDisInfo(List<BudgetInfoForm> formList) {
		StringBuffer sb = new StringBuffer();
		for(BudgetInfoForm f : formList){
			sb.append("<br>");
		}
		return sb.toString();
	}
	
	
	private ActionForward delete(BaseForm form ,HttpServletRequest request,ActionMapping mapping,HttpServletResponse response)throws Exception {
		if(!budgetDetailBO.queryForAll(((BudgetInfoForm)form).getBudgetId())){
			String s = "{\"msg\":\"不能删除，该预算已有报销\"}";
			writeAjaxResponse(response, s);
			return null;
		}
		budgetInfoBO.delete(((BudgetInfoForm)form).getBudgetId());
		if(!CheckUtil.isEmptry(request.getParameter("oper"))){
			writeSuccessMsg(response);
			return null;
		}else{
			return forwardSuccessPage(request, mapping, "删除成功","BudgetInfo.do?act=list&projectNo="+((BudgetInfoForm)form).getProjectNo()+
					"&depart="+((BudgetInfoForm)form).getDepart()+"&feeKind="+((BudgetInfoForm)form).getFeeKind()+"&budgetKind="+((BudgetInfoForm)form).getBudgetKind());
		}
	}
	

	private ActionForward list(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		BudgetInfo vo = new BudgetInfo();
		setPageResult(request, budgetInfoBO.queryForList(form,user));
		return mapping.findForward("list");
	}
	private ActionForward report(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		BudgetInfo vo = new BudgetInfo();
		List<BudgetInfo> blist = budgetInfoBO.queryForList(form,user);
		setPageResult(request, budgetInfoBO.queryForList(form,user));
		return mapping.findForward("report");
	}
	private ActionForward edit(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		BudgetInfo vo = new BudgetInfo();
		copyProperties(vo, form); 
		budgetInfoBO.update(vo);
		return forwardSuccessPage(request, mapping, "修改成功","BudgetInfo.do?act=list");
	}
	
	private ActionForward editInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		BudgetInfo vo = new BudgetInfo();
		vo = (BudgetInfo)budgetInfoBO.queryForObject(((BudgetInfoForm)form).getBudgetId());
		copyProperties(form,vo); 
		request.setAttribute("projectList",projectMaintainBO.getActiveList(new ProjectMaintainForm(),user));
		return mapping.findForward("new");
	}
	
	private ActionForward newInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		request.setAttribute("projectList",projectMaintainBO.getActiveList(new ProjectMaintainForm(),user));
		return mapping.findForward("new");
	}
	
	private ActionForward viewInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		BudgetInfo vo = new BudgetInfo();
		vo = (BudgetInfo)budgetInfoBO.queryForObject(((BudgetInfoForm)form).getBudgetId());
		copyProperties(form,vo); 
		return mapping.findForward("view");
	}

}
