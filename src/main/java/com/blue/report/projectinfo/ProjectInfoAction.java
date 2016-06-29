package com.blue.report.projectinfo;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;

public class ProjectInfoAction extends IbatisBaseAction {
	@Autowired
	private ProjectInfoReport projectInfoReport;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("list".equals(act)){
			return list(mapping,form,request);
		}else if("down".equals(act)){
			return download(request,response);
		}
		return null;
	}

	private ActionForward download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		projectInfoReport.createExcel(null, false);
		response.setContentType("application/octet-stream");
		String filename = projectInfoReport.getEt().getSheetName()+".xls";
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefoxä¯ÀÀÆ÷
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		projectInfoReport.getEt().write(out);
		out.close();
		return null;
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request) throws Exception{
		setPageResult(request, bo.queryForList(null));
		return mapping.findForward("list");
	}

}
