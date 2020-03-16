package com.atguigu.springboot.mapper;

import com.atguigu.springboot.entity.Type;
import com.atguigu.springboot.entity.TypeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeMapper {

    int countByExample(TypeExample example);

    int deleteByExample(TypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByExample(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> selectAllType();

}
