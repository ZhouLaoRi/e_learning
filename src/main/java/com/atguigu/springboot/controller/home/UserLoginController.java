package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.entity.Manager;
import com.atguigu.springboot.entity.ManagerExample;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.entity.UserExample;
import com.atguigu.springboot.service.ManagerService;
import com.atguigu.springboot.service.UserService;
import com.atguigu.springboot.utils.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserLoginController {

    @Resource
    private UserService userService;
    @Resource
    private ManagerService managerService;

    //用户注册
    @PostMapping("/user/signup")
    public String userSignUp(User user){
        String saltPwd = MD5Utils.MD5(user.getUserPassword());
        user.setUserPassword(saltPwd);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsvip(0);
        //因为钱有小数
        user.setMoney("0.00");
        user.setJifen(0);
        //userService.insert(user);
        return "redirect:/home/index";
    }

    //用户名唯一
    @PostMapping("/user/userNameOnly")
    @ResponseBody
    public boolean userNameOnly(String userName){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<User> users = userService.selectByExample(userExample);
        if(users.size()==0){
            return true;
        }
        return false;
    }

    @PostMapping(value="/user/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("userPassword") String userPassword,
                        HttpSession session,
                        @RequestParam String verifyCode){
        //首先验证验证码是否存在
        String trueVerifyCode = (String) session.getAttribute("verifyCode");
        if (trueVerifyCode == null) {
            //map.put("loginMsg","需要刷新验证码");
            session.setAttribute("loginMsg","需要刷新验证码");
            return "redirect:/home/index";
        }
        //判断验证码是否输入正确（验证码忽略大小写）
        if (!trueVerifyCode.equalsIgnoreCase(verifyCode)) {
            //map.put("loginMsg","验证码不正确");
            session.setAttribute("loginMsg","验证码不正确");
            return "redirect:/home/index";
        }

        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(userPassword)) {
            //登陆成功
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUserNameEqualTo(userName);
            //未删除的
            criteria.andDeleteTimeIsNull();
            List<User> users = userService.selectByExample(userExample);
            if(users.size() == 0){
                //map.put("loginMsg","用户不存在");
                session.setAttribute("loginMsg","用户不存在");
                return "redirect:/home/index";
            }

            //用户名和密码分开判断
            String encodePwd = MD5Utils.MD5(userPassword);
            criteria.andUserPasswordEqualTo(encodePwd);
            users = userService.selectByExample(userExample);

            if(users.size() == 0){
                //map.put("loginMsg","密码错误");
                session.setAttribute("loginMsg","密码错误");
                return "redirect:/home/index";
            }
            //虽然用户名不能重复
            session.setAttribute("loginUser",users.get(0));
            return "redirect:/home/index";
        }
        //登陆失败
        //map.put("loginMsg","用户名密码错误");
        session.setAttribute("loginMsg","用户名密码错误");
        return "login";
    }



    /**
     * 登出
     *
     */
    @RequestMapping("/user/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/home/index";
    }


    /*@PostMapping("/user/signin")
    public String signin(User user) {
        userService.insert(user);
        return "redirect:/";
    }*/
}
