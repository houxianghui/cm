package com.blue.version.batchchg;

import com.blue.version.batchchg.BatchChg;
import com.blue.version.batchchg.BatchChgExample;
import java.util.List;

public interface BatchChgDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int countByExample(BatchChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int deleteByExample(BatchChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    void insert(BatchChg record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    void insertSelective(BatchChg record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    List selectByExample(BatchChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    BatchChg selectByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByExampleSelective(BatchChg record, BatchChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByExample(BatchChg record, BatchChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByPrimaryKeySelective(BatchChg record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByPrimaryKey(BatchChg record);
}