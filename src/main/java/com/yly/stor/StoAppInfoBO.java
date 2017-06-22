package com.yly.stor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.key.KeyGenerator;
import com.eis.util.CheckUtil;
import com.eis.util.StringUtil;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductDAO;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappDAO;
import com.yly.issue.Issuetaskctrl;
import com.yly.issue.MWsIssuetbForm;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoDAO;
import com.yly.ls.LsinfoExample;
import com.yly.presscard.Presscardapptb;
import com.yly.presscard.PresscardapptbExample;
import com.yly.stor.StoappinfoExample.Criteria;


public class StoAppInfoBO extends IbatisBO {
	private StoappinfoDAO stoappinfoDAO;
	private IssueappDAO issueappDAO;
	private LsinfoDAO lsinfoDAO;
	private StoproductDAO stoproductDAO;
	public StoproductDAO getStoproductDAO() {
		return stoproductDAO;
	}

	public void setStoproductDAO(StoproductDAO stoproductDAO) {
		this.stoproductDAO = stoproductDAO;
	}
	public LsinfoDAO getLsinfoDAO() {
		return lsinfoDAO;
	}

	public void setLsinfoDAO(LsinfoDAO lsinfoDAO) {
		this.lsinfoDAO = lsinfoDAO;
	}
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
	public void tranBackTb(Stoappinfo vo,Stoappinfo backsto,Lsinfo lsvo) throws Exception {
		stoappinfoDAO.updateByPrimaryKeySelective(vo);
		stoappinfoDAO.insert(backsto);
		lsinfoDAO.insert(lsvo);
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
	public void tranUpdate(Issueapp vo,Stoappinfo sto,List<Lsinfo> ls,List<Stoproduct> il) throws Exception {
		if(vo!=null)
			issueappDAO.updateByPrimaryKeySelective(vo);
		if(sto!=null)
			stoappinfoDAO.updateByPrimaryKeySelective(sto);
		if(ls!=null && ls.size()>0){
			for(Lsinfo lsinfo:ls){
				lsinfoDAO.insert(lsinfo);
			}
		}
		if(il != null && il.size()>0){
			for(Stoproduct di:il){
				stoproductDAO.insert(di);
			}
		}
	}
	public void tranMain(Stoappinfo sto,Stoappinfo partSto,Issueapp app) throws Exception {
		stoappinfoDAO.insert(sto);
		if(partSto!=null)
			stoappinfoDAO.updateByPrimaryKeySelective(partSto);
		if(app!=null)
			issueappDAO.updateByPrimaryKeySelective(app);
	}
	public void tranInsert(Stoappinfo sto,Lsinfo lsvo) throws Exception {
		if(lsvo!=null)
			lsinfoDAO.insert(lsvo);
		if(sto!=null)
			stoappinfoDAO.insert(sto);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public List getInStockAppList(StoAppInfoForm obj)throws Exception {
		
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getProdId())){
			c.andProdIdEqualTo(obj.getProdId());
		}
		if(obj.getOperationType_f()!=null && obj.getOperationType_f()>0){
			c.andOperationTypeEqualTo(obj.getOperationType_f().shortValue());
		}else if(obj.getOperationType()!=null && obj.getOperationType()>0){
			c.andOperationTypeEqualTo(obj.getOperationType().shortValue());
		}else{
			 c.andOperationTypeBetween((short)10, (short)19);
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
		if(!CheckUtil.isEmptry(obj.getPhiTypeId_f())){
			c.andPhiTypeIdEqualTo(obj.getPhiTypeId_f());
		}
		if(!CheckUtil.isEmptry(String.valueOf(obj.getCurrPeriodAmt_f()))){
			c.andCurrPeriodAmtGreaterThanOrEqualTo(obj.getCurrPeriodAmt_f());
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
		e.setOrderByClause("CurrDate desc");
		return stoappinfoDAO.selectByExample(e);
	}	
	public List getAppList(StoAppInfoForm obj)throws Exception {
		
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getProdId())){
			c.andProdIdEqualTo(obj.getProdId());
		}
		if(obj.getOperationType_f()!=null && obj.getOperationType_f()>0){
			c.andOperationTypeEqualTo(obj.getOperationType_f().shortValue());
		}else if(obj.getOperationType()!=null && obj.getOperationType()>0){
			c.andOperationTypeEqualTo(obj.getOperationType().shortValue());
		}else{
			c.andOperationTypeNotEqualTo((short)94);		
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
		if(!CheckUtil.isEmptry(obj.getPhiTypeId_f())){
			c.andPhiTypeIdEqualTo(obj.getPhiTypeId_f());
		}
		if(!CheckUtil.isEmptry(String.valueOf(obj.getCurrPeriodAmt_f()))){
			c.andCurrPeriodAmtGreaterThanOrEqualTo(obj.getCurrPeriodAmt_f());
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
		e.setOrderByClause("CurrDate desc");
		return stoappinfoDAO.selectByExample(e);
	}
	public List getAppListByExample(StoAppInfoForm obj)throws Exception {
		
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
			if(opertype==34 ||opertype==52 ||opertype==42 ){
				obj.setProdId("4");//Ð¡Ä£¿é
			}
			if(opertype==51||opertype==41 ){
				obj.setProdId("3");//esam
			}
		}		
		if(!CheckUtil.isEmptry(obj.getProdId())){
			c.andProdIdEqualTo(obj.getProdId());
		}
		c.andOperationTypeLessThan((short)20);
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
	public List getReport(Object obj) throws Exception {
		Stoappinfo vo =(Stoappinfo)obj;
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		c.andOperationTypeLessThan((short)20);
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		return  stoappinfoDAO.getReport(e);
	}
	public List getPosChargeBackReport(Object obj) throws Exception {
		Stoappinfo vo =(Stoappinfo)obj;
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		c.andOperationTypeEqualTo((short)92);
		c.andProdIdEqualTo("4");
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		c.andLsWithStoByPosChargeBack();
		return  stoappinfoDAO.getPosChargeBackReport(e);
	}
	public List getStockReport(Object obj) throws Exception {
		Stoappinfo vo =(Stoappinfo)obj;
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		e.setOrderByClause("ProdId,CurrDate");
 		return  stoappinfoDAO.selectByExample(e);
	}
	public List getStockMap(Object obj) throws Exception {
		Map<String,List> prodMap = new HashMap<String, List>();
		Stoappinfo vo =(Stoappinfo)obj;
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		List operList=new ArrayList();
		operList.add("11");
		operList.add("12");
		operList.add("13");
		operList.add("61");
		operList.add("92");
		operList.add("94");
		c.andOperationTypeIn(operList);
		e.setOrderByClause("ProdId,CurrDate");
 		List stoInfo=stoappinfoDAO.selectByExample(e);
 	
		LsinfoExample o_back = new LsinfoExample();
		com.yly.ls.LsinfoExample.Criteria c_back = o_back.createCriteria();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c_back.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c_back.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
 		List appInfo=lsinfoDAO.getStockLsByProd(o_back);
 		if(stoInfo.size()>0){
 			stoInfo.addAll(appInfo);
 		}else if(appInfo.size()>0){
 			return appInfo;
 		}
 		return stoInfo;
	}
	public List getInStockBalReport(Object obj) throws Exception {		
		Stoappinfo vo =(Stoappinfo)obj;
		StoappinfoExample e = new StoappinfoExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		c.andOperationTypeBetween((short)10, (short)20);
 		return stoappinfoDAO.getStockBalReport(e);
	}
	public List getOutStockBalReport(Object obj) throws Exception {		
		Stoappinfo vo =(Stoappinfo)obj;
		LsinfoExample o_back = new LsinfoExample();
		com.yly.ls.LsinfoExample.Criteria c_back = o_back.createCriteria();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c_back.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c_back.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		
 		return lsinfoDAO.getStockBalReport(o_back);
	}
	public List getBackStockBalReport(Object obj) throws Exception {	
		Stoappinfo vo =(Stoappinfo)obj;
		LsinfoExample e_back = new LsinfoExample();
		com.yly.ls.LsinfoExample.Criteria c_back = e_back.createCriteria();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c_back.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c_back.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		c_back.andApplyAndLsOper((short)60,(short)70);
 		return lsinfoDAO.getLSBackReport(e_back);		
	}
	public List getDisStockBalReport(Object obj) throws Exception {	
		Stoappinfo vo =(Stoappinfo)obj;
		LsinfoExample e_dis = new LsinfoExample();
		com.yly.ls.LsinfoExample.Criteria c_dis = e_dis.createCriteria();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c_dis.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c_dis.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		c_dis.andOperationTypeEqualTo((short)71);
 		return lsinfoDAO.getLsDisCardReport(e_dis);		
	}
}
