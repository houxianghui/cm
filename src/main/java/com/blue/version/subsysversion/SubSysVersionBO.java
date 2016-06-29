package com.blue.version.subsysversion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class SubSysVersionBO extends BaseBO<SubSysVersion> {
	@Autowired
	private SubSysVersionDAO dao;
	@Override
	public void update(SubSysVersion obj) throws Exception {

		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void insert(SubSysVersion obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public SubSysVersion queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public List<SubSysVersion> queryForList(Object obj) throws Exception {
		SubSysVersionExample e = new SubSysVersionExample();
		SubSysVersionForm form = (SubSysVersionForm)obj;
		e.createCriteria().andVersionIdEqualTo(form.getVersionId());
		return dao.selectByExample(e);
	}

	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}
	
	public List<SubSysVersion> queryForListByVersionId(String versionId){
		SubSysVersionExample e = new SubSysVersionExample();
		e.createCriteria().andVersionIdEqualTo(versionId);
		return dao.selectByExample(e);
	}
	
	public List<SubSysVersion> queryForListBySysName(String sysName){
		SubSysVersionExample e = new SubSysVersionExample();
		e.createCriteria().andSysNameEqualTo(sysName);
		e.setOrderByClause(" NEXT_VERSION desc");
		return dao.selectByExample(e);
	}
	
	public List<String> getAllSubSysList(){
		Map<String, Map<String,String>> m = SingleDicMap.getDicMap(SingleDic.SUB_SYS);
		List<String> result = new ArrayList<String>();
		Set<String> set = m.keySet();
		for(String t : set){
			result.add(m.get(t).get("ITEM_CODE"));
		}
		return result;
	}
}
