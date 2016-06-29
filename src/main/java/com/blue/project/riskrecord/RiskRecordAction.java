package com.blue.project.riskrecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;

public class RiskRecordAction extends IbatisBaseAction {
	private final String BACK = getClass().getName();
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if(act.equals("init")){
			request.setAttribute("act", act);
			request.setAttribute("projectId", request.getParameter("projectId"));
			return mapping.findForward("edit");
		}
		if(act.equals("edit")){
			request.setAttribute("act", act);
			return edit(request, form, mapping);
		}
		if(act.equals("update")){
			return update(form,mapping,request,user);
		}
		if(act.equals("add")){
			return add(form,mapping,user,request);
		}
		if(act.equals("list")){
			request.getSession().setAttribute(BACK, act);
			return list(form,mapping,request);
		}
		if(act.equals("delete")){
			return delete(form,mapping,request);
		}
		if(act.equals("all")){
			request.getSession().setAttribute(BACK, act);
			return all(form,mapping,request);
		}
		return null;
	}

	private ActionForward all(BaseForm form, ActionMapping mapping, HttpServletRequest request)throws Exception {
		setPageResult(request, bo.queryForList(null));
		return mapping.findForward("all");
	}

	private ActionForward delete(BaseForm form, ActionMapping mapping,
			HttpServletRequest request)throws Exception {
		bo.delete(request.getParameter("id"));
		return forwardSuccessPage(request, mapping, "删除成功",getListUrl(request));
	}

	private ActionForward list(BaseForm form, ActionMapping mapping,HttpServletRequest request) throws Exception{
		request.setAttribute("projectId", request.getParameter("projectId"));
		setPageResult(request, bo.queryForList(form));
		return mapping.findForward("list");
	}

	private ActionForward add(BaseForm form, ActionMapping mapping,
			UserContext user,HttpServletRequest request) throws Exception{
		RiskRecord r = new RiskRecord();
		copyProperties(r, form);
		r.setInputDate(DateUtil.getDTStr());
		r.setUserId(user.getUserID());
		int id = ((RiskRecordBO)bo).getMax(r.getProjectId());
		r.setRiskId(id);
		bo.insert(r);
		return forwardSuccessPage(request, mapping, "增加成功",getListUrl(request));
	}

	private ActionForward update(BaseForm form, ActionMapping mapping,
			HttpServletRequest request, UserContext user) throws Exception{
		RiskRecord r = new RiskRecord();
		copyProperties(r, form);
		((RiskRecordBO)bo).update(r,user);
		return forwardSuccessPage(request, mapping, "修改成功",getListUrl(request));
	}

	private ActionForward edit(HttpServletRequest request,BaseForm form,ActionMapping mapping) throws Exception{
		RiskRecord r = (RiskRecord)bo.queryForObject(request.getParameter("id"));
		copyProperties(form, r);
		
		return mapping.findForward("edit");
	}
	private String getListUrl(HttpServletRequest request){
		StringBuffer sb = new StringBuffer("RiskRecord.do?act="+request.getSession().getAttribute(BACK));
		String id = request.getParameter("projectId");
		if(!CheckUtil.isEmptry(id)){
			sb.append("&projectId="+id);
		}
		return sb.toString();
	}

}
