package com.yly.stor;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.base.PageObject;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class StoAppInfoForm  extends BaseForm {

	private String appTypeId;
	public String getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(String appTypeId) {
		this.appTypeId = appTypeId;
	}
	private String appNo;
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	private String taskCtrlNo;
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.FormNo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String formNo;

    public String getTaskCtrlNo() {
		return taskCtrlNo;
	}

	public void setTaskCtrlNo(String taskCtrlNo) {
		this.taskCtrlNo = taskCtrlNo;
	}
	/**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.ProjContNum
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String projContNum;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.PurchaseAmt
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private Long purchaseAmt;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.PurchaseType
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String purchaseType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.CommRate
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String commRate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.UnitPrice
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private Long unitPrice;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.ManufacId
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String manufacId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.ProdId
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String prodId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.OperationType
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private Long operationType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.OrigFormNo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String origFormNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.PurchasePerson
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String purchasePerson;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.OperId
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String operId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.CurrDate
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String currDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.Remarks
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String remarks;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column stoappinfo.Rsvd
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private String rsvd;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.FormNo
     *
     * @return the value of stoappinfo.FormNo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getFormNo() {
        return formNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.FormNo
     *
     * @param formNo the value for stoappinfo.FormNo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setFormNo(String formNo) {
        this.formNo = formNo == null ? null : formNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.ProjContNum
     *
     * @return the value of stoappinfo.ProjContNum
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getProjContNum() {
        return projContNum;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.ProjContNum
     *
     * @param projContNum the value for stoappinfo.ProjContNum
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setProjContNum(String projContNum) {
        this.projContNum = projContNum == null ? null : projContNum.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.PurchaseAmt
     *
     * @return the value of stoappinfo.PurchaseAmt
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public Long getPurchaseAmt() {
        return purchaseAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.PurchaseAmt
     *
     * @param purchaseAmt the value for stoappinfo.PurchaseAmt
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setPurchaseAmt(Long purchaseAmt) {
        this.purchaseAmt = purchaseAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.PurchaseType
     *
     * @return the value of stoappinfo.PurchaseType
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getPurchaseType() {
        return purchaseType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.PurchaseType
     *
     * @param purchaseType the value for stoappinfo.PurchaseType
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType == null ? null : purchaseType.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.CommRate
     *
     * @return the value of stoappinfo.CommRate
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getCommRate() {
        return commRate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.CommRate
     *
     * @param commRate the value for stoappinfo.CommRate
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setCommRate(String commRate) {
        this.commRate = commRate == null ? null : commRate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.UnitPrice
     *
     * @return the value of stoappinfo.UnitPrice
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public Long getUnitPrice() {
        return unitPrice;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.UnitPrice
     *
     * @param unitPrice the value for stoappinfo.UnitPrice
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.ManufacId
     *
     * @return the value of stoappinfo.ManufacId
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getManufacId() {
        return manufacId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.ManufacId
     *
     * @param manufacId the value for stoappinfo.ManufacId
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setManufacId(String manufacId) {
        this.manufacId = manufacId == null ? null : manufacId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.ProdId
     *
     * @return the value of stoappinfo.ProdId
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.ProdId
     *
     * @param prodId the value for stoappinfo.ProdId
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.OperationType
     *
     * @return the value of stoappinfo.OperationType
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public Long getOperationType() {
        return operationType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.OperationType
     *
     * @param operationType the value for stoappinfo.OperationType
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setOperationType(Long operationType) {
        this.operationType = operationType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.OrigFormNo
     *
     * @return the value of stoappinfo.OrigFormNo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getOrigFormNo() {
        return origFormNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.OrigFormNo
     *
     * @param origFormNo the value for stoappinfo.OrigFormNo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setOrigFormNo(String origFormNo) {
        this.origFormNo = origFormNo == null ? null : origFormNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.PurchasePerson
     *
     * @return the value of stoappinfo.PurchasePerson
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getPurchasePerson() {
        return purchasePerson;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.PurchasePerson
     *
     * @param purchasePerson the value for stoappinfo.PurchasePerson
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setPurchasePerson(String purchasePerson) {
        this.purchasePerson = purchasePerson == null ? null : purchasePerson.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.OperId
     *
     * @return the value of stoappinfo.OperId
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getOperId() {
        return operId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.OperId
     *
     * @param operId the value for stoappinfo.OperId
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.CurrDate
     *
     * @return the value of stoappinfo.CurrDate
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getCurrDate() {
        return currDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.CurrDate
     *
     * @param currDate the value for stoappinfo.CurrDate
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setCurrDate(String currDate) {
        this.currDate = currDate == null ? null : currDate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.Remarks
     *
     * @return the value of stoappinfo.Remarks
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.Remarks
     *
     * @param remarks the value for stoappinfo.Remarks
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column stoappinfo.Rsvd
     *
     * @return the value of stoappinfo.Rsvd
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public String getRsvd() {
        return rsvd;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column stoappinfo.Rsvd
     *
     * @param rsvd the value for stoappinfo.Rsvd
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void setRsvd(String rsvd) {
        this.rsvd = rsvd == null ? null : rsvd.trim();
    }
    private String beginDate_f;
    private String endDate_f;
	private Collection manufacIdCollection;
	private Collection prodIdCollection;
	private Collection operationTypeCollection;
	private Collection phiTypeIdCollection;
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

	public Collection getManufacIdCollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.MAUN_ID);
	}

	public void setManufacIdCollection(Collection manufacIdCollection) {
		this.manufacIdCollection = manufacIdCollection;
	}

	public Collection getProdIdCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.PROD_ID);
	}

	public void setProdIdCollection(Collection prodIdCollection) {
		this.prodIdCollection = prodIdCollection;
	}

	public Collection getOperationTypeCollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.INOPERATIONTYPE);
	}

	public void setOperationTypeCollection(Collection operationTypeCollection) {
		this.operationTypeCollection = operationTypeCollection;
	}
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
    private Long currPeriodAmt;
    public Long getCurrPeriodAmt() {
		return currPeriodAmt;
	}

	public void setCurrPeriodAmt(Long currPeriodAmt) {
		this.currPeriodAmt = currPeriodAmt;
	}
	private String projName;
	private String phiTypeId;
	private String exFormNo ;
	private String pressCardScale;

	public Collection getPhiTypeIdCollection() {
		return  SingleDicMap.getOptionCollection(SingleDic.COMM_RATE);
	}

	public void setPhiTypeIdCollection(Collection phiTypeIdCollection) {
		this.phiTypeIdCollection = phiTypeIdCollection;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getPhiTypeId() {
		return phiTypeId;
	}

	public void setPhiTypeId(String phiTypeId) {
		this.phiTypeId = phiTypeId;
	}

	public String getExFormNo() {
		return exFormNo;
	}

	public void setExFormNo(String exFormNo) {
		this.exFormNo = exFormNo;
	}

	public String getPressCardScale() {
		return pressCardScale;
	}

	public void setPressCardScale(String pressCardScale) {
		this.pressCardScale = pressCardScale;
	}
	
    private String isHTCard;
    public String getIsHTCard() {
		return isHTCard;
	}

	public void setIsHTCard(String isHTCard) {
		this.isHTCard = isHTCard;
	}

	public String getIsPki() {
		return isPki;
	}

	public void setIsPki(String isPki) {
		this.isPki = isPki;
	}
	private String isPki;
	private String moduleVersion;
	private String moduleBatchId;
	public String getModuleBatchId() {
		return moduleBatchId;
	}

	public void setModuleBatchId(String moduleBatchId) {
		this.moduleBatchId = moduleBatchId;
	}

	public String getModuleVersion() {
		return moduleVersion;
	}

	public void setModuleVersion(String moduleVersion) {
		this.moduleVersion = moduleVersion;
	}


}