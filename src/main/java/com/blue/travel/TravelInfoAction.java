package com.blue.travel;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blue.budget.detail.BudgetDetail;
import com.blue.budget.detail.BudgetDetailDAO;
import com.blue.budget.detail.BudgetDetailExample;
import com.blue.travel.detail.TravelDetail;
import com.blue.travel.detail.TravelDetailBO;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;
import com.eis.util.SimpleMoneyFormat;


public class TravelInfoAction extends IbatisBaseAction {
	@Autowired
	private TravelInfoBO travelInfoBO;
	@Autowired
	private TravelDetailBO travelDetailBO;
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
		}else if("report".equalsIgnoreCase(act)){
			return report(form,mapping,request,response,user);
		}
		return null;
	}

	private ActionForward delete(BaseForm form, HttpServletRequest request,ActionMapping mapping)throws Exception {
		travelInfoBO.delereToUp(form);
		return forwardSuccessPage(request, mapping, "É¾³ý³É¹¦","TravelInfo.do?act=list");
	}

	private ActionForward list(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		setPageResult(request, travelInfoBO.queryForList(form,user));
		return mapping.findForward("list");
	}
	
	private ActionForward report(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		List<TravelInfo> eilist = travelInfoBO.queryForList(form,user);
		double totAmt=0;
		for(TravelInfo e:eilist){
			totAmt+=e.getTotalAmt();
		}
		request.setAttribute("list", eilist);
		request.setAttribute("totAmt", totAmt);
		return mapping.findForward("report");
	}

	private ActionForward add(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TravelInfo vo = new TravelInfo();
		copyProperties(vo, form);
		travelInfoBO.updateToUp(vo, user);
		copyProperties(form,vo);
		return mapping.findForward("new");
	}
	
	private ActionForward editInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TravelInfo vo = new TravelInfo();
		vo = (TravelInfo)travelInfoBO.queryForObject(((TravelInfoForm)form).getExpensesId());
		copyProperties(form,vo); 
		return mapping.findForward("new");
	}
	
	private ActionForward viewInfo(BaseForm form, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception{
		TravelInfo vo = (TravelInfo)travelInfoBO.queryForObject(((TravelInfoForm)form).getExpensesId());
		TravelDetail td = new TravelDetail();
		td.setExpensesId(((TravelInfoForm)form).getExpensesId());
		setPageResult(request, travelDetailBO.queryForList(td));
		request.setAttribute("chineseAmt",SimpleMoneyFormat.getInstance().format(vo.getTotalAmt()));
		copyProperties(form,vo);
		return mapping.findForward("view");
	}
	
//	private  List JSON2Map(String jsonMapStr,Long expensesId) {
//		List list = new ArrayList();
//	    JSONObject jsonMap = JSONObject.fromObject(jsonMapStr);  
//	    TreeMap treemap = new TreeMap(jsonMap);  
//	    int keyMax =Integer.parseInt(treemap.lastKey().toString().replaceAll("[a-zA-Z]", ""));
//	    for(int i=1;i<=keyMax;i++){
//	    	if(jsonMap.get("txnamt"+i)!=null&&Double.parseDouble((String)jsonMap.get("txnamt"+i))>0){
//		    	TravelDetail td = new TravelDetail();
//		    	td.setTravelFrom((String)jsonMap.get("txnfrom"+i));
//		    	td.setTravelTo((String)jsonMap.get("txnto"+i));
//		    	td.setTransportMode((String)jsonMap.get("txntransport"+i)); 
//		    	td.setInvoiceno(Integer.parseInt((String)jsonMap.get("txninvoice"+i)));
//		    	td.setFee(Double.parseDouble((String)jsonMap.get("txnamt"+i)));
//		    	td.setExpensesId(expensesId);
//		    	list.add(td);
//	    	}
//	    }
//	    return list;  
//	}  

}
