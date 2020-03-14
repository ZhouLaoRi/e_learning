package com.atguigu.springboot.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by limi on 2017/10/14.
 */
@Data
public class CommentDTO {

    private Integer commentId;

    private Integer userId;

    private Date commentDate;

    private String commentText;

    //点赞的数量
    private Integer Like;

    //是不是博主
    private boolean adminComment;

    //回复节点
    private List<CommentDTO> replyComments = new ArrayList<>();

    //父节点，用来保存@某人  这个符号 ,不需要parentId  直接就是父节点
    private CommentDTO parentComment;



}
