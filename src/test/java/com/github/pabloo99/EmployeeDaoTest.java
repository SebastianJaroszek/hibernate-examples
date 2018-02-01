package com.github.pabloo99;

import com.github.pabloo99.dao.EmployeeDao;
import com.github.pabloo99.dto.EmployeeGroupedByJobDto;
import com.github.pabloo99.entity.Employee;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Log4j
public class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    public EmployeeDaoTest() {
        employeeDao = new EmployeeDao();
    }

    @Test
    void shouldReturnAllEmployees(){
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> result = employeeDao.findAll();
        assertEquals(result.size(), 108);
    }

    @Test
    void shouldReturnEmployeeFoundedById(){
        EmployeeDao employeeDao = new EmployeeDao();
        Employee result = employeeDao.findEmployeeById(100);
        assertEquals(result.getLastName(), "King");
    }

    @Test
    void shouldSaveEmployeeAndDelete(){
        Employee employeeBeforeSave = employeeDao.findEmployeeById(100);
        employeeBeforeSave.setEmployeeId(607);
        employeeBeforeSave.setEmail("cos@cos7.pl");

        employeeDao.saveEmployee(employeeBeforeSave);

        Employee employeeAfterSave = employeeDao.findEmployeeById(607);

        employeeDao.deleteEmployeeById(607);

        assertEquals(employeeAfterSave, employeeBeforeSave);
    }

    @Test
    void shouldUpdateEmployee(){
        Employee employee = employeeDao.findEmployeeById(200);
        String oldName = employee.getFirstName();
        employee.setFirstName("Sebastian");

        employeeDao.updateEmployee(employee);

        Employee employeeAfterUpdate = employeeDao.findEmployeeById(200);

        employee.setFirstName(oldName);
        employeeDao.updateEmployee(employee);

        assertEquals(employeeAfterUpdate.getFirstName(), "Sebastian");

    }

    @Test
    public void shouldDisplayEmployeeCountByJob(){
        List<EmployeeGroupedByJobDto> result = employeeDao.countByJob();

        result.forEach(employeeGroupedByJobDto -> log.info(employeeGroupedByJobDto));

        assertTrue(result.size() > 0);
    }

}
