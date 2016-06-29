package com.blue.projectdistribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectDistributeExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    public ProjectDistributeExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    protected ProjectDistributeExample(ProjectDistributeExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
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
     * This method corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table project_distribute
     *
     * @ibatorgenerated Fri Aug 02 16:26:25 CST 2013
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
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

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
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

        public Criteria andStepIsNull() {
            addCriterion("STEP is null");
            return this;
        }

        public Criteria andStepIsNotNull() {
            addCriterion("STEP is not null");
            return this;
        }

        public Criteria andStepEqualTo(String value) {
            addCriterion("STEP =", value, "step");
            return this;
        }

        public Criteria andStepNotEqualTo(String value) {
            addCriterion("STEP <>", value, "step");
            return this;
        }

        public Criteria andStepGreaterThan(String value) {
            addCriterion("STEP >", value, "step");
            return this;
        }

        public Criteria andStepGreaterThanOrEqualTo(String value) {
            addCriterion("STEP >=", value, "step");
            return this;
        }

        public Criteria andStepLessThan(String value) {
            addCriterion("STEP <", value, "step");
            return this;
        }

        public Criteria andStepLessThanOrEqualTo(String value) {
            addCriterion("STEP <=", value, "step");
            return this;
        }

        public Criteria andStepLike(String value) {
            addCriterion("STEP like", value, "step");
            return this;
        }

        public Criteria andStepNotLike(String value) {
            addCriterion("STEP not like", value, "step");
            return this;
        }

        public Criteria andStepIn(List values) {
            addCriterion("STEP in", values, "step");
            return this;
        }

        public Criteria andStepNotIn(List values) {
            addCriterion("STEP not in", values, "step");
            return this;
        }

        public Criteria andStepBetween(String value1, String value2) {
            addCriterion("STEP between", value1, value2, "step");
            return this;
        }

        public Criteria andStepNotBetween(String value1, String value2) {
            addCriterion("STEP not between", value1, value2, "step");
            return this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("START_DATE =", value, "startDate");
            return this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("START_DATE <>", value, "startDate");
            return this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("START_DATE >", value, "startDate");
            return this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_DATE >=", value, "startDate");
            return this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("START_DATE <", value, "startDate");
            return this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("START_DATE <=", value, "startDate");
            return this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("START_DATE like", value, "startDate");
            return this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("START_DATE not like", value, "startDate");
            return this;
        }

        public Criteria andStartDateIn(List values) {
            addCriterion("START_DATE in", values, "startDate");
            return this;
        }

        public Criteria andStartDateNotIn(List values) {
            addCriterion("START_DATE not in", values, "startDate");
            return this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
            return this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("END_DATE =", value, "endDate");
            return this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("END_DATE <>", value, "endDate");
            return this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("END_DATE >", value, "endDate");
            return this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("END_DATE >=", value, "endDate");
            return this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("END_DATE <", value, "endDate");
            return this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("END_DATE <=", value, "endDate");
            return this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("END_DATE like", value, "endDate");
            return this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("END_DATE not like", value, "endDate");
            return this;
        }

        public Criteria andEndDateIn(List values) {
            addCriterion("END_DATE in", values, "endDate");
            return this;
        }

        public Criteria andEndDateNotIn(List values) {
            addCriterion("END_DATE not in", values, "endDate");
            return this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
            return this;
        }

        public Criteria andPercentIsNull() {
            addCriterion("PERCENT is null");
            return this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("PERCENT is not null");
            return this;
        }

        public Criteria andPercentEqualTo(Integer value) {
            addCriterion("PERCENT =", value, "percent");
            return this;
        }

        public Criteria andPercentNotEqualTo(Integer value) {
            addCriterion("PERCENT <>", value, "percent");
            return this;
        }

        public Criteria andPercentGreaterThan(Integer value) {
            addCriterion("PERCENT >", value, "percent");
            return this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("PERCENT >=", value, "percent");
            return this;
        }

        public Criteria andPercentLessThan(Integer value) {
            addCriterion("PERCENT <", value, "percent");
            return this;
        }

        public Criteria andPercentLessThanOrEqualTo(Integer value) {
            addCriterion("PERCENT <=", value, "percent");
            return this;
        }

        public Criteria andPercentIn(List values) {
            addCriterion("PERCENT in", values, "percent");
            return this;
        }

        public Criteria andPercentNotIn(List values) {
            addCriterion("PERCENT not in", values, "percent");
            return this;
        }

        public Criteria andPercentBetween(Integer value1, Integer value2) {
            addCriterion("PERCENT between", value1, value2, "percent");
            return this;
        }

        public Criteria andPercentNotBetween(Integer value1, Integer value2) {
            addCriterion("PERCENT not between", value1, value2, "percent");
            return this;
        }

        public Criteria andIsDoneIsNull() {
            addCriterion("IS_DONE is null");
            return this;
        }

        public Criteria andIsDoneIsNotNull() {
            addCriterion("IS_DONE is not null");
            return this;
        }

        public Criteria andIsDoneEqualTo(String value) {
            addCriterion("IS_DONE =", value, "isDone");
            return this;
        }

        public Criteria andIsDoneNotEqualTo(String value) {
            addCriterion("IS_DONE <>", value, "isDone");
            return this;
        }

        public Criteria andIsDoneGreaterThan(String value) {
            addCriterion("IS_DONE >", value, "isDone");
            return this;
        }

        public Criteria andIsDoneGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DONE >=", value, "isDone");
            return this;
        }

        public Criteria andIsDoneLessThan(String value) {
            addCriterion("IS_DONE <", value, "isDone");
            return this;
        }

        public Criteria andIsDoneLessThanOrEqualTo(String value) {
            addCriterion("IS_DONE <=", value, "isDone");
            return this;
        }

        public Criteria andIsDoneLike(String value) {
            addCriterion("IS_DONE like", value, "isDone");
            return this;
        }

        public Criteria andIsDoneNotLike(String value) {
            addCriterion("IS_DONE not like", value, "isDone");
            return this;
        }

        public Criteria andIsDoneIn(List values) {
            addCriterion("IS_DONE in", values, "isDone");
            return this;
        }

        public Criteria andIsDoneNotIn(List values) {
            addCriterion("IS_DONE not in", values, "isDone");
            return this;
        }

        public Criteria andIsDoneBetween(String value1, String value2) {
            addCriterion("IS_DONE between", value1, value2, "isDone");
            return this;
        }

        public Criteria andIsDoneNotBetween(String value1, String value2) {
            addCriterion("IS_DONE not between", value1, value2, "isDone");
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

        public Criteria andFinalEndDateIsNull() {
            addCriterion("FINAL_END_DATE is null");
            return this;
        }

        public Criteria andFinalEndDateIsNotNull() {
            addCriterion("FINAL_END_DATE is not null");
            return this;
        }

        public Criteria andFinalEndDateEqualTo(String value) {
            addCriterion("FINAL_END_DATE =", value, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateNotEqualTo(String value) {
            addCriterion("FINAL_END_DATE <>", value, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateGreaterThan(String value) {
            addCriterion("FINAL_END_DATE >", value, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("FINAL_END_DATE >=", value, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateLessThan(String value) {
            addCriterion("FINAL_END_DATE <", value, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateLessThanOrEqualTo(String value) {
            addCriterion("FINAL_END_DATE <=", value, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateLike(String value) {
            addCriterion("FINAL_END_DATE like", value, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateNotLike(String value) {
            addCriterion("FINAL_END_DATE not like", value, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateIn(List values) {
            addCriterion("FINAL_END_DATE in", values, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateNotIn(List values) {
            addCriterion("FINAL_END_DATE not in", values, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateBetween(String value1, String value2) {
            addCriterion("FINAL_END_DATE between", value1, value2, "finalEndDate");
            return this;
        }

        public Criteria andFinalEndDateNotBetween(String value1, String value2) {
            addCriterion("FINAL_END_DATE not between", value1, value2, "finalEndDate");
            return this;
        }

        public Criteria andMileStoneIdIsNull() {
            addCriterion("MILE_STONE_ID is null");
            return this;
        }

        public Criteria andMileStoneIdIsNotNull() {
            addCriterion("MILE_STONE_ID is not null");
            return this;
        }

        public Criteria andMileStoneIdEqualTo(Long value) {
            addCriterion("MILE_STONE_ID =", value, "mileStoneId");
            return this;
        }

        public Criteria andMileStoneIdNotEqualTo(Long value) {
            addCriterion("MILE_STONE_ID <>", value, "mileStoneId");
            return this;
        }

        public Criteria andMileStoneIdGreaterThan(Long value) {
            addCriterion("MILE_STONE_ID >", value, "mileStoneId");
            return this;
        }

        public Criteria andMileStoneIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MILE_STONE_ID >=", value, "mileStoneId");
            return this;
        }

        public Criteria andMileStoneIdLessThan(Long value) {
            addCriterion("MILE_STONE_ID <", value, "mileStoneId");
            return this;
        }

        public Criteria andMileStoneIdLessThanOrEqualTo(Long value) {
            addCriterion("MILE_STONE_ID <=", value, "mileStoneId");
            return this;
        }

        public Criteria andMileStoneIdIn(List values) {
            addCriterion("MILE_STONE_ID in", values, "mileStoneId");
            return this;
        }

        public Criteria andMileStoneIdNotIn(List values) {
            addCriterion("MILE_STONE_ID not in", values, "mileStoneId");
            return this;
        }

        public Criteria andMileStoneIdBetween(Long value1, Long value2) {
            addCriterion("MILE_STONE_ID between", value1, value2, "mileStoneId");
            return this;
        }

        public Criteria andMileStoneIdNotBetween(Long value1, Long value2) {
            addCriterion("MILE_STONE_ID not between", value1, value2, "mileStoneId");
            return this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("TASK_NAME is null");
            return this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("TASK_NAME is not null");
            return this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("TASK_NAME =", value, "taskName");
            return this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("TASK_NAME <>", value, "taskName");
            return this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("TASK_NAME >", value, "taskName");
            return this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_NAME >=", value, "taskName");
            return this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("TASK_NAME <", value, "taskName");
            return this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("TASK_NAME <=", value, "taskName");
            return this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("TASK_NAME like", value, "taskName");
            return this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("TASK_NAME not like", value, "taskName");
            return this;
        }

        public Criteria andTaskNameIn(List values) {
            addCriterion("TASK_NAME in", values, "taskName");
            return this;
        }

        public Criteria andTaskNameNotIn(List values) {
            addCriterion("TASK_NAME not in", values, "taskName");
            return this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("TASK_NAME between", value1, value2, "taskName");
            return this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("TASK_NAME not between", value1, value2, "taskName");
            return this;
        }

        public Criteria andActionIdIsNull() {
            addCriterion("ACTION_ID is null");
            return this;
        }

        public Criteria andActionIdIsNotNull() {
            addCriterion("ACTION_ID is not null");
            return this;
        }

        public Criteria andActionIdEqualTo(Integer value) {
            addCriterion("ACTION_ID =", value, "actionId");
            return this;
        }

        public Criteria andActionIdNotEqualTo(Integer value) {
            addCriterion("ACTION_ID <>", value, "actionId");
            return this;
        }

        public Criteria andActionIdGreaterThan(Integer value) {
            addCriterion("ACTION_ID >", value, "actionId");
            return this;
        }

        public Criteria andActionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACTION_ID >=", value, "actionId");
            return this;
        }

        public Criteria andActionIdLessThan(Integer value) {
            addCriterion("ACTION_ID <", value, "actionId");
            return this;
        }

        public Criteria andActionIdLessThanOrEqualTo(Integer value) {
            addCriterion("ACTION_ID <=", value, "actionId");
            return this;
        }

        public Criteria andActionIdIn(List values) {
            addCriterion("ACTION_ID in", values, "actionId");
            return this;
        }

        public Criteria andActionIdNotIn(List values) {
            addCriterion("ACTION_ID not in", values, "actionId");
            return this;
        }

        public Criteria andActionIdBetween(Integer value1, Integer value2) {
            addCriterion("ACTION_ID between", value1, value2, "actionId");
            return this;
        }

        public Criteria andActionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ACTION_ID not between", value1, value2, "actionId");
            return this;
        }

        public Criteria andModuleIdIsNull() {
            addCriterion("MODULE_ID is null");
            return this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("MODULE_ID is not null");
            return this;
        }

        public Criteria andModuleIdEqualTo(String value) {
            addCriterion("MODULE_ID =", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotEqualTo(String value) {
            addCriterion("MODULE_ID <>", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdGreaterThan(String value) {
            addCriterion("MODULE_ID >", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_ID >=", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdLessThan(String value) {
            addCriterion("MODULE_ID <", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(String value) {
            addCriterion("MODULE_ID <=", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdLike(String value) {
            addCriterion("MODULE_ID like", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotLike(String value) {
            addCriterion("MODULE_ID not like", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdIn(List values) {
            addCriterion("MODULE_ID in", values, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotIn(List values) {
            addCriterion("MODULE_ID not in", values, "moduleId");
            return this;
        }

        public Criteria andModuleIdBetween(String value1, String value2) {
            addCriterion("MODULE_ID between", value1, value2, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotBetween(String value1, String value2) {
            addCriterion("MODULE_ID not between", value1, value2, "moduleId");
            return this;
        }
    }
}