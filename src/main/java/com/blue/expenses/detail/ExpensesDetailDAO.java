package com.blue.expenses.detail;

import com.blue.expenses.detail.ExpensesDetail;
import com.blue.expenses.detail.ExpensesDetailExample;
import java.util.List;

public interface ExpensesDetailDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    int countByExample(ExpensesDetailExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    int deleteByExample(ExpensesDetailExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    int deleteByPrimaryKey(Long edetailId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    void insert(ExpensesDetail record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    void insertSelective(ExpensesDetail record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    List selectByExample(ExpensesDetailExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    ExpensesDetail selectByPrimaryKey(Long edetailId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    int updateByExampleSelective(ExpensesDetail record, ExpensesDetailExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    int updateByExample(ExpensesDetail record, ExpensesDetailExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    int updateByPrimaryKeySelective(ExpensesDetail record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table expenses_detail
     *
     * @ibatorgenerated Tue Apr 15 15:14:50 CST 2014
     */
    int updateByPrimaryKey(ExpensesDetail record);
}