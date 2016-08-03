package com.yly.ls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.ls.LsinfoExample.Criteria;


public class LsinfoBO extends IbatisBO {
	private LsinfoDAO lsinfoDAO;



	public LsinfoDAO getLsinfoDAO() {
		return lsinfoDAO;
	}

	public void setLsinfoDAO(LsinfoDAO lsinfoDAO) {
		this.lsinfoDAO = lsinfoDAO;
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
		lsinfoDAO.insertSelective((Lsinfo)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		Lsinfo vo=(Lsinfo)obj;
		return lsinfoDAO.selectByPrimaryKey(vo.getFlowNo());
	
	}
	public Lsinfo queryLastObject(Lsinfo vo) throws Exception {
		LsinfoExample e = new LsinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(vo.getSamCSN()))
			c.andSamCSNEqualTo(vo.getSamCSN());
		if(!CheckUtil.isEmptry(vo.getSamId()))
			c.andSamIdEqualTo(vo.getSamId());
		if(!CheckUtil.isEmptry(vo.getFlowNo()))
			c.andFlowNoEqualTo(vo.getFlowNo());
		if(!CheckUtil.isEmptry(vo.getFormNo()))
			c.andFormNoEqualTo(vo.getFormNo());
		if(vo.getOperationType()!=null && vo.getOperationType()!=0)
			c.andOperationTypeEqualTo(vo.getOperationType());
		e.setOrderByClause("CurrDate desc");
		Lsinfo result=new Lsinfo();
		List list=lsinfoDAO.selectByExample(e);
		if (list != null) {
			Iterator iter = list.iterator();
			if (iter.hasNext()) {
				result = (Lsinfo) iter.next();
			}
		}
		return result;
		
	}
	public List queryForListByFormNo(String formNo) throws Exception {
		LsinfoExample e = new LsinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(formNo))
			c.andFormNoEqualTo(formNo);
		e.setOrderByClause("CurrDate desc");
		return lsinfoDAO.selectByExample(e);
		
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		LsinfoExample e = new LsinfoExample();
		Criteria c = e.createCriteria();
		Lsinfo vo=(Lsinfo)obj;
		if(!CheckUtil.isEmptry(vo.getAppNo()))
			c.andAppNoEqualTo(vo.getAppNo());
		if(!CheckUtil.isEmptry(vo.getFlowNo()))
			c.andFlowNoEqualTo(vo.getFlowNo());
		if(vo.getOperationType()!=null && vo.getOperationType()!=0)
			c.andOperationTypeEqualTo(vo.getOperationType());
		if(!CheckUtil.isEmptry(vo.getSamCSN()))
			c.andSamCSNEqualTo(vo.getSamCSN());
		if(!CheckUtil.isEmptry(vo.getSamId()))
			c.andSamIdEqualTo(vo.getSamId());		
		return lsinfoDAO.selectByExample(e);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public int countByExample(Lsinfo vo) throws Exception {
		LsinfoExample e = new LsinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(vo.getAppNo()))
			c.andAppNoEqualTo(vo.getAppNo());
		if(!CheckUtil.isEmptry(vo.getFlowNo()))
			c.andFlowNoEqualTo(vo.getFlowNo());
		if(vo.getOperationType()!=null && vo.getOperationType()!=0)
			c.andOperationTypeEqualTo(vo.getOperationType());
		return lsinfoDAO.countByExample(e);
	}

}
