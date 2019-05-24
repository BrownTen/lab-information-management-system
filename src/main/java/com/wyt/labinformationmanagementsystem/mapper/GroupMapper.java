package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GroupMapper {

    /////////////////////////////////////////////////////
    @Select("select * from group_tbl where group_id = #{groupID}")
    Group getGroupByGroupId(Integer groupId);

}
