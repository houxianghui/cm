package com.blue.project.riskrecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiskRecordExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public RiskRecordExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    protected RiskRecordExample(RiskRecordExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
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
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table risk_record
     *
     * @ibatorgenerated Sun Feb 17 14:25:11 CST 2013
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

        public Criteria andProjectIdIsNull() {
            addCriterion("PROJECT_ID is null");
            return this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("PROJECT_ID is not null");
            return this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("PROJECT_ID =", value, "projectId");
            return this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("PROJECT_ID <>", value, "projectId");
            return this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("PROJECT_ID >", value, "projectId");
            return this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_ID >=", value, "projectId");
            return this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("PROJECT_ID <", value, "projectId");
            return this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_ID <=", value, "projectId");
            return this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("PROJECT_ID like", value, "projectId");
            return this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("PROJECT_ID not like", value, "projectId");
            return this;
        }

        public Criteria andProjectIdIn(List values) {
            addCriterion("PROJECT_ID in", values, "projectId");
            return this;
        }

        public Criteria andProjectIdNotIn(List values) {
            addCriterion("PROJECT_ID not in", values, "projectId");
            return this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("PROJECT_ID between", value1, value2, "projectId");
            return this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("PROJECT_ID not between", value1, value2, "projectId");
            return this;
        }

        public Criteria andRiskNameIsNull() {
            addCriterion("RISK_NAME is null");
            return this;
        }

        public Criteria andRiskNameIsNotNull() {
            addCriterion("RISK_NAME is not null");
            return this;
        }

        public Criteria andRiskNameEqualTo(String value) {
            addCriterion("RISK_NAME =", value, "riskName");
            return this;
        }

        public Criteria andRiskNameNotEqualTo(String value) {
            addCriterion("RISK_NAME <>", value, "riskName");
            return this;
        }

        public Criteria andRiskNameGreaterThan(String value) {
            addCriterion("RISK_NAME >", value, "riskName");
            return this;
        }

        public Criteria andRiskNameGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_NAME >=", value, "riskName");
            return this;
        }

        public Criteria andRiskNameLessThan(String value) {
            addCriterion("RISK_NAME <", value, "riskName");
            return this;
        }

        public Criteria andRiskNameLessThanOrEqualTo(String value) {
            addCriterion("RISK_NAME <=", value, "riskName");
            return this;
        }

        public Criteria andRiskNameLike(String value) {
            addCriterion("RISK_NAME like", value, "riskName");
            return this;
        }

        public Criteria andRiskNameNotLike(String value) {
            addCriterion("RISK_NAME not like", value, "riskName");
            return this;
        }

        public Criteria andRiskNameIn(List values) {
            addCriterion("RISK_NAME in", values, "riskName");
            return this;
        }

        public Criteria andRiskNameNotIn(List values) {
            addCriterion("RISK_NAME not in", values, "riskName");
            return this;
        }

        public Criteria andRiskNameBetween(String value1, String value2) {
            addCriterion("RISK_NAME between", value1, value2, "riskName");
            return this;
        }

        public Criteria andRiskNameNotBetween(String value1, String value2) {
            addCriterion("RISK_NAME not between", value1, value2, "riskName");
            return this;
        }

        public Criteria andRiskTypeIsNull() {
            addCriterion("RISK_TYPE is null");
            return this;
        }

        public Criteria andRiskTypeIsNotNull() {
            addCriterion("RISK_TYPE is not null");
            return this;
        }

        public Criteria andRiskTypeEqualTo(String value) {
            addCriterion("RISK_TYPE =", value, "riskType");
            return this;
        }

        public Criteria andRiskTypeNotEqualTo(String value) {
            addCriterion("RISK_TYPE <>", value, "riskType");
            return this;
        }

        public Criteria andRiskTypeGreaterThan(String value) {
            addCriterion("RISK_TYPE >", value, "riskType");
            return this;
        }

        public Criteria andRiskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_TYPE >=", value, "riskType");
            return this;
        }

        public Criteria andRiskTypeLessThan(String value) {
            addCriterion("RISK_TYPE <", value, "riskType");
            return this;
        }

        public Criteria andRiskTypeLessThanOrEqualTo(String value) {
            addCriterion("RISK_TYPE <=", value, "riskType");
            return this;
        }

        public Criteria andRiskTypeLike(String value) {
            addCriterion("RISK_TYPE like", value, "riskType");
            return this;
        }

        public Criteria andRiskTypeNotLike(String value) {
            addCriterion("RISK_TYPE not like", value, "riskType");
            return this;
        }

        public Criteria andRiskTypeIn(List values) {
            addCriterion("RISK_TYPE in", values, "riskType");
            return this;
        }

        public Criteria andRiskTypeNotIn(List values) {
            addCriterion("RISK_TYPE not in", values, "riskType");
            return this;
        }

        public Criteria andRiskTypeBetween(String value1, String value2) {
            addCriterion("RISK_TYPE between", value1, value2, "riskType");
            return this;
        }

        public Criteria andRiskTypeNotBetween(String value1, String value2) {
            addCriterion("RISK_TYPE not between", value1, value2, "riskType");
            return this;
        }

        public Criteria andRiskLvIsNull() {
            addCriterion("RISK_LV is null");
            return this;
        }

        public Criteria andRiskLvIsNotNull() {
            addCriterion("RISK_LV is not null");
            return this;
        }

        public Criteria andRiskLvEqualTo(String value) {
            addCriterion("RISK_LV =", value, "riskLv");
            return this;
        }

        public Criteria andRiskLvNotEqualTo(String value) {
            addCriterion("RISK_LV <>", value, "riskLv");
            return this;
        }

        public Criteria andRiskLvGreaterThan(String value) {
            addCriterion("RISK_LV >", value, "riskLv");
            return this;
        }

        public Criteria andRiskLvGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_LV >=", value, "riskLv");
            return this;
        }

        public Criteria andRiskLvLessThan(String value) {
            addCriterion("RISK_LV <", value, "riskLv");
            return this;
        }

        public Criteria andRiskLvLessThanOrEqualTo(String value) {
            addCriterion("RISK_LV <=", value, "riskLv");
            return this;
        }

        public Criteria andRiskLvLike(String value) {
            addCriterion("RISK_LV like", value, "riskLv");
            return this;
        }

        public Criteria andRiskLvNotLike(String value) {
            addCriterion("RISK_LV not like", value, "riskLv");
            return this;
        }

        public Criteria andRiskLvIn(List values) {
            addCriterion("RISK_LV in", values, "riskLv");
            return this;
        }

        public Criteria andRiskLvNotIn(List values) {
            addCriterion("RISK_LV not in", values, "riskLv");
            return this;
        }

        public Criteria andRiskLvBetween(String value1, String value2) {
            addCriterion("RISK_LV between", value1, value2, "riskLv");
            return this;
        }

        public Criteria andRiskLvNotBetween(String value1, String value2) {
            addCriterion("RISK_LV not between", value1, value2, "riskLv");
            return this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return this;
        }

        public Criteria andUserIdIn(List values) {
            addCriterion("USER_ID in", values, "userId");
            return this;
        }

        public Criteria andUserIdNotIn(List values) {
            addCriterion("USER_ID not in", values, "userId");
            return this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return this;
        }

        public Criteria andInputDateIsNull() {
            addCriterion("INPUT_DATE is null");
            return this;
        }

        public Criteria andInputDateIsNotNull() {
            addCriterion("INPUT_DATE is not null");
            return this;
        }

        public Criteria andInputDateEqualTo(String value) {
            addCriterion("INPUT_DATE =", value, "inputDate");
            return this;
        }

        public Criteria andInputDateNotEqualTo(String value) {
            addCriterion("INPUT_DATE <>", value, "inputDate");
            return this;
        }

        public Criteria andInputDateGreaterThan(String value) {
            addCriterion("INPUT_DATE >", value, "inputDate");
            return this;
        }

        public Criteria andInputDateGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_DATE >=", value, "inputDate");
            return this;
        }

        public Criteria andInputDateLessThan(String value) {
            addCriterion("INPUT_DATE <", value, "inputDate");
            return this;
        }

        public Criteria andInputDateLessThanOrEqualTo(String value) {
            addCriterion("INPUT_DATE <=", value, "inputDate");
            return this;
        }

        public Criteria andInputDateLike(String value) {
            addCriterion("INPUT_DATE like", value, "inputDate");
            return this;
        }

        public Criteria andInputDateNotLike(String value) {
            addCriterion("INPUT_DATE not like", value, "inputDate");
            return this;
        }

        public Criteria andInputDateIn(List values) {
            addCriterion("INPUT_DATE in", values, "inputDate");
            return this;
        }

        public Criteria andInputDateNotIn(List values) {
            addCriterion("INPUT_DATE not in", values, "inputDate");
            return this;
        }

        public Criteria andInputDateBetween(String value1, String value2) {
            addCriterion("INPUT_DATE between", value1, value2, "inputDate");
            return this;
        }

        public Criteria andInputDateNotBetween(String value1, String value2) {
            addCriterion("INPUT_DATE not between", value1, value2, "inputDate");
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

        public Criteria andRiskStatusIsNull() {
            addCriterion("RISK_STATUS is null");
            return this;
        }

        public Criteria andRiskStatusIsNotNull() {
            addCriterion("RISK_STATUS is not null");
            return this;
        }

        public Criteria andRiskStatusEqualTo(String value) {
            addCriterion("RISK_STATUS =", value, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusNotEqualTo(String value) {
            addCriterion("RISK_STATUS <>", value, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusGreaterThan(String value) {
            addCriterion("RISK_STATUS >", value, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_STATUS >=", value, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusLessThan(String value) {
            addCriterion("RISK_STATUS <", value, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusLessThanOrEqualTo(String value) {
            addCriterion("RISK_STATUS <=", value, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusLike(String value) {
            addCriterion("RISK_STATUS like", value, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusNotLike(String value) {
            addCriterion("RISK_STATUS not like", value, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusIn(List values) {
            addCriterion("RISK_STATUS in", values, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusNotIn(List values) {
            addCriterion("RISK_STATUS not in", values, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusBetween(String value1, String value2) {
            addCriterion("RISK_STATUS between", value1, value2, "riskStatus");
            return this;
        }

        public Criteria andRiskStatusNotBetween(String value1, String value2) {
            addCriterion("RISK_STATUS not between", value1, value2, "riskStatus");
            return this;
        }

        public Criteria andSolutionIsNull() {
            addCriterion("SOLUTION is null");
            return this;
        }

        public Criteria andSolutionIsNotNull() {
            addCriterion("SOLUTION is not null");
            return this;
        }

        public Criteria andSolutionEqualTo(String value) {
            addCriterion("SOLUTION =", value, "solution");
            return this;
        }

        public Criteria andSolutionNotEqualTo(String value) {
            addCriterion("SOLUTION <>", value, "solution");
            return this;
        }

        public Criteria andSolutionGreaterThan(String value) {
            addCriterion("SOLUTION >", value, "solution");
            return this;
        }

        public Criteria andSolutionGreaterThanOrEqualTo(String value) {
            addCriterion("SOLUTION >=", value, "solution");
            return this;
        }

        public Criteria andSolutionLessThan(String value) {
            addCriterion("SOLUTION <", value, "solution");
            return this;
        }

        public Criteria andSolutionLessThanOrEqualTo(String value) {
            addCriterion("SOLUTION <=", value, "solution");
            return this;
        }

        public Criteria andSolutionLike(String value) {
            addCriterion("SOLUTION like", value, "solution");
            return this;
        }

        public Criteria andSolutionNotLike(String value) {
            addCriterion("SOLUTION not like", value, "solution");
            return this;
        }

        public Criteria andSolutionIn(List values) {
            addCriterion("SOLUTION in", values, "solution");
            return this;
        }

        public Criteria andSolutionNotIn(List values) {
            addCriterion("SOLUTION not in", values, "solution");
            return this;
        }

        public Criteria andSolutionBetween(String value1, String value2) {
            addCriterion("SOLUTION between", value1, value2, "solution");
            return this;
        }

        public Criteria andSolutionNotBetween(String value1, String value2) {
            addCriterion("SOLUTION not between", value1, value2, "solution");
            return this;
        }

        public Criteria andSoluteDateIsNull() {
            addCriterion("SOLUTE_DATE is null");
            return this;
        }

        public Criteria andSoluteDateIsNotNull() {
            addCriterion("SOLUTE_DATE is not null");
            return this;
        }

        public Criteria andSoluteDateEqualTo(String value) {
            addCriterion("SOLUTE_DATE =", value, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateNotEqualTo(String value) {
            addCriterion("SOLUTE_DATE <>", value, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateGreaterThan(String value) {
            addCriterion("SOLUTE_DATE >", value, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateGreaterThanOrEqualTo(String value) {
            addCriterion("SOLUTE_DATE >=", value, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateLessThan(String value) {
            addCriterion("SOLUTE_DATE <", value, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateLessThanOrEqualTo(String value) {
            addCriterion("SOLUTE_DATE <=", value, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateLike(String value) {
            addCriterion("SOLUTE_DATE like", value, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateNotLike(String value) {
            addCriterion("SOLUTE_DATE not like", value, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateIn(List values) {
            addCriterion("SOLUTE_DATE in", values, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateNotIn(List values) {
            addCriterion("SOLUTE_DATE not in", values, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateBetween(String value1, String value2) {
            addCriterion("SOLUTE_DATE between", value1, value2, "soluteDate");
            return this;
        }

        public Criteria andSoluteDateNotBetween(String value1, String value2) {
            addCriterion("SOLUTE_DATE not between", value1, value2, "soluteDate");
            return this;
        }

        public Criteria andRiskIdIsNull() {
            addCriterion("RISK_ID is null");
            return this;
        }

        public Criteria andRiskIdIsNotNull() {
            addCriterion("RISK_ID is not null");
            return this;
        }

        public Criteria andRiskIdEqualTo(Integer value) {
            addCriterion("RISK_ID =", value, "riskId");
            return this;
        }

        public Criteria andRiskIdNotEqualTo(Integer value) {
            addCriterion("RISK_ID <>", value, "riskId");
            return this;
        }

        public Criteria andRiskIdGreaterThan(Integer value) {
            addCriterion("RISK_ID >", value, "riskId");
            return this;
        }

        public Criteria andRiskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("RISK_ID >=", value, "riskId");
            return this;
        }

        public Criteria andRiskIdLessThan(Integer value) {
            addCriterion("RISK_ID <", value, "riskId");
            return this;
        }

        public Criteria andRiskIdLessThanOrEqualTo(Integer value) {
            addCriterion("RISK_ID <=", value, "riskId");
            return this;
        }

        public Criteria andRiskIdIn(List values) {
            addCriterion("RISK_ID in", values, "riskId");
            return this;
        }

        public Criteria andRiskIdNotIn(List values) {
            addCriterion("RISK_ID not in", values, "riskId");
            return this;
        }

        public Criteria andRiskIdBetween(Integer value1, Integer value2) {
            addCriterion("RISK_ID between", value1, value2, "riskId");
            return this;
        }

        public Criteria andRiskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("RISK_ID not between", value1, value2, "riskId");
            return this;
        }
    }
}