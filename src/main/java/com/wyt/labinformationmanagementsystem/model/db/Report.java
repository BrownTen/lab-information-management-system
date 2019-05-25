package com.wyt.labinformationmanagementsystem.model.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class Report {
    private Integer reportId;
    private String reportContentPart1;
    private String reportContentPart2;
    private String reportContentPart3;
    private Integer reportStatus;
    private Double reportScorePart1;
    private Double reportScorePart2;

    private Order order;
    private Student student;
}
