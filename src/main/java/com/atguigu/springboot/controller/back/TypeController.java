package com.atguigu.springboot.controller.back;

import com.atguigu.springboot.entity.Type;
import com.atguigu.springboot.entity.TypeExample;
import com.atguigu.springboot.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/back/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    //查询所有分类返回列表页面
    @GetMapping("/types")
    public String listType(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model , Type type){

        TypeExample typeExample = new TypeExample();
        TypeExample.Criteria criteria = typeExample.createCriteria();

        if(type.getId() != null && !"".equals(type.getId())){
            criteria.andIdEqualTo(type.getId());
        }
        if(type.getName() != null && !"".equals(type.getName() )){
            criteria.andNameEqualTo(type.getName());
        }
        criteria.andDeleteTimeIsNull();

        PageHelper.startPage(pageNum, 10);
        List<Type> types = typeService.selectByExample(typeExample);
        PageInfo<Type> pageInfo = new PageInfo<Type>(types);
        model.addAttribute("pageInfo",pageInfo);
        return "type/list";
    }

    //用来条件查询的 ：查询所有分类返回列表页面
    @ResponseBody
    @PostMapping("/typesAll")
    public PageInfo<Type> listAllType(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum ,Type type){

        TypeExample typeExample = new TypeExample();
        TypeExample.Criteria criteria = typeExample.createCriteria();
        if(type.getId() != null && !"".equals(type.getId())){
            criteria.andIdEqualTo(type.getId());
        }
        if(type.getName() != null && !"".equals(type.getName())){
            criteria.andNameEqualTo(type.getName());
        }
        PageHelper.startPage(pageNum, 10);
        List<Type> types = typeService.selectByExample(typeExample);
        PageInfo<Type> pageInfo = new PageInfo<Type>(types);
        return pageInfo;
    }


    //来到分类添加页面
    @RequestMapping("/type")
    public String addTypeGuide(Type type,Model model){
        model.addAttribute("types",typeService.selectByExample(new TypeExample()));
        return "type/add";
    }

    //分类添加
    @PostMapping("/type")
    public String addType(Type type){
        type.setCreateTime(new Date());
        typeService.insertSelective(type);
        return "redirect:/types";
    }

    //来到修改页面，查出当前，在页面回显
    @GetMapping("/type/{id}")
    public String toEditPage(@PathVariable Integer id, Model model){
        model.addAttribute("types",typeService.selectByExample(new TypeExample()));
        Type type = typeService.selectByPrimaryKey(id);
        model.addAttribute("type",type);
        return "type/edit";
    }

    //分类修改:需要提交分类id;
    @PutMapping("/type")
    public String updateType(Type type){
        typeService.updateByPrimaryKeySelective(type);
        return "redirect:/types";
    }

    //分类删除
    @DeleteMapping("/type/{id}")
    public String deleteType(@PathVariable Integer id){
        //cri.andIdEqualTo(id);
        //改为假删除
        Type type = new Type();
        type.setId(id);
        type.setDeleteTime(new Date());
        typeService.updateByPrimaryKeySelective(type);
        return "redirect:/types";
    }

    //分类恢复
    @PutMapping("/type/recover/{id}")
    public String recoverType(@PathVariable Integer typeId){
        typeService.recoverTypeByPrimaryKey(typeId);
        return "redirect:/types";
    }



}
