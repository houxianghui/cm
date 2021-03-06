package com.yly.issue;

import com.yly.issue.Issueapp;
import com.yly.issue.IssueappExample;
import java.util.List;

public interface IssueappDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    int countByExample(IssueappExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    int deleteByExample(IssueappExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    int deleteByPrimaryKey(String appNo);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    void insert(Issueapp record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    void insertSelective(Issueapp record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    List selectByExample(IssueappExample example);
    List queryIssuExample(IssueappExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    Issueapp selectByPrimaryKey(String appNo);
    String selectByOA(String oaAppNo);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    int updateByExampleSelective(Issueapp record, IssueappExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    int updateByExample(Issueapp record, IssueappExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    int updateByPrimaryKeySelective(Issueapp record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table issueapp
     *
     * @ibatorgenerated Tue Mar 29 14:27:18 CST 2016
     */
    int updateByPrimaryKey(Issueapp record);
    List selectTaskNoByExample(IssueappForm record);
    List getExchangeRawReport(IssueappExample example);
    List getMakeUpRawReport(IssueappExample example);
    Issueapp queryPosExStoreInfo(IssueappExample example);
    Issueapp queryPosBackInfo(IssueappExample example);

}