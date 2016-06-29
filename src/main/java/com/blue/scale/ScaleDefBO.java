package com.blue.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;

public class ScaleDefBO extends BaseBO<ScaleDef> {
	@Autowired
	private ScaleDefDAO dao;
	@Override
	public void update(ScaleDef obj) throws Exception {

	}

	@Override
	public void insert(ScaleDef obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ScaleDef queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(Integer.parseInt(obj.toString()));
	}

	@Override
	public List<ScaleDef> queryForList(Object obj) throws Exception {
		return dao.selectByExampleWithoutBLOBs(new ScaleDefExample());
	}

	@Override
	public void delete(Object obj) throws Exception {
		// TODO Auto-generated method stub

	}

}
