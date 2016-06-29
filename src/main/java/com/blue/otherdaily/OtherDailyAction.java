package com.blue.otherdaily;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;

public class OtherDailyAction extends IbatisBaseAction {
	@Autowired
	private OtherDailyBO otherDailyBO;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		
		request.setAttribute("act", act);
		if("init".equals(act)){
			return mapping.findForward("edit");
		}else if("add".equals(act)){
			return add(mapping,form,request,response,user);
		}else if("edit".equals(act)){
			return edit(mapping,form,request,response,user);
		}else if("delete".equals(act)){
			return delete(mapping,request,response,user);
		}else if("list".equals(act)){
			return list(mapping,request,response,user,form);
		}else if("update".equals(act)){
			return update(mapping, form, request, response, user);
		}else if("all".equals(act)){
			return all(mapping,request,response,user,form);
		}else if("qa".equals(act)){
			return query(mapping,request,response,form);
		}else if("product".equals(act)){
			return addProductDaily(mapping,request,response,form);
		}
		return null;
	}

	private ActionForward addProductDaily(ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, BaseForm form) {
		
		return mapping.findForward("product");
	}

	private ActionForward query(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response,
			BaseForm form) throws Exception{
		setPageResult(request, otherDailyBO.query(form));
		return mapping.findForward("query");
	}

	private ActionForward all(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response,
			UserContext user, BaseForm form)throws Exception {
		setPageResult(request, otherDailyBO.queryForAll(null,user));
		request.setAttribute("NOACT", "true");
		return mapping.findForward("list");
	}

	private ActionForward list(ActionMapping mapping,HttpServletRequest request, HttpServletResponse response, UserContext user, BaseForm form)throws Exception {
		setPageResult(request, otherDailyBO.queryForList(form,user));
		return mapping.findForward("list");
	}

	private ActionForward delete(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response,
			UserContext user) throws Exception{
		String id = request.getParameter("id");
		otherDailyBO.delete(id);
		return forwardSuccessPage(request, mapping, "删除成功",getBackUrl(request));
	}

	private ActionForward edit(ActionMapping mapping, BaseForm form,HttpServletRequest request, HttpServletResponse response,
			UserContext user) throws Exception{
		OtherDaily daily = (OtherDaily)otherDailyBO.queryForObject(request.getParameter("id"));
		copyProperties(form, daily);
		request.setAttribute("info", daily.getInfo());
		return mapping.findForward("edit");
	}
	private ActionForward update(ActionMapping mapping, BaseForm form,HttpServletRequest request, HttpServletResponse response,
			UserContext user)throws Exception {
		OtherDaily daily = new OtherDaily();
		copyProperties(daily, form);
		otherDailyBO.update(daily);
		return forwardSuccessPage(request, mapping, "修改成功",getBackUrl(request));
	}
	private ActionForward add(ActionMapping mapping, BaseForm form,HttpServletRequest request, HttpServletResponse response,
			UserContext user)throws Exception {
		OtherDaily daily = new OtherDaily();
		copyProperties(daily, form);
		daily.setInputDate(DateUtil.getDTStr());
		daily.setInputUser(user.getUserID());
		otherDailyBO.insert(daily);
		return forwardSuccessPage(request, mapping, "添加成功",getBackUrl(request));
	}

	private String getBackUrl(HttpServletRequest request) {
		return "OtherDaily.do?act=list";
	}

}
