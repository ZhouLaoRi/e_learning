package com.atguigu.springboot.entity;

import java.util.Date;

public class Course {
    private Integer courseId;

    private String courseName;

    private String courseIntro;

    private String courseAuthor;

    private String courseAvatar;

    private Integer courseView;

    private Integer courseLike;

    private Integer courseLevel;

    private Integer typeId;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro == null ? null : courseIntro.trim();
    }

    public String getCourseAuthor() {
        return courseAuthor;
    }

    public void setCourseAuthor(String courseAuthor) {
        this.courseAuthor = courseAuthor == null ? null : courseAuthor.trim();
    }

    public String getCourseAvatar() {
        return courseAvatar;
    }

    public void setCourseAvatar(String courseAvatar) {
        this.courseAvatar = courseAvatar == null ? null : courseAvatar.trim();
    }

    public Integer getCourseView() {
        return courseView;
    }

    public void setCourseView(Integer courseView) {
        this.courseView = courseView;
    }

    public Integer getCourseLike() {
        return courseLike;
    }

    public void setCourseLike(Integer courseLike) {
        this.courseLike = courseLike;
    }

    public Integer getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(Integer courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}