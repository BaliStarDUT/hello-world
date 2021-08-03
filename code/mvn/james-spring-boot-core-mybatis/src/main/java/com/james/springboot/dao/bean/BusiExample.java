package com.james.springboot.dao.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BusiExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBusiIdIsNull() {
            addCriterion("busi_id is null");
            return (Criteria) this;
        }

        public Criteria andBusiIdIsNotNull() {
            addCriterion("busi_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusiIdEqualTo(String value) {
            addCriterion("busi_id =", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdNotEqualTo(String value) {
            addCriterion("busi_id <>", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdGreaterThan(String value) {
            addCriterion("busi_id >", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdGreaterThanOrEqualTo(String value) {
            addCriterion("busi_id >=", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdLessThan(String value) {
            addCriterion("busi_id <", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdLessThanOrEqualTo(String value) {
            addCriterion("busi_id <=", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdLike(String value) {
            addCriterion("busi_id like", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdNotLike(String value) {
            addCriterion("busi_id not like", value, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdIn(List<String> values) {
            addCriterion("busi_id in", values, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdNotIn(List<String> values) {
            addCriterion("busi_id not in", values, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdBetween(String value1, String value2) {
            addCriterion("busi_id between", value1, value2, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiIdNotBetween(String value1, String value2) {
            addCriterion("busi_id not between", value1, value2, "busiId");
            return (Criteria) this;
        }

        public Criteria andBusiCodeIsNull() {
            addCriterion("busi_code is null");
            return (Criteria) this;
        }

        public Criteria andBusiCodeIsNotNull() {
            addCriterion("busi_code is not null");
            return (Criteria) this;
        }

        public Criteria andBusiCodeEqualTo(String value) {
            addCriterion("busi_code =", value, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeNotEqualTo(String value) {
            addCriterion("busi_code <>", value, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeGreaterThan(String value) {
            addCriterion("busi_code >", value, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeGreaterThanOrEqualTo(String value) {
            addCriterion("busi_code >=", value, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeLessThan(String value) {
            addCriterion("busi_code <", value, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeLessThanOrEqualTo(String value) {
            addCriterion("busi_code <=", value, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeLike(String value) {
            addCriterion("busi_code like", value, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeNotLike(String value) {
            addCriterion("busi_code not like", value, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeIn(List<String> values) {
            addCriterion("busi_code in", values, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeNotIn(List<String> values) {
            addCriterion("busi_code not in", values, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeBetween(String value1, String value2) {
            addCriterion("busi_code between", value1, value2, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiCodeNotBetween(String value1, String value2) {
            addCriterion("busi_code not between", value1, value2, "busiCode");
            return (Criteria) this;
        }

        public Criteria andBusiNameIsNull() {
            addCriterion("busi_name is null");
            return (Criteria) this;
        }

        public Criteria andBusiNameIsNotNull() {
            addCriterion("busi_name is not null");
            return (Criteria) this;
        }

        public Criteria andBusiNameEqualTo(String value) {
            addCriterion("busi_name =", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameNotEqualTo(String value) {
            addCriterion("busi_name <>", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameGreaterThan(String value) {
            addCriterion("busi_name >", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameGreaterThanOrEqualTo(String value) {
            addCriterion("busi_name >=", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameLessThan(String value) {
            addCriterion("busi_name <", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameLessThanOrEqualTo(String value) {
            addCriterion("busi_name <=", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameLike(String value) {
            addCriterion("busi_name like", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameNotLike(String value) {
            addCriterion("busi_name not like", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameIn(List<String> values) {
            addCriterion("busi_name in", values, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameNotIn(List<String> values) {
            addCriterion("busi_name not in", values, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameBetween(String value1, String value2) {
            addCriterion("busi_name between", value1, value2, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameNotBetween(String value1, String value2) {
            addCriterion("busi_name not between", value1, value2, "busiName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeIsNull() {
            addCriterion("cmos_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeIsNotNull() {
            addCriterion("cmos_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeEqualTo(Date value) {
            addCriterion("cmos_modify_time =", value, "cmosModifyTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeNotEqualTo(Date value) {
            addCriterion("cmos_modify_time <>", value, "cmosModifyTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeGreaterThan(Date value) {
            addCriterion("cmos_modify_time >", value, "cmosModifyTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cmos_modify_time >=", value, "cmosModifyTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeLessThan(Date value) {
            addCriterion("cmos_modify_time <", value, "cmosModifyTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("cmos_modify_time <=", value, "cmosModifyTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeIn(List<Date> values) {
            addCriterion("cmos_modify_time in", values, "cmosModifyTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeNotIn(List<Date> values) {
            addCriterion("cmos_modify_time not in", values, "cmosModifyTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeBetween(Date value1, Date value2) {
            addCriterion("cmos_modify_time between", value1, value2, "cmosModifyTime");
            return (Criteria) this;
        }

        public Criteria andCmosModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("cmos_modify_time not between", value1, value2, "cmosModifyTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}