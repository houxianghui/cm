package com.huateng.blue.work;

import com.huateng.blue.work.SignRecord;
import com.huateng.blue.work.SignRecordExample;
import com.huateng.blue.work.SignRecordKey;
import java.util.List;

public interface SignRecordDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    int countByExample(SignRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    int deleteByExample(SignRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    int deleteByPrimaryKey(SignRecordKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    void insert(SignRecord record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    void insertSelective(SignRecord record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    List selectByExample(SignRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    SignRecord selectByPrimaryKey(SignRecordKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    int updateByExampleSelective(SignRecord record, SignRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    int updateByExample(SignRecord record, SignRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    int updateByPrimaryKeySelective(SignRecord record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table sign_record
     *
     * @ibatorgenerated Tue Nov 01 09:58:54 CST 2011
     */
    int updateByPrimaryKey(SignRecord record);
}