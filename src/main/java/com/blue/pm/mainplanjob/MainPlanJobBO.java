package com.blue.pm.mainplanjob;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;
import com.blue.enums.Status;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.project.ProjectListExample;

public class MainPlanJobBO extends BaseBO<MainPlanJob> {
	@Autowired
	private MainPlanJobDAO dao;
	@Autowired
	private ProjectListDAO projectListDAO;
	@Override
	public void update(MainPlanJob obj) throws Exception {
		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void insert(MainPlanJob obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public MainPlanJob queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(obj.toString());
	}

	public List<ProjectList> queryForProjects(String userId) throws Exception {
		MainPlanJobExample e = new MainPlanJobExample();
		e.createCriteria().andUserIdEqualTo(userId).andIsDoenEqualTo("N");
		List<MainPlanJob> l = dao.selectByExample(e);
		if(l == null || l.size() == 0){
			return new ArrayList<ProjectList>();
		}
		List<String> key = new ArrayList<String>(l.size());
		for(MainPlanJob m : l){
			key.add(m.getProjectId());
		}
		ProjectListExample pe = new ProjectListExample();
		List<String> unActiveStatus = new ArrayList<String>();
		unActiveStatus.add(Status.F.toString());
		unActiveStatus.add(Status.D.toString());
		pe.createCriteria().andProjectIdIn(key).andStatNotIn(unActiveStatus);
		return projectListDAO.selectByExample(pe);
	}

	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(obj.toString());
	}

	@Override
	public List<MainPlanJob> queryForList(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
