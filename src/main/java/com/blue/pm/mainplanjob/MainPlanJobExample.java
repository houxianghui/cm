package com.blue.pm.mainplanjob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPlanJobExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    public MainPlanJobExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    protected MainPlanJobExample(MainPlanJobExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
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
     * This method corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table main_plan_job
     *
     * @ibatorgenerated Tue Aug 06 11:24:58 CST 2013
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

        public Criteria andIsDoenIsNull() {
            addCriterion("IS_DOEN is null");
            return this;
        }

        public Criteria andIsDoenIsNotNull() {
            addCriterion("IS_DOEN is not null");
            return this;
        }

        public Criteria andIsDoenEqualTo(String value) {
            addCriterion("IS_DOEN =", value, "isDoen");
            return this;
        }

        public Criteria andIsDoenNotEqualTo(String value) {
            addCriterion("IS_DOEN <>", value, "isDoen");
            return this;
        }

        public Criteria andIsDoenGreaterThan(String value) {
            addCriterion("IS_DOEN >", value, "isDoen");
            return this;
        }

        public Criteria andIsDoenGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DOEN >=", value, "isDoen");
            return this;
        }

        public Criteria andIsDoenLessThan(String value) {
            addCriterion("IS_DOEN <", value, "isDoen");
            return this;
        }

        public Criteria andIsDoenLessThanOrEqualTo(String value) {
            addCriterion("IS_DOEN <=", value, "isDoen");
            return this;
        }

        public Criteria andIsDoenLike(String value) {
            addCriterion("IS_DOEN like", value, "isDoen");
            return this;
        }

        public Criteria andIsDoenNotLike(String value) {
            addCriterion("IS_DOEN not like", value, "isDoen");
            return this;
        }

        public Criteria andIsDoenIn(List values) {
            addCriterion("IS_DOEN in", values, "isDoen");
            return this;
        }

        public Criteria andIsDoenNotIn(List values) {
            addCriterion("IS_DOEN not in", values, "isDoen");
            return this;
        }

        public Criteria andIsDoenBetween(String value1, String value2) {
            addCriterion("IS_DOEN between", value1, value2, "isDoen");
            return this;
        }

        public Criteria andIsDoenNotBetween(String value1, String value2) {
            addCriterion("IS_DOEN not between", value1, value2, "isDoen");
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