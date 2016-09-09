package com.yly.issue;

import java.util.Collection;
import java.util.Date;

import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class MWsIssuetbForm  extends BaseForm {
	public Collection getModuleVerEffcollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.EFFMODULEVER);
	}

	public void setModuleVerEffcollection(Collection moduleVerEffcollection) {
		this.moduleVerEffcollection = moduleVerEffcollection;
	}
	private String origSamId;
 
	
	public String getOrigSamId() {
		return origSamId;
	}

	public void setOrigSamId(String origSamId) {
		this.origSamId = origSamId;
	}
	private short formState_f;
	public short getFormState_f() {
		return formState_f;
	}

	public void setFormState_f(short formState_f) {
		this.formState_f = formState_f;
	}
	private Collection formStatecollection;
	public Collection getFormStatecollection() {
		return SingleDicMap.getOptionCollection(SingleDic.FORMTYPE);
	}

	public void setFormStatecollection(Collection formStatecollection) {
		this.formStatecollection = formStatecollection;
	}
	private Collection authkeycollection;
	public Collection getAuthkeycollection() {
		return SingleDicMap.getOptionCollection(SingleDic.AUTHKEY);
	}
	public void setAuthkeycollection(Collection authkeycollection) {
		this.authkeycollection = authkeycollection;
	}
	private Collection moduleVerEffcollection;
	private String cardcsn;
	private String oldTranskey;
	private String newTranskey;
	private int SJL05IP;
	private String SJL05PORT;
	private int keyType;
	private int w2Sign;
	private int w2Limits;
	private int authSign;
	private String fivePara;
	private String ef15;
	private String ef16;
	private String ef17;
	private String retpki;
	private String inpki;
	private String motEf17;
	private Short operationType;
	private Integer appTypeId;
	private String OAappNo;
	private int appUnitId;
	
	public int getAppUnitId() {
		return appUnitId;
	}

	public void setAppUnitId(int appUnitId) {
		this.appUnitId = appUnitId;
	}

	public String getOAappNo() {
		return OAappNo;
	}

	public void setOAappNo(String oAappNo) {
		OAappNo = oAappNo;
	}

	public Short getOperationType() {
		return operationType;
	}

	public Integer getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(Integer appTypeId) {
		this.appTypeId = appTypeId;
	}

	public void setOperationType(Short operationType) {
		this.operationType = operationType;
	}

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

	private int cardtype;
	private int modelflag;
	private String version;
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

	public int getW2Limits() {
		return w2Limits;
	}

	public void setW2Limits(int w2Limits) {
		this.w2Limits = w2Limits;
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


	private String authkey;
	private String result;
	private String manufacId;
	public String getManufacId() {
		return manufacId;
	}

	public void setManufacId(String manufacId) {
		this.manufacId = manufacId;
	}

	private Collection operationTypecollection;
	private Collection unitIdcollection;
    private String beginDate_f;
    private String endDate_f;
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

	public Collection getOperationTypecollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.ISSOPERATIONTYPE);
	}

	public void setOperationTypecollection(Collection operationTypecollection) {
		this.operationTypecollection = operationTypecollection;
	}

	public Collection getUnitIdcollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.ALL_UNITID);	}

	public void setUnitIdcollection(Collection unitIdcollection) {
		this.unitIdcollection = unitIdcollection;
	}

	/**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.FormNo
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String formNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.TaskCtrlNo
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String taskCtrlNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.FormTime
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String formTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.WsSnr
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private Short wsSnr;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.ProdId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String prodId;


    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.BinFileVer
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String binFileVer;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.PhiTypeId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String phiTypeId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.ApplyAttr
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String applyAttr;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.BatchId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String batchId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.PressCardScale
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String pressCardScale;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.WorkSheetAmt
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private Long workSheetAmt;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.SamIdBegin
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String samIdBegin;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.SamIdEnd
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String samIdEnd;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.SamId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String samId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.IssueDoneAmt
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private Long issueDoneAmt;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.FormState
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private Short formState;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.IssueOperID
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String issueOperID;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column mwsissuetb.Remarks
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private String remarks;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.FormNo
     *
     * @return the value of mwsissuetb.FormNo
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getFormNo() {
        return formNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.FormNo
     *
     * @param formNo the value for mwsissuetb.FormNo
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setFormNo(String formNo) {
        this.formNo = formNo == null ? null : formNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.TaskCtrlNo
     *
     * @return the value of mwsissuetb.TaskCtrlNo
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getTaskCtrlNo() {
        return taskCtrlNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.TaskCtrlNo
     *
     * @param taskCtrlNo the value for mwsissuetb.TaskCtrlNo
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setTaskCtrlNo(String taskCtrlNo) {
        this.taskCtrlNo = taskCtrlNo == null ? null : taskCtrlNo.trim();
    }

 

    public String getFormTime() {
		return formTime;
	}

	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}

	/**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.WsSnr
     *
     * @return the value of mwsissuetb.WsSnr
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public Short getWsSnr() {
        return wsSnr;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.WsSnr
     *
     * @param wsSnr the value for mwsissuetb.WsSnr
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setWsSnr(Short wsSnr) {
        this.wsSnr = wsSnr;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.ProdId
     *
     * @return the value of mwsissuetb.ProdId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.ProdId
     *
     * @param prodId the value for mwsissuetb.ProdId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.KeyType
     *
     * @param keyType the value for mwsissuetb.KeyType
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setKeyType(Short keyType) {
        this.keyType = keyType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.BinFileVer
     *
     * @return the value of mwsissuetb.BinFileVer
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getBinFileVer() {
        return binFileVer;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.BinFileVer
     *
     * @param binFileVer the value for mwsissuetb.BinFileVer
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setBinFileVer(String binFileVer) {
        this.binFileVer = binFileVer == null ? null : binFileVer.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.PhiTypeId
     *
     * @return the value of mwsissuetb.PhiTypeId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getPhiTypeId() {
        return phiTypeId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.PhiTypeId
     *
     * @param phiTypeId the value for mwsissuetb.PhiTypeId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setPhiTypeId(String phiTypeId) {
        this.phiTypeId = phiTypeId == null ? null : phiTypeId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.ApplyAttr
     *
     * @return the value of mwsissuetb.ApplyAttr
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getApplyAttr() {
        return applyAttr;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.ApplyAttr
     *
     * @param applyAttr the value for mwsissuetb.ApplyAttr
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setApplyAttr(String applyAttr) {
        this.applyAttr = applyAttr == null ? null : applyAttr.trim();
    }


    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.BatchId
     *
     * @return the value of mwsissuetb.BatchId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.BatchId
     *
     * @param batchId the value for mwsissuetb.BatchId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.PressCardScale
     *
     * @return the value of mwsissuetb.PressCardScale
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getPressCardScale() {
        return pressCardScale;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.PressCardScale
     *
     * @param pressCardScale the value for mwsissuetb.PressCardScale
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setPressCardScale(String pressCardScale) {
        this.pressCardScale = pressCardScale == null ? null : pressCardScale.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.WorkSheetAmt
     *
     * @return the value of mwsissuetb.WorkSheetAmt
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public Long getWorkSheetAmt() {
        return workSheetAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.WorkSheetAmt
     *
     * @param workSheetAmt the value for mwsissuetb.WorkSheetAmt
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setWorkSheetAmt(Long workSheetAmt) {
        this.workSheetAmt = workSheetAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.SamIdBegin
     *
     * @return the value of mwsissuetb.SamIdBegin
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getSamIdBegin() {
        return samIdBegin;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.SamIdBegin
     *
     * @param samIdBegin the value for mwsissuetb.SamIdBegin
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setSamIdBegin(String samIdBegin) {
        this.samIdBegin = samIdBegin == null ? null : samIdBegin.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.SamIdEnd
     *
     * @return the value of mwsissuetb.SamIdEnd
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getSamIdEnd() {
        return samIdEnd;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.SamIdEnd
     *
     * @param samIdEnd the value for mwsissuetb.SamIdEnd
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setSamIdEnd(String samIdEnd) {
        this.samIdEnd = samIdEnd == null ? null : samIdEnd.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.SamId
     *
     * @return the value of mwsissuetb.SamId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getSamId() {
        return samId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.SamId
     *
     * @param samId the value for mwsissuetb.SamId
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setSamId(String samId) {
        this.samId = samId == null ? null : samId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.IssueDoneAmt
     *
     * @return the value of mwsissuetb.IssueDoneAmt
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public Long getIssueDoneAmt() {
        return issueDoneAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.IssueDoneAmt
     *
     * @param issueDoneAmt the value for mwsissuetb.IssueDoneAmt
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setIssueDoneAmt(Long issueDoneAmt) {
        this.issueDoneAmt = issueDoneAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.FormState
     *
     * @return the value of mwsissuetb.FormState
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public Short getFormState() {
        return formState;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.FormState
     *
     * @param formState the value for mwsissuetb.FormState
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setFormState(Short formState) {
        this.formState = formState;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.IssueOperID
     *
     * @return the value of mwsissuetb.IssueOperID
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getIssueOperID() {
        return issueOperID;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.IssueOperID
     *
     * @param issueOperID the value for mwsissuetb.IssueOperID
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setIssueOperID(String issueOperID) {
        this.issueOperID = issueOperID == null ? null : issueOperID.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column mwsissuetb.Remarks
     *
     * @return the value of mwsissuetb.Remarks
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column mwsissuetb.Remarks
     *
     * @param remarks the value for mwsissuetb.Remarks
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

	private int zeroExauthFlag;
    private int isPki;
	private int isHTCard;

	public int getZeroExauthFlag() {
		return zeroExauthFlag;
	}

	public void setZeroExauthFlag(int zeroExauthFlag) {
		this.zeroExauthFlag = zeroExauthFlag;
	}

	public int getIsPki() {
		return isPki;
	}

	public void setIsPki(int isPki) {
		this.isPki = isPki;
	}

	public int getIsHTCard() {
		return isHTCard;
	}

	public void setIsHTCard(int isHTCard) {
		this.isHTCard = isHTCard;
	}
    private String appNo;
    private String taskNo;
	private Integer unitId;
	private Short consumeType;
	private Short paymentType;
	private Long unitPrice;
	private Short taskState;

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Short getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(Short consumeType) {
		this.consumeType = consumeType;
	}

	public Short getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Short paymentType) {
		this.paymentType = paymentType;
	}

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Short getTaskState() {
		return taskState;
	}

	public void setTaskState(Short taskState) {
		this.taskState = taskState;
	}
}