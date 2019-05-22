package com.wyt.labinformationmanagementsystem.model.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class Student {
    private Integer stuId;
    private String stuNumber;
    private String stuPassword;
    private String stuName;
    private String stuPhone;
    private String stuEmail;

    private Group group;
}
