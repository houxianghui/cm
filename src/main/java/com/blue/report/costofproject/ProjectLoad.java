package com.blue.report.costofproject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.eis.portal.user.UserVO;

public class ProjectLoad implements Comparable<ProjectLoad>{
	private UserVO user;
	private Map<String,Double> costMap;
	public ProjectLoad(UserVO user) {
		this.user = user;
		costMap = new HashMap<String,Double>();
	}
	
	public UserVO getUser() {
		return user;
	}
	public void addCost(String k,Double t){
		costMap.put(k, t);
	}
	public Map<String, Double> getCostMap() {
		return costMap;
	}
	public double getTotal() {
		Set<String> set = costMap.keySet();
		double total = 0;
		for(String s : set){
			total += costMap.get(s);
		}
		return total;
	}

	public int compareTo(ProjectLoad o) {
		if(o.getUser().getDepart_id() == null || user.getDepart_id() == null){
			return 0;
		}
		if(o.getUser().getDepart_id().compareTo(user.getDepart_id())>0){
			return -1;
		}
		if(o.getUser().getDepart_id().compareTo(user.getDepart_id())<0){
			return 1;
		}
		return 0;
	}
}
