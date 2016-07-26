package com.yly.manu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BimanufacinfotbExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public BimanufacinfotbExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    protected BimanufacinfotbExample(BimanufacinfotbExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
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
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table bimanufacinfotb
     *
     * @ibatorgenerated Tue Jul 26 14:53:31 CST 2016
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

        public Criteria andManufacIdIsNull() {
            addCriterion("ManufacId is null");
            return this;
        }

        public Criteria andManufacIdIsNotNull() {
            addCriterion("ManufacId is not null");
            return this;
        }

        public Criteria andManufacIdEqualTo(Short value) {
            addCriterion("ManufacId =", value, "manufacId");
            return this;
        }

        public Criteria andManufacIdNotEqualTo(Short value) {
            addCriterion("ManufacId <>", value, "manufacId");
            return this;
        }

        public Criteria andManufacIdGreaterThan(Short value) {
            addCriterion("ManufacId >", value, "manufacId");
            return this;
        }

        public Criteria andManufacIdGreaterThanOrEqualTo(Short value) {
            addCriterion("ManufacId >=", value, "manufacId");
            return this;
        }

        public Criteria andManufacIdLessThan(Short value) {
            addCriterion("ManufacId <", value, "manufacId");
            return this;
        }

        public Criteria andManufacIdLessThanOrEqualTo(Short value) {
            addCriterion("ManufacId <=", value, "manufacId");
            return this;
        }

        public Criteria andManufacIdIn(List values) {
            addCriterion("ManufacId in", values, "manufacId");
            return this;
        }

        public Criteria andManufacIdNotIn(List values) {
            addCriterion("ManufacId not in", values, "manufacId");
            return this;
        }

        public Criteria andManufacIdBetween(Short value1, Short value2) {
            addCriterion("ManufacId between", value1, value2, "manufacId");
            return this;
        }

        public Criteria andManufacIdNotBetween(Short value1, Short value2) {
            addCriterion("ManufacId not between", value1, value2, "manufacId");
            return this;
        }

        public Criteria andManufacnameIsNull() {
            addCriterion("Manufacname is null");
            return this;
        }

        public Criteria andManufacnameIsNotNull() {
            addCriterion("Manufacname is not null");
            return this;
        }

        public Criteria andManufacnameEqualTo(String value) {
            addCriterion("Manufacname =", value, "manufacname");
            return this;
        }

        public Criteria andManufacnameNotEqualTo(String value) {
            addCriterion("Manufacname <>", value, "manufacname");
            return this;
        }

        public Criteria andManufacnameGreaterThan(String value) {
            addCriterion("Manufacname >", value, "manufacname");
            return this;
        }

        public Criteria andManufacnameGreaterThanOrEqualTo(String value) {
            addCriterion("Manufacname >=", value, "manufacname");
            return this;
        }

        public Criteria andManufacnameLessThan(String value) {
            addCriterion("Manufacname <", value, "manufacname");
            return this;
        }

        public Criteria andManufacnameLessThanOrEqualTo(String value) {
            addCriterion("Manufacname <=", value, "manufacname");
            return this;
        }

        public Criteria andManufacnameLike(String value) {
            addCriterion("Manufacname like", value, "manufacname");
            return this;
        }

        public Criteria andManufacnameNotLike(String value) {
            addCriterion("Manufacname not like", value, "manufacname");
            return this;
        }

        public Criteria andManufacnameIn(List values) {
            addCriterion("Manufacname in", values, "manufacname");
            return this;
        }

        public Criteria andManufacnameNotIn(List values) {
            addCriterion("Manufacname not in", values, "manufacname");
            return this;
        }

        public Criteria andManufacnameBetween(String value1, String value2) {
            addCriterion("Manufacname between", value1, value2, "manufacname");
            return this;
        }

        public Criteria andManufacnameNotBetween(String value1, String value2) {
            addCriterion("Manufacname not between", value1, value2, "manufacname");
            return this;
        }

        public Criteria andManufacpersonIsNull() {
            addCriterion("Manufacperson is null");
            return this;
        }

        public Criteria andManufacpersonIsNotNull() {
            addCriterion("Manufacperson is not null");
            return this;
        }

        public Criteria andManufacpersonEqualTo(String value) {
            addCriterion("Manufacperson =", value, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonNotEqualTo(String value) {
            addCriterion("Manufacperson <>", value, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonGreaterThan(String value) {
            addCriterion("Manufacperson >", value, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonGreaterThanOrEqualTo(String value) {
            addCriterion("Manufacperson >=", value, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonLessThan(String value) {
            addCriterion("Manufacperson <", value, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonLessThanOrEqualTo(String value) {
            addCriterion("Manufacperson <=", value, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonLike(String value) {
            addCriterion("Manufacperson like", value, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonNotLike(String value) {
            addCriterion("Manufacperson not like", value, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonIn(List values) {
            addCriterion("Manufacperson in", values, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonNotIn(List values) {
            addCriterion("Manufacperson not in", values, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonBetween(String value1, String value2) {
            addCriterion("Manufacperson between", value1, value2, "manufacperson");
            return this;
        }

        public Criteria andManufacpersonNotBetween(String value1, String value2) {
            addCriterion("Manufacperson not between", value1, value2, "manufacperson");
            return this;
        }

        public Criteria andManufactelIsNull() {
            addCriterion("Manufactel is null");
            return this;
        }

        public Criteria andManufactelIsNotNull() {
            addCriterion("Manufactel is not null");
            return this;
        }

        public Criteria andManufactelEqualTo(String value) {
            addCriterion("Manufactel =", value, "manufactel");
            return this;
        }

        public Criteria andManufactelNotEqualTo(String value) {
            addCriterion("Manufactel <>", value, "manufactel");
            return this;
        }

        public Criteria andManufactelGreaterThan(String value) {
            addCriterion("Manufactel >", value, "manufactel");
            return this;
        }

        public Criteria andManufactelGreaterThanOrEqualTo(String value) {
            addCriterion("Manufactel >=", value, "manufactel");
            return this;
        }

        public Criteria andManufactelLessThan(String value) {
            addCriterion("Manufactel <", value, "manufactel");
            return this;
        }

        public Criteria andManufactelLessThanOrEqualTo(String value) {
            addCriterion("Manufactel <=", value, "manufactel");
            return this;
        }

        public Criteria andManufactelLike(String value) {
            addCriterion("Manufactel like", value, "manufactel");
            return this;
        }

        public Criteria andManufactelNotLike(String value) {
            addCriterion("Manufactel not like", value, "manufactel");
            return this;
        }

        public Criteria andManufactelIn(List values) {
            addCriterion("Manufactel in", values, "manufactel");
            return this;
        }

        public Criteria andManufactelNotIn(List values) {
            addCriterion("Manufactel not in", values, "manufactel");
            return this;
        }

        public Criteria andManufactelBetween(String value1, String value2) {
            addCriterion("Manufactel between", value1, value2, "manufactel");
            return this;
        }

        public Criteria andManufactelNotBetween(String value1, String value2) {
            addCriterion("Manufactel not between", value1, value2, "manufactel");
            return this;
        }

        public Criteria andManufacaddrIsNull() {
            addCriterion("Manufacaddr is null");
            return this;
        }

        public Criteria andManufacaddrIsNotNull() {
            addCriterion("Manufacaddr is not null");
            return this;
        }

        public Criteria andManufacaddrEqualTo(String value) {
            addCriterion("Manufacaddr =", value, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrNotEqualTo(String value) {
            addCriterion("Manufacaddr <>", value, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrGreaterThan(String value) {
            addCriterion("Manufacaddr >", value, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrGreaterThanOrEqualTo(String value) {
            addCriterion("Manufacaddr >=", value, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrLessThan(String value) {
            addCriterion("Manufacaddr <", value, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrLessThanOrEqualTo(String value) {
            addCriterion("Manufacaddr <=", value, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrLike(String value) {
            addCriterion("Manufacaddr like", value, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrNotLike(String value) {
            addCriterion("Manufacaddr not like", value, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrIn(List values) {
            addCriterion("Manufacaddr in", values, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrNotIn(List values) {
            addCriterion("Manufacaddr not in", values, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrBetween(String value1, String value2) {
            addCriterion("Manufacaddr between", value1, value2, "manufacaddr");
            return this;
        }

        public Criteria andManufacaddrNotBetween(String value1, String value2) {
            addCriterion("Manufacaddr not between", value1, value2, "manufacaddr");
            return this;
        }

        public Criteria andManufacfaxIsNull() {
            addCriterion("Manufacfax is null");
            return this;
        }

        public Criteria andManufacfaxIsNotNull() {
            addCriterion("Manufacfax is not null");
            return this;
        }

        public Criteria andManufacfaxEqualTo(String value) {
            addCriterion("Manufacfax =", value, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxNotEqualTo(String value) {
            addCriterion("Manufacfax <>", value, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxGreaterThan(String value) {
            addCriterion("Manufacfax >", value, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxGreaterThanOrEqualTo(String value) {
            addCriterion("Manufacfax >=", value, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxLessThan(String value) {
            addCriterion("Manufacfax <", value, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxLessThanOrEqualTo(String value) {
            addCriterion("Manufacfax <=", value, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxLike(String value) {
            addCriterion("Manufacfax like", value, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxNotLike(String value) {
            addCriterion("Manufacfax not like", value, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxIn(List values) {
            addCriterion("Manufacfax in", values, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxNotIn(List values) {
            addCriterion("Manufacfax not in", values, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxBetween(String value1, String value2) {
            addCriterion("Manufacfax between", value1, value2, "manufacfax");
            return this;
        }

        public Criteria andManufacfaxNotBetween(String value1, String value2) {
            addCriterion("Manufacfax not between", value1, value2, "manufacfax");
            return this;
        }

        public Criteria andOperIdIsNull() {
            addCriterion("OperId is null");
            return this;
        }

        public Criteria andOperIdIsNotNull() {
            addCriterion("OperId is not null");
            return this;
        }

        public Criteria andOperIdEqualTo(String value) {
            addCriterion("OperId =", value, "operId");
            return this;
        }

        public Criteria andOperIdNotEqualTo(String value) {
            addCriterion("OperId <>", value, "operId");
            return this;
        }

        public Criteria andOperIdGreaterThan(String value) {
            addCriterion("OperId >", value, "operId");
            return this;
        }

        public Criteria andOperIdGreaterThanOrEqualTo(String value) {
            addCriterion("OperId >=", value, "operId");
            return this;
        }

        public Criteria andOperIdLessThan(String value) {
            addCriterion("OperId <", value, "operId");
            return this;
        }

        public Criteria andOperIdLessThanOrEqualTo(String value) {
            addCriterion("OperId <=", value, "operId");
            return this;
        }

        public Criteria andOperIdLike(String value) {
            addCriterion("OperId like", value, "operId");
            return this;
        }

        public Criteria andOperIdNotLike(String value) {
            addCriterion("OperId not like", value, "operId");
            return this;
        }

        public Criteria andOperIdIn(List values) {
            addCriterion("OperId in", values, "operId");
            return this;
        }

        public Criteria andOperIdNotIn(List values) {
            addCriterion("OperId not in", values, "operId");
            return this;
        }

        public Criteria andOperIdBetween(String value1, String value2) {
            addCriterion("OperId between", value1, value2, "operId");
            return this;
        }

        public Criteria andOperIdNotBetween(String value1, String value2) {
            addCriterion("OperId not between", value1, value2, "operId");
            return this;
        }

        public Criteria andCurrDateIsNull() {
            addCriterion("CurrDate is null");
            return this;
        }

        public Criteria andCurrDateIsNotNull() {
            addCriterion("CurrDate is not null");
            return this;
        }

        public Criteria andCurrDateEqualTo(String value) {
            addCriterion("CurrDate =", value, "currDate");
            return this;
        }

        public Criteria andCurrDateNotEqualTo(String value) {
            addCriterion("CurrDate <>", value, "currDate");
            return this;
        }

        public Criteria andCurrDateGreaterThan(String value) {
            addCriterion("CurrDate >", value, "currDate");
            return this;
        }

        public Criteria andCurrDateGreaterThanOrEqualTo(String value) {
            addCriterion("CurrDate >=", value, "currDate");
            return this;
        }

        public Criteria andCurrDateLessThan(String value) {
            addCriterion("CurrDate <", value, "currDate");
            return this;
        }

        public Criteria andCurrDateLessThanOrEqualTo(String value) {
            addCriterion("CurrDate <=", value, "currDate");
            return this;
        }

        public Criteria andCurrDateLike(String value) {
            addCriterion("CurrDate like", value, "currDate");
            return this;
        }

        public Criteria andCurrDateNotLike(String value) {
            addCriterion("CurrDate not like", value, "currDate");
            return this;
        }

        public Criteria andCurrDateIn(List values) {
            addCriterion("CurrDate in", values, "currDate");
            return this;
        }

        public Criteria andCurrDateNotIn(List values) {
            addCriterion("CurrDate not in", values, "currDate");
            return this;
        }

        public Criteria andCurrDateBetween(String value1, String value2) {
            addCriterion("CurrDate between", value1, value2, "currDate");
            return this;
        }

        public Criteria andCurrDateNotBetween(String value1, String value2) {
            addCriterion("CurrDate not between", value1, value2, "currDate");
            return this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("Remarks is null");
            return this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("Remarks is not null");
            return this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("Remarks =", value, "remarks");
            return this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("Remarks <>", value, "remarks");
            return this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("Remarks >", value, "remarks");
            return this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("Remarks >=", value, "remarks");
            return this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("Remarks <", value, "remarks");
            return this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("Remarks <=", value, "remarks");
            return this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("Remarks like", value, "remarks");
            return this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("Remarks not like", value, "remarks");
            return this;
        }

        public Criteria andRemarksIn(List values) {
            addCriterion("Remarks in", values, "remarks");
            return this;
        }

        public Criteria andRemarksNotIn(List values) {
            addCriterion("Remarks not in", values, "remarks");
            return this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("Remarks between", value1, value2, "remarks");
            return this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("Remarks not between", value1, value2, "remarks");
            return this;
        }
    }
}