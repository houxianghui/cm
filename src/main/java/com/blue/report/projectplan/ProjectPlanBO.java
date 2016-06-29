package com.blue.report.projectplan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.blue.enums.Steps;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.projectdistribute.ProjectDistribute;
import com.blue.projectdistribute.ProjectDistributeDAO;
import com.blue.projectdistribute.ProjectDistributeExample;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.util.DateUtil;

public class ProjectPlanBO {
	@Autowired
	private ProjectDistributeDAO projectDistributeDAO;
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProjectListDAO projectListDAO;
	
	public ProjectList getProjectInfo(String projectId){
		return projectListDAO.selectByPrimaryKey(projectId);
	}
	public static String[] steps = {Steps.R.toString(),Steps.G.toString(),Steps.D.toString(),Steps.S.toString(),Steps.U.toString(),Steps.P.toString()};
	public static String[] stepDesc = {
		"需求分析阶段<br>(包括需求分析，需求评审）",
		"方案设计阶段<br>(包括功能设计，功能评审）",
		"代码开发、单元<br>测试阶段",
		"集成测试阶段",
		"用户测试阶段",
		"投产阶段"};
	
	private void setStepPlanGroupInfo(String projectId,StepPlan s){
		ProjectDistributeExample e = new ProjectDistributeExample();
		e.createCriteria().andProjectIdEqualTo(projectId).andStepEqualTo(s.getStep());
		List<ProjectDistribute> l = projectDistributeDAO.selectByExample(e);
		double total = 0;
		List<String> userName = new ArrayList<String>();
		for(ProjectDistribute p : l){
			total+=DateUtil.getDaysExceptWeekend(p.getStartDate(), p.getEndDate())*p.getPercent()/100.0;
			userName.add(ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, p.getUserId()));
		}
		s.setUsers(userName);
		s.setWorkLoad(total);
	}
	
	public Map<String,StepPlan> getStepPlans(final String projectId){
		List<StepPlan> l = jdbcTemplate.query("select min(start_date) as startDate,max(end_date) as endDate,step from project_distribute where project_id=?  group by STEP;",new String[]{projectId}, new RowMapper<StepPlan>(){
		
			public StepPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
				StepPlan s = new StepPlan();
				s.setStartDate(rs.getString("startDate"));
				s.setEndDate(rs.getString("endDate"));
				s.setStep(rs.getString("step").trim());
				setStepPlanGroupInfo(projectId, s);
				return s;
			}
		});
		Map<String,StepPlan> m = new HashMap<String, StepPlan>();
		for(StepPlan s :l){
			m.put(s.getStep(), s);
		}
		return m;
		
	}
	
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
