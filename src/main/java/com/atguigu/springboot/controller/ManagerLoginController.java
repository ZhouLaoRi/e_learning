package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.Manager;
import com.atguigu.springboot.entity.ManagerExample;
import com.atguigu.springboot.service.ManagerService;
import com.atguigu.springboot.service.ManagerService;
import com.atguigu.springboot.utils.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerLoginController {

    @Resource
    private ManagerService managerService;

    @PostMapping(value="/manager/login")
    public String login(@RequestParam("managerName") String managerName,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session,
                        @RequestParam String verifyCode){
        //首先验证验证码是否存在
        String trueVerifyCode = (String) session.getAttribute("verifyCode");
        if (trueVerifyCode == null) {
            map.put("msg","需要刷新验证码");
            return "login";
        }
        //判断验证码是否输入正确（验证码忽略大小写）
        if (!trueVerifyCode.equalsIgnoreCase(verifyCode)) {
            map.put("msg","验证码不正确");
            return "login";
        }
        /*if (!StringUtils.isEmpty(managername) && "123".equals(password)) {
            //登陆成功
            session.setAttribute("loginManager",managername);
            return "redirect:/main.html";
        }*/
        //空在前台校验吧
        if (!StringUtils.isEmpty(managerName) && !StringUtils.isEmpty(password)) {
            //登陆成功
            ManagerExample managerExample = new ManagerExample();
            ManagerExample.Criteria criteria = managerExample.createCriteria();
            criteria.andManagerNameEqualTo(managerName);
            //未删除的
            criteria.andDeleteTimeIsNull();
            List<Manager> managers = managerService.selectByExample(managerExample);
            if(managers.size() == 0){
                map.put("msg","用户不存在");
                return "login";
            }

            //用户名和密码分开判断
            String encodePwd = MD5Utils.MD5(password);
            criteria.andManagerPasswordEqualTo(encodePwd);
            managers = managerService.selectByExample(managerExample);

            if(managers.size() == 0){
                map.put("msg","密码错误");
                return "login";
            }
            //虽然用户名不能重复
            session.setAttribute("loginManager",managers.get(0));
            return "redirect:/main.html";
        }
        //登陆失败
        map.put("msg","用户名密码错误");
        return "login";
    }



    /**
     * 登出
     *
     * @param session 会话session
     * @return com.sfwiki.utils.resulthandler.ResponseBean<com.sfwiki.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/30 15:07
     */
    @RequestMapping("/manager/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("loginManager");
        return "redirect:/";
    }


    @PostMapping("/manager/signin")
    public String signin(Manager manager) {
        managerService.insert(manager);
        return "redirect:/";
    }
}
