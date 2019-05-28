package com.wyt.labinformationmanagementsystem.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class OrderRecordCondition {

    private String LabName;
    private Date orderDate;
    private Integer orderTime;
    private String courseName;
    private String groupName;
    private Integer orderStatus;

    private String teacherName;
}
