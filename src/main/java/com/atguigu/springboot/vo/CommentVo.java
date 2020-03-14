package com.atguigu.springboot.vo;

import lombok.Data;

import java.util.Date;


@Data
public class CommentVo {

    private Integer userId;

    private Integer parentCommentId;

    private String commentText;

    private String nickname;

    private Integer courseId;


    private String avatar;
    private Date commentDate;
    private Integer like;
    private Integer commentID;
}
