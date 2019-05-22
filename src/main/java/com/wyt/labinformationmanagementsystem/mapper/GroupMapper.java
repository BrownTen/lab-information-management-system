package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Course;
import com.wyt.labinformationmanagementsystem.model.db.Group;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface GroupMapper {

    @Select("select * from group_tbl where group_id in " +
            "(select group_id from course_tbl where course_id = #{courseId})")
    @Results({
            @Result(property = "courses", column = "course_id",javaType = Course.class,
            many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.CourseMapper.getCoursesByGroupId", fetchType = FetchType.LAZY)),
    })
    List<Group> getGroupsByCourseId(Integer courseId);

}
