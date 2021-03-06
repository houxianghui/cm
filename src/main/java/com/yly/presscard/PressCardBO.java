package com.yly.presscard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.yly.presscard.PresscardapptbExample;
import com.yly.presscard.PresscardapptbExample.Criteria;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;


public class PressCardBO extends IbatisBO {
	private PresscardapptbDAO presscardapptbDAO;
	

	public PresscardapptbDAO getPresscardapptbDAO() {
		return presscardapptbDAO;
	}

	public void setPresscardapptbDAO(PresscardapptbDAO presscardapptbDAO) {
		this.presscardapptbDAO = presscardapptbDAO;
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

	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("presscardapptb.queryCardList",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(String formNo) throws Exception {
		return dao.queryForList("presscardapptb.queryCardList",formNo);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public List getAppList(PressCardForm obj)throws Exception {
		
		PresscardapptbExample e = new PresscardapptbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getCardType())){
			c.andCardTypeEqualTo(obj.getCardType());
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
		
		return presscardapptbDAO.selectByExample(e);
	}
	public List getEreaderAppList(PressCardForm obj)throws Exception {
		
		PresscardapptbExample e = new PresscardapptbExample();
		Criteria c = e.createCriteria();
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
		return presscardapptbDAO.selectEreaderByExample(e);
	}
	public void transinsert(Object obj,String[] cards) throws Exception {
		Presscardapptb p = (Presscardapptb)obj;
		p.setFormNo(cards[0]+"_"+cards[cards.length-1]);
		presscardapptbDAO.insert(p);
		for(int i=0;i<cards.length;i++){
			p.setCardNo(cards[i]);
			presscardapptbDAO.insertCard(p);
		}
			
	}
	public List getCardList(PressCardForm obj)throws Exception {
		
		PresscardapptbExample e = new PresscardapptbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getCardType())){
			c.andCardTypeEqualTo(obj.getCardType());
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
		
		return presscardapptbDAO.selectByExample(e);
	}
	public List queryCardList(PressCardForm obj) throws Exception {
		PresscardapptbExample e = new PresscardapptbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(obj.getPressCard_min()) || !CheckUtil.isEmptry(obj.getPressCard_max())){
			int loc=-1;
			if(obj.getPressCard_min().length()==14){
				loc=5;
			}else{
				loc=8;
			}
			c.andPressCardNoLike(obj.getPressCard_min().substring(0,loc)+"%");
			c.andPressCardNoGreaterThanOrEqualTo((obj.getPressCard_min().substring(loc)), loc+1);
			c.andPressCardNoLessThanOrEqualTo((obj.getPressCard_max().substring(loc)), loc+1);
		}
		if(!CheckUtil.isEmptry(obj.getClassId())){
			c.andClassIdEqualTo(obj.getClassId());
		}
		if(!CheckUtil.isEmptry(obj.getManufacId())){
			if(!CheckUtil.isEmptry(obj.getClassId()) && !obj.getClassId().equals("E")){
				if(obj.getManufacId().equals("02"))
					obj.setManufacId("WQ");
				else if(obj.getManufacId().equals("04"))
					obj.setManufacId("TY");
				else if(obj.getManufacId().equals("06"))
					obj.setManufacId("JD");
			}
			c.andManufacIdEqualTo(obj.getManufacId());
		}
		if(!CheckUtil.isEmptry(obj.getCommRate())){
			c.andCommRateEqualTo(obj.getCommRate());
		}		
		return presscardapptbDAO.selectCardByExample(e);
	}
	public List getReport(Object obj) throws Exception {
		Presscardapptb vo =(Presscardapptb)obj;
		PresscardapptbExample e = new PresscardapptbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(vo.getBeginDate_f())){
			c.andCurrDateGreaterThanOrEqualTo(vo.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(vo.getEndDate_f())){
			c.andCurrDateLessThanOrEqualTo(vo.getEndDate_f()+"999999");
		}
		return  presscardapptbDAO.getReport(e);
	}

}
