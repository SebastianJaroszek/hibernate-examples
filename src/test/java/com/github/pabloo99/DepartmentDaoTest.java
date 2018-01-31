package com.github.pabloo99;

import com.github.pabloo99.dao.DepartmentDao;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DepartmentDaoTest {

    @Test
    public void shouldReturnEmployeeCount(){
        DepartmentDao departmentDao = new DepartmentDao();
        int count = departmentDao.countEmployeesByDepartmentId(60);
        assertEquals(count, 6);
    }

}
