package com.yly.discard;

import com.yly.discard.Disproduct;
import com.yly.discard.DisproductExample;
import java.util.List;

public interface DisproductDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    int countByExample(DisproductExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    int deleteByExample(DisproductExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    int deleteByPrimaryKey(String samId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    void insert(Disproduct record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    void insertSelective(Disproduct record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    List selectByExample(DisproductExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    Disproduct selectByPrimaryKey(String samId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    int updateByExampleSelective(Disproduct record, DisproductExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    int updateByExample(Disproduct record, DisproductExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    int updateByPrimaryKeySelective(Disproduct record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table disproduct
     *
     * @ibatorgenerated Thu May 26 17:56:20 CST 2016
     */
    int updateByPrimaryKey(Disproduct record);
}