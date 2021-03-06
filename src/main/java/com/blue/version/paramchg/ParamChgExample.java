package com.blue.version.paramchg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamChgExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public ParamChgExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    protected ParamChgExample(ParamChgExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table param_chg
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
     * This method corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table param_chg
     *
     * @ibatorgenerated Thu Mar 21 09:59:31 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table param_chg
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

        public Criteria andSubSysIsNull() {
            addCriterion("SUB_SYS is null");
            return this;
        }

        public Criteria andSubSysIsNotNull() {
            addCriterion("SUB_SYS is not null");
            return this;
        }

        public Criteria andSubSysEqualTo(String value) {
            addCriterion("SUB_SYS =", value, "subSys");
            return this;
        }

        public Criteria andSubSysNotEqualTo(String value) {
            addCriterion("SUB_SYS <>", value, "subSys");
            return this;
        }

        public Criteria andSubSysGreaterThan(String value) {
            addCriterion("SUB_SYS >", value, "subSys");
            return this;
        }

        public Criteria andSubSysGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_SYS >=", value, "subSys");
            return this;
        }

        public Criteria andSubSysLessThan(String value) {
            addCriterion("SUB_SYS <", value, "subSys");
            return this;
        }

        public Criteria andSubSysLessThanOrEqualTo(String value) {
            addCriterion("SUB_SYS <=", value, "subSys");
            return this;
        }

        public Criteria andSubSysLike(String value) {
            addCriterion("SUB_SYS like", value, "subSys");
            return this;
        }

        public Criteria andSubSysNotLike(String value) {
            addCriterion("SUB_SYS not like", value, "subSys");
            return this;
        }

        public Criteria andSubSysIn(List values) {
            addCriterion("SUB_SYS in", values, "subSys");
            return this;
        }

        public Criteria andSubSysNotIn(List values) {
            addCriterion("SUB_SYS not in", values, "subSys");
            return this;
        }

        public Criteria andSubSysBetween(String value1, String value2) {
            addCriterion("SUB_SYS between", value1, value2, "subSys");
            return this;
        }

        public Criteria andSubSysNotBetween(String value1, String value2) {
            addCriterion("SUB_SYS not between", value1, value2, "subSys");
            return this;
        }

        public Criteria andParentMenuIsNull() {
            addCriterion("PARENT_MENU is null");
            return this;
        }

        public Criteria andParentMenuIsNotNull() {
            addCriterion("PARENT_MENU is not null");
            return this;
        }

        public Criteria andParentMenuEqualTo(String value) {
            addCriterion("PARENT_MENU =", value, "parentMenu");
            return this;
        }

        public Criteria andParentMenuNotEqualTo(String value) {
            addCriterion("PARENT_MENU <>", value, "parentMenu");
            return this;
        }

        public Criteria andParentMenuGreaterThan(String value) {
            addCriterion("PARENT_MENU >", value, "parentMenu");
            return this;
        }

        public Criteria andParentMenuGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_MENU >=", value, "parentMenu");
            return this;
        }

        public Criteria andParentMenuLessThan(String value) {
            addCriterion("PARENT_MENU <", value, "parentMenu");
            return this;
        }

        public Criteria andParentMenuLessThanOrEqualTo(String value) {
            addCriterion("PARENT_MENU <=", value, "parentMenu");
            return this;
        }

        public Criteria andParentMenuLike(String value) {
            addCriterion("PARENT_MENU like", value, "parentMenu");
            return this;
        }

        public Criteria andParentMenuNotLike(String value) {
            addCriterion("PARENT_MENU not like", value, "parentMenu");
            return this;
        }

        public Criteria andParentMenuIn(List values) {
            addCriterion("PARENT_MENU in", values, "parentMenu");
            return this;
        }

        public Criteria andParentMenuNotIn(List values) {
            addCriterion("PARENT_MENU not in", values, "parentMenu");
            return this;
        }

        public Criteria andParentMenuBetween(String value1, String value2) {
            addCriterion("PARENT_MENU between", value1, value2, "parentMenu");
            return this;
        }

        public Criteria andParentMenuNotBetween(String value1, String value2) {
            addCriterion("PARENT_MENU not between", value1, value2, "parentMenu");
            return this;
        }

        public Criteria andSubMenuIsNull() {
            addCriterion("SUB_MENU is null");
            return this;
        }

        public Criteria andSubMenuIsNotNull() {
            addCriterion("SUB_MENU is not null");
            return this;
        }

        public Criteria andSubMenuEqualTo(String value) {
            addCriterion("SUB_MENU =", value, "subMenu");
            return this;
        }

        public Criteria andSubMenuNotEqualTo(String value) {
            addCriterion("SUB_MENU <>", value, "subMenu");
            return this;
        }

        public Criteria andSubMenuGreaterThan(String value) {
            addCriterion("SUB_MENU >", value, "subMenu");
            return this;
        }

        public Criteria andSubMenuGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_MENU >=", value, "subMenu");
            return this;
        }

        public Criteria andSubMenuLessThan(String value) {
            addCriterion("SUB_MENU <", value, "subMenu");
            return this;
        }

        public Criteria andSubMenuLessThanOrEqualTo(String value) {
            addCriterion("SUB_MENU <=", value, "subMenu");
            return this;
        }

        public Criteria andSubMenuLike(String value) {
            addCriterion("SUB_MENU like", value, "subMenu");
            return this;
        }

        public Criteria andSubMenuNotLike(String value) {
            addCriterion("SUB_MENU not like", value, "subMenu");
            return this;
        }

        public Criteria andSubMenuIn(List values) {
            addCriterion("SUB_MENU in", values, "subMenu");
            return this;
        }

        public Criteria andSubMenuNotIn(List values) {
            addCriterion("SUB_MENU not in", values, "subMenu");
            return this;
        }

        public Criteria andSubMenuBetween(String value1, String value2) {
            addCriterion("SUB_MENU between", value1, value2, "subMenu");
            return this;
        }

        public Criteria andSubMenuNotBetween(String value1, String value2) {
            addCriterion("SUB_MENU not between", value1, value2, "subMenu");
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

        public Criteria andChgTypeIsNull() {
            addCriterion("CHG_TYPE is null");
            return this;
        }

        public Criteria andChgTypeIsNotNull() {
            addCriterion("CHG_TYPE is not null");
            return this;
        }

        public Criteria andChgTypeEqualTo(String value) {
            addCriterion("CHG_TYPE =", value, "chgType");
            return this;
        }

        public Criteria andChgTypeNotEqualTo(String value) {
            addCriterion("CHG_TYPE <>", value, "chgType");
            return this;
        }

        public Criteria andChgTypeGreaterThan(String value) {
            addCriterion("CHG_TYPE >", value, "chgType");
            return this;
        }

        public Criteria andChgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CHG_TYPE >=", value, "chgType");
            return this;
        }

        public Criteria andChgTypeLessThan(String value) {
            addCriterion("CHG_TYPE <", value, "chgType");
            return this;
        }

        public Criteria andChgTypeLessThanOrEqualTo(String value) {
            addCriterion("CHG_TYPE <=", value, "chgType");
            return this;
        }

        public Criteria andChgTypeLike(String value) {
            addCriterion("CHG_TYPE like", value, "chgType");
            return this;
        }

        public Criteria andChgTypeNotLike(String value) {
            addCriterion("CHG_TYPE not like", value, "chgType");
            return this;
        }

        public Criteria andChgTypeIn(List values) {
            addCriterion("CHG_TYPE in", values, "chgType");
            return this;
        }

        public Criteria andChgTypeNotIn(List values) {
            addCriterion("CHG_TYPE not in", values, "chgType");
            return this;
        }

        public Criteria andChgTypeBetween(String value1, String value2) {
            addCriterion("CHG_TYPE between", value1, value2, "chgType");
            return this;
        }

        public Criteria andChgTypeNotBetween(String value1, String value2) {
            addCriterion("CHG_TYPE not between", value1, value2, "chgType");
            return this;
        }

        public Criteria andOriginlIsNull() {
            addCriterion("ORIGINL is null");
            return this;
        }

        public Criteria andOriginlIsNotNull() {
            addCriterion("ORIGINL is not null");
            return this;
        }

        public Criteria andOriginlEqualTo(String value) {
            addCriterion("ORIGINL =", value, "originl");
            return this;
        }

        public Criteria andOriginlNotEqualTo(String value) {
            addCriterion("ORIGINL <>", value, "originl");
            return this;
        }

        public Criteria andOriginlGreaterThan(String value) {
            addCriterion("ORIGINL >", value, "originl");
            return this;
        }

        public Criteria andOriginlGreaterThanOrEqualTo(String value) {
            addCriterion("ORIGINL >=", value, "originl");
            return this;
        }

        public Criteria andOriginlLessThan(String value) {
            addCriterion("ORIGINL <", value, "originl");
            return this;
        }

        public Criteria andOriginlLessThanOrEqualTo(String value) {
            addCriterion("ORIGINL <=", value, "originl");
            return this;
        }

        public Criteria andOriginlLike(String value) {
            addCriterion("ORIGINL like", value, "originl");
            return this;
        }

        public Criteria andOriginlNotLike(String value) {
            addCriterion("ORIGINL not like", value, "originl");
            return this;
        }

        public Criteria andOriginlIn(List values) {
            addCriterion("ORIGINL in", values, "originl");
            return this;
        }

        public Criteria andOriginlNotIn(List values) {
            addCriterion("ORIGINL not in", values, "originl");
            return this;
        }

        public Criteria andOriginlBetween(String value1, String value2) {
            addCriterion("ORIGINL between", value1, value2, "originl");
            return this;
        }

        public Criteria andOriginlNotBetween(String value1, String value2) {
            addCriterion("ORIGINL not between", value1, value2, "originl");
            return this;
        }

        public Criteria andTargetIsNull() {
            addCriterion("TARGET is null");
            return this;
        }

        public Criteria andTargetIsNotNull() {
            addCriterion("TARGET is not null");
            return this;
        }

        public Criteria andTargetEqualTo(String value) {
            addCriterion("TARGET =", value, "target");
            return this;
        }

        public Criteria andTargetNotEqualTo(String value) {
            addCriterion("TARGET <>", value, "target");
            return this;
        }

        public Criteria andTargetGreaterThan(String value) {
            addCriterion("TARGET >", value, "target");
            return this;
        }

        public Criteria andTargetGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET >=", value, "target");
            return this;
        }

        public Criteria andTargetLessThan(String value) {
            addCriterion("TARGET <", value, "target");
            return this;
        }

        public Criteria andTargetLessThanOrEqualTo(String value) {
            addCriterion("TARGET <=", value, "target");
            return this;
        }

        public Criteria andTargetLike(String value) {
            addCriterion("TARGET like", value, "target");
            return this;
        }

        public Criteria andTargetNotLike(String value) {
            addCriterion("TARGET not like", value, "target");
            return this;
        }

        public Criteria andTargetIn(List values) {
            addCriterion("TARGET in", values, "target");
            return this;
        }

        public Criteria andTargetNotIn(List values) {
            addCriterion("TARGET not in", values, "target");
            return this;
        }

        public Criteria andTargetBetween(String value1, String value2) {
            addCriterion("TARGET between", value1, value2, "target");
            return this;
        }

        public Criteria andTargetNotBetween(String value1, String value2) {
            addCriterion("TARGET not between", value1, value2, "target");
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