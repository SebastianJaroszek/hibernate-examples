package com.github.pabloo99.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
public class JobHistoryPK implements Serializable {

    protected Integer employeeId;
    protected Date startDate;
}
