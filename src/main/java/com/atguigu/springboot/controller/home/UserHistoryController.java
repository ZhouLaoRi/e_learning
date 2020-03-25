package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.entity.History;
import com.atguigu.springboot.entity.HistoryExample;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.service.HistoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class UserHistoryController {

    @Resource
    private HistoryService historyService;

    @RequestMapping("/showHistory")
    public String findHistory(Model model, HttpSession session){
        User user = (User) session.getAttribute("loginUser");

        if(user == null){
            return "home/personalHistory :: historyList";
        }
        Integer userId = user.getUserId();
        HistoryExample historyExample = new HistoryExample();
        HistoryExample.Criteria cri = historyExample.createCriteria();
        cri.andUserIdEqualTo(userId);
        historyExample.setOrderByClause("history_date DESC");
        List<History> historys = historyService.selectByExample(historyExample);
        PageInfo<History> historyPageInfo = new PageInfo<History>(historys);
        model.addAttribute("historyPageInfo",historyPageInfo);
        return "home/personalHistory";
    }
}
