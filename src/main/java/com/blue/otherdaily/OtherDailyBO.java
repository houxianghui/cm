package com.blue.otherdaily;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.logic.IbatisBO;
import com.blue.otherdaily.OtherDailyExample.Criteria;
import com.eis.base.BaseForm;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;

public class OtherDailyBO extends IbatisBO {
	@Autowired
	private OtherDailyDAO otherDailyDAO;
	@Override
	public void update(Object obj) throws Exception {
		otherDailyDAO.updateByPrimaryKeySelective((OtherDaily)obj);

	}

	@Override
	public void insert(Object obj) throws Exception {
		otherDailyDAO.insertSelective((OtherDaily)obj);

	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return otherDailyDAO.selectByPrimaryKey(Long.parseLong((String)obj));
	}
	@Override
	public List queryForList(Object obj) throws Exception {
		return null;
	}
	public List queryForList(Object obj,UserContext user) throws Exception {
		OtherDailyExample e = new OtherDailyExample();
		Criteria c = e.createCriteria();
		
		c.andInputUserEqualTo(user.getUserID());
		OtherDailyForm f = (OtherDailyForm)obj;
		if(!CheckUtil.isEmptry(f.getWorkDate())){
			c.andWorkDateEqualTo(f.getWorkDate());
		}
		e.setOrderByClause(" WORK_DATE desc");
		return otherDailyDAO.selectByExample(e);
	}
	public List queryForAll(Object obj,UserContext user) throws Exception {
		OtherDailyExample e = new OtherDailyExample();
		e.createCriteria().andInputUserEqualTo(user.getUserID());
		e.setOrderByClause(" WORK_DATE desc");
		return otherDailyDAO.selectByExample(e);
	}
	public List query(BaseForm form)throws Exception{
		OtherDailyExample e = new OtherDailyExample();
		OtherDailyExample.Criteria c = e.createCriteria();
		OtherDailyForm f = (OtherDailyForm)form;
		if(!CheckUtil.isEmptry(f.getInputUser())){
			c.andInputUserEqualTo(f.getInputUser());
		}
		if(!CheckUtil.isEmptry(f.getStartDate())){
			c.andWorkDateGreaterThanOrEqualTo(f.getStartDate());
		}
		if(!CheckUtil.isEmptry(f.getEndDate())){
			c.andWorkDateLessThanOrEqualTo(f.getEndDate());
		}
		e.setOrderByClause(" WORK_DATE desc");
		return otherDailyDAO.selectByExample(e);
	}
	@Override
	public void delete(Object obj) throws Exception {
		otherDailyDAO.deleteByPrimaryKey(Long.parseLong((String)obj));

	}

}
