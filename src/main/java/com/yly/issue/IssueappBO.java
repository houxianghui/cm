package com.yly.issue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.exstore.StoproductDAO;
import com.yly.exstore.StoproductForm;
import com.yly.issue.IssueappExample.Criteria;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoDAO;
import com.yly.stor.StoAppInfoBO;
import com.yly.stor.StoAppInfoForm;
import com.yly.stor.Stoappinfo;
import com.yly.stor.StoappinfoDAO;



public class IssueappBO extends IbatisBO {
	private IssueappDAO issueappDAO;
	private IssuetaskCtrlBO issuetaskctrlBO;
	private StoproductDAO stoproductDAO;
	public StoproductDAO getStoproductDAO() {
		return stoproductDAO;
	}

	public void setStoproductDAO(StoproductDAO stoproductDAO) {
		this.stoproductDAO = stoproductDAO;
	}

	public IssuetaskCtrlBO getIssuetaskctrlBO() {
		return issuetaskctrlBO;
	}

	public void setIssuetaskctrlBO(IssuetaskCtrlBO issuetaskctrlBO) {
		this.issuetaskctrlBO = issuetaskctrlBO;
	}


	private LsinfoDAO lsinfoDAO;
	private StoappinfoDAO stoappinfoDAO;
	public LsinfoDAO getLsinfoDAO() {
		return lsinfoDAO;
	}

	public void setLsinfoDAO(LsinfoDAO lsinfoDAO) {
		this.lsinfoDAO = lsinfoDAO;
	}

	public StoappinfoDAO getStoappinfoDAO() {
		return stoappinfoDAO;
	}

	public void setStoappinfoDAO(StoappinfoDAO stoappinfoDAO) {
		this.stoappinfoDAO = stoappinfoDAO;
	}


	static final short READY=1;

	public IssueappDAO getIssueappDAO() {
		return issueappDAO;
	}

	public void setIssueappDAO(IssueappDAO issueappDAO) {
		this.issueappDAO = issueappDAO;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		Issueapp p = (Issueapp)obj;
		p.setFormState(READY);
		issueappDAO.updateByPrimaryKeySelective(p);
	}
	public void tranUpdate(Issueapp vo,Stoappinfo sto,Lsinfo lsvo) throws Exception {
		if(vo!=null)
			issueappDAO.updateByPrimaryKeySelective(vo);
		if(sto!=null)
			stoappinfoDAO.updateByPrimaryKeySelective(sto);
		if(lsvo!=null)
			lsinfoDAO.insert(lsvo);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		Issueapp p = (Issueapp)obj;
		issueappDAO.insert(p);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}
	public Issueapp queryForObject(String appNo) throws Exception{
		return issueappDAO.selectByPrimaryKey(appNo);
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
	public List getAppList(IssueappForm obj)throws Exception {
		
		IssueappExample e = new IssueappExample();
		Criteria c = e.createCriteria();
		c.andOperationTypeBetween((short)21, (short)29);//发行业务
		queryListByExample(obj, c);
		return issueappDAO.selectByExample(e);
	}

	private void queryListByExample(IssueappForm obj, Criteria c) {
		if(obj.getOperationType()!=null && obj.getOperationType()>0){
			c.andOperationTypeEqualTo(obj.getOperationType().shortValue());
		}
		if(obj.getUnitId()!=null && obj.getUnitId()>0){
			c.andUnitIdEqualTo(obj.getUnitId());
		}
		if(!CheckUtil.isEmptry(obj.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(obj.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(obj.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(obj.getEndDate_f()+"999999");
		}
		if(obj.getFormState()!=null && obj.getFormState()>0){
			c.andFormStateEqualTo(obj.getFormState().shortValue());
		}
	}
	
	
	public List getExList(IssueappForm obj)throws Exception {
		
		IssueappExample e = new IssueappExample();
		Criteria c = e.createCriteria();
		c.andOperationTypeBetween((short)31, (short)39);//出库业务
		queryListByExample(obj, c);
		return issueappDAO.selectByExample(e);
	}
	public List getMakeUpList(IssueappForm obj)throws Exception {
		
		IssueappExample e = new IssueappExample();
		Criteria c = e.createCriteria();
		c.andOperationTypeBetween((short)51, (short)59);//出库业务
		queryListByExample(obj, c);
		return issueappDAO.selectByExample(e);
	}	
	public List getExBackList(IssueappForm obj)throws Exception {
		
		IssueappExample e = new IssueappExample();
		Criteria c = e.createCriteria();
		c.andOperationTypeEqualTo((short)92);//冲回业务
		queryListByExample(obj, c);
		return issueappDAO.selectByExample(e);
	}	
	public void validate(IssueappForm obj)throws Exception {
		IssuetaskctrlForm f=new IssuetaskctrlForm();
		f.setAppNo(obj.getAppNo());
		if(obj.getTaskAmt()!=issuetaskctrlBO.sumByAppNo(f))
			throw new Exception("未完成任务制定计划,请完成后提交");
			
	}
	public List getW_QueryList(IssueappForm obj)throws Exception {
		
		IssueappExample e = new IssueappExample();
		Criteria c = e.createCriteria();
		if(obj.getOperationType()!=null && obj.getOperationType()>0){
			c.andApplyOperTypeEqualTo(obj.getOperationType().shortValue());
		}
		if(obj.getUnitId()!=null && obj.getUnitId()>0){
			c.andLeadUnitIdEqualTo(obj.getUnitId());
		}
		if(!CheckUtil.isEmptry(obj.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(obj.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(obj.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(obj.getEndDate_f()+"999999");
		}
		if(!CheckUtil.isEmptry(obj.getTaskCtrlNo())){
			c.andTaskCtrlNoEqualTo(obj.getTaskCtrlNo());
		}	
		if(!CheckUtil.isEmptry(obj.getProdId())){
			c.andProdIdEqualTo(obj.getProdId());
		}
		if(!CheckUtil.isEmptry(obj.getPhiTypeId())){
			c.andPhiTypeIdEqualTo(obj.getPhiTypeId());
		}
		c.andFormStateEqualTo(READY);
		
		return issueappDAO.queryIssuExample(e);
	}			


}
