package com.atguigu.springboot.service;

import com.atguigu.springboot.dto.CommentDTO;
import com.atguigu.springboot.entity.Comment;
import com.atguigu.springboot.entity.CommentExample;
import com.atguigu.springboot.mapper.CommentDTOMapper;
import com.atguigu.springboot.mapper.CommentMapper;
import com.atguigu.springboot.vo.CommentVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Resource
    private CommentDTOMapper commentDTOMapper;

    public List<Comment> selectByExample(CommentExample commentExample){
        return commentMapper.selectByExample(commentExample);
    }

    public List<CommentDTO> getAllCommentDTO(Integer courseId){
        return commentDTOMapper.getAllCommentDTO(courseId);
    }
    public int insert(Comment comment){
        return commentMapper.insert(comment);
    }

    public void likeCommentDTO(Integer commentId) {
        commentDTOMapper.likeCommentDTO(commentId);
    }

    public void saveComment(Comment comment) {
        commentDTOMapper.saveComment(comment);
    }

    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    public void updateByPrimaryKeySelective(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    public void deleteByPrimaryKey(Integer commentId) {
        commentMapper.deleteByPrimaryKey(commentId);
    }

    public void dislikeCommentDTO(Integer commentId) {
        commentDTOMapper.dislikeCommentDTO(commentId);
    }
}
