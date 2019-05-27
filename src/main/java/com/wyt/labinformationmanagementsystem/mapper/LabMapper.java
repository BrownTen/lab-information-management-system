package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Lab;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LabMapper {

    /////////////////////////////////////////////////////
    @Select("select * from lab_tbl where lab_id = #{labId}")
    @Results({
            @Result(property = "admin", column = "adm_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.AdminMapper.getAdminByAdmId")),
    })
    Lab getLabByLabId(Integer labId);

    @Select("select count(*) from lab_tbl")
    Integer getTotalLabCount();

    @Select("select * from lab_tbl limit ${index},${currentCount}")
    @Results({
            @Result(property = "admin", column = "adm_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.AdminMapper.getAdminByAdmId")),
    })
    List<Lab> getLabsLimit(Integer index, Integer currentCount);

    @Update("update lab_tbl set lab_name = #{labName}, lab_Address = #{labAddress} where lab_id = #{labId}")
    void updateLabInfo(Lab lab);

    @Delete("delete from lab_tbl where lab_id = #{labId}")
    void deleteLabInfoByLabId(Integer labId);

    @Insert("insert into lab_tbl(lab_name, lab_address, adm_id) " +
            "values(#{labName}, #{labAddress}, 1)")
    @Options(useGeneratedKeys = true)
    void insertLab(Lab lab);

    @Select("<script>" +
                "select count(*) from lab_tbl where 1=1 " +
                "<if test = 'labName != null'> " +
                    "and lab_name like concat('%', #{labName}, '%') " +
                "</if>" +
                "<if test = 'labAddress != null'> " +
                    "and lab_address like concat('%', #{labAddress}, '%') " +
                "</if>" +
            "</script>")
    Integer getTotalLabCountByCondition(Lab lab);

    @Select("<script>" +
                "select * from lab_tbl where 1=1 " +
                "<if test = 'lab.labName != null'> " +
                    "and lab_name like concat('%', #{lab.labName}, '%') " +
                "</if>" +
                "<if test = 'lab.labAddress != null'> " +
                    "and lab_address like concat('%', #{lab.labAddress}, '%') " +
                "</if> " +
                "limit ${index},${currentCount}" +
            "</script>")
    @Results({
            @Result(property = "admin", column = "adm_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.AdminMapper.getAdminByAdmId")),
    })
    List<Lab> getLabsLimitByCondition(Integer index, Integer currentCount, Lab lab);
}
