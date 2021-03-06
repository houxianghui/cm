package com.blue.product;

import com.blue.product.ProductDef;
import com.blue.product.ProductDefExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProductDefDAOImpl extends SqlMapClientDaoSupport implements ProductDefDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public ProductDefDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int countByExample(ProductDefExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("product_def.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int deleteByExample(ProductDefExample example) {
        int rows = getSqlMapClientTemplate().delete("product_def.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int deleteByPrimaryKey(String productCode) {
        ProductDef key = new ProductDef();
        key.setProductCode(productCode);
        int rows = getSqlMapClientTemplate().delete("product_def.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public void insert(ProductDef record) {
        getSqlMapClientTemplate().insert("product_def.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public void insertSelective(ProductDef record) {
        getSqlMapClientTemplate().insert("product_def.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public List selectByExample(ProductDefExample example) {
        List list = getSqlMapClientTemplate().queryForList("product_def.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public ProductDef selectByPrimaryKey(String productCode) {
        ProductDef key = new ProductDef();
        key.setProductCode(productCode);
        ProductDef record = (ProductDef) getSqlMapClientTemplate().queryForObject("product_def.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int updateByExampleSelective(ProductDef record, ProductDefExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("product_def.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int updateByExample(ProductDef record, ProductDefExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("product_def.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int updateByPrimaryKeySelective(ProductDef record) {
        int rows = getSqlMapClientTemplate().update("product_def.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int updateByPrimaryKey(ProductDef record) {
        int rows = getSqlMapClientTemplate().update("product_def.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table product_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    private static class UpdateByExampleParms extends ProductDefExample {
        private Object record;

        public UpdateByExampleParms(Object record, ProductDefExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}