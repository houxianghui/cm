package com.blue.report.projectinfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.logic.IbatisBO;
import com.blue.dailyrecord.DailyRecord;
import com.blue.dailyrecord.DailyRecordDAO;
import com.blue.dailyrecord.DailyRecordExample;
import com.blue.mile.MileStone;
import com.blue.mile.MileStoneDAO;
import com.blue.mile.MileStoneExample;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.project.ProjectListExample;
import com.blue.projectdistribute.ProjectDistribute;
import com.blue.projectdistribute.ProjectDistributeDAO;
import com.blue.projectdistribute.ProjectDistributeExample;
import com.eis.util.DateUtil;

public class ProjectInfoBO extends IbatisBO {
	private ProjectListDAO projectListDAO;
	private MileStoneDAO mileStoneDAO;
	private ProjectDistributeDAO projectDistributeDAO;
	@Autowired
	private DailyRecordDAO dailyRecordDAO;
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
		ProjectListExample e = new ProjectListExample();
		e.setOrderByClause(" PLAN_START_DATE");
		List<ProjectList> projects = projectListDAO.selectByExample(e);
		List<ProjectInfo> result = new ArrayList<ProjectInfo>(projects.size());
		for(ProjectList pl:projects){
			ProjectInfo info = new ProjectInfo();
			info.setProject(pl);
			
			MileStoneExample me = new MileStoneExample();
			me.createCriteria().andProjectIdEqualTo(pl.getProjectId());
			List<MileStone> ms = mileStoneDAO.selectByExample(me);
			info.setStones(ms);
			
			ProjectDistributeExample pe = new ProjectDistributeExample();
			pe.createCriteria().andProjectIdEqualTo(pl.getProjectId());
			List<ProjectDistribute> pds = projectDistributeDAO.selectByExample(pe);
			double count = 0;
			for(ProjectDistribute pd : pds){
				long days = DateUtil.getDaysExceptWeekend(pd.getStartDate(), pd.getEndDate());
				count += (days*(pd.getPercent()/100.0));
			}
			info.setPlanWorkLoad(count);
			DailyRecordExample de = new DailyRecordExample();
			de.createCriteria().andProjectIdEqualTo(pl.getProjectId());
			List<DailyRecord> dl = dailyRecordDAO.selectByExample(de);
			double realCount = 0;
			for(DailyRecord d : dl){
				realCount += d.getTaskCost().doubleValue();
			}
			info.setRealWorkLoad(realCount);
			long delayDays = DateUtil.getDays(pl.getPlanEndDate(),DateUtil.getDTStr());
			info.setDelayDays(delayDays<0?0:(int)delayDays);
			result.add(info);
		}
		return result;
	}

	@Override
	public void delete(Object obj) throws Exception {
		// TODO Auto-generated method stub

	}

	public ProjectListDAO getProjectListDAO() {
		return projectListDAO;
	}

	public void setProjectListDAO(ProjectListDAO projectListDAO) {
		this.projectListDAO = projectListDAO;
	}

	public MileStoneDAO getMileStoneDAO() {
		return mileStoneDAO;
	}

	public void setMileStoneDAO(MileStoneDAO mileStoneDAO) {
		this.mileStoneDAO = mileStoneDAO;
	}

	public ProjectDistributeDAO getProjectDistributeDAO() {
		return projectDistributeDAO;
	}

	public void setProjectDistributeDAO(ProjectDistributeDAO projectDistributeDAO) {
		this.projectDistributeDAO = projectDistributeDAO;
	}

}
