package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    /////////////////////////////////////////////////////
    @Select("select * from student_tbl where stu_number = #{number}")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId"))
    })
    Student findStudentByStuNumber(String number);

    @Select("select * from student_tbl where stu_id = #{stuId}")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId"))
    })
    Student getStudentByStudentId(Integer stuId);

    @Update("update student_tbl set stu_number = #{stuNumber}, stu_password = #{stuPassword}, stu_name = #{stuName}, " +
            "stu_phone = #{stuPhone}, stu_email = #{stuEmail}, group_id = #{group.groupId} where stu_id = #{stuId}")
    void updateStudentInfo(Student student);

    @Select("select count(*) from student_tbl")
    Integer getTotalStuCount();

    @Select("select * from student_tbl limit ${index},${currentCount}")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId"))
    })
    List<Student> getStusLimit(Integer index, Integer currentCount);

    @Delete("delete from student_tbl where stu_id = #{stuId}")
    void deleteStuInfoByStuid(Integer stuId);

    @Insert("insert into student_tbl(stu_number, stu_password, stu_name, stu_phone, stu_email, group_id) " +
            "values(#{stuNumber}, #{stuPassword}, #{stuName}, #{stuPhone}, #{stuEmail}, #{group.groupId})")
    @Options(useGeneratedKeys = true)
    void insertStu(Student stu);

    @Select("<script>" +
                "select count(*) from student_tbl where 1=1 " +
                "<if test = 'stuNumber != null'> " +
                    "and stu_Number like concat('%', #{stuNumber}, '%') " +
                "</if>" +
                "<if test = 'stuName != null'> " +
                    "and stu_name like concat('%', #{stuName}, '%') " +
                "</if>" +
                "<if test = 'group.groupName != null'> " +
                    "and group_id in " +
                        "(select group_id from group_tbl where group_name like concat('%', #{group.groupName}, '%')) " +
                "</if>" +
            "</script>")
    Integer getTotalStuCountByCondition(Student stu);

    @Select("<script>" +
                "select * from student_tbl where 1=1 " +
                "<if test = 'stu.stuNumber != null'> " +
                    "and stu_Number like concat('%', #{stu.stuNumber}, '%') " +
                "</if>" +
                "<if test = 'stu.stuName != null'> " +
                    "and stu_name like concat('%', #{stu.stuName}, '%') " +
                "</if>" +
                "<if test = 'stu.group.groupName != null'> " +
                    "and group_id in " +
                        "(select group_id from group_tbl where group_name like concat('%', #{stu.group.groupName}, '%')) " +
                "</if> " +
                "limit ${index},${currentCount}" +
            "</script>")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId"))
    })
    List<Student> getStusLimitByCondition(Integer index, Integer currentCount, Student stu);
}