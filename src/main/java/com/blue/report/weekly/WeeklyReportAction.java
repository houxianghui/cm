package com.blue.report.weekly;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.project.riskrecord.RiskRecordBO;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;
import com.eis.util.ValidateUtil;

public class WeeklyReportAction extends IbatisBaseAction {
	@Autowired
	private WeeklyReport weeklyReport;
	@Autowired
	private WeeklyReportBO reportBO;
	@Autowired
	private RiskRecordBO riskRecordBO;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("down".equals(act)){
			return down(request,response,form,user); 
		}
		if("list".equals(act)){
			return list(request,response,mapping,form,user);
		}
		return null;
	}

	private ActionForward list(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping,BaseForm form, UserContext user)throws Exception {
		WeeklyReportForm f = (WeeklyReportForm)form;
		if(f.getDate() == null){
			f.setDate(DateUtil.getDTStr());
		}
		request.setAttribute("startDate", DateUtil.getMondayStr(f.getDate()));
		request.setAttribute("endDate", DateUtil.getSundayStr(f.getDate()));
		request.setAttribute("risk", riskRecordBO.queryForListByDeparts(f.getIds()));
		request.setAttribute("plan", reportBO.getPlan(f));
		request.setAttribute("report", reportBO.getReport(f));
		request.setAttribute("checkbox", SingleDicMap.getCheckBox("ids", SingleDic.DEPART,f.getIds()));
		return mapping.findForward("list");
	}

	private ActionForward down(HttpServletRequest request,HttpServletResponse response,  BaseForm form,UserContext user) throws Exception{
		String date = request.getParameter("date");
		ValidateUtil.rejectIfEmpty(date, "导出周");
		weeklyReport.createExcel(form, false);
		response.setContentType("application/octet-stream");
		String filename = "贷记卡核心系统研发周报-("+DateUtil.getMondayStr(date)+"-"+DateUtil.getSundayStr(date)+").xls";
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		weeklyReport.getEt().write(out);
		out.close();
		return null;
	}

}
