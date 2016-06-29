package com.blue.projectchgrecord;

import com.blue.projectchgrecord.ProjectChgRecord;
import com.blue.projectchgrecord.ProjectChgRecordExample;
import java.util.List;

public interface ProjectChgRecordDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    int countByExample(ProjectChgRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    int deleteByExample(ProjectChgRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    void insert(ProjectChgRecord record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    void insertSelective(ProjectChgRecord record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    List selectByExample(ProjectChgRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    ProjectChgRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    int updateByExampleSelective(ProjectChgRecord record, ProjectChgRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    int updateByExample(ProjectChgRecord record, ProjectChgRecordExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    int updateByPrimaryKeySelective(ProjectChgRecord record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    int updateByPrimaryKey(ProjectChgRecord record);
}