package com.atguigu.springboot.dto;

import com.atguigu.springboot.entity.Course;
import com.atguigu.springboot.entity.Type;
import lombok.Data;

import java.util.List;

@Data
public class TypeAndCourseDTO {

    private Type type;

    private List<Course> courseList;

}
