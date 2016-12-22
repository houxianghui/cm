package com.yly.exstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.abc.logic.IbatisBO;
import com.eis.exception.MessageException;
import com.eis.util.CheckUtil;
import com.yly.discard.Disproduct;
import com.yly.discard.DisproductDAO;
import com.yly.discard.DisproductExample;
import com.yly.exstore.StoproductExample.Criteria;
import com.yly.issue.Issueapp;
import com.yly.issue.IssueappDAO;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoDAO;
import com.yly.reuse.Storeuse;
import com.yly.reuse.StoreuseDAO;
import com.yly.reuse.StoreuseExample;




public class StoproductBO extends IbatisBO {
	private DisproductDAO disproductDAO;
	public DisproductDAO getDisproductDAO() {
		return disproductDAO;
	}

	public void setDisproductDAO(DisproductDAO disproductDAO) {
		this.disproductDAO = disproductDAO;
	}
	private StoreuseDAO storeuseDAO;
	public StoreuseDAO getStoreuseDAO() {
		return storeuseDAO;
	}

	public void setStoreuseDAO(StoreuseDAO storeuseDAO) {
		this.storeuseDAO = storeuseDAO;
	}
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
//		if(CheckUtil.isEmptry(((Stoproduct)obj).getSamId())){  //���»���
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
		    while (iter.hasNext()) {//ֻѡ�������������һ����¼����		 
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
				Disproduct disvo=new Disproduct();
				copyProperties(disvo,vo);
				stoproductDAO.deleteByPrimaryKey(vo);
				disproductDAO.insert(disvo);
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
	public void transLsInsert(Stoproduct sto,Lsinfo ls) throws Exception {
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
	public int countIssueByExample(Stoproduct sto){
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();

		if(!CheckUtil.isEmptry(sto.getOAappNo())){
			c.andOAappNoEqualTo(sto.getOAappNo());
		}
		c.andWkStateEqualTo((short)12);
		return stoproductDAO.countByExample(e);
	}
	//81xxxԭ�ϻ��� samid
	public String getMaxBadCard() throws Exception{
		String samId="";
		samId=stoproductDAO.selectMaxCard("81");
		if(CheckUtil.isEmptry(samId)){
			samId="810000000000";
		}
		Long tmp=Long.parseLong(samId)+1;
		samId=String.valueOf(tmp);
		if(samId.compareTo("820000000000")==0){
			throw new MessageException("ԭ�ϻ����Ѵﵽ��������,����"+samId);
		}
		return samId;
	}

	//88888�˻ػ����޸� samid
	public String getMaxBadReturnCard() throws Exception{
		String samId="";
		String r_samId="";
		String d_samId="";
		StoproductExample e = new StoproductExample();
		Criteria c = e.createCriteria();
		c.andSamIdLike("88888%");
 		e.setOrderByClause("SamId desc");
		List<Stoproduct> il=stoproductDAO.selectByExample(e);
		
		StoreuseExample re = new StoreuseExample();
		com.yly.reuse.StoreuseExample.Criteria rc = re.createCriteria();
		rc.andSamIdLike("88888%");
 		re.setOrderByClause("SamId desc");
		List<Storeuse> ril=storeuseDAO.selectByExample(re);		
		
		
		DisproductExample dis=new DisproductExample();
		com.yly.discard.DisproductExample.Criteria dc = dis.createCriteria();
		dc.andSamIdLike("88888%");
		dis.setOrderByClause("SamId desc");
		List<Disproduct> dl=disproductDAO.selectByExample(dis);		
		
		if(il != null && il.size()>0){
			for(Stoproduct vo:il){
				samId=vo.getSamId();
				break;
			}	
		}
		if(ril != null && ril.size()>0){
			for(Storeuse rvo:ril){
				r_samId=rvo.getSamId();
				break;
			}	
		}
		
		if(dl != null && dl.size()>0){
			for(Disproduct dvo:dl){
				d_samId=dvo.getSamId();
				break;
			}	
		}
				
		if(!CheckUtil.isEmptry(samId) || !CheckUtil.isEmptry(r_samId) || !CheckUtil.isEmptry(d_samId) ){
			if((samId).compareTo(r_samId)<0)
				samId=r_samId;	
			if((samId).compareTo(d_samId)<0)
				samId=d_samId;
		}else{
			samId="888880000000";
		}
	
		Long tmp=Long.parseLong(samId)+1;
		samId=String.valueOf(tmp);
		if(samId.compareTo("888890000000")==0){
			throw new MessageException("�˻ػ����Ѵﵽ��������,����"+samId);
		}
		return samId;
	}
	public void querySamIdValidate(StoproductForm p)throws MessageException{
		if(CheckUtil.isEmptry(p.getOAappNo()) && (CheckUtil.isEmptry(p.getSamId_min()) || CheckUtil.isEmptry(p.getSamId_max()))){	
			throw new MessageException("OA���뵥�Ż��߿�ʼ���źͽ������ű�����д");
		}
		if(!CheckUtil.isEmptry(p.getSamId_min()) && !CheckUtil.isEmptry(p.getSamId_max())){	
			if(CheckUtil.isEmptry(p.getSamId_min()) || CheckUtil.isEmptry(p.getSamId_max())){	
				throw new MessageException("��ʼ���źͽ������ű�����д");
			}
			if(p.getSamId_min().length()!=p.getSamId_max().length()){
				throw new MessageException("��ʼ���ų��Ⱥͽ������ų��ȱ���һ��");
			}
			if(p.getSamId_min().length()!=12 ){
				throw new MessageException("���ų��ȱ�������12λ");
			}
			if(!p.getSamId_min().substring(0, 5).equals(p.getSamId_max().substring(0, 5)) ){
				throw new MessageException("����ǰ5λ����һ��");
			}		
			if(p.getSamId_min().compareTo(p.getSamId_max())>0)
				throw new MessageException("��ʼ���Ų��ܴ��ڽ�������");
		}
		
	}
	public void transInsert(Stoproduct sto,Lsinfo ls) throws Exception {
		if(sto != null){		
			stoproductDAO.insert(sto);		
		}
		if(ls!=null){
			lsinfoDAO.insert(ls);			
		}
	}
	public void transUpdateSto(Stoproduct sto,Lsinfo ls,Storeuse reusevo) throws Exception {
		if(ls!=null){
			lsinfoDAO.insert(ls);			
		}
		if(reusevo!=null){
			storeuseDAO.insert(reusevo);
			stoproductDAO.deleteByPrimaryKey(sto);
		}else{
			int row=stoproductDAO.updateByPrimaryKeySelective(sto);	
			if(row<1){
				stoproductDAO.insert(sto);
			}
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
			throw new MessageException("����������");
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
	public void copyProperties(Object dest,Object origin) throws Exception {
		new BeanUtilsBean().copyProperties(dest,origin);
	}

}
