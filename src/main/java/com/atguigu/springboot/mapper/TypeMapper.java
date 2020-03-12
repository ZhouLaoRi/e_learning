package com.atguigu.springboot.mapper;

import com.atguigu.springboot.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {

    int insert(Type type);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(Type type);

    List<Type> selectAllType();

    Type selectByPrimaryKey(Integer id);

}
