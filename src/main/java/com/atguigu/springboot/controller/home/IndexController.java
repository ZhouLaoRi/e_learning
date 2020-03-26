package com.atguigu.springboot.controller.home;

import com.atguigu.springboot.dto.CommentDTO;
import com.atguigu.springboot.entity.*;
import com.atguigu.springboot.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
public class IndexController {

    @Resource
    private CourseService courseService;

    @Resource
    private TypeService typeService;

    @Resource
    private DataService dataService;

    @Resource
    private HistoryService historyService;

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

    /**
     *
     * @param courseId
     * @param dataNum       表示播放第几个视频
     * @param model
     * @param session
     * @return
     */
    @GetMapping(value = {"/course/{courseId}/{dataNum}","/course/{courseId}"})
    public String blog(@PathVariable(value = "courseId") Integer courseId,
                       @PathVariable(value = "dataNum",required = false) Integer dataNum,
                       Model model, HttpSession session) {
        //找到那个courseId
        model.addAttribute("course", courseService.selectByPrimaryKey(courseId));

        //课程播放数量+1
        courseService.viewCourse(courseId);

        //还要找到所有的dataId
        DataExample dataExample = new DataExample();
        DataExample.Criteria criteria = dataExample.createCriteria();
        criteria.andCourseIdEqualTo(courseId);

        List<Data> dataList = dataService.selectByExample(dataExample);
        model.addAttribute("datas",dataList);

        if(dataNum == null || dataNum >= dataList.size()){
            dataNum = 0;
        }else{
            dataNum -= 1;
        }
        model.addAttribute("dataNum",dataNum);

        //添加历史记录
        User user = (User) session.getAttribute("loginUser");
        if(user != null){
            History history = new History();
            history.setUserId(user.getUserId());
            history.setCourseId(courseId);
            history.setDataId(dataNum);
            history.setHistoryDate(new Date());
            historyService.insert(history);
        }

        //获取评论区,页面上有ajax 这样初始化请求了
        /*CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria2 = commentExample.createCriteria();
        criteria2.andDataIdEqualTo(courseId);
        List<Comment> comments = commentService.selectByExample(commentExample);*/
        /*List<CommentDTO> comments = commentService.getAllCommentDTO(courseId);
        model.addAttribute("comments");*/

        return "home/data";
    }



    /*@GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }*/

}
