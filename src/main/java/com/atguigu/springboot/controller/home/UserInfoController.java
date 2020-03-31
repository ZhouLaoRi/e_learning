package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.service.UserService;
import com.atguigu.springboot.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/home")
public class UserInfoController {

    @Resource
    private UserService userService;

    @GetMapping("/user/getUserInfo")
    public String getUserInfo(){
        return "home/userInfo";
    }

    //突然发现session里面的user就是信息了

    //只要一个修改信息的了，非空才修改
    @PutMapping("/user/edit")
    public String updateUser(User user, HttpSession session){
        user.setUpdateTime(new Date());
        userService.updateByPrimaryKeySelective(user);

        User newUser = userService.selectByPrimaryKey(user.getUserId());
        session.setAttribute("loginUser",newUser);
        return "redirect:/home/user/getUserInfo";
    }

    @RequestMapping("/userUpload/{userId}")
    public String dataUpload(@PathVariable Integer userId, MultipartFile file, HttpSession session) {
        if(file == null) {
            return "redirect:/home/user/getUserInfo";
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

        User newUser = userService.selectByPrimaryKey(userId);
        session.setAttribute("loginUser",newUser);
        //因为这里只是更换了图片 啥都没了。所以我觉得应该得修改session。重新登陆才行
        //然后我建议这个接口 只返回一个路径。然后ajax修改他
        //或者说 上传之后 那个图片自动变化
        return "redirect:/home/user/getUserInfo";
        //return "home/userInfo :: userAvatar";
    }

    @GetMapping("/user/toEditPassword")
    public String toEditPassword(){
        return "home/editPassword";
    }

    @PostMapping("/user/editPassword")
    @ResponseBody
    public String editPassword(User user, HttpSession session){
        if(user == null){
            return "0";
        }
        //加密
        user.setUserPassword(MD5Utils.MD5(user.getUserPassword()));
        User oldUser = userService.selectByPrimaryKey(user.getUserId());
        if (oldUser.getUserPassword() == user.getUserPassword()){
            return "-1";
        }

        user.setUpdateTime(new Date());
        userService.updateByPrimaryKeySelective(user);
        User newUser = userService.selectByPrimaryKey(user.getUserId());
        session.setAttribute("loginUser",newUser);
        return "1";
    }


}
