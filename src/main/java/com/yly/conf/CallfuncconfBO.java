package com.yly.conf;


import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.exception.MessageException;
import com.eis.util.CheckUtil;
import com.yly.conf.CallfuncconfExample.Criteria;


public class CallfuncconfBO extends IbatisBO {
	private CallfuncconfDAO callfuncconfDAO;




	public CallfuncconfDAO getCallfuncconfDAO() {
		return callfuncconfDAO;
	}

	public void setCallfuncconfDAO(CallfuncconfDAO callfuncconfDAO) {
		this.callfuncconfDAO = callfuncconfDAO;
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
		callfuncconfDAO.insertSelective((Callfuncconf)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		String callfuncid=((Callfuncconf)obj).getCallerId();
		return callfuncconfDAO.selectByPrimaryKey(callfuncid);
	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		CallfuncconfExample e = new CallfuncconfExample();
		Criteria c = e.createCriteria();
		String callfuncid=((CallfuncconfForm)obj).getCallerId();
		if(!CheckUtil.isEmptry(callfuncid))
			c.andCallerIdEqualTo(callfuncid);
		return callfuncconfDAO.selectByExample(e);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public Callfuncconf queryForObject(Callfuncconf obj) throws Exception {
		CallfuncconfExample e = new CallfuncconfExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getApplyTypeId()))
			c.andApplyTypeIdEqualTo(obj.getApplyTypeId());
		if(!CheckUtil.isEmptry(obj.getProdId()))
			c.andProdIdEqualTo(obj.getProdId());
		if(!CheckUtil.isEmptry(obj.getManufacId()))
			c.andManufacIdEqualTo(obj.getManufacId());
		if(obj.getOperationType()>0)
			c.andOperationTypeEqualTo(obj.getOperationType());		
		Callfuncconf vo = new Callfuncconf();
		List l=callfuncconfDAO.selectByExample(e);
		if (l != null) {
		    Iterator iter = l.iterator();
		    if (iter.hasNext()) {
		    	vo=(Callfuncconf)iter.next();
		    }else{
				throw new MessageException("未找到合适的方法,请联系技术人员!"+obj.getApplyTypeId()+obj.getProdId()+obj.getManufacId()+obj.getOperationType());
		    }
		}
		return vo;
	}

}
