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
public class Lab {
    private Integer labId;
    private String labName;
    private String labAddress;

    private Admin admin;
    private List<Order> orders;
}
