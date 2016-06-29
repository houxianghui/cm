package com.yly.para;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.para.ApplytypeinfoExample.Criteria;


public class ApplytypeinfoBO extends IbatisBO {
	private ApplytypeinfoDAO applytypeinfoDAO;


	public ApplytypeinfoDAO getApplytypeinfoDAO() {
		return applytypeinfoDAO;
	}

	public void setApplytypeinfoDAO(ApplytypeinfoDAO applytypeinfoDAO) {
		this.applytypeinfoDAO = applytypeinfoDAO;
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
		applytypeinfoDAO.insertSelective((Applytypeinfo)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		String applytypeid=((Applytypeinfo)obj).getApplyTypeId();
		return applytypeinfoDAO.selectByPrimaryKey(applytypeid);
	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		ApplytypeinfoExample e = new ApplytypeinfoExample();
		Criteria c = e.createCriteria();
		String applytypeid=((ApplytypeinfoForm)obj).getApplyTypeId();
		if(!CheckUtil.isEmptry(applytypeid))
			c.andApplyTypeIdEqualTo(applytypeid);
		return applytypeinfoDAO.selectByExample(e);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}


}
