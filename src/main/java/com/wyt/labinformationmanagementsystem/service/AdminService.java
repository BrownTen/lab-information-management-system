package com.wyt.labinformationmanagementsystem.service;

import com.wyt.labinformationmanagementsystem.mapper.*;
import com.wyt.labinformationmanagementsystem.model.db.Group;
import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import com.wyt.labinformationmanagementsystem.model.vo.PageBean;
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

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    LabMapper labMapper;

    @Autowired
    OrderMapper orderMapper;

    public PageBean<Teacher> getTeachersLimit(Integer currentPage, Integer currentCount) {
        PageBean<Teacher> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = teacherMapper.getTotalCount();

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Teacher> teachers = teacherMapper.getTeachersLimit(index, currentCount);

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

    public Teacher findTeacherByTeacherId(Integer id) {
        return teacherMapper.findTeacherByTeacherId(id);
    }

    public void updateTeacherInfo(Teacher teacher) {
        teacherMapper.updateTeacherInfo(teacher);
    }

    public List<Teacher> getTeachersByCondition(Teacher teacher) {
        return teacherMapper.getTeachersByCondition(teacher);
    }

    public PageBean<Group> getGroupsLimit(Integer currentPage, Integer currentCount) {
        PageBean<Group> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = groupMapper.getTotalGroupCount();

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Group> groups = groupMapper.getGroupsLimit(index, currentCount);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(groups);

        return pageBean;
    }

    public Group getGroupByGroupId(Integer groupId) {
        return groupMapper.getGroupByGroupId(groupId);
    }

    public void updateGroupInfo(Group group) {
        groupMapper.updateGroupInfo(group);
    }

    public void deleteGroupInfoByGroupId(Integer groupId) {
        groupMapper.deleteGroupInfoByGroupId(groupId);
    }

    public void insertGroup(Group group) {
        groupMapper.insertGroup(group);
    }

    public PageBean<Group> getGroupsLimitByCondition(Integer currentPage, Integer currentCount, Group group) {
        PageBean<Group> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = groupMapper.getTotalGroupCountByCondition(group);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Group> groups = groupMapper.getGroupsLimitByCondition(index, currentCount, group);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(groups);

        return pageBean;
    }
}
