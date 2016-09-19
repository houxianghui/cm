package com.yly.pki;

import java.sql.Date;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.exception.MessageException;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.yly.pki.SecpkitbExample.Criteria;



public class SecpkitbBO extends IbatisBO {
	private SecpkitbDAO secpkitbDAO;


	public SecpkitbDAO getSecpkitbDAO() {
		return secpkitbDAO;
	}

	public void setSecpkitbDAO(SecpkitbDAO secpkitbDAO) {
		this.secpkitbDAO = secpkitbDAO;
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
		secpkitbDAO.insertSelective((Secpkitb)obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		Secpkitb vo=((Secpkitb)obj);
		String samId=((Secpkitb)obj).getSamId();
		return secpkitbDAO.selectByPrimaryKey(samId);
	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		Secpkitb vo=((Secpkitb)obj);
		SecpkitbExample e = new SecpkitbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(vo.getSamCSN()))
			c.andSamCSNEqualTo(vo.getSamCSN());
		if(!CheckUtil.isEmptry(vo.getSamId()))
			c.andSamIdEqualTo(vo.getSamId());	
		e.setOrderByClause("IssueTime desc");
		return secpkitbDAO.selectByExample(e);
	}
	public List queryForListByScale(SecpkitbForm f) throws Exception {
		SecpkitbExample e = new SecpkitbExample();
		Criteria c = e.createCriteria();
		if(!CheckUtil.isEmptry(f.getSamId_min()))
			c.andSamIdGreaterThanOrEqualTo(f.getSamId_min());
		if(!CheckUtil.isEmptry(f.getSamId_max()))
			c.andSamIdLessThanOrEqualTo(f.getSamId_max());
		if(!CheckUtil.isEmptry(f.getBeginDate_f())){
			c.andIssueTimeGreaterThanOrEqualTo(DateUtil.parseDate(f.getBeginDate_f()));
		}
		if(!CheckUtil.isEmptry(f.getEndDate_f())){
			c.andIssueTimeLessThanOrEqualTo(DateUtil.parseDate(f.getEndDate_f()));
		}
		e.setOrderByClause("IssueTime desc");
		return secpkitbDAO.selectByExample(e);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public void querySamIdValidate(SecpkitbForm p)throws MessageException{
		if(!CheckUtil.isEmptry(p.getSamId_min()) && !CheckUtil.isEmptry(p.getSamId_max())){	
			if(p.getSamId_min().length()!=p.getSamId_max().length()){
				throw new MessageException("开始卡号长度和结束卡号长度必须一致");
			}
			if(p.getSamId_min().length()!=12 ){
				throw new MessageException("卡号长度必须满足12位");
			}
			if(p.getSamId_min().compareTo(p.getSamId_max())>0)
				throw new MessageException("开始卡号不能大于结束卡号");
		}
		
	}

}
