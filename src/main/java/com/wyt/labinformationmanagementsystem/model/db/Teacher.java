package com.wyt.labinformationmanagementsystem.model.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class Teacher {
    private Integer teacherId;
    private String teacherNumber;
    private String teacherPassword;
    private String teacherName;
    private String teacherPhone;
    private String teacherEmail;

    private List<Course> courses;
}
