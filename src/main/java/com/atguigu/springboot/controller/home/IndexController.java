package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.entity.Course;
import com.atguigu.springboot.entity.CourseExample;
import com.atguigu.springboot.service.CourseService;
import com.atguigu.springboot.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by limi on 2017/10/13.
 */
@Controller
@RequestMapping("/home")
public class IndexController {

    @Autowired
    private CourseService courseService;

    @Resource
    private TypeService typeService;

    /*@Autowired
    private TagService tagService;*/

    @GetMapping("/index")
    public String index(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,Model model) {

        PageHelper.startPage(pageNum, 5);
        List<Course> courses = courseService.selectByExample(new CourseExample());
        PageInfo<Course> pageInfo = new PageInfo<Course>(courses);

        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("types", typeService.selectAllType());
        return "home/index";
    }

    /**
     * 相当于是找课程，还是模糊查询
     */
    @PostMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("page", courseService.selectByQuery(query));
        model.addAttribute("query", query); //显示在页面，看看搜索的是啥。。。。
        return "search";
    }

    @GetMapping("/course/{id}")
    public String blog(@PathVariable Integer id, Model model) {
        model.addAttribute("course", courseService.selectByPrimaryKey(id));
        return "course";
    }

    /*@GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }*/

}
