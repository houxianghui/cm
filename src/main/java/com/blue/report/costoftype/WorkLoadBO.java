package com.blue.report.costoftype;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.eis.portal.user.UserBO;
import com.eis.portal.user.UserVO;
import com.projectmaintain.ProjectDistributeBO;

public class WorkLoadBO {
	private JdbcTemplate template;
	@Autowired
	private UserBO userBO;
	@Autowired
	private ProjectDistributeBO projectDistributeBO;
	private List<WorkCost> getWorkCostByType(String startDate, String endDate) {
		List<WorkCost> type = template
				.query("select b.user_id,a.Project_class as type,sum(b.task_cost) as taskCost from project_list a,daily_record b where a.project_id = b.project_id and b.work_date between ? and ? group by  b.user_id,a.Project_class;",
						new String[] { startDate, endDate }, new WorkCostRowMapper());
		List<WorkCost> other = template
				.query("select input_user as user_id,type,sum(cost) as taskCost from other_daily where work_date between ? and ? group by input_user,type;",new String[]{startDate,endDate},
						new WorkCostRowMapper());
		type.addAll(other);
		return type;
	}

	public Map<String,WorkLoad> getWorkLoadByType(WorkLoadForm form)throws Exception{
		List<WorkCost> cost = getWorkCostByType(form.getStartDate(), form.getEndDate());
		List<UserVO> users = userBO.getStaffByDeparts(form.getIds(),false);
		Map<String,WorkLoad> result = new HashMap<String, WorkLoad>();
		for(UserVO v : users){
			WorkLoad wl = new WorkLoad(v);
			result.put(v.getUser_id(), wl);
		}
		for(WorkCost w : cost){
			WorkLoad wl = result.get(w.getUserId());
			if(wl == null){
				continue;
			}
			wl.addWorkCost(w.getType(), w.getCost());
		}
		return result;
	}
	public List<WorkCost> getWorkCostByProject(String startDate, String endDate,String departs) {
		return template
				.query("select b.user_id,a.Project_class as type,sum(b.task_cost) as taskCost from project_list a,daily_record b where a.project_id = b.project_id and b.work_date between ? and ? group by  b.user_id,a.Project_class;",
						new String[] { startDate, endDate }, new WorkCostRowMapper());
	}

	class WorkCostRowMapper implements RowMapper<WorkCost> {
	
		public WorkCost mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkCost cost = new WorkCost();
			cost.setType(rs.getString("type"));
			cost.setUserId(rs.getString("user_id"));
			cost.setCost(rs.getDouble("taskCost"));
			return cost;
		}
	}
	@Autowired
	public void setDataSource(DataSource dataSource){
		template = new JdbcTemplate(dataSource);
	}
	
	public static Map<String,Double> getTotalMap(Map<String ,WorkLoad> map){
		Map<String,Double> result = new HashMap<String,Double>();
		Set<String> set = map.keySet();
		for(String s : set){
			WorkLoad wl = map.get(s);
			Map<String,Double> v = wl.getWorkCost();
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
