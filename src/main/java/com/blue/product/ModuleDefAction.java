package com.blue.product;

import java.util.ArrayList;
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
import com.eis.util.CheckUtil;

public class ModuleDefAction extends IbatisBaseAction {
	@Autowired
	private ModuleDefBO bo;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("list".equals(act)){
			return list(mapping,form,request,user);
		}
		if("edit".equals(act)){
			return edit(mapping,form,request,user);
		}
		if("new".equals(act)){
			return init(mapping,form,request,user);
		}
		if("add".equals(act)){
			return add(mapping,form,request,user);
		}
		if("update".equals(act)){
			return update(mapping,form,request,user);
		}
		if("delete".equals(act)){
			return delete(mapping,form,request,user);
		}
		if("getModuleSelect".equals(act)){
			getModuleSelect(request,response);
			return null;
		}
		if("getModuleOption".equals(act)){
			getModuleOption(request,response);
			return null;
		}
		if("getMyModule".equals(act)){
			getMyModule(request,response,user);
			return null;
		}
		return null;
	}

	private void getMyModule(HttpServletRequest request, HttpServletResponse response, UserContext user)throws Exception {
		List<ModuleDef> l = bo.queryForMyModule(user.getUserID());
		Map<String, Object> map = new HashMap<String, Object>();
		for(ModuleDef m : l){
			map.put(m.getModuleId().toString(),m.getName());
		}
		writeAjaxResponse(response, makeOptions(map, request.getParameter("selected")));
		
	}

	private void getModuleOption(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ModuleDef> l = bo.queryForAllModule();
		String selected = request.getParameter("selected");
		Map<String, Object> map = new HashMap<String, Object>();
		for(ModuleDef m : l){
			map.put(m.getModuleId(),m.getName());
		}
		writeAjaxResponse(response, makeOptions(map,selected));
		
	}

	private void getModuleSelect(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ModuleDef> l = bo.queryForAllModule();
		Map<String, Object> map = new HashMap<String, Object>();
		for(ModuleDef m : l){
			map.put(m.getModuleId().toString(),m.getName());
		}
		writeAjaxResponse(response, makeSelect(map));
	}

	private ActionForward add(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		ModuleDef v = new ModuleDef();
		copyProperties(v, form);
		List<ParticipateList> l = new ArrayList<ParticipateList>();
		String[] participates = request.getParameterValues("users");
		if(participates != null){
			for(String s : participates){
				ParticipateList p = new ParticipateList();
				l.add(p);
				p.setModuleId(v.getModuleId());
				p.setUserId(s);
			}
		}
		bo.insert(v,l);
		return forwardSuccessPage(request, mapping, "保存成功",getBackUrl(v));
	}

	private ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		ModuleDef v = bo.queryForObject(request.getParameter("moduleId"));
		bo.delete(request.getParameter("moduleId"));
		return forwardSuccessPage(request, mapping, "删除成功",getBackUrl(v));
	}

	private ActionForward update(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		ModuleDef v = new ModuleDef();
		copyProperties(v, form);
		List<ParticipateList> l = new ArrayList<ParticipateList>();
		String[] participates = request.getParameterValues("users");
		if(participates != null){
			for(String s : participates){
				ParticipateList p = new ParticipateList();
				l.add(p);
				p.setModuleId(v.getModuleId());
				p.setUserId(s);
			}
		}
		bo.update(v,l);
		return forwardSuccessPage(request, mapping, "修改成功",getBackUrl(v));
	}

	private ActionForward init(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		form.setAct("add");
		((ModuleDefForm)form).setProductCode(request.getParameter("productCode"));
		return mapping.findForward("edit");
	}

	private ActionForward edit(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		ModuleDef v = bo.queryForObject(request.getParameter("moduleId"));
		copyProperties(form, v);
		request.setAttribute("selected",bo.getParticipates(request.getParameter("moduleId")));
		form.setAct("update");
		return mapping.findForward("edit");
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		setPageResult(request, bo.queryForListVO(((ModuleDefForm)form).getProductCode()));
		((ModuleDefForm)form).setProductCode(request.getParameter("productCode"));
		return mapping.findForward("list");
	}
	private String getBackUrl(ModuleDef v){
		return "ModuleDef.do?act=list&productCode="+v.getProductCode();
	}

}
