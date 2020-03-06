package com.atguigu.springboot.service;

import com.atguigu.springboot.entity.*;
import com.atguigu.springboot.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {
    @Resource
    private CourseMapper courseMapper;

    public List<Course> selectByExample(CourseExample example){
        return courseMapper.selectByExample(example);
    }
    public int updateByPrimaryKey(Course course){
        return courseMapper.updateByPrimaryKey(course);
    }

    public int insert(Course course){
        return courseMapper.insert(course);
    }

    public int deleteByExample(CourseExample courseExample){
        return courseMapper.deleteByExample(courseExample);
    }

    public Course selectByPrimaryKey(Integer courseId){
        return courseMapper.selectByPrimaryKey(courseId);
    }
}
