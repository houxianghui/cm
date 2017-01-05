package com.yly.discard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.discard.DisproductExample.Criteria;


public class DisproductBO extends IbatisBO {
	private DisproductDAO disproductDAO;




	public DisproductDAO getDisproductDAO() {
		return disproductDAO;
	}

	public void setDisproductDAO(DisproductDAO disproductDAO) {
		this.disproductDAO = disproductDAO;
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
		disproductDAO.insertSelective((Disproduct)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		Disproduct vo=((Disproduct)obj);
		String samId=((Disproduct)obj).getSamId();
		return disproductDAO.selectByPrimaryKey(samId);
	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		DisproductForm f=((DisproductForm)obj);
		DisproductExample e = new DisproductExample();
		Criteria c = e.createCriteria();
		
		if(!CheckUtil.isEmptry(f.getSamCsn_f())){
			c.andSamCSNEqualTo(f.getSamCsn_f());
		}else if(!CheckUtil.isEmptry(f.getSamCSN())){
			c.andSamCSNEqualTo(f.getSamCSN());
		}
		
		if(!CheckUtil.isEmptry(f.getSamId_min())){
			c.andSamIdGreaterThanOrEqualTo(f.getSamId_min().trim());
		}		
		if(!CheckUtil.isEmptry(f.getSamId_max())){
			c.andSamIdLessThanOrEqualTo(f.getSamId_max().trim());
		}
		e.setOrderByClause("WkStateChgDate asc");
		return disproductDAO.selectByExample(e);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}


}
