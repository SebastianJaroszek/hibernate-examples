package com.github.pabloo99.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class EmployeeGroupedByJobDto {

    private Integer count;
    private String jobId;

}
