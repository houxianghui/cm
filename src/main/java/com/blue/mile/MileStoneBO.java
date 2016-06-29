package com.blue.mile;

import java.util.List;


import com.abc.logic.IbatisBO;
public class MileStoneBO extends IbatisBO {
	private MileStoneDAO mileStoneDao;
	@Override
	public void update(Object obj) throws Exception {
		mileStoneDao.updateByPrimaryKeySelective((MileStone)obj);
	}

	@Override
	public void insert(Object obj) throws Exception {
		mileStoneDao.insert((MileStone)obj);
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return mileStoneDao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		MileStoneExample e = new MileStoneExample();
		e.createCriteria().andProjectIdEqualTo(obj.toString());
		return mileStoneDao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		mileStoneDao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}
	public void setMileStoneDao(MileStoneDAO dao) {
		this.mileStoneDao = dao;
	}

}
