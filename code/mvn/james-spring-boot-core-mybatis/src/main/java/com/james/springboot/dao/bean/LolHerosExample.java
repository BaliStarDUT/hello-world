package com.james.springboot.dao.bean;

import java.util.ArrayList;
import java.util.List;

public class LolHerosExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LolHerosExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlIsNull() {
            addCriterion("headpic_url is null");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlIsNotNull() {
            addCriterion("headpic_url is not null");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlEqualTo(String value) {
            addCriterion("headpic_url =", value, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlNotEqualTo(String value) {
            addCriterion("headpic_url <>", value, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlGreaterThan(String value) {
            addCriterion("headpic_url >", value, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("headpic_url >=", value, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlLessThan(String value) {
            addCriterion("headpic_url <", value, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlLessThanOrEqualTo(String value) {
            addCriterion("headpic_url <=", value, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlLike(String value) {
            addCriterion("headpic_url like", value, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlNotLike(String value) {
            addCriterion("headpic_url not like", value, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlIn(List<String> values) {
            addCriterion("headpic_url in", values, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlNotIn(List<String> values) {
            addCriterion("headpic_url not in", values, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlBetween(String value1, String value2) {
            addCriterion("headpic_url between", value1, value2, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andHeadpicUrlNotBetween(String value1, String value2) {
            addCriterion("headpic_url not between", value1, value2, "headpicUrl");
            return (Criteria) this;
        }

        public Criteria andNameCnIsNull() {
            addCriterion("name_cn is null");
            return (Criteria) this;
        }

        public Criteria andNameCnIsNotNull() {
            addCriterion("name_cn is not null");
            return (Criteria) this;
        }

        public Criteria andNameCnEqualTo(String value) {
            addCriterion("name_cn =", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnNotEqualTo(String value) {
            addCriterion("name_cn <>", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnGreaterThan(String value) {
            addCriterion("name_cn >", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnGreaterThanOrEqualTo(String value) {
            addCriterion("name_cn >=", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnLessThan(String value) {
            addCriterion("name_cn <", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnLessThanOrEqualTo(String value) {
            addCriterion("name_cn <=", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnLike(String value) {
            addCriterion("name_cn like", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnNotLike(String value) {
            addCriterion("name_cn not like", value, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnIn(List<String> values) {
            addCriterion("name_cn in", values, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnNotIn(List<String> values) {
            addCriterion("name_cn not in", values, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnBetween(String value1, String value2) {
            addCriterion("name_cn between", value1, value2, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameCnNotBetween(String value1, String value2) {
            addCriterion("name_cn not between", value1, value2, "nameCn");
            return (Criteria) this;
        }

        public Criteria andNameEnIsNull() {
            addCriterion("name_en is null");
            return (Criteria) this;
        }

        public Criteria andNameEnIsNotNull() {
            addCriterion("name_en is not null");
            return (Criteria) this;
        }

        public Criteria andNameEnEqualTo(String value) {
            addCriterion("name_en =", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnNotEqualTo(String value) {
            addCriterion("name_en <>", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnGreaterThan(String value) {
            addCriterion("name_en >", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("name_en >=", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnLessThan(String value) {
            addCriterion("name_en <", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnLessThanOrEqualTo(String value) {
            addCriterion("name_en <=", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnLike(String value) {
            addCriterion("name_en like", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnNotLike(String value) {
            addCriterion("name_en not like", value, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnIn(List<String> values) {
            addCriterion("name_en in", values, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnNotIn(List<String> values) {
            addCriterion("name_en not in", values, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnBetween(String value1, String value2) {
            addCriterion("name_en between", value1, value2, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNameEnNotBetween(String value1, String value2) {
            addCriterion("name_en not between", value1, value2, "nameEn");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlIsNull() {
            addCriterion("sounds_url is null");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlIsNotNull() {
            addCriterion("sounds_url is not null");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlEqualTo(String value) {
            addCriterion("sounds_url =", value, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlNotEqualTo(String value) {
            addCriterion("sounds_url <>", value, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlGreaterThan(String value) {
            addCriterion("sounds_url >", value, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("sounds_url >=", value, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlLessThan(String value) {
            addCriterion("sounds_url <", value, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlLessThanOrEqualTo(String value) {
            addCriterion("sounds_url <=", value, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlLike(String value) {
            addCriterion("sounds_url like", value, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlNotLike(String value) {
            addCriterion("sounds_url not like", value, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlIn(List<String> values) {
            addCriterion("sounds_url in", values, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlNotIn(List<String> values) {
            addCriterion("sounds_url not in", values, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlBetween(String value1, String value2) {
            addCriterion("sounds_url between", value1, value2, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andSoundsUrlNotBetween(String value1, String value2) {
            addCriterion("sounds_url not between", value1, value2, "soundsUrl");
            return (Criteria) this;
        }

        public Criteria andStoryIsNull() {
            addCriterion("story is null");
            return (Criteria) this;
        }

        public Criteria andStoryIsNotNull() {
            addCriterion("story is not null");
            return (Criteria) this;
        }

        public Criteria andStoryEqualTo(String value) {
            addCriterion("story =", value, "story");
            return (Criteria) this;
        }

        public Criteria andStoryNotEqualTo(String value) {
            addCriterion("story <>", value, "story");
            return (Criteria) this;
        }

        public Criteria andStoryGreaterThan(String value) {
            addCriterion("story >", value, "story");
            return (Criteria) this;
        }

        public Criteria andStoryGreaterThanOrEqualTo(String value) {
            addCriterion("story >=", value, "story");
            return (Criteria) this;
        }

        public Criteria andStoryLessThan(String value) {
            addCriterion("story <", value, "story");
            return (Criteria) this;
        }

        public Criteria andStoryLessThanOrEqualTo(String value) {
            addCriterion("story <=", value, "story");
            return (Criteria) this;
        }

        public Criteria andStoryLike(String value) {
            addCriterion("story like", value, "story");
            return (Criteria) this;
        }

        public Criteria andStoryNotLike(String value) {
            addCriterion("story not like", value, "story");
            return (Criteria) this;
        }

        public Criteria andStoryIn(List<String> values) {
            addCriterion("story in", values, "story");
            return (Criteria) this;
        }

        public Criteria andStoryNotIn(List<String> values) {
            addCriterion("story not in", values, "story");
            return (Criteria) this;
        }

        public Criteria andStoryBetween(String value1, String value2) {
            addCriterion("story between", value1, value2, "story");
            return (Criteria) this;
        }

        public Criteria andStoryNotBetween(String value1, String value2) {
            addCriterion("story not between", value1, value2, "story");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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