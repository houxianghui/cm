package com.blue.mailconfig;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;

public class MailConfigAction extends IbatisBaseAction {

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
		List<MailConfig> list = new ArrayList<MailConfig>();
		for(String s:result){
			MailConfig mc = new MailConfig();
			String[] t = s.split("-");
			mc.setFunctionId(Integer.parseInt(t[0]));
			mc.setRoleId(t[1]);
			list.add(mc);
		}
		((MailConfigBO)bo).transInsert(list);
		return forwardSuccessPage(request, mapping, "更新成功","MailConfig.do?act=init");
	}

	private ActionForward init(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("table", ((MailConfigBO)bo).getConfigTable());
		return mapping.findForward("init");
	}

}
