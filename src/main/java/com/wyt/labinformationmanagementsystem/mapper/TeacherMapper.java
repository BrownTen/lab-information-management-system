package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {
    @Select("select * from teacher_tbl where teacher_number = #{number}")
    Teacher findTeacherByTeacherNumber(String number);

    @Select("select * from teacher_tbl")
    List<Teacher> findAllTeachers();

    @Delete("delete from teacher_tbl where teacher_id = #{teacherId}")
    void deleteTeacherByTeacherId(Integer teacherId);

    @Insert("insert into teacher_tbl(teacher_number,teacher_password,teacher_name,teacher_phone,teacher_email) " +
            "values(#{teacherNumber},#{teacherPassword},#{teacherName},#{teacherPhone},#{teacherEmail})")
    @Options(useGeneratedKeys = true)
    void insertTeacher(Teacher teacher);

    @Select("select count(*) from teacher_tbl")
    Integer getTotalCount();

    @Select("select * from teacher_tbl limit ${index}, ${currentCount}")
    List<Teacher> getTeachersLimits(Integer index, Integer currentCount);
}
