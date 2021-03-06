package com.blue.version.datachg;

import com.blue.version.datachg.DataChg;
import com.blue.version.datachg.DataChgExample;
import java.util.List;

public interface DataChgDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int countByExample(DataChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int deleteByExample(DataChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    void insert(DataChg record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    void insertSelective(DataChg record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    List selectByExample(DataChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    DataChg selectByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByExampleSelective(DataChg record, DataChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByExample(DataChg record, DataChgExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByPrimaryKeySelective(DataChg record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByPrimaryKey(DataChg record);
}