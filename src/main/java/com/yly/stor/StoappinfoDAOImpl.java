package com.yly.stor;

import com.yly.stor.Stoappinfo;
import com.yly.stor.StoappinfoExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class StoappinfoDAOImpl extends SqlMapClientDaoSupport implements StoappinfoDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public StoappinfoDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public int countByExample(StoappinfoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("stoappinfo.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public int deleteByExample(StoappinfoExample example) {
        int rows = getSqlMapClientTemplate().delete("stoappinfo.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public int deleteByPrimaryKey(String formNo) {
        Stoappinfo key = new Stoappinfo();
        key.setFormNo(formNo);
        int rows = getSqlMapClientTemplate().delete("stoappinfo.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void insert(Stoappinfo record) {
        getSqlMapClientTemplate().insert("stoappinfo.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public void insertSelective(Stoappinfo record) {
        getSqlMapClientTemplate().insert("stoappinfo.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public List selectByExample(StoappinfoExample example) {
        List list = getSqlMapClientTemplate().queryForList("stoappinfo.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public Stoappinfo selectByPrimaryKey(String formNo) {
        Stoappinfo key = new Stoappinfo();
        key.setFormNo(formNo);
        Stoappinfo record = (Stoappinfo) getSqlMapClientTemplate().queryForObject("stoappinfo.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public int updateByExampleSelective(Stoappinfo record, StoappinfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("stoappinfo.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public int updateByExample(Stoappinfo record, StoappinfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("stoappinfo.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public int updateByPrimaryKeySelective(Stoappinfo record) {
        int rows = getSqlMapClientTemplate().update("stoappinfo.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    public int updateByPrimaryKey(Stoappinfo record) {
        int rows = getSqlMapClientTemplate().update("stoappinfo.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table stoappinfo
     *
     * @ibatorgenerated Tue Mar 15 19:56:56 CST 2016
     */
    private static class UpdateByExampleParms extends StoappinfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, StoappinfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    public List getReport(StoappinfoExample example) {
        List list = getSqlMapClientTemplate().queryForList("stoappinfo.getReport", example);
        return list;
    }
    public List getPosChargeBackReport(StoappinfoExample example) {
        List list = getSqlMapClientTemplate().queryForList("stoappinfo.getPosChargeBackReport", example);
        return list;
    }
    public List getStockBalReport(StoappinfoExample example){
        List list = getSqlMapClientTemplate().queryForList("stoappinfo.getStockBalReport", example);
        return list;
    }

}