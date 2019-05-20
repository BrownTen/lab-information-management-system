package com.wyt.labinformationmanagementsystem.service;

import com.wyt.labinformationmanagementsystem.mapper.AdminMapper;
import com.wyt.labinformationmanagementsystem.mapper.StudentMapper;
import com.wyt.labinformationmanagementsystem.mapper.TeacherMapper;
import com.wyt.labinformationmanagementsystem.model.Admin;
import com.wyt.labinformationmanagementsystem.model.Student;
import com.wyt.labinformationmanagementsystem.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    AdminMapper adminMapper;

    public Student stuentLogin(String number) {
        return studentMapper.findStudentByStuNumber(number);
    }

    public Teacher teacherLogin(String number) {
        return teacherMapper.findTeacherByTeacherNumber(number);
    }

    public Admin adminLogin(String number) {
        return adminMapper.findAdminByAdminUsername(number);
    }
}
