package com.github.pabloo99.dao;

import com.github.pabloo99.connection.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HibernateDao<T> {

    private final Logger logger = Logger.getLogger(HibernateDao.class);
    private Class<T> type;

    public HibernateDao(Class<T> type) {
        this.type = type;
    }

    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;
        List<T> items = new ArrayList<>();

        try {
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(type);
            Root<T> root = query.from(type);
            query.select(root);
            Query<T> q = session.createQuery(query);
            items = q.getResultList();

            transaction.commit();

            return items;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }

        return items;
    }

    public T findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            T item = (T) session.get(type, id);
            transaction.commit();

            return item;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }

        return null;
    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            T item = (T) session.get(type, id);
            session.delete(item);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public void update(T item) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public void save(T item) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }
}
