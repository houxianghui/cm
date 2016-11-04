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
import com.yly.issue.MWsIssueBO;
public class InfoAction extends IbatisBaseAction {
	@Autowired
	private Ep_noticeBO noticeBO;
	@Autowired
	private ProjectDistributeBO distributeBO;
	@Autowired
	private DailyRecordBO recordBO;
	@Autowired
	private MailConfigBO configBO;
	@Autowired	
	private MWsIssueBO mwsissuetbBO;
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
		request.setAttribute("task", mwsissuetbBO.getMyIssueTask(user.getUserID()));
		return mapping.findForward("info");
	}

}
