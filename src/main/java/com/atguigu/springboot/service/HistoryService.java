package com.atguigu.springboot.service;

import com.atguigu.springboot.entity.History;
import com.atguigu.springboot.entity.HistoryExample;
import com.atguigu.springboot.mapper.HistoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryService {

    @Resource
    private HistoryMapper historyMapper;

    public int deleteByExample(HistoryExample example){
        return historyMapper.deleteByExample(example);
    }

    public List<History> selectByExample(HistoryExample example){
        return historyMapper.selectByExample(example);
    }

    public History selectByPrimaryKey(Integer historyId){
        return historyMapper.selectByPrimaryKey(historyId);
    }

    public int insert(History history){
        return historyMapper.insert(history);
    }

    public int updateByPrimaryKeySelective(History history){
        return historyMapper.updateByPrimaryKeySelective(history);
    }

    public int deleteByPrimaryKey(Integer historyId){
        return historyMapper.deleteByPrimaryKey(historyId);
    }
}
