package com.service;

import com.bean.Student;

import java.util.List;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/1/20 11:05
 */
public interface StudentService {
    Student queryStudent(String name, String pwd);

    List<Student> queryAllStudent();

    void removeStudent(Integer uid, Integer cid);

    Student queryStudentById(Integer uid);

    void saveStudent(Student stu);

    void addStudent(Student student);
}
