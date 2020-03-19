package com.atguigu.springboot.mapper;

import com.atguigu.springboot.dto.CommentDTO;
import com.atguigu.springboot.entity.Comment;
import com.atguigu.springboot.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDTOMapper {

    public List<CommentDTO> getAllCommentDTO(@Param("courseId") Integer courseId);

    void likeCommentDTO(Integer commentId);

    void saveComment(Comment comment);
}
