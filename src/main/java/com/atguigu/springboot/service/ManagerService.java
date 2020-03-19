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

    public int countByExample(ManagerExample example){
        return managerMapper.countByExample(example);
    }

    public int deleteByExample(ManagerExample example){
        return managerMapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Integer managerId){
        return managerMapper.deleteByPrimaryKey(managerId);
    }

    public int insert(Manager record){
        return managerMapper.insert(record);
    }

    public int insertSelective(Manager record){
        return managerMapper.insertSelective(record);
    }

    public List<Manager> selectByExample(ManagerExample example){
        return managerMapper.selectByExample(example);
    }

    public Manager selectByPrimaryKey(Integer managerId){
        return managerMapper.selectByPrimaryKey(managerId);
    }

    public int updateByExampleSelective(Manager record, ManagerExample example){
        return managerMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Manager record, ManagerExample example){
        return managerMapper.updateByExample(record, example);
    }

    public int updateByPrimaryKeySelective(Manager record){
        return managerMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Manager record){
        return managerMapper.updateByPrimaryKey(record);
    }

    public int updateManagerAvatar(Integer managerId, String managerAvatar) {
        return managerMapper.updateManagerAvatar(managerId,managerAvatar);
    }

    public int recoverManagerByPrimaryKey(Integer managerId) {
        return managerMapper.recoverManagerByPrimaryKey(managerId);
    }
}
