package com.yly.reuse;


import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.abc.logic.IbatisBO;
import com.eis.exception.MessageException;
import com.eis.util.CheckUtil;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductDAO;
import com.yly.exstore.StoproductForm;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappDAO;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoDAO;
import com.yly.reuse.StoreuseExample.Criteria;


public class StoreuseBO extends IbatisBO {
	private StoreuseDAO storeuseDAO;
	private LsinfoDAO lsinfoDAO;	
	private StoproductDAO stoproductDAO;
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
		return storeuseDAO.selectByPrimaryKey((Storeuse)obj);
	
	}
	
	public Object queryObjectBySamId(String samId) throws Exception {
		return storeuseDAO.selectBySamId(samId);
	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		StoreuseForm f=((StoreuseForm)obj);
		StoreuseExample e = new StoreuseExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(f.getSamId_min())){
			c.andSamIdGreaterThanOrEqualTo(f.getSamId_min());
		}
		if(!CheckUtil.isEmptry(f.getSamId_max())){
			c.andSamIdLessThanOrEqualTo(f.getSamId_max());
		}		
		if(!CheckUtil.isEmptry(f.getSamCsn_f())){
			c.andSamCSNEqualTo(f.getSamCsn_f());
		}else if(!CheckUtil.isEmptry(f.getSamCSN())){
			c.andSamCSNEqualTo(f.getSamCSN());
		}
		
		if(!CheckUtil.isEmptry(f.getSamId()))
			c.andSamIdEqualTo(f.getSamId());	
		if(null!=f.getUnitId() && f.getUnitId()>0)
			c.andUnitIdEqualTo(f.getUnitId());			
		e.setOrderByClause("IOStateChgDate asc");
		return storeuseDAO.selectByExample(e);
	}
	public List queryForExList(Object obj) throws Exception {
		StoreuseForm f=((StoreuseForm)obj);
		StoreuseExample e = new StoreuseExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(f.getSamId_min())){
			c.andSamIdGreaterThanOrEqualTo(f.getSamId_min());
		}
		if(!CheckUtil.isEmptry(f.getSamId_max())){
			c.andSamIdLessThanOrEqualTo(f.getSamId_max());
		}
		if(f.getKeyType()!=null){
			c.andKeyTypeEqualTo(f.getKeyType());
			if(f.getKeyType()==1){
				c.andUnitIdEqualTo(f.getUnitId());	
			}
		}
		e.setOrderByClause("IOStateChgDate asc");
		return storeuseDAO.selectByExample(e);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public void transUpdateTB(Issueapp app,Stoproduct s,Storeuse storeuse,Lsinfo ls) throws Exception {
		lsinfoDAO.insert(ls);
		if(storeuse!=null){
			storeuseDAO.insert(storeuse);
			stoproductDAO.deleteByPrimaryKey(s);
		}else{
			int row=stoproductDAO.updateByPrimaryKeySelective(s);
			if(row<1)
				stoproductDAO.insert(s);
		}
		if(app!=null){
			issueappDAO.updateByPrimaryKeySelective(app);
		}
	}
	public void transReExTb(Issueapp app,List<Storeuse> il,List<Lsinfo> lsl) throws Exception {
		if(il != null && il.size()>0){
			for(Storeuse vo:il){
				Stoproduct sto =  new Stoproduct();
				copyProperties(sto,vo);
				stoproductDAO.insert(sto);
				storeuseDAO.deleteByPrimaryKey(vo);
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
	public void transUpdate() throws Exception {

	}
	public void copyProperties(Object dest,Object origin) throws Exception {
		new BeanUtilsBean().copyProperties(dest,origin);
	}
	public void querySamIdValidate(StoreuseForm p)throws MessageException{
		if((CheckUtil.isEmptry(p.getSamId_min()) || CheckUtil.isEmptry(p.getSamId_max())) && CheckUtil.isEmptry(p.getSamCsn_f())){	
			throw new MessageException("必须录入查询条件(发行卡号/印刷卡号)");
		}
		if(!CheckUtil.isEmptry(p.getSamId_min()) || !CheckUtil.isEmptry(p.getSamId_max())){
			if(p.getSamId_min().length()!=p.getSamId_max().length()){
				throw new MessageException("开始卡号长度和结束卡号长度必须一致");
			}
			if(p.getSamId_min().length()!=12 ){
				throw new MessageException("卡号长度必须满足12位");
			}
			if(!p.getSamId_min().substring(0, 5).equals(p.getSamId_max().substring(0, 5)) ){
				throw new MessageException("卡号前5位必须一致");
			}		
			if(p.getSamId_min().compareTo(p.getSamId_max())>0)
				throw new MessageException("开始卡号不能大于结束卡号");
		}
	}
	public List getReStoreReport(Object obj) throws Exception {
		Stoproduct vo =(Stoproduct)obj;
		StoreuseExample e = new StoreuseExample();
		Criteria c = e.createCriteria();
		c.andReStoreAsscoTbs();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andIOStateChgDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andIOStateChgDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		List<StoproductForm> result=storeuseDAO.getReStoreReport(e);
		return  result;
	}	

}
