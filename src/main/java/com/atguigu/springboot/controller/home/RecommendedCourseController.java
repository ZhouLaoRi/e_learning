package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.entity.*;
import com.atguigu.springboot.service.CourseService;
import com.atguigu.springboot.service.DataService;
import com.atguigu.springboot.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class RecommendedCourseController {
    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;
    @Resource
    private DataService dataService;


    //最新推荐
    @RequestMapping("/showNewRecommended")
    public String showNewRecommended(Model model){

        //根据方向匹配对应的课程id
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria cri = courseExample.createCriteria();
        cri.andDeleteTimeIsNull();
        courseExample.setOrderByClause("update_time DESC,course_view DESC,course_like DESC");
        //单纯用来当作limit 0,10 的作用。。。
        PageHelper.startPage(0, 10);
        List<Course> newRecommendedCourses = courseService.selectByExample(courseExample);

        model.addAttribute("newRecommendedCourses",newRecommendedCourses);
        return "home/index :: newRecommendedCoursesList";
    }


    //相关推荐
    @RequestMapping("/showRecommendedCourse")
    public String showRecommendedCourse(Model model,Integer courseId){

        //想法1：根据用户的观看历史，获得所有course,再根据分类去划分区域，然后选择分类最大的，去推荐

        //想法2：都已经在看视频了，那么我们就用这个分类，去直接搜索最高热度的就好了，搜索5个 嘻嘻。 感觉这个好点，也不用用户资料

        //先找出该分类id
        Course course = courseService.selectByPrimaryKey(courseId);
        Integer typeId = course.getTypeId();

        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria cri = courseExample.createCriteria();
        cri.andTypeIdEqualTo(typeId);
        //最多人看，点赞最多，更新时间最近
        courseExample.setOrderByClause("course_view DESC,course_like DESC,update_time DESC");
        //单纯用来当作limit 0,5 的作用。。。
        PageHelper.startPage(0, 5);
        List<Course> recommendedCourses = courseService.selectByExample(courseExample);
        model.addAttribute("recommendedCourses",recommendedCourses);
        return "home/data :: recommendedCoursesList";
    }
}
