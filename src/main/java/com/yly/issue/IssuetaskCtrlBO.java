package com.yly.issue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abc.logic.IbatisBO;
import com.eis.base.BaseDAO;
import com.eis.base.IbatisBaseBO;
import com.eis.key.KeyGenerator;
import com.eis.util.CheckUtil;
import com.eis.util.StringUtil;
import com.yly.issue.IssuetaskctrlExample.Criteria;
import com.yly.para.Applytypeinfo;
import com.yly.para.ApplytypeinfoDAO;



public class IssuetaskCtrlBO extends IbatisBO {
	private IssuetaskctrlDAO issuetaskctrlDAO;
	private ApplytypeinfoDAO applytypeinfoDAO;
	public ApplytypeinfoDAO getApplytypeinfoDAO() {
		return applytypeinfoDAO;
	}

	public void setApplytypeinfoDAO(ApplytypeinfoDAO applytypeinfoDAO) {
		this.applytypeinfoDAO = applytypeinfoDAO;
	}
	private static String EREADER = "4";



	public IssuetaskctrlDAO getIssuetaskctrlDAO() {
		return issuetaskctrlDAO;
	}

	public void setIssuetaskctrlDAO(IssuetaskctrlDAO issuetaskctrlDAO) {
		this.issuetaskctrlDAO = issuetaskctrlDAO;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {

	}
	public int update(Issuetaskctrl obj) throws Exception {
		 return issuetaskctrlDAO.updateByPrimaryKeySelective(obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		Issuetaskctrl p = (Issuetaskctrl)obj;
		issuetaskctrlDAO.insert(p);
	}
	public void batchinsert(List<Issuetaskctrl> il) throws Exception{
		if(il != null && il.size()>0){
			for(Issuetaskctrl di:il){
				di.setTaskCtrlNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("IssueTaskCtrl")),16));
				dao.insertByGenerate("issuetaskctrl", di);
			}
		}
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}
	public Issuetaskctrl queryForObject(String formNo) throws Exception{
		return issuetaskctrlDAO.selectByPrimaryKey(formNo);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("issuetaskctrl.queryList",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(String formNo) throws Exception {
		return dao.queryForList("issuetaskctrl.queryList",formNo);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}
	public List getAppList(IssuetaskctrlForm obj)throws Exception {
		
		IssuetaskctrlExample e = new IssuetaskctrlExample();
		Criteria c = e.createCriteria();
		c.andTaskNoEqualTo(obj.getTaskNo());
		return issuetaskctrlDAO.selectByExample(e);
	}

	public List getListByPrimaryKey(IssuetaskctrlForm obj)throws Exception {
		
		IssuetaskctrlExample e = new IssuetaskctrlExample();
		Criteria c = e.createCriteria();
		c.andTaskCtrlNoEqualTo(obj.getTaskCtrlNo());
		return issuetaskctrlDAO.selectByExample(e);
	}
	public List queryList(String taskno) throws Exception {
		IssuetaskctrlExample e = new IssuetaskctrlExample();
		Criteria c = e.createCriteria();
		c.andTaskNoEqualTo(taskno);
		return issuetaskctrlDAO.selectByExample(e);
	}
	public String[] getSamNo(IssuetaskctrlForm prod,Issuetaskctrl obj){
		Applytypeinfo apply=new Applytypeinfo();
		String[] samNum={"0","0"};
		String ABBBB="";
		if(prod.getKeyType()==2){
			 ABBBB="99999"; //≤‚ ‘√‹‘ø
		}else{
			ABBBB=String.valueOf(obj.getUnitId()).substring(3);
		}
		apply=applytypeinfoDAO.selectByPrimaryKey(String.valueOf(prod.getAppTypeId()));
		String CC=apply.getProdClassId();
		samNum=getSamNum(ABBBB+CC,obj.getIssueAmt().intValue());
		return samNum;
	}
    public String[] getSamNum(String preCardNo,int num) {
    	String currMaxcard =issuetaskctrlDAO.getSamNum(preCardNo);
    	String maxcard_s=preCardNo+"00000";
    	
    	
    	if(!CheckUtil.isEmptry(currMaxcard)){

    		maxcard_s=currMaxcard;
    	}
    	Long maxcard=Long.parseLong(maxcard_s);
    	String a=String.valueOf(++maxcard);
    	Long maxcard_end=maxcard+num-1;
    	String b=String.valueOf(maxcard_end);
		if(a.length()!=12){
			a=StringUtil.addZeroB(a, 12-a.length());
			b=StringUtil.addZeroB(b, 12-b.length());
		}    	
    	String[] cardno={a,b};
        return cardno;
    }
	public int sumByAppNo(IssuetaskctrlForm obj){
		IssuetaskctrlExample e = new IssuetaskctrlExample();
		Criteria c = e.createCriteria();
		c.andAppNoEqualTo(obj.getAppNo());
		return issuetaskctrlDAO.sumByExample(e);
	}

    	
}
;