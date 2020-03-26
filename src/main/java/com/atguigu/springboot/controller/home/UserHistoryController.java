package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.dto.HistoryDTO;
import com.atguigu.springboot.entity.History;
import com.atguigu.springboot.entity.HistoryExample;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.service.HistoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class UserHistoryController {

    @Resource
    private HistoryService historyService;

    @GetMapping("/showHistory")
    public String showUserHistory(Model model, HttpSession session, @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum){
        User user = (User) session.getAttribute("loginUser");

        if(user == null){
            return "home/personalHistory :: historyList";
        }
        Integer userId = user.getUserId();

        List<HistoryDTO> historys = historyService.showUserHistory(userId);
        PageHelper.startPage(pageNum, 10);
        PageInfo<HistoryDTO> historyPageInfo = new PageInfo<HistoryDTO>(historys);
        model.addAttribute("historyPageInfo",historyPageInfo);
        return "home/personalHistory";
        //return "home/personalHistory :: historyList";
    }
}
