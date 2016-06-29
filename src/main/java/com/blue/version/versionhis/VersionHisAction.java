package com.blue.version.versionhis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.enums.DetailType;
import com.blue.enums.YesOrNo;
import com.blue.mailconfig.MailConfigBO;
import com.blue.project.ProjectList;
import com.blue.projectfiles.ProjectFilesBO;
import com.blue.version.subsysversion.SubSysVersion;
import com.blue.version.subsysversion.SubSysVersionBO;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.MailSender;
import com.projectmaintain.ProjectMaintainBO;

public class VersionHisAction extends IbatisBaseAction {
	@Autowired
	private VersionHisBO versionHisBO;
	@Autowired
	private VersionHisValidator validator;
	@Autowired
	private ProjectMaintainBO projectMaintainBO;
	@Autowired
	private SubSysVersionBO subSysVersionBO;
	@Autowired
	private ProjectFilesBO projectFilesBO;
	@Autowired
	private MailConfigBO mailConfigBO;
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("list".equals(act)){
			return list(mapping,form,request,user);
		}
		if("edit".equals(act)){
			return edit(mapping,form,request,user);
		}
		if("new".equals(act)){
			return init(mapping,form,request,user);
		}
		if("add".equals(act)){
			return add(mapping,form,request,user);
		}
		if("update".equals(act)){
			return update(mapping,form,request,user);
		}
		if("delete".equals(act)){
			return delete(mapping,form,request,user);
		}
		if("sub".equals(act)){
			return maintainSubSys(mapping,form,request,user);
		}
		if("tabs".equals(act)){
			return tabs(mapping,form,request,user);
		}
		if("view".equals(act)){
			return view(mapping,form,request,user);
		}
		if("getOpenVersion".equals(act)){
			getOpenVersion(request,response);
		}
		if("release".equals(act)){
			return release(mapping,form,request,user);
		}
		if("viewDoc".equals(act)){
			return viewDoc(mapping,form,request,user);
		}
		if("display".equals(act)){
			return display(mapping,form,request,user);
		}
		return null;
	}

	private ActionForward display(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		request.setAttribute("list", versionHisBO.getDisplay());
		request.setAttribute("subSys", subSysVersionBO.getAllSubSysList());
		return mapping.findForward("display");
	}

	private ActionForward viewDoc(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		String versionId = request.getParameter("versionId");
		List<ProjectList> projects = projectMaintainBO.getProjectsByVersionId(versionId);
		setPageResult(request, projectFilesBO.getDocsOfVersion(projects,versionId));
		return mapping.findForward("doc");
	}

	private ActionForward release(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		String versionId = request.getParameter("versionId");
		VersionHis v = versionHisBO.queryForObject(versionId);
		validator.isReleasable(v, user);
		v.setIsReleased(YesOrNo.Y.toString());
		v.setReleaseDate(DateUtil.getDTStr());
		versionHisBO.update(v);
		String[] to = mailConfigBO.getNotifyUsers(versionHisBO, null);
		MailSender.sendMail("版本发布通知", "版本"+v.getVersionId()+"已发布，请登录系统查看", to);
		return forwardSuccessPage(request, mapping, "发布成功",getBackUrl());
	}

	private void getOpenVersion(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String selected = request.getParameter("versionId");
		List<VersionHis> l = versionHisBO.getOpenVersion();
		Map<String,Object> m = new HashMap<String, Object>();
		for(VersionHis v : l){
			m.put(v.getVersionId(), v.getVersionId());
		}
		writeAjaxResponse(response, makeOptions(m, selected));
	}

	private ActionForward view(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		String versionId = request.getParameter("versionId");
		VersionHis v = versionHisBO.queryForObject(versionId);
		List<ProjectList> projects = projectMaintainBO.getProjectsByVersionId(versionId);
		List<String> l = new ArrayList<String>(projects.size());
		for(ProjectList p : projects){
			l.add(p.getProjectId());
		}
		List<SubSysVersion> subSys = subSysVersionBO.queryForListByVersionId(versionId);
		request.setAttribute("v", v);
		request.setAttribute("p", l);
		request.setAttribute("s", subSys);
		return mapping.findForward("view");
	}

	private ActionForward tabs(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		request.setAttribute("versionId", request.getParameter("versionId"));
		String type = request.getParameter("type");
		if(!CheckUtil.isEmptry(type)){
			DetailType dt = DetailType.valueOf(type);
			request.setAttribute("tabId", dt.getIndex());
		}
		return mapping.findForward("tabs");
	}

	private ActionForward maintainSubSys(ActionMapping mapping, BaseForm form, HttpServletRequest request,
			UserContext user) {
		return null;
	}

	private ActionForward add(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		VersionHis v = new VersionHis();
		copyProperties(v, form);
		v.setInputUser(user.getUserID());
		v.setInputDate(DateUtil.getDTStr());
		v.setIsReleased(YesOrNo.N.toString());
		versionHisBO.insert(v);
		return forwardSuccessPage(request, mapping, "保存成功",getBackUrl());
	}

	private ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		VersionHis v = versionHisBO.queryForObject(request.getParameter("versionId"));
		validator.isDeletable(v, user);
		versionHisBO.delete(v.getVersionId());
		return forwardSuccessPage(request, mapping, "删除成功",getBackUrl());
	}

	private ActionForward update(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		VersionHis v = new VersionHis();
		copyProperties(v, form);
		versionHisBO.update(v);
		return forwardSuccessPage(request, mapping, "修改成功",getBackUrl());
	}

	private ActionForward init(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) {
		form.setAct("add");
		return mapping.findForward("edit");
	}

	private ActionForward edit(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user)throws Exception {
		VersionHis v = versionHisBO.queryForObject(request.getParameter("versionId"));
		validator.isEditable(v, user);
		copyProperties(form, v);
		form.setAct("update");
		return mapping.findForward("edit");
	}

	private ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request, UserContext user) throws Exception{
		setPageResult(request, versionHisBO.getVersionHisVOList((VersionHisForm)form));
		return mapping.findForward("list");
	}
	private String getBackUrl(){
		return "VersionHis.do?act=list";
	}

}
