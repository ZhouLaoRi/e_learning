package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.entity.UserLike;
import com.atguigu.springboot.service.CourseService;
import com.atguigu.springboot.service.UserLikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class CourseHomeController {
    @Resource
    private CourseService courseService;

    @Resource
    private UserLikeService userLikeService;


    @PostMapping("/courses/like")
    @ResponseBody
    public String courseLike(UserLike userLike, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if(user == null){
            return "-1";
        }
        userLike.setUserId(user.getUserId());

        UserLike oldUser = userLikeService.getUserLike(userLike);
        //点赞
        if(oldUser == null){
            courseService.likeCourse(userLike.getCommentId());
            userLikeService.insertByCourseId(userLike);
            //return "评论点赞成功";
            return "1";
        }
        //取消点赞
        userLikeService.deleteUserLike(userLike);
        courseService.dislikeCourse(userLike.getCommentId());
        //return "取消点赞成功";
        return "0";
    }
}
