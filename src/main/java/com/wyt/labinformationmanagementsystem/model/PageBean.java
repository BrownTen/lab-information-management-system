package com.wyt.labinformationmanagementsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class PageBean<T> {
    private Integer currentPage;
    private Integer currentCount;
    private Integer totalCount;
    private Integer totalPage;

    private List<T> list;
}
