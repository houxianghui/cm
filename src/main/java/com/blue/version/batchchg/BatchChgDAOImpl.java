package com.blue.version.batchchg;

import com.blue.version.batchchg.BatchChg;
import com.blue.version.batchchg.BatchChgExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BatchChgDAOImpl extends SqlMapClientDaoSupport implements BatchChgDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public BatchChgDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int countByExample(BatchChgExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("batch_chg.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int deleteByExample(BatchChgExample example) {
        int rows = getSqlMapClientTemplate().delete("batch_chg.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int deleteByPrimaryKey(Long id) {
        BatchChg key = new BatchChg();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("batch_chg.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void insert(BatchChg record) {
        getSqlMapClientTemplate().insert("batch_chg.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void insertSelective(BatchChg record) {
        getSqlMapClientTemplate().insert("batch_chg.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public List selectByExample(BatchChgExample example) {
        List list = getSqlMapClientTemplate().queryForList("batch_chg.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public BatchChg selectByPrimaryKey(Long id) {
        BatchChg key = new BatchChg();
        key.setId(id);
        BatchChg record = (BatchChg) getSqlMapClientTemplate().queryForObject("batch_chg.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int updateByExampleSelective(BatchChg record, BatchChgExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("batch_chg.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int updateByExample(BatchChg record, BatchChgExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("batch_chg.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int updateByPrimaryKeySelective(BatchChg record) {
        int rows = getSqlMapClientTemplate().update("batch_chg.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public int updateByPrimaryKey(BatchChg record) {
        int rows = getSqlMapClientTemplate().update("batch_chg.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table batch_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    private static class UpdateByExampleParms extends BatchChgExample {
        private Object record;

        public UpdateByExampleParms(Object record, BatchChgExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}