package com.atguigu.springboot.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Integer commentId;

    private Integer userId;

    private String nickname;

    private String avatar;

    private Date commentDate;

    private String commentText;

    private Integer commentLike;

    private Integer parentCommentId;

    private Integer courseId;
}