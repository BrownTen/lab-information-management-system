package com.wyt.labinformationmanagementsystem.service;

import com.wyt.labinformationmanagementsystem.mapper.StudentMapper;
import com.wyt.labinformationmanagementsystem.mapper.TeacherMapper;
import com.wyt.labinformationmanagementsystem.model.PageBean;
import com.wyt.labinformationmanagementsystem.model.Student;
import com.wyt.labinformationmanagementsystem.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    public List<Student> findAllStus(){
        return studentMapper.findAllStudents();
    }

    public List<Teacher> findAllTeachers(){
        return teacherMapper.findAllTeachers();
    }

    public PageBean<Teacher> getTeachersLimits(Integer currentPage, Integer currentCount) {
        PageBean<Teacher> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = teacherMapper.getTotalCount();

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Teacher> teachers = teacherMapper.getTeachersLimits(index, currentCount);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(teachers);

        return pageBean;
    }

    public void deleteTeacherInfoByTeacherId(Integer id) {
        teacherMapper.deleteTeacherByTeacherId(id);
    }

    public void insertTeacher(Teacher teacher) {
        teacherMapper.insertTeacher(teacher);
    }
}
