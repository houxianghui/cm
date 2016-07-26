package com.yly.manu;

import java.util.List;
import com.abc.logic.IbatisBO;
import com.eis.util.CheckUtil;
import com.yly.manu.BimanufacinfotbExample.Criteria;



public class BimanufacinfotbBO extends IbatisBO {
	private BimanufacinfotbDAO manuinfoDAO;



	public BimanufacinfotbDAO getManuinfoDAO() {
		return manuinfoDAO;
	}

	public void setManuinfoDAO(BimanufacinfotbDAO manuinfoDAO) {
		this.manuinfoDAO = manuinfoDAO;
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
		Bimanufacinfotb p = (Bimanufacinfotb)obj;
		manuinfoDAO.insert(p);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}
	public Bimanufacinfotb queryForObject(Short unitid) throws Exception{
		return manuinfoDAO.selectByPrimaryKey(unitid);
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

	public List queryList(BimanufacinfotbForm obj) throws Exception {
		BimanufacinfotbExample e = new BimanufacinfotbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getManufacname_f()))
			c.andManufacnameLike(obj.getManufacname_f()+"%");
		return manuinfoDAO.selectByExample(e);
	}

}
