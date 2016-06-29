package com.yly.stor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.util.CheckUtil;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappDAO;
import com.yly.stor.StoappinfoExample.Criteria;


public class StoAppInfoBO extends IbatisBO {
	private StoappinfoDAO stoappinfoDAO;
	private IssueappDAO issueappDAO;
	public IssueappDAO getIssueappDAO() {
		return issueappDAO;
	}

	public void setIssueappDAO(IssueappDAO issueappDAO) {
		this.issueappDAO = issueappDAO;
	}
	static final String EXCHANGE="13";
	static final Short DONE=3;


	public StoappinfoDAO getStoappinfoDAO() {
		return stoappinfoDAO;
	}

	public void setStoappinfoDAO(StoappinfoDAO stoappinfoDAO) {
		this.stoappinfoDAO = stoappinfoDAO;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		Stoappinfo p = (Stoappinfo)obj;
		stoappinfoDAO.updateByPrimaryKeySelective(p);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		Stoappinfo p = (Stoappinfo)obj;
		if(EXCHANGE.equals(p.getOperationType())){
			transinsert(p);
		}
		stoappinfoDAO.insert(p);
	}
	public void transinsert(Object obj) throws Exception {
		Stoappinfo p = (Stoappinfo)obj;
		Issueapp appinfo = new Issueapp();
		appinfo.setAppNo(p.getExFormNo());
		appinfo.setFormState(DONE);
		issueappDAO.updateByPrimaryKey(appinfo);
		stoappinfoDAO.insert(p);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}
	public Stoappinfo queryForObject(String formNo) throws Exception{
		return stoappinfoDAO.selectByPrimaryKey(formNo);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("stoappinfo.queryCardList",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(String formNo) throws Exception {
		return dao.queryForList("stoappinfo.queryCardList",formNo);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public List getAppList(StoAppInfoForm obj)throws Exception {
		
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getProdId())){
			c.andProdIdEqualTo(obj.getProdId());
		}
		if(obj.getOperationType()!=null && obj.getOperationType()>0){
			c.andOperationTypeEqualTo(obj.getOperationType().shortValue());
		}
		if(!CheckUtil.isEmptry(obj.getManufacId())){
			c.andManufacIdEqualTo(obj.getManufacId());
		}
		if(!CheckUtil.isEmptry(obj.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(obj.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(obj.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(obj.getEndDate_f()+"999999");
		}
		if(!CheckUtil.isEmptry(obj.getFormNo())){
			c.andFormNoEqualTo(obj.getFormNo());
		}
		if(!CheckUtil.isEmptry(obj.getPhiTypeId())){
			c.andPhiTypeIdEqualTo(obj.getPhiTypeId());
		}
		if(!CheckUtil.isEmptry(obj.getIsPki())){
			c.andIsPkiEqualTo(obj.getIsPki());
		}
		if(!CheckUtil.isEmptry(obj.getIsHTCard())){
			c.andIsHTCardEqualTo(obj.getIsHTCard());
		}
		if(!CheckUtil.isEmptry(String.valueOf(obj.getCurrPeriodAmt()))){
			c.andCurrPeriodAmtGreaterThanOrEqualTo(obj.getCurrPeriodAmt());
		}
		return stoappinfoDAO.selectByExample(e);
	}
	public List getExList(StoAppInfoForm obj)throws Exception {
		
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		if(obj.getOperationType()!=null && obj.getOperationType()>0){
			long opertype=obj.getOperationType();
			if(opertype==34 ||opertype==52 ){
				obj.setProdId("4");//Ð¡Ä£¿é
			}
			if(opertype==51){
				obj.setProdId("3");//esam
			}
		}
		if(!CheckUtil.isEmptry(obj.getProdId())){
			c.andProdIdEqualTo(obj.getProdId());
		}
		if(!CheckUtil.isEmptry(obj.getManufacId())){
			c.andManufacIdEqualTo(obj.getManufacId());
		}
		if(!CheckUtil.isEmptry(obj.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(obj.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(obj.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(obj.getEndDate_f()+"999999");
		}
		if(!CheckUtil.isEmptry(obj.getFormNo())){
			c.andFormNoEqualTo(obj.getFormNo());
		}
		if(!CheckUtil.isEmptry(obj.getPhiTypeId())){
			c.andPhiTypeIdEqualTo(obj.getPhiTypeId());
		}
		if(!CheckUtil.isEmptry(String.valueOf(obj.getCurrPeriodAmt()))){
			c.andCurrPeriodAmtGreaterThanOrEqualTo(obj.getCurrPeriodAmt());
		}
		if(!CheckUtil.isEmptry(obj.getIsPki())){
			c.andIsPkiEqualTo(obj.getIsPki());
		}
		if(!CheckUtil.isEmptry(obj.getIsHTCard())){
			c.andIsHTCardEqualTo(obj.getIsHTCard());
		}
		return stoappinfoDAO.selectByExample(e);
	}

	public List getCardList(StoAppInfoForm obj)throws Exception {
		
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getProdId())){
			c.andProdIdEqualTo(obj.getProdId());
		}
		if(!CheckUtil.isEmptry(obj.getManufacId())){
			c.andManufacIdEqualTo(obj.getManufacId());
		}
		if(!CheckUtil.isEmptry(obj.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(obj.getBeginDate_f());
		}
		if(!CheckUtil.isEmptry(obj.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(obj.getEndDate_f());
		}
		if(!CheckUtil.isEmptry(obj.getFormNo())){
			c.andFormNoEqualTo(obj.getFormNo());
		}
		if(!CheckUtil.isEmptry(obj.getIsPki())){
			c.andIsPkiEqualTo(obj.getIsPki());
		}
		if(!CheckUtil.isEmptry(obj.getIsHTCard())){
			c.andIsHTCardEqualTo(obj.getIsHTCard());
		}
		return stoappinfoDAO.selectByExample(e);
	}


}
