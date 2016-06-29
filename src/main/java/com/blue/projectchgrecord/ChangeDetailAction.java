package com.blue.projectchgrecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;

public class ChangeDetailAction extends IbatisBaseAction {

	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if(act.equals("init")){
			request.setAttribute("act", act);
			request.setAttribute("id", request.getParameter("id"));
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
			return list(form,mapping,request);
		}
		if(act.equals("delete")){
			return delete(form,mapping,request);
		}
		return null;
	}

	private ActionForward delete(BaseForm form, ActionMapping mapping,
			HttpServletRequest request)throws Exception {
		bo.delete(request.getParameter("detailId"));
		return forwardSuccessPage(request, mapping, "删除成功",getListUrl(request.getParameter("id")));
	}

	private ActionForward list(BaseForm form, ActionMapping mapping,HttpServletRequest request) throws Exception{
		setPageResult(request, bo.queryForList(form));
		return mapping.findForward("list");
	}

	private ActionForward add(BaseForm form, ActionMapping mapping,
			UserContext user,HttpServletRequest request) throws Exception{
		ChangeDetail chg = new ChangeDetail();
		copyProperties(chg, form);
		bo.insert(chg);
		return forwardSuccessPage(request, mapping, "增加成功",getListUrl(request.getParameter("id")));
	}

	private ActionForward update(BaseForm form, ActionMapping mapping,
			HttpServletRequest request, UserContext user) throws Exception{
		ChangeDetail chg = new ChangeDetail();
		copyProperties(chg, form);
		bo.update(chg);
		return forwardSuccessPage(request, mapping, "修改成功",getListUrl(request.getParameter("id")));
	}

	private ActionForward edit(HttpServletRequest request,BaseForm form,ActionMapping mapping) throws Exception{
		ChangeDetail chg = (ChangeDetail)bo.queryForObject(request.getParameter("id"));
		copyProperties(form, chg);
		
		return mapping.findForward("edit");
	}
	private String getListUrl(String id){
		return "ChangeDetail.do?act=list&id="+id;
	}

}
