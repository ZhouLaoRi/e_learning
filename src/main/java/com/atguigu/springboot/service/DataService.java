package com.atguigu.springboot.service;

import com.atguigu.springboot.entity.Data;
import com.atguigu.springboot.entity.DataExample;
import com.atguigu.springboot.mapper.DataMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataService {
    @Resource
    private DataMapper dataMapper;

    public List<Data> selectByExample(DataExample example){
        return dataMapper.selectByExample(example);
    }

    public int updateByPrimaryKey(Data data){
        return dataMapper.updateByPrimaryKey(data);
    }

    public int insert(Data data){
        return dataMapper.insert(data);
    }

    public int deleteByExample(DataExample dataExample){
        return dataMapper.deleteByExample(dataExample);
    }
}
