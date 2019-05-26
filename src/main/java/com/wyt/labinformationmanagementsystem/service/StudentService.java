package com.wyt.labinformationmanagementsystem.service;

import com.wyt.labinformationmanagementsystem.mapper.StudentMapper;
import com.wyt.labinformationmanagementsystem.model.db.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public void updateStudent(Student student) {
        studentMapper.updateStudentInfo(student);
    }
}
