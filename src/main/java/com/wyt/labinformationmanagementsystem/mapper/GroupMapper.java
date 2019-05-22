package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Course;
import com.wyt.labinformationmanagementsystem.model.db.Group;
import com.wyt.labinformationmanagementsystem.model.db.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface GroupMapper {

    @Select("select * from group_tbl where group_id in " +
            "(select group_id from course_tbl where course_id = #{courseId})")
    @Results({
            @Result(property = "courses", column = "course_id", javaType = Course.class,
            many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCoursesByGroupId", fetchType = FetchType.LAZY)),
    })
    List<Group> getGroupsByCourseId(Integer courseId);

    @Select("select * from group_tbl where group_id in " +
            "(select group_id from student_tbl where stu_id = #{stuId)")
    @Results({
            @Result(property = "students", column = "stu_id", javaType = Student.class,
            one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.StudentMapper.getStudentsByGroupId", fetchType = FetchType.LAZY))
    })
    Group getGroupByStuId(Integer stuId);

}
