package com.wyt.labinformationmanagementsystem.service;

import com.wyt.labinformationmanagementsystem.mapper.CourseMapper;
import com.wyt.labinformationmanagementsystem.mapper.OrderMapper;
import com.wyt.labinformationmanagementsystem.mapper.ReportMapper;
import com.wyt.labinformationmanagementsystem.mapper.TeacherMapper;
import com.wyt.labinformationmanagementsystem.model.db.Course;
import com.wyt.labinformationmanagementsystem.model.db.Order;
import com.wyt.labinformationmanagementsystem.model.db.Report;
import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import com.wyt.labinformationmanagementsystem.model.vo.OrderRecordCondition;
import com.wyt.labinformationmanagementsystem.model.vo.PageBean;
import com.wyt.labinformationmanagementsystem.model.vo.ReportCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ReportMapper reportMapper;

    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacherInfo(teacher);
    }

    public PageBean<Course> getCoursesLimitByTeacherId(Integer currentPage, Integer currentCount, Integer teacherId) {
        PageBean<Course> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = courseMapper.getTotalCountByTeacherId(teacherId);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Course> courses = courseMapper.getCoursesLimitByTeacherId(index, currentCount, teacherId);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(courses);

        return pageBean;
    }

    public List<Course> getCoursesByCondition(String courseName, String groupName, Integer teacherId) {
        return courseMapper.getCoursesByCondition(courseName, groupName, teacherId);
    }

    public PageBean<Order> getOrdersLimitStatus0(Integer currentPage, Integer currentCount) {
        PageBean<Order> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = orderMapper.getTotalCountByStatus0();

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Order> orders = orderMapper.getOrdersLimitStatus0(index, currentCount);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(orders);

        return pageBean;
    }

    public PageBean<Order> getOrdersLimitStatus0ByCondition(Integer currentPage, Integer currentCount, Date orderDate, Integer orderTime) {
        PageBean<Order> pageBean = new PageBean<>();

        String formatDate = null;
        if(orderDate!=null){
            formatDate =  new SimpleDateFormat("yyyy-MM-dd").format(orderDate);
        }

        Integer totalCount = 0;
        totalCount = orderMapper.getTotalCountByStatus0ByCondition(formatDate, orderTime);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;

        List<Order> orders = orderMapper.getOrdersLimitStatus0ByCondition(index, currentCount, formatDate, orderTime);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(orders);

        return pageBean;
    }

    public void updateOrderStatus2ByOrderId(Integer orderId, Integer courseId) {
        orderMapper.updateOrderStatus2ByOrderId(orderId, courseId);
    }

    public PageBean<Order> getOrderedLabsLimitStatus123ByTeacherId(Integer currentPage, Integer currentCount, Integer teacherId) {
        PageBean<Order> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = orderMapper.getTotalCountByStatus123ByTeacherId(teacherId);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Order> orders = orderMapper.getOrdersLimitStatus123ByTeacherId(index, currentCount, teacherId);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(orders);

        return pageBean;
    }

    public PageBean<Order> getOrderedLabsLimitByConditionByTeacherId(Integer currentPage, Integer currentCount, OrderRecordCondition orderRecordCondition, Integer teacherId) {
        PageBean<Order> pageBean = new PageBean<>();

        String formatDate = null;
        if(orderRecordCondition.getOrderDate()!=null){
            formatDate =  new SimpleDateFormat("yyyy-MM-dd").format(orderRecordCondition.getOrderDate());
        }

        Integer totalCount = 0;
        totalCount = orderMapper.getTotalCountByConditionByTeacherId(orderRecordCondition, teacherId, formatDate);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Order> orders = orderMapper.getOrdersLimitByConditionByTeacherId(index, currentCount, orderRecordCondition, teacherId, formatDate);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(orders);

        return pageBean;
    }

    public void updateOrderStatus3ByOrderId(Integer orderId, String orderMessage) {
        orderMapper.updateOrderStatus3ByOrderId(orderId, orderMessage);
    }

    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    public Order findOrderByOrderId(Integer orderId) {
        return orderMapper.findOrderByOrderId(orderId);
    }

    public PageBean<Report> getReportsLimitByTeacherId(Integer currentPage, Integer currentCount, Integer teacherId) {
        PageBean<Report> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = reportMapper.getReportTotalCountByTeacherId(teacherId);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Report> reports = reportMapper.getReportsLimitByTeacherId(index, currentCount, teacherId);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(reports);

        return pageBean;
    }

    public PageBean<Report> getReportsLimitByConditionByTeacherId(Integer currentPage, Integer currentCount, ReportCondition reportCondition, Integer teacherId) {
        PageBean<Report> pageBean = new PageBean<>();

        String formatDate = null;
        if(reportCondition.getOrderDate()!=null){
            formatDate =  new SimpleDateFormat("yyyy-MM-dd").format(reportCondition.getOrderDate());
        }

        Integer totalCount = 0;
        totalCount = reportMapper.getTotalCountByConditionByTeacherId(reportCondition, teacherId, formatDate);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Report> reports = reportMapper.getReportsLimitByConditionByTeacherId(index, currentCount, reportCondition, teacherId, formatDate);

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

    public void updateReportScoreAndStatus(Report report) {
        reportMapper.updateReportScoreAndStatus(report);
    }
}
