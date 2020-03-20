package com.atguigu.springboot.controller.back;

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
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/back/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/users")
    public String login(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){

        PageHelper.startPage(pageNum, 10);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andDeleteTimeIsNull();
        List<User> users = userService.selectByExample(userExample);

        PageInfo<User> pageInfo = new PageInfo<User>(users);
        model.addAttribute("pageInfo",pageInfo);
        return "user/list";
    }

    //条件查询所有资料返回列表页面，要改模糊查询
    @PostMapping("/usersAll")
    public String listUsersAll(@Param(value = "pageNum") Integer pageNum, Model model , Integer userId, String userName){

        if(pageNum == null) pageNum = 1;

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(userId != null && !"".equals(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(userName != null && !"".equals(userName)){
            criteria.andUserNameEqualTo(userName);
        }
        PageHelper.startPage(pageNum, 10);
        List<User> users = userService.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        model.addAttribute("pageInfo",pageInfo);
        return "user/list :: UserList";
    }

    //来到修改页面，查出当前，在页面回显
    @GetMapping("/user/{userId}")
    public String toUserEditPage(@PathVariable Integer userId, Model model){
        User user = userService.selectByPrimaryKey(userId);
        model.addAttribute("user",user);
        return "user/edit";
    }

    //用户修改:需要提交用户id;
    @PutMapping("/user")
    public String updateUser(User user){
        user.setUpdateTime(new Date());
        userService.updateByPrimaryKeySelective(user);
        return "redirect:/users";
    }

    //用户删除
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Integer id){
        /*UserExample userExample = new UserExample();
        UserExample.Criteria cri = userExample.createCriteria();
        cri.andUserIdEqualTo(id);
        userService.deleteByExample(userExample);*/
        //改为加删除
        User user = new User();
        user.setUserId(id);
        user.setDeleteTime(new Date());
        userService.updateByPrimaryKeySelective(user);
        return "redirect:/users";
    }

    //用户恢复
    @PutMapping("/user/recover/{userId}")
    public String recoverCourse(@PathVariable Integer userId){
        userService.recoverUserByPrimaryKey(userId);
        return "redirect:/courses";
    }

    @RequestMapping("/userUpload/{userId}")
    public String dataUpload(@PathVariable Integer userId, MultipartFile file) {
        if(file == null) {
            return "redirect:/users";
        }
        //userId 做路径文件夹吧，重名得防止

        String fileName = file.getOriginalFilename();
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            String path = ResourceUtils.getURL("classpath:").getPath() + "/static/image/user/"+userId;

            //destination 目的地的文件夹
            File dest = new File(path);
            //先创建文件夹，再上传文件
            if (!dest.exists()) dest.mkdirs();
            dest = new File(path + "/" + fileName);
            file.transferTo(dest);

            String userAvatar = "/image/user/" + userId + "/" + fileName;
            userService.updateUserAvatar(userId, userAvatar);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
