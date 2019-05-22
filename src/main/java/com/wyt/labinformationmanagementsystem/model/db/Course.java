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
public class Course {
    private Integer courseId;
    private String courseName;

    private List<Teacher> teachers;
    private List<Group> groups;
    private List<Order> orders;
}
