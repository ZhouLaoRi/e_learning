package com.atguigu.springboot.controller.back;

import com.atguigu.springboot.entity.Manager;
import com.atguigu.springboot.entity.ManagerExample;
import com.atguigu.springboot.service.ManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/back/manager")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @GetMapping("/managers")
    public String login(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){

        PageHelper.startPage(pageNum, 10);
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria = managerExample.createCriteria();
        criteria.andDeleteTimeIsNull();
        List<Manager> managers = managerService.selectByExample(managerExample);

        PageInfo<Manager> pageInfo = new PageInfo<Manager>(managers);
        model.addAttribute("pageInfo",pageInfo);
        return "manager/list";
    }

    //条件查询所有资料返回列表页面，要改模糊查询
    @PostMapping("/managersAll")
    public String listManagersAll(@Param(value = "pageNum") Integer pageNum, Model model , Integer managerId, String managerName){

        if(pageNum == null) pageNum = 1;

        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria = managerExample.createCriteria();
        if(managerId != null && !"".equals(managerId)){
            criteria.andManagerIdEqualTo(managerId);
        }
        if(managerName != null && !"".equals(managerName)){
            criteria.andManagerNameEqualTo(managerName);
        }
        PageHelper.startPage(pageNum, 10);
        List<Manager> managers = managerService.selectByExample(managerExample);
        PageInfo<Manager> pageInfo = new PageInfo<Manager>(managers);
        model.addAttribute("pageInfo",pageInfo);
        return "manager/list :: ManagerList";
    }

    //来到修改页面，查出当前，在页面回显
    @GetMapping("/manager/{managerId}")
    public String toManagerEditPage(@PathVariable Integer managerId, Model model){
        Manager manager = managerService.selectByPrimaryKey(managerId);
        model.addAttribute("manager",manager);
        return "manager/edit";
    }

    //管理员修改:需要提交管理员id;
    @PutMapping("/manager")
    public String updateManager(Manager manager){
        manager.setUpdateTime(new Date());
        managerService.updateByPrimaryKeySelective(manager);
        return "redirect:/back/manager/managers";
    }

    //管理员删除
    @DeleteMapping("/manager/{id}")
    public String deleteManager(@PathVariable Integer id){
        /*ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria cri = managerExample.createCriteria();
        cri.andManagerIdEqualTo(id);
        managerService.deleteByExample(managerExample);*/
        //改为加删除
        Manager manager = new Manager();
        manager.setManagerId(id);
        manager.setDeleteTime(new Date());
        managerService.updateByPrimaryKeySelective(manager);
        return "redirect:/back/manager/managers";
    }

    //管理员恢复
    @PutMapping("/manager/recover/{managerId}")
    public String recoverCourse(@PathVariable Integer managerId){
        managerService.recoverManagerByPrimaryKey(managerId);
        return "redirect:/courses";
    }

    @RequestMapping("/managerUpload/{managerId}")
    public String dataUpload(@PathVariable Integer managerId, MultipartFile file) {
        if(file == null) {
            return "redirect:/back/manager/managers";
        }
        //managerId 做路径文件夹吧，重名得防止

        String fileName = file.getOriginalFilename();
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            String path = ResourceUtils.getURL("classpath:").getPath() + "/static/image/manager/"+managerId;

            //destination 目的地的文件夹
            File dest = new File(path);
            //先创建文件夹，再上传文件
            if (!dest.exists()) dest.mkdirs();
            dest = new File(path + "/" + fileName);
            file.transferTo(dest);

            String managerAvatar = "/image/manager/" + managerId + "/" + fileName;
            managerService.updateManagerAvatar(managerId, managerAvatar);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/back/manager/managers";
    }
}
