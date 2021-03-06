package com.blue.projectchgrecord;

import com.blue.projectchgrecord.ProjectChgRecord;
import com.blue.projectchgrecord.ProjectChgRecordExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProjectChgRecordDAOImpl extends SqlMapClientDaoSupport implements ProjectChgRecordDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public ProjectChgRecordDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public int countByExample(ProjectChgRecordExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("project_chg_record.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public int deleteByExample(ProjectChgRecordExample example) {
        int rows = getSqlMapClientTemplate().delete("project_chg_record.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public int deleteByPrimaryKey(Long id) {
        ProjectChgRecord key = new ProjectChgRecord();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("project_chg_record.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public void insert(ProjectChgRecord record) {
        getSqlMapClientTemplate().insert("project_chg_record.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public void insertSelective(ProjectChgRecord record) {
        getSqlMapClientTemplate().insert("project_chg_record.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public List selectByExample(ProjectChgRecordExample example) {
        List list = getSqlMapClientTemplate().queryForList("project_chg_record.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public ProjectChgRecord selectByPrimaryKey(Long id) {
        ProjectChgRecord key = new ProjectChgRecord();
        key.setId(id);
        ProjectChgRecord record = (ProjectChgRecord) getSqlMapClientTemplate().queryForObject("project_chg_record.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public int updateByExampleSelective(ProjectChgRecord record, ProjectChgRecordExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("project_chg_record.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public int updateByExample(ProjectChgRecord record, ProjectChgRecordExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("project_chg_record.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public int updateByPrimaryKeySelective(ProjectChgRecord record) {
        int rows = getSqlMapClientTemplate().update("project_chg_record.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public int updateByPrimaryKey(ProjectChgRecord record) {
        int rows = getSqlMapClientTemplate().update("project_chg_record.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    private static class UpdateByExampleParms extends ProjectChgRecordExample {
        private Object record;

        public UpdateByExampleParms(Object record, ProjectChgRecordExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}