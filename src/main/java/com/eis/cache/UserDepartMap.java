package com.eis.cache;

import java.util.HashMap;
import java.util.Map;

public class UserDepartMap {
	private static Map<String, Map<String,String>> map = new HashMap<String, Map<String,String>>();
	
	private UserDepartMap (){};
	public static UserDepartMap getInstance(){
		return new UserDepartMap();
	}
	public void clear(){
		map.clear();
	}
	public Map<String,String> getDeparts(String roleId){
		return map.get(roleId);
	}
	public void put(String roleId,String depart){
		Map<String,String> m = map.get(roleId);
		if(m == null){
			m = new HashMap<String,String>();
			map.put(roleId, m);
		}
		m.put(depart, depart);
	}
}
