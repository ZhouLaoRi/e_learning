package com.atguigu.springboot.entity;

import lombok.Data;

@Data
public class UserLike {

    private Integer id;

    private Integer userId;

    private Integer courseId;

    private Integer commentId;

}
