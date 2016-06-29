package com.blue.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.version.subsysversion.SubSysVersion;
import com.blue.version.updatestep.UpdateStepForm;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;

public class ProductDefAction extends IbatisBaseAction {
	@Autowired
	private ProductDefBO bo;
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
		if("getProducts".equals(act)){
			getProducts(request,response);
		}
		if("getCheckbox".equals(act)){
			getProductsCheckbox(request, response);
		}
		return null;
	}

	private void getProducts(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ProductDef> l = bo.queryForList(new ProductDefForm());
		Map<String,Object> m = new HashMap<String, Object>();
		for(ProductDef v : l){
			m.put(v.getProductCode(), v.getName());
		}
		writeAjaxResponse(response, makeOptions(m, request.getParameter("productCode")));
	}
	
	private void getProductsCheckbox(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ProductDef> l = bo.queryForList(new ProductDefForm());
		Map<String,Object> m = new HashMap<String, Object>();
		for(ProductDef v : l){
			m.put(v.getProductCode(), v.getName());
		}
		String selected = request.getParameter("selected");
		Map<String,String> selectedMap = new HashMap<String,String>();
		if(selected != null){
			String[] keys = selected.split(",");
			for(String s:keys){
				selectedMap.put(s, s);
			}
		}
		writeAjaxResponse(response, makeCheckbox(m, selectedMap,"productIds"));
	}
	
	private ActionForward add(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		ProductDef v = new ProductDef();
		copyProperties(v, form);
		bo.insert(v);
		return forwardSuccessPage(request, mapping, "保存成功",getBackUrl(v));
	}

	private ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		ProductDef v = bo.queryForObject(request.getParameter("productCode"));
		bo.delete(v.getProductCode());
		return forwardSuccessPage(request, mapping, "删除成功",getBackUrl(v));
	}

	private ActionForward update(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		ProductDef v = new ProductDef();
		copyProperties(v, form);
		bo.update(v);
		return forwardSuccessPage(request, mapping, "修改成功",getBackUrl(v));
	}

	private ActionForward init(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		form.setAct("add");
		return mapping.findForward("edit");
	}

	private ActionForward edit(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		ProductDef v = bo.queryForObject(request.getParameter("productCode"));
		copyProperties(form, v);
		form.setAct("update");
		return mapping.findForward("edit");
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		setPageResult(request, bo.queryForListVO(form));
		((ProductDefForm)form).setProductCode(request.getParameter("productCode"));
		return mapping.findForward("list");
	}
	private String getBackUrl(ProductDef v){
		return "ProductDef.do?act=list&productCode="+v.getProductCode();
	}

}
