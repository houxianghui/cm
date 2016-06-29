/*
 * @# ProjectMaintainForm.java 2008-11-6 houxh
 *
 */
 
package com.yly.presscard;

import java.util.Collection;
import com.eis.base.BaseForm;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;


public class PressCardForm extends BaseForm {
	
	private String formNo;
	private String cardType;
	private Collection cardTypeCollection;
	private String manufacId;
	private Collection manufacIdCollection;
	private String ereaderManufacId;
	private Collection ereaderManufacIdCollection;	
	public String getEreaderManufacId() {
		return ereaderManufacId;
	}
	public void setEreaderManufacId(String ereaderManufacId) {
		this.ereaderManufacId = ereaderManufacId;
	}
	public Collection getEreaderManufacIdCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.EREADERMAUN_ID);
	}
	public void setEreaderManufacIdCollection(Collection ereaderManufacIdCollection) {
		this.ereaderManufacIdCollection = ereaderManufacIdCollection;
	}
	public Collection getManufacIdCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.MAUN_ID);
	}
	public void setManufacIdCollection(Collection manufacIdCollection) {
		this.manufacIdCollection = manufacIdCollection;
	}
	private int purchaseAmt;
	private String commRate;
	public String getFormNo() {
		return formNo;
	}
	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Collection getCardTypeCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.CARD_TYPE);
	}
	public void setCardTypeCollection(Collection cardTypeCollection) {
		this.cardTypeCollection = cardTypeCollection;
	}
	public String getManufacId() {
		return manufacId;
	}
	public void setManufacId(String manufacId) {
		this.manufacId = manufacId;
	}
	public int getPurchaseAmt() {
		return purchaseAmt;
	}
	public void setPurchaseAmt(int purchaseAmt) {
		this.purchaseAmt = purchaseAmt;
	}
	public String getCommRate() {
		return commRate;
	}
	public void setCommRate(String commRate) {
		this.commRate = commRate;
	}

	public String getCurrDate() {
		return currDate;
	}
	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}
	public String getRsvd() {
		return rsvd;
	}
	public void setRsvd(String rsvd) {
		this.rsvd = rsvd;
	}
	public String getPressCard() {
		return pressCard;
	}
	public void setPressCard(String pressCard) {
		this.pressCard = pressCard;
	}
	public String getBeginDate_f() {
		return beginDate_f;
	}
	public void setBeginDate_f(String beginDate_f) {
		this.beginDate_f = beginDate_f;
	}
	public String getEndDate_f() {
		return endDate_f;
	}
	public void setEndDate_f(String endDate_f) {
		this.endDate_f = endDate_f;
	}
	private String currDate;
	private String operId;
	private String remarks;
	private String rsvd;
	private String pressCard;
	private String pressCard_min;
	private String pressCard_max;	
	public String getPressCard_max() {
		return pressCard_max;
	}
	public void setPressCard_max(String pressCard_max) {
		this.pressCard_max = pressCard_max;
	}
	public void setPressCard_min(String pressCard_min) {
		this.pressCard_min = pressCard_min;
	}
	public String getPressCard_min() {
		return pressCard_min;
	}
	public void setPressCard_f(String pressCard_min) {
		this.pressCard_min = pressCard_min;
	}
	private String beginDate_f;
	private String endDate_f;
	private String requery;
	public String getRequery() {
		return requery;
	}
	public void setRequery(String requery) {
		this.requery = requery;
	}
	
	private String purchaseType; 
	private String applyAttr;
	private String hardVersion;
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getApplyAttr() {
		return applyAttr;
	}
	public void setApplyAttr(String applyAttr) {
		this.applyAttr = applyAttr;
	}
	public String getHardVersion() {
		return hardVersion;
	}
	public void setHardVersion(String hardVersion) {
		this.hardVersion = hardVersion;
	}
	
	
}
