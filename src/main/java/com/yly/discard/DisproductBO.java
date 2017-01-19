package com.yly.discard;

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
import com.yly.discard.DisproductExample.Criteria;
import com.yly.reuse.StoreuseForm;


public class DisproductBO extends IbatisBO {
	private DisproductDAO disproductDAO;




	public DisproductDAO getDisproductDAO() {
		return disproductDAO;
	}

	public void setDisproductDAO(DisproductDAO disproductDAO) {
		this.disproductDAO = disproductDAO;
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
		disproductDAO.insertSelective((Disproduct)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		Disproduct vo=((Disproduct)obj);
		String samId=((Disproduct)obj).getSamId();
		return disproductDAO.selectByPrimaryKey(samId);
	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		DisproductForm f=((DisproductForm)obj);
		DisproductExample e = new DisproductExample();
		Criteria c = e.createCriteria();
		
		if(!CheckUtil.isEmptry(f.getSamCsn_f())){
			c.andSamCSNEqualTo(f.getSamCsn_f());
		}else if(!CheckUtil.isEmptry(f.getSamCSN())){
			c.andSamCSNEqualTo(f.getSamCSN());
		}
		if(!CheckUtil.isEmptry(f.getPhiTypeId())){
			c.andPhiTypeIdEqualTo(f.getPhiTypeId());
		}		
		if(!CheckUtil.isEmptry(f.getAppTypeId())){
			c.andAppTypeIdEqualTo(f.getAppTypeId());
		}
		if(!CheckUtil.isEmptry(f.getProdId())){
			c.andProdIdEqualTo(f.getProdId());
		}
		if(!CheckUtil.isEmptry(f.getBeginDate_f())){
			c.andWkStateChgDateGreaterThanOrEqualTo(f.getBeginDate_f()+"000000");
		}
		if(!CheckUtil.isEmptry(f.getEndDate_f())){
			c.andWkStateChgDateLessThanOrEqualTo(f.getEndDate_f()+"999999");
		}
		if(!CheckUtil.isEmptry(f.getSamId_min())){
			c.andSamIdGreaterThanOrEqualTo(f.getSamId_min().trim());
		}		
		if(!CheckUtil.isEmptry(f.getSamId_max())){
			c.andSamIdLessThanOrEqualTo(f.getSamId_max().trim());
		}
		e.setOrderByClause("WkStateChgDate asc");
		return disproductDAO.selectByExample(e);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public void querySamIdValidate(DisproductForm p)throws MessageException{
		boolean flag=false;
		if(!CheckUtil.isEmptry(p.getSamId_min()) && !CheckUtil.isEmptry(p.getSamId_max())){	
			flag=true;
		}		
		if(!CheckUtil.isEmptry(p.getSamCsn_f())){	
			flag=true;
		}			
		if(!CheckUtil.isEmptry(p.getBeginDate_f()) && !CheckUtil.isEmptry(p.getEndDate_f())){
			flag=true;
		}
		if(!flag){
			throw new MessageException("必须录入任一查询条件(报废日期/发行卡号/印刷卡号)");
		}
		if(!CheckUtil.isEmptry(p.getBeginDate_f()) || !CheckUtil.isEmptry(p.getEndDate_f())){
			if(CheckUtil.isEmptry(p.getBeginDate_f()) || CheckUtil.isEmptry(p.getEndDate_f())){	
				throw new MessageException("开始日期和结束日期必须填写");
			}
			if(p.getBeginDate_f().compareTo(p.getEndDate_f())>0)
				throw new MessageException("开始日期不能大于结束日期");
			if(DateUtil.getDays(p.getBeginDate_f(), p.getEndDate_f())>90)
				throw new MessageException("日期间隔不能超过3个月");
		}
		if(!CheckUtil.isEmptry(p.getSamId_min()) || !CheckUtil.isEmptry(p.getSamId_max())){
			if(CheckUtil.isEmptry(p.getSamId_min()) || CheckUtil.isEmptry(p.getSamId_max())){	
				throw new MessageException("开始卡号和结束卡号必须填写");
			}
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


}
