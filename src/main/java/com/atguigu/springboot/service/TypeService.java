package com.atguigu.springboot.service;

import com.atguigu.springboot.entity.Type;
import com.atguigu.springboot.entity.TypeExample;
import com.atguigu.springboot.mapper.TypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeService {


    @Resource
    private TypeMapper typeMapper;

    public int insert(Type type){
        return typeMapper.insert(type);
    }

    public int insertSelective(Type type){
        return typeMapper.insertSelective(type);
    }

    public List<Type> selectByExample(TypeExample example){
        return typeMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer id){
        return typeMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Type type){
        return typeMapper.updateByPrimaryKey(type);
    }

    public List<Type> selectAllType(){
        return typeMapper.selectAllType();
    }

    public Type selectByPrimaryKey(Integer id){
        return typeMapper.selectByPrimaryKey(id);
    }

    public void updateByPrimaryKeySelective(Type type) {
        typeMapper.updateByPrimaryKeySelective(type);
    }

    public void recoverTypeByPrimaryKey(Integer typeId) {
        typeMapper.recoverTypeByPrimaryKey(typeId);
    }

    public void addTotalOne(Integer typeId) {
        typeMapper.addTotalOne(typeId);
    }
}
