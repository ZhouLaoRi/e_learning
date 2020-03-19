package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.Manager;
import com.atguigu.springboot.entity.ManagerExample;
import com.atguigu.springboot.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


public class ManagerController {
    @Resource
    private ManagerService managerService;

    @RequestMapping("/loginManager")
    public String loginManager(Model model, String managername, String managerpassword){
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria cri = managerExample.createCriteria();
        cri.andManagerNameEqualTo(managername);
        cri.andManagerPasswordEqualTo(managerpassword);
        List<Manager> managers = managerService.selectByExample(managerExample);
        if(managers != null && managers.size()>0){
            //管理员登陆成功
            return "redict:/index";
        }else {
            return "redict:/index";
        }
    }

}
