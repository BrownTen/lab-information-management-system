package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    /////////////////////////////////////////////////////
    @Select("select * from course_tbl where course_id = #{courseId}")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId")),
    })
    Course getCourseByCourseId(Integer courseId);

    /////////////////////////////////////////////////////
    @Select("select count(*) from course_tbl where teacher_id = #{teacherId}")
    Integer getTotalCountByTeacherId(Integer teacherId);

    /////////////////////////////////////////////////////
    @Select("select * from course_tbl where course_id in " +
            "(select course_id from course_tbl where teacher_id = #{teacherId}) " +
            "limit ${index},${currentCount}")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId")),
    })
    List<Course> getCoursesLimitByTeacherId(Integer index, Integer currentCount, Integer teacherId);

    /////////////////////////////////////////////////////
    @Select("<script>" +
                "select * from course_tbl where course_id in " +
                "(select course_id from course_tbl where teacher_id = #{teacherId}) " +
                "<if test = 'courseName!=null'>" +
                "and course_name like concat('%',#{courseName},'%') " +
                "</if>" +
                "<if test = 'groupName!=null'>" +
                "and group_id in " +
                "(select group_id from group_tbl where group_name like concat('%',#{groupName},'%')) " +
                "</if>" +
            "</script>")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId")),
    })
    List<Course> getCoursesByCondition(String courseName, String groupName, Integer teacherId);

}
