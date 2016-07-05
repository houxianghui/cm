package com.yly.exstore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.exstore.StoproductExample.Criteria;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappDAO;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoDAO;




public class StoproductBO extends IbatisBO {
	private StoproductDAO stoproductDAO;
	private LsinfoDAO lsinfoDAO;
	private IssueappDAO issueappDAO;


	public IssueappDAO getIssueappDAO() {
		return issueappDAO;
	}

	public void setIssueappDAO(IssueappDAO issueappDAO) {
		this.issueappDAO = issueappDAO;
	}

	public LsinfoDAO getLsinfoDAO() {
		return lsinfoDAO;
	}

	public void setLsinfoDAO(LsinfoDAO lsinfoDAO) {
		this.lsinfoDAO = lsinfoDAO;
	}

	public StoproductDAO getStoproductDAO() {
		return stoproductDAO;
	}

	public void setStoproductDAO(StoproductDAO stoproductDAO) {
		this.stoproductDAO = stoproductDAO;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		stoproductDAO.updateByPrimaryKeySelective((Stoproduct)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		Stoproduct p = (Stoproduct)obj;
		stoproductDAO.insert(p);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}
	public Stoproduct queryForObject(String sto) throws Exception{
		return stoproductDAO.selectByPrimaryKey(sto);
	}
	public List queryForList(StoproductForm sto) throws Exception{
		StoproductExample e = queryForListByEx(sto);			
		e.setOrderByClause("IssueTime desc");
		return stoproductDAO.selectByExample(e);
	}
	public Stoproduct queryForObjByKey(StoproductForm sto) throws Exception{
		Stoproduct vo = new Stoproduct();
		List l=queryForList(sto);
		if (l != null) {
		    Iterator iter = l.iterator();
		    while (iter.hasNext()) {//只选择发行日期最近的一个记录出库		 
		    	vo = (Stoproduct)iter.next();	
		    	break;
		    }
		}
		return vo;
	}
	public List queryForListAsc(StoproductForm sto) throws Exception{
		StoproductExample e = queryForListByEx(sto);			
		e.setOrderByClause("IssueTime asc");
		return stoproductDAO.selectByExample(e);
	}
	private StoproductExample queryForListByEx(StoproductForm sto) {
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();

		if(!CheckUtil.isEmptry(sto.getSamId())){
			c.andSamIdEqualTo(sto.getSamId());
		}
		if(!CheckUtil.isEmptry(sto.getSamId_min())){
			c.andSamIdGreaterThanOrEqualTo(sto.getSamId_min().trim());
		}		
		if(!CheckUtil.isEmptry(sto.getSamId_max())){
			c.andSamIdLessThanOrEqualTo(sto.getSamId_max().trim());
		}
		if(!CheckUtil.isEmptry(sto.getSamCSN())){
			c.andSamCSNEqualTo(sto.getSamCSN());
		}
		if(null!=sto.getOperationType()){
			int opertype=sto.getOperationType();
			if(opertype==31){
				sto.setWkState((short)12);
				c.andDetectSignNotEqualTo((short)2);
			}
			if(opertype==33){
				sto.setWkState((short)13);
				c.andDetectSignEqualTo((short)2);
			}
			c.andWkStateEqualTo(sto.getWkState());
			c.andIOStateNotEqualTo((short)2);
		}
		if(!CheckUtil.isEmptry(sto.getOAappNo())){
			c.andOAappNoEqualTo(sto.getOAappNo());
		}
		if(sto.getUnitId()!=null){
			c.andUnitIdEqualTo(sto.getUnitId());
		}
		if(!CheckUtil.isEmptry(sto.getPhiTypeId())){
			c.andPhiTypeIdEqualTo(sto.getPhiTypeId());
		}		
		if(!CheckUtil.isEmptry(sto.getAppTypeId())){
			c.andAppTypeIdEqualTo(sto.getAppTypeId());
		}
		return e;
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("stoproduct.queryCardList",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(String oaAppNo) throws Exception {
		return dao.queryForList("stoproduct.queryCardListByOa",oaAppNo);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public void transUpdate(Issueapp app,List<Stoproduct> il,List<Lsinfo> lsl) throws Exception {
		if(il != null && il.size()>0){
			for(Stoproduct vo:il){
				stoproductDAO.updateByPrimaryKeySelective(vo);
			}
		}
		if(lsl != null && lsl.size()>0){
			for(Lsinfo vo:lsl){
				lsinfoDAO.insert(vo);
			}
		}
		if(app.getFormState()==3)
			issueappDAO.updateByPrimaryKeySelective(app);
	}

	public void transMoveInfo(List<Stoproduct> il,List<Lsinfo> lsl) throws Exception {
		if(il != null && il.size()>0){
			for(Stoproduct vo:il){
				stoproductDAO.updateByPrimaryKeySelective(vo);
			}
		}
		if(lsl != null && lsl.size()>0){
			for(Lsinfo vo:lsl){
				lsinfoDAO.insert(vo);
			}
		}
		
	}
	public void transLsUpdate(Stoproduct sto,Lsinfo ls) throws Exception {
		if(sto != null){		
			stoproductDAO.updateByPrimaryKeySelective(sto);		
		}
		if(ls!=null){
			lsinfoDAO.updateByPrimaryKeySelective(ls);			
		}
		
	}

}
