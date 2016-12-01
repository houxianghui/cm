package com.yly.func;

public class Para {
	private String cardcsn;
	public String getCardcsn() {
		return cardcsn;
	}
	public void setCardcsn(String cardcsn) {
		this.cardcsn = cardcsn;
	}
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


	public int getIsPki() {
		return isPki;
	}
	public void setIsPki(int isPki) {
		this.isPki = isPki;
	}

	public int getW2Limit() {
		return w2Limit;
	}
	public void setW2Limit(int w2Limit) {
		this.w2Limit = w2Limit;
	}
	public String getFivePara() {
		return fivePara;
	}
	public void setFivePara(String fivePara) {
		this.fivePara = fivePara;
	}
	public String getEf15() {
		return ef15;
	}
	public void setEf15(String ef15) {
		this.ef15 = ef15;
	}
	public String getEf16() {
		return ef16;
	}
	public void setEf16(String ef16) {
		this.ef16 = ef16;
	}
	public String getEf17() {
		return ef17;
	}
	public void setEf17(String ef17) {
		this.ef17 = ef17;
	}
	public String getRetpki() {
		return retpki;
	}
	public void setRetpki(String retpki) {
		this.retpki = retpki;
	}
	public String getInpki() {
		return inpki;
	}
	public void setInpki(String inpki) {
		this.inpki = inpki;
	}
	public String getMotEf17() {
		return motEf17;
	}
	public void setMotEf17(String motEf17) {
		this.motEf17 = motEf17;
	}

	public int getZeroExauthFlag() {
		return zeroExauthFlag;
	}
	public void setZeroExauthFlag(int zeroExauthFlag) {
		this.zeroExauthFlag = zeroExauthFlag;
	}

	public int getCardtype() {
		return cardtype;
	}
	public void setCardtype(int cardtype) {
		this.cardtype = cardtype;
	}
	public int getModelflag() {
		return modelflag;
	}
	public void setModelflag(int modelflag) {
		this.modelflag = modelflag;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	private String oldTranskey;
	private String newTranskey;
	private int SJL05IP;
	private String SJL05PORT;
	private int keyType;
	private int isPki;
	private int w2Sign;
	private int w2Limit;
	private String fivePara;
	private String ef15;
	private String ef16;
	private String ef17;
	private String retpki;
    private String phiTypeId;
	public String getPhiTypeId() {
		return phiTypeId;
	}
	public void setPhiTypeId(String phiTypeId) {
		this.phiTypeId = phiTypeId;
	}
	public String getSamId() {
		return samId;
	}
	public void setSamId(String samId) {
		this.samId = samId;
	}
	private String inpki;
	private String motEf17;
	private int authSign;
	public int getKeyType() {
		return keyType;
	}
	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}
	public int getW2Sign() {
		return w2Sign;
	}
	public void setW2Sign(int w2Sign) {
		this.w2Sign = w2Sign;
	}
	public int getAuthSign() {
		return authSign;
	}
	public void setAuthSign(int authSign) {
		this.authSign = authSign;
	}
	private int zeroExauthFlag;
	private String samId;
	private int cardtype;
	private int modelflag;
	private String version;
	private String authkey;
	private String result;
}
