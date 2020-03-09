package com.atguigu.springboot.controller.back;

import com.atguigu.springboot.entity.Course;
import com.atguigu.springboot.entity.CourseExample;
import com.atguigu.springboot.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CourseController {

    @Resource
    private CourseService courseService;

    //查询所有课程返回列表页面
    @GetMapping("/courses")
    public String listCourse(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,Model model ,Course course){

        CourseExample courseExample = new CourseExample();
        if(course.getCourseId() != null || course.getCourseName() != null){
            CourseExample.Criteria criteria = courseExample.createCriteria();
            criteria.andCourseIdEqualTo(course.getCourseId());
            criteria.andCourseNameEqualTo(course.getCourseName());
        }
        PageHelper.startPage(pageNum, 5);
        List<Course> courses = courseService.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<Course>(courses);
        model.addAttribute("courses",courses);
        model.addAttribute("pageInfo",pageInfo);
        return "course/list";
    }

    //查询所有课程返回列表页面
    @PostMapping("/coursesAllBlog")
    public String listCoursesAllBlog(@Param(value = "pageNum") Integer pageNum, Model model , Course course){

        if(pageNum == null) pageNum = 1;

        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        if(course.getCourseId() != null ){
            criteria.andCourseIdEqualTo(course.getCourseId());
        }
        if(course.getCourseName() != null){
            criteria.andCourseNameEqualTo(course.getCourseName());
        }
        PageHelper.startPage(pageNum, 5);
        List<Course> courses = courseService.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<Course>(courses);
        model.addAttribute("courses",courses);
        model.addAttribute("pageInfo",pageInfo);
        return "course/list :: CourseList";
    }

    //用来条件查询的 ：查询所有课程返回列表页面
    @ResponseBody
    @GetMapping("/coursesAll")
    public PageInfo<Course> listAllCourse(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum ,Course course){

        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        if(course.getCourseId() != null ){
            criteria.andCourseIdEqualTo(course.getCourseId());
        }
        if(course.getCourseName() != null){
            criteria.andCourseNameEqualTo(course.getCourseName());
        }
        PageHelper.startPage(pageNum, 5);
        List<Course> courses = courseService.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<Course>(courses);
        return pageInfo;
    }


    //来到课程添加页面
    @RequestMapping("/course")
    public String addCourseGuide(Course course){
        return "course/add";
    }

    //课程添加
    @PostMapping("/course")
    public String addCourse(Course course){
        courseService.insert(course);
        return "redirect:/courses";
    }

    //来到修改页面，查出当前，在页面回显
    @GetMapping("/course/{id}")
    public String toEditPage(@PathVariable Integer id, Model model){
        Course course = courseService.selectByPrimaryKey(id);
        model.addAttribute("course",course);
        return "course/edit";
    }

    //课程修改:需要提交课程id;
    @PutMapping("/course")
    public String updateCourse(Course course){
        courseService.updateByPrimaryKey(course);
        return "redirect:/courses";
    }

    //课程删除
    @DeleteMapping("/course/{id}")
    public String deleteCourse(@PathVariable Integer id){
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria cri = courseExample.createCriteria();
        cri.andCourseIdEqualTo(id);
        courseService.deleteByExample(courseExample);
        return "redirect:/courses";
    }
}
