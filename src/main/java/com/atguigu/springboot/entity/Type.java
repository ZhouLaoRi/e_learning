package com.atguigu.springboot.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Type {

    private Integer id;
    private String name;
    private Integer total;  //该分类的课程 总个数


    private Date createTime;

    private Date deleteTime;

}
