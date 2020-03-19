package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.History;
import com.atguigu.springboot.entity.HistoryExample;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.service.HistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


public class HistoryController {


    @Resource
    private HistoryService historyService;

    @RequestMapping("/showHistory")
    public String findHistory(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "showHistory";
        }
        Integer userId = user.getUserId();
        HistoryExample historyExample = new HistoryExample();
        HistoryExample.Criteria cri = historyExample.createCriteria();
        cri.andUserIdEqualTo(userId);
        List<History> histories = historyService.selectByExample(historyExample);
        model.addAttribute("histories",histories);
        return "showHistory";
    }
}
