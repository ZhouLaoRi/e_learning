package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.entity.*;
import com.atguigu.springboot.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
public class RecommendedCourseController {

    @Resource
    private CourseService courseService;



    //最新推荐
    @GetMapping("/showNewRecommended")
    public String showNewRecommended(Model model){

        //根据方向匹配对应的课程id
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria cri = courseExample.createCriteria();
        cri.andDeleteTimeIsNull();
        courseExample.setOrderByClause("update_time DESC,course_view DESC,course_like DESC");
        //单纯用来当作limit 0,10 的作用。。。
        PageHelper.startPage(0, 5);
        List<Course> newRecommendedCourses = courseService.selectByExample(courseExample);

        model.addAttribute("newRecommendedCourses",newRecommendedCourses);
        return "home/index :: newRecommendedCoursesList";
    }


    //相关推荐
    @PostMapping("/showRecommendedCourse")
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


    //个人推荐
    @GetMapping("/showPersonalRecommended")
    public String showPersonalRecommended(Model model ,HttpSession session ,
                                            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum){

        //想法1：根据用户的观看历史，获得所有course,再根据分类去划分区域，计算各个分类有的course，需要group by courseId?
        // 然后选择分类最大的，去推荐
        User user = (User) session.getAttribute("loginUser");

        if(user == null){
            return "home/personalHistory :: historyList";
        }

        Integer userId = user.getUserId();
        //得到的是一个typeId的 从数量多到少的序列
        List<Integer> typeIds = courseService.showPersonalRecommended(userId);

        //找出最多的分类id
        Integer typeId = typeIds.get(0);

        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria cri = courseExample.createCriteria();
        cri.andTypeIdEqualTo(typeId);
        // 更新时间最近，取近期的一个星期，由于数据较少，取一年吧。。。。
        Date date = new Date();//获取当前时间    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间
        //calendar.add(Calendar.MONTH,-1);//当前时间减去一个月，即一个月前的时间
        date = calendar.getTime();//获取一年前的时间，或者一个月前的时间

        cri.andUpdateTimeBetween(date,new Date());
        System.out.println("1年前" + date);

        //最多人看，点赞最多
        courseExample.setOrderByClause("course_view DESC,course_like DESC");
        //单纯用来当作limit 0,5 的作用。。。
        PageHelper.startPage(pageNum, 10);
        List<Course> courses = courseService.selectByExample(courseExample);
        PageInfo<Course> pageInfoRecommended = new PageInfo<Course>(courses);
        model.addAttribute("pageInfoRecommended",pageInfoRecommended);
        //return "home/personalRecommended :: personalRecommended";
        return "home/personalRecommended";
    }
}
