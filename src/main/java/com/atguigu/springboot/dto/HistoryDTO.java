package com.atguigu.springboot.dto;

import com.atguigu.springboot.entity.Course;
import lombok.Data;

import java.util.Date;

//并上Course

@Data
public class HistoryDTO {

    private Integer historyId;

    private Integer userId;

    private Date historyDate;

    private Integer courseId;

    private Integer dataId;

    private Course course;

}