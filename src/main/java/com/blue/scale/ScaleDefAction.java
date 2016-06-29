package com.blue.scale;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.product.ProductDef;
import com.blue.product.ProductDefForm;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;

public class ScaleDefAction extends IbatisBaseAction {
	@Autowired
	private ScaleDefBO bo;
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
		if("getScales".equals(act)){
			getScales(request,response);
			return null;
		}
		if("getActions".equals(act)){
			getActions(request,response);
			return null;
		}
		return null;
	}

	private void getActions(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String step = request.getParameter("step");
		String scaleId = request.getParameter("scaleId");
		if(CheckUtil.isEmptry(scaleId)){
			throw new MessageException("项目缺少项目规模信息，请先修改项目信息");
		}
		Step s = ScaleCache.getStep(step,Integer.parseInt(scaleId));
		if(s ==null){
			return;
		}
		List<Action> l = s.getActions();
		Map<String,Object> m = new HashMap<String,Object>();
		for(Action a : l){
			m.put(String.valueOf(a.getActionDef().getActionId()), a.getActionDef().getName());
		}
		writeAjaxResponse(response, makeOptions(m, request.getParameter("selected")));
	}

	private void getScales(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ScaleDef> l = bo.queryForList(new ScaleDefForm());
		Map<String,Object> m = new HashMap<String, Object>();
		for(ScaleDef v : l){
			m.put(v.getScaleId().toString(), v.getScaleDesc());
		}
		writeAjaxResponse(response, makeOptions(m, request.getParameter("scaleId")));
	}

	private ActionForward add(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		ScaleDef v = new ScaleDef();
		copyProperties(v, form);
		bo.insert(v);
		return forwardSuccessPage(request, mapping, "保存成功",getBackUrl(v));
	}

	private ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		ScaleDef v = bo.queryForObject(request.getParameter("scaleId"));
		bo.delete(v.getScaleId());
		return forwardSuccessPage(request, mapping, "删除成功",getBackUrl(v));
	}

	private ActionForward update(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		ScaleDef v = new ScaleDef();
		copyProperties(v, form);
		bo.update(v);
		return forwardSuccessPage(request, mapping, "修改成功",getBackUrl(v));
	}

	private ActionForward init(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		form.setAct("add");
		return mapping.findForward("edit");
	}

	private ActionForward edit(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		ScaleDef v = bo.queryForObject(request.getParameter("productCode"));
		copyProperties(form, v);
		form.setAct("update");
		return mapping.findForward("edit");
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		setPageResult(request, bo.queryForList(form));
		((ProductDefForm)form).setProductCode(request.getParameter("productCode"));
		return mapping.findForward("list");
	}
	private String getBackUrl(ScaleDef v){
		return "ProductDef.do?act=list";
	}

}
