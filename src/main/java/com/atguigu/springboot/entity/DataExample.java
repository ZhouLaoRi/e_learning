package com.atguigu.springboot.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataExample() {
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

        public Criteria andDataIdIsNull() {
            addCriterion("data_id is null");
            return (Criteria) this;
        }

        public Criteria andDataIdIsNotNull() {
            addCriterion("data_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataIdEqualTo(Integer value) {
            addCriterion("data_id =", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotEqualTo(Integer value) {
            addCriterion("data_id <>", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThan(Integer value) {
            addCriterion("data_id >", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_id >=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThan(Integer value) {
            addCriterion("data_id <", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThanOrEqualTo(Integer value) {
            addCriterion("data_id <=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdIn(List<Integer> values) {
            addCriterion("data_id in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotIn(List<Integer> values) {
            addCriterion("data_id not in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdBetween(Integer value1, Integer value2) {
            addCriterion("data_id between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotBetween(Integer value1, Integer value2) {
            addCriterion("data_id not between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataNameIsNull() {
            addCriterion("data_name is null");
            return (Criteria) this;
        }

        public Criteria andDataNameIsNotNull() {
            addCriterion("data_name is not null");
            return (Criteria) this;
        }

        public Criteria andDataNameEqualTo(String value) {
            addCriterion("data_name =", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotEqualTo(String value) {
            addCriterion("data_name <>", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameGreaterThan(String value) {
            addCriterion("data_name >", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameGreaterThanOrEqualTo(String value) {
            addCriterion("data_name >=", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLessThan(String value) {
            addCriterion("data_name <", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLessThanOrEqualTo(String value) {
            addCriterion("data_name <=", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLike(String value) {
            addCriterion("data_name like", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotLike(String value) {
            addCriterion("data_name not like", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameIn(List<String> values) {
            addCriterion("data_name in", values, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotIn(List<String> values) {
            addCriterion("data_name not in", values, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameBetween(String value1, String value2) {
            addCriterion("data_name between", value1, value2, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotBetween(String value1, String value2) {
            addCriterion("data_name not between", value1, value2, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataIntroIsNull() {
            addCriterion("data_intro is null");
            return (Criteria) this;
        }

        public Criteria andDataIntroIsNotNull() {
            addCriterion("data_intro is not null");
            return (Criteria) this;
        }

        public Criteria andDataIntroEqualTo(String value) {
            addCriterion("data_intro =", value, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroNotEqualTo(String value) {
            addCriterion("data_intro <>", value, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroGreaterThan(String value) {
            addCriterion("data_intro >", value, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroGreaterThanOrEqualTo(String value) {
            addCriterion("data_intro >=", value, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroLessThan(String value) {
            addCriterion("data_intro <", value, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroLessThanOrEqualTo(String value) {
            addCriterion("data_intro <=", value, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroLike(String value) {
            addCriterion("data_intro like", value, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroNotLike(String value) {
            addCriterion("data_intro not like", value, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroIn(List<String> values) {
            addCriterion("data_intro in", values, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroNotIn(List<String> values) {
            addCriterion("data_intro not in", values, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroBetween(String value1, String value2) {
            addCriterion("data_intro between", value1, value2, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataIntroNotBetween(String value1, String value2) {
            addCriterion("data_intro not between", value1, value2, "dataIntro");
            return (Criteria) this;
        }

        public Criteria andDataLevelIsNull() {
            addCriterion("data_level is null");
            return (Criteria) this;
        }

        public Criteria andDataLevelIsNotNull() {
            addCriterion("data_level is not null");
            return (Criteria) this;
        }

        public Criteria andDataLevelEqualTo(Integer value) {
            addCriterion("data_level =", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelNotEqualTo(Integer value) {
            addCriterion("data_level <>", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelGreaterThan(Integer value) {
            addCriterion("data_level >", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_level >=", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelLessThan(Integer value) {
            addCriterion("data_level <", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelLessThanOrEqualTo(Integer value) {
            addCriterion("data_level <=", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelIn(List<Integer> values) {
            addCriterion("data_level in", values, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelNotIn(List<Integer> values) {
            addCriterion("data_level not in", values, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelBetween(Integer value1, Integer value2) {
            addCriterion("data_level between", value1, value2, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("data_level not between", value1, value2, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(Integer value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(Integer value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(Integer value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(Integer value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(Integer value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<Integer> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<Integer> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(Integer value1, Integer value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataPathIsNull() {
            addCriterion("data_path is null");
            return (Criteria) this;
        }

        public Criteria andDataPathIsNotNull() {
            addCriterion("data_path is not null");
            return (Criteria) this;
        }

        public Criteria andDataPathEqualTo(String value) {
            addCriterion("data_path =", value, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathNotEqualTo(String value) {
            addCriterion("data_path <>", value, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathGreaterThan(String value) {
            addCriterion("data_path >", value, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathGreaterThanOrEqualTo(String value) {
            addCriterion("data_path >=", value, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathLessThan(String value) {
            addCriterion("data_path <", value, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathLessThanOrEqualTo(String value) {
            addCriterion("data_path <=", value, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathLike(String value) {
            addCriterion("data_path like", value, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathNotLike(String value) {
            addCriterion("data_path not like", value, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathIn(List<String> values) {
            addCriterion("data_path in", values, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathNotIn(List<String> values) {
            addCriterion("data_path not in", values, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathBetween(String value1, String value2) {
            addCriterion("data_path between", value1, value2, "dataPath");
            return (Criteria) this;
        }

        public Criteria andDataPathNotBetween(String value1, String value2) {
            addCriterion("data_path not between", value1, value2, "dataPath");
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

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(Integer value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(Integer value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(Integer value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(Integer value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(Integer value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<Integer> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<Integer> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(Integer value1, Integer value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
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