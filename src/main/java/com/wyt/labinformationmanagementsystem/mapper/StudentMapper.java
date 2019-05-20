package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from student_tbl where stu_number = #{number}")
    Student findStudentByStuNumber(String number);

    @Select("select * from student_tbl")
    List<Student> findAllStudents();

    @Delete("delete from student_tbl where stu_id = #{stuId}")
    void deleteStudentByStuId(Integer stuId);

    @Insert("insert into student_tbl(stu_number,stu_password,stu_name,stu_phone,stu_email,class_id) " +
            "values(#{stuNumber},#{stuPassword},#{stuName},#{stuPhone},#{stuEmail},#{classId})")
    @Options(useGeneratedKeys = true)
    void insertStudent(Student student);

}
