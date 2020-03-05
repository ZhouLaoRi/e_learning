package com.atguigu.springboot.mapper;

import com.atguigu.springboot.entities.Department;
import org.apache.ibatis.annotations.*;

//指定这是一个操作数据库的mapper
@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    //加上这个可以有id也一起显示    使用自动增长 主键 是id
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName ) values (#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
