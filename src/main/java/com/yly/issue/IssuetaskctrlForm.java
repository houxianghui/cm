package com.yly.issue;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class IssuetaskctrlForm  extends BaseForm {
	private Integer keyType;
	public Integer getKeyType() {
		return keyType;
	}
	public void setKeyType(Integer keyType) {
		this.keyType = keyType;
	}
	private String OAappNo;
	public String getOAappNo() {
		return OAappNo;
	}
	public void setOAappNo(String oAappNo) {
		OAappNo = oAappNo;
	}
	private String sql;
	private String operationType;
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}

	private Collection unitIdcollection;

	public Collection getUnitIdcollection() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.UNITID);
	}
	public void setUnitIdcollection(Collection unitIdcollection) {
		this.unitIdcollection = unitIdcollection;
	}
    private String phiTypeId;
    public String getPhiTypeId() {
		return phiTypeId;
	}
	public void setPhiTypeId(String phiTypeId) {
		this.phiTypeId = phiTypeId;
	}
	
	public Integer getAppTypeId() {
		return appTypeId;
	}
	public void setAppTypeId(Integer appTypeId) {
		this.appTypeId = appTypeId;
	}

	private Integer appTypeId;
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.TaskCtrlNo
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private String taskCtrlNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.AppNo
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private String appNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.TaskNo
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private String taskNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.UnitId
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private Integer unitId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.SamIdBegin
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private String samIdBegin;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.SamIdEnd
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private String samIdEnd;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.IssueAmt
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private Long issueAmt;
    private Long taskAmt;
    public Long getTaskAmt() {
		return taskAmt;
	}
	public void setTaskAmt(Long taskAmt) {
		this.taskAmt = taskAmt;
	}

	/**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.IssueDoneAmt
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private Long issueDoneAmt;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.ConsumeType
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private Short consumeType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.PaymentType
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private Short paymentType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetaskctrl.UnitPrice
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    private Long unitPrice;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.TaskCtrlNo
     *
     * @return the value of issuetaskctrl.TaskCtrlNo
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public String getTaskCtrlNo() {
        return taskCtrlNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.TaskCtrlNo
     *
     * @param taskCtrlNo the value for issuetaskctrl.TaskCtrlNo
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setTaskCtrlNo(String taskCtrlNo) {
        this.taskCtrlNo = taskCtrlNo == null ? null : taskCtrlNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.AppNo
     *
     * @return the value of issuetaskctrl.AppNo
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public String getAppNo() {
        return appNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.AppNo
     *
     * @param appNo the value for issuetaskctrl.AppNo
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setAppNo(String appNo) {
        this.appNo = appNo == null ? null : appNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.TaskNo
     *
     * @return the value of issuetaskctrl.TaskNo
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public String getTaskNo() {
        return taskNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.TaskNo
     *
     * @param taskNo the value for issuetaskctrl.TaskNo
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo == null ? null : taskNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.UnitId
     *
     * @return the value of issuetaskctrl.UnitId
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public Integer getUnitId() {
        return unitId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.UnitId
     *
     * @param unitId the value for issuetaskctrl.UnitId
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.SamIdBegin
     *
     * @return the value of issuetaskctrl.SamIdBegin
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public String getSamIdBegin() {
        return samIdBegin;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.SamIdBegin
     *
     * @param samIdBegin the value for issuetaskctrl.SamIdBegin
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setSamIdBegin(String samIdBegin) {
        this.samIdBegin = samIdBegin == null ? null : samIdBegin.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.SamIdEnd
     *
     * @return the value of issuetaskctrl.SamIdEnd
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public String getSamIdEnd() {
        return samIdEnd;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.SamIdEnd
     *
     * @param samIdEnd the value for issuetaskctrl.SamIdEnd
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setSamIdEnd(String samIdEnd) {
        this.samIdEnd = samIdEnd == null ? null : samIdEnd.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.IssueAmt
     *
     * @return the value of issuetaskctrl.IssueAmt
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public Long getIssueAmt() {
        return issueAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.IssueAmt
     *
     * @param issueAmt the value for issuetaskctrl.IssueAmt
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setIssueAmt(Long issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.IssueDoneAmt
     *
     * @return the value of issuetaskctrl.IssueDoneAmt
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public Long getIssueDoneAmt() {
        return issueDoneAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.IssueDoneAmt
     *
     * @param issueDoneAmt the value for issuetaskctrl.IssueDoneAmt
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setIssueDoneAmt(Long issueDoneAmt) {
        this.issueDoneAmt = issueDoneAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.ConsumeType
     *
     * @return the value of issuetaskctrl.ConsumeType
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public Short getConsumeType() {
        return consumeType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.ConsumeType
     *
     * @param consumeType the value for issuetaskctrl.ConsumeType
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setConsumeType(Short consumeType) {
        this.consumeType = consumeType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.PaymentType
     *
     * @return the value of issuetaskctrl.PaymentType
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public Short getPaymentType() {
        return paymentType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.PaymentType
     *
     * @param paymentType the value for issuetaskctrl.PaymentType
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setPaymentType(Short paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetaskctrl.UnitPrice
     *
     * @return the value of issuetaskctrl.UnitPrice
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public Long getUnitPrice() {
        return unitPrice;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetaskctrl.UnitPrice
     *
     * @param unitPrice the value for issuetaskctrl.UnitPrice
     *
     * @ibatorgenerated Thu Apr 28 20:44:03 CST 2016
     */
    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }
    private String prodId;
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.TaskState
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private Short taskState;
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.TaskState
     *
     * @return the value of issuetask.TaskState
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public Short getTaskState() {
        return taskState;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.TaskState
     *
     * @param taskState the value for issuetask.TaskState
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setTaskState(Short taskState) {
        this.taskState = taskState;
    }

}