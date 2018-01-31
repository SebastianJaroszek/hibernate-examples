package com.github.pabloo99.dao;

import com.github.pabloo99.connection.HibernateUtil;
import com.github.pabloo99.entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DepartmentDao {

    private static final Logger logger = Logger.getLogger(EmployeeDao.class);

    public Integer countEmployeesByDepartmentId(int departmentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("SELECT COUNT(*) AS count FROM Employee WHERE departmentId = :department_id");
            query.setParameter("department_id", departmentId);

            Long resultLong = (Long) query.getSingleResult();
            transaction.commit();

            return resultLong.intValue();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return 0;
    }

    /*public Employee getManager(int departmentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;
        Employee employee = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Department d" +
                    "JOIN Employee e ON d.manager_id = e.employee_id" +
                    "WHERE d.department_id = :department_id");
        }

    }*/

}
