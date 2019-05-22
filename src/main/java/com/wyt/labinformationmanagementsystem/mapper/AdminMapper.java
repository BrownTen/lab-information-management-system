package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Admin;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface AdminMapper {
    @Select("select * from admin_tbl where adm_username = #{number}")
    Admin findAdminByAdminUsername(String number);

    @Select("select * from admin_tbl where adm_id in " +
            "(select adm_id from lab_tbl where lab_id = #{labId})")
    @Results({
            @Result(property = "labs", column = "lab_id",
            one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.LabMapper.getLabsByAdminId", fetchType = FetchType.LAZY))
    })
    Admin getAdminByLabId(Integer labId);
}
