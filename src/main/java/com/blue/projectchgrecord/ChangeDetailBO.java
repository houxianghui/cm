package com.blue.projectchgrecord;

import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;

public class ChangeDetailBO extends IbatisBO {
	
	private ChangeDetailDAO changeDetailDao;
	@Override
	public void update(Object obj) throws Exception {
		changeDetailDao.updateByPrimaryKeySelective((ChangeDetail)obj);
	}

	@Override
	public void insert(Object obj) throws Exception {
		changeDetailDao.insertSelective((ChangeDetail)obj);
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return changeDetailDao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		ChangeDetailForm form = (ChangeDetailForm)obj;
		ChangeDetailExample e = new ChangeDetailExample();
		e.createCriteria().andIdEqualTo(form.getId());
		return changeDetailDao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		changeDetailDao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

	public void setChangeDetailDao(ChangeDetailDAO changeDetailDao) {
		this.changeDetailDao = changeDetailDao;
	}

}
