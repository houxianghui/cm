package com.blue.report.costoftype;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;

public class WorkLoadAction extends IbatisBaseAction {
	@Autowired
	private WorkLoadBO workLoadBO;
	@Autowired
	private WorkLoadReport report;
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
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("form", form);
		report.createExcel(param, false);
		response.setContentType("application/octet-stream");
		String filename = "工时统计报表-("+DateUtil.format(startDate)+"-"+DateUtil.format(endDate)+").xls";
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
		request.setAttribute("map", workLoadBO.getWorkLoadByType((WorkLoadForm)form));
		request.setAttribute("checkbox", SingleDicMap.getCheckBox("ids", SingleDic.DEPART,((WorkLoadForm)form).getIds()));
		return mapping.findForward("list");
	}

}
