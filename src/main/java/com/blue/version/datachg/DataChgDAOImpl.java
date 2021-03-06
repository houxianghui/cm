package com.blue.version.datachg;

import com.blue.version.datachg.DataChg;
import com.blue.version.datachg.DataChgExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DataChgDAOImpl extends SqlMapClientDaoSupport implements DataChgDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public DataChgDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int countByExample(DataChgExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("data_chg.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int deleteByExample(DataChgExample example) {
        int rows = getSqlMapClientTemplate().delete("data_chg.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int deleteByPrimaryKey(Long id) {
        DataChg key = new DataChg();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("data_chg.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void insert(DataChg record) {
        getSqlMapClientTemplate().insert("data_chg.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void insertSelective(DataChg record) {
        getSqlMapClientTemplate().insert("data_chg.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public List selectByExample(DataChgExample example) {
        List list = getSqlMapClientTemplate().queryForList("data_chg.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public DataChg selectByPrimaryKey(Long id) {
        DataChg key = new DataChg();
        key.setId(id);
        DataChg record = (DataChg) getSqlMapClientTemplate().queryForObject("data_chg.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int updateByExampleSelective(DataChg record, DataChgExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("data_chg.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int updateByExample(DataChg record, DataChgExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("data_chg.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int updateByPrimaryKeySelective(DataChg record) {
        int rows = getSqlMapClientTemplate().update("data_chg.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int updateByPrimaryKey(DataChg record) {
        int rows = getSqlMapClientTemplate().update("data_chg.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    private static class UpdateByExampleParms extends DataChgExample {
        private Object record;

        public UpdateByExampleParms(Object record, DataChgExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}