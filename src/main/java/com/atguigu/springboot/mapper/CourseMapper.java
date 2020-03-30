package com.atguigu.springboot.mapper;

import com.atguigu.springboot.entity.Course;
import com.atguigu.springboot.entity.CourseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    int countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer courseId);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer courseId);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> selectByQuery(@Param("query") String query);

    void recoverCourseByPrimaryKey(Integer courseId);

    void viewCourse(Integer courseId);

    void likeCourse(Integer courseId);

    List<Integer> showPersonalRecommended(Integer userId);

    void dislikeCourse(Integer courseId);
}