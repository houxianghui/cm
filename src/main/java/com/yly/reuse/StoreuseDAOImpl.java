package com.yly.reuse;

import com.yly.reuse.Storeuse;
import com.yly.reuse.StoreuseExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class StoreuseDAOImpl extends SqlMapClientDaoSupport implements StoreuseDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public StoreuseDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public int countByExample(StoreuseExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("storeuse.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public int deleteByExample(StoreuseExample example) {
        int rows = getSqlMapClientTemplate().delete("storeuse.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public int deleteByPrimaryKey(String samId) {
        Storeuse key = new Storeuse();
        key.setSamId(samId);
        int rows = getSqlMapClientTemplate().delete("storeuse.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public void insert(Storeuse record) {
        getSqlMapClientTemplate().insert("storeuse.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public void insertSelective(Storeuse record) {
        getSqlMapClientTemplate().insert("storeuse.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public List selectByExample(StoreuseExample example) {
        List list = getSqlMapClientTemplate().queryForList("storeuse.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public Storeuse selectByPrimaryKey(String samId) {
        Storeuse key = new Storeuse();
        key.setSamId(samId);
        Storeuse record = (Storeuse) getSqlMapClientTemplate().queryForObject("storeuse.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public int updateByExampleSelective(Storeuse record, StoreuseExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("storeuse.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public int updateByExample(Storeuse record, StoreuseExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("storeuse.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public int updateByPrimaryKeySelective(Storeuse record) {
        int rows = getSqlMapClientTemplate().update("storeuse.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    public int updateByPrimaryKey(Storeuse record) {
        int rows = getSqlMapClientTemplate().update("storeuse.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table storeuse
     *
     * @ibatorgenerated Mon May 30 14:59:08 CST 2016
     */
    private static class UpdateByExampleParms extends StoreuseExample {
        private Object record;

        public UpdateByExampleParms(Object record, StoreuseExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}