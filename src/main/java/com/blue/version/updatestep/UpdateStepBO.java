package com.blue.version.updatestep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;

public class UpdateStepBO extends BaseBO<UpdateStep> {
	@Autowired
	private UpdateStepDAO dao;
	@Override
	public void update(UpdateStep obj) throws Exception {
		dao.updateByPrimaryKeySelective(obj);

	}

	@Override
	public void insert(UpdateStep obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public UpdateStep queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	public int count(int step,String versionId)throws Exception{
		UpdateStepExample e = new UpdateStepExample();
		e.createCriteria().andStepEqualTo(step).andVersionIdEqualTo(versionId);
		return dao.selectByExample(e).size();
	}
	@Override
	public List<UpdateStep> queryForList(Object obj) throws Exception {
		UpdateStepForm form = (UpdateStepForm)obj;
		UpdateStepExample e = new UpdateStepExample();
		e.createCriteria().andVersionIdEqualTo(form.getVersionId());
		e.setOrderByClause(" STEP asc");
		return dao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

}
