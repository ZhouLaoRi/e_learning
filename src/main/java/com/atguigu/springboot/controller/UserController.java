package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.Manager;
import com.atguigu.springboot.entity.ManagerExample;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.entity.UserExample;
import com.atguigu.springboot.service.CourseService;
import com.atguigu.springboot.service.HistoryService;
import com.atguigu.springboot.service.ManagerService;
import com.atguigu.springboot.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/users")
    public String login(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){

        PageHelper.startPage(pageNum, 5);
        List<User> users = userService.selectByExample(new UserExample());
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        model.addAttribute("users",users);
        model.addAttribute("pageInfo",pageInfo);
        return "user/list";
    }

    //来到修改页面，查出当前，在页面回显
    @GetMapping("/user/{userId}")
    public String toEditPage(@PathVariable Integer userId, Model model){
        User user = userService.selectByPrimaryKey(userId);
        model.addAttribute("user",user);
        return "user/edit";
    }

    //用户修改:需要提交用户id;
    @PutMapping("/user")
    public String updateCourse(User user){
        userService.updateByPrimaryKey(user);
        return "redirect:/users";
    }

    //课程删除
    @DeleteMapping("/user/{id}")
    public String deleteCourse(@PathVariable Integer id){
        UserExample userExample = new UserExample();
        UserExample.Criteria cri = userExample.createCriteria();
        cri.andUserIdEqualTo(id);
        userService.deleteByExample(userExample);
        return "redirect:/users";
    }





    /*@RequestMapping("/findUserById")
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
    }*/
}
