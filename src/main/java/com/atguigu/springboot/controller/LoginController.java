package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.Manager;
import com.atguigu.springboot.entity.ManagerExample;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.entity.UserExample;
import com.atguigu.springboot.service.ManagerService;
import com.atguigu.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private ManagerService managerService;

    @PostMapping(value="/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
            //登陆成功
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }
        //登陆失败
        map.put("msg","用户名密码错误");
        return "login";
    }


    /*@RequestMapping("/login")
    public String login(Model model, String username, String password, HttpSession session){
        UserExample userExample = new UserExample();
        UserExample.Criteria cri = userExample.createCriteria();
        cri.andUserNameEqualTo(username);
        cri.andUserPasswordEqualTo(password);
        List<User> users = userService.selectByExample(userExample);

        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria managerCri = managerExample.createCriteria();
        managerCri.andManagerNameEqualTo(username);
        managerCri.andManagerPasswordEqualTo(password);
        List<Manager> managers = managerService.selectByExample(managerExample);

        if(users!=null && users.size()>0){
            //这是与用户匹配
            //登陆成功
            *//*return "redirect:findHistory";*//*
            *//*return "index";*//*
            session.setAttribute("user",users.get(0));
            return "redirect:/";
        }else if (managers!=null && managers.size()>0){
            //这是与管理员匹配
            session.setAttribute("manager",managers.get(0));
            return "redirect:/showAllData";
        }else{
            //登陆失败
            return "index";
        }
    }*/
}
