package com.huateng.blue.work;

import com.huateng.blue.work.DailyIssue;
import com.huateng.blue.work.DailyIssueExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DailyIssueDAOImpl extends SqlMapClientDaoSupport implements DailyIssueDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public DailyIssueDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public int countByExample(DailyIssueExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("daily_issue.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public int deleteByExample(DailyIssueExample example) {
        int rows = getSqlMapClientTemplate().delete("daily_issue.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public int deleteByPrimaryKey(Integer id) {
        DailyIssue key = new DailyIssue();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("daily_issue.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public void insert(DailyIssue record) {
        getSqlMapClientTemplate().insert("daily_issue.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public void insertSelective(DailyIssue record) {
        getSqlMapClientTemplate().insert("daily_issue.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public List selectByExample(DailyIssueExample example) {
        List list = getSqlMapClientTemplate().queryForList("daily_issue.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public DailyIssue selectByPrimaryKey(Integer id) {
        DailyIssue key = new DailyIssue();
        key.setId(id);
        DailyIssue record = (DailyIssue) getSqlMapClientTemplate().queryForObject("daily_issue.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public int updateByExampleSelective(DailyIssue record, DailyIssueExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("daily_issue.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public int updateByExample(DailyIssue record, DailyIssueExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("daily_issue.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public int updateByPrimaryKeySelective(DailyIssue record) {
        int rows = getSqlMapClientTemplate().update("daily_issue.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public int updateByPrimaryKey(DailyIssue record) {
        int rows = getSqlMapClientTemplate().update("daily_issue.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    private static class UpdateByExampleParms extends DailyIssueExample {
        private Object record;

        public UpdateByExampleParms(Object record, DailyIssueExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}