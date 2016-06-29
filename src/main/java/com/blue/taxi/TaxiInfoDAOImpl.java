package com.blue.taxi;

import com.blue.taxi.TaxiInfo;
import com.blue.taxi.TaxiInfoExample;
import com.eis.util.CheckUtil;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class TaxiInfoDAOImpl extends SqlMapClientDaoSupport implements TaxiInfoDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public TaxiInfoDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public int countByExample(TaxiInfoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("taxi_info.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public int deleteByExample(TaxiInfoExample example) {
        int rows = getSqlMapClientTemplate().delete("taxi_info.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public int deleteByPrimaryKey(Integer taxiId) {
        TaxiInfo key = new TaxiInfo();
        key.setTaxiId(taxiId);
        int rows = getSqlMapClientTemplate().delete("taxi_info.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public void insert(TaxiInfo record) {
        getSqlMapClientTemplate().insert("taxi_info.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public void insertSelective(TaxiInfo record) {
        getSqlMapClientTemplate().insert("taxi_info.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public List selectByExample(TaxiInfoExample example) {
        List list = getSqlMapClientTemplate().queryForList("taxi_info.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public TaxiInfo selectByPrimaryKey(Integer taxiId) {
        TaxiInfo key = new TaxiInfo();
        key.setTaxiId(taxiId);
        TaxiInfo record = (TaxiInfo) getSqlMapClientTemplate().queryForObject("taxi_info.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public int updateByExampleSelective(TaxiInfo record, TaxiInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("taxi_info.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public int updateByExample(TaxiInfo record, TaxiInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("taxi_info.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public int updateByPrimaryKeySelective(TaxiInfo record) {
        int rows = getSqlMapClientTemplate().update("taxi_info.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    public int updateByPrimaryKey(TaxiInfo record) {
        int rows = getSqlMapClientTemplate().update("taxi_info.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table taxi_info
     *
     * @ibatorgenerated Fri Apr 18 09:41:45 CST 2014
     */
    private static class UpdateByExampleParms extends TaxiInfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, TaxiInfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    public double getTotal(String expensesId){
		double total=0;
		TaxiInfoExample e  = new TaxiInfoExample();
		TaxiInfoExample.Criteria c = e.createCriteria();
		if(CheckUtil.isEmptry(expensesId))
			return total;
		else
			c.andExpensesIdEqualTo(Long.parseLong(expensesId));
		
		List<TaxiInfo> tlist = selectByExample(e);
		for (TaxiInfo taxiInfo : tlist) {
			total += taxiInfo.getTaxiAmt();
		}
		return total;
	}
    
    public int getInvoiceNoSum(String expensesId){
		int total = 0;
		TaxiInfoExample e  = new TaxiInfoExample();
		TaxiInfoExample.Criteria c = e.createCriteria();
		if(CheckUtil.isEmptry(expensesId))
			return total;
		else
			c.andExpensesIdEqualTo(Long.parseLong(expensesId));
		
		List<TaxiInfo> tlist = selectByExample(e);
		for (TaxiInfo taxiInfo : tlist) {
			total += taxiInfo.getTaxiInvoiceno();
		}
		return total;
	}
}