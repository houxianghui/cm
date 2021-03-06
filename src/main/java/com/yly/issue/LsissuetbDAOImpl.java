package com.yly.issue;

import com.yly.issue.Lsissuetb;
import com.yly.issue.LsissuetbExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class LsissuetbDAOImpl extends SqlMapClientDaoSupport implements LsissuetbDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public LsissuetbDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public int countByExample(LsissuetbExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lsissuetb.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public int deleteByExample(LsissuetbExample example) {
        int rows = getSqlMapClientTemplate().delete("lsissuetb.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public int deleteByPrimaryKey(String flowNo) {
        Lsissuetb key = new Lsissuetb();
        key.setFlowNo(flowNo);
        int rows = getSqlMapClientTemplate().delete("lsissuetb.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public void insert(Lsissuetb record) {
        getSqlMapClientTemplate().insert("lsissuetb.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public void insertSelective(Lsissuetb record) {
        getSqlMapClientTemplate().insert("lsissuetb.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public List selectByExample(LsissuetbExample example) {
        List list = getSqlMapClientTemplate().queryForList("lsissuetb.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public Lsissuetb selectByPrimaryKey(String flowNo) {
        Lsissuetb key = new Lsissuetb();
        key.setFlowNo(flowNo);
        Lsissuetb record = (Lsissuetb) getSqlMapClientTemplate().queryForObject("lsissuetb.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public int updateByExampleSelective(Lsissuetb record, LsissuetbExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("lsissuetb.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public int updateByExample(Lsissuetb record, LsissuetbExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("lsissuetb.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public int updateByPrimaryKeySelective(Lsissuetb record) {
        int rows = getSqlMapClientTemplate().update("lsissuetb.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    public int updateByPrimaryKey(Lsissuetb record) {
        int rows = getSqlMapClientTemplate().update("lsissuetb.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table lsissuetb
     *
     * @ibatorgenerated Tue Mar 29 14:31:44 CST 2016
     */
    private static class UpdateByExampleParms extends LsissuetbExample {
        private Object record;

        public UpdateByExampleParms(Object record, LsissuetbExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}