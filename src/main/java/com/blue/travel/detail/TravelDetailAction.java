package com.blue.travel.detail;



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
import com.blue.travel.TravelInfo;
import com.blue.travel.TravelInfoBO;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;


public class TravelDetailAction extends IbatisBaseAction {
	@Autowired
	private TravelDetailBO travelDetailBO;
	@Autowired
	private TravelInfoBO travelInfoBO;
	@Autowired
	private BudgetDetailDAO budgetDetailDAO;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("list".equalsIgnoreCase(act)){
			return list(form,mapping,request,response,user);
		}else if("c".equalsIgnoreCase(act)){
			return newInfo(form,mapping,request,response,user);
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
			return updateTravelDetail(form,request,mapping,user,response);
		}
		return null;
	}
	
	
	public ActionForward updateTravelDetail(BaseForm form ,HttpServletRequest request,ActionMapping mapping,UserContext user,HttpServletResponse response)throws Exception{
		if(((TravelDetailForm)form).getExpensesId()==null||((TravelDetailForm)form).getExpensesId()==0){
			return null;
		}
		TravelDetail vo = new TravelDetail();
		copyProperties(vo,form);
		try{
			travelDetailBO.updateToUp(vo, form);
			
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
		List<TravelDetail> l = travelDetailBO.getSelectedStaff(request,true,user);
		List<TravelDetailForm> formList = new ArrayList<TravelDetailForm>();
		for(TravelDetail p:l){
			TravelDetailForm form = new TravelDetailForm();
			copyProperties(form, p);
			formList.add(form);
		}
		if(!CheckUtil.isEmptry(request.getParameter("oper"))){
			writeMessage(makeDisInfo(formList), response);
		}else{
			writeList(request, response, formList);
		}
	}
	private String makeDisInfo(List<TravelDetailForm> formList) {
		StringBuffer sb = new StringBuffer();
		for(TravelDetailForm f : formList){
			sb.append("<br>");
		}
		return sb.toString();
	}

	private ActionForward delete(BaseForm form ,HttpServletRequest request,ActionMapping mapping,HttpServletResponse response)throws Exception {
		travelDetailBO.deleteToUp(form);
		
		if(!CheckUtil.isEmptry(request.getParameter("oper"))){
			writeSuccessMsg(response);
			return null;
		}else{
		return forwardSuccessPage(request, mapping, "删除成功","TravelDetail.do?act=list");
		}
	}

	private ActionForward list(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TravelDetail vo = new TravelDetail();
		vo.setExpensesId(Long.parseLong(request.getParameter("expensesId")));
		setPageResult(request, travelDetailBO.queryForList(vo));
		return mapping.findForward("list");
	}


	private ActionForward add(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TravelDetail vo = new TravelDetail();
		copyProperties(vo, form); 
		travelDetailBO.insert(vo);
		return forwardSuccessPage(request, mapping, "添加成功","TravelDetail.do?act=list");
	}
	
	private ActionForward edit(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TravelDetail vo = new TravelDetail();
		copyProperties(vo, form); 
		travelDetailBO.update(vo);
		return forwardSuccessPage(request, mapping, "修改成功","TravelDetail.do?act=list");
	}
	
	private ActionForward editInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TravelDetail vo = new TravelDetail();
		vo = (TravelDetail)travelDetailBO.queryForObject(((TravelDetailForm)form).getTdetailId());
		copyProperties(form,vo); 
		return mapping.findForward("edit");
	}
	
	private ActionForward newInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TravelDetail vo = new TravelDetail();
		vo.setExpensesId((((TravelDetailForm)form).getExpensesId()));
		copyProperties(form,vo); 
		return mapping.findForward("new");
	}
	
	private ActionForward viewInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TravelDetail vo = new TravelDetail();
		setPageResult(request, travelDetailBO.queryForList(vo));
		request.setAttribute("dept", request.getParameter("dept"));
		return mapping.findForward("view");
	}

}
