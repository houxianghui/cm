package com.blue.version.updatestep;

import com.blue.version.updatestep.UpdateStep;
import com.blue.version.updatestep.UpdateStepExample;
import java.util.List;

public interface UpdateStepDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int countByExample(UpdateStepExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int deleteByExample(UpdateStepExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    void insert(UpdateStep record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    void insertSelective(UpdateStep record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    List selectByExample(UpdateStepExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    UpdateStep selectByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByExampleSelective(UpdateStep record, UpdateStepExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByExample(UpdateStep record, UpdateStepExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByPrimaryKeySelective(UpdateStep record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table update_step
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    int updateByPrimaryKey(UpdateStep record);
}