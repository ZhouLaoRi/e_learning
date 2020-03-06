package com.atguigu.springboot.entity;

public class Data {
    private Integer dataId;

    private String dataName;

    private String dataPath;

    private Integer dataLevel;

    private String dataIntro;

    private Integer courseId;

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath == null ? null : dataPath.trim();
    }

    public Integer getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(Integer dataLevel) {
        this.dataLevel = dataLevel;
    }

    public String getDataIntro() {
        return dataIntro;
    }

    public void setDataIntro(String dataIntro) {
        this.dataIntro = dataIntro == null ? null : dataIntro.trim();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}