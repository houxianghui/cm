package com.blue.version.datachg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;

public class DataChgBO extends BaseBO<DataChg> {
	@Autowired
	private DataChgDAO dao;
	@Override
	public void update(DataChg obj) throws Exception {
		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void insert(DataChg obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public DataChg queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List<DataChg> queryForList(Object obj) throws Exception {
		DataChgForm form = (DataChgForm)obj;
		DataChgExample e = new DataChgExample();
		e.createCriteria().andVersionIdEqualTo(form.getVersionId());
		return dao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

}
