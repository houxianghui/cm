package com.yly.issue;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class IssuetaskForm  extends BaseForm {
	private Collection moduleVerEffcollection;
	
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
	/**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.TaskNo
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private String taskNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.OAappNo
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private String OAappNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.AppNo
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private String appNo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.OperationType
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private Short operationType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.ProdId
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private String prodId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.IssueAmt
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private Long issueAmt;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.KeyType
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private Short keyType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.PhiTypeId
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private String phiTypeId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.AppTypeId
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private Integer appTypeId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.BinFileVer
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private String binFileVer;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.AuthSign
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private Short authSign;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.W2Sign
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private Short w2Sign;


    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column issuetask.Remarks
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    private String remarks;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.TaskNo
     *
     * @return the value of issuetask.TaskNo
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public String getTaskNo() {
        return taskNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.TaskNo
     *
     * @param taskNo the value for issuetask.TaskNo
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo == null ? null : taskNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.OAappNo
     *
     * @return the value of issuetask.OAappNo
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public String getOAappNo() {
        return OAappNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.OAappNo
     *
     * @param OAappNo the value for issuetask.OAappNo
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setOAappNo(String OAappNo) {
        this.OAappNo = OAappNo == null ? null : OAappNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.AppNo
     *
     * @return the value of issuetask.AppNo
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public String getAppNo() {
        return appNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.AppNo
     *
     * @param appNo the value for issuetask.AppNo
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setAppNo(String appNo) {
        this.appNo = appNo == null ? null : appNo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.OperationType
     *
     * @return the value of issuetask.OperationType
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public Short getOperationType() {
        return operationType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.OperationType
     *
     * @param operationType the value for issuetask.OperationType
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setOperationType(Short operationType) {
        this.operationType = operationType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.ProdId
     *
     * @return the value of issuetask.ProdId
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.ProdId
     *
     * @param prodId the value for issuetask.ProdId
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.IssueAmt
     *
     * @return the value of issuetask.IssueAmt
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public Long getIssueAmt() {
        return issueAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.IssueAmt
     *
     * @param issueAmt the value for issuetask.IssueAmt
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setIssueAmt(Long issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.KeyType
     *
     * @return the value of issuetask.KeyType
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public Short getKeyType() {
        return keyType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.KeyType
     *
     * @param keyType the value for issuetask.KeyType
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setKeyType(Short keyType) {
        this.keyType = keyType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.PhiTypeId
     *
     * @return the value of issuetask.PhiTypeId
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public String getPhiTypeId() {
        return phiTypeId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.PhiTypeId
     *
     * @param phiTypeId the value for issuetask.PhiTypeId
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setPhiTypeId(String phiTypeId) {
        this.phiTypeId = phiTypeId == null ? null : phiTypeId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.AppTypeId
     *
     * @return the value of issuetask.AppTypeId
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public Integer getAppTypeId() {
        return appTypeId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.AppTypeId
     *
     * @param appTypeId the value for issuetask.AppTypeId
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setAppTypeId(Integer appTypeId) {
        this.appTypeId = appTypeId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.BinFileVer
     *
     * @return the value of issuetask.BinFileVer
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public String getBinFileVer() {
        return binFileVer;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.BinFileVer
     *
     * @param binFileVer the value for issuetask.BinFileVer
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setBinFileVer(String binFileVer) {
        this.binFileVer = binFileVer == null ? null : binFileVer.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.AuthSign
     *
     * @return the value of issuetask.AuthSign
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public Short getAuthSign() {
        return authSign;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.AuthSign
     *
     * @param authSign the value for issuetask.AuthSign
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setAuthSign(Short authSign) {
        this.authSign = authSign;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.W2Sign
     *
     * @return the value of issuetask.W2Sign
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public Short getW2Sign() {
        return w2Sign;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.W2Sign
     *
     * @param w2Sign the value for issuetask.W2Sign
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setW2Sign(Short w2Sign) {
        this.w2Sign = w2Sign;
    }



    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column issuetask.Remarks
     *
     * @return the value of issuetask.Remarks
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column issuetask.Remarks
     *
     * @param remarks the value for issuetask.Remarks
     *
     * @ibatorgenerated Thu Apr 28 20:52:23 CST 2016
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
    private  Long taskAmt;

	public Long getTaskAmt() {
		return taskAmt;
	}

	public void setTaskAmt(Long taskAmt) {
		this.taskAmt = taskAmt;
	}
    private Integer w2Limit;
    private Integer zeroExauthFlag;
    private Integer isPki;
	private String isHTCard;
	public Integer getW2Limit() {
		return w2Limit;
	}

	public void setW2Limit(Integer w2Limit) {
		this.w2Limit = w2Limit;
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

	public String getIsHTCard() {
		return isHTCard;
	}

	public void setIsHTCard(String isHTCard) {
		this.isHTCard = isHTCard;
	}
}