package com.blue.projectfiles;

import com.blue.projectfiles.ProjectFiles;
import com.blue.projectfiles.ProjectFilesExample;
import java.util.List;

public interface ProjectFilesDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    int countByExample(ProjectFilesExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    int deleteByExample(ProjectFilesExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    void insert(ProjectFiles record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    void insertSelective(ProjectFiles record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    List selectByExampleWithBLOBs(ProjectFilesExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    List selectByExampleWithoutBLOBs(ProjectFilesExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    ProjectFiles selectByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    int updateByExampleSelective(ProjectFiles record, ProjectFilesExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    int updateByExampleWithBLOBs(ProjectFiles record, ProjectFilesExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    int updateByExampleWithoutBLOBs(ProjectFiles record, ProjectFilesExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    int updateByPrimaryKeySelective(ProjectFiles record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    int updateByPrimaryKeyWithBLOBs(ProjectFiles record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_files
     *
     * @ibatorgenerated Wed Mar 27 15:38:43 CST 2013
     */
    int updateByPrimaryKeyWithoutBLOBs(ProjectFiles record);
}