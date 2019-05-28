package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Report;
import com.wyt.labinformationmanagementsystem.model.vo.ReportCondition;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReportMapper {

    @Select("select count(*) from report_tbl where order_id in " +
            "(select order_id from order_tbl where order_status = 1 and course_id in " +
            "(select course_id from course_tbl where teacher_id = #{teacherId}))")
    Integer getReportTotalCountByTeacherId(Integer teacherId);

    @Select("select count(*) from report_tbl where stu_id = #{stuId}")
    Integer getReportTotalCountByStuId(Integer stuId);

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

    @Select("select * from report_tbl where stu_id = #{stuId} " +
            "order by report_status desc limit ${index},${currentCount}")
    @Results({
            @Result(property = "order", column = "order_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.OrderMapper.findOrderByOrderId")),
    })
    List<Report> getReportsLimitByStuIdOrderByStatusDesc(Integer index, Integer currentCount, Integer stuId);

    @Select("<script>" +
                "select count(*) from report_tbl where order_id in " +
                "(select order_id from order_tbl where order_status = 1 and course_id in " +
                "(select course_id from course_tbl where teacher_id = #{teacherId})) " +
                "<if test = 'reportCondition.stuNumber!=null'>" +
                "and stu_id in (select stu_id from student_tbl where stu_number like concat('%',#{reportCondition.stuNumber},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.stuName!=null'>" +
                "and stu_id in (select stu_id from student_tbl where stu_name like concat('%',#{reportCondition.stuName},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.groupName!=null'>" +
                "and stu_id in (select stu_id from student_tbl where group_id in " +
                    "(select group_id from group_tbl where group_name like concat('%',#{reportCondition.groupName},'%'))) " +
                "</if> " +
                "<if test = 'reportCondition.courseName!=null'>" +
                "and order_id in (select order_id from order_tbl where course_id in " +
                    "(select course_id from course_tbl where course_name like concat('%',#{reportCondition.courseName},'%'))) " +
                "</if> " +
                "<if test = 'orderDate!=null'>" +
                "and order_id in (select order_id from order_tbl where order_date like concat('%',#{orderDate},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.orderTime!=null'>" +
                "and order_id in (select order_id from order_tbl where order_time = #{reportCondition.orderTime}) " +
                "</if> " +
                "<if test = 'reportCondition.reportStatus!=null'>" +
                "and report_status = #{reportCondition.reportStatus} " +
                "</if> " +
            "</script>")
    Integer getTotalCountByConditionByTeacherId(ReportCondition reportCondition, Integer teacherId, String orderDate);

    @Select("<script>" +
                "select count(*) from report_tbl where stu_id = #{stuId} " +
                "<if test = 'reportCondition.teacherName!=null'>" +
                "and order_id in (select order_id from order_tbl where course_id in " +
                    "(select course_id from course_tbl where teacher_id in " +
                        "(select teacher_id from teacher_tbl where teacher_name like concat('%',#{reportCondition.teacherName},'%')))) " +
                "</if> " +
                "<if test = 'reportCondition.courseName!=null'>" +
                "and order_id in (select order_id from order_tbl where course_id in " +
                    "(select course_id from course_tbl where course_name like concat('%',#{reportCondition.courseName},'%'))) " +
                "</if> " +
                "<if test = 'orderDate!=null'>" +
                "and order_id in (select order_id from order_tbl where order_date like concat('%',#{orderDate},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.orderTime!=null'>" +
                "and order_id in (select order_id from order_tbl where order_time = #{reportCondition.orderTime}) " +
                "</if> " +
                "<if test = 'reportCondition.reportStatus!=null'>" +
                "and report_status = #{reportCondition.reportStatus} " +
                "</if> " +
            "</script>")
    Integer getTotalCountByConditionByStuId(ReportCondition reportCondition, Integer stuId, String orderDate);

    @Select("<script>" +
                "select * from report_tbl where order_id in " +
                "(select order_id from order_tbl where order_status = 1 and course_id in " +
                "(select course_id from course_tbl where teacher_id = #{teacherId})) " +
                "<if test = 'reportCondition.stuNumber!=null'>" +
                "and stu_id in (select stu_id from student_tbl where stu_number like concat('%',#{reportCondition.stuNumber},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.stuName!=null'>" +
                "and stu_id in (select stu_id from student_tbl where stu_name like concat('%',#{reportCondition.stuName},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.groupName!=null'>" +
                "and stu_id in (select stu_id from student_tbl where group_id in " +
                    "(select group_id from group_tbl where group_name like concat('%',#{reportCondition.groupName},'%'))) " +
                "</if> " +
                "<if test = 'reportCondition.courseName!=null'>" +
                "and order_id in (select order_id from order_tbl where course_id in " +
                    "(select course_id from course_tbl where course_name like concat('%',#{reportCondition.courseName},'%'))) " +
                "</if> " +
                "<if test = 'orderDate!=null'>" +
                "and order_id in (select order_id from order_tbl where order_date like concat('%',#{orderDate},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.orderTime!=null'>" +
                "and order_id in (select order_id from order_tbl where order_time = #{reportCondition.orderTime}) " +
                "</if> " +
                "<if test = 'reportCondition.reportStatus!=null'>" +
                "and report_status = #{reportCondition.reportStatus} " +
                "</if> " +
                "order by report_status limit ${index},${currentCount}" +
            "</script>")
    @Results({
            @Result(property = "order", column = "order_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.OrderMapper.findOrderByOrderId")),
            @Result(property = "student", column = "stu_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.StudentMapper.getStudentByStudentId"))
    })
    List<Report> getReportsLimitByConditionByTeacherIdOrderByStatus(Integer index, Integer currentCount, ReportCondition reportCondition, Integer teacherId, String orderDate);

    @Select("<script>" +
                "select * from report_tbl where stu_id = #{stuId} " +
                "<if test = 'reportCondition.teacherName!=null'>" +
                "and order_id in (select order_id from order_tbl where course_id in " +
                    "(select course_id from course_tbl where teacher_id in " +
                        "(select teacher_id from teacher_tbl where teacher_name like concat('%',#{reportCondition.teacherName},'%')))) " +
                "</if> " +
                "<if test = 'reportCondition.courseName!=null'>" +
                "and order_id in (select order_id from order_tbl where course_id in " +
                    "(select course_id from course_tbl where course_name like concat('%',#{reportCondition.courseName},'%'))) " +
                "</if> " +
                "<if test = 'orderDate!=null'>" +
                "and order_id in (select order_id from order_tbl where order_date like concat('%',#{orderDate},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.orderTime!=null'>" +
                "and order_id in (select order_id from order_tbl where order_time = #{reportCondition.orderTime}) " +
                "</if> " +
                "<if test = 'reportCondition.reportStatus!=null'>" +
                "and report_status = #{reportCondition.reportStatus} " +
                "</if> " +
                "order by report_status limit ${index},${currentCount}" +
            "</script>")
    @Results({
            @Result(property = "order", column = "order_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.OrderMapper.findOrderByOrderId")),
    })
    List<Report> getReportsLimitByConditionByStuIdOrderByStatusDesc(Integer index, Integer currentCount, ReportCondition reportCondition, Integer stuId, String orderDate);

    @Select("select * from report_tbl where report_id = #{reportId}")
    @Results({
            @Result(property = "order", column = "order_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.OrderMapper.findOrderByOrderId")),
            @Result(property = "student", column = "stu_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.StudentMapper.getStudentByStudentId"))
    })
    Report getReportByReportId(Integer reportId);

    @Update("update report_tbl set report_status = 1, report_score_part1 = #{reportScorePart1}, report_score_part2 = #{reportScorePart2} where report_id = #{reportId}")
    void updateReportScoreAndStatus(Report report);

    @Update("update report_tbl set report_status = 0, report_content_part1 = #{reportContentPart1}, report_content_part2 = #{reportContentPart2}, " +
            "report_content_part3 = #{reportContentPart3} where report_id = #{reportId}")
    void updateReportContent(Report report);

    @Select("select count(*) from report_tbl")
    Integer getReportTotalCount();

    @Select("select * from report_tbl order by report_status limit ${index},${currentCount}")
    @Results({
            @Result(property = "order", column = "order_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.OrderMapper.findOrderByOrderId")),
            @Result(property = "student", column = "stu_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.StudentMapper.getStudentByStudentId"))
    })
    List<Report> getReportsLimitOrderByStatus(Integer index, Integer currentCount);

    @Select("<script>" +
                "select count(*) from report_tbl where 1=1 " +
                "<if test = 'reportCondition.teacherName!=null'>" +
                    "and order_id in (select order_id from order_tbl where course_id in " +
                        "(select course_id from course_tbl where teacher_id in " +
                            "(select teacher_id from teacher_tbl where teacher_name like concat('%',#{reportCondition.teacherName},'%')))) " +
                "</if> " +
                "<if test = 'reportCondition.courseName!=null'>" +
                    "and order_id in (select order_id from order_tbl where course_id in " +
                        "(select course_id from course_tbl where course_name like concat('%',#{reportCondition.courseName},'%'))) " +
                "</if> " +
                "<if test = 'orderDate!=null'>" +
                    "and order_id in (select order_id from order_tbl where order_date like concat('%',#{orderDate},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.orderTime!=null'>" +
                    "and order_id in (select order_id from order_tbl where order_time = #{reportCondition.orderTime}) " +
                "</if> " +
                "<if test = 'reportCondition.reportStatus!=null'>" +
                    "and report_status = #{reportCondition.reportStatus} " +
                "</if> " +
                "<if test = 'reportCondition.stuName!=null'>" +
                    "and stu_id in (select stu_id from student_tbl where stu_name like concat('%', #{reportCondition.stuName},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.stuNumber!=null'>" +
                    "and stu_id in (select stu_id from student_tbl where stu_number like concat('%', #{reportCondition.stuNumber},'%')) " +
                "</if> " +
            "</script>")
    Integer getTotalCountByCondition(ReportCondition reportCondition, String orderDate);


    @Select("<script>" +
                "select * from report_tbl where 1=1 " +
                "<if test = 'reportCondition.teacherName!=null'>" +
                    "and order_id in (select order_id from order_tbl where course_id in " +
                        "(select course_id from course_tbl where teacher_id in " +
                            "(select teacher_id from teacher_tbl where teacher_name like concat('%',#{reportCondition.teacherName},'%')))) " +
                "</if> " +
                "<if test = 'reportCondition.courseName!=null'>" +
                    "and order_id in (select order_id from order_tbl where course_id in " +
                        "(select course_id from course_tbl where course_name like concat('%',#{reportCondition.courseName},'%'))) " +
                "</if> " +
                "<if test = 'orderDate!=null'>" +
                    "and order_id in (select order_id from order_tbl where order_date like concat('%',#{orderDate},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.orderTime!=null'>" +
                    "and order_id in (select order_id from order_tbl where order_time = #{reportCondition.orderTime}) " +
                "</if> " +
                "<if test = 'reportCondition.reportStatus!=null'>" +
                    "and report_status = #{reportCondition.reportStatus} " +
                "</if> " +
                "<if test = 'reportCondition.stuName!=null'>" +
                    "and stu_id in (select stu_id from student_tbl where stu_name like concat('%', #{reportCondition.stuName},'%')) " +
                "</if> " +
                "<if test = 'reportCondition.stuNumber!=null'>" +
                    "and stu_id in (select stu_id from student_tbl where stu_number like concat('%', #{reportCondition.stuNumber},'%')) " +
                "</if> " +
                "order by report_status limit ${index},${currentCount}" +
            "</script>")
    @Results({
            @Result(property = "order", column = "order_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.OrderMapper.findOrderByOrderId")),
            @Result(property = "student", column = "stu_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.StudentMapper.getStudentByStudentId"))
    })
    List<Report> getReportsLimitByConditionOrderByStatus(Integer index, Integer currentCount, ReportCondition reportCondition, String orderDate);

    @Insert("insert into report_tbl(report_status, order_id, stu_id) values(2, #{order.orderId}, #{student.stuId})")
    @Options(useGeneratedKeys = true)
    void insertReport(Report report);
}
