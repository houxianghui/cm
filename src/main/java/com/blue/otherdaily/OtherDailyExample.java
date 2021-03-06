package com.blue.otherdaily;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherDailyExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    public OtherDailyExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    protected OtherDailyExample(OtherDailyExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
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
     * This method corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table other_daily
     *
     * @ibatorgenerated Wed Feb 06 15:57:52 CST 2013
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

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return this;
        }

        public Criteria andTypeIn(List values) {
            addCriterion("TYPE in", values, "type");
            return this;
        }

        public Criteria andTypeNotIn(List values) {
            addCriterion("TYPE not in", values, "type");
            return this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return this;
        }

        public Criteria andWorkDateIsNull() {
            addCriterion("WORK_DATE is null");
            return this;
        }

        public Criteria andWorkDateIsNotNull() {
            addCriterion("WORK_DATE is not null");
            return this;
        }

        public Criteria andWorkDateEqualTo(String value) {
            addCriterion("WORK_DATE =", value, "workDate");
            return this;
        }

        public Criteria andWorkDateNotEqualTo(String value) {
            addCriterion("WORK_DATE <>", value, "workDate");
            return this;
        }

        public Criteria andWorkDateGreaterThan(String value) {
            addCriterion("WORK_DATE >", value, "workDate");
            return this;
        }

        public Criteria andWorkDateGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_DATE >=", value, "workDate");
            return this;
        }

        public Criteria andWorkDateLessThan(String value) {
            addCriterion("WORK_DATE <", value, "workDate");
            return this;
        }

        public Criteria andWorkDateLessThanOrEqualTo(String value) {
            addCriterion("WORK_DATE <=", value, "workDate");
            return this;
        }

        public Criteria andWorkDateLike(String value) {
            addCriterion("WORK_DATE like", value, "workDate");
            return this;
        }

        public Criteria andWorkDateNotLike(String value) {
            addCriterion("WORK_DATE not like", value, "workDate");
            return this;
        }

        public Criteria andWorkDateIn(List values) {
            addCriterion("WORK_DATE in", values, "workDate");
            return this;
        }

        public Criteria andWorkDateNotIn(List values) {
            addCriterion("WORK_DATE not in", values, "workDate");
            return this;
        }

        public Criteria andWorkDateBetween(String value1, String value2) {
            addCriterion("WORK_DATE between", value1, value2, "workDate");
            return this;
        }

        public Criteria andWorkDateNotBetween(String value1, String value2) {
            addCriterion("WORK_DATE not between", value1, value2, "workDate");
            return this;
        }

        public Criteria andCostIsNull() {
            addCriterion("COST is null");
            return this;
        }

        public Criteria andCostIsNotNull() {
            addCriterion("COST is not null");
            return this;
        }

        public Criteria andCostEqualTo(BigDecimal value) {
            addCriterion("COST =", value, "cost");
            return this;
        }

        public Criteria andCostNotEqualTo(BigDecimal value) {
            addCriterion("COST <>", value, "cost");
            return this;
        }

        public Criteria andCostGreaterThan(BigDecimal value) {
            addCriterion("COST >", value, "cost");
            return this;
        }

        public Criteria andCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COST >=", value, "cost");
            return this;
        }

        public Criteria andCostLessThan(BigDecimal value) {
            addCriterion("COST <", value, "cost");
            return this;
        }

        public Criteria andCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COST <=", value, "cost");
            return this;
        }

        public Criteria andCostIn(List values) {
            addCriterion("COST in", values, "cost");
            return this;
        }

        public Criteria andCostNotIn(List values) {
            addCriterion("COST not in", values, "cost");
            return this;
        }

        public Criteria andCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COST between", value1, value2, "cost");
            return this;
        }

        public Criteria andCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COST not between", value1, value2, "cost");
            return this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("INFO is null");
            return this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("INFO is not null");
            return this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("INFO =", value, "info");
            return this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("INFO <>", value, "info");
            return this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("INFO >", value, "info");
            return this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("INFO >=", value, "info");
            return this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("INFO <", value, "info");
            return this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("INFO <=", value, "info");
            return this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("INFO like", value, "info");
            return this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("INFO not like", value, "info");
            return this;
        }

        public Criteria andInfoIn(List values) {
            addCriterion("INFO in", values, "info");
            return this;
        }

        public Criteria andInfoNotIn(List values) {
            addCriterion("INFO not in", values, "info");
            return this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("INFO between", value1, value2, "info");
            return this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("INFO not between", value1, value2, "info");
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

        public Criteria andInputUserIsNull() {
            addCriterion("INPUT_USER is null");
            return this;
        }

        public Criteria andInputUserIsNotNull() {
            addCriterion("INPUT_USER is not null");
            return this;
        }

        public Criteria andInputUserEqualTo(String value) {
            addCriterion("INPUT_USER =", value, "inputUser");
            return this;
        }

        public Criteria andInputUserNotEqualTo(String value) {
            addCriterion("INPUT_USER <>", value, "inputUser");
            return this;
        }

        public Criteria andInputUserGreaterThan(String value) {
            addCriterion("INPUT_USER >", value, "inputUser");
            return this;
        }

        public Criteria andInputUserGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_USER >=", value, "inputUser");
            return this;
        }

        public Criteria andInputUserLessThan(String value) {
            addCriterion("INPUT_USER <", value, "inputUser");
            return this;
        }

        public Criteria andInputUserLessThanOrEqualTo(String value) {
            addCriterion("INPUT_USER <=", value, "inputUser");
            return this;
        }

        public Criteria andInputUserLike(String value) {
            addCriterion("INPUT_USER like", value, "inputUser");
            return this;
        }

        public Criteria andInputUserNotLike(String value) {
            addCriterion("INPUT_USER not like", value, "inputUser");
            return this;
        }

        public Criteria andInputUserIn(List values) {
            addCriterion("INPUT_USER in", values, "inputUser");
            return this;
        }

        public Criteria andInputUserNotIn(List values) {
            addCriterion("INPUT_USER not in", values, "inputUser");
            return this;
        }

        public Criteria andInputUserBetween(String value1, String value2) {
            addCriterion("INPUT_USER between", value1, value2, "inputUser");
            return this;
        }

        public Criteria andInputUserNotBetween(String value1, String value2) {
            addCriterion("INPUT_USER not between", value1, value2, "inputUser");
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
    }
}