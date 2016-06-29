package com.yly.pki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.pki.SecpkitbExample.Criteria;


public class SecpkitbBO extends IbatisBO {
	private SecpkitbDAO secpkitbDAO;


	public SecpkitbDAO getSecpkitbDAO() {
		return secpkitbDAO;
	}

	public void setSecpkitbDAO(SecpkitbDAO secpkitbDAO) {
		this.secpkitbDAO = secpkitbDAO;
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
		secpkitbDAO.insertSelective((Secpkitb)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		Secpkitb vo=((Secpkitb)obj);
		String samId=((Secpkitb)obj).getSamId();
		return secpkitbDAO.selectByPrimaryKey(samId);
	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		Secpkitb vo=((Secpkitb)obj);
		SecpkitbExample e = new SecpkitbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(vo.getSamCSN()))
			c.andSamCSNEqualTo(vo.getSamCSN());
		if(!CheckUtil.isEmptry(vo.getSamId()))
			c.andSamIdEqualTo(vo.getSamId());	
		e.setOrderByClause("WkStateChgDate asc");
		return secpkitbDAO.selectByExample(e);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}


}
