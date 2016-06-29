package com.blue.report.projectplan;

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

public class ProjectPlanAction extends IbatisBaseAction {

	@Autowired
	private ProjectPlanBO projectPlanBO;
	@Autowired
	private ProjectPlanReport report;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("list".equals(act)){
			return list(mapping,form,request,response);
		}
		if("down".equals(act)){
			return down(mapping,form,request,response);
		}
		return null;
	}

	private ActionForward down(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String projectId = request.getParameter("projectId");
		report.createExcel(projectId, false);
		response.setContentType("application/octet-stream");
		String filename =" 项目计划表-"+projectId+".xls";
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.addHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = response.getOutputStream();
		report.getEt().write(out);
		out.close();
		return null;
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		String projectId = request.getParameter("projectId");
		request.setAttribute("map", projectPlanBO.getStepPlans(projectId));
		request.setAttribute("project", projectPlanBO.getProjectInfo(projectId	));
		return mapping.findForward("list");
	}

}
