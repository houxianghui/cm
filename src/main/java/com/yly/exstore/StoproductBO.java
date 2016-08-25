package com.yly.exstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.exception.MessageException;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.StringUtil;
import com.yly.exstore.StoproductExample.Criteria;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappDAO;
import com.yly.issue.IssueappForm;
import com.yly.issue.MWsIssuetbForm;
import com.yly.issue.Mwsissuetb;
import com.yly.issue.MwsissuetbExample;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoDAO;
import com.yly.pki.SecpkitbForm;




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
//		if(CheckUtil.isEmptry(((Stoproduct)obj).getSamId())){  //更新坏卡
////			((Stoproduct)obj).setSamId(getBadSamId(((Stoproduct)obj).getSamCSN()));
////			if(CheckUtil.isEmptry(((Stoproduct)obj).getSamId())){
////				((Stoproduct)obj).setSamId(getMaxBadCard());
////				stoproductDAO.insert(((Stoproduct)obj));
////			}
////			stoproductDAO.updateBySamCSN((Stoproduct)obj);
//			((Stoproduct)obj).setSamId(getMaxBadCard());
//			stoproductDAO.insert(((Stoproduct)obj));
//		}else{
			stoproductDAO.updateByPrimaryKeySelective((Stoproduct)obj);
//		}
		
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
	public Stoproduct queryForObject(Stoproduct sto) throws Exception{
		return stoproductDAO.selectByPrimaryKey(sto);
	}
	public Stoproduct queryObjectBySamId(String sto) throws Exception{
		return stoproductDAO.selectBySamId(sto);
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
				c.andIOStateNotEqualTo((short)2);
			}
			if(opertype==33){
				sto.setWkState((short)13);
				c.andIOStateNotEqualTo((short)2);
				sto.setOAappNo("");
				c.andManufacIdEqualTo(String.valueOf(sto.getUnitId()));
				sto.setUnitId(null);
				c.andSamIdLike("81%");
			}
		}
		
		if(sto.getWkState()!=null && sto.getWkState()>0){
			c.andWkStateEqualTo(sto.getWkState());
		}
		if(sto.getIOState()!=null && sto.getIOState()>0){
			c.andIOStateEqualTo(sto.getIOState());
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
		if(!CheckUtil.isEmptry(sto.getProdId())){
			c.andProdIdEqualTo(sto.getProdId());
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
		String appNo=issueappDAO.selectByOA(oaAppNo);
		return dao.queryForList("stoproduct.queryCardListByOa",appNo);
	}
	public String changeOAappNo(String oaAppNo) throws Exception {
		String appNo=issueappDAO.selectByOA(oaAppNo);
		return appNo;
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
			int row=stoproductDAO.updateByPrimaryKeySelective(sto);	
			if(row<1){
				stoproductDAO.insert(sto);
			}
		}
		if(ls!=null){
			int row=lsinfoDAO.updateByPrimaryKeySelective(ls);		
			if(row<1){
				lsinfoDAO.insert(ls);
			}
				
		}
		
	}
	public int countIssueByExample(Stoproduct sto){
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();

		if(!CheckUtil.isEmptry(sto.getOAappNo())){
			c.andOAappNoEqualTo(sto.getOAappNo());
		}
		c.andWkStateEqualTo((short)12);
		return stoproductDAO.countByExample(e);
	}
	//81xxx原料坏卡 samid
	public String getMaxBadCard() throws Exception{
		String samId="";
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();
		c.andSamIdLike("81%");
 		e.setOrderByClause("SamId desc");
		List<Stoproduct> il=stoproductDAO.selectByExample(e);
		if(il != null && il.size()>0){
			for(Stoproduct vo:il){
				samId=vo.getSamId();
				break;
			}	
		}else{
			samId="810000000000";
		}
		Long tmp=Long.parseLong(samId)+1;
		samId=String.valueOf(tmp);
		if(samId.compareTo("820000000000")==0){
			throw new MessageException("原料坏卡已达到数量上限,卡号"+samId);
		}
		return samId;
	}

	//88888退回坏卡修复 samid
	public String getMaxBadReturnCard() throws Exception{
		String samId="";
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();
		c.andSamIdLike("88888%");
 		e.setOrderByClause("SamId desc");
		List<Stoproduct> il=stoproductDAO.selectByExample(e);
		if(il != null && il.size()>0){
			for(Stoproduct vo:il){
				samId=vo.getSamId();
				break;
			}	
		}else{
			samId="888880000000";
		}
		Long tmp=Long.parseLong(samId)+1;
		samId=String.valueOf(tmp);
		if(samId.compareTo("888890000000")==0){
			throw new MessageException("退回坏卡已达到数量上限,卡号"+samId);
		}
		return samId;
	}
	public void querySamIdValidate(StoproductForm p)throws MessageException{
		if(CheckUtil.isEmptry(p.getSamId_min()) || CheckUtil.isEmptry(p.getSamId_max())){	
			throw new MessageException("开始卡号和结束卡号必须填写");
		}
		if(p.getSamId_min().length()!=p.getSamId_max().length()){
			throw new MessageException("开始卡号长度和结束卡号长度必须一致");
		}
		if(p.getSamId_min().length()!=12 ){
			throw new MessageException("卡号长度必须满足12位");
		}
		if(p.getSamId_min().compareTo(p.getSamId_max())>0)
			throw new MessageException("开始卡号不能大于结束卡号");
		
	}
	public void transInsert(Stoproduct sto,Lsinfo ls) throws Exception {
		if(sto != null){		
			stoproductDAO.insert(sto);		
		}
		if(ls!=null){
			lsinfoDAO.insert(ls);			
		}
	}
	public void transUpdateSto(Stoproduct sto,Lsinfo ls) throws Exception {
		if(sto != null){
			int row=stoproductDAO.updateByPrimaryKeySelective(sto);	
			if(row<1){
				stoproductDAO.insert(sto);
			}
		}
		if(ls!=null){
			lsinfoDAO.insert(ls);			
		}
		
	}
	
	public List getReport(Object obj) throws Exception {
		Stoproduct vo =(Stoproduct)obj;
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();
		c.andAsscoTbs();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andIOStateChgDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andIOStateChgDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		List<StoproductForm> result=stoproductDAO.getReport(e);
		if(result.size()<1){
			throw new MessageException("不存在数据");
		}
		return  result;
	}
	public List getExchangeReport(Object obj) throws Exception {
		Stoproduct vo =(Stoproduct)obj;
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();
		c.andExchangeAsscoTbs();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andIOStateChgDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andIOStateChgDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		List<StoproductForm> result=stoproductDAO.getExchangeReport(e);

		return  result;
	}
	public List getMakeUpReport(Object obj) throws Exception {
		Stoproduct vo =(Stoproduct)obj;
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();
		c.andMakeUpAsscoTbs();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andIOStateChgDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andIOStateChgDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		List<StoproductForm> result=stoproductDAO.getExchangeReport(e);

		return  result;
	}	
	public List getReStoreReport(Object obj) throws Exception {
		Stoproduct vo =(Stoproduct)obj;
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();
		c.andReStoreAsscoTbs();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andIOStateChgDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andIOStateChgDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		List<StoproductForm> result=stoproductDAO.getReStoreReport(e);
		return  result;
	}	
}
