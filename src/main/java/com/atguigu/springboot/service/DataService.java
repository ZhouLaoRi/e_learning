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

    public Data selectByPrimaryKey(Integer dataId){
        return dataMapper.selectByPrimaryKey(dataId);
    }

    public int updateByPrimaryKey(Data data){
        return dataMapper.updateByPrimaryKey(data);
    }

    public int updateByPrimaryKeySelective(Data data){
        return dataMapper.updateByPrimaryKeySelective(data);
    }

    public int updateDataPath(Integer dataId ,String dataPath){
        return dataMapper.updateDataPath(dataId, dataPath);
    }

    public int insert(Data data){
        return dataMapper.insert(data);
    }

    public int deleteByExample(DataExample dataExample){
        return dataMapper.deleteByExample(dataExample);
    }
}
