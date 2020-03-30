package com.atguigu.springboot.service;

import com.atguigu.springboot.entity.UserLike;
import com.atguigu.springboot.mapper.UserLikeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLikeService {

    @Resource
    private UserLikeMapper userLikeMapper;

    public UserLike getUserLike(UserLike userLike){
        return userLikeMapper.getUserLike(userLike);
    }

    public int insertByCourseId(UserLike userLike){
        return userLikeMapper.insertByCourseId(userLike);
    }

    public int insertByCommentId(UserLike userLike){
        return userLikeMapper.insertByCommentId(userLike);
    }

    public void deleteUserLike(UserLike userLike) {
        userLikeMapper.deleteUserLike(userLike);
    }
}
