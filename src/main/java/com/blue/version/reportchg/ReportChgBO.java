package com.blue.version.reportchg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;

public class ReportChgBO extends BaseBO<ReportChg> {
	@Autowired
	private ReportChgDAO dao;
	@Override
	public void update(ReportChg obj) throws Exception {
		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void insert(ReportChg obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public ReportChg queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List<ReportChg> queryForList(Object obj) throws Exception {
		ReportChgForm form = (ReportChgForm)obj;
		ReportChgExample e = new ReportChgExample();
		e.createCriteria().andVersionIdEqualTo(form.getVersionId());
		return dao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

}
