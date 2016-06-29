package com.blue.userdepartauth;

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

public class UserDepartAuthAction extends IbatisBaseAction {
	@Autowired
	private UserDepartAuthBO authBO;
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
		List<UserDepartAuth> list = new ArrayList<UserDepartAuth>();
		List<String> list2 = new ArrayList<String>();
		for(String s:result){
			UserDepartAuth r = new UserDepartAuth();
			String[] t = s.split("-");
			r.setDepartId(t[0]);
			r.setUserId(t[1]);
			list.add(r);
			list2.add(t[1]);
		}
		authBO.transInsert(list,list2);
		return forwardSuccessPage(request, mapping, "更新成功","UserDepartAuth.do?act=init");
	}

	private ActionForward init(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = "00000109";
		if(request.getParameter("role_id")!=null){
			roleId = (String)request.getParameter("role_id");
		}
		request.setAttribute("table", authBO.getConfigTable(roleId));
		return mapping.findForward("init");
	}
}
