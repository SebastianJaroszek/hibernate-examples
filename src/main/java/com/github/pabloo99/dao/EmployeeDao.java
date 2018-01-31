package com.github.pabloo99.dao;

import com.github.pabloo99.connection.HibernateUtil;
import com.github.pabloo99.entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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

}
