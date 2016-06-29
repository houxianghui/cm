package com.blue.report.costofproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.blue.enums.Status;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.blue.project.ProjectListExample;
import com.eis.portal.user.UserBO;
import com.eis.portal.user.UserVO;
import com.projectmaintain.ProjectDistributeBO;

public class ProjectCostBO {
	private JdbcTemplate template;
	@Autowired
	private UserBO userBO;
	@Autowired
	private ProjectListDAO projectListDAO;
	@Autowired
	private ProjectDistributeBO projectDistributeBO;
	private List<ProjectCost> getProjectCost(String startDate, String endDate) {
		return template
				.query("select user_id,project_id,sum(task_cost) as taskCost from daily_record where work_date between ? and ? group by user_id,project_id;",
						new String[] { startDate, endDate }, new ProjectCostRowMapper());
	}
	
	public Map<String,ProjectLoad> getProjectLoad(ProjectCostForm form)throws Exception{
		List<ProjectCost> cost = getProjectCost(form.getStartDate(), form.getEndDate());
		List<UserVO> users = userBO.getStaffByDeparts(form.getIds(),false);
		Map<String,ProjectLoad> result = new HashMap<String, ProjectLoad>();
		for(UserVO v : users){
			ProjectLoad wl = new ProjectLoad(v);
			result.put(v.getUser_id(), wl);
		}
		for(ProjectCost w : cost){
			ProjectLoad wl = result.get(w.getUserId());
			if(wl != null){
				wl.addCost(w.getProjectId(), w.getCost());
			}
		}
		return result;
	}
	
	public List<ProjectList> getActiveProjects(){
		List<String> stat = new ArrayList<String>();
		stat.add(Status.D.toString());
		ProjectListExample e = new ProjectListExample();
		e.createCriteria().andStatNotIn(stat);
		return projectListDAO.selectByExample(e);
//		return template.query("select project_id from project_list where STAT not in ('F','D')", new RowMapper<String>(){
//			@Override
//			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return rs.getString("project_id");
//			}
//		});
	}

	class ProjectCostRowMapper implements RowMapper<ProjectCost> {
		
		public ProjectCost mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProjectCost cost = new ProjectCost();
			cost.setProjectId(rs.getString("project_id"));
			cost.setUserId(rs.getString("user_id"));
			cost.setCost(rs.getDouble("taskCost"));
			return cost;
		}
	}
	@Autowired
	public void setDataSource(DataSource dataSource){
		template = new JdbcTemplate(dataSource);
	}
	
	public static Map<String,Double> getTotalMap(Map<String ,ProjectLoad> map){
		Map<String,Double> result = new HashMap<String,Double>();
		Set<String> set = map.keySet();
		for(String s : set){
			ProjectLoad wl = map.get(s);
			Map<String,Double> v = wl.getCostMap();
			Set<String> types = v.keySet();
			for(String t : types){
				if(result.get(t)==null){
					result.put(t, v.get(t));
				}else{
					result.put(t, result.get(t)+v.get(t));
				}
			}
		}
		return result;
	}
}
