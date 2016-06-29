package com.blue.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;

public class ModuleDefBO extends BaseBO<ModuleDef> {
	@Autowired
	private ModuleDefDAO dao;
	@Autowired
	private ParticipateListDAO participateListDAO;
	public void update(ModuleDef obj, List<ParticipateList> l) throws Exception {
		dao.updateByPrimaryKeySelective(obj);
		ParticipateListExample e = new ParticipateListExample();
		e.createCriteria().andModuleIdEqualTo(obj.getModuleId());
		participateListDAO.deleteByExample(e);
		for(ParticipateList p : l){
			participateListDAO.insertSelective(p);
		}
	}

	public void insert(ModuleDef obj, List<ParticipateList> l) throws Exception {
		dao.insertSelective(obj);
		for(ParticipateList p : l){
			participateListDAO.insertSelective(p);
		}
	}
	
	public String getParticipates(String moduleId){
		ParticipateListExample e = new ParticipateListExample();
		e.createCriteria().andModuleIdEqualTo(moduleId);
		
		List<ParticipateList> l = participateListDAO.selectByExample(e);
		StringBuffer sb = new StringBuffer();
		for(ParticipateList p : l){
			sb.append(p.getUserId());
			sb.append(",");
		}
		return sb.toString();
	}

	@Override
	public ModuleDef queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(obj.toString());
	}

	@Override
	public List<ModuleDef> queryForList(Object obj) throws Exception {
		ModuleDefExample e = new ModuleDefExample();
		ModuleDefForm form = (ModuleDefForm)obj;
		e.createCriteria().andProductCodeEqualTo(form.getProductCode());
		return dao.selectByExample(e);
	}
	
	public List<ModuleDefVO> queryForListVO(String productCode)throws Exception{
		ModuleDefExample me = new ModuleDefExample();
		me.createCriteria().andProductCodeEqualTo(productCode);
		List<ModuleDef> l = dao.selectByExample(me);
		List<ModuleDefVO> result = new ArrayList<ModuleDefVO>();
		for(ModuleDef m : l){
			ModuleDefVO v = new ModuleDefVO();
			BeanUtils.copyProperties(m, v);
			ParticipateListExample e = new ParticipateListExample();
			e.createCriteria().andModuleIdEqualTo(m.getModuleId());
			List<ParticipateList> pl = participateListDAO.selectByExample(e);
			StringBuffer sb = new StringBuffer();
			for(ParticipateList p : pl){
				sb.append(ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,  p.getUserId())+",");
			}
			v.setParticipate(sb.toString());
			result.add(v);
		}
		return result;
	}
	
	public List<ModuleDef> queryForAllModule(){
		return dao.selectByExample(new ModuleDefExample());
	}
	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(obj.toString());
	}

	@Override
	public void insert(ModuleDef obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ModuleDef obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<ModuleDef> queryForMyModule(String userID) {
		ParticipateListExample e = new ParticipateListExample();
		e.createCriteria().andUserIdEqualTo(userID);
		List<ParticipateList> l = participateListDAO.selectByExample(e);
		ModuleDefExample me = new ModuleDefExample();
		me.createCriteria().andManagerIdEqualTo(userID);
		if(l != null && l.size() > 0){
			List<String> key = new ArrayList<String>();
			for(ParticipateList p : l){
				key.add(p.getModuleId());
			}
			me.or(me.createCriteria().andModuleIdIn(key));
		}
		return dao.selectByExample(me);
	}

}
