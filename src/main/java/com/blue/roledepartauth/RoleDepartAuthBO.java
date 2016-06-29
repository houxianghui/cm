package com.blue.roledepartauth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eis.cache.RoleDepartMap;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.html.HtmlCheckbox;
import com.eis.html.HtmlLabel;
import com.eis.html.HtmlTD;
import com.eis.html.HtmlTR;
import com.eis.html.HtmlTable;
import com.eis.portal.UserContext;
import com.eis.portal.role.RoleDAO;
import com.eis.portal.role.RoleVO;

public class RoleDepartAuthBO{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private RoleDepartAuthDAO roleDepartAuthDAO;
	@Autowired
	private RoleDAO roleDAO;
	
	@Transactional
	public void transInsert(List<RoleDepartAuth> list) {
		roleDepartAuthDAO.deleteByExample(new RoleDepartAuthExample());
		for(RoleDepartAuth r : list){
			roleDepartAuthDAO.insertSelective(r);
		}
	}

	public Object getConfigTable() throws Exception{
		Map<String, Map<String,String>> departMap = SingleDicMap.getDicMap(SingleDic.DEPART);
		List<RoleVO> roles = roleDAO.queryList("select * from ep_role where STAT='1'");
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		Set<String> set = departMap.keySet();
		for(String s : set){
			for(RoleVO role:roles){
				RoleDepartAuthKey r = new RoleDepartAuthKey(s,role.getRole_id());
				map.put(r.getKey(),false);
			}
			
		}
		List<RoleDepartAuth> l = roleDepartAuthDAO.selectByExample(new RoleDepartAuthExample());
		for(RoleDepartAuth r:l){
			RoleDepartAuthKey key = new RoleDepartAuthKey(r);
			map.put(key.getKey(), true);
		}
		return makeTable(roles,departMap,map);
	}
	
	
	private String makeTable(List<RoleVO> roles, Map<String, Map<String, String>> departMap, Map<String, Boolean> map) {
		HtmlTable table = new HtmlTable();
		table.setId("roleDepartAuth");
		
		HtmlTR head = new HtmlTR();
		
		HtmlLabel empty = new HtmlLabel("");
		head.addTD(new HtmlTD(empty));
		
		for(RoleVO r : roles){
			HtmlLabel h = new HtmlLabel(r.getRole_name());
			HtmlTD td = new HtmlTD(h);
			head.addTD(td);
		}
		table.addTr(head);
		
		Set<String> set = departMap.keySet();
		List<String> sort = new ArrayList<String>();
		for(String s : set){
			sort.add(s);
		}
		Collections.sort(sort);
		for(String s : sort){
			HtmlTR tr = new HtmlTR();
			HtmlLabel lab = new HtmlLabel(departMap.get(s).get("ITEM_VAL"));
			HtmlTD depart = new HtmlTD(lab);
			tr.addTD(depart);
			
			for(RoleVO r : roles){
				RoleDepartAuthKey key = new RoleDepartAuthKey(s,r.getRole_id());
				HtmlCheckbox box = new HtmlCheckbox(key.getKey(), map.get(key.getKey()));
				HtmlTD td = new HtmlTD(box);
				tr.addTD(td);
			}
			table.addTr(tr);
		}
		return table.toString();
	}
	
	public Map<String,String> getGrantedDeparts(UserContext user){
		init(user.getRoleID());
		RoleDepartMap map = RoleDepartMap.getInstance();
		String roleId = user.getRoleID();
		Map<String,String> departs = map.getDeparts(roleId);
		return departs;
	}

	private void init(String roleId) {
		RoleDepartMap map = RoleDepartMap.getInstance();
		if(map.getDeparts(roleId) != null){
			return;
		}
		logger.debug("初始化角色部门权限");
		RoleDepartAuthExample e = new RoleDepartAuthExample();
		e.createCriteria().andRoleIdEqualTo(roleId);
		List<RoleDepartAuth> l = roleDepartAuthDAO.selectByExample(new RoleDepartAuthExample());
		for(RoleDepartAuth r : l){
			map.put(r.getRoleId(), r.getDepartId());
		}
	}
	

}
