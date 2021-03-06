package com.blue.project.riskrecord;

import com.blue.project.riskrecord.RiskRecord;
import com.blue.project.riskrecord.RiskRecordExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RiskRecordDAOImpl extends SqlMapClientDaoSupport implements RiskRecordDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public RiskRecordDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public int countByExample(RiskRecordExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("risk_record.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public int deleteByExample(RiskRecordExample example) {
        int rows = getSqlMapClientTemplate().delete("risk_record.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public int deleteByPrimaryKey(Long id) {
        RiskRecord key = new RiskRecord();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("risk_record.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public void insert(RiskRecord record) {
        getSqlMapClientTemplate().insert("risk_record.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public void insertSelective(RiskRecord record) {
        getSqlMapClientTemplate().insert("risk_record.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public List selectByExample(RiskRecordExample example) {
        List list = getSqlMapClientTemplate().queryForList("risk_record.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public RiskRecord selectByPrimaryKey(Long id) {
        RiskRecord key = new RiskRecord();
        key.setId(id);
        RiskRecord record = (RiskRecord) getSqlMapClientTemplate().queryForObject("risk_record.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public int updateByExampleSelective(RiskRecord record, RiskRecordExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("risk_record.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public int updateByExample(RiskRecord record, RiskRecordExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("risk_record.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public int updateByPrimaryKeySelective(RiskRecord record) {
        int rows = getSqlMapClientTemplate().update("risk_record.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public int updateByPrimaryKey(RiskRecord record) {
        int rows = getSqlMapClientTemplate().update("risk_record.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    private static class UpdateByExampleParms extends RiskRecordExample {
        private Object record;

        public UpdateByExampleParms(Object record, RiskRecordExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}