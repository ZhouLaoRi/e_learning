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

    public List<History> selectByExample(HistoryExample example){
        return historyMapper.selectByExample(example);
    }

    public int insert(History history){
        return historyMapper.insert(history);
    }
}
