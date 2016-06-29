/*
 * @# ProjectDistributeBO.java 2008-11-21 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.abc.logic.IbatisBO;
import com.blue.dailyrecord.DailyRecord;
import com.blue.dailyrecord.DailyRecordDAO;
import com.blue.dailyrecord.DailyRecordExample;
import com.blue.enums.Status;
import com.blue.enums.Steps;
import com.blue.enums.WorkType;
import com.blue.enums.YesOrNo;
import com.blue.functiondef.FunctionDef;
import com.blue.functiondef.FunctionDefDAO;
import com.blue.functiondef.FunctionDefExample;
import com.blue.mailconfig.MailConfigBO;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.projectdistribute.ProjectDistribute;
import com.blue.projectdistribute.ProjectDistributeDAO;
import com.blue.projectdistribute.ProjectDistributeExample;
import com.blue.user.EpUserDAOImpl;
import com.blue.user.EpUserExample;
import com.blue.user.EpUserExample.Criteria;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.portal.user.UserBO;
import com.eis.portal.user.UserVO;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.MailSender;
import com.eis.util.StringUtil;


public class ProjectDistributeBO extends IbatisBO {
	@Value("#{env['work.max']}")
	private int maxWork;
	private ProjectDistributeDAO projectDistributeDAO;
	
	private ProjectListDAO projectListDao;
	
	private EpUserDAOImpl epUserDAO;
	private FunctionDefDAO functionDefDAO;
	private MailConfigBO mailConfigBO;
	@Autowired(required=true)
	private DailyRecordDAO dailyRecordDAO;
	@Autowired
	private UserBO userBO;
	public void validateStatus(Object obj)throws Exception{
		ProjectDistributeForm form = (ProjectDistributeForm)obj;
		ProjectList pl = projectListDao.selectByPrimaryKey(form.getProjectId());
		if(!form.getStep().equals(pl.getStep())){
			throw new MessageException("项目当前阶段为"+Steps.valueOf(pl.getStep()).getDesc()+",你还不能执行此操作！");
		}
	}
	public void validateWork(Object obj,UserContext user)throws Exception{
		ProjectDistributeForm form = (ProjectDistributeForm)obj;
		DailyRecordExample e = new DailyRecordExample();
		e.createCriteria().andUserIdEqualTo(user.getUserID()).andIdEqualTo(form.getId()).andWorkDateEqualTo(DateUtil.getDTStr());
		
		List<DailyRecord> l = dailyRecordDAO.selectByExample(e);
		if(l == null || l.size() == 0){
			throw new MessageException("该项目当日工时未填写，请先填写工时");
		}
	}
	private Object getProject(String projectId){
		ProjectMaintainVO vo = new ProjectMaintainVO();
		vo.setProjectId(projectId);
		return dao.queryForObject(namespace+".getProjectUseVO",vo);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {

	}
	public List getDoneProjectDistributes(Object obj)throws Exception{
//		return dao.queryForList(namespace+".getDoneProjectDistributes",obj);
		ProjectDistributeExample example = new ProjectDistributeExample();
		ProjectDistributeExample.Criteria c = example.createCriteria();
//		c.andUserIdEqualTo(value);
		return projectDistributeDAO.selectByExample(example);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj,UserContext user) throws Exception {
//		ChangeRecordVO vo = new ChangeRecordVO();		
//		vo.setProjectId(((ProjectDistributeVO)obj).getProjectId());
//		vo.setChangeDate(DateUtil.getDTStr());
//		vo.setContent(getContent(obj));
//		vo.setReason(((ProjectDistributeVO)obj).getReason());
//		vo.setUserId(user.getUserID());
//		vo.setId(((ProjectDistributeVO)obj).getId());
		
//		dao.insert(namespace+".insertChangeRecord",vo);
//		dao.update(namespace+".updateProjectDistribute",obj);
		projectDistributeDAO.updateByPrimaryKeySelective((ProjectDistribute)obj);
	}
	private String getContent(final Object obj)throws Exception{
		StringBuffer sb = new StringBuffer();
		ProjectDistributeVO vo = (ProjectDistributeVO)obj;
		sb.append("新内容为:"+getDiscription(vo));
		ProjectDistributeVO pdvo = (ProjectDistributeVO)queryForObject(obj);
		sb.append("<br>原内容为:"+getDiscription(pdvo));
		return sb.toString();
	}
	private String getDiscription(ProjectDistributeVO vo){
		return "开始时间-<strong>"+vo.getStartDate()+"</strong>结束时间-<strong>"+vo.getEndDate()+"</strong>";	
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		projectDistributeDAO.insertSelective((ProjectDistribute)obj);
	}
	public Object getProjectDistributeDetail(int id)throws Exception{
		return projectDistributeDAO.selectByPrimaryKey(id);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return dao.queryForObject(namespace+".queryForProjectDistribute",obj);
	}
	
	public void transRemove(String[] id)throws Exception{
		for(String i:id){
			projectDistributeDAO.deleteByPrimaryKey(Integer.parseInt(i));
		}
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList(namespace+".getProjectDistribute",obj);
	}
	public Map<Integer, List<ProjectDistribute>> getProjectDistributeMap(String projectId){
		ProjectDistributeExample e = new ProjectDistributeExample();
		e.createCriteria().andProjectIdEqualTo(projectId);
		List<ProjectDistribute> l = projectDistributeDAO.selectByExample(e);
		Map<Integer, List<ProjectDistribute>> map = new HashMap<Integer, List<ProjectDistribute>>();
		for(ProjectDistribute p : l){
			Integer i = p.getActionId();
			if(map.get(i)==null){
				map.put(i, new ArrayList<ProjectDistribute>());
			}
			map.get(i).add(p);
		}
		return map;
	}
	public List getNotDoneWorks(Object obj)throws Exception{
		return dao.queryForList(namespace+".getNotDoneWorks",obj);
	}
	
	public List getDoneWorks(Object obj)throws Exception{
		ProjectDistributeExample e = new ProjectDistributeExample();
		com.blue.projectdistribute.ProjectDistributeExample.Criteria c = e.createCriteria();
		c.andIsDoneEqualTo(YesOrNo.Y.toString());
		
		ProjectDistributeForm form = (ProjectDistributeForm)obj;
		if(!CheckUtil.isEmptry(form.getProject_f())){
			c.andProjectIdEqualTo(form.getProject_f());
		}
		if(!CheckUtil.isEmptry(form.getEndDate_f())){
			c.andFinalEndDateGreaterThanOrEqualTo(form.getEndDate_f());
		}
		if(!CheckUtil.isEmptry(form.getEndDate_t())){
			c.andFinalEndDateLessThanOrEqualTo(form.getEndDate_t());
		}
		return projectDistributeDAO.selectByExample(e);
	}
	/**
	 * 查找开始日期最晚的分配任务
	 * @param projectId
	 * @param step
	 * @return
	 */
	public ProjectDistribute getLatestStartDate(String projectId,String step){
		ProjectDistributeExample e = new ProjectDistributeExample();
		e.createCriteria().andProjectIdEqualTo(projectId).andStepEqualTo(step);
		e.setOrderByClause(" START_DATE DESC");
		List<ProjectDistribute> l = projectDistributeDAO.selectByExample(e);
		if(l != null && l.size() >0){
			return l.get(0);
		}
		return null;
	}
	public List queryForProjectDistributeList(Object obj)throws Exception{
		ProjectDistributeExample e  = new ProjectDistributeExample();
		e.createCriteria().andProjectIdEqualTo(obj.toString());
		List<ProjectDistribute> l =  projectDistributeDAO.selectByExample(e);
		
		if(l == null ||l.size() == 0){
			return l;
		}
		Tree root = new Tree(l.get(0));
		root.node = l.get(0);
		for(int i = 1;i<l.size();i++){
			insert(root,l.get(i));
		}
		List<ProjectDistribute> result = new ArrayList<ProjectDistribute>(l.size());
		visite(root,result);
		return result;
	}
	/**
	 * 对二叉树进行左序遍历
	 * @param tree
	 * @param result
	 */
	private void visite(Tree tree,List<ProjectDistribute> result){
		if(tree.left != null){
			visite(tree.left,result);
		}
		result.add(tree.node);
		if(tree.right != null){
			visite(tree.right,result);
		}
	}
	private int getIndex(ProjectDistribute p){
		return Steps.valueOf(p.getStep()).getIndex();
	}
	/**
	 * 生成二叉排序树
	 * @param tree
	 * @param pd
	 */
	private void insert(Tree tree,ProjectDistribute pd){
		ProjectDistribute root = tree.node;
		if(getIndex(pd)<=getIndex(root)){
			if(tree.left == null){
				tree.left = new Tree(pd);
				return;
			}else{
				insert(tree.left,pd);
			}
		}else{
			if(tree.right == null){
				tree.right = new Tree(pd);
				return;
			}else{
				insert(tree.right,pd);
			}
		}
	}
	/**
	 * @author blue
	 * 二叉树
	 */
	class Tree{
		Tree(ProjectDistribute pd){
			node = pd;
		}
		ProjectDistribute node;
		Tree left;
		Tree right;
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {
		dao.delete(namespace+".deleteProjectDistribute",obj);
	}
	
	
	public List<ProjectDistribute> getSelectedStaff(HttpServletRequest request,boolean sort)throws Exception{
		ProjectDistributeExample e  = new ProjectDistributeExample();
		String step = request.getParameter("step");
		if(CheckUtil.isEmptry(step)){
			step = Steps.U.toString();
		}
		com.blue.projectdistribute.ProjectDistributeExample.Criteria c = e.createCriteria();
		c.andProjectIdEqualTo(request.getParameter("projectId")).andStepEqualTo(step).getCriteriaWithSingleValue();
		if(!CheckUtil.isEmptry(request.getParameter("actionId"))){
			c.andActionIdEqualTo(Integer.parseInt(request.getParameter("actionId")));
		}
		c.getCriteriaWithoutValue().add(getSearchCondition(request));
		if(sort){
			String orderby = request.getParameter("sidx");
			String order = request.getParameter("sord");
			if(!CheckUtil.isEmptry(orderby)){
				e.setOrderByClause(orderby+" "+order);
			}
		}
		return projectDistributeDAO.selectByExample(e);
	}
	public List<UserContext> getAllStuff(HttpServletRequest request,List<String> staffs)throws Exception{
		EpUserExample e = new EpUserExample();
		Criteria c = e.createCriteria();
		if(staffs != null && staffs.size() > 0){
			c.andUserIdIn(staffs);
		}
		c.getCriteriaWithoutValue().add(getSearchCondition(request));
		String orderby = request.getParameter("sidx");
		String order = request.getParameter("sord");
		if(!CheckUtil.isEmptry(orderby)){
			e.setOrderByClause(orderby+" "+order);
		}
		return epUserDAO.selectByExample(e);
	}
	public List<UserContext> getNotSelectedStuff(HttpServletRequest request,List<String> staffs)throws Exception{
		List<ProjectDistribute> selectedUsers = getSelectedStaff(request,false);
		
		List<String> selectedList = new ArrayList<String>();
		for(ProjectDistribute pd:selectedUsers){
			selectedList.add(pd.getUserId());
		}
		
		EpUserExample e = new EpUserExample();
		Criteria c = e.createCriteria();
		if(selectedList != null && selectedList.size() > 0){
			c.andUserIdNotIn(selectedList);
		}
		if(staffs != null && staffs.size() > 0){
			c.andUserIdIn(staffs);
		}
		c.getCriteriaWithoutValue().add(getSearchCondition(request));
		String orderby = request.getParameter("sidx");
		String order = request.getParameter("sord");
		if(!CheckUtil.isEmptry(orderby)){
			e.setOrderByClause(orderby+" "+order);
		}
		return epUserDAO.selectByExample(e);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#transOperation(java.lang.Object[])
	 */
	public void transOperation(Object[] obj,UserContext user) throws Exception {
		if(obj.length  == 0){
			return;
		}
		//delete(obj[0]);
		ProjectList pl = new ProjectList();
		pl.setProjectId(((ProjectDistribute)obj[0]).getProjectId());
		pl.setStat(Status.S.toString());
		projectListDao.updateByPrimaryKeySelective(pl);
		//修改项目状态为已分配
		for(int i = 0;i < obj.length;i++){
			insert(obj[i]);
		}
		
		//邮件发送
		String[]  to = new String[obj.length+1];
		String[] mails = new String[obj.length+1];
		
		for(int i = 0;i<=obj.length;i++){
			if(i==obj.length){
				to[i] = user.getUserID();
			}else{
				to[i] = ((ProjectDistribute)obj[i]).getUserId();
			}
			mails[i] = jdbcTemplate.queryForObject("select EMAIL from ep_user where user_id = '"+to[i]+"'", String.class);
		}
		
		MailSender.sendMail("项目分配通知", "项目"+pl.getProjectId()+"已分配给你，请注意查看", mails);
	}
	public int getFunctionId(){
		FunctionDefExample e = new FunctionDefExample();
		e.createCriteria().andClassNameEqualTo(this.getClass().getName());
		List<FunctionDef> l = functionDefDAO.selectByExample(e);
		if(l == null || l.size() == 0){
			return -1;
		}
		return l.get(0).getFunctionId();
	}
	public List<ProjectDistributeVO> getMyProjects(Object obj)throws Exception{
//		return dao.queryForList(namespace+".getMyProjects",obj);
		if(obj == null){
			return new ArrayList<ProjectDistributeVO>();
		}
		ProjectDistributeExample e = new ProjectDistributeExample();
		com.blue.projectdistribute.ProjectDistributeExample.Criteria c = e.createCriteria();
		c.andUserIdEqualTo(obj.toString()).andIsDoneEqualTo(YesOrNo.N.toString());
		List<ProjectDistribute> myProjects = projectDistributeDAO.selectByExample(e);
		List<ProjectDistributeVO> result = new ArrayList<ProjectDistributeVO>();
		for(ProjectDistribute p : myProjects){
			ProjectList find = projectListDao.selectByPrimaryKey(p.getProjectId());
			ProjectDistributeVO pv = new ProjectDistributeVO();
			BeanUtils.copyProperties(pv, p);
			pv.setType(WorkType.P.toString());
			pv.setReady(YesOrNo.Y.toString());
			pv.setHasLink(find.getIsInContract());
			pv.setReminingDays((int)DateUtil.getDays(DateUtil.getDTStr(), p.getEndDate()));
			pv.setProject(find);
			//只显示当前阶段>=项目当前阶段的列表
//			if(Steps.valueOf(find.getStep()).getIndex() <= Steps.valueOf(p.getStep()).getIndex()){
				result.add(pv);
	//		}
			
		}
		return result;
	}
	public List<ProjectDistributeVO> getMyProjectsPop(Object obj)throws Exception{
//		return dao.queryForList(namespace+".getMyProjects",obj);
		if(obj == null){
			return new ArrayList<ProjectDistributeVO>();
		}
		ProjectDistributeExample e = new ProjectDistributeExample();
		com.blue.projectdistribute.ProjectDistributeExample.Criteria c = e.createCriteria();
		c.andUserIdEqualTo(obj.toString());
		e.setOrderByClause(" START_DATE DESC");
		List<ProjectDistribute> myProjects = projectDistributeDAO.selectByExample(e);
		List<ProjectDistributeVO> result = new ArrayList<ProjectDistributeVO>();
		for(ProjectDistribute p : myProjects){
			ProjectList find = projectListDao.selectByPrimaryKey(p.getProjectId());
			ProjectDistributeVO pv = new ProjectDistributeVO();
			BeanUtils.copyProperties(pv, p);
			pv.setType(WorkType.P.toString());
			pv.setReady(YesOrNo.Y.toString());
			pv.setHasLink(find.getIsInContract());
			pv.setReminingDays((int)DateUtil.getDays(DateUtil.getDTStr(), p.getEndDate()));
			pv.setProject(find);
			//只显示当前阶段>=项目当前阶段的列表
//			if(Steps.valueOf(find.getStep()).getIndex() <= Steps.valueOf(p.getStep()).getIndex()){
				result.add(pv);
	//		}
			
		}
		return result;
	}
	/**
	 * 获得当月所有人员的工作分配情况
	 * 
	 * @param obj 月份
	 * @return List of ProjectDistribu
	 */
	public List getMonthlyDistribute(Object obj)throws Exception{
		return dao.queryForList(namespace+".getMonthlyDistribute",obj);
	}
	public List getWeeklyDistribute(String monday)throws Exception{
		return dao.queryForList(namespace+".getWeeklyDistribute",monday);
	}
	/**
	 * 获得有时间冲突的分配记录
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List getConflictDistribute(Object obj)throws Exception{
		return dao.queryForList(namespace+".getConflictDistribute",obj);
	}
	public void checkBusy(ProjectDistributeVO vo)throws Exception{
		List<ProjectDistributeVO> l = getConflictDistribute(vo);
		if(l == null || l.size() <= maxWork){
			return;
		}
		List<C> result = new ArrayList<C>();
		for(ProjectDistributeVO v: l){
			C c = new C();
			c.vo = v;
			c.from = Long.parseLong(getMax(vo.getStartDate() ,v.getStartDate()));
			c.to = Long.parseLong(getMin(vo.getEndDate(),v.getEndDate()));
			for(C t : result){
				if(isConflict(t, c)){
					t.count++;
					c.count++;
				}
			}
			result.add(c);
		}
		StringBuffer sb = new StringBuffer("时间分配冲突，冲突项目信息：\\n");
		for(C c : result){
			if(c.count>maxWork){
				sb.append(c.vo.getProjectId()+"-"+Steps.valueOf(c.vo.getStep()).getDesc()+"["+c.vo.getStartDate()+"-"+c.vo.getEndDate()+"]\\n");
			}
		}
		throw new MessageException(sb.toString());
	}
	private boolean isConflict(C a,C b){
		if(a.from >= b.from && a.from <=b.to){
			return true;
		}else if(a.to >= b.from && a.to <=b.to){
			return true;
		}
		return false;
	}
	
	private String getMin(String ... s){
		String temp = "";
		for(String t : s){
			if(temp.length() == 0){
				temp = t;
			}
			if(temp.compareTo(t)>0){
				temp = t;
			}
		}
		return temp;
	}
	private String getMax(String ...s){
		String temp = "";
		for(String t : s){
			if(temp.length() == 0){
				temp = t;
			}
			if(temp.compareTo(t)<0){
				temp = t;
			}
		}
		return temp;
	}
	class C{
		ProjectDistributeVO vo;
		long from;
		long to;
		int count=1;
	}
	/**
	 * 获得员工列表
	 * 
	 * @return List of Stuff userId
	 */
	public List getStuff(){
		return dao.queryForList(namespace+".getActiveStuff",null);
	}
	
	public List<UserVO> getStaff(){
		return dao.queryForList(namespace+".getStaff",null);
	}
	public void finishMyProject(Object obj,UserContext user){
		ProjectDistribute pd = new ProjectDistribute();
		pd.setId(Integer.parseInt(obj.toString()));
		pd.setIsDone(YesOrNo.Y.toString());
		pd.setFinalEndDate(DateUtil.getDTStr());
		projectDistributeDAO.updateByPrimaryKeySelective(pd);
//		ProjectDistributeVO vo = new ProjectDistributeVO();
//		vo.setId(form.getId());
//		vo.setUserId(user.getUserID());
//		vo.setProjectId(form.getProjectId());
//		vo.setStep(form.getStatus());
//		dao.update(namespace+".finishMyProject",vo);
	}
	
	public String getDisplay(String now,String[] ids)throws Exception{
		WorkTable wt[] = getWorkTable(now,ids);
		String end = DateUtil.getSundayStr(now);
		String start = DateUtil.getMondayStr(now);
		
		int days = (int)DateUtil.getDays(start,end)+1;
		Calendar dayOfWeek[] = new Calendar[days];
		setDate(dayOfWeek,start);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table width=\"98%\" class=\"dtPanel_Line1\" border=\"0\" cellspacing=\"1\" align=\"left\" cellpadding=\"0\">");
		sb.append(getHead(days,dayOfWeek));
		for(int i = 0;i < wt.length;i++){
			sb.append(getLine(wt[i],days,dayOfWeek));
		}
		
		sb.append("</table>");
		return sb.toString();
	}
	
	private StringBuffer getLine(WorkTable wt,int days,Calendar[] c){
		StringBuffer sb = new StringBuffer();	
		sb.append("<tr align=\"center\" class=\"dtPanel_Top01\">");
		sb.append("<td width=\"7%\">"+StringUtil.formatUser(ReDefSDicMap.getDicItemVal("0003",wt.getUserId()))+"</td>");
		String bgColor = null;
		
		for(int i = 0;i<days;i++){
			if(isWeekEnd(c[i])){
				bgColor = "gray";
			}else{
				bgColor = "green";
			}
			if(wt.getTable()[i] == null){
				sb.append("<td bgcolor="+bgColor+"></td>");
			}else{
				String fontColor = "white";
				if(wt.getTable()[i].isHeavyWork()){
					bgColor = "#FF0033";
					fontColor="yellow";
				}
				sb.append("<td bgcolor="+bgColor+"><font color=\""+fontColor+"\">"+wt.getTable()[i].getDailyDisplay()+"</font></td>");
			}
			
		}
		
		sb.append("</tr>");
		return sb;
	}
	

	private StringBuffer getHead(int days,Calendar[] c){
		StringBuffer sb = new StringBuffer("<tr align=\"center\" class=\"dtPanel_Top01\">");
		sb.append("<td></td>");		
		for(int i = 1;i <= days;i++){ 
			sb.append("<td>"+DateUtil.format(c[i-1].getTime())+"<br>"+getNewDayOfWeek(c[i-1])+"</td>");
		}
		sb.append("</tr>");
		return sb;
	}
	private boolean isWeekEnd(Calendar c){
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			return true;
		}
		return false;
	}
	private String getDayOfWeek(Calendar c){
		SimpleDateFormat sf = new SimpleDateFormat("E");
		return sf.format(c.getTime());
	}
	private String getNewDayOfWeek(Calendar c){
		return getDayOfWeek(c).substring(2);
	}
	private void setDate(Calendar[] days,String now){
		for(int i = 0;i < days.length;i++){
			days[i] = getDayOfWeek(i,now);
		}
	}
	private Calendar getDayOfWeek(int day,String now){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR,Integer.parseInt(now.substring(0,4)));
		c.set(Calendar.MONTH,Integer.parseInt(now.substring(4,6))-1);
		c.set(Calendar.DATE,Integer.parseInt(now.substring(6))+day);
		return c;
	}
	private WorkTable[] getWorkTable(String now,String[] ids)throws Exception{		
		String monthEnd = DateUtil.getSundayStr(now);		
		List<UserVO> stuff = userBO.getStaffByDeparts(ids,true);// getStuff();
		WorkTable wt[] = new WorkTable[stuff.size()];
		Iterator it = stuff.iterator();
		int i = 0;
		for(UserVO u : stuff){
			wt[i] = new WorkTable();
			wt[i++].setUserId(u.getUser_id());
		}
		
		List projectDistribute = getWeeklyDistribute(DateUtil.getMondayStr(now));
		Iterator pd = projectDistribute.iterator();
		while(pd.hasNext()){
			ProjectDistributeVO vo = (ProjectDistributeVO)pd.next();					
			if(vo.getStartDate().compareTo(monthEnd) > 0){
				continue;
			}
			int position = findPosition(vo,wt);
			if(position!=-1){
				int[] se = getStartAndEndPosition(vo,now,wt[position]);
				for(int j = se[0];j <se[1];j++ ){
					WorkContent wc = wt[position].getTable()[j];
					if(wc == null){
						wc = new WorkContent();
					}
					wt[position].getTable()[j] = wc;
					if("P".equals(vo.getType())){
						wc.addProject(vo.getProjectId(), vo.getStep());
					}else{
						wc.addWork(vo.getProjectId());
					}
	//				wc.setBusy(true);
	//				wc.setProjectId(vo.getProjectId());
	//				wc.setStatus(vo.getStatus());
				}		
			}
		}
		return wt;
	}
	
	private int findPosition(ProjectDistributeVO vo,WorkTable[] wt)throws Exception{
		for(int i = 0;i < wt.length;i++){
			if(vo.getUserId().equals(wt[i].getUserId())){
				return i;
			}
		}
		return -1;
//		throw new MessageException("项目 "+vo.getProjectId()+" 阶段 "+vo.getStatus()+" 分配给员工 "+
//		ReDefSDicMap.getDicItemVal("0003",vo.getUserId())+" ,但该员工身份已经无效，请将对应工作分配记录删除");
	}
	private int[] getStartAndEndPosition(ProjectDistributeVO vo,String now,WorkTable wt){
		int positions[] = new int[2];
		String monthEnd = DateUtil.getSundayStr(now);
		String monthStart = DateUtil.getMondayStr(now);
		
		if(vo.getStartDate().compareTo(monthStart) < 0){
			positions[0] = 0;
			if(vo.getEndDate().compareTo(monthEnd) > 0){
				positions[1] = wt.getTable().length;
			}else{
				positions[1] = (int)DateUtil.getDays(monthStart,vo.getEndDate())+1;
			}
		}else{
			positions[0] = (int)DateUtil.getDays(monthStart,vo.getStartDate());
			if(vo.getEndDate().compareTo(monthEnd) > 0){
				positions[1] = wt.getTable().length;
			}else{
				positions[1] = (int)DateUtil.getDays(monthStart,vo.getEndDate())+1;
			}
		}
		return positions;
	}
	public boolean checkBlankWork(UserContext user)throws Exception{
		BlankWorkVO vo = new BlankWorkVO();
		boolean flag = false;
		Calendar c = Calendar.getInstance();
		int days = c.get(Calendar.DAY_OF_WEEK);
		String sdate = "";
		String edate = "";
		int count;
		vo.setUser(user.getUserID());
		if(days == 1 || days == 7 || days == 2){
			if(days==1){
				sdate = DateUtil.getDateBeforeSysdt(6);
				edate = DateUtil.getDateBeforeSysdt(2);
			}
			if(days==7){
				sdate = DateUtil.getDateBeforeSysdt(5);
				edate = DateUtil.getDateBeforeSysdt(1);
			}
			if(days==2){
				sdate = DateUtil.getDateBeforeSysdt(7);
				edate = DateUtil.getDateBeforeSysdt(3);
			}
			vo.setEdate(edate);
			vo.setSdate(sdate);
			Object obj = dao.queryForObject(namespace+".getBlankWork",vo);
			if(null==obj){
				count=0;
			}else{
				count = ((Integer)obj).intValue();
			}
			if(count <5){
				flag = true;
			}
				
		}else{
			sdate = DateUtil.getDateBeforeSysdt(days-2);
			edate = DateUtil.getDateBeforeSysdt(1);
			vo.setEdate(edate);
			vo.setSdate(sdate);
			Object obj = dao.queryForObject(namespace+".getBlankWork",vo);
			if(null==obj){
				count=0;
			}else{
				count = ((Integer)obj).intValue();
			}
			if(count <(days-2)){
				flag = true;
			}
				
		}
		return flag;

	}
	public void setProjectDistributeDAO(ProjectDistributeDAO projectDistributeDAO) {
		this.projectDistributeDAO = projectDistributeDAO;
	}
	public void setEpUserDAO(EpUserDAOImpl epUserDAO) {
		this.epUserDAO = epUserDAO;
	}
	public void setProjectListDao(ProjectListDAO projectListDao) {
		this.projectListDao = projectListDao;
	}
	public static void main(String[] args) {
		ProjectDistributeBO bo = new ProjectDistributeBO();
		System.out.println(bo.getMax("a","b"));
		System.out.println("------");
		System.out.println(bo.getMin("a","b"));
		C c1 = bo.new C();
		C c2 = bo.new C();
		c1.from = 20130101;
		c1.to = 20130203;
		c2.from = 20130204;
		c2.to = 20130303;
		System.out.println(bo.isConflict(c1, c2));
	}
	public ProjectDistribute getLatestStartDateByActionId(String projectId, String actionId) {
		ProjectDistributeExample e = new ProjectDistributeExample();
		e.createCriteria().andProjectIdEqualTo(projectId).andActionIdEqualTo(Integer.parseInt(actionId));
		e.setOrderByClause(" START_DATE DESC");
		List<ProjectDistribute> l = projectDistributeDAO.selectByExample(e);
		if(l != null && l.size() >0){
			return l.get(0);
		}
		return null;
	}
	
}
