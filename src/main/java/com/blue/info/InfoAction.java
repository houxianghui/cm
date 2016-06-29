package com.blue.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blue.dailyrecord.DailyRecordBO;
import com.blue.mailconfig.MailConfigBO;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.huateng.blue.notice.Ep_noticeBO;
import com.projectmaintain.ProjectDistributeBO;
public class InfoAction extends IbatisBaseAction {
	@Autowired
	private Ep_noticeBO noticeBO;
	@Autowired
	private ProjectDistributeBO distributeBO;
	@Autowired
	private DailyRecordBO recordBO;
	@Autowired
	private MailConfigBO configBO;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if(act.equals("info")){
			return info(request,response,mapping,user);
		}
		return null;
	}

	private ActionForward info(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping,UserContext user) throws Exception{
		request.setAttribute("notice", noticeBO.getNotice("1".equals(request.getParameter("flag"))));
		request.setAttribute("task", distributeBO.getMyProjects(user.getUserID()).size());
		request.setAttribute("report", recordBO.queryForYestodayRecord().size());
//		request.setAttribute("info", configBO.getConfigTable());
		return mapping.findForward("info");
	}

}
