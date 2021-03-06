package com.huateng.blue.work;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;

public class WorkListForm extends BaseForm {
	private String day;
	private String reason;
	private String workId_f;
	private Collection statusCollection;
	public Collection getStatusCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.WORK_STATUS);
	}
	public void setStatusCollection(Collection statusCollection) {
		this.statusCollection = statusCollection;
	}
	/**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column work_list.WORK_ID
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    private String workId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column work_list.WORK_NAME
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    private String workName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column work_list.CONTENT
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    private String content;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column work_list.WORK_STATUS
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    private String workStatus;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column work_list.START_DATE
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    private String startDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column work_list.END_DATE
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    private String endDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column work_list.INPUT_USER
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    private String inputUser;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column work_list.INPUT_DATE
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    private String inputDate;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column work_list.WORK_ID
     *
     * @return the value of work_list.WORK_ID
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public String getWorkId() {
        return workId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column work_list.WORK_ID
     *
     * @param workId the value for work_list.WORK_ID
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column work_list.WORK_NAME
     *
     * @return the value of work_list.WORK_NAME
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public String getWorkName() {
        return workName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column work_list.WORK_NAME
     *
     * @param workName the value for work_list.WORK_NAME
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public void setWorkName(String workName) {
        this.workName = workName == null ? null : workName.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column work_list.CONTENT
     *
     * @return the value of work_list.CONTENT
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column work_list.CONTENT
     *
     * @param content the value for work_list.CONTENT
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column work_list.WORK_STATUS
     *
     * @return the value of work_list.WORK_STATUS
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public String getWorkStatus() {
        return workStatus;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column work_list.WORK_STATUS
     *
     * @param workStatus the value for work_list.WORK_STATUS
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus == null ? null : workStatus.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column work_list.START_DATE
     *
     * @return the value of work_list.START_DATE
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column work_list.START_DATE
     *
     * @param startDate the value for work_list.START_DATE
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column work_list.END_DATE
     *
     * @return the value of work_list.END_DATE
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column work_list.END_DATE
     *
     * @param endDate the value for work_list.END_DATE
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column work_list.INPUT_USER
     *
     * @return the value of work_list.INPUT_USER
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public String getInputUser() {
        return inputUser;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column work_list.INPUT_USER
     *
     * @param inputUser the value for work_list.INPUT_USER
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column work_list.INPUT_DATE
     *
     * @return the value of work_list.INPUT_DATE
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public String getInputDate() {
        return inputDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column work_list.INPUT_DATE
     *
     * @param inputDate the value for work_list.INPUT_DATE
     *
     * @ibatorgenerated Mon Jul 25 15:07:09 CST 2011
     */
    public void setInputDate(String inputDate) {
        this.inputDate = inputDate == null ? null : inputDate.trim();
    }

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getWorkId_f() {
		return workId_f;
	}

	public void setWorkId_f(String workId_f) {
		this.workId_f = workId_f;
	}
}
