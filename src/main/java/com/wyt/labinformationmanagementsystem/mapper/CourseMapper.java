package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("select count(*) from course_tbl where teacher_id = #{teacherId}")
    Integer getTotalCountByTeacherId(Integer teacherId);

    @Select("select count(*) from course_tbl")
    Integer getTotalCount();

    @Select("select * from course_tbl where course_id in " +
            "(select course_id from course_tbl where group_id = #{groupId})")
    @Results({
            @Result(property = "groups", column = "group_id",
            many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupsByCourseId")),
            @Result(property = "teachers", column = "teacher_id",
            many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.TeacherMapper.getTeachersByCourseId"))
    })
    List<Course> getCoursesByGroupId(Integer groupId);

    @Select("select * from course_tbl where course_id in " +
            "(select course_id from course_tbl where teacher_id = #{teacherId})")
    @Results({
            @Result(property = "teachers", column = "teacher_id",
            many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.TeacherMapper.getTeachersByCourseId")),
            @Result(property = "groups", column = "group_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupsByCourseId"))
    })
    List<Course> getCoursesByTeacherid(Integer teacherId);

    @Select("select * from course_tbl where course_id in " +
            "(select course_id from course_tbl where teacher_id = #{teacherId}) " +
            "and teacher_id = #{teacherId} limit ${index},${currentCount}")
    @Results({
            @Result(property = "teachers", column = "teacher_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.TeacherMapper.getTeachersByCourseId")),
            @Result(property = "groups", column = "group_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupsByCourseId"))
    })
    List<Course> getCoursesLimitByTeacherId(Integer index, Integer currentCount, Integer teacherId);

    @Select("select * from course_tbl limit ${index},${currentCount}")
    List<Course> getCoursesLimit(Integer index, Integer currentCount);
}
