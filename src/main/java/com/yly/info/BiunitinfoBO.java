package com.yly.info;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.info.BiunitinfotbExample.Criteria;



public class BiunitinfoBO extends IbatisBO {
	private BiunitinfotbDAO biunitinfoDAO;

	public BiunitinfotbDAO getBiunitinfoDAO() {
		return biunitinfoDAO;
	}

	public void setBiunitinfoDAO(BiunitinfotbDAO biunitinfoDAO) {
		this.biunitinfoDAO = biunitinfoDAO;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		Biunitinfotb p = (Biunitinfotb)obj;
		biunitinfoDAO.updateByPrimaryKey(p);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		Biunitinfotb p = (Biunitinfotb)obj;
		biunitinfoDAO.insert(p);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}
	public Biunitinfotb queryForObject(Integer unitid) throws Exception{
		return biunitinfoDAO.selectByPrimaryKey(unitid);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("stoappinfo.queryCardList",obj);
	}


	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public List getList(BiunitinfotbForm obj)throws Exception {
		
		BiunitinfotbExample e = new BiunitinfotbExample();
		Criteria c = e.createCriteria();
		if(obj.getLeadStore()!=null &&obj.getLeadStore()>0)
			c.andLeadStoreEqualTo(obj.getLeadStore());
		if(!CheckUtil.isEmptry(obj.getChnshort()))
			c.andChnshortLike("%"+obj.getChnshort().trim()+"%");
		return biunitinfoDAO.selectByExample(e);
	}
	public void insert(Object obj,String[] cards) throws Exception {
		Biunitinfotb p = (Biunitinfotb)obj;	
		biunitinfoDAO.insert(p);

			
	}
	public List getCardList(BiunitinfotbForm obj)throws Exception {
		
		BiunitinfotbExample e = new BiunitinfotbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getChnshort_f()))
			c.andChnshortLike("%"+obj.getChnshort_f().trim()+"%");
		if(obj.getHyid_f()>0)
			c.andHyidEqualTo(obj.getHyid_f());
		return biunitinfoDAO.selectByExample(e);
	}
	public List queryList(Biunitinfotb obj) throws Exception {
		BiunitinfotbExample e = new BiunitinfotbExample();
		Criteria c = e.createCriteria();
		if(obj.getUnitid()!=null && obj.getUnitid()>-1){
			c.andUnitidEqualTo(obj.getUnitid());
		}
		if(obj.getLeadStore()!=null && obj.getLeadStore()>0){
			c.andLeadStoreEqualTo(obj.getLeadStore());
		}
		if(!CheckUtil.isEmptry(obj.getUnitlevel())){
			c.andUnitlevelEqualTo(obj.getUnitlevel());
		}
		if(obj.getUnitstat()!=null && obj.getUnitstat()>-1){
			c.andUnitstatEqualTo(obj.getUnitstat());
		}
		
		return biunitinfoDAO.selectByExample(e);
	}

}
