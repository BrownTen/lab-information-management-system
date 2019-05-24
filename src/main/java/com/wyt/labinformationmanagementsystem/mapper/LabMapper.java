package com.wyt.labinformationmanagementsystem.mapper;

import com.wyt.labinformationmanagementsystem.model.db.Lab;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LabMapper {

    @Select("select * from lab_tbl where lab_id = #{labId}")
    @Results({
            @Result(property = "admin", column = "adm_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.AdminMapper.getAdminByAdmId")),
    })
    Lab getLabByLabId(Integer labId);

    @Select("select * from lab_tbl where adm_id = #{admId}")
    @Results({
            @Result(property = "admin", column = "adm_id",
            one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.AdminMapper.getAdminByLabId")),
            @Result(property = "orders", column = "order_id",
            many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.OrderMapper.getOrdersByLabId"))
    })
    List<Lab> getLabsByAdmId(Integer admId);


    @Select("select * from lab_tbl where lab_id in " +
            "(select lab_id from order_tbl where order_id = #{orderId})")
    @Results({
            @Result(property = "admin", column = "adm_id",
                    many = @Many(select = "com.wyt.labinformationmanagementsystem.mapper.AdminMapper.getAdminByLabId")),
            @Result(property = "orders", column = "order_id",
                    one = @One(select = "com.wyt.labinformationmanagementsystem.mapper.OrderMapper.getOrdersByLabId"))
    })
    Lab getLabByOrderId(Integer orderId);

}
