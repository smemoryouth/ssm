package com.service.impl;

import com.bean.Clazz;
import com.mapper.ClazzMapper;
import com.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/1/20 12:01
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClazzMapper mapper;

    @Override
    public void update(Integer cid) {
        mapper.updateByPrimaryKey(cid);
    }

    @Override
    public List<Clazz> getAllClazz() {
        return mapper.selectAll();
    }

    @Override
    public void updateClazzStuNumberDesc(Integer id) {
        mapper.updateClazzStuNumberDesc(id);
    }

    @Override
    public void updateClazzStuNumberInc(Integer id) {
        mapper.updateClazzStuNumberInc(id);
    }
}
