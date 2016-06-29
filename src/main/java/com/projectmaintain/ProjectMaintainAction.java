/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.blue.enums.ProjectType;
import com.blue.enums.Status;
import com.blue.enums.YesOrNo;
import com.blue.project.ProjectList;
import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.ValidateUtil;
import com.ibm.icu.text.DecimalFormat;


public class ProjectMaintainAction extends IbatisBaseAction {
	private ProjectValidator validator;
	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("ap".equals(act)){		//add project
			return addProject(form,mapping,request,user);
		}
		if("up".equals(act)){		//update project
			return updateProject(form,mapping,request,user);
		}
		if("ep".equals(act)){		//edit project
			return initEdit(form,mapping,request);
		}
		if("dp".equals(act)){		//delete project
			return deleteProject(form,mapping,request,user);
		}
		if("qp".equals(act)){		//query project
			return queryProject(form,mapping,request);
		}
		if("qpl".equals(act)){		//query active projects
			return queryProjectList(form,mapping,request,user);
		}
		if("qal".equals(act)){		//query all projects
			return queryAllProjectList(form,mapping,request,user);
		}
		if("epp".equals(act)){		//edit preprojects
			return editPreProjects(form,mapping,request);
		}
		if("upp".equals(act)){		//update preprojects
			return updatePreProjects(form,mapping,request);
		}
		if("gpp".equals(act)){		//get preprojects
			return getPreProjects(form,mapping,request);
		}
		if("gcp".equals(act)){		//get conflict projects
			return getConflictProjects(form,mapping,request);
		}
		if("gc".equals(act)){
			return getChangeList(mapping,request,form);
		}
		if("gmip".equals(act)){
			return getMyInputProjects(form,mapping,request,user);
		}
		if("d".equals(act)){
			getSubMenu(request,response);
			return null;
		}
		if("finish".equals(act)){
			return finish(request,response,mapping,user);
		}
		if("findProjects".equals(act)){
			return findActiveProjects(request,response,user);
		}
		if("getSubSysOptions".equals(act)){
			getSubSysOptions(request,response);
		}
		if("getProName".equals(act)){
			getProName(request,response);
			return null;
		}
		if(act.equals("makeProjectSelect")){
			makeProjectSelect(request,response,user);
			return null;
		}
		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	private void getSubSysOptions(HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Map<String,String>> m = SingleDicMap.getDicMap(SingleDic.SUB_SYS);
		Map<String,Object> map = new HashMap<String,Object>();
		for(String s : m.keySet()){
			map.put(s, m.get(s).get("ITEM_VAL"));
		}
		String selecteds[] = request.getParameter("selected").split(",");
		Map<String,String> selected = new HashMap<String,String>();
		
		if(selecteds != null){
			for(String t:selecteds){
				selected.put(t,t);
			}
		}
		writeAjaxResponse(response, makeCheckbox(map, selected, "subSys"));
	}

	private ActionForward findActiveProjects(HttpServletRequest request, HttpServletResponse response,UserContext user) throws Exception{
		List l = ((ProjectMaintainBO)bo).getActiveList(new ProjectMaintainForm(),user);
		JSONArray array = JSONArray.fromObject(l);
		PrintWriter pw = response.getWriter();
		pw.write(array.toString());
		pw.flush();
		pw.close();
		return null;
	}
	
