package com.yly.issue;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefMDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class IssueappForm  extends BaseForm {
	
	private Collection makeupTypecollection;
	public Collection getMakeupTypecollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.MAKEUPTYPE);
	}
	public void setMakeupTypecollection(Collection makeupTypecollection) {
		this.makeupTypecollection = makeupTypecollection;
	}
	private Collection formStatecollection;
	private Collection operationTypecollection;
	private Collection exOperTypecollection;
	public Collection getExOperTypecollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.EXOPERATIONTYPE);
	}
	public void setExOperTypecollection(Collection exOperTypecollection) {
		this.exOperTypecollection = exOperTypecollection;
	}
	private Collection exChangeTypecollection;
	public Collection getExChangeTypecollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.EXCHANGETYPE);
	}
	public void setExChangeTypecollection(Collection exChangeTypecollection) {
		this.exChangeTypecollection = exChangeTypecollection;
	}
	private Collection unitIdcollection;
	private Collection prodIdcollection;
	private Collection phiTypeIdcollection;
	public Collection getProdIdcollection() {
		return SingleDicMap.getOptionCollection(SingleDic.PROD_ID);
	}
	public void setProdIdcollection(Collection prodIdcollection) {
		this.prodIdcollection = prodIdcollection;
	}
	public Collection getPhiTypeIdcollection() {
		return SingleDicMap.getOptionCollection(SingleDic.COMM_RATE);
	}
	public void setPhiTypeIdcollection(Collection phiTypeIdcollection) {
		this.phiTypeIdcollection = phiTypeIdcollection;
	}
	private Double totalPrice;
	private Integer appTypeId;
	private Integer formState;
	private Integer operationType;
	private Integer operationType_f;
	public Integer getOperationType_f() {
		return operationType_f;
	}
	public void setOperationType_f(Integer operationType_f) {
		this.operationType_f = operationType_f;
	}
	private Integer taskAmt;
	private Integer taskAmtLeft;
	public Integer getTaskAmtLeft() {
		return taskAmtLeft;
	}
	public void setTaskAmtLeft(Integer taskAmtLeft) {
		this.taskAmtLeft = taskAmtLeft;
	}
	private Integer unitId;
	private Integer unitId_f;
	public Integer getUnitId_f() {
		return unitId_f;
	}
	public void setUnitId_f(Integer unitId_f) {
		this.unitId_f = unitId_f;
	}
	private Long issueAmt;
	private Long issueDoneAmt;
	private Short authSign;
	private Short consumeType;
	private Short keyType;
	private Short paymentType;
	private Short w2Sign;
	private String appNo;
	private String beginDate_f;
	private String binFileVer;
	private String currDate;
	private String director;
	private String endDate_f;
	private String OAappNo;
	private String operId;
	private String origSamId;
	private String[] cx;
	public String[] getCx() {
		return cx;
	}
	public void setCx(String[] cx) {
		this.cx = cx;
	}
	public Collection getFormStatecollection() {
		return formStatecollection;
	}
	public void setFormStatecollection(Collection formStatecollection) {
		this.formStatecollection = formStatecollection;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getAppTypeId() {
		return appTypeId;
	}
	public void setAppTypeId(Integer appTypeId) {
		this.appTypeId = appTypeId;
	}
	public Integer getFormState() {
		return formState;
	}
	public void setFormState(Integer formState) {
		this.formState = formState;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public Integer getTaskAmt() {
		return taskAmt;
	}
	public void setTaskAmt(Integer taskAmt) {
		this.taskAmt = taskAmt;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Long getIssueAmt() {
		return issueAmt;
	}
	public void setIssueAmt(Long issueAmt) {
		this.issueAmt = issueAmt;
	}
	public Long getIssueDoneAmt() {
		return issueDoneAmt;
	}
	public void setIssueDoneAmt(Long issueDoneAmt) {
		this.issueDoneAmt = issueDoneAmt;
	}
	public Short getAuthSign() {
		return authSign;
	}
	public void setAuthSign(Short authSign) {
		this.authSign = authSign;
	}
	public Short getConsumeType() {
		return consumeType;
	}
	public void setConsumeType(Short consumeType) {
		this.consumeType = consumeType;
	}
	public Short getKeyType() {
		return keyType;
	}
	public void setKeyType(Short keyType) {
		this.keyType = keyType;
	}
	public Short getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Short paymentType) {
		this.paymentType = paymentType;
	}
	public Short getW2Sign() {
		return w2Sign;
	}
	public void setW2Sign(Short w2Sign) {
		this.w2Sign = w2Sign;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getBeginDate_f() {
		return beginDate_f;
	}
	public void setBeginDate_f(String beginDate_f) {
		this.beginDate_f = beginDate_f;
	}
	public String getBinFileVer() {
		return binFileVer;
	}
	public void setBinFileVer(String binFileVer) {
		this.binFileVer = binFileVer;
	}
	public String getCurrDate() {
		return currDate;
	}
	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getEndDate_f() {
		return endDate_f;
	}
	public void setEndDate_f(String endDate_f) {
		this.endDate_f = endDate_f;
	}
	public String getOAappNo() {
		return OAappNo;
	}
	public void setOAappNo(String oAappNo) {
		OAappNo = oAappNo;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getOrigSamId() {
		return origSamId;
	}
	public void setOrigSamId(String origSamId) {
		this.origSamId = origSamId;
	}
	public String getPhiTypeId() {
		return phiTypeId;
	}
	public void setPhiTypeId(String phiTypeId) {
		this.phiTypeId = phiTypeId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProjContNum() {
		return projContNum;
	}
	public void setProjContNum(String projContNum) {
		this.projContNum = projContNum;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSamIdBegin() {
		return samIdBegin;
	}
	public void setSamIdBegin(String samIdBegin) {
		this.samIdBegin = samIdBegin;
	}
	public String getSamIdEnd() {
		return samIdEnd;
	}
	public void setSamIdEnd(String samIdEnd) {
		this.samIdEnd = samIdEnd;
	}
	public String getTaskCtrlNo() {
		return taskCtrlNo;
	}
	public void setTaskCtrlNo(String taskCtrlNo) {
		this.taskCtrlNo = taskCtrlNo;
	}
	public String getUnitperson() {
		return unitperson;
	}
	public void setUnitperson(String unitperson) {
		this.unitperson = unitperson;
	}
	public String getUnittel() {
		return unittel;
	}
	public void setUnittel(String unittel) {
		this.unittel = unittel;
	}
	private String phiTypeId;
	private String prodId;
	private String projContNum;
	private String projName;
	private String remarks;
	private String samIdBegin;
	private String samIdEnd;
	private String taskCtrlNo;
	private String unitperson;
	private String unittel;
	private String formNo;

	public String getFormNo() {
		return formNo;
	}
	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}
	public Collection getUnitIdcollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.UNITID);
	}
	public void setUnitIdcollection(Collection unitIdcollection) {
		this.unitIdcollection = unitIdcollection;
	}

	public Collection getOperationTypecollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.ISSOPERATIONTYPE);
	}
	public void setOperationTypecollection(Collection operationTypecollection) {
		this.operationTypecollection = operationTypecollection;
	}
    private Integer w2limit;
	private Integer zeroExauthFlag;
    private Integer isPki;
	private Integer isHTCard;
	public Integer getW2limit() {
		return w2limit;
	}
	public void setW2limit(Integer w2limit) {
		this.w2limit = w2limit;
	}
	public Integer getZeroExauthFlag() {
		return zeroExauthFlag;
	}
	public void setZeroExauthFlag(Integer zeroExauthFlag) {
		this.zeroExauthFlag = zeroExauthFlag;
	}
	public Integer getIsPki() {
		return isPki;
	}
	public void setIsPki(Integer isPki) {
		this.isPki = isPki;
	}
	public Integer getIsHTCard() {
		return isHTCard;
	}
	public void setIsHTCard(Integer isHTCard) {
		this.isHTCard = isHTCard;
	}
	private String batchIdParts;
	public String getBatchIdParts() {
		return batchIdParts;
	}
	public void setBatchIdParts(String batchIdParts) {
		this.batchIdParts = batchIdParts;
	}
	
}