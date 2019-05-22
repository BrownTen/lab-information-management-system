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
}

