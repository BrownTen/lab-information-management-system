package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Order;
import com.wyt.labinformationmanagementsystem.model.vo.OrderRecordCondition;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderMapper {

    /////////////////////////////////////////////////////
    @Select("select count(*) from order_tbl where order_status = 0")
    Integer getTotalCountByStatus0();

    /////////////////////////////////////////////////////
    @Select("select * from order_tbl where order_status = 0 limit #{index},#{currentCount}")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId")),
    })
    List<Order> getOrdersLimitStatus0(Integer index, Integer currentCount);

    /////////////////////////////////////////////////////
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
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId", fetchType = FetchType.LAZY)),
    })
    List<Order> getOrdersLimitStatus0ByCondition(Integer index, Integer currentCount, String orderDate, Integer orderTime);

    /////////////////////////////////////////////////////
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

    /////////////////////////////////////////////////////
    @Update("update order_tbl set order_status = 2, course_id = #{courseId} where order_id = #{orderId}")
    void updateOrderStatusByOrderId(Integer orderId, Integer courseId);

    /////////////////////////////////////////////////////
    @Select("select count(*) from order_tbl where order_status != 0 and " +
            "course_id in (select course_id from course_tbl where teacher_id = #{teacherId})")
    Integer getTotalCountByStatus123ByTeacherId(Integer teacherId);

    /////////////////////////////////////////////////////
    @Select("select * from order_tbl where order_status != 0 and " +
            "course_id in (select course_id from course_tbl where teacher_id = #{teacherId}) " +
            "limit #{index},#{currentCount}")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId")),
            @Result(property = "course", column = "course_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByCourseId"))
    })
    List<Order> getOrdersLimitStatus123ByTeacherId(Integer index, Integer currentCount, Integer teacherId);

    /////////////////////////////////////////////////////
    @Select("<script>" +
                "select count(*) from order_tbl where 1=1 " +
                "and order_status != 0 " +
                "and course_id in (select course_id from course_tbl where teacher_id = #{teacherId}) " +
                "<if test = 'orderRecordCondition.labName!=null'>" +
                "and lab_id in (select lab_id from lab_tbl where lab_name like concat('%',#{orderRecordCondition.labName},'%')) " +
                "</if> " +
                "<if test = 'orderDate!=null'> " +
                "and order_date = #{orderDate} " +
                "</if> " +
                "<if test = 'orderRecordCondition.orderTime!=null'> " +
                "and order_time = #{orderRecordCondition.orderTime} " +
                "</if> " +
                "<if test = 'orderRecordCondition.courseName!=null'> " +
                "and course_id in (select course_id from course_tbl where course_name like concat('%',#{orderRecordCondition.courseName},'%')) " +
                "</if> " +
                "<if test = 'orderRecordCondition.groupName!=null'> " +
                "and course_id in (select course_id from course_tbl where group_id in " +
                    "(select group_id from group_tbl where group_name like concat('%',#{orderRecordCondition.groupName},'%'))) " +
                "</if> " +
                "<if test = 'orderRecordCondition.orderStatus!=null'> " +
                "and order_status = #{orderRecordCondition.orderStatus} " +
                "</if> " +
            "</script>")
    Integer getTotalCountByConditionByTeacherId(OrderRecordCondition orderRecordCondition, Integer teacherId, String orderDate);

    /////////////////////////////////////////////////////
    @Select("<script>" +
                "select * from order_tbl where 1=1 " +
                "and order_status != 0 " +
                "and course_id in (select course_id from course_tbl where teacher_id = #{teacherId}) " +
                "<if test = 'orderRecordCondition.labName!=null'>" +
                "and lab_id in (select lab_id from lab_tbl where lab_name like concat('%',#{orderRecordCondition.labName},'%')) " +
                "</if> " +
                "<if test = 'orderDate!=null'> " +
                "and order_date = #{orderDate} " +
                "</if> " +
                "<if test = 'orderRecordCondition.orderTime!=null'> " +
                "and order_time = #{orderRecordCondition.orderTime} " +
                "</if> " +
                "<if test = 'orderRecordCondition.courseName!=null'> " +
                "and course_id in (select course_id from course_tbl where course_name like concat('%',#{orderRecordCondition.courseName},'%')) " +
                "</if> " +
                "<if test = 'orderRecordCondition.groupName!=null'> " +
                "and course_id in (select course_id from course_tbl where group_id in " +
                    "(select group_id from group_tbl where group_name like concat('%',#{orderRecordCondition.groupName},'%'))) " +
                "</if> " +
                "<if test = 'orderRecordCondition.orderStatus!=null'> " +
                "and order_status = #{orderRecordCondition.orderStatus} " +
                "</if> " +
                "limit #{index},#{currentCount} " +
            "</script>")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId")),
            @Result(property = "course", column = "course_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByCourseId"))
    })
    List<Order> getOrdersLimitByConditionByTeacherId(Integer index, Integer currentCount, OrderRecordCondition orderRecordCondition, Integer teacherId, String orderDate);
}

