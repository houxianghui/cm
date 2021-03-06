package com.yly.exstore;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class StoproductForm extends BaseForm {
	private String isHTCard;
	public String getIsHTCard() {
		return isHTCard;
	}

	public void setIsHTCard(String isHTCard) {
		this.isHTCard = isHTCard;
	}
	private String beginDate_f;
	private String endDate_f;
	private String samCsn_f;
	public String getSamCsn_f() {
		return samCsn_f;
	}

	public void setSamCsn_f(String samCsn_f) {
		this.samCsn_f = samCsn_f;
	}

	public String getEndDate_f() {
		return endDate_f;
	}

	public void setEndDate_f(String endDate_f) {
		this.endDate_f = endDate_f;
	}

	public String getBeginDate_f() {
		return beginDate_f;
	}

	public void setBeginDate_f(String beginDate_f) {
		this.beginDate_f = beginDate_f;
	}
	private Integer issueAmt;
	private Integer exAmt;
    public Integer getExAmt() {
		return exAmt;
	}

	public void setExAmt(Integer exAmt) {
		this.exAmt = exAmt;
	}
	private Integer exUnitId;
	public Integer getIssueAmt() {
		return issueAmt;
	}

	public void setIssueAmt(Integer issueAmt) {
		this.issueAmt = issueAmt;
	}

	public Integer getExUnitId() {
		return exUnitId;
	}

	public void setExUnitId(Integer exUnitId) {
		this.exUnitId = exUnitId;
	}
	private Collection manufacIdCollection;
	public Collection getManufacIdCollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.MAUN_ID);
	}

	public void setManufacIdCollection(Collection manufacIdCollection) {
		this.manufacIdCollection = manufacIdCollection;
	}
	private Collection prodIdCollection;
	public Collection getProdIdCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.PROD_ID);
	}

	public void setProdIdCollection(Collection prodIdCollection) {
		this.prodIdCollection = prodIdCollection;
	}
	private Collection IOStatecollection;
	public Collection getIOStatecollection() {
		return SingleDicMap.getOptionCollection(SingleDic.IO_STATE);
	}

	public void setIOStatecollection(Collection iOStatecollection) {
		IOStatecollection = iOStatecollection;
	}

	private Collection wkStatecollection;
	public Collection getWkStatecollection() {
		return SingleDicMap.getOptionCollection(SingleDic.WKSTATE);
	}

	public void setWkStatecollection(Collection wkStatecollection) {
		this.wkStatecollection = wkStatecollection;
	}

	private String appNo;
	private Integer taskAmt;
	private Integer taskAmtLeft;
	private String BatchIdParts;

	public String getBatchIdParts() {
		return BatchIdParts;
	}

	public void setBatchIdParts(String batchIdParts) {
		BatchIdParts = batchIdParts;
	}

	public Integer getTaskAmtLeft() {
		return taskAmtLeft;
	}

	public void setTaskAmtLeft(Integer taskAmtLeft) {
		this.taskAmtLeft = taskAmtLeft;
	}

	private Integer operationType;
	private Collection unitIdcollection;
	private Collection prodIdcollection;
	private Collection phiTypeIdcollection;
	private Collection appTypeIdcollection;
	private Collection moduleVerEffcollection;

	public Collection getModuleVerEffcollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.MODULEVERSION);
	}

	public void setModuleVerEffcollection(Collection moduleVerEffcollection) {
		this.moduleVerEffcollection = moduleVerEffcollection;
	}

	public Collection getAppTypeIdcollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.APPTYPEID);
	}

	public void setAppTypeIdcollection(Collection appTypeIdcollection) {
		this.appTypeIdcollection = appTypeIdcollection;
	}

	public Collection getUnitIdcollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.UNITID);
	}

	public void setUnitIdcollection(Collection unitIdcollection) {
		this.unitIdcollection = unitIdcollection;
	}

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

	public String[] getCx() {
		return cx;
	}

	public void setCx(String[] cx) {
		this.cx = cx;
	}

	private String[] cx;
    public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public Integer getTaskAmt() {
		return taskAmt;
	}

	public void setTaskAmt(Integer taskAmt) {
		this.taskAmt = taskAmt;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	/**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.SamId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String samId;
    private String samId_min;
    public String getSamId_min() {
		return samId_min;
	}

	public void setSamId_min(String samId_min) {
		this.samId_min = samId_min;
	}

	public String getSamId_max() {
		return samId_max;
	}

	public void setSamId_max(String samId_max) {
		this.samId_max = samId_max;
	}

	private String samId_max;
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.SamCSN
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String samCSN;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.OAappNo
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String OAappNo;
    private String OAappNo_f;

    public String getOAappNo_f() {
		return OAappNo_f;
	}

	public void setOAappNo_f(String oAappNo_f) {
		OAappNo_f = oAappNo_f;
	}

	/**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.ProdId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String prodId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.PhiTypeId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String phiTypeId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.AppTypeId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String appTypeId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.KeyType
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Short keyType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.BinFileVer
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String binFileVer;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.ManufacId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String manufacId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.BatchId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String batchId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.WkState
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Short wkState;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.WkStateChgDate
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String wkStateChgDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.IOState
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Short IOState;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.IOStateChgDate
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String IOStateChgDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.ReuseTime
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Short reuseTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.UnitId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Integer unitId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.IssueTime
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String issueTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.DetectSign
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Short detectSign;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.DetectTime
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String detectTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.PaymentType
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Short paymentType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.ConsumeType
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Short consumeType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.W2Sign
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Short w2Sign;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.AuthSign
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Short authSign;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.UnitPrice
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private Long unitPrice;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.PurchuseDate
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String purchuseDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoproduct.Remarks
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    private String remarks;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.SamId
     *
     * @return the value of stoproduct.SamId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getSamId() {
        return samId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.SamId
     *
     * @param samId the value for stoproduct.SamId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setSamId(String samId) {
        this.samId = samId == null ? null : samId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.SamCSN
     *
     * @return the value of stoproduct.SamCSN
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getSamCSN() {
        return samCSN;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.SamCSN
     *
     * @param samCSN the value for stoproduct.SamCSN
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setSamCSN(String samCSN) {
        this.samCSN = samCSN == null ? null : samCSN.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.OAappNo
     *
     * @return the value of stoproduct.OAappNo
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getOAappNo() {
        return OAappNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.OAappNo
     *
     * @param OAappNo the value for stoproduct.OAappNo
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setOAappNo(String OAappNo) {
        this.OAappNo = OAappNo == null ? null : OAappNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.ProdId
     *
     * @return the value of stoproduct.ProdId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.ProdId
     *
     * @param prodId the value for stoproduct.ProdId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.PhiTypeId
     *
     * @return the value of stoproduct.PhiTypeId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getPhiTypeId() {
        return phiTypeId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.PhiTypeId
     *
     * @param phiTypeId the value for stoproduct.PhiTypeId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setPhiTypeId(String phiTypeId) {
        this.phiTypeId = phiTypeId == null ? null : phiTypeId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.AppTypeId
     *
     * @return the value of stoproduct.AppTypeId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getAppTypeId() {
        return appTypeId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.AppTypeId
     *
     * @param appTypeId the value for stoproduct.AppTypeId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setAppTypeId(String appTypeId) {
        this.appTypeId = appTypeId == null ? null : appTypeId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.KeyType
     *
     * @return the value of stoproduct.KeyType
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Short getKeyType() {
        return keyType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.KeyType
     *
     * @param keyType the value for stoproduct.KeyType
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setKeyType(Short keyType) {
        this.keyType = keyType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.BinFileVer
     *
     * @return the value of stoproduct.BinFileVer
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getBinFileVer() {
        return binFileVer;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.BinFileVer
     *
     * @param binFileVer the value for stoproduct.BinFileVer
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setBinFileVer(String binFileVer) {
        this.binFileVer = binFileVer == null ? null : binFileVer.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.ManufacId
     *
     * @return the value of stoproduct.ManufacId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getManufacId() {
        return manufacId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.ManufacId
     *
     * @param manufacId the value for stoproduct.ManufacId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setManufacId(String manufacId) {
        this.manufacId = manufacId == null ? null : manufacId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.BatchId
     *
     * @return the value of stoproduct.BatchId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.BatchId
     *
     * @param batchId the value for stoproduct.BatchId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.WkState
     *
     * @return the value of stoproduct.WkState
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Short getWkState() {
        return wkState;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.WkState
     *
     * @param wkState the value for stoproduct.WkState
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setWkState(Short wkState) {
        this.wkState = wkState;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.WkStateChgDate
     *
     * @return the value of stoproduct.WkStateChgDate
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getWkStateChgDate() {
        return wkStateChgDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.WkStateChgDate
     *
     * @param wkStateChgDate the value for stoproduct.WkStateChgDate
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setWkStateChgDate(String wkStateChgDate) {
        this.wkStateChgDate = wkStateChgDate == null ? null : wkStateChgDate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.IOState
     *
     * @return the value of stoproduct.IOState
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Short getIOState() {
        return IOState;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.IOState
     *
     * @param IOState the value for stoproduct.IOState
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setIOState(Short IOState) {
        this.IOState = IOState;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.IOStateChgDate
     *
     * @return the value of stoproduct.IOStateChgDate
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getIOStateChgDate() {
        return IOStateChgDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.IOStateChgDate
     *
     * @param IOStateChgDate the value for stoproduct.IOStateChgDate
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setIOStateChgDate(String IOStateChgDate) {
        this.IOStateChgDate = IOStateChgDate == null ? null : IOStateChgDate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.ReuseTime
     *
     * @return the value of stoproduct.ReuseTime
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Short getReuseTime() {
        return reuseTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.ReuseTime
     *
     * @param reuseTime the value for stoproduct.ReuseTime
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setReuseTime(Short reuseTime) {
        this.reuseTime = reuseTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.UnitId
     *
     * @return the value of stoproduct.UnitId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Integer getUnitId() {
        return unitId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.UnitId
     *
     * @param unitId the value for stoproduct.UnitId
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.IssueTime
     *
     * @return the value of stoproduct.IssueTime
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getIssueTime() {
        return issueTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.IssueTime
     *
     * @param issueTime the value for stoproduct.IssueTime
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime == null ? null : issueTime.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.DetectSign
     *
     * @return the value of stoproduct.DetectSign
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Short getDetectSign() {
        return detectSign;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.DetectSign
     *
     * @param detectSign the value for stoproduct.DetectSign
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setDetectSign(Short detectSign) {
        this.detectSign = detectSign;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.DetectTime
     *
     * @return the value of stoproduct.DetectTime
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getDetectTime() {
        return detectTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.DetectTime
     *
     * @param detectTime the value for stoproduct.DetectTime
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setDetectTime(String detectTime) {
        this.detectTime = detectTime == null ? null : detectTime.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.PaymentType
     *
     * @return the value of stoproduct.PaymentType
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Short getPaymentType() {
        return paymentType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.PaymentType
     *
     * @param paymentType the value for stoproduct.PaymentType
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setPaymentType(Short paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.ConsumeType
     *
     * @return the value of stoproduct.ConsumeType
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Short getConsumeType() {
        return consumeType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.ConsumeType
     *
     * @param consumeType the value for stoproduct.ConsumeType
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setConsumeType(Short consumeType) {
        this.consumeType = consumeType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.W2Sign
     *
     * @return the value of stoproduct.W2Sign
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Short getW2Sign() {
        return w2Sign;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.W2Sign
     *
     * @param w2Sign the value for stoproduct.W2Sign
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setW2Sign(Short w2Sign) {
        this.w2Sign = w2Sign;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.AuthSign
     *
     * @return the value of stoproduct.AuthSign
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Short getAuthSign() {
        return authSign;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.AuthSign
     *
     * @param authSign the value for stoproduct.AuthSign
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setAuthSign(Short authSign) {
        this.authSign = authSign;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.UnitPrice
     *
     * @return the value of stoproduct.UnitPrice
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public Long getUnitPrice() {
        return unitPrice;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.UnitPrice
     *
     * @param unitPrice the value for stoproduct.UnitPrice
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.PurchuseDate
     *
     * @return the value of stoproduct.PurchuseDate
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getPurchuseDate() {
        return purchuseDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.PurchuseDate
     *
     * @param purchuseDate the value for stoproduct.PurchuseDate
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setPurchuseDate(String purchuseDate) {
        this.purchuseDate = purchuseDate == null ? null : purchuseDate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoproduct.Remarks
     *
     * @return the value of stoproduct.Remarks
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoproduct.Remarks
     *
     * @param remarks the value for stoproduct.Remarks
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
    private Short cardPhyStat;
	public Short getCardPhyStat() {
		return cardPhyStat;
	}

	public void setCardPhyStat(Short cardPhyStat) {
		this.cardPhyStat = cardPhyStat;
	}

}
