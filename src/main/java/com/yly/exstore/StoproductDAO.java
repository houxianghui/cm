package com.yly.exstore;

import com.yly.exstore.Stoproduct;
import com.yly.exstore.StoproductExample;
import java.util.List;

public interface StoproductDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    int countByExample(StoproductExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    int deleteByExample(StoproductExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    int deleteByPrimaryKey(Stoproduct record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    void insert(Stoproduct record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    void insertSelective(Stoproduct record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    List selectByExample(StoproductExample example);
    String selectMaxCard(String samId);
    

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    Stoproduct selectByPrimaryKey(Stoproduct  sto);
    Stoproduct selectBySamId(String samId);
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    int updateByExampleSelective(Stoproduct record, StoproductExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    int updateByExample(Stoproduct record, StoproductExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    int updateByPrimaryKeySelective(Stoproduct record);
    int updateBySamCSN(Stoproduct record);
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoproduct
     *
     * @ibatorgenerated Fri May 13 17:34:02 CST 2016
     */
    int updateByPrimaryKey(Stoproduct record);
    
    List queryCardListByOa(String appNo);
    List getReport(StoproductExample example);
    List getExchangeReport(StoproductExample example);
    List getMakeUpReport(StoproductExample example);
    List getReStoreReport(StoproductExample example);
    int getIssueNumByOaAppNo(String OAappNo);   
}