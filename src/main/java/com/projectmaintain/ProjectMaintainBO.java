/*
 * @# ProjectMaintainBO.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.abc.logic.IbatisBO;
import com.blue.enums.Status;
import com.blue.enums.Steps;
import com.blue.functiondef.FunctionDef;
import com.blue.functiondef.FunctionDefDAO;
import com.blue.functiondef.FunctionDefExample;
import com.blue.mailconfig.MailConfigBO;
import com.blue.pm.mainplanjob.MainPlanJob;
import com.blue.pm.mainplanjob.MainPlanJobDAO;
import com.blue.pm.mainplanjob.MainPlanJobExample;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.project.ProjectListExample;
import com.blue.project.ProjectListExample.Criteria;
import com.blue.roledepartauth.RoleDepartAuthBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.MailSender;

@Service
public class ProjectMaintainBO extends IbatisBO {
	private ProjectListDAO projectListDAO;
	private FunctionDefDAO functionDefDAO;
	private MailConfigBO mailConfigBO;
	private RoleDepartAuthBO authBO;
	private MainPlanJobDAO mainPlanJobDAO;
	
	public List<ProjectList> getProjectsByVersionId(String versionId){
		ProjectListExample e = new ProjectListExample();
		e.createCriteria().andVersionIdEqualTo(versionId).andStatNotEqualTo(Status.D.toString());
		return projectListDAO.selectByExample(e);
	}
	private int getFunctionId()throws Exception{
		FunctionDefExample e = new FunctionDefExample();
		e.createCriteria().andClassNameEqualTo(this.getClass().getName());
		List<FunctionDef> l = functionDefDAO.selectByExample(e);
		if(l == null || l.size() == 0){
			return -1;
		}
		return l.get(0).getFunctionId();
	}
	@Override
	public List queryForList(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj,UserContext user) throws Exception {
		dao.update(namespace+".deleteProject",obj);
		ProjectDistributeVO vo = new ProjectDistributeVO();
		vo.setProjectId(((ProjectMaintainVO)obj).getProjectId());
		dao.delete(namespace+".deleteProjectDistribute",vo);
		String[] to = mailConfigBO.getNotifyUsers(this,null);
		MailSender.sendMail(vo.getProjectId()+"被删除", "项目"+vo.getProjectId()+"被"+user.getUserName()+"删除", to);
	}
	public void deletePreProjects(Object obj)throws Exception{
		dao.delete(namespace+".deleteProjectRelation",obj);
	}
	public List getChangeRecord(String projectId,String id)throws Exception{
		ChangeRecordVO vo = new ChangeRecordVO();
		vo.setProjectId(projectId);
		if(!CheckUtil.isEmptry(id)){
			vo.setId(Integer.parseInt(id));
		}else{
			vo.setId(null);
		}
		return dao.queryForList(namespace+".getChangeRecord",vo);
	}
	/**
	 * 获得冲突项目列表
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List getConflictProjects(Object obj)throws Exception{
		return dao.queryForList(namespace+".getConflictProjects",obj);
	}
	private String getContent(ProjectList obj)throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("新内容为:"+getDiscription(obj));
		ProjectList vo2 = (ProjectList)queryForObjectUseVO(obj);
		sb.append("<br>原内容为:"+getDiscription(vo2));
		return sb.toString();
	}
	private String getDiscription(ProjectList vo){
		return "阶段-<strong>"+SingleDicMap.getDicItemVal(SingleDic.PROJECT_STAT,vo.getStat())+
				"</strong>;是否合同内-<strong>"+SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsInContract())+
				"</strong>;是否参数调整实现-<strong>"+SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getNeedDev())+
				"</strong>;问题单列表-<strong>"+vo.getIssId()+"</strong>";	
	}
	public List getMyInputProjects(ProjectMaintainForm obj)throws Exception{
		ProjectListExample example = new ProjectListExample();
		Criteria c = example.createCriteria().andUserIdEqualTo(obj.getUserId());
		if(!CheckUtil.isEmptry(obj.getProjectId_f())){
			c.andProjectIdEqualTo(obj.getProjectId_f());
		}
		if(!CheckUtil.isEmptry(obj.getStep_f())){
			c.andStepEqualTo(obj.getStep_f());
		}
		return projectListDAO.selectByExample(example);
	}
	public List getNotSelectedProjects(Object obj)throws Exception{
		List l = dao.queryForList(namespace+".getNotSelectedProjects",obj);
		ArrayList al = new ArrayList();
		Iterator it = l.iterator();
		while(it.hasNext()){			
			al.add(it.next());
		}
		return al;
	}

	/**
	 * 获得制定项目编号的项目的所有前驱项目的编号
	 * @param projectId
	 * @param l
	 * @return 
	 */
	private List getPreProject(String projectId,List l){
		List list = new ArrayList();
		Iterator it = l.iterator();
		while(it.hasNext()){
			PreProjectVO pv = (PreProjectVO)it.next();
			if(projectId.equals(pv.getProjectId())){
				List preProject = getPreProject(pv.getPreProject(),l);
				Iterator iter = preProject.iterator();
				while(iter.hasNext()){
					list.add(iter.next().toString());
				}
				list.add(pv.getPreProject());
			}
		}
		list.add("''");
		return list;
	}

	private List getPreProjects(Object obj)throws Exception{
		return getPreProject(((ProjectMaintainForm)obj).getProjectId(),getProjectRelation(obj));
	}
	
	/**
	 * 获得前驱项目列表
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List getPreProjectStatus(Object obj)throws Exception{
		return dao.queryForList(namespace+".getPreProjectStatus",getPreProjects(obj));
	}
	/**
	 * 获得所有项目的前驱关系信息
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private List getProjectRelation(Object obj)throws Exception{
		return dao.queryForList(namespace+".getProjectRelation",obj);
	}
	/**
	 * 将两个List合并成一个List
	 * @param preList
	 * @param sufList
	 * @return
	 */
	private List getRelateProjects(List preList,List sufList){
		List l = new ArrayList();
		Iterator it = preList.iterator();
		while(it.hasNext()){
			l.add(it.next().toString());
		}
		it = sufList.iterator();
		while(it.hasNext()){
			l.add(it.next().toString());
		}
		return l;
	}
	/**
	 * 获得与本项目有前驱或者后继关系的项目编号列表
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List getRelateProjects(Object obj)throws Exception{
		List l = getProjectRelation(obj);
		return getRelateProjects(getPreProject(((ProjectMaintainForm)obj).getProjectId(),l),getSufProject(((ProjectMaintainForm)obj).getProjectId(),l));
	}
	public List getSelectedProjects(Object obj)throws Exception{
		List l = dao.queryForList(namespace+".getSelectedProjects",obj);
		ArrayList al = new ArrayList();
		Iterator it = l.iterator();
		while(it.hasNext()){
			al.add(it.next());
		}
		return al;
	}
	/**
	 * 获得制定项目编号的项目的所有后继项目的编号
	 * @param projectId
	 * @param l
	 * @return 
	 */
	private List getSufProject(String projectId,List l){
		List list = new ArrayList();
		Iterator it = l.iterator();
		while(it.hasNext()){
			PreProjectVO pv = (PreProjectVO)it.next();
			if(projectId.equals(pv.getPreProject())){
				List sufProject = getSufProject(pv.getProjectId(),l);
				Iterator iter = sufProject.iterator();
				while(iter.hasNext()){
					list.add(iter.next().toString());
				}
				list.add(pv.getProjectId());
			}
		}

		return list;
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj,UserContext user) throws Exception {
		ProjectList p = (ProjectList)obj;
		projectListDAO.insert((ProjectList)obj);
		String[] to = mailConfigBO.getNotifyUsers(this,null);
		
		if("1".equals(p.getMainPlanDis())){
			MainPlanJob m = new MainPlanJob();
			m.setInputDate(DateUtil.getDTStr());
			m.setIsDoen("N");
			m.setProjectId(p.getProjectId());
			m.setUserId(p.getProjectManager());
			mainPlanJobDAO.insert(m);
		}
		MailSender.sendMail("新增项目["+((ProjectList)obj).getProjectId()+"-"+((ProjectList)obj).getProjectName()+"]",user.getUserName()+" 新增项目:"+((ProjectList)obj).getProjectName(), to);
	}
	/**
	 * 
	 * 调整为查询完成项目列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List queryForAllList(ProjectMaintainForm form,UserContext user)throws Exception{
		ProjectListExample e = new ProjectListExample();
		Criteria c = e.createCriteria();
		c.andStatEqualTo(Status.F.toString());
		c.andOwningIn(getRoleDeparts(user));
		if(!CheckUtil.isEmptry(form.getProjectId_f())){
			c.andProjectIdEqualTo(form.getProjectId_f());
		}
		if(!CheckUtil.isEmptry(form.getStep_f()) && !form.getStep_f().equals(Steps.A.toString())){
			c.andStepEqualTo(form.getStep_f());
		}
		return projectListDAO.selectByExample(e);
//		return dao.queryForList(namespace+".getProjectList",obj);
	}
	
	/**
	 * 活动项目列表
	 * @param form
	 * @param request
	 * @throws Exception
	 */
	public List getActiveList(ProjectMaintainForm form,UserContext user) throws Exception {
		ProjectListExample example = new ProjectListExample();
		List<String> unActiveStatus = new ArrayList<String>();
		unActiveStatus.add(Status.F.toString());
		unActiveStatus.add(Status.D.toString());
		Criteria c = example.createCriteria();
		c.andStatNotIn(unActiveStatus);
		//部门权限管理
		//c.andOwningIn(getRoleDeparts(user));
		//~~
		if(!CheckUtil.isEmptry(form.getOwning())){
			c.andOwningEqualTo(form.getOwning());
		}
		if(!CheckUtil.isEmptry(form.getProjectId_f())){
			c.andProjectIdEqualTo(form.getProjectId_f());
		}
		if(!CheckUtil.isEmptry(form.getStatus_f()) && !form.getStatus_f().equals(Status.A.toString())){
			c.andStatEqualTo(form.getStatus_f());
		}
		if(!CheckUtil.isEmptry(form.getStep_f()) && !form.getStep_f().equals(Steps.A.toString())){
			c.andStepEqualTo(form.getStep_f());
		}
		if(!CheckUtil.isEmptry(form.getEndDate_f())){
			if(CheckUtil.isEmptry(form.getDay())){
				c.andPlanEndDateEqualTo(form.getEndDate_f());
			}else{
				c.andPlanEndDateEqualTo(DateUtil.getDateAfter(form.getEndDate_f(), Integer.parseInt(form.getDay())));
			}
		}
		if(form.getPriority() != null && form.getPriority()>0){
			c.andPriorityEqualTo(form.getPriority());
		}
		if(!CheckUtil.isEmptry(form.getProjectName())){
			c.andProjectNameLike("%"+form.getProjectName()+"%");
		}
		return projectListDAO.selectByExample(example);
	}
	public List<String> getRoleDeparts(UserContext user) {
		Map<String,String> departs =  authBO.getGrantedDeparts(user);
	
		List<String> granted = new ArrayList<String>();
		if(departs == null){
			granted.add(user.getDeptID());
			return granted; 
		}
		Set<String> set = departs.keySet();
		for(String s : set){
			granted.add(s);
		}
		granted.add(user.getDeptID());
		return granted;
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public ProjectList queryForObject(String obj) throws Exception {
		return projectListDAO.selectByPrimaryKey(obj.toString());
	}
	@Override
	public Object queryForObject(Object obj) throws Exception {
		return queryForObject(obj.toString());
	}
	
	public ProjectList queryForObjectUseVO(ProjectList obj)throws Exception{
		return projectListDAO.selectByPrimaryKey(obj.getProjectId());
	}
	public void setProjectListDAO(ProjectListDAO projectListDAO) {
		this.projectListDAO = projectListDAO;
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#transOperation(java.lang.Object[])
	 */
	public void transOperation(Object[] obj) throws Exception {
		dao.delete(namespace+".deleteProjectRelation",obj[0]);
		for(int i=0;i < obj.length;i++){
			dao.insert(namespace+".insertProjectRelation",obj[i]);
		}
	}
	@Override
	public void update(Object obj) throws Exception {
		projectListDAO.updateByPrimaryKeySelective((ProjectList)obj);
	}
	public void update(ProjectList obj,String reason,UserContext user) throws Exception {
		projectListDAO.updateByPrimaryKeySelective(obj);
		ProjectList p = obj;
		MainPlanJobExample e = new MainPlanJobExample();
		e.createCriteria().andProjectIdEqualTo(p.getProjectId());
		mainPlanJobDAO.deleteByExample(e);
		if("1".equals(p.getMainPlanDis())){
			MainPlanJob m = new MainPlanJob();
			m.setInputDate(DateUtil.getDTStr());
			m.setIsDoen("N");
			m.setProjectId(p.getProjectId());
			m.setUserId(p.getProjectManager());
			mainPlanJobDAO.insert(m);
		}
		String[] to = mailConfigBO.getNotifyUsers(this,null);
		MailSender.sendMail("项目["+obj.getProjectId()+"-"+obj.getProjectName()+"]被修改", user.getUserName()+"修改项目"+obj.getProjectId()+",修改原因:"+reason, to);
	}
	public void validateUpdateStatus(Object obj)throws Exception{
		if(((ProjectMaintainForm)obj).getStat().equals("9")){
			return;
		}
		List l = getPreProjectStatus(obj);
		Iterator it = l.iterator();
		while(it.hasNext()){
			ProjectMaintainVO vo = (ProjectMaintainVO)it.next();
			if(Integer.parseInt(vo.getStatus()) <= Integer.parseInt(((ProjectMaintainForm)obj).getStat())){
				throw new MessageException("前驱项目"+vo.getProjectId()+" "+vo.getProjectName()+"阶段为"+
				SingleDicMap.getDicItemVal(SingleDic.PROJECT_STAT, vo.getStatus())+"本项目阶段不能为"+
				SingleDicMap.getDicItemVal(SingleDic.PROJECT_STAT, ((ProjectMaintainForm)obj).getStat()));
			}
		}
	}
	public String getMax(StringBuffer sb) {
		return jdbcTemplate.queryForObject("select max(project_id) from project_list where project_id like '"+sb+"%'", String.class);
	}
	public void setFunctionDefDAO(FunctionDefDAO functionDefDAO) {
		this.functionDefDAO = functionDefDAO;
	}
	public void setMailConfigBO(MailConfigBO mailConfigBO) {
		this.mailConfigBO = mailConfigBO;
	}
	@Override
	public void insert(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void setAuthBO(RoleDepartAuthBO authBO) {
		this.authBO = authBO;
	}
	public MainPlanJobDAO getMainPlanJobDAO() {
		return mainPlanJobDAO;
	}
	public void setMainPlanJobDAO(MainPlanJobDAO mainPlanJobDAO) {
		this.mainPlanJobDAO = mainPlanJobDAO;
	}
	
}
