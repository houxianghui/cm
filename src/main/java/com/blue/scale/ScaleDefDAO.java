package com.blue.scale;

import com.blue.scale.ScaleDef;
import com.blue.scale.ScaleDefExample;
import java.util.List;

public interface ScaleDefDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    int countByExample(ScaleDefExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    int deleteByExample(ScaleDefExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    int deleteByPrimaryKey(Integer scaleId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    void insert(ScaleDef record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    void insertSelective(ScaleDef record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    List selectByExampleWithBLOBs(ScaleDefExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    List selectByExampleWithoutBLOBs(ScaleDefExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    ScaleDef selectByPrimaryKey(Integer scaleId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    int updateByExampleSelective(ScaleDef record, ScaleDefExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    int updateByExampleWithBLOBs(ScaleDef record, ScaleDefExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    int updateByExampleWithoutBLOBs(ScaleDef record, ScaleDefExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    int updateByPrimaryKeySelective(ScaleDef record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    int updateByPrimaryKeyWithBLOBs(ScaleDef record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table scale_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    int updateByPrimaryKeyWithoutBLOBs(ScaleDef record);
}