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
public class ReportCondition {
    private String stuNumber;
    private String stuName;
    private String groupName;
    private String courseName;
    private Date orderDate;
    private Integer orderTime;
    private Integer reportStatus;

    private String teacherName;
}
