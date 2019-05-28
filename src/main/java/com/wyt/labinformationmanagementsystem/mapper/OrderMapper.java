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
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId")),
    })
    List<Order> getOrdersLimitStatus0(Integer index, Integer currentCount);

    /////////////////////////////////////////////////////
    @Select("<script>" +
                "select * from order_tbl where order_status = 0 " +
                "<if test = 'orderDate!=null'>" +
                "and order_date like concat('%',#{orderDate},'%') " +
                "</if> " +
                "<if test = 'orderTime!=null'> " +
                "and order_time = #{orderTime} " +
                "</if> " +
                "limit #{index},#{currentCount} " +
            "</script>")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId", fetchType = FetchType.LAZY)),
    })
    List<Order> getOrdersLimitStatus0ByCondition(Integer index, Integer currentCount, String orderDate, Integer orderTime);

    /////////////////////////////////////////////////////
    @Select("<script>" +
                "select count(*) from order_tbl where order_status = 0 " +
                "<if test = 'orderDate!=null'>" +
                "and order_date like concat('%',#{orderDate},'%') " +
                "</if> " +
                "<if test = 'orderTime!=null'> " +
                "and order_time = #{orderTime} " +
                "</if> " +
            "</script>")
    Integer getTotalCountByStatus0ByCondition(String orderDate, Integer orderTime);

    @Update("update order_tbl set order_status = 1 where order_id = #{orderId}")
    void updateOrderStatus1ByOrderId(Integer orderId);
    /////////////////////////////////////////////////////

    @Update("update order_tbl set order_status = 2, course_id = #{courseId} where order_id = #{orderId}")
    void updateOrderStatus2ByOrderId(Integer orderId, Integer courseId);

    @Update("update order_tbl set order_status = 3, order_message = #{orderMessage} where order_id = #{orderId}")
    void updateOrderStatus3ByOrderId(Integer orderId, String orderMessage);
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
                "and order_date like concat('%',#{orderDate},'%') " +
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
                "and order_date like concat('%',#{orderDate},'%') " +
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
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId")),
            @Result(property = "course", column = "course_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByCourseId"))
    })
    List<Order> getOrdersLimitByConditionByTeacherId(Integer index, Integer currentCount, OrderRecordCondition orderRecordCondition, Integer teacherId, String orderDate);

    @Insert("insert into order_tbl(order_date,order_time,order_status,lab_id) " +
            "values(#{orderDate},#{order.orderTime},0,#{order.lab.labId})")
    @Options(useGeneratedKeys = true)
    void insertOrder(Order order, String orderDate);

    @Select("select * from order_tbl where order_id = #{orderId}")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId")),
            @Result(property = "course", column = "course_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByCourseId"))
    })
    Order findOrderByOrderId(Integer orderId);

    @Select("select count(*) from order_tbl")
    Integer getTotalOrderCount();

    @Select("select * from order_tbl limit #{index},#{currentCount}")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId")),
            @Result(property = "course", column = "course_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByCourseId"))
    })
    List<Order> getOrdersLimit(Integer index, Integer currentCount);

    @Select("<script>" +
                "select count(*) from order_tbl where 1=1 " +
                "<if test = 'orderRecordCondition.labName!=null'>" +
                    "and lab_id in (select lab_id from lab_tbl where lab_name like concat('%',#{orderRecordCondition.labName},'%')) " +
                "</if> " +
                "<if test = 'orderDate!=null'> " +
                    "and order_date like concat('%',#{orderDate},'%') " +
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
                "<if test = 'orderRecordCondition.teacherName!=null'> " +
                    "and course_id in (select course_id from course_tbl where teacher_id in " +
                        "(select teacher_id from teacher_tbl where teacher_name like concat('%', #{orderRecordCondition.teacherName}, '%'))) " +
                "</if> " +
            "</script>")
    Integer getTotalOrderCountByCondition(OrderRecordCondition orderRecordCondition, String orderDate);

    @Select("<script>" +
                "select * from order_tbl where 1=1 " +
                "<if test = 'orderRecordCondition.labName!=null'>" +
                    "and lab_id in (select lab_id from lab_tbl where lab_name like concat('%',#{orderRecordCondition.labName},'%')) " +
                "</if> " +
                "<if test = 'orderDate!=null'> " +
                    "and order_date like concat('%',#{orderDate},'%') " +
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
                "<if test = 'orderRecordCondition.teacherName!=null'> " +
                    "and course_id in (select course_id from course_tbl where teacher_id in " +
                        "(select teacher_id from teacher_tbl where teacher_name like concat('%', #{orderRecordCondition.teacherName}, '%'))) " +
                "</if> " +
                "limit ${index},${currentCount}" +
            "</script>")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId")),
            @Result(property = "course", column = "course_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCourseByCourseId"))
    })
    List<Order> getOrdersLimitByCondition(Integer index, Integer currentCount, OrderRecordCondition orderRecordCondition, String orderDate);

    @Delete("delete from order_tbl where order_id = #{orderId}")
    void deleteOrderInfoByOrderId(Integer orderId);

    @Select("select * from order_tbl where order_id = #{orderId}")
    @Results({
            @Result(property = "lab", column = "lab_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabByLabId")),
    })
    Order getOrderByOrderId(Integer orderId);

    @Update("update order_tbl set lab_id = #{lab.labId}, order_date = #{orderDate}, order_time = #{orderTime} where order_id = #{orderId}")
    void updateOrderInfoByOrderId(Order order);

    @Select("select stu_id from student_tbl where group_id in (select group_id from course_tbl where course_id in " +
            "(select course_id from order_tbl where order_id = #{orderId}))")
    List<Integer> getStudentIdsByOrderId(Integer orderId);
}

