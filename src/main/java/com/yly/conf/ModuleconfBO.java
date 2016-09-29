package com.yly.conf;


import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.exception.MessageException;
import com.yly.conf.ModuleconfExample.Criteria;

public class ModuleconfBO extends IbatisBO {
	private ModuleconfDAO moduleconfDAO;




	public ModuleconfDAO getModuleconfDAO() {
		return moduleconfDAO;
	}

	public void setModuleconfDAO(ModuleconfDAO moduleconfDAO) {
		this.moduleconfDAO = moduleconfDAO;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		moduleconfDAO.updateByPrimaryKeySelective((Moduleconf)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		moduleconfDAO.insertSelective((Moduleconf)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		int moduleid=((Moduleconf)obj).getModuleId();
		return moduleconfDAO.selectByPrimaryKey(moduleid);
	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		ModuleconfExample e = new ModuleconfExample();
		Criteria c = e.createCriteria();
		int moduleid=((ModuleconfForm)obj).getModuleId();
		if(moduleid!=0)
			c.andModuleIdEqualTo(moduleid);
		return moduleconfDAO.selectByExample(e);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public Moduleconf queryForObject(Moduleconf obj) throws Exception {
		ModuleconfExample e = new ModuleconfExample();
		Criteria c = e.createCriteria();
		if(obj.getModuleId()!=0)
			c.andModuleIdEqualTo(obj.getModuleId());
		Moduleconf vo = new Moduleconf();
		List l=moduleconfDAO.selectByExample(e);
		if (l != null) {
		    Iterator iter = l.iterator();
		    if (iter.hasNext()) {
		    	vo=(Moduleconf)iter.next();
		    }else{
				throw new MessageException("未找到合适的方法,请联系技术人员!"+obj.getModuleId());
		    }
		}
		return vo;
	}

}
