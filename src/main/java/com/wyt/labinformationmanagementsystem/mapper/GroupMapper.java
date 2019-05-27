package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupMapper {

    /////////////////////////////////////////////////////
    @Select("select * from group_tbl where group_id = #{groupID}")
    Group getGroupByGroupId(Integer groupId);

    @Select("select count(*) from group_tbl")
    Integer getTotalGroupCount();

    @Select("select * from group_tbl limit ${index},${currentCount}")
    List<Group> getGroupsLimit(Integer index, Integer currentCount);

    @Insert("insert into group_tbl(group_name) values(#{groupName})")
    @Options(useGeneratedKeys = true)
    void insertGroup(Group group);

    @Delete("delete from group_tbl where group_id = #{groupId}")
    void deleteGroupInfoByGroupId(Integer groupId);

    @Update("update group_tbl set group_name = #{groupName} where group_id = #{groupId}")
    void updateGroupInfo(Group group);

    @Select("<script>" +
                "select count(*) from group_tbl where 1=1 " +
                "<if test = 'groupName != null'> " +
                    "and group_name like concat('%', #{groupName}, '%') " +
                "</if>" +
            "</script>")
    Integer getTotalGroupCountByCondition(Group group);

    @Select("<script>" +
                "select * from group_tbl where 1=1 " +
                "<if test = 'group.groupName != null'> " +
                    "and group_name like concat('%', #{group.groupName}, '%') " +
                "</if> " +
                "limit ${index},${currentCount}" +
            "</script>")
    List<Group> getGroupsLimitByCondition(Integer index, Integer currentCount, Group group);

    @Select("select count(*) from group_tbl where group_name like concat('%', #{groupName}, '%')")
    Integer getTotalGroupCountByGroupName(String groupName);

    @Select("select group_id from group_tbl where group_name like concat('%', #{groupName}, '%')")
    Integer getGroupByGroupName(String groupName);
}
