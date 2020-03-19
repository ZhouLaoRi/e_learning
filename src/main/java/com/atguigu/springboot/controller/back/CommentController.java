package com.atguigu.springboot.controller.back;

import com.atguigu.springboot.entity.Comment;
import com.atguigu.springboot.entity.CommentExample;
import com.atguigu.springboot.service.CommentService;
import com.atguigu.springboot.vo.CommentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {


    @Resource
    private CommentService commentService;

    //查询所有评论返回列表页面
    @GetMapping("/comments")
    public String listComment(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model , Comment comment){

        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();

        PageHelper.startPage(pageNum, 10);
        List<Comment> comments = commentService.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(comments);
        model.addAttribute("pageInfo",pageInfo);
        return "comment/list";
    }

    //用来条件查询的 ：查询所有评论返回列表页面
    @PostMapping("/commentsAll")
    public String listAllComment(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum , CommentVo commentVo, Model model){

        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();

        if(commentVo.getUserId() != null && !"".equals(commentVo.getUserId() )){
            criteria.andUserIdEqualTo(commentVo.getUserId());
        }
        if(commentVo.getNickname() != null && !"".equals(commentVo.getNickname())){
            criteria.andNicknameEqualTo(commentVo.getNickname());
        }
        if(commentVo.getCourseId() != null && !"".equals(commentVo.getCourseId())){
            criteria.andCourseIdEqualTo(commentVo.getCourseId());
        }
        if(commentVo.getCommentDateBegin() != null && !"".equals(commentVo.getCommentDateBegin())
        &&commentVo.getCommentDateBegin() != null && !"".equals(commentVo.getCommentDateBegin())){
            criteria.andCommentDateBetween(commentVo.getCommentDateBegin(),commentVo.getCommentDateEnd());
        }

        PageHelper.startPage(pageNum, 10);
        List<Comment> comments = commentService.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(comments);
        model.addAttribute("pageInfo",pageInfo);
        return "comment/list :: CommentList";
    }


    //来到评论添加页面
    @RequestMapping("/comment")
    public String addCommentGuide(Comment comment,Model model){
        model.addAttribute("comments",commentService.selectByExample(new CommentExample()));
        return "comment/add";
    }

    //评论添加
    /*@PostMapping("/comment")
    public String addComment(Comment comment){
        comment.setCreateTime(new Date());
        commentService.insertSelective(comment);
        return "redirect:/comments";
    }*/

    //来到修改页面，查出当前，在页面回显
    @GetMapping("/comment/{id}")
    public String toEditPage(@PathVariable Integer id, Model model){
        model.addAttribute("comments",commentService.selectByExample(new CommentExample()));
        Comment comment = commentService.selectByPrimaryKey(id);
        model.addAttribute("comment",comment);
        return "comment/edit";
    }

    //评论修改:需要提交评论id;
    @PutMapping("/comment")
    public String updateComment(Comment comment){
        commentService.updateByPrimaryKeySelective(comment);
        return "redirect:/comments";
    }

    //评论删除
    @DeleteMapping("/comment/{id}")
    public String deleteComment(@PathVariable Integer id){
        commentService.deleteByPrimaryKey(id);
        return "redirect:/comments";
    }

    //评论恢复
    /*@PutMapping("/comment/recover/{id}")
    public String recoverComment(@PathVariable Integer commentId){
        commentService.recoverCommentByPrimaryKey(commentId);
        return "redirect:/comments";
    }*/




}
