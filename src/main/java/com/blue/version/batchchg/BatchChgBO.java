package com.blue.version.batchchg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;

public class BatchChgBO extends BaseBO<BatchChg> {
	@Autowired
	private BatchChgDAO dao;
	@Override
	public void update(BatchChg obj) throws Exception {
		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void insert(BatchChg obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public BatchChg queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List<BatchChg> queryForList(Object obj) throws Exception {
		BatchChgForm form = (BatchChgForm)obj;
		BatchChgExample e = new BatchChgExample();
		e.createCriteria().andVersionIdEqualTo(form.getVersionId());
		return dao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

}
