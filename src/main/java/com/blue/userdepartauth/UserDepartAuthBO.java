package com.blue.userdepartauth;

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

import com.blue.taxi.TaxiInfoAction;
import com.blue.taxi.TaxiInfoBO;
import com.blue.taxi.TaxiInfoDAO;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.UserDepartMap;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.html.HtmlCheckbox;
import com.eis.html.HtmlLabel;
import com.eis.html.HtmlTD;
import com.eis.html.HtmlTR;
import com.eis.html.HtmlTable;
import com.eis.portal.UserContext;
import com.eis.portal.user.UserDAO;
import com.eis.portal.user.UserRoleDAO;
import com.eis.portal.user.UserRoleVO;

public class UserDepartAuthBO{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDepartAuthDAO userDepartAuthDAO;
	@Autowired
	private UserRoleDAO userDAO;
	
	@Transactional
	public void transInsert(List<UserDepartAuth> list,List<String> list2) {
		UserDepartAuthExample uda = new UserDepartAuthExample();
		uda.createCriteria().andUserIdIn(list2);
		userDepartAuthDAO.deleteByExample(uda);
		for(UserDepartAuth r : list){
			userDepartAuthDAO.insertSelective(r);
		}
	}

	public Object getConfigTable(String roleId) throws Exception{
		Map<String, Map<String,String>> departMap = SingleDicMap.getDicMap(SingleDic.DEPART);
		List <UserRoleVO> users = userDAO.queryList("select * from ep_user_role where ROLE_ID='"+roleId+"'");
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		Set<String> set = departMap.keySet();
		for(String s : set){
			for(UserRoleVO user:users){
				UserDepartAuthKey r = new UserDepartAuthKey(s,user.getUser_id());
				map.put(r.getKey(),false);
			}
			
		}
		List<UserDepartAuth> l = userDepartAuthDAO.selectByExample(new UserDepartAuthExample());
		for(UserDepartAuth r:l){
			UserDepartAuthKey key = new UserDepartAuthKey(r);
			map.put(key.getKey(), true);
		}
		return makeTable(users,departMap,map);
	}
	
	
	private String makeTable(List<UserRoleVO> users, Map<String, Map<String, String>> departMap, Map<String, Boolean> map) {
		HtmlTable table = new HtmlTable();
		table.setId("userDepartAuth");
		
		HtmlTR head = new HtmlTR();
		
		HtmlLabel empty = new HtmlLabel("");
		head.addTD(new HtmlTD(empty));
		
		for(UserRoleVO r : users){
			HtmlLabel h = new HtmlLabel(ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,r.getUser_id()));
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
			
			for(UserRoleVO r : users){
				UserDepartAuthKey key = new UserDepartAuthKey(s,r.getUser_id());
				HtmlCheckbox box = new HtmlCheckbox(key.getKey(), map.get(key.getKey()));
				HtmlTD td = new HtmlTD(box);
				tr.addTD(td);
			}
			table.addTr(tr);
		}
		return table.toString();
	}
	
	public Map<String,String> getGrantedDeparts(UserContext user){
		init(user.getUserID());
		UserDepartMap map = UserDepartMap.getInstance();
		String userId = user.getUserID();
		Map<String,String> departs = map.getDeparts(userId);
		return departs;
	}

	private void init(String userId) {
		UserDepartMap map = UserDepartMap.getInstance();
		if(map.getDeparts(userId) != null){
			return;
		}
		logger.debug("初始化角色部门权限");
		UserDepartAuthExample e = new UserDepartAuthExample();
		e.createCriteria().andUserIdEqualTo(userId);
		List<UserDepartAuth> l = userDepartAuthDAO.selectByExample(new UserDepartAuthExample());
		for(UserDepartAuth r : l){
			map.put(r.getUserId(), r.getDepartId());
		}
	}


}
