package com.yly.issue;


import java.util.List;

import com.abc.logic.IbatisBO;

import com.yly.issue.IssuetaskExample.Criteria;



public class IssuetaskBO extends IbatisBO {
	private IssuetaskDAOImpl issuetaskDAO;
	private IssuetaskctrlDAOImpl issuetaskctrlDAO;
	public IssuetaskctrlDAOImpl getIssuetaskctrlDAO() {
		return issuetaskctrlDAO;
	}

	public void setIssuetaskctrlDAO(IssuetaskctrlDAOImpl issuetaskctrlDAO) {
		this.issuetaskctrlDAO = issuetaskctrlDAO;
	}

	public IssuetaskDAOImpl getIssuetaskDAO() {
		return issuetaskDAO;
	}

	public void setIssuetaskDAO(IssuetaskDAOImpl issuetaskDAO) {
		this.issuetaskDAO = issuetaskDAO;
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
		Issuetask p = (Issuetask)obj;
		
		issuetaskDAO.insert(p);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}
	public Issuetask queryForObject(String formNo) throws Exception{
		return issuetaskDAO.selectByPrimaryKey(formNo);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("issuetask.queryList",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(String formNo) throws Exception {
		return dao.queryForList("issuetask.queryList",formNo);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public int transDelete(String formNo) throws Exception {
		IssuetaskctrlExample e = new IssuetaskctrlExample();
		com.yly.issue.IssuetaskctrlExample.Criteria c= e.createCriteria();
		c.andTaskNoEqualTo(formNo);
		return issuetaskDAO.deleteByPrimaryKey(formNo)+issuetaskctrlDAO.deleteByExample(e);

	}
	public void delete(Object obj) throws Exception {

	}
	public List getAppList(IssueappForm obj)throws Exception {
		
		IssuetaskExample e = new IssuetaskExample();
		Criteria c = e.createCriteria();

		return issuetaskDAO.selectByExample(e);
	}
	public List queryList(String appno) throws Exception {
		IssuetaskExample e = new IssuetaskExample();
		Criteria c = e.createCriteria();
		c.andAppNoEqualTo(appno);
		return issuetaskDAO.selectByExample(e);
	}
	public int sumByAppNo(IssuetaskForm obj){
		IssuetaskExample e = new IssuetaskExample();
		Criteria c = e.createCriteria();
		c.andAppNoEqualTo(obj.getAppNo());
		return issuetaskDAO.sumByExample(e);
	}
	public void validateNum(IssuetaskForm obj)throws Exception {
		if(obj.getTaskAmt()<(sumByAppNo(obj)+obj.getIssueAmt()))
			throw new Exception("任务单发行总数量超过申请单总数,请修改");
	}


}
