package com.atguigu.springboot.controller.back;

import com.atguigu.springboot.entity.History;
import com.atguigu.springboot.entity.HistoryExample;
import com.atguigu.springboot.service.HistoryService;
import com.atguigu.springboot.vo.HistoryVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class HistoryController {


    @Resource
    private HistoryService historyService;

    //查询所有历史记录返回列表页面
    @GetMapping("/historys")
    public String listHistory(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model , History history){

        HistoryExample historyExample = new HistoryExample();
        HistoryExample.Criteria criteria = historyExample.createCriteria();
        //xml里面是${}  直接替换变量的
        historyExample.setOrderByClause("history_date DESC");

        PageHelper.startPage(pageNum, 10);
        List<History> historys = historyService.selectByExample(historyExample);
        PageInfo<History> pageInfo = new PageInfo<History>(historys);
        model.addAttribute("pageInfo",pageInfo);
        return "history/list";
    }

    //用来条件查询的 ：查询所有历史记录返回列表页面
    @PostMapping("/historysAll")
    public String listAllHistory(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum ,Model model , HistoryVo historyVo){

        HistoryExample historyExample = new HistoryExample();
        HistoryExample.Criteria criteria = historyExample.createCriteria();
        historyExample.setOrderByClause("history_date DESC");

        if(historyVo.getUserId() != null && !"".equals(historyVo.getUserId() )){
            criteria.andUserIdEqualTo(historyVo.getUserId());
        }
        if(historyVo.getCourseId() != null && !"".equals(historyVo.getCourseId())){
            criteria.andCourseIdEqualTo(historyVo.getCourseId());
        }
        if(historyVo.getHistoryDateBegin() != null && !"".equals(historyVo.getHistoryDateBegin())
                &&historyVo.getHistoryDateBegin() != null && !"".equals(historyVo.getHistoryDateBegin())){
            criteria.andHistoryDateBetween(historyVo.getHistoryDateBegin(),historyVo.getHistoryDateEnd());
        }

        PageHelper.startPage(pageNum, 10);
        List<History> historys = historyService.selectByExample(historyExample);
        PageInfo<History> pageInfo = new PageInfo<History>(historys);
        model.addAttribute("pageInfo",pageInfo);

        return "history/list :: HistoryList";
    }


    //来到历史记录添加页面
    /*@RequestMapping("/history")
    public String addHistoryGuide(History history,Model model){
        model.addAttribute("historys",historyService.selectByExample(new HistoryExample()));
        return "history/add";
    }*/

    //历史记录添加
    /*@PostMapping("/history")
    public String addHistory(History history){
        history.setCreateTime(new Date());
        historyService.insertSelective(history);
        return "redirect:/historys";
    }*/

    //来到修改页面，查出当前，在页面回显
    @GetMapping("/history/{id}")
    public String toEditPage(@PathVariable Integer id, Model model){
        model.addAttribute("historys",historyService.selectByExample(new HistoryExample()));
        History history = historyService.selectByPrimaryKey(id);
        model.addAttribute("history",history);
        return "history/edit";
    }

    //历史记录修改:需要提交历史记录id;
    @PutMapping("/history")
    public String updateHistory(History history){
        historyService.updateByPrimaryKeySelective(history);
        return "redirect:/historys";
    }

    //历史记录删除
    @DeleteMapping("/history/{id}")
    public String deleteHistory(@PathVariable Integer id){
        historyService.deleteByPrimaryKey(id);
        return "redirect:/historys";
    }

    //历史记录恢复
    /*@PutMapping("/history/recover/{id}")
    public String recoverHistory(@PathVariable Integer historyId){
        historyService.recoverHistoryByPrimaryKey(historyId);
        return "redirect:/historys";
    }*/



}
