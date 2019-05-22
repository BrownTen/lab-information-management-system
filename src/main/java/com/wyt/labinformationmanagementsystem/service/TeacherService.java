package com.wyt.labinformationmanagementsystem.service;

import com.wyt.labinformationmanagementsystem.mapper.CourseMapper;
import com.wyt.labinformationmanagementsystem.mapper.OrderMapper;
import com.wyt.labinformationmanagementsystem.mapper.TeacherMapper;
import com.wyt.labinformationmanagementsystem.model.db.Course;
import com.wyt.labinformationmanagementsystem.model.db.Order;
import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import com.wyt.labinformationmanagementsystem.model.vo.PageBean;
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

    public void updateOrderStatusByOrderId(Integer orderId) {
        orderMapper.updateOrderStatusByOrderId(orderId);
    }
}
