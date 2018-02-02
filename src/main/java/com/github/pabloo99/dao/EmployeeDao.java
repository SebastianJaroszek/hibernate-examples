package com.github.pabloo99.dao;

import com.github.pabloo99.connection.HibernateUtil;
import com.github.pabloo99.dto.EmployeeDto;
import com.github.pabloo99.dto.EmployeeGroupedByJobDto;
import com.github.pabloo99.entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDao {

    private static final Logger logger = Logger.getLogger(EmployeeDao.class);

    public List<Employee> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List employees = new ArrayList();
        try {
            transaction = session.beginTransaction();
            employees = session.createQuery("FROM Employee").getResultList();

            transaction.commit();
            return employees;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }


        return employees;
    }

    public Employee findEmployeeById(int employeeId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Employee WHERE employeeId = :employee_id");
            query.setParameter("employee_id", employeeId);
            employee = (Employee)query.getSingleResult();
            transaction.commit();
            return employee;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return employee;
    }

    public void saveEmployee(Employee employee){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public void updateEmployee(Employee employee){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public void deleteEmployeeById(int employeeId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Employee employee = findEmployeeById(employeeId);
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public List<EmployeeGroupedByJobDto> countByJob(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        List<EmployeeGroupedByJobDto> result = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(*) AS empCountr, MAX(emp.jobId) AS jobTitle " +
                    "FROM Employee AS emp GROUP BY emp.jobId");

            List<?> items = query.getResultList();

            transaction.commit();

            return items.stream()
                    .map(o -> {
                        Object[] attributes = (Object[])o;
                        Long count = (Long) attributes[0];
                        return new EmployeeGroupedByJobDto(count.intValue(), (String)attributes[1]);
                    })
                    .collect(Collectors.toList());
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    public List<Employee> findWithNiceSalary(){
        //SELECT * FROM employees WHERE salary > (SELECT AVG(salary) FROM employees);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Employee> result = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Employee " +
                    "WHERE salary > (SELECT AVG(salary) FROM Employee)");
            result = query.getResultList();
            transaction.commit();
            return result;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

}