	private void makeProjectSelect(HttpServletRequest request, HttpServletResponse response,UserContext user) throws Exception{
		List<ProjectList> l = ((ProjectMaintainBO)bo).getActiveList(new ProjectMaintainForm(),user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("", "部门预算");
		for(ProjectList m : l){
			map.put(m.getProjectId().toString(),m.getProjectName());
		}
		writeAjaxResponse(response, makeSelect(map));
	}

	private ActionForward finish(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping,
			UserContext user) throws Exception{
		ProjectList pl = new ProjectList();
		pl.setProjectId(request.getParameter("projectId"));
		pl.setStat(Status.F.toString());
		pl.setRealEndDate(DateUtil.getDTStr());
		((ProjectMaintainBO)bo).update(pl);
		return forwardSuccessPage(request, mapping, "更新成功","ProjectMaintain.do?act=qpl");
	}

	private void getSubMenu(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		String type = null;
		HashMap map = null;
		String projectClass = request.getParameter("projectClass").trim();
		if(ProjectMaintainForm.V_PLUS.equals(projectClass)){
			map = SingleDicMap.getDicMap(ProjectMaintainForm.V_PLUS_COLLECTION);
			type = ProjectMaintainForm.V_PLUS_COLLECTION;
		}else if(ProjectMaintainForm.EIS.equals(projectClass)){
			map = SingleDicMap.getDicMap(ProjectMaintainForm.EIS_COLLECTION);
			type = ProjectMaintainForm.EIS_COLLECTION;
		}
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"gbk\"?><options>");
		if(map != null){
			processValues(sb,map,type);
		}else{
			sb.append("<option><key>&nbsp;</key><value>&nbsp;</value><option>");
		}
		
		sb.append("</options>");
		
		response.setContentType("text/html;charset=gbk");
		PrintWriter pw = response.getWriter();
		response.setContentType("text/xml");
		response.setLocale(Locale.CHINESE);
		
		pw.write(sb.toString());
		pw.close();
	}
	private void processValues(StringBuffer sb,HashMap map,String type){
		Set set = map.keySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			sb.append("<option>");
			setKey(key,sb);
			String value = SingleDicMap.getDicItemVal(type,key);
			setValue(value,sb);
			sb.append("</option>");
		}
	}
	private void setKey(String key,StringBuffer sb){
		sb.append("<key>");
		sb.append(key);
		sb.append("</key>");
	}
	private void setValue(String value,StringBuffer sb){
		sb.append("<value>");
		sb.append(value);
		sb.append("</value>");
	}
	private ActionForward getChangeList(ActionMapping mapping,HttpServletRequest request,BaseForm form)throws Exception{
		String projectId = request.getParameter("projectId");
		String id = request.getParameter("id");
		if(CheckUtil.isEmptry(id)){
			id = "-1";
		}
		((ProjectMaintainForm)form).setId(Integer.parseInt(id));
		setPageResult(request,((ProjectMaintainBO)bo).getChangeRecord(projectId,id));
		return mapping.findForward("changelist");
	}
	public ActionForward initEdit(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		ProjectList vo = (ProjectList)bo.queryForObject(request.getParameter("projectId"));
		copyProperties(form,vo);
		return mapping.findForward("edit");
	}
	public ActionForward addProject(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		ProjectList vo = new ProjectList();
		copyProperties(vo,form);
		validator.validate(vo);
		vo.setProductIds(getProductIds(request));
		vo.setProjectId(generateProjectId(vo));
		vo.setUserId(user.getUserID());
		vo.setStat(Status.U.toString());
		String[] subSys = request.getParameterValues("subSys");
		StringBuffer sb = new StringBuffer();
		if(subSys != null){
			for(String t:subSys){
				sb.append(t);
				sb.append(",");
			}
		}
		vo.setSubSys(sb.toString());
		((ProjectMaintainBO)bo).insert(vo,user);
		if("0".equals(vo.getMainPlanDis())){//不是由项目经理完成分配，则进入主计划分配页面
			return forwardSuccessPage(request,mapping,"请完成主计划分配",getBackUrl(vo));
		}else{
			return forwardSuccessPage(request,mapping,"保存成功","ProjectMaintain.do?act=qpl");
		}
	}

	/**
	 * @param vo
	 * @return
	 * /ProjectDistribute.do?act=dl&&projectId=P13008&status=U
	 */
	private String getBackUrl(ProjectList vo) {
		return "ProjectDistribute.do?act=dl&projectId="+vo.getProjectId()+"&status="+vo.getStat();
	}

	public String getProductIds(HttpServletRequest request) {
		String[] productIds = request.getParameterValues("productIds");
		if(productIds == null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(String s:productIds){
			sb.append(s);
			sb.append(",");
		}
		return sb.substring(0, sb.length()-1);//去掉末尾的,
	}
	private String generateProjectId(ProjectList vo) throws Exception{
		ProjectType pt = ProjectType.valueOf(vo.getProjectClass());
		if(pt.needProjectId()){
			return vo.getProjectId();
		}
		StringBuffer sb = new StringBuffer(pt.toString());
		sb.append(DateUtil.getYear().substring(2));
		
		String s = ((ProjectMaintainBO)bo).getMax(sb);
		if(CheckUtil.isEmptry(s)){
			s = "000000";
		}
		DecimalFormat df = new DecimalFormat("000");
		Number n = df.parse(s.substring(pt.toString().length()+2));
		int next = n.intValue()+1;
		sb.append(df.format(next));
		return sb.toString();
	}

	public ActionForward updateProject(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		((ProjectMaintainBO)bo).validateUpdateStatus(form);
		ProjectList vo = new ProjectList();
		copyProperties(vo,form);
		validator.validate(vo);
		vo.setProductIds(getProductIds(request));
		String[] subSys = request.getParameterValues("subSys");
		StringBuffer sb = new StringBuffer();
		if(subSys != null){
			for(String t:subSys){
				sb.append(t);
				sb.append(",");
			}
		}
		vo.setSubSys(sb.toString());
		ValidateUtil.rejectIfEmpty(((ProjectMaintainForm)form).getReason(),"修改原因");
		vo.setUserId(user.getUserID());		
		((ProjectMaintainBO)bo).update(vo,((ProjectMaintainForm)form).getReason(),user);
		return forwardSuccessPage(request,mapping,"更新成功","ProjectMaintain.do?act=qpl");
	}
	public ActionForward deleteProject(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String projectId = ((ProjectMaintainForm)form).getProjectId();
		
		ProjectList p = ((ProjectMaintainBO)bo).queryForObject(projectId);
		if(!p.getStat().equals(Status.U.toString())){
			throw new MessageException("项目非待分配阶段，禁止删除");
		}
		ProjectMaintainVO vo = new ProjectMaintainVO();
		copyProperties(vo,form);
		vo.setUserId(user.getUserID());
		((ProjectMaintainBO)bo).delete(vo,user);
		return forwardSuccessPage(request,mapping,"删除成功","ProjectMaintain.do?act=qpl");
	}
	public ActionForward queryProject(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		ProjectList p = ((ProjectMaintainBO)bo).queryForObject(request.getParameter("projectId"));
		copyProperties(form,p);
		return mapping.findForward("view");
	}
	public ActionForward queryProjectList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		ProjectMaintainForm f = (ProjectMaintainForm)form;
		setPageResult(request, ((ProjectMaintainBO)bo).getActiveList((ProjectMaintainForm)form,user));
		return mapping.findForward("qpl");
	}
	public ActionForward getMyInputProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		((ProjectMaintainForm)form).setUserId(user.getUserID());
		setPageResult(request,((ProjectMaintainBO)bo).getMyInputProjects((ProjectMaintainForm)form));
		return mapping.findForward("gmip");
	}
	public ActionForward queryAllProjectList(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		setPageResult(request,((ProjectMaintainBO)bo).queryForAllList((ProjectMaintainForm)form,user));
		return mapping.findForward("qal");
	}
	public ActionForward editPreProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		request.setAttribute("selectedProjects",((ProjectMaintainBO)bo).getSelectedProjects(form));
		request.setAttribute("notSelectedProjects",((ProjectMaintainBO)bo).getNotSelectedProjects(form));
		return mapping.findForward("epp");
	}
	public ActionForward updatePreProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		String projectIds[] = request.getParameterValues("rolesRight");
		if(projectIds == null){
			PreProjectVO vo = new PreProjectVO();
			vo.setProjectId(((ProjectMaintainForm)form).getProjectId());
			((ProjectMaintainBO)bo).deletePreProjects(vo);
		}else{
			String projectId = request.getParameter("projectId");
			PreProjectVO vos[] = new PreProjectVO[projectIds.length];
			for(int i = 0;i < projectIds.length;i++){
				vos[i] = new PreProjectVO();
				vos[i].setProjectId(projectId);
				vos[i].setPreProject(projectIds[i]);
			}
			((ProjectMaintainBO)bo).transOperation(vos);
		}
		
		return forwardSuccessPage(request,mapping,"更新成功","ProjectMaintain.do?act=qpl");
	}
	public ActionForward getPreProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		request.setAttribute("selectedProjects",((ProjectMaintainBO)bo).getSelectedProjects(form));
		return mapping.findForward("gpp");
	}
	public ActionForward getConflictProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		List relate = ((ProjectMaintainBO)bo).getRelateProjects(form);
		List l = ((ProjectMaintainBO)bo).getConflictProjects(form);
		request.setAttribute("conflictProjects", getConflictTable(l,relate).toString());
		return mapping.findForward("gcp");
	}
	private StringBuffer getConflictTable(List l,List relate) {
		StringBuffer head = new StringBuffer("<tr align='center' class='dtPanel_Top01'><td>");
		StringBuffer body = new StringBuffer("<tr align='left' class='dtPanel_Main'><td align='center' class='dtPanel_Top01'>");
		Iterator it = l.iterator();
		String preProject = null;
		while(it.hasNext()){
			ConflictProjectVO vo = (ConflictProjectVO)it.next();
			if(!relate.contains(vo.getPreProject())){
				if(preProject == null){
					preProject = vo.getPreProject();
					head.append(vo.getProjectId()+"</td>");
					body.append("冲突程序列表");
					head.append("<td>"+preProject+"-"+vo.getProjectName()+"&nbsp;阶段("+SingleDicMap.getDicItemVal(SingleDic.PROJECT_STAT,vo.getStatus())+")</td>");
					body.append("<td>"+vo.getProgram()+"<br>");
	
				}else{
					if(preProject.equals(vo.getPreProject())){
						body.append(vo.getProgram()+"<br>");
					}else{
						preProject = vo.getPreProject();
						head.append("<td>"+preProject+"-"+vo.getProjectName()+"&nbsp;阶段("+SingleDicMap.getDicItemVal(SingleDic.PROJECT_STAT,vo.getStatus())+")</td>");
						body.append("</td><td>"+vo.getProgram()+"<br>");
					}
				}
			}
			
		}
		head.append("</td></tr>");
		body.append("</td></tr>");
		head.append(body);
		return head;
	}

	public void setValidator(ProjectValidator validator) {
		this.validator = validator;
	}
	
	public void getProName(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ProjectList vo = (ProjectList)bo.queryForObject(request.getParameter("projectId"));
		if(vo == null){
			return;
		}
		response.setContentType("text/xml");
		response.setLocale(Locale.CHINESE);
		response.getWriter().print(vo.getProjectName()+","+vo.getOwning());
	}
}
