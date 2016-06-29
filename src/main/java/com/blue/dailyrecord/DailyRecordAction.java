package com.blue.dailyrecord;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.enums.CheckStatus;
import com.blue.enums.Steps;
import com.blue.project.ProjectList;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;
import com.projectmaintain.ProjectMaintainBO;

public class DailyRecordAction extends IbatisBaseAction {
	public static final String PROjECT_KEY = "project";
	@Autowired
	private ProjectMaintainBO projectMaintainBO;
	
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("new".equals(act)){
			return init(form,request,mapping);
		}else if("edit".equals(act)){
			return edit(mapping,form,request,user);
		}else if("add".equals(act)){
			return add(mapping,form,request,user);
		}else if("update".equals(act)){
			return update(mapping,form,request,user);
		}else if("delete".equals(act)){
			return delete(mapping,form,request,user);
		}else if("list".equals(act)){
			return list(mapping,form,request,user);
		}else if("ql".equals(act)){
			return queryAll(mapping,form,request,user);
		}else if("qa".equals(act)){
			return query(mapping,form,request,user);
		}else if("upload".equals(act)){
			return upload(mapping,form,request,user);
		}else if("uploadInit".equals(act)){
			return mapping.findForward("upload");
		}
		if("checklist".equalsIgnoreCase(act)){
			return checkList(mapping,form,request,user);
		}
		if("pass".equals(act)){
			return pass(mapping,form,request,user);
		}
		if("refuse".equals(act)){
			return refuse(mapping,form,request,user);
		}
		if("summary".equals(act)){
			return summary(mapping,form,request,user);
		}
		throw new MessageException("不合法的地址");
	}

	private ActionForward summary(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		request.setAttribute("project", ((DailyRecordBO)bo).sumaryOfProject((DailyRecordForm)form));
		request.setAttribute("type", ((DailyRecordBO)bo).sumaryOfType((DailyRecordForm)form));
		request.setAttribute("checkbox", SingleDicMap.getCheckBox("departIds", SingleDic.DEPART,((DailyRecordForm)form).getDepartIds()));
		return mapping.findForward("summary");
	}

	private ActionForward refuse(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		((DailyRecordBO)bo).refuse(request,user,(DailyRecordForm)form);
		return forwardSuccessPage(request, mapping, "操作成功","DailyRecord.do?act=checklist");
	}

	private ActionForward pass(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		((DailyRecordBO)bo).pass(request,user);
		return forwardSuccessPage(request, mapping, "操作成功","DailyRecord.do?act=checklist");
	}

	/**
	 * 获得待审核列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param user
	 * @return
	 */
	private ActionForward checkList(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		setPageResult(request, ((DailyRecordBO)bo).getCheckList(form));
		request.setAttribute("checkbox", SingleDicMap.getCheckBox("departIds", SingleDic.DEPART,((DailyRecordForm)form).getDepartIds()));
		return mapping.findForward("cl");
	}

	private ActionForward upload(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		
		((DailyRecordBO)bo).upload(form,user);
		return forwardSuccessPage(request, mapping, "上传成功","DailyRecord.do?act=uploadInit");
	}

	private ActionForward query(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		setPageResult(request, ((DailyRecordBO)bo).query(form, user));
		return mapping.findForward("query");
	}

	private ActionForward queryAll(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		setPageResult(request, ((DailyRecordBO)bo).queryForAll(form,user));
		request.setAttribute("NOACT", "NOACT");
		return mapping.findForward("list");
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		setPageResult(request,((DailyRecordBO)bo).queryForList(form,user));
		return mapping.findForward("list");
	}

	private ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		bo.delete(request.getParameter("recordId"));
		return forwardSuccessPage(request, mapping, "删除成功","DailyRecord.do?act=list");
	}

	private ActionForward update(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		((DailyRecordBO)bo).validateWorkDate((DailyRecordForm)form, user);
		DailyRecord record = new DailyRecord();
		copyProperties(record, form);
		if(((DailyRecordBO)bo).needCheck(user)){
			record.setChecked(CheckStatus.N.toString());
			record.setInputCost(record.getTaskCost());
			record.setTaskCost(BigDecimal.ZERO);
		}else{
			record.setChecked(CheckStatus.Y.toString());
		}
		
		bo.update(record);
		return forwardSuccessPage(request, mapping, "修改成功","DailyRecord.do?act=list");
	}

	private ActionForward add(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		DailyRecord record = new DailyRecord();
		((DailyRecordBO)bo).validateWorkDate((DailyRecordForm)form,user);
		copyProperties(record, form);
		record.setInputDate(DateUtil.getDTStr());
		record.setUserId(user.getUserID());
		if(((DailyRecordBO)bo).needCheck(user)){
			record.setChecked(CheckStatus.N.toString());
			record.setInputCost(record.getTaskCost());
			record.setTaskCost(BigDecimal.ZERO);
		}else{
			record.setChecked(CheckStatus.Y.toString());
		}
		bo.insert(record);
		return forwardSuccessPage(request, mapping, "保存成功","DailyRecord.do?act=list");
	}

	private ActionForward edit(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		DailyRecord record = (DailyRecord)bo.queryForObject(request.getParameter("recordId"));
		copyProperties(form, record);
		form.setAct("update");
		ProjectList pl = projectMaintainBO.queryForObject(record.getProjectId());
		request.setAttribute(PROjECT_KEY, pl);
		return mapping.findForward("edit");
	}

	private ActionForward init(BaseForm form,HttpServletRequest request, ActionMapping mapping) throws Exception{
		String projectId = request.getParameter("projectId");
		ProjectList pl = projectMaintainBO.queryForObject(projectId);
//		if(!pl.getStep().equals(request.getParameter("step"))){
//			throw new MessageException("当前项目阶段为"+Steps.valueOf(pl.getStep()).getDesc()+",不可以填写此工时");
//		}
		form.setAct("add");
		request.setAttribute(PROjECT_KEY, pl);
		return mapping.findForward("edit");
	}

	public ProjectMaintainBO getProjectMaintainBO() {
		return projectMaintainBO;
	}

	public void setProjectMaintainBO(ProjectMaintainBO projectMaintainBO) {
		this.projectMaintainBO = projectMaintainBO;
	}

}
