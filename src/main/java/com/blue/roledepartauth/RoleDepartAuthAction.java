package com.blue.roledepartauth;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;

public class RoleDepartAuthAction extends IbatisBaseAction {
	@Autowired
	private RoleDepartAuthBO authBO;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("init".equals(act)){
			return init(mapping,request,response);
		}else if("update".equals(act)){
			return update(mapping,request,response);
		}
		return null;
	}

	private ActionForward update(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) {
		String ids = (String)request.getParameter("ids");
		String[] result = ids.split(",");
		List<RoleDepartAuth> list = new ArrayList<RoleDepartAuth>();
		for(String s:result){
			RoleDepartAuth r = new RoleDepartAuth();
			String[] t = s.split("-");
			r.setDepartId(t[0]);
			r.setRoleId(t[1]);
			list.add(r);
		}
		authBO.transInsert(list);
		return forwardSuccessPage(request, mapping, "更新成功","RoleDepartAuth.do?act=init");
	}

	private ActionForward init(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("table", authBO.getConfigTable());
		return mapping.findForward("init");
	}
}
