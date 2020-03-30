package com.atguigu.springboot.mapper;

import com.atguigu.springboot.entity.UserLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLikeMapper {

    UserLike getUserLike(UserLike userLike);

    int insertByCourseId(UserLike userLike);

    int insertByCommentId(UserLike userLike);

    void deleteUserLike(UserLike userLike);
}
