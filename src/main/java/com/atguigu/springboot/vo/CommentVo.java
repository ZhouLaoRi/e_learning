package com.atguigu.springboot.vo;

import lombok.Data;

import java.util.Date;


@Data
public class CommentVo {

    private Integer userId;

    private String nickname;

    private Integer courseId;

    private Date commentDateBegin;

    private Date commentDateEnd;
}
