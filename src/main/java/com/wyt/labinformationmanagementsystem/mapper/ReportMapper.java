package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Report;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReportMapper {

    @Select("select count(*) from report_tbl where order_id in " +
            "(select order_id from order_tbl where order_status = 1 and course_id in " +
            "(select course_id from course_tbl where teacher_id = #{teacherId}))")
    Integer getTotalCountByTeacherId(Integer teacherId);

    @Select("select * from report_tbl where order_id in " +
            "(select order_id from order_tbl where order_status = 1 and course_id in " +
            "(select course_id from course_tbl where teacher_id = #{teacherId})) " +
            "limit ${index},${currentCount}")
    @Results({
            @Result(property = "order", column = "order_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.OrderMapper.findOrderByOrderId")),
            @Result(property = "student", column = "stu_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.StudentMapper.getStudentByStudentId"))
    })
    List<Report> getReportsLimitByTeacherId(Integer index, Integer currentCount, Integer teacherId);
}
