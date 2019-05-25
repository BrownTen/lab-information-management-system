package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentMapper {

    /////////////////////////////////////////////////////
    @Select("select * from student_tbl where stu_number = #{number}")
    Student findStudentByStuNumber(String number);

    @Select("select * from student_tbl where stu_id = #{stuId}")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId"))
    })
    Student getStudentByStudentId(Integer stuId);
}
