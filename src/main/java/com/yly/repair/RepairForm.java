package com.yly.repair;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class RepairForm extends BaseForm {
	private String cardcsn;
	public String getCardcsn() {
		return cardcsn;
	}
	public void setCardcsn(String cardcsn) {
		this.cardcsn = cardcsn;
	}
	public String getManufacId() {
		return manufacId;
	}
	public void setManufacId(String manufacId) {
		this.manufacId = manufacId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public short getOperationType() {
		return operationType;
	}
	public void setOperationType(short operationType) {
		this.operationType = operationType;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public String getAuthkey() {
		return authkey;
	}
    private String phiTypeId;

	public String getPhiTypeId() {
		return phiTypeId;
	}
	public void setPhiTypeId(String phiTypeId) {
		this.phiTypeId = phiTypeId;
	}
	private Collection authkeycollection;
	public Collection getAuthkeycollection() {
		return SingleDicMap.getOptionCollection(SingleDic.AUTHKEY);
	}
	public void setAuthkeycollection(Collection authkeycollection) {
		this.authkeycollection = authkeycollection;
	}

	private String authkey;
    private String manufacId;
    private String prodId;
    private short operationType;
	private String oldTranskey;
	private String newTranskey;
	public String getOldTranskey() {
		return oldTranskey;
	}
	public void setOldTranskey(String oldTranskey) {
		this.oldTranskey = oldTranskey;
	}
	public String getNewTranskey() {
		return newTranskey;
	}
	public void setNewTranskey(String newTranskey) {
		this.newTranskey = newTranskey;
	}

	private int SJL05IP;
	private String SJL05PORT;
	public int getSJL05IP() {
		return SJL05IP;
	}
	public void setSJL05IP(int sJL05IP) {
		SJL05IP = sJL05IP;
	}
	public String getSJL05PORT() {
		return SJL05PORT;
	}
	public void setSJL05PORT(String sJL05PORT) {
		SJL05PORT = sJL05PORT;
	}
	
}
