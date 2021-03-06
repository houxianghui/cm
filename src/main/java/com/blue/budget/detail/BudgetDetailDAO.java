package com.blue.budget.detail;

import com.blue.budget.detail.BudgetDetail;
import com.blue.budget.detail.BudgetDetailExample;
import com.eis.exception.MessageException;

import java.util.List;

public interface BudgetDetailDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    int countByExample(BudgetDetailExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    int deleteByExample(BudgetDetailExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    void insert(BudgetDetail record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    void insertSelective(BudgetDetail record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    List selectByExample(BudgetDetailExample example);
    
    List selectByReportProject(BudgetDetailExample example);
    List selectByReportDept(BudgetDetailExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    int updateByExampleSelective(BudgetDetail record, BudgetDetailExample example) throws MessageException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    int updateByExample(BudgetDetail record, BudgetDetailExample example);
}