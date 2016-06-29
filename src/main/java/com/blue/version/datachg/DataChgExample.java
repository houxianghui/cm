package com.blue.version.datachg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataChgExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public DataChgExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    protected DataChgExample(DataChgExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table data_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return this;
        }

        public Criteria andIdIn(List values) {
            addCriterion("ID in", values, "id");
            return this;
        }

        public Criteria andIdNotIn(List values) {
            addCriterion("ID not in", values, "id");
            return this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return this;
        }

        public Criteria andShellNameIsNull() {
            addCriterion("SHELL_NAME is null");
            return this;
        }

        public Criteria andShellNameIsNotNull() {
            addCriterion("SHELL_NAME is not null");
            return this;
        }

        public Criteria andShellNameEqualTo(String value) {
            addCriterion("SHELL_NAME =", value, "shellName");
            return this;
        }

        public Criteria andShellNameNotEqualTo(String value) {
            addCriterion("SHELL_NAME <>", value, "shellName");
            return this;
        }

        public Criteria andShellNameGreaterThan(String value) {
            addCriterion("SHELL_NAME >", value, "shellName");
            return this;
        }

        public Criteria andShellNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHELL_NAME >=", value, "shellName");
            return this;
        }

        public Criteria andShellNameLessThan(String value) {
            addCriterion("SHELL_NAME <", value, "shellName");
            return this;
        }

        public Criteria andShellNameLessThanOrEqualTo(String value) {
            addCriterion("SHELL_NAME <=", value, "shellName");
            return this;
        }

        public Criteria andShellNameLike(String value) {
            addCriterion("SHELL_NAME like", value, "shellName");
            return this;
        }

        public Criteria andShellNameNotLike(String value) {
            addCriterion("SHELL_NAME not like", value, "shellName");
            return this;
        }

        public Criteria andShellNameIn(List values) {
            addCriterion("SHELL_NAME in", values, "shellName");
            return this;
        }

        public Criteria andShellNameNotIn(List values) {
            addCriterion("SHELL_NAME not in", values, "shellName");
            return this;
        }

        public Criteria andShellNameBetween(String value1, String value2) {
            addCriterion("SHELL_NAME between", value1, value2, "shellName");
            return this;
        }

        public Criteria andShellNameNotBetween(String value1, String value2) {
            addCriterion("SHELL_NAME not between", value1, value2, "shellName");
            return this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return this;
        }

        public Criteria andMemoIn(List values) {
            addCriterion("MEMO in", values, "memo");
            return this;
        }

        public Criteria andMemoNotIn(List values) {
            addCriterion("MEMO not in", values, "memo");
            return this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return this;
        }

        public Criteria andVersionIdIsNull() {
            addCriterion("VERSION_ID is null");
            return this;
        }

        public Criteria andVersionIdIsNotNull() {
            addCriterion("VERSION_ID is not null");
            return this;
        }

        public Criteria andVersionIdEqualTo(String value) {
            addCriterion("VERSION_ID =", value, "versionId");
            return this;
        }

        public Criteria andVersionIdNotEqualTo(String value) {
            addCriterion("VERSION_ID <>", value, "versionId");
            return this;
        }

        public Criteria andVersionIdGreaterThan(String value) {
            addCriterion("VERSION_ID >", value, "versionId");
            return this;
        }

        public Criteria andVersionIdGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION_ID >=", value, "versionId");
            return this;
        }

        public Criteria andVersionIdLessThan(String value) {
            addCriterion("VERSION_ID <", value, "versionId");
            return this;
        }

        public Criteria andVersionIdLessThanOrEqualTo(String value) {
            addCriterion("VERSION_ID <=", value, "versionId");
            return this;
        }

        public Criteria andVersionIdLike(String value) {
            addCriterion("VERSION_ID like", value, "versionId");
            return this;
        }

        public Criteria andVersionIdNotLike(String value) {
            addCriterion("VERSION_ID not like", value, "versionId");
            return this;
        }

        public Criteria andVersionIdIn(List values) {
            addCriterion("VERSION_ID in", values, "versionId");
            return this;
        }

        public Criteria andVersionIdNotIn(List values) {
            addCriterion("VERSION_ID not in", values, "versionId");
            return this;
        }

        public Criteria andVersionIdBetween(String value1, String value2) {
            addCriterion("VERSION_ID between", value1, value2, "versionId");
            return this;
        }

        public Criteria andVersionIdNotBetween(String value1, String value2) {
            addCriterion("VERSION_ID not between", value1, value2, "versionId");
            return this;
        }
    }
}