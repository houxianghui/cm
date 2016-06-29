package com.blue.version.paramchg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;

public class ParamChgBO extends BaseBO<ParamChg> {
	@Autowired
	private ParamChgDAO dao;
	
	@Override
	public void update(ParamChg obj) throws Exception {
		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void insert(ParamChg obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public ParamChg queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List<ParamChg> queryForList(Object obj) throws Exception {
		ParamChgForm form = (ParamChgForm)obj;
		ParamChgExample e = new ParamChgExample();
		e.createCriteria().andVersionIdEqualTo(form.getVersionId());
		return dao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

}
