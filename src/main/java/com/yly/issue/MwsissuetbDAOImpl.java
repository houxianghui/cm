package com.yly.issue;

import com.yly.issue.Mwsissuetb;
import com.yly.issue.MwsissuetbExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MwsissuetbDAOImpl extends SqlMapClientDaoSupport implements MwsissuetbDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public MwsissuetbDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public int countByExample(MwsissuetbExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("mwsissuetb.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public int deleteByExample(MwsissuetbExample example) {
        int rows = getSqlMapClientTemplate().delete("mwsissuetb.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public int deleteByPrimaryKey(String formNo) {
        Mwsissuetb key = new Mwsissuetb();
        key.setFormNo(formNo);
        int rows = getSqlMapClientTemplate().delete("mwsissuetb.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void insert(Mwsissuetb record) {
        getSqlMapClientTemplate().insert("mwsissuetb.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public void insertSelective(Mwsissuetb record) {
        getSqlMapClientTemplate().insert("mwsissuetb.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public List selectByExample(MwsissuetbExample example) {
        List list = getSqlMapClientTemplate().queryForList("mwsissuetb.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public Mwsissuetb selectByPrimaryKey(String formNo) {
        Mwsissuetb key = new Mwsissuetb();
        key.setFormNo(formNo);
        Mwsissuetb record = (Mwsissuetb) getSqlMapClientTemplate().queryForObject("mwsissuetb.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public int updateByExampleSelective(Mwsissuetb record, MwsissuetbExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("mwsissuetb.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public int updateByExample(Mwsissuetb record, MwsissuetbExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("mwsissuetb.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public int updateByPrimaryKeySelective(Mwsissuetb record) {
        int rows = getSqlMapClientTemplate().update("mwsissuetb.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    public int updateByPrimaryKey(Mwsissuetb record) {
        int rows = getSqlMapClientTemplate().update("mwsissuetb.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table mwsissuetb
     *
     * @ibatorgenerated Tue May 10 13:31:14 CST 2016
     */
    private static class UpdateByExampleParms extends MwsissuetbExample {
        private Object record;

        public UpdateByExampleParms(Object record, MwsissuetbExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}