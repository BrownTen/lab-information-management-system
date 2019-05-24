package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    /////////////////////////////////////////////////////
    @Select("select * from student_tbl where stu_number = #{number}")
    Student findStudentByStuNumber(String number);

}
