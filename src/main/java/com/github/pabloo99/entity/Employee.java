package com.github.pabloo99.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Data
public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private double salary;
    private double commissionPct;
    private int managerId;
    private int departmentId;
}