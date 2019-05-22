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
public class Admin {
    private Integer admId;
    private String admUsername;
    private String admPassword;

    private List<Lab> labs;
}


