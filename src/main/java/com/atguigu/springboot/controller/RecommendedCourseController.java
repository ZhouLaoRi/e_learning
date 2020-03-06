package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.*;
import com.atguigu.springboot.service.CourseService;
import com.atguigu.springboot.service.DataService;
import com.atguigu.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RecommendedCourseController {
    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;
    @Resource
    private DataService dataService;

    @RequestMapping("/showRecommendedCourse")
    public String showRecommendedCourse(Model model, HttpSession session){
        //获得用户选择的方向
        User user = (User) session.getAttribute("user");
        String userDirection = null;
        if (user == null || user.getUserDirection() == null) {
            userDirection = "-1";
            return "recommendedCourse";
        } else {
            userDirection = user.getUserDirection();
        }

        //根据方向匹配对应的课程id
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria cri = courseExample.createCriteria();
        cri.andCourseNameEqualTo(userDirection);
        List<Course> courses = courseService.selectByExample(courseExample);
        Integer courseId = courses.get(0).getCourseId();

        DataExample dataExample = new DataExample();
        DataExample.Criteria dataCri = dataExample.createCriteria();
        dataCri.andCourseIdEqualTo(courseId);
        List<Data> dataRecommended = dataService.selectByExample(dataExample);
        model.addAttribute("dataRecommended",dataRecommended);
        return "recommendedCourse";
    }
    /*public String findHistory(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        HistoryExample historyExample = new HistoryExample();
        HistoryExample.Criteria cri = historyExample.createCriteria();
        cri.andUserIdEqualTo(userId);
        List<History> histories = historyService.selectByExample(historyExample);
        model.addAttribute("histories",histories);
        return "showHistory";
    }*/

    //根据userDirection去获取
    /*public String showRecommendedCourse(Model model,String userDirection){

        *//*CourseExample courseExample = new CourseExample();
        CourseExample.Criteria cri = courseExample.createCriteria();
        cri.andCourseNameEqualTo(userDirection);
        List<Course> courses = courseService.selectByExample(courseExample);
        Integer courseId = courses.get(0).getCourseId();*//*

        System.out.println(userDirection);
        Integer courseId = 0;
        if(userDirection.equals("python")){
            courseId = 1;
        }else {
            courseId = 2;
        }
        DataExample dataExample = new DataExample();
        DataExample.Criteria dataCri = dataExample.createCriteria();
        dataCri.andCourseIdEqualTo(courseId);
        List<Data> dataRecommended = dataService.selectByExample(dataExample);
        model.addAttribute("dataRecommended",dataRecommended);
        return "recommendedCourse";
    }*/
}
