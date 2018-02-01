package com.github.pabloo99.facade;

import com.github.pabloo99.dao.EmployeeDao;
import com.github.pabloo99.dto.EmployeeDto;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFacade {

    private EmployeeDao employeeDao;

    public EmployeeFacade(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<EmployeeDto> createShortEmployeeList(){
        return employeeDao.findAll().stream()
                .map(employee -> new EmployeeDto(employee.getFirstName(), employee.getLastName()))
                .collect(Collectors.toList());
    }
}
