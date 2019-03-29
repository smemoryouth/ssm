package com.mapper;

import com.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student select(@Param("name")String name, @Param("pwd") String pwd);

    List<Student> selectAll();

    void updateStudent(Student stu);
}