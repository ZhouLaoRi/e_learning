package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.dto.CommentDTO;
import com.atguigu.springboot.entity.Comment;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.entity.UserLike;
import com.atguigu.springboot.service.CommentService;
import com.atguigu.springboot.service.UserLikeService;
import com.atguigu.springboot.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
public class CommentHomeController {

    @Resource
    private CommentService commentService;

    @Resource
    private UserLikeService userLikeService;

    @GetMapping("/comments/{courseId}")
    public String comments(@PathVariable Integer courseId, Model model) {
        List<CommentDTO> comments = commentService.getAllCommentDTO(courseId);
        model.addAttribute("comments", comments);
        return "home/data :: commentList";
    }

    /*@GetMapping("/comments/like/{commentId}")
    public String commentsLike(@PathVariable Integer commentId) {
        commentService.likeCommentDTO(commentId);
        return "home/data :: commentList";
    }*/

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {

        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            //不能评论
        }

        //comment.setAvatar("/blog/images/1005-100x100.jpg");
        //comment.setNickname("admin");//前端传进来了 但是是没用的
        comment.setAvatar(user.getAvatar());
        comment.setNickname(user.getUserName());
        comment.setCommentLike(0);
        comment.setCommentDate(new Date());

        commentService.saveComment(comment);
        return "redirect:/home/comments/" + comment.getCourseId();
    }

    @PostMapping("/comments/like")
    @ResponseBody
    public boolean commentsLike(UserLike userLike) {
        UserLike oldUser = userLikeService.getUserLike(userLike);
        //点赞
        if(oldUser == null){
            commentService.likeCommentDTO(userLike.getCommentId());
            userLikeService.insertByCommentId(userLike);
            //return "评论点赞成功";
            return true;
        }
        //取消点赞
        userLikeService.deleteUserLike(userLike);
        commentService.dislikeCommentDTO(userLike.getCommentId());
        //return "取消点赞成功";
        return false;
    }


}
