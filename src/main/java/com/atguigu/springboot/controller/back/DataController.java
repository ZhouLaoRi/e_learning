package com.atguigu.springboot.controller.back;

import com.atguigu.springboot.entity.*;
import com.atguigu.springboot.service.CommentService;
import com.atguigu.springboot.service.CourseService;
import com.atguigu.springboot.service.DataService;
import com.atguigu.springboot.service.HistoryService;
import com.atguigu.springboot.utils.UploadUtils;
/*import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;*/
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.xml.transform.OutputKeys.ENCODING;

@Controller
@RequestMapping("/back/data")
public class DataController {

    @Resource
    private CourseService courseService;
    @Resource
    private DataService dataService;
    @Resource
    private CommentService commentService;
    @Resource
    private HistoryService historyService;

    @GetMapping("/datas")
    public String showAllData(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 10);
        List<Data> datas = dataService.selectByExample(new DataExample());
        PageInfo<Data> pageInfo = new PageInfo<Data>(datas);
        model.addAttribute("datas",datas);
        model.addAttribute("pageInfo",pageInfo);
        return "data/list";
    }


    //条件查询所有资料返回列表页面，要改模糊查询
    @PostMapping("/datasAll")
    public String listDatasAll(@Param(value = "pageNum") Integer pageNum, Model model , Data data){

        if(pageNum == null) pageNum = 1;

        DataExample dataExample = new DataExample();
        DataExample.Criteria criteria = dataExample.createCriteria();
        if(data.getDataId() != null && !"".equals(data.getDataId())){
            criteria.andDataIdEqualTo(data.getDataId());
        }
        if(data.getDataName() != null && !"".equals(data.getDataName())){
            criteria.andDataNameEqualTo(data.getDataName());
        }

        PageHelper.startPage(pageNum, 10);
        List<Data> datas = dataService.selectByExample(dataExample);
        PageInfo<Data> pageInfo = new PageInfo<Data>(datas);
        model.addAttribute("datas",datas);
        model.addAttribute("pageInfo",pageInfo);
        return "data/list :: DataList";
    }



    //来到资料添加页面
    @RequestMapping("/data")
    public String addDataGuide(Model model){
        model.addAttribute("courses",courseService.selectByExample(new CourseExample()));
        return "data/add";
    }


    //资料添加
    @PostMapping("/data")
    public String addCourse(Data data){
        data.setCreateTime(new Date());
        dataService.insert(data);
        return "redirect:/datas";
    }

    //来到修改页面，查出当前，在页面回显
    @GetMapping("/data/{dataId}")
    public String toEditPage(@PathVariable Integer dataId, Model model){
        Data data = dataService.selectByPrimaryKey(dataId);
        model.addAttribute("data",data);

        model.addAttribute("courses",courseService.selectByExample(new CourseExample()));
        return "data/edit";
    }

    //资料修改:需要提交资料id;
    @PutMapping("/data")
    public String updateCourse(Data data){
        dataService.updateByPrimaryKeySelective(data);
        return "redirect:/datas";
    }

    //资料删除
    @DeleteMapping("/data/{id}")
    public String deleteCourse(@PathVariable Integer id){
        DataExample dataExample = new DataExample();
        DataExample.Criteria cri = dataExample.createCriteria();
        cri.andCourseIdEqualTo(id);
        dataService.deleteByExample(dataExample);
        return "redirect:/datas";
    }

    @RequestMapping("/dataUpload/{dataId}")
    public String dataUpload(@PathVariable Integer dataId,MultipartFile file) {
        if(file == null) {
            return "redirect:/datas";
        }
        //获得courseName 以方便增加路径
        Data data = dataService.selectByPrimaryKey(dataId);
        Integer courseId = data.getCourseId();
        Course course = courseService.selectByPrimaryKey(courseId);
        String courseName = course.getCourseName();

        String fileName = file.getOriginalFilename();
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            String path = ResourceUtils.getURL("classpath:").getPath() + "/static/uploads/course/"+courseName;

            //destination 目的地的文件夹
            File dest = new File(path);
            //先创建文件夹，再上传文件
            if (!dest.exists()) dest.mkdirs();
            dest = new File(path + "/" + fileName);
            file.transferTo(dest);

            String dataPath = "/uploads/course/" + courseName + "/" + fileName;
            dataService.updateDataPath(dataId, dataPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/datas";
    }


    @RequestMapping("/dataDownload/{dataId}")
    public void dataDownload(@PathVariable Integer dataId,HttpServletResponse response) {
        Data data = dataService.selectByPrimaryKey(dataId);
        String path = data.getDataPath();
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
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File("F:\\JetBrains\\IdeaProjects2\\e_learning\\src\\main\\resources\\uploads\\"+ fileName)));
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














    /*@RequestMapping("/showDataById")
    public String showDataById(Model model, Integer dataId, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer userId = user.getUserId();
            if(userId == null){
                userId = -1;
            }
            History history = new History();
            history.setDataId(dataId);
            history.setUserId(userId);
            history.setHistoryDate(new Date());
            historyService.insert(history);
        }
        DataExample dataExample = new DataExample();
        DataExample.Criteria cri = dataExample.createCriteria();
        cri.andDataIdEqualTo(dataId);
        List<Data> data = dataService.selectByExample(dataExample);
        model.addAttribute("data",data.get(0));
        //获取评论
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria commentCri = commentExample.createCriteria();
        commentCri.andDataIdEqualTo(dataId);
        List<Comment> comments = commentService.selectByExample(commentExample);
        model.addAttribute("comments",comments);

        return "showDataById";
    }*/

    @RequestMapping("/findDataById")
    public String findDataById(Model model, Integer dataId){

        DataExample dataExample = new DataExample();
        DataExample.Criteria cri = dataExample.createCriteria();
        cri.andDataIdEqualTo(dataId);
        List<Data> datas = dataService.selectByExample(dataExample);
        model.addAttribute("data",datas.get(0));
        return "dataInfor";
    }


    /*public String dataUpdate(HttpSession session, Integer courseId, String dataName, Integer dataPath, Integer dataLevel, String Intro){
        //Data data = (Data) session.getAttribute("data");*/
    @RequestMapping("/dataUpdate")
    public String dataUpdate(Integer dataId, Integer courseId, String dataName, String dataPath, Integer dataLevel, String dataIntro){
        Data data = new Data();
        data.setCourseId(courseId);
        data.setDataId(dataId);
        data.setDataName(dataName);
        data.setDataLevel(dataLevel);
        data.setDataPath(dataPath);
        data.setDataIntro(dataIntro);
        dataService.updateByPrimaryKey(data);
        return "redirect:/showAllData";
    }

    @RequestMapping("/dataDelete")
    public String dataDelete(Integer dataId){
        DataExample dataExample = new DataExample();
        DataExample.Criteria cri = dataExample.createCriteria();
        cri.andDataIdEqualTo(dataId);
        dataService.deleteByExample(dataExample);
        return "redirect:/showAllData";
    }

    /*@RequestMapping("/addData")
    public String addData(HttpServletRequest request){

        try {
            //0.使用fileupload保存图片和将商品的信息放入map中
            //0.1创建map集合用来存放商品的信息
            Map<String, Object> map = new HashMap<String, Object>();

            //0.2创建磁盘文件项工厂（设置临时文件大小和位置）
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //0.3创建核心上传对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            //0.4解析request
            List<FileItem> list = upload.parseRequest(request);

            //0.5遍历list，获取每一个文件项
            for (FileItem fi : list) {
                //0.6获取name属性值
                String key = fi.getFieldName();
                //0.7判断是否是普通的上传组件
                if (fi.isFormField()) {
                    //普通
                    map.put(key, fi.getString("utf-8"));
                } else {
                    //文件
                    //a.获取文件的名称
                    String name = fi.getName();
                    //b.获取文件的真实名称
                    String realName = UploadUtils.getRealName(name);
                    //c.获取文件的uuid名称
                    String uuidName = UploadUtils.getUUIDName(realName);
                    //d.获取随机的目录
                    String dir = UploadUtils.getDir();
                    //e.获取文件的内容（输入流）
                    InputStream is = fi.getInputStream();
                    //f.创建输出流
                    //获取products目录的真实路径
                    String pPath = request.getSession().getServletContext().getRealPath("/uploads");
                    //创建随机目录
                    File dirFile = new File(pPath, dir);
                    if (!dirFile.exists()) {
                        dirFile.mkdirs();
                    }
                    FileOutputStream os = new FileOutputStream(new File(dirFile, uuidName));
                    //g.对拷流
                    IOUtils.copy(is, os);
                    //h.释放资料
                    os.close();
                    is.close();
                    //i.删除临时文件
                    fi.delete();
                    //j.将商品的路径放入map中
                    map.put(key, "uploads" + dir + "/" + uuidName);
                }
            }

            //1.封装product对象
            Data d = new Data();
            BeanUtils.populate(d, map);

            //2.调用service完成保存操作
            dataService.insert(d);

            //3.重定向
            //resp.sendRedirect(req.getContextPath() + "/adminProduct?method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
            //request.setAttribute("msg", "未知异常，保存失败！");
            //request.getRequestDispatcher("/admin/welcome.jsp").forward(req, resp);
        }
        return "redirect:/showAllData";
    }

    @RequestMapping("/dataDownload")
    public String dataDownload(String dataPath, String dataName, HttpServletRequest request, HttpServletResponse response) {
        String pPath = request.getSession().getServletContext().getRealPath("/");
        dataPath = pPath + dataPath;
        *//*System.out.println(pPath);
        System.out.println(dataPath);*//*
        try {
            File file = new File(dataPath);

            System.out.println(dataPath);

            if (file.exists()) {

                //下载注意事项1：设置下载文件的mimeType
                String filename = dataName + dataPath.substring(dataPath.lastIndexOf('.'));
                String mimeType = request.getSession().getServletContext().getMimeType(filename);
                response.setContentType(mimeType);

                String agent=request.getHeader("user-agent");
               *//* if (agent.contains("MSIE")) {
                    // IE浏览器
                    filename = URLEncoder.encode(filename, "utf-8");
                    filename = filename.replace("+", " ");//utf-8的问题
                } else if (agent.contains("Firefox")) {
                    // 火狐浏览器
                    BASE64Encoder base64Encoder = new BASE64Encoder();
                    filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
                }else {
                    // 其它浏览器
                    filename = URLEncoder.encode(filename, "utf-8");
                    filename = filename.replace("+", " ");//Google
                }*//*

                //下载注意事项2：永远是下载--设置响应头
                response.setHeader("content-disposition", "attachment;filename=" + filename);

                byte[] bys = FileUtils.readFileToByteArray(file);//将指定文件读取到byte[]数组中。
                response.getOutputStream().write(bys);

            } else {
                throw new RuntimeException("资料不存在！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }*/

    @RequestMapping("/managerIndex")
    public String managerIndex(){
        return "showAllData";
    }


    @RequestMapping("/searchDatas")
    public String searchDatas(Model model, Integer dataLevel, Integer courseId){
        DataExample dataExample = new DataExample();
        DataExample.Criteria cri =dataExample.createCriteria();
        if(dataLevel != null){
            cri.andDataLevelEqualTo(dataLevel);
        }
        if(courseId != null){
            cri.andCourseIdEqualTo(courseId);
        }
        List<Data> datas = dataService.selectByExample(dataExample);
        model.addAttribute("datas",datas);
        return "showAllData";
    }

}
