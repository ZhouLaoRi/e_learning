package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.entity.Course;
import com.atguigu.springboot.entity.CourseExample;
import com.atguigu.springboot.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
public class TypeHomeController {

    @Resource
    private CourseService courseService;

    //显示分类
    //@PostMapping("/showType")
    @GetMapping("/showType/{typeId}/{pageNum}")
    public String showNewRecommended(@PathVariable(value = "typeId")  Integer typeId ,
                                     @PathVariable(value = "pageNum") Integer pageNum ,Model model){
    //public String showNewRecommended(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,@RequestParam(value = "typeId") Integer typeId ,Model model){

        //根据方向匹配对应的课程id
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria cri = courseExample.createCriteria();
        cri.andDeleteTimeIsNull();
        cri.andTypeIdEqualTo(typeId);

        courseExample.setOrderByClause("course_view DESC,course_like DESC,course_Id");

        //单纯用来当作limit 0,10 的作用。。。
        PageHelper.startPage(pageNum, 10);
        List<Course> courses = courseService.selectByExample(courseExample);
        PageInfo<Course> typeCourses = new PageInfo<Course>(courses);

        model.addAttribute("typeCourses",typeCourses);
        return "home/type";
    }
}
