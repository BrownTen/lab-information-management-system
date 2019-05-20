package com.wyt.labinformationmanagementsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class Admin {
    private Integer admId;
    private String admUsername;
    private String admPassword;
}


