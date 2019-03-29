package com.mapper;

import com.bean.Clazz;

import java.util.List;

public interface ClazzMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Clazz record);

    int insertSelective(Clazz record);

    Clazz selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Clazz record);

    int updateByPrimaryKey(Clazz record);

    void updateByPrimaryKey(Integer cid);

    List<Clazz> selectAll();

    void updateClazzStuNumberDesc(Integer id);

    void updateClazzStuNumberInc(Integer id);
}