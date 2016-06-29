/*
 * @# GetNotWriteNoteStuffBO.java 2009-2-26 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.abc.logic.IbatisBO;
import com.blue.dailyrecord.DailyRecord;
import com.blue.dailyrecord.DailyRecordBO;
import com.blue.dailyrecord.DailyRecordDAO;
import com.blue.dailyrecord.DailyRecordExample;
import com.blue.enums.Roles;
import com.blue.otherdaily.OtherDaily;
import com.blue.otherdaily.OtherDailyDAO;
import com.blue.otherdaily.OtherDailyExample;
import com.eis.base.IbatisBaseBO;
import com.eis.portal.user.UserBO;
import com.eis.portal.user.UserVO;
import com.eis.util.CheckUtil;


public class GetNotWriteNoteStuffBO extends IbatisBO {
	@Autowired
	private UserBO userBO;
	@Autowired
	private DailyRecordDAO dailyRecordDAO;
	@Autowired
	private OtherDailyDAO otherDailyDAO;
	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {

	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {

	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		GetNotWriteNoteStuffForm f = (GetNotWriteNoteStuffForm)obj;
		List<UserVO> users = userBO.getStaffByDeparts(f.getIds(),false);
		
		Map<String,UserVO> remining = new HashMap<String, UserVO>();
		List<String> l = getUserIds(users,remining);
		removeHasDaily(f.getDate(),l,remining);
		removeOtherDaily(f.getDate(),l,remining);
		
		List<UserVO> result = new ArrayList<UserVO>(remining.size());
		Set<String> set = remining.keySet();
		for(String s : set){
			result.add(remining.get(s));
		}
		return result;
	}
	
	public Map<String, List<NotFullVO>> getNotFull(GetNotWriteNoteStuffForm form)throws Exception{
		String date = form.getDate();
		String toDate = form.getToDate();
		List<UserVO> users = userBO.getStaffByDeparts(form.getIds(),false);
		Map<String,UserVO> m = new HashMap<String, UserVO>();
		Map<String,List<NotFullVO>> result = new HashMap<String ,List<NotFullVO>>();
		for(UserVO u:users){
			m.put(u.getUser_id(), u);
		}
		StringBuffer sb = new StringBuffer("select depart_id,work_date,ep_user.user_id,ep_user.user_name,sum(task_cost) as cost" +
				" from ep_user,daily_record " +
				"where ep_user.user_id = daily_record.user_id" );
		if(!CheckUtil.isEmptry(date)){
			sb.append(" and work_date >='"+date+"' ");
		}
		if(!CheckUtil.isEmptry(toDate)){
			sb.append(" and work_date <='"+toDate+"' ");
		}
		sb.append(" group by depart_id,work_date,ep_user.user_id having sum(task_cost)<8");
		List<NotFullVO> l = jdbcTemplate.query(sb.toString(), new NotFullMapper());
		
		StringBuffer other =  new StringBuffer("select depart_id,work_date,b.user_id,b.user_name,sum(cost) as cost from other_daily a,ep_user b where a.INPUT_USER = b.USER_ID ");
		if(!CheckUtil.isEmptry(date)){
			other.append(" and work_date >='"+date+"' ");
		}
		if(!CheckUtil.isEmptry(toDate)){
			other.append(" and work_date <='"+toDate+"' ");
		}
		other.append(" group by depart_id,work_date,b.user_id having sum(cost)<8");
		
		List<NotFullVO> otherl = jdbcTemplate.query(other.toString(), new NotFullMapper());
		Map<String ,NotFullVO> count = new HashMap<String,NotFullVO>();
		for(NotFullVO nf : l){
			count.put(nf.getUserId(), nf);
		}
		for(NotFullVO nf : otherl){//other +daily>=8µÄÅÅ³ý
			if(count.get(nf.getUserId()) == null){
				count.put(nf.getUserId(), nf);
			}else{
				NotFullVO vo = count.get(nf.getUserId());
				vo.setCost(vo.getCost()+nf.getCost());
				if(vo.getCost()>=8){
					count.remove(nf.getUserId());
				}
			}
		}
		for(String s : count.keySet()){
			NotFullVO nf = count.get(s);
			if(m.get(nf.getUserId()) != null){
				if(result.get(nf.getDepartId()) == null){
					result.put(nf.getDepartId(),new ArrayList<NotFullVO>());
				}
				result.get(nf.getDepartId()).add(nf);
			}
		}
		return result;
	}
	class NotFullMapper implements RowMapper<NotFullVO>{
		
		public NotFullVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			NotFullVO nv = new NotFullVO();
			nv.setDepartId(rs.getString("depart_id"));
			nv.setDate(rs.getString("work_date"));
			nv.setUserId(rs.getString("user_id"));
			nv.setUserName(rs.getString("user_name"));
			nv.setCost(rs.getDouble("cost"));
			return nv;
		}
	}
	public List<UserVO> getNotWriteListByDate(String date)throws Exception{
		List<UserVO> users = userBO.getUsersByRole(Roles.stuff);
		Map<String,UserVO> remining = new HashMap<String, UserVO>();
		List<String> l = getUserIds(users,remining);
		removeHasDaily(date,l,remining);
		removeOtherDaily(date,l,remining);
		
		List<UserVO> result = new ArrayList<UserVO>(remining.size());
		Set<String> set = remining.keySet();
		for(String s : set){
			result.add(remining.get(s));
		}
		return result;
	}
	private void removeOtherDaily(String date, List<String> l, Map<String, UserVO> remining) {
		OtherDailyExample e = new OtherDailyExample();
		e.createCriteria().andInputUserIn(l).andWorkDateEqualTo(date);
		List<OtherDaily> others = otherDailyDAO.selectByExample(e);
		for(OtherDaily o : others){
			remining.remove(o.getInputUser());
		}
	}

	private List<String> getUserIds(List<UserVO> users,Map<String,UserVO> map) {
		List<String> result = new ArrayList<String>(users.size());
		for(UserVO u : users){
			result.add(u.getUser_id());
			map.put(u.getUser_id(), u);
		}
		result.add("");
		return result;
	}

	public void removeHasDaily(String date,List<String> l,Map<String,UserVO> map) {
		DailyRecordExample e = new DailyRecordExample();
		e.createCriteria().andUserIdIn(l).andWorkDateEqualTo(date);
		List<DailyRecord> dailys = dailyRecordDAO.selectByExample(e);
		
		for(DailyRecord d : dailys){
			map.remove(d.getUserId());
		}
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#transOperation(java.lang.Object[])
	 */
	public void transOperation(Object[] obj) throws Exception {

	}

}
