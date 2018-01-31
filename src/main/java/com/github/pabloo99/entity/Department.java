package com.github.pabloo99.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Department {

    private Integer departmentId;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;

}
