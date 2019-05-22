package com.wyt.labinformationmanagementsystem.model.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class Order {
    private Integer orderId;
    private Date orderDate;
    private Integer orderTime;
    private Integer orederStatus;
    private String orderMessage;

    private Integer labId;
    private Integer courseId;
}
