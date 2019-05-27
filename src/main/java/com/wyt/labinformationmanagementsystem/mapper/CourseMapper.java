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
            @Result(property = "teacher", column = "teacher_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.TeacherMapper.findTeacherByTeacherId"))
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

    @Select("select count(*) from course_tbl")
    Integer getTotalCourseCount();

    @Select("select * from course_tbl limit ${index},${currentCount}")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId")),
            @Result(property = "teacher", column = "teacher_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.TeacherMapper.findTeacherByTeacherId"))
    })
    List<Course> getCourseLimit(Integer index, Integer currentCount);

    @Update("update course_tbl set course_name = #{courseName}, teacher_id = #{teacher.teacherId}, group_id = #{group.groupId} where course_id = #{courseId}")
    void updateCourseInfo(Course course);

    @Delete("delete from course_tbl where course_id= #{courseId}")
    void deleteCourseInfoByCourseId(Integer courseId);

    @Insert("insert into course_tbl(course_name, teacher_id, group_id) " +
            "values(#{courseName}, #{teacher.teacherId}, #{group.groupId})")
    void insertCourse(Course course);

    @Select("<script>" +
                "select count(*) from course_tbl where 1=1 " +
                "<if test = 'courseName != null'>" +
                    "and course_name like concat('%',#{courseName},'%') " +
                "</if>" +
                "<if test = 'teacher.teacherName != null'>" +
                    "and teacher_id in (select teacher_id from teacher_tbl where teacher_name like concat('%', #{teacher.teacherName}, '%')) " +
                "</if>" +
                "<if test = 'group.groupName != null'>" +
                    "and group_id in (select group_id from group_tbl where group_name like concat('%', #{group.groupName}, '%')) " +
                "</if>" +
            "</script>")
    Integer getTotalCourseCountByCondition(Course course);

    @Select("<script>" +
                "select * from course_tbl where 1=1 " +
                "<if test = 'course.courseName != null'>" +
                    "and course_name like concat('%',#{course.courseName},'%') " +
                "</if>" +
                "<if test = 'course.teacher.teacherName != null'>" +
                    "and teacher_id in (select teacher_id from teacher_tbl where teacher_name like concat('%', #{course.teacher.teacherName}, '%')) " +
                "</if>" +
                "<if test = 'course.group.groupName != null'>" +
                    "and group_id in (select group_id from group_tbl where group_name like concat('%', #{course.group.groupName}, '%')) " +
                "</if> " +
                "limit ${index},${currentCount}" +
            "</script>")
    @Results({
            @Result(property = "group", column = "group_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.GroupMapper.getGroupByGroupId")),
            @Result(property = "teacher", column = "teacher_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.TeacherMapper.findTeacherByTeacherId"))
    })
    List<Course> getCoursesLimitByCondition(Integer index, Integer currentCount, Course course);
}
