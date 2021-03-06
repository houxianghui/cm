package com.blue.version.versionhis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.blue.base.BaseBO;
import com.blue.enums.YesOrNo;
import com.blue.version.subsysversion.SubSysVersion;
import com.eis.util.CheckUtil;

public class VersionHisBO extends BaseBO<VersionHis>{
	@Autowired
	private VersionHisDAO versionHisDAO;
	
	@Override
	public void update(VersionHis obj) throws Exception {
		versionHisDAO.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void insert(VersionHis obj) throws Exception {
		versionHisDAO.insertSelective(obj);
	}

	@Override
	public VersionHis queryForObject(Object obj) throws Exception {
		return versionHisDAO.selectByPrimaryKey(obj.toString());
	}

	@Override
	public List<VersionHis> queryForList(Object obj) throws Exception {
		VersionHisForm form = (VersionHisForm)obj;
		VersionHisExample e = new VersionHisExample();
		VersionHisExample.Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(form.getVersionId_f())){
			c.andVersionIdEqualTo(form.getVersionId_f());
		}
		return versionHisDAO.selectByExample(e);
	}
	public List<VersionHisVO> getDisplay()throws Exception{
		VersionHisExample e = new VersionHisExample();
		e.setOrderByClause(" PLAN_RELEASE_DATE desc");
		List<VersionHis> list = versionHisDAO.selectByExample(e);
		
		final Map<String,Map<String,SubSysVersion>> subMap = new HashMap<String,Map<String,SubSysVersion>>();
		jdbcTemplate.query("select * from sub_sys_version", new RowMapper<SubSysVersion>() {
			
			public SubSysVersion mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubSysVersion s = new SubSysVersion();
				s.setVersionId(rs.getString("version_id"));
				s.setPreVersion(rs.getString("pre_version"));
				s.setNextVersion(rs.getString("next_version"));
				s.setSysName(rs.getString("sys_name"));
				if(subMap.get(s.getVersionId()) == null){
					subMap.put(s.getVersionId(), new HashMap<String,SubSysVersion>());
				}
				subMap.get(s.getVersionId()).put(s.getSysName(),s);
				return s;
			}
		});
		List<VersionHisVO> result = new ArrayList<VersionHisVO>(list.size());
		for(VersionHis v : list){
			VersionHisVO vo = new VersionHisVO();
			BeanUtils.copyProperties(vo, v);
			vo.setSubSys(subMap.get(v.getVersionId()));
			result.add(vo);
		}
		return result;
	}
	
	public List<VersionHisVO> getVersionHisVOList(VersionHisForm form)throws Exception{
		VersionHisExample e = new VersionHisExample();
		VersionHisExample.Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(form.getVersionId_f())){
			c.andVersionIdEqualTo(form.getVersionId_f());
		}
		e.setOrderByClause(" PLAN_RELEASE_DATE desc");
		List<VersionHis> l = versionHisDAO.selectByExample(e);
		final Map<String,Integer> subMap = new HashMap<String,Integer>();
		final Map<String,Integer> projectMap = new HashMap<String,Integer>();
		
		jdbcTemplate.query("select count(*) as num,version_id as versionId from sub_sys_version group by version_id", new RowMapper() {
			
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				subMap.put(rs.getString("versionId"), rs.getInt("num"));
				return null;
			}
		});
		jdbcTemplate.query("select count(*) as num,version_id as versionId from project_list where version_id is not null group by version_id", new RowMapper() {
			
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				projectMap.put(rs.getString("versionId"), rs.getInt("num"));
				return null;
			}
		});
		List<VersionHisVO> result = new ArrayList<VersionHisVO>(l.size());
		for(VersionHis v : l){
			VersionHisVO vo = new VersionHisVO();
			BeanUtils.copyProperties(vo, v);
			vo.setProjectCount(projectMap.get(v.getVersionId())==null?0:projectMap.get(v.getVersionId()));
			vo.setSubSysCount(subMap.get(v.getVersionId())==null?0:subMap.get(v.getVersionId()));
			result.add(vo);
		}
		return result;
	}
	
	
	@Override
	public void delete(Object obj) throws Exception {
		versionHisDAO.deleteByPrimaryKey(obj.toString());
	}
	
	public List<VersionHis> getOpenVersion() throws Exception {
		VersionHisExample e = new VersionHisExample();
		e.createCriteria().andIsReleasedEqualTo(YesOrNo.N.toString());
		return versionHisDAO.selectByExample(e);
	}

}
