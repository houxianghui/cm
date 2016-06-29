package com.blue.report.weekly;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.logic.IbatisBO;
import com.blue.dailyrecord.DailyRecord;
import com.blue.dailyrecord.DailyRecordDAO;
import com.blue.dailyrecord.DailyRecordExample;
import com.blue.enums.Status;
import com.blue.enums.Steps;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAOImpl;
import com.blue.project.ProjectListExample;
import com.blue.project.ProjectListExample.Criteria;
import com.blue.projectdistribute.ProjectDistribute;
import com.blue.projectdistribute.ProjectDistributeDAO;
import com.blue.projectdistribute.ProjectDistributeExample;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.portal.user.UserBO;
import com.eis.portal.user.UserVO;
import com.eis.util.DateUtil;
public class WeeklyReportBO extends IbatisBO {
	@Autowired
	private ProjectListDAOImpl projectListDAO;
	@Autowired
	private DailyRecordDAO dailyRecordDAO;
	@Autowired
	private ProjectDistributeDAO projectDistributeDAO;
	@Autowired
	private UserBO userBO;
	@Override
	public void update(Object obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Object obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object obj) throws Exception {
		// TODO Auto-generated method stub

	}
	
	public List<Weekly> getReport(WeeklyReportForm form)throws Exception{
		String startDate = DateUtil.getMondayStr(form.getDate());
		String endDate = DateUtil.getSundayStr(startDate);
		
		List<UserVO> users = userBO.getStaffByDeparts(form.getIds(),false);
		Map<String,UserVO> userMap = new HashMap<String,UserVO>();
		for(UserVO u:users){
			userMap.put(u.getUser_id(), u);
		}
		
		ProjectListExample e = new ProjectListExample();
		Criteria c = e.createCriteria();
		c.andPlanStartDateLessThanOrEqualTo(endDate);
		c.andPlanEndDateGreaterThanOrEqualTo(startDate);
		
		c.andOwningIn(getFilterDeparts(form));
		
		List<ProjectList> projects = projectListDAO.selectByExample(e);
		List<Weekly> result = new ArrayList<Weekly>(projects.size());
		DecimalFormat df = new DecimalFormat("#0.00");
		for(ProjectList p : projects){
			Weekly w = new Weekly();
			w.setProjectName(p.getProjectName());
			w.setStatus(Status.valueOf(p.getStat()).getDesc());
			w.setStep(Steps.valueOf(p.getStep()).getDesc());
			w.setDating(DateUtil.format(p.getPlanStartDate(),"/")+" - "+DateUtil.format(p.getPlanEndDate(),"/"));
			w.setMemo(p.getMemo());
			DailyRecordExample de = new DailyRecordExample();
			de.createCriteria().andProjectIdEqualTo(p.getProjectId()).andWorkDateBetween(startDate, endDate);
			List<DailyRecord> dailys = dailyRecordDAO.selectByExample(de);
			StringBuffer sb = new StringBuffer();
			double load = 0;
			Map<String,String> include = new HashMap<String,String>();//用map过滤重复的名字
			for(DailyRecord d : dailys){
				if(userMap.get(d.getUserId()) == null){	//不在给定机构内的直接跳过
					continue;
				}else{
					include.put(d.getUserId(), userMap.get(d.getUserId()).getUser_name());
					load+=d.getTaskCost().doubleValue();
				}
			}
			Set<String> set = include.keySet();
			for(String s : set){
				sb.append(include.get(s)+"、");
			}
			if(sb.length()>0){
				w.setUsers(sb.substring(0,sb.length()-1));
			}else{
				w.setUsers("");
			}
			
			w.setWorkLoad(df.format(load/8));
			result.add(w);
		}
		return result;
	}

	public List<String> getFilterDeparts(WeeklyReportForm form) {
		List<String> l =  new ArrayList<String>();
		if(form.getIds() != null){
			for(String s : form.getIds()){
				l.add(s);
			}
		}else{
			l.add("");
		}
		return l;
	}
	public List<Weekly> getPlan(WeeklyReportForm form)throws Exception{
		String startDate = DateUtil.getNextModayStr(form.getDate());
		String endDate = DateUtil.getSundayStr(startDate);
		List<UserVO> users = userBO.getStaffByDeparts(form.getIds(),false);
		Map<String,UserVO> userMap = new HashMap<String,UserVO>();
		for(UserVO u:users){
			userMap.put(u.getUser_id(), u);
		}
		ProjectListExample e = new ProjectListExample();
		Criteria c = e.createCriteria();
		c.andPlanStartDateLessThanOrEqualTo(endDate);
		c.andPlanEndDateGreaterThanOrEqualTo(startDate);
		c.andOwningIn(getFilterDeparts(form));
		List<ProjectList> projects = projectListDAO.selectByExample(e);
		List<Weekly> result = new ArrayList<Weekly>(projects.size());
		for(ProjectList p : projects){
			Weekly w = new Weekly();
			w.setProjectName(p.getProjectName());
			w.setStatus(Status.valueOf(p.getStat()).getDesc());
			w.setStep(Steps.valueOf(p.getStep()).getDesc());
			w.setDating(DateUtil.format(p.getPlanStartDate(),"/")+" - "+DateUtil.format(p.getPlanEndDate(),"/"));
			w.setMemo(p.getMemo());
			ProjectDistributeExample pe = new ProjectDistributeExample();
			pe.createCriteria().andProjectIdEqualTo(p.getProjectId()).andStartDateLessThanOrEqualTo(endDate).andEndDateGreaterThanOrEqualTo(startDate);
			List<ProjectDistribute> dailys = projectDistributeDAO.selectByExample(pe);
			StringBuffer sb = new StringBuffer();
			for(ProjectDistribute d : dailys){
				if(userMap.get(d.getUserId()) == null){
					continue;
				}
				sb.append(ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, d.getUserId())+"、");
			}
			if(sb.length()>0){
				w.setUsers(sb.substring(0,sb.length()-1));
			}else{
				w.setUsers("");
			}
			w.setWorkLoad("");
			result.add(w);
		}
		return result;
	}

}
