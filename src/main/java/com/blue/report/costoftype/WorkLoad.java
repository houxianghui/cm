package com.blue.report.costoftype;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.eis.portal.user.UserVO;

public class WorkLoad implements Comparable<WorkLoad>{
	private UserVO user;
	private Map<String,Double> workCost;
	public WorkLoad(UserVO user) {
		this.user = user;
		workCost = new HashMap<String,Double>();
	}
	
	public UserVO getUser() {
		return user;
	}
	public Map<String,Double> getWorkCost() {
		return workCost;
	}
	public void addWorkCost(String k,Double t){
		workCost.put(k, t);
	}
	
	public double getTotal() {
		Set<String> set = workCost.keySet();
		double total = 0;
		for(String s : set){
			total += workCost.get(s);
		}
		return total;
	}
	
	public int compareTo(WorkLoad o) {
		if(o.getUser().getDepart_id().compareTo(user.getDepart_id())>0){
			return -1;
		}
		if(o.getUser().getDepart_id().compareTo(user.getDepart_id())<0){
			return 1;
		}
		return 0;
	}
}	
