package com.yly.reuse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.reuse.StoreuseExample.Criteria;


public class StoreuseBO extends IbatisBO {
	private StoreuseDAO storeuseDAO;





	public StoreuseDAO getStoreuseDAO() {
		return storeuseDAO;
	}

	public void setStoreuseDAO(StoreuseDAO storeuseDAO) {
		this.storeuseDAO = storeuseDAO;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {

	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		storeuseDAO.insertSelective((Storeuse)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		Storeuse vo=((Storeuse)obj);
		String samId=((Storeuse)obj).getSamId();
		return storeuseDAO.selectByPrimaryKey(samId);
	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		Storeuse vo=((Storeuse)obj);
		StoreuseExample e = new StoreuseExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(vo.getSamCSN()))
			c.andSamCSNEqualTo(vo.getSamCSN());
		if(!CheckUtil.isEmptry(vo.getSamId()))
			c.andSamIdEqualTo(vo.getSamId());	
		e.setOrderByClause("WkStateChgDate asc");
		return storeuseDAO.selectByExample(e);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}


}
