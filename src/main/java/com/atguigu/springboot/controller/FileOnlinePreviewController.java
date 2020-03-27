package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class FileOnlinePreviewController {

    @ResponseBody
    @GetMapping("/preview")
    public void pdfStreamHandler(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "dataPath") String dataPath) {
        //PDF文件地址
        //File file = new File("C:\\Users\\Administrator\\Desktop\\usingthymeleaf.pdf");

        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            System.out.println(path + "/static" + dataPath);
            File file = new File(path + "/static" + dataPath);
            if (file.exists()) {
                byte[] data = null;
                FileInputStream input=null;
                try {
                    input= new FileInputStream(file);
                    data = new byte[input.available()];
                    input.read(data);
                    response.getOutputStream().write(data);
                } catch (Exception e) {
                    System.out.println("pdf文件处理异常：" + e);
                }finally{
                    try {
                        if(input!=null){
                            input.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //点击播放按钮，开始播放视频
    @GetMapping(value = "/videoPlayByIdAndAdmin")
    public String videoPlayByIdAndAdmin(Integer id, Model model) {
        model.addAttribute("title","小视频");
        model.addAttribute("path","/data1.mp4");
        model.addAttribute("musicPath","/Taylor Swift - Blank Space.mp3");
        return "pdfview";
    }
}
