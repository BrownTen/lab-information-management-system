package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {
    /////////////////////////////////////////////////////
    @Select("select * from teacher_tbl where teacher_number = #{number}")
    Teacher findTeacherByTeacherNumber(String number);

    /////////////////////////////////////////////////////
    @Select("select * from teacher_tbl where teacher_id = #{id}")
    Teacher findTeacherByTeacherId(Integer id);

    /////////////////////////////////////////////////////
    @Delete("delete from teacher_tbl where teacher_id = #{teacherId}")
    void deleteTeacherByTeacherId(Integer teacherId);

    /////////////////////////////////////////////////////
    @Insert("insert into teacher_tbl(teacher_number,teacher_password,teacher_name,teacher_phone,teacher_email) " +
            "values(#{teacherNumber},#{teacherPassword},#{teacherName},#{teacherPhone},#{teacherEmail})")
    @Options(useGeneratedKeys = true)
    void insertTeacher(Teacher teacher);

    /////////////////////////////////////////////////////
    @Select("select count(*) from teacher_tbl")
    Integer getTotalCount();

    /////////////////////////////////////////////////////
    @Select("select * from teacher_tbl limit ${index}, ${currentCount}")
    List<Teacher> getTeachersLimit(Integer index, Integer currentCount);

    /////////////////////////////////////////////////////
    @Update("update teacher_tbl set teacher_number=#{teacherNumber}, teacher_password=#{teacherPassword}, " +
            "teacher_name=#{teacherName}, teacher_phone=#{teacherPhone}, teacher_email=#{teacherEmail} " +
            "where teacher_id=#{teacherId}")
    void updateTeacherInfo(Teacher teacher);

    /////////////////////////////////////////////////////
    @Select("<script>" +
                "select * from teacher_tbl where 1=1 " +
                "<if test = 'teacherNumber!=null'>" +
                "and teacher_number like concat('%',#{teacherNumber},'%') " +
                "</if>" +
                "<if test = 'teacherName!=null'>" +
                "and teacher_name like concat('%',#{teacherName},'%') " +
                "</if>" +
            "</script>")
    List<Teacher> getTeachersByCondition(Teacher teacher);

    @Select("select count(*) from teacher_tbl where teacher_name like concat('%', #{teacherName}, '%')")
    Integer getTotalTeacherCountByTeacherName(String teacherName);

    @Select("select teacher_id from teacher_tbl where teacher_name like concat('%', #{teacherName}, '%')")
    Integer getTeacherIdByTeacherName(String teacherName);
}

