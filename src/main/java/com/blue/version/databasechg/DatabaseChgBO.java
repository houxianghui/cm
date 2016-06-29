package com.blue.version.databasechg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;

public class DatabaseChgBO extends BaseBO<DatabaseChg>{
	@Autowired
	private DatabaseChgDAO dao;
	@Override
	public void update(DatabaseChg obj) throws Exception {
		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void insert(DatabaseChg obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public DatabaseChg queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List<DatabaseChg> queryForList(Object obj) throws Exception {
		DatabaseChgForm form = (DatabaseChgForm)obj;
		DatabaseChgExample e = new DatabaseChgExample();
		e.createCriteria().andVersionIdEqualTo(form.getVersionId());
		return dao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}
	
}
