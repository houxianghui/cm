package com.yly.stor;

import com.yly.stor.Stoappinfo;
import com.yly.stor.StoappinfoExample;
import java.util.List;

public interface StoappinfoDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    int countByExample(StoappinfoExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    int deleteByExample(StoappinfoExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    int deleteByPrimaryKey(String formNo);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    void insert(Stoappinfo record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    void insertSelective(Stoappinfo record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    List selectByExample(StoappinfoExample example);
    List getReport(StoappinfoExample example);

    List getPosChargeBackReport(StoappinfoExample example);
    List getStockBalReport(StoappinfoExample example);
 

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    Stoappinfo selectByPrimaryKey(String formNo);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    int updateByExampleSelective(Stoappinfo record, StoappinfoExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    int updateByExample(Stoappinfo record, StoappinfoExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    int updateByPrimaryKeySelective(Stoappinfo record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    int updateByPrimaryKey(Stoappinfo record);
    
}