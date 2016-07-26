package com.yly.manu;

import com.yly.manu.Bimanufacinfotb;
import com.yly.manu.BimanufacinfotbExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BimanufacinfotbDAOImpl extends SqlMapClientDaoSupport implements BimanufacinfotbDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public BimanufacinfotbDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public int countByExample(BimanufacinfotbExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("bimanufacinfotb.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public int deleteByExample(BimanufacinfotbExample example) {
        int rows = getSqlMapClientTemplate().delete("bimanufacinfotb.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public int deleteByPrimaryKey(Short manufacId) {
        Bimanufacinfotb key = new Bimanufacinfotb();
        key.setManufacId(manufacId);
        int rows = getSqlMapClientTemplate().delete("bimanufacinfotb.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public void insert(Bimanufacinfotb record) {
        getSqlMapClientTemplate().insert("bimanufacinfotb.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public void insertSelective(Bimanufacinfotb record) {
        getSqlMapClientTemplate().insert("bimanufacinfotb.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public List selectByExample(BimanufacinfotbExample example) {
        List list = getSqlMapClientTemplate().queryForList("bimanufacinfotb.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public Bimanufacinfotb selectByPrimaryKey(Short manufacId) {
        Bimanufacinfotb key = new Bimanufacinfotb();
        key.setManufacId(manufacId);
        Bimanufacinfotb record = (Bimanufacinfotb) getSqlMapClientTemplate().queryForObject("bimanufacinfotb.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public int updateByExampleSelective(Bimanufacinfotb record, BimanufacinfotbExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("bimanufacinfotb.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public int updateByExample(Bimanufacinfotb record, BimanufacinfotbExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("bimanufacinfotb.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public int updateByPrimaryKeySelective(Bimanufacinfotb record) {
        int rows = getSqlMapClientTemplate().update("bimanufacinfotb.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public int updateByPrimaryKey(Bimanufacinfotb record) {
        int rows = getSqlMapClientTemplate().update("bimanufacinfotb.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    private static class UpdateByExampleParms extends BimanufacinfotbExample {
        private Object record;

        public UpdateByExampleParms(Object record, BimanufacinfotbExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}