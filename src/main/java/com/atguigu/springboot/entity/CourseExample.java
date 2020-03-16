package com.atguigu.springboot.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
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

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseIntroIsNull() {
            addCriterion("course_intro is null");
            return (Criteria) this;
        }

        public Criteria andCourseIntroIsNotNull() {
            addCriterion("course_intro is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIntroEqualTo(String value) {
            addCriterion("course_intro =", value, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroNotEqualTo(String value) {
            addCriterion("course_intro <>", value, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroGreaterThan(String value) {
            addCriterion("course_intro >", value, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroGreaterThanOrEqualTo(String value) {
            addCriterion("course_intro >=", value, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroLessThan(String value) {
            addCriterion("course_intro <", value, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroLessThanOrEqualTo(String value) {
            addCriterion("course_intro <=", value, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroLike(String value) {
            addCriterion("course_intro like", value, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroNotLike(String value) {
            addCriterion("course_intro not like", value, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroIn(List<String> values) {
            addCriterion("course_intro in", values, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroNotIn(List<String> values) {
            addCriterion("course_intro not in", values, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroBetween(String value1, String value2) {
            addCriterion("course_intro between", value1, value2, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseIntroNotBetween(String value1, String value2) {
            addCriterion("course_intro not between", value1, value2, "courseIntro");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorIsNull() {
            addCriterion("course_author is null");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorIsNotNull() {
            addCriterion("course_author is not null");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorEqualTo(String value) {
            addCriterion("course_author =", value, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorNotEqualTo(String value) {
            addCriterion("course_author <>", value, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorGreaterThan(String value) {
            addCriterion("course_author >", value, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("course_author >=", value, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorLessThan(String value) {
            addCriterion("course_author <", value, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorLessThanOrEqualTo(String value) {
            addCriterion("course_author <=", value, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorLike(String value) {
            addCriterion("course_author like", value, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorNotLike(String value) {
            addCriterion("course_author not like", value, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorIn(List<String> values) {
            addCriterion("course_author in", values, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorNotIn(List<String> values) {
            addCriterion("course_author not in", values, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorBetween(String value1, String value2) {
            addCriterion("course_author between", value1, value2, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAuthorNotBetween(String value1, String value2) {
            addCriterion("course_author not between", value1, value2, "courseAuthor");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarIsNull() {
            addCriterion("course_avatar is null");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarIsNotNull() {
            addCriterion("course_avatar is not null");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarEqualTo(String value) {
            addCriterion("course_avatar =", value, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarNotEqualTo(String value) {
            addCriterion("course_avatar <>", value, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarGreaterThan(String value) {
            addCriterion("course_avatar >", value, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("course_avatar >=", value, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarLessThan(String value) {
            addCriterion("course_avatar <", value, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarLessThanOrEqualTo(String value) {
            addCriterion("course_avatar <=", value, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarLike(String value) {
            addCriterion("course_avatar like", value, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarNotLike(String value) {
            addCriterion("course_avatar not like", value, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarIn(List<String> values) {
            addCriterion("course_avatar in", values, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarNotIn(List<String> values) {
            addCriterion("course_avatar not in", values, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarBetween(String value1, String value2) {
            addCriterion("course_avatar between", value1, value2, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseAvatarNotBetween(String value1, String value2) {
            addCriterion("course_avatar not between", value1, value2, "courseAvatar");
            return (Criteria) this;
        }

        public Criteria andCourseViewIsNull() {
            addCriterion("course_view is null");
            return (Criteria) this;
        }

        public Criteria andCourseViewIsNotNull() {
            addCriterion("course_view is not null");
            return (Criteria) this;
        }

        public Criteria andCourseViewEqualTo(Integer value) {
            addCriterion("course_view =", value, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseViewNotEqualTo(Integer value) {
            addCriterion("course_view <>", value, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseViewGreaterThan(Integer value) {
            addCriterion("course_view >", value, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseViewGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_view >=", value, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseViewLessThan(Integer value) {
            addCriterion("course_view <", value, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseViewLessThanOrEqualTo(Integer value) {
            addCriterion("course_view <=", value, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseViewIn(List<Integer> values) {
            addCriterion("course_view in", values, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseViewNotIn(List<Integer> values) {
            addCriterion("course_view not in", values, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseViewBetween(Integer value1, Integer value2) {
            addCriterion("course_view between", value1, value2, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseViewNotBetween(Integer value1, Integer value2) {
            addCriterion("course_view not between", value1, value2, "courseView");
            return (Criteria) this;
        }

        public Criteria andCourseLikeIsNull() {
            addCriterion("course_like is null");
            return (Criteria) this;
        }

        public Criteria andCourseLikeIsNotNull() {
            addCriterion("course_like is not null");
            return (Criteria) this;
        }

        public Criteria andCourseLikeEqualTo(Integer value) {
            addCriterion("course_like =", value, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLikeNotEqualTo(Integer value) {
            addCriterion("course_like <>", value, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLikeGreaterThan(Integer value) {
            addCriterion("course_like >", value, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLikeGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_like >=", value, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLikeLessThan(Integer value) {
            addCriterion("course_like <", value, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLikeLessThanOrEqualTo(Integer value) {
            addCriterion("course_like <=", value, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLikeIn(List<Integer> values) {
            addCriterion("course_like in", values, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLikeNotIn(List<Integer> values) {
            addCriterion("course_like not in", values, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLikeBetween(Integer value1, Integer value2) {
            addCriterion("course_like between", value1, value2, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLikeNotBetween(Integer value1, Integer value2) {
            addCriterion("course_like not between", value1, value2, "courseLike");
            return (Criteria) this;
        }

        public Criteria andCourseLevelIsNull() {
            addCriterion("course_level is null");
            return (Criteria) this;
        }

        public Criteria andCourseLevelIsNotNull() {
            addCriterion("course_level is not null");
            return (Criteria) this;
        }

        public Criteria andCourseLevelEqualTo(Integer value) {
            addCriterion("course_level =", value, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andCourseLevelNotEqualTo(Integer value) {
            addCriterion("course_level <>", value, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andCourseLevelGreaterThan(Integer value) {
            addCriterion("course_level >", value, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andCourseLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_level >=", value, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andCourseLevelLessThan(Integer value) {
            addCriterion("course_level <", value, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andCourseLevelLessThanOrEqualTo(Integer value) {
            addCriterion("course_level <=", value, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andCourseLevelIn(List<Integer> values) {
            addCriterion("course_level in", values, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andCourseLevelNotIn(List<Integer> values) {
            addCriterion("course_level not in", values, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andCourseLevelBetween(Integer value1, Integer value2) {
            addCriterion("course_level between", value1, value2, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andCourseLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("course_level not between", value1, value2, "courseLevel");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNull() {
            addCriterion("delete_time is null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNotNull() {
            addCriterion("delete_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeEqualTo(Date value) {
            addCriterion("delete_time =", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotEqualTo(Date value) {
            addCriterion("delete_time <>", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThan(Date value) {
            addCriterion("delete_time >", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("delete_time >=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThan(Date value) {
            addCriterion("delete_time <", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("delete_time <=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIn(List<Date> values) {
            addCriterion("delete_time in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotIn(List<Date> values) {
            addCriterion("delete_time not in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeBetween(Date value1, Date value2) {
            addCriterion("delete_time between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("delete_time not between", value1, value2, "deleteTime");
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