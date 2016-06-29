package com.blue.mailconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abc.logic.IbatisBO;
import com.blue.functiondef.FunctionDef;
import com.blue.functiondef.FunctionDefDAO;
import com.blue.functiondef.FunctionDefExample;
import com.blue.functiondef.FunctionDefExample.Criteria;
import com.blue.user.EpUser;
import com.eis.html.HtmlCheckbox;
import com.eis.html.HtmlLabel;
import com.eis.html.HtmlTD;
import com.eis.html.HtmlTR;
import com.eis.html.HtmlTable;
import com.eis.portal.role.RoleDAO;
import com.eis.portal.role.RoleVO;
import com.eis.portal.user.UserDAO;
import com.eis.portal.user.UserVO;
import com.eis.util.CheckUtil;

public class MailConfigBO extends IbatisBO {
	private RoleDAO roleDAO;
	private FunctionDefDAO functionDefDAO;
	private MailConfigDAO mailConfigDAO;
	private UserDAO userDAO;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object obj) throws Exception {
		// TODO Auto-generated method stub

	}
	public void transInsert(List<MailConfig> list){
		MailConfigExample e = new MailConfigExample();
		mailConfigDAO.deleteByExample(e);
		for(MailConfig m : list){
			mailConfigDAO.insertSelective(m);
		}
	}
	public String getConfigTable()throws Exception{
		List<RoleVO> roles = roleDAO.queryList("select * from ep_role where STAT='1'");
		List<FunctionDef> functions = functionDefDAO.selectByExample(new FunctionDefExample());
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		
		for(FunctionDef fd : functions){
			for(RoleVO r : roles){
				map.put(getkey(fd, r), false);
			}
		}
		List<MailConfig> ml = mailConfigDAO.selectByExample(new MailConfigExample());
		for(MailConfig m : ml){
			map.put(getkey(m), true);
		}
		return makeTable(roles,functions,map);
	}

	private String makeTable(List<RoleVO> roles, List<FunctionDef> functions, Map<String, Boolean> map) {
		HtmlTable table = new HtmlTable();
		table.setId("MAIL");
		HtmlTR head = new HtmlTR();
		
		HtmlLabel empty = new HtmlLabel("");
		head.addTD(new HtmlTD(empty));
		
		for(RoleVO r : roles){
			HtmlLabel h = new HtmlLabel(r.getRole_name());
			HtmlTD td = new HtmlTD(h);
			head.addTD(td);
		}
		table.addTr(head);
		
		for(FunctionDef fd : functions){
			HtmlTR tr = new HtmlTR();
			HtmlLabel lab = new HtmlLabel(fd.getMemo());
			HtmlTD func = new HtmlTD(lab);
			tr.addTD(func);
			
			for(RoleVO r : roles){
				String key = getkey(fd, r);
				HtmlCheckbox box = new HtmlCheckbox(key, map.get(key));
				HtmlTD td = new HtmlTD(box);
				tr.addTD(td);
			}
			table.addTr(tr);
		}
		return table.toString();
	}
	
	private String getkey(FunctionDef def,RoleVO role){
		return def.getFunctionId()+"-"+role.getRole_id();
	}
	private String getkey(MailConfig mc){
		return mc.getFunctionId()+"-"+mc.getRoleId();
	}
	public String[] getNotifyUsers(Object obj,String function)throws Exception{
		MailConfigExample e = new MailConfigExample();
		e.createCriteria().andFunctionIdEqualTo(getFunctionId(obj, function));
		List<MailConfig> list = mailConfigDAO.selectByExample(e);
		if(list == null || list.size() == 0){
			return null;
		}
		StringBuffer roles = new StringBuffer();
		for(MailConfig mc : list){
			roles.append("'"+mc.getRoleId()+"',");
		}
		roles.append("''");
		List<UserVO> users = userDAO.queryList("select * from ep_user where STAT in ('1','4') and user_id in (select user_id from ep_user_role where ROLE_ID in ("+roles+"))");
		String[] result = new String[users.size()];
		for(int i = 0;i<result.length;i++){
			result[i] = users.get(i).getEmail();
		}
		return result;
	}
	
	private int getFunctionId(Object obj,String function){
		FunctionDefExample e = new FunctionDefExample();
		Criteria c = e.createCriteria();
		c.andClassNameEqualTo(obj.getClass().getName());
		if(!CheckUtil.isEmptry(function)){
			c.andFunctionNameEqualTo(function);
		}
		List<FunctionDef> l = functionDefDAO.selectByExample(e);
		if(l == null || l.size() == 0){
			return -1;
		}
		return l.get(0).getFunctionId();
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setFunctionDefDAO(FunctionDefDAO functionDefDAO) {
		this.functionDefDAO = functionDefDAO;
	}

	public void setMailConfigDAO(MailConfigDAO mailConfigDAO) {
		this.mailConfigDAO = mailConfigDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
