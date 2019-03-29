package com.service.impl;

import com.bean.Student;
import com.mapper.StudentMapper;
import com.service.ClassService;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/1/20 12:02
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper mapper;

    @Autowired
    ClassService cs;

    @Override
    public Student queryStudent(String name, String pwd) {
        return mapper.select(name, pwd);
    }

    @Override
    public List<Student> queryAllStudent() {
        return mapper.selectAll();
    }

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public void removeStudent(Integer uid, Integer cid) {
        mapper.deleteByPrimaryKey(uid);
        cs.update(cid);
    }

    @Override
    public Student queryStudentById(Integer uid) {
        return mapper.selectByPrimaryKey(uid);
    }

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public void saveStudent(Student stu) {
        Student oldstu = mapper.selectByPrimaryKey(stu.getId());
        // 更新学生的修改信息
        mapper.updateStudent(stu);

        // 检查学生的班级是否改变，如果改变，则要修改班级人数
        if(!oldstu.getClazz().getId().equals(stu.getClazz().getId())){
            // 学生所在的原来班级人数减一，新班级人数加一
            cs.updateClazzStuNumberDesc(oldstu.getClazz().getId());
            cs.updateClazzStuNumberInc(stu.getClazz().getId());
        }

    }

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public void addStudent(Student student) {
        mapper.insert(student);
        cs.updateClazzStuNumberInc(student.getClazz().getId());
    }
}
