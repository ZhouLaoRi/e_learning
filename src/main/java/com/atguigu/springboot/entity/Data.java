package com.atguigu.springboot.entity;

import java.util.Date;

public class Data {
    private Integer dataId;

    private String dataName;

    private String dataIntro;

    private Integer dataLevel;

    //1为视频，2为音频，3为文件
    private Integer dataType;

    private String dataPath;

    private Date createTime;

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

    public String getDataIntro() {
        return dataIntro;
    }

    public void setDataIntro(String dataIntro) {
        this.dataIntro = dataIntro == null ? null : dataIntro.trim();
    }

    public Integer getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(Integer dataLevel) {
        this.dataLevel = dataLevel;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath == null ? null : dataPath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}