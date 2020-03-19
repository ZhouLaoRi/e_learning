package com.atguigu.springboot.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryVo {

    private Integer userId;

    private Integer courseId;

    private Date historyDateBegin;

    private Date historyDateEnd;
}
