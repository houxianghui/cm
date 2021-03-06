package com.blue.projectchgrecord;

import com.blue.projectchgrecord.ChangeDetail;
import com.blue.projectchgrecord.ChangeDetailExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ChangeDetailDAOImpl extends SqlMapClientDaoSupport implements ChangeDetailDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public ChangeDetailDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public int countByExample(ChangeDetailExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("change_detail.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public int deleteByExample(ChangeDetailExample example) {
        int rows = getSqlMapClientTemplate().delete("change_detail.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public int deleteByPrimaryKey(Long detailId) {
        ChangeDetail key = new ChangeDetail();
        key.setDetailId(detailId);
        int rows = getSqlMapClientTemplate().delete("change_detail.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public void insert(ChangeDetail record) {
        getSqlMapClientTemplate().insert("change_detail.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public void insertSelective(ChangeDetail record) {
        getSqlMapClientTemplate().insert("change_detail.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public List selectByExample(ChangeDetailExample example) {
        List list = getSqlMapClientTemplate().queryForList("change_detail.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public ChangeDetail selectByPrimaryKey(Long detailId) {
        ChangeDetail key = new ChangeDetail();
        key.setDetailId(detailId);
        ChangeDetail record = (ChangeDetail) getSqlMapClientTemplate().queryForObject("change_detail.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public int updateByExampleSelective(ChangeDetail record, ChangeDetailExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("change_detail.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public int updateByExample(ChangeDetail record, ChangeDetailExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("change_detail.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public int updateByPrimaryKeySelective(ChangeDetail record) {
        int rows = getSqlMapClientTemplate().update("change_detail.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    public int updateByPrimaryKey(ChangeDetail record) {
        int rows = getSqlMapClientTemplate().update("change_detail.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table change_detail
     *
     * @ibatorgenerated Tue Jan 08 15:32:41 CST 2013
     */
    private static class UpdateByExampleParms extends ChangeDetailExample {
        private Object record;

        public UpdateByExampleParms(Object record, ChangeDetailExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}