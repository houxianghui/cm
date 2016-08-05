package com.yly.issue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.abc.logic.IbatisBO;
import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.KeyVDatagram;
import com.eis.util.StringUtil;
import com.yly.drools.Func;
import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductDAO;
import com.yly.issue.MwsissuetbExample.Criteria;
import com.yly.ls.Lsinfo;
import com.yly.ls.LsinfoDAO;
import com.yly.pki.Secpkitb;
import com.yly.pki.SecpkitbDAO;
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
	private SecpkitbDAO secpkitbDAO;
	public SecpkitbDAO getSecpkitbDAO() {
		return secpkitbDAO;
	}

	public void setSecpkitbDAO(SecpkitbDAO secpkitbDAO) {
		this.secpkitbDAO = secpkitbDAO;
	}

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
	public void transFiveTb(Mwsissuetb mtb,Stoproduct s,Lsinfo ls,Issueapp issue,Secpkitb sec) throws Exception {
		stoproductDAO.insert(s);
		lsinfoDAO.insert(ls);
		secpkitbDAO.insert(sec);
		mwsissuetbDAO.updateByPrimaryKeySelective(mtb);
		if(issue!=null && issue.getFormState()==3)
			issueappDAO.updateByPrimaryKeySelective(issue);
	}
	
	
	public void transRepairTb(Stoproduct s,Lsinfo ls,Secpkitb sec) throws Exception {
		stoproductDAO.updateByPrimaryKeySelective(s);
		lsinfoDAO.insert(ls);
		secpkitbDAO.updateBySamIdAndSamCsn(sec);
	}
	@Override
	public List queryForList(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Mwsissuetb queryIssueTaskCtrl(String formNo) throws Exception{
		return mwsissuetbDAO.queryIssueTaskCtrl(formNo);
	}
	
	@Autowired
	KeyVDatagram keyVDatagram;
	public void initMwsissueToPara(MWsIssuetbForm f) {
		f.setAuthSign(0);
		f.setW2Sign(0);
		f.setW2Limits(0);
		f.setIsPki(0);
		f.setIsHTCard(0);
		f.setZeroExauthFlag(0);
		short operType=f.getOperationType();
		if(operType==21||operType==24){
			f.setOldTranskey(keyVDatagram.getMainKeyMap(getMainKey(f)));
			f.setNewTranskey(keyVDatagram.getMainKeyMap("BMAC_KEY"));    
		}else if(operType==22 || operType==23||operType==25||operType==26){
			f.setOldTranskey(keyVDatagram.getMainKeyMap("BMAC_KEY"));
			f.setNewTranskey(keyVDatagram.getMainKeyMap(getMainKey(f)));    
		}else{
			f.setOldTranskey("");
			f.setNewTranskey("");    
		}
		f.setSJL05IP(6666);        
		f.setSJL05PORT("192.168.1.82");               
		f.setFivePara("10100");       
		if(f.getSamId()!=null && f.getSamId().length()==12){
			f.setEf15("00001000"+f.getSamId()+ "01011000");           
			f.setEf16(f.getSamId());      
		}else{
			f.setEf15("");
			f.setEf16("");
		}
		f.setEf17("0101011000000000001101100000000000"+DateUtil.getDTStr()+"20991231");           
		f.setRetpki("");         
		f.setInpki("");          
		f.setMotEf17("");        
		f.setSamId("");       
		f.setModelflag(0);      
		f.setVersion("");        
		f.setAuthkey("");        
		f.setResult("");         
		f.setCardcsn(""); 
		f.setCardtype(0);
	}
	private String getMainKey(MWsIssuetbForm f) {
		String KEY;
		if(CheckUtil.isEmptry(f.getManufacId())){
			return "BMAC_KEY";
		}else{
			if(f.getProdId().equals("4"))
				KEY="JSB_KEY";
			else{
				if(f.getManufacId().equals("1"))
					KEY="WQ_KEY";
				else
					KEY="ALLF_KEY";
			}
		}
		
		return KEY;
	}
	public Stoproduct setSto(Mwsissuetb vo, Lsinfo lsvo) throws Exception {
		Stoproduct sto= new Stoproduct();
		copyProperties(sto, vo);
		if(vo.getProdId().equals("4")){
			Stoappinfo stoapp =stoappinfoDAO.selectByPrimaryKey(vo.getBatchId());
			sto.setBatchIdParts(stoapp.getRsvd());
		}
		sto.setAppTypeId(vo.getApplyAttr());
		sto.setIssueTime(DateUtil.getTimeStr());
		sto.setWkStateChgDate(DateUtil.getTimeStr());
		sto.setDetectSign((short)0);
		sto.setSamCSN(lsvo.getSamCSN());
		sto.setOAappNo(vo.getAppNo());
		sto.setWkState((short)12);//已发行
		sto.setCardPhyStat((short)1);//好卡
		sto.setSamId(lsvo.getSamId());
		sto.setOAappNo(lsvo.getAppNo());
		sto.setIOState((short)1);
		sto.setOAappNo(vo.getOAappNo());
		return sto;
	}
	public void copyProperties(Object dest,Object origin) throws Exception {
		new BeanUtilsBean().copyProperties(dest,origin);
	}
	public Lsinfo setLsInfo(UserContext user, Mwsissuetb vo) throws Exception {
		Lsinfo lsvo= new Lsinfo();
		copyProperties(lsvo, vo);
		lsvo.setCurrDate(DateUtil.getTimeStr());
		lsvo.setDetectSign((short)0);
		lsvo.setFlowNo(StringUtil.addZero(Long.toString(KeyGenerator.getNextKey("LsInfo")),20));
		lsvo.setOperId(user.getUserID());
		lsvo.setErrorCode((short)0);
		lsvo.setSamCSNOld("");
		lsvo.setSamIdOld("");
		lsvo.setSamCSN("");
		return lsvo;
	}
	public void setFunc(MWsIssuetbForm f, Func func) {
		func.setApplyAttr(f.getApplyAttr());
		func.setManufacId(f.getManufacId());
		func.setProdId(f.getProdId());
	}

	public void setOperAct(Mwsissuetb vo, Func func,int step) {
		short operType=vo.getOperationType();
		if(operType==21||operType==24||operType==43||operType==53 ){
			if(step==1){
				func.setOperAct("RC");
			}else if(step==2){
				func.setOperAct("W");
			}else if(step==3){
				func.setOperAct("I");
			}
			
		}else if(operType==22 || operType==23||operType==25||operType==26){	
			if(step==1){
				func.setOperAct("R");
			}else if(step==2){
				func.setOperAct("W");
			}else if(step==3){
				func.setOperAct("I");
			}
		}
	}
}
