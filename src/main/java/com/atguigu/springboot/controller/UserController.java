package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.Manager;
import com.atguigu.springboot.entity.ManagerExample;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.entity.UserExample;
import com.atguigu.springboot.service.CourseService;
import com.atguigu.springboot.service.HistoryService;
import com.atguigu.springboot.service.ManagerService;
import com.atguigu.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private CourseService courseService;

    @Resource
    private HistoryService historyService;

    @Resource
    private ManagerService managerService;


    @RequestMapping("/login")
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
            /*return "redirect:findHistory";*/
            /*return "index";*/
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
    }

    /*@RequestMapping("/login")
    public String login(Model model, String username, String password, HttpSession session){
        UserExample userExample = new UserExample();
        UserExample.Criteria cri = userExample.createCriteria();
        cri.andUserNameEqualTo(username);
        cri.andUserPasswordEqualTo(password);
        List<User> users = userService.selectByExample(userExample);
        if(users!=null && users.size()>0){
            //登陆成功
            *//*return "redirect:findHistory";*//*
            *//*return "index";*//*
            session.setAttribute("user",users.get(0));
            return "redirect:/";
        }else{
            //登陆失败
            return "index";
        }
    }*/

    @RequestMapping("/showAllUsers")
    public String login(Model model){
        UserExample userExample = new UserExample();
        List<User> users = userService.selectByExample(userExample);
        model.addAttribute("users",users);
        return "showAllUsers";
    }

    @RequestMapping("/findUserById")
    public String findByUserId(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        return "userInfor";
    }

    @RequestMapping("/findUserById2")
    public String findByUserId2(Model model, Integer userId){
        UserExample userExample = new UserExample();
        UserExample.Criteria cri = userExample.createCriteria();
        cri.andUserIdEqualTo(userId);
        List<User> users = userService.selectByExample(userExample);
        User user = users.get(0);
        model.addAttribute("user",user);
        return "userInfor2";
    }

    @RequestMapping("/userUpdate")
    public String userUpdate(HttpSession session, String userName, String userPassword, Integer userLevel, String userDirection){
        User user = (User) session.getAttribute("user");
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserLevel(userLevel);
        user.setUserDirection(userDirection);
        userService.updateByPrimaryKey(user);
        return "redirect:/index";
    }

    @RequestMapping("/userUpdate2")
    public String userUpdate2(Integer userId,String userName,String userPassword,Integer userLevel,String userDirection){
        User user = new User();
        //System.out.println(userDirection);
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserLevel(userLevel);
        user.setUserDirection(userDirection);
        userService.updateByPrimaryKey(user);
        return "redirect:/showAllUsers";
    }

    @RequestMapping("/addUser")
    public String addUser(String userName,String userPassword,Integer userLevel,String userDirection){
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserLevel(userLevel);
        user.setUserDirection(userDirection);
        userService.insert(user);
        return "redirect:/index";
    }
    @RequestMapping("/delUserById")
    public String dataDelete(Integer userId){
        UserExample userExample = new UserExample();
        UserExample.Criteria cri = userExample.createCriteria();
        cri.andUserIdEqualTo(userId);
        userService.deleteByExample(userExample);
        return "redirect:/showAllUsers";
    }
}
