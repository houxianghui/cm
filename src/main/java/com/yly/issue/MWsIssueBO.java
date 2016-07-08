package com.yly.issue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductDAO;
import com.yly.issue.MwsissuetbExample.Criteria;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoDAO;
import com.yly.stor.StoAppInfoBO;
import com.yly.stor.Stoappinfo;
import com.yly.stor.StoappinfoDAO;




public class MWsIssueBO extends IbatisBO {
	private MwsissuetbDAO mwsissuetbDAO;
	private StoappinfoDAO stoappinfoDAO;
	private IssuetaskctrlDAO issuetaskctrlDAO;	
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

	public StoappinfoDAO getStoappinfoDAO() {
		return stoappinfoDAO;
	}

	public void setStoappinfoDAO(StoappinfoDAO stoappinfoDAO) {
		this.stoappinfoDAO = stoappinfoDAO;
	}

	public IssuetaskctrlDAO getIssuetaskctrlDAO() {
		return issuetaskctrlDAO;
	}

	public void setIssuetaskctrlDAO(IssuetaskctrlDAO issuetaskctrlDAO) {
		this.issuetaskctrlDAO = issuetaskctrlDAO;
	}

	public MwsissuetbDAO getMwsissuetbDAO() {
		return mwsissuetbDAO;
	}

	public void setMwsissuetbDAO(MwsissuetbDAO mwsissuetbDAO) {
		this.mwsissuetbDAO = mwsissuetbDAO;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		Mwsissuetb p = (Mwsissuetb)obj;
		mwsissuetbDAO.updateByPrimaryKey(p);
	}
	public void updateBySelective(Object obj) throws Exception {
		Mwsissuetb p = (Mwsissuetb)obj;
		mwsissuetbDAO.updateByPrimaryKeySelective(p);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		Mwsissuetb p = (Mwsissuetb)obj;
		mwsissuetbDAO.insert(p);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}
	public Mwsissuetb queryForObject(String formNo) throws Exception{
		return mwsissuetbDAO.selectByPrimaryKey(formNo);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForListByExample(MWsIssuetbForm obj) throws Exception {
		MwsissuetbExample e = new MwsissuetbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getTaskCtrlNo()))
			c.andTaskCtrlNoEqualTo(obj.getTaskCtrlNo());
		if(!CheckUtil.isEmptry(obj.getPhiTypeId()))
			c.andPhiTypeIdEqualTo(obj.getPhiTypeId());		
		if(!CheckUtil.isEmptry(obj.getProdId()))
			c.andPhiTypeIdEqualTo(obj.getProdId());	
		if(!CheckUtil.isEmptry(obj.getBeginDate_f())){
			c.andFormTimeGreaterThanOrEqualTo(obj.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(obj.getEndDate_f())){
			c.andFormTimeLessThanOrEqualTo(obj.getEndDate_f()+"999999");
		}
		return mwsissuetbDAO.selectByExample(e);

	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(String formNo) throws Exception {
		return dao.queryForList("mwsissuetb.queryCardList",formNo);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	

	public void transOperate(boolean isPickStor,long amt,String batchId,Mwsissuetb obj[],Issuetaskctrl taskobj[]) throws Exception {
		if(isPickStor){
			Stoappinfo sto = new Stoappinfo();
			sto.setFormNo(batchId);
			sto.setCurrPeriodAmt(amt);
			stoappinfoDAO.updateByPrimaryKeySelective(sto);
		}
		for(int i=0;i < obj.length;i++){
			mwsissuetbDAO.insert(obj[i]);
			issuetaskctrlDAO.updateByPrimaryKeySelective(taskobj[i]);

		}
		
	}
	public void transThreeTb(Mwsissuetb mtb,Stoproduct s,Lsinfo ls) throws Exception {
		stoproductDAO.insert(s);
		lsinfoDAO.insert(ls);
		mwsissuetbDAO.updateByPrimaryKeySelective(mtb);
	}
	public void transFourTb(Mwsissuetb mtb,Stoproduct s,Lsinfo ls,Issueapp issue) throws Exception {
		stoproductDAO.insert(s);
		lsinfoDAO.insert(ls);
		mwsissuetbDAO.updateByPrimaryKeySelective(mtb);
		if(issue!=null && issue.getFormState()==3)
			issueappDAO.updateByPrimaryKeySelective(issue);
	}
	@Override
	public List queryForList(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Mwsissuetb queryIssueTaskCtrl(String formNo) throws Exception{
		return mwsissuetbDAO.queryIssueTaskCtrl(formNo);
	}
}
