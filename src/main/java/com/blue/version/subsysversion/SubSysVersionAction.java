package com.blue.version.subsysversion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;

public class SubSysVersionAction extends IbatisBaseAction {
	@Autowired
	private SubSysVersionBO bo;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("list".equals(act)){
			return list(mapping,form,request,user);
		}
		if("new".equals(act)){
			return init(mapping,form,request,user);
		}
		if("add".equals(act)){
			return add(mapping,form,request,user);
		}
		if("edit".equals(act)){
			return edit(mapping,form,request,user);
		}
		if("update".equals(act)){
			return update(mapping,form,request,user);
		}
		if("delete".equals(act)){
			return delete(mapping,form,request,user);
		}
		if("getPreVersion".equals(act)){
			getPreVersions(request,response);
		}
		return null;
	}
	

	private void getPreVersions(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SubSysVersion> l = bo.queryForListBySysName(request.getParameter("sysName"));
		Map<String,Object> m = new HashMap<String, Object>();
		for(SubSysVersion v : l){
			m.put(v.getNextVersion(), v.getNextVersion());
		}
		writeAjaxResponse(response, makeOptions(m, request.getParameter("preVersion")));
	}


	private ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		SubSysVersion v = bo.queryForObject(request.getParameter("id"));
		bo.delete(v.getId());
		return forwardSuccessPage(request, mapping, "删除成功",getBackUrl(v));
	}

	private String getBackUrl(SubSysVersion v) {
		return "SubSysVersion.do?act=list&versionId="+v.getVersionId();
	}

	private ActionForward update(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		SubSysVersion v = new SubSysVersion();
		copyProperties(v, form);
		bo.update(v);
		return forwardSuccessPage(request, mapping, "修改成功",getBackUrl(v));
	}

	private ActionForward edit(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		SubSysVersion v = bo.queryForObject(request.getParameter("id"));
		copyProperties(form, v);
		form.setAct("update");
		request.setAttribute("preVersion", v.getPreVersion());
		return mapping.findForward("edit");
	}

	private ActionForward add(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		SubSysVersion v = new SubSysVersion();
		copyProperties(v, form);
		v.setId(null);
		bo.insert(v);
		return forwardSuccessPage(request, mapping, "增加成功",getBackUrl(v));
	}

	private ActionForward init(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		((SubSysVersionForm)form).setVersionId(request.getParameter("versionId"));
		form.setAct("add");
		return mapping.findForward("edit");
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		setPageResult(request, bo.queryForList(form));
		((SubSysVersionForm)form).setVersionId(request.getParameter("versionId"));
		return mapping.findForward("list");
	}

}
