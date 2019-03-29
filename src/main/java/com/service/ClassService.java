package com.service;

import com.bean.Clazz;

import java.util.List;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/1/20 11:05
 */
public interface ClassService {
    void update(Integer cid);

    List<Clazz> getAllClazz();

    void updateClazzStuNumberDesc(Integer id);

    void updateClazzStuNumberInc(Integer id);
}
