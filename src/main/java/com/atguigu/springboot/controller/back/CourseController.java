package com.atguigu.springboot.controller.back;

import com.atguigu.springboot.entity.Course;
import com.atguigu.springboot.entity.CourseExample;
import com.atguigu.springboot.entity.TypeExample;
import com.atguigu.springboot.service.CourseService;
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
@RequestMapping("/back/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private TypeService typeService;

    //查询所有课程返回列表页面
    @GetMapping("/courses")
    public String listCourse(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,Model model ,Course course){

        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();

        if(course.getCourseId() != null && !"".equals(course.getCourseId())){
            criteria.andCourseIdEqualTo(course.getCourseId());
        }
        if(course.getCourseName() != null && !"".equals(course.getCourseName())){
            criteria.andCourseNameEqualTo(course.getCourseName());
        }
        criteria.andDeleteTimeIsNull();

        PageHelper.startPage(pageNum, 5);
        List<Course> courses = courseService.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<Course>(courses);
        model.addAttribute("courses",courses);
        model.addAttribute("pageInfo",pageInfo);
        return "course/list";
    }

    //条件查询所有课程返回列表页面，要改模糊查询
    @PostMapping("/coursesAllBlog")
    public String listCoursesAllBlog(@Param(value = "pageNum") Integer pageNum, Model model , Course course){

        if(pageNum == null) pageNum = 1;

        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        if(course.getCourseId() != null && !"".equals(course.getCourseId())){
            criteria.andCourseIdEqualTo(course.getCourseId());
        }
        if(course.getCourseName() != null && !"".equals(course.getCourseName())){
            criteria.andCourseNameEqualTo(course.getCourseName());
        }
        PageHelper.startPage(pageNum, 10);
        List<Course> courses = courseService.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<Course>(courses);
        model.addAttribute("courses",courses);
        model.addAttribute("pageInfo",pageInfo);
        return "course/list :: CourseList";
    }

    //用来条件查询的 ：查询所有课程返回列表页面
    @ResponseBody
    @GetMapping("/coursesAll")
    public PageInfo<Course> listAllCourse(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum ,Course course){

        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        if(course.getCourseId() != null ){
            criteria.andCourseIdEqualTo(course.getCourseId());
        }
        if(course.getCourseName() != null){
            criteria.andCourseNameEqualTo(course.getCourseName());
        }
        PageHelper.startPage(pageNum, 5);
        List<Course> courses = courseService.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<Course>(courses);
        return pageInfo;
    }


    //来到课程添加页面
    @RequestMapping("/course")
    public String addCourseGuide(Course course,Model model){
        model.addAttribute("types",typeService.selectByExample(new TypeExample()));
        return "course/add";
    }

    //课程添加
    @PostMapping("/course")
    public String addCourse(Course course){
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        course.setCourseView(0);
        course.setCourseLike(0);
        courseService.insert(course);
        //同时分类里面的总数也要加1
        typeService.addTotalOne(course.getTypeId());

        return "redirect:/back/course/courses";
    }

    //来到修改页面，查出当前，在页面回显
    @GetMapping("/course/{id}")
    public String toEditPage(@PathVariable Integer id, Model model){
        model.addAttribute("types",typeService.selectByExample(new TypeExample()));
        Course course = courseService.selectByPrimaryKey(id);
        model.addAttribute("course",course);
        return "course/edit";
    }

    //课程修改:需要提交课程id;
    @PutMapping("/course")
    public String updateCourse(Course course){
        course.setUpdateTime(new Date());
        courseService.updateByPrimaryKeySelective(course);
        return "redirect:/back/course/courses";
    }

    //课程删除
    @DeleteMapping("/course/{id}")
    public String deleteCourse(@PathVariable Integer id){
        //cri.andCourseIdEqualTo(id);
        //改为假删除
        Course course = new Course();
        course.setCourseId(id);
        course.setDeleteTime(new Date());
        courseService.updateByPrimaryKeySelective(course);
        return "redirect:/courses";
    }

    //课程恢复
    @PutMapping("/course/recover/{id}")
    public String recoverCourse(@PathVariable Integer courseId){
        courseService.recoverCourseByPrimaryKey(courseId);
        return "redirect:/back/course/courses";
    }


    @RequestMapping("/courseUpload/{courseId}")
    public String courseUpload(@PathVariable Integer courseId, MultipartFile file) {
        if(file == null) {
            return "redirect:/back/course/courses";
        }
        String fileName = file.getOriginalFilename();
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            //classpath找的是target的文件了
            String path = ResourceUtils.getURL("classpath:").getPath();
            //destination 目的地，他加上path找的是全路径，所以是有static的，而且是target，这样感觉@{} 他是静态资源 不是target的。。。不过好像都是找target位置的喔，怪怪的
            File dest = new File(path + "/static/image/course");
            //先创建文件夹，再上传文件
            if (!dest.exists()) dest.mkdirs();
            dest = new File(path + "/static/image/course" + "/" + fileName);
            file.transferTo(dest);

            String coursePath = "/image/course" + "/" + fileName;
            Course course = new Course();
            course.setCourseId(courseId);
            course.setCourseAvatar(coursePath);
            courseService.updateByPrimaryKeySelective(course);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/back/course/courses";
    }


    @RequestMapping("/courseDownload/{courseId}")
    public void dataDownload(@PathVariable Integer courseId, HttpServletResponse response) {
        Course course = courseService.selectByPrimaryKey(courseId);
        String path = course.getCourseAvatar();
        String[] strings = path.split("/");
        String fileName = strings[strings.length-1];
        // 告诉浏览器输出内容为流
        response.setHeader("content-type", "application/octet-stream;charset=utf-8");

        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {

            String downloadPath = ResourceUtils.getURL("classpath:").getPath();

            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(downloadPath + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }







}
