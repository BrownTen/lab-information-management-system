package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from order_tbl where order_id in " +
            "(select order_id from order_tbl where lab_id = #{labId})")
    @Results({
            @Result(property = "lab", column = "lab_id",
            many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByOrderId", fetchType = FetchType.LAZY)),
            @Result(property = "course", column = "course_id",
            many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByOrderId", fetchType = FetchType.LAZY))
    })
    List<Order> getOrdersByLabid(Integer labId);

    @Select("select * from order_tbl where order_id in " +
            "(select course_id from order_tbl where course_id = #{courseId})")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByOrderId", fetchType = FetchType.LAZY)),
            @Result(property = "course", column = "course_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByOrderId", fetchType = FetchType.LAZY))
    })
    List<Order> getOrdersByCourseid(Integer courseId);

    @Select("select count(*) from order_tbl where order_status = 0")
    Integer getTotalCountByStatus0();

    @Select("select * from order_tbl where order_status = 0 limit #{index},#{currentCount}")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByOrderId", fetchType = FetchType.LAZY)),
            @Result(property = "course", column = "course_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByOrderId", fetchType = FetchType.LAZY))
    })
    List<Order> getOrdersLimitStatus0(Integer index, Integer currentCount);

    @Select("<script>" +
                "select * from order_tbl where order_status = 0 " +
                "<if test = 'orderDate!=null'>" +
                "and order_date = #{orderDate} " +
                "</if> " +
                "<if test = 'orderTime!=null'> " +
                "and order_time = #{orderTime} " +
                "</if> " +
                "limit #{index},#{currentCount} " +
            "</script>")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByOrderId", fetchType = FetchType.LAZY)),
            @Result(property = "course", column = "course_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByOrderId", fetchType = FetchType.LAZY))
    })
    List<Order> getOrdersLimitStatus0ByCondition(Integer index, Integer currentCount, String orderDate, Integer orderTime);

    @Select("<script>" +
                "select count(*) from order_tbl where order_status = 0 " +
                "<if test = 'orderDate!=null'>" +
                "and order_date = #{orderDate} " +
                "</if> " +
                "<if test = 'orderTime!=null'> " +
                "and order_time = #{orderTime} " +
                "</if> " +
            "</script>")
    Integer getTotalCountByStatus0ByCondition(String orderDate, Integer orderTime);

    @Update("update order_tbl set order_status = 2, course_id = #{courseId} where order_id = #{orderId}")
    void updateOrderStatusByOrderId(Integer orderId, Integer courseId);

    @Select("select count(*) from order_tbl where order_status != 0 and " +
            "course_id in (select course_id from course_tbl where teacher_id = #{teacherId})")
    Integer getTotalCountByStatus123ByTeacherId(Integer teacherId);

    @Select("select * from order_tbl where order_status != 0 and " +
            "course_id in (select course_id from course_tbl where teacher_id = #{teacherId}) " +
            "limit #{index},#{currentCount}")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByOrderId", fetchType = FetchType.LAZY)),
            @Result(property = "course", column = "course_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByOrderId", fetchType = FetchType.LAZY))
    })
    List<Order> getOrdersLimitStatus123ByTeacherId(Integer index, Integer currentCount, Integer teacherId);
}

