package com.blue.projectchgrecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectChgRecordExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public ProjectChgRecordExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    protected ProjectChgRecordExample(ProjectChgRecordExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
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
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table project_chg_record
     *
     * @ibatorgenerated Mon Feb 18 17:16:02 CST 2013
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

        public Criteria andFireDateIsNull() {
            addCriterion("FIRE_DATE is null");
            return this;
        }

        public Criteria andFireDateIsNotNull() {
            addCriterion("FIRE_DATE is not null");
            return this;
        }

        public Criteria andFireDateEqualTo(String value) {
            addCriterion("FIRE_DATE =", value, "fireDate");
            return this;
        }

        public Criteria andFireDateNotEqualTo(String value) {
            addCriterion("FIRE_DATE <>", value, "fireDate");
            return this;
        }

        public Criteria andFireDateGreaterThan(String value) {
            addCriterion("FIRE_DATE >", value, "fireDate");
            return this;
        }

        public Criteria andFireDateGreaterThanOrEqualTo(String value) {
            addCriterion("FIRE_DATE >=", value, "fireDate");
            return this;
        }

        public Criteria andFireDateLessThan(String value) {
            addCriterion("FIRE_DATE <", value, "fireDate");
            return this;
        }

        public Criteria andFireDateLessThanOrEqualTo(String value) {
            addCriterion("FIRE_DATE <=", value, "fireDate");
            return this;
        }

        public Criteria andFireDateLike(String value) {
            addCriterion("FIRE_DATE like", value, "fireDate");
            return this;
        }

        public Criteria andFireDateNotLike(String value) {
            addCriterion("FIRE_DATE not like", value, "fireDate");
            return this;
        }

        public Criteria andFireDateIn(List values) {
            addCriterion("FIRE_DATE in", values, "fireDate");
            return this;
        }

        public Criteria andFireDateNotIn(List values) {
            addCriterion("FIRE_DATE not in", values, "fireDate");
            return this;
        }

        public Criteria andFireDateBetween(String value1, String value2) {
            addCriterion("FIRE_DATE between", value1, value2, "fireDate");
            return this;
        }

        public Criteria andFireDateNotBetween(String value1, String value2) {
            addCriterion("FIRE_DATE not between", value1, value2, "fireDate");
            return this;
        }

        public Criteria andFinishDateIsNull() {
            addCriterion("FINISH_DATE is null");
            return this;
        }

        public Criteria andFinishDateIsNotNull() {
            addCriterion("FINISH_DATE is not null");
            return this;
        }

        public Criteria andFinishDateEqualTo(String value) {
            addCriterion("FINISH_DATE =", value, "finishDate");
            return this;
        }

        public Criteria andFinishDateNotEqualTo(String value) {
            addCriterion("FINISH_DATE <>", value, "finishDate");
            return this;
        }

        public Criteria andFinishDateGreaterThan(String value) {
            addCriterion("FINISH_DATE >", value, "finishDate");
            return this;
        }

        public Criteria andFinishDateGreaterThanOrEqualTo(String value) {
            addCriterion("FINISH_DATE >=", value, "finishDate");
            return this;
        }

        public Criteria andFinishDateLessThan(String value) {
            addCriterion("FINISH_DATE <", value, "finishDate");
            return this;
        }

        public Criteria andFinishDateLessThanOrEqualTo(String value) {
            addCriterion("FINISH_DATE <=", value, "finishDate");
            return this;
        }

        public Criteria andFinishDateLike(String value) {
            addCriterion("FINISH_DATE like", value, "finishDate");
            return this;
        }

        public Criteria andFinishDateNotLike(String value) {
            addCriterion("FINISH_DATE not like", value, "finishDate");
            return this;
        }

        public Criteria andFinishDateIn(List values) {
            addCriterion("FINISH_DATE in", values, "finishDate");
            return this;
        }

        public Criteria andFinishDateNotIn(List values) {
            addCriterion("FINISH_DATE not in", values, "finishDate");
            return this;
        }

        public Criteria andFinishDateBetween(String value1, String value2) {
            addCriterion("FINISH_DATE between", value1, value2, "finishDate");
            return this;
        }

        public Criteria andFinishDateNotBetween(String value1, String value2) {
            addCriterion("FINISH_DATE not between", value1, value2, "finishDate");
            return this;
        }

        public Criteria andChangeTypeIsNull() {
            addCriterion("CHANGE_TYPE is null");
            return this;
        }

        public Criteria andChangeTypeIsNotNull() {
            addCriterion("CHANGE_TYPE is not null");
            return this;
        }

        public Criteria andChangeTypeEqualTo(String value) {
            addCriterion("CHANGE_TYPE =", value, "changeType");
            return this;
        }

        public Criteria andChangeTypeNotEqualTo(String value) {
            addCriterion("CHANGE_TYPE <>", value, "changeType");
            return this;
        }

        public Criteria andChangeTypeGreaterThan(String value) {
            addCriterion("CHANGE_TYPE >", value, "changeType");
            return this;
        }

        public Criteria andChangeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_TYPE >=", value, "changeType");
            return this;
        }

        public Criteria andChangeTypeLessThan(String value) {
            addCriterion("CHANGE_TYPE <", value, "changeType");
            return this;
        }

        public Criteria andChangeTypeLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_TYPE <=", value, "changeType");
            return this;
        }

        public Criteria andChangeTypeLike(String value) {
            addCriterion("CHANGE_TYPE like", value, "changeType");
            return this;
        }

        public Criteria andChangeTypeNotLike(String value) {
            addCriterion("CHANGE_TYPE not like", value, "changeType");
            return this;
        }

        public Criteria andChangeTypeIn(List values) {
            addCriterion("CHANGE_TYPE in", values, "changeType");
            return this;
        }

        public Criteria andChangeTypeNotIn(List values) {
            addCriterion("CHANGE_TYPE not in", values, "changeType");
            return this;
        }

        public Criteria andChangeTypeBetween(String value1, String value2) {
            addCriterion("CHANGE_TYPE between", value1, value2, "changeType");
            return this;
        }

        public Criteria andChangeTypeNotBetween(String value1, String value2) {
            addCriterion("CHANGE_TYPE not between", value1, value2, "changeType");
            return this;
        }

        public Criteria andFireUserIsNull() {
            addCriterion("FIRE_USER is null");
            return this;
        }

        public Criteria andFireUserIsNotNull() {
            addCriterion("FIRE_USER is not null");
            return this;
        }

        public Criteria andFireUserEqualTo(String value) {
            addCriterion("FIRE_USER =", value, "fireUser");
            return this;
        }

        public Criteria andFireUserNotEqualTo(String value) {
            addCriterion("FIRE_USER <>", value, "fireUser");
            return this;
        }

        public Criteria andFireUserGreaterThan(String value) {
            addCriterion("FIRE_USER >", value, "fireUser");
            return this;
        }

        public Criteria andFireUserGreaterThanOrEqualTo(String value) {
            addCriterion("FIRE_USER >=", value, "fireUser");
            return this;
        }

        public Criteria andFireUserLessThan(String value) {
            addCriterion("FIRE_USER <", value, "fireUser");
            return this;
        }

        public Criteria andFireUserLessThanOrEqualTo(String value) {
            addCriterion("FIRE_USER <=", value, "fireUser");
            return this;
        }

        public Criteria andFireUserLike(String value) {
            addCriterion("FIRE_USER like", value, "fireUser");
            return this;
        }

        public Criteria andFireUserNotLike(String value) {
            addCriterion("FIRE_USER not like", value, "fireUser");
            return this;
        }

        public Criteria andFireUserIn(List values) {
            addCriterion("FIRE_USER in", values, "fireUser");
            return this;
        }

        public Criteria andFireUserNotIn(List values) {
            addCriterion("FIRE_USER not in", values, "fireUser");
            return this;
        }

        public Criteria andFireUserBetween(String value1, String value2) {
            addCriterion("FIRE_USER between", value1, value2, "fireUser");
            return this;
        }

        public Criteria andFireUserNotBetween(String value1, String value2) {
            addCriterion("FIRE_USER not between", value1, value2, "fireUser");
            return this;
        }

        public Criteria andVersionAfterIsNull() {
            addCriterion("VERSION_AFTER is null");
            return this;
        }

        public Criteria andVersionAfterIsNotNull() {
            addCriterion("VERSION_AFTER is not null");
            return this;
        }

        public Criteria andVersionAfterEqualTo(String value) {
            addCriterion("VERSION_AFTER =", value, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterNotEqualTo(String value) {
            addCriterion("VERSION_AFTER <>", value, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterGreaterThan(String value) {
            addCriterion("VERSION_AFTER >", value, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION_AFTER >=", value, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterLessThan(String value) {
            addCriterion("VERSION_AFTER <", value, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterLessThanOrEqualTo(String value) {
            addCriterion("VERSION_AFTER <=", value, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterLike(String value) {
            addCriterion("VERSION_AFTER like", value, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterNotLike(String value) {
            addCriterion("VERSION_AFTER not like", value, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterIn(List values) {
            addCriterion("VERSION_AFTER in", values, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterNotIn(List values) {
            addCriterion("VERSION_AFTER not in", values, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterBetween(String value1, String value2) {
            addCriterion("VERSION_AFTER between", value1, value2, "versionAfter");
            return this;
        }

        public Criteria andVersionAfterNotBetween(String value1, String value2) {
            addCriterion("VERSION_AFTER not between", value1, value2, "versionAfter");
            return this;
        }

        public Criteria andOperUserIsNull() {
            addCriterion("OPER_USER is null");
            return this;
        }

        public Criteria andOperUserIsNotNull() {
            addCriterion("OPER_USER is not null");
            return this;
        }

        public Criteria andOperUserEqualTo(String value) {
            addCriterion("OPER_USER =", value, "operUser");
            return this;
        }

        public Criteria andOperUserNotEqualTo(String value) {
            addCriterion("OPER_USER <>", value, "operUser");
            return this;
        }

        public Criteria andOperUserGreaterThan(String value) {
            addCriterion("OPER_USER >", value, "operUser");
            return this;
        }

        public Criteria andOperUserGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_USER >=", value, "operUser");
            return this;
        }

        public Criteria andOperUserLessThan(String value) {
            addCriterion("OPER_USER <", value, "operUser");
            return this;
        }

        public Criteria andOperUserLessThanOrEqualTo(String value) {
            addCriterion("OPER_USER <=", value, "operUser");
            return this;
        }

        public Criteria andOperUserLike(String value) {
            addCriterion("OPER_USER like", value, "operUser");
            return this;
        }

        public Criteria andOperUserNotLike(String value) {
            addCriterion("OPER_USER not like", value, "operUser");
            return this;
        }

        public Criteria andOperUserIn(List values) {
            addCriterion("OPER_USER in", values, "operUser");
            return this;
        }

        public Criteria andOperUserNotIn(List values) {
            addCriterion("OPER_USER not in", values, "operUser");
            return this;
        }

        public Criteria andOperUserBetween(String value1, String value2) {
            addCriterion("OPER_USER between", value1, value2, "operUser");
            return this;
        }

        public Criteria andOperUserNotBetween(String value1, String value2) {
            addCriterion("OPER_USER not between", value1, value2, "operUser");
            return this;
        }

        public Criteria andTargetVersionIsNull() {
            addCriterion("TARGET_VERSION is null");
            return this;
        }

        public Criteria andTargetVersionIsNotNull() {
            addCriterion("TARGET_VERSION is not null");
            return this;
        }

        public Criteria andTargetVersionEqualTo(String value) {
            addCriterion("TARGET_VERSION =", value, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionNotEqualTo(String value) {
            addCriterion("TARGET_VERSION <>", value, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionGreaterThan(String value) {
            addCriterion("TARGET_VERSION >", value, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET_VERSION >=", value, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionLessThan(String value) {
            addCriterion("TARGET_VERSION <", value, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionLessThanOrEqualTo(String value) {
            addCriterion("TARGET_VERSION <=", value, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionLike(String value) {
            addCriterion("TARGET_VERSION like", value, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionNotLike(String value) {
            addCriterion("TARGET_VERSION not like", value, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionIn(List values) {
            addCriterion("TARGET_VERSION in", values, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionNotIn(List values) {
            addCriterion("TARGET_VERSION not in", values, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionBetween(String value1, String value2) {
            addCriterion("TARGET_VERSION between", value1, value2, "targetVersion");
            return this;
        }

        public Criteria andTargetVersionNotBetween(String value1, String value2) {
            addCriterion("TARGET_VERSION not between", value1, value2, "targetVersion");
            return this;
        }

        public Criteria andChangeSeqIsNull() {
            addCriterion("CHANGE_SEQ is null");
            return this;
        }

        public Criteria andChangeSeqIsNotNull() {
            addCriterion("CHANGE_SEQ is not null");
            return this;
        }

        public Criteria andChangeSeqEqualTo(String value) {
            addCriterion("CHANGE_SEQ =", value, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqNotEqualTo(String value) {
            addCriterion("CHANGE_SEQ <>", value, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqGreaterThan(String value) {
            addCriterion("CHANGE_SEQ >", value, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_SEQ >=", value, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqLessThan(String value) {
            addCriterion("CHANGE_SEQ <", value, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_SEQ <=", value, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqLike(String value) {
            addCriterion("CHANGE_SEQ like", value, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqNotLike(String value) {
            addCriterion("CHANGE_SEQ not like", value, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqIn(List values) {
            addCriterion("CHANGE_SEQ in", values, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqNotIn(List values) {
            addCriterion("CHANGE_SEQ not in", values, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqBetween(String value1, String value2) {
            addCriterion("CHANGE_SEQ between", value1, value2, "changeSeq");
            return this;
        }

        public Criteria andChangeSeqNotBetween(String value1, String value2) {
            addCriterion("CHANGE_SEQ not between", value1, value2, "changeSeq");
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

        public Criteria andBaseLineIsNull() {
            addCriterion("BASE_LINE is null");
            return this;
        }

        public Criteria andBaseLineIsNotNull() {
            addCriterion("BASE_LINE is not null");
            return this;
        }

        public Criteria andBaseLineEqualTo(String value) {
            addCriterion("BASE_LINE =", value, "baseLine");
            return this;
        }

        public Criteria andBaseLineNotEqualTo(String value) {
            addCriterion("BASE_LINE <>", value, "baseLine");
            return this;
        }

        public Criteria andBaseLineGreaterThan(String value) {
            addCriterion("BASE_LINE >", value, "baseLine");
            return this;
        }

        public Criteria andBaseLineGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_LINE >=", value, "baseLine");
            return this;
        }

        public Criteria andBaseLineLessThan(String value) {
            addCriterion("BASE_LINE <", value, "baseLine");
            return this;
        }

        public Criteria andBaseLineLessThanOrEqualTo(String value) {
            addCriterion("BASE_LINE <=", value, "baseLine");
            return this;
        }

        public Criteria andBaseLineLike(String value) {
            addCriterion("BASE_LINE like", value, "baseLine");
            return this;
        }

        public Criteria andBaseLineNotLike(String value) {
            addCriterion("BASE_LINE not like", value, "baseLine");
            return this;
        }

        public Criteria andBaseLineIn(List values) {
            addCriterion("BASE_LINE in", values, "baseLine");
            return this;
        }

        public Criteria andBaseLineNotIn(List values) {
            addCriterion("BASE_LINE not in", values, "baseLine");
            return this;
        }

        public Criteria andBaseLineBetween(String value1, String value2) {
            addCriterion("BASE_LINE between", value1, value2, "baseLine");
            return this;
        }

        public Criteria andBaseLineNotBetween(String value1, String value2) {
            addCriterion("BASE_LINE not between", value1, value2, "baseLine");
            return this;
        }

        public Criteria andModuleIsNull() {
            addCriterion("MODULE is null");
            return this;
        }

        public Criteria andModuleIsNotNull() {
            addCriterion("MODULE is not null");
            return this;
        }

        public Criteria andModuleEqualTo(String value) {
            addCriterion("MODULE =", value, "module");
            return this;
        }

        public Criteria andModuleNotEqualTo(String value) {
            addCriterion("MODULE <>", value, "module");
            return this;
        }

        public Criteria andModuleGreaterThan(String value) {
            addCriterion("MODULE >", value, "module");
            return this;
        }

        public Criteria andModuleGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE >=", value, "module");
            return this;
        }

        public Criteria andModuleLessThan(String value) {
            addCriterion("MODULE <", value, "module");
            return this;
        }

        public Criteria andModuleLessThanOrEqualTo(String value) {
            addCriterion("MODULE <=", value, "module");
            return this;
        }

        public Criteria andModuleLike(String value) {
            addCriterion("MODULE like", value, "module");
            return this;
        }

        public Criteria andModuleNotLike(String value) {
            addCriterion("MODULE not like", value, "module");
            return this;
        }

        public Criteria andModuleIn(List values) {
            addCriterion("MODULE in", values, "module");
            return this;
        }

        public Criteria andModuleNotIn(List values) {
            addCriterion("MODULE not in", values, "module");
            return this;
        }

        public Criteria andModuleBetween(String value1, String value2) {
            addCriterion("MODULE between", value1, value2, "module");
            return this;
        }

        public Criteria andModuleNotBetween(String value1, String value2) {
            addCriterion("MODULE not between", value1, value2, "module");
            return this;
        }
    }
}