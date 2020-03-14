package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.dto.CommentDTO;
import com.atguigu.springboot.entity.*;
import com.atguigu.springboot.service.CommentService;
import com.atguigu.springboot.service.CourseService;
import com.atguigu.springboot.service.DataService;
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

    @Resource
    private CourseService courseService;

    @Resource
    private TypeService typeService;

    @Resource
    private CommentService commentService;

    @Resource
    private DataService dataService;

    /*@Autowired
    private TagService tagService;*/

    @GetMapping("/index")
    public String index(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,Model model) {

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

    @GetMapping("/course/{courseId}")
    public String blog(@PathVariable Integer courseId, Model model) {
        //找到那个courseId
        model.addAttribute("course", courseService.selectByPrimaryKey(courseId));
        //还要找到所有的dataId
        DataExample dataExample = new DataExample();
        DataExample.Criteria criteria = dataExample.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        model.addAttribute("datas",dataService.selectByExample(dataExample));

        //获取评论区,页面上有ajax 这样初始化请求了
        /*CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria2 = commentExample.createCriteria();
        criteria2.andDataIdEqualTo(courseId);
        List<Comment> comments = commentService.selectByExample(commentExample);*/
        /*List<CommentDTO> comments = commentService.getAllCommentDTO(courseId);
        model.addAttribute("comments");*/

        return "home/data";
    }


    @GetMapping("/comments/{courseId}")
    public String comments(@PathVariable Integer courseId, Model model) {
        List<CommentDTO> comments = commentService.getAllCommentDTO(courseId);
        model.addAttribute("comments", comments);
        return "home/data :: commentList";
    }

    /*@GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }*/

}
