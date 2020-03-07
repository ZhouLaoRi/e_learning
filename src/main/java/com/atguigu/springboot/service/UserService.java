package com.atguigu.springboot.service;

import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.entity.UserExample;
import com.atguigu.springboot.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> selectByExample(UserExample example){
        return userMapper.selectByExample(example);
    }

    public User selectByPrimaryKey(Integer userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    public int updateByPrimaryKey(User user){
        return userMapper.updateByPrimaryKey(user);
    }
    public int insert(User user){
        return userMapper.insert(user);
    }
    public int deleteByExample(UserExample userExample){
        return userMapper.deleteByExample(userExample);
    }
}
