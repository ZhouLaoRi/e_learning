package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.dto.CommentDTO;
import com.atguigu.springboot.entity.Comment;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.service.CommentService;
import com.atguigu.springboot.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
public class CommentHomeController {


    @Resource
    private CommentService commentService;

    @GetMapping("/comments/{courseId}")
    public String comments(@PathVariable Integer courseId, Model model) {
        List<CommentDTO> comments = commentService.getAllCommentDTO(courseId);
        model.addAttribute("comments", comments);
        return "home/data :: commentList";
    }

    @GetMapping("/comments/like/{commentId}")
    public String commentsLike(@PathVariable Integer commentId) {
        commentService.likeCommentDTO(commentId);
        return "home/data :: commentList";
    }



    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            /*commentVo.setAvatar(user.getAvatar());
            comment.setAvatar(avatar);*/
        }
        comment.setAvatar("/blog/images/1005-100x100.jpg");
        comment.setNickname("admin");//前端传进来了 但是是没用的
        comment.setCommentLike(0);
        comment.setCommentDate(new Date());

        commentService.saveComment(comment);
        return "redirect:/home/comments/" + comment.getCourseId();
    }



}
