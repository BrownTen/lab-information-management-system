package com.wyt.labinformationmanagementsystem.service;

import com.wyt.labinformationmanagementsystem.mapper.TeacherMapper;
import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public Teacher findTeacherByTeacherId(Integer id) {
        return teacherMapper.findTeacherByTeacherId(id);
    }

    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacherInfo(teacher);
    }
}
