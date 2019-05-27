package com.wyt.labinformationmanagementsystem.service;

import com.wyt.labinformationmanagementsystem.mapper.*;
import com.wyt.labinformationmanagementsystem.model.db.*;
import com.wyt.labinformationmanagementsystem.model.vo.PageBean;
import com.wyt.labinformationmanagementsystem.model.vo.ReportCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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

    @Autowired
    ReportMapper reportMapper;

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

    public PageBean<Lab> getLabsLimit(Integer currentPage, Integer currentCount) {
        PageBean<Lab> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = labMapper.getTotalLabCount();

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Lab> labs = labMapper.getLabsLimit(index, currentCount);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(labs);

        return pageBean;
    }

    public Lab getLabByLabId(Integer labId) {
        return labMapper.getLabByLabId(labId);
    }

    public void updateLabInfo(Lab lab) {
        labMapper.updateLabInfo(lab);
    }

    public void deleteLabInfoByLabId(Integer labId) {
        labMapper.deleteLabInfoByLabId(labId);
    }

    public void insertLab(Lab lab) {
        labMapper.insertLab(lab);
    }

    public PageBean<Lab> getLabsLimitByCondition(Integer currentPage, Integer currentCount, Lab lab) {
        PageBean<Lab> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = labMapper.getTotalLabCountByCondition(lab);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Lab> labs = labMapper.getLabsLimitByCondition(index, currentCount, lab);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(labs);

        return pageBean;
    }

    public PageBean<Student> getStusLimit(Integer currentPage, Integer currentCount) {
        PageBean<Student> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = studentMapper.getTotalStuCount();

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Student> stus = studentMapper.getStusLimit(index, currentCount);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(stus);

        return pageBean;
    }

    public Student getStuByStuId(Integer stuId) {
        return studentMapper.getStudentByStudentId(stuId);
    }

    public void updateStuInfo(Student stu) {
        studentMapper.updateStudentInfo(stu);
    }

    public void deleteStuInfoByStuId(Integer stuId) {
        studentMapper.deleteStuInfoByStuid(stuId);
    }

    public void insertStu(Student stu) {
        studentMapper.insertStu(stu);
    }

    public PageBean<Student> getStusLimitByCondition(Integer currentPage, Integer currentCount, Student stu) {
        PageBean<Student> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = studentMapper.getTotalStuCountByCondition(stu);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Student> stus = studentMapper.getStusLimitByCondition(index, currentCount, stu);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(stus);

        return pageBean;
    }

    public Integer getGroupIdByGroupName(String groupName) {
        Integer onlyOne = groupMapper.getTotalGroupCountByGroupName(groupName);
        if(onlyOne != 1){
            return null;
        }
        return groupMapper.getGroupIdByGroupName(groupName);
    }

    public PageBean<Course> getCoursesLimit(Integer currentPage, Integer currentCount) {
        PageBean<Course> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = courseMapper.getTotalCourseCount();

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Course> courses = courseMapper.getCourseLimit(index, currentCount);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(courses);

        return pageBean;
    }

    public Course getCourseByCourseId(Integer courseId) {
        return courseMapper.getCourseByCourseId(courseId);
    }

    public Integer getTeacherIdByTeacherName(String teacherName) {
        Integer onlyOne = teacherMapper.getTotalTeacherCountByTeacherName(teacherName);
        if(onlyOne != 1){
            return null;
        }
        return teacherMapper.getTeacherIdByTeacherName(teacherName);
    }

    public void updateCourseInfo(Course course) {
        courseMapper.updateCourseInfo(course);
    }

    public void deleteCourseInfoByCourseId(Integer courseId) {
        courseMapper.deleteCourseInfoByCourseId(courseId);
    }

    public void insertCourse(Course course) {
        courseMapper.insertCourse(course);
    }

    public PageBean<Course> getCoursesLimitByCondition(Integer currentPage, Integer currentCount, Course course) {
        PageBean<Course> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = courseMapper.getTotalCourseCountByCondition(course);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Course> courses = courseMapper.getCoursesLimitByCondition(index, currentCount, course);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(courses);

        return pageBean;
    }

    public PageBean<Report> getReportsLimit(Integer currentPage, Integer currentCount) {
        PageBean<Report> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = reportMapper.getReportTotalCount();

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Report> reports = reportMapper.getReportsLimit(index, currentCount);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(reports);

        return pageBean;
    }

    public Report getReportByReportId(Integer reportId) {
        return reportMapper.getReportByReportId(reportId);
    }

    public PageBean<Report> getReportsLimitByCondition(Integer currentPage, Integer currentCount, ReportCondition reportCondition) {
        PageBean<Report> pageBean = new PageBean<>();

        String formatDate = null;
        if(reportCondition.getOrderDate()!=null){
            formatDate =  new SimpleDateFormat("yyyy-MM-dd").format(reportCondition.getOrderDate());
        }

        Integer totalCount = 0;
        totalCount = reportMapper.getTotalCountByCondition(reportCondition, formatDate);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Report> reports = reportMapper.getReportsLimitByCondition(index, currentCount, reportCondition, formatDate);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(reports);

        return pageBean;
    }
}
