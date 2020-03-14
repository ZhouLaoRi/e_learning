package com.atguigu.springboot.controller;

import com.atguigu.springboot.dto.CommentDTO;
import com.atguigu.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController2 {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Resource
    private CommentService commentService;

    @ResponseBody
    @GetMapping("/query")
    public Map<String,Object> map(){
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from department");
        return list.get(0);
    }

    @ResponseBody
    @GetMapping("/queryComments")
    public List<CommentDTO> map2(){

        List<CommentDTO> comments = commentService.getAllCommentDTO(1);
        return  comments;
    }
}
