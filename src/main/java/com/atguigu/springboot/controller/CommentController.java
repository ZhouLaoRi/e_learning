package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.Comment;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {
    @Resource
    private CommentService commentService;

    @RequestMapping("/addComment")
    public String addComment(String commentText, HttpSession session, Integer dataId){
        Comment comment = new Comment();
        User user = (User) session.getAttribute("user");
        comment.setCommentDate(new Date());
        comment.setCommentText(commentText);
        comment.setDataId(dataId);
        //comment.setDataId(1);
        commentService.insert(comment);
        return "redirect:/showDataById?dataId="+dataId;
    }
}
