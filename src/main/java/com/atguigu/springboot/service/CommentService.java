package com.atguigu.springboot.service;

import com.atguigu.springboot.entity.Comment;
import com.atguigu.springboot.entity.CommentExample;
import com.atguigu.springboot.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;

    public List<Comment> selectByExample(CommentExample commentExample){
        return commentMapper.selectByExample(commentExample);
    }

    public int insert(Comment comment){
        return commentMapper.insert(comment);
    }
}
