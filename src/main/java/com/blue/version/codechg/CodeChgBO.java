package com.blue.version.codechg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class CodeChgBO  {
	@Autowired
	private CodeChgDAO dao;
	@Transactional
	public void transUpdate(List<CodeChg> l,String versionId){
		CodeChgExample e = new CodeChgExample();
		e.createCriteria().andVersionIdEqualTo(versionId);
		dao.deleteByExample(e);
		for(CodeChg c :l){
			dao.insertSelective(c);
		}
	}
	
	public List<CodeChg> queryForList(String versionId){
		CodeChgExample e = new CodeChgExample();
		e.createCriteria().andVersionIdEqualTo(versionId);
		return dao.selectByExample(e);
	}
}
