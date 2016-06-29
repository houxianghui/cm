package com.blue.budget.detail;

import com.blue.budget.detail.BudgetDetail;
import com.blue.budget.detail.BudgetDetailExample;
import com.eis.exception.MessageException;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BudgetDetailDAOImpl extends SqlMapClientDaoSupport implements BudgetDetailDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    public BudgetDetailDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    public int countByExample(BudgetDetailExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("budget_detail.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    public int deleteByExample(BudgetDetailExample example) {
        int rows = getSqlMapClientTemplate().delete("budget_detail.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    public void insert(BudgetDetail record) {
        getSqlMapClientTemplate().insert("budget_detail.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    public void insertSelective(BudgetDetail record) {
        getSqlMapClientTemplate().insert("budget_detail.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    public List selectByExample(BudgetDetailExample example) {
        List list = getSqlMapClientTemplate().queryForList("budget_detail.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     * @throws MessageException 
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    public int updateByExampleSelective(BudgetDetail record, BudgetDetailExample example) throws MessageException {
    	if(record.getFeeBal()!=null&&record.getFeeBal()<0){
    		throw new MessageException("ʣ��Ԥ�㲻�㱨��");
    	}
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("budget_detail.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    public int updateByExample(BudgetDetail record, BudgetDetailExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("budget_detail.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table budget_detail
     *
     * @ibatorgenerated Wed Apr 02 17:59:32 CST 2014
     */
    private static class UpdateByExampleParms extends BudgetDetailExample {
        private Object record;

        public UpdateByExampleParms(Object record, BudgetDetailExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    public List selectByReportProject(BudgetDetailExample example) {
        List list = getSqlMapClientTemplate().queryForList("budget_detail.selectByReportProject", example);
        return list;
    }
    
    public List selectByReportDept(BudgetDetailExample example) {
        List list = getSqlMapClientTemplate().queryForList("budget_detail.selectByReportDept", example);
        return list;
    }
}