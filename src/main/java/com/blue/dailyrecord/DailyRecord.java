package com.blue.dailyrecord;

import java.math.BigDecimal;

public class DailyRecord {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.RECORD_ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private Long recordId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.PROJECT_ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String projectId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.STEP
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String step;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.WORK_DATE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String workDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.TASK_COST
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private BigDecimal taskCost;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.USER_ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String userId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.INPUT_DATE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String inputDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.WORK_MEMO
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String workMemo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.WORK_ISSUE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String workIssue;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.CHECKED
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String checked;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.CHECK_USER
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String checkUser;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.CHECK_DATE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String checkDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.REFUSE_REASON
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private String refuseReason;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column daily_record.INPUT_COST
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    private BigDecimal inputCost;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.RECORD_ID
     *
     * @return the value of daily_record.RECORD_ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.RECORD_ID
     *
     * @param recordId the value for daily_record.RECORD_ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.ID
     *
     * @return the value of daily_record.ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.ID
     *
     * @param id the value for daily_record.ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.PROJECT_ID
     *
     * @return the value of daily_record.PROJECT_ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.PROJECT_ID
     *
     * @param projectId the value for daily_record.PROJECT_ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.STEP
     *
     * @return the value of daily_record.STEP
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getStep() {
        return step;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.STEP
     *
     * @param step the value for daily_record.STEP
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setStep(String step) {
        this.step = step == null ? null : step.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.WORK_DATE
     *
     * @return the value of daily_record.WORK_DATE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getWorkDate() {
        return workDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.WORK_DATE
     *
     * @param workDate the value for daily_record.WORK_DATE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setWorkDate(String workDate) {
        this.workDate = workDate == null ? null : workDate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.TASK_COST
     *
     * @return the value of daily_record.TASK_COST
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public BigDecimal getTaskCost() {
        return taskCost;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.TASK_COST
     *
     * @param taskCost the value for daily_record.TASK_COST
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setTaskCost(BigDecimal taskCost) {
        this.taskCost = taskCost;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.USER_ID
     *
     * @return the value of daily_record.USER_ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.USER_ID
     *
     * @param userId the value for daily_record.USER_ID
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.INPUT_DATE
     *
     * @return the value of daily_record.INPUT_DATE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getInputDate() {
        return inputDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.INPUT_DATE
     *
     * @param inputDate the value for daily_record.INPUT_DATE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setInputDate(String inputDate) {
        this.inputDate = inputDate == null ? null : inputDate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.WORK_MEMO
     *
     * @return the value of daily_record.WORK_MEMO
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getWorkMemo() {
        return workMemo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.WORK_MEMO
     *
     * @param workMemo the value for daily_record.WORK_MEMO
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setWorkMemo(String workMemo) {
        this.workMemo = workMemo == null ? null : workMemo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.WORK_ISSUE
     *
     * @return the value of daily_record.WORK_ISSUE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getWorkIssue() {
        return workIssue;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.WORK_ISSUE
     *
     * @param workIssue the value for daily_record.WORK_ISSUE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setWorkIssue(String workIssue) {
        this.workIssue = workIssue == null ? null : workIssue.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.CHECKED
     *
     * @return the value of daily_record.CHECKED
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getChecked() {
        return checked;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.CHECKED
     *
     * @param checked the value for daily_record.CHECKED
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setChecked(String checked) {
        this.checked = checked == null ? null : checked.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.CHECK_USER
     *
     * @return the value of daily_record.CHECK_USER
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getCheckUser() {
        return checkUser;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.CHECK_USER
     *
     * @param checkUser the value for daily_record.CHECK_USER
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser == null ? null : checkUser.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.CHECK_DATE
     *
     * @return the value of daily_record.CHECK_DATE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getCheckDate() {
        return checkDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.CHECK_DATE
     *
     * @param checkDate the value for daily_record.CHECK_DATE
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate == null ? null : checkDate.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.REFUSE_REASON
     *
     * @return the value of daily_record.REFUSE_REASON
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public String getRefuseReason() {
        return refuseReason;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.REFUSE_REASON
     *
     * @param refuseReason the value for daily_record.REFUSE_REASON
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column daily_record.INPUT_COST
     *
     * @return the value of daily_record.INPUT_COST
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public BigDecimal getInputCost() {
        return inputCost;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column daily_record.INPUT_COST
     *
     * @param inputCost the value for daily_record.INPUT_COST
     *
     * @ibatorgenerated Mon Jan 06 13:59:27 CST 2014
     */
    public void setInputCost(BigDecimal inputCost) {
        this.inputCost = inputCost;
    }
}