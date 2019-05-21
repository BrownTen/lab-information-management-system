package com.wyt.labinformationmanagementsystem.model.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class Class {
    private Integer classId;
    private String className;
}
