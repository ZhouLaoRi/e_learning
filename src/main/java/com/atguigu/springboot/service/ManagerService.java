package com.atguigu.springboot.service;

import com.atguigu.springboot.entity.Manager;
import com.atguigu.springboot.entity.ManagerExample;
import com.atguigu.springboot.mapper.ManagerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerService {
    @Resource
    private ManagerMapper managerMapper;

    public List<Manager> selectByExample(ManagerExample example){
        return managerMapper.selectByExample(example);
    }
}
