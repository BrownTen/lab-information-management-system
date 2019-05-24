package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Lab;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LabMapper {

    /////////////////////////////////////////////////////
    @Select("select * from lab_tbl where lab_id = #{labId}")
    @Results({
            @Result(property = "admin", column = "adm_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.AdminMapper.getAdminByAdmId")),
    })
    Lab getLabByLabId(Integer labId);

}
