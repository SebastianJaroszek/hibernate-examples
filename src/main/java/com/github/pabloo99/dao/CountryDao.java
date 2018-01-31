package com.github.pabloo99.dao;

import com.github.pabloo99.connection.HibernateUtil;
import com.github.pabloo99.entity.Country;
import com.github.pabloo99.entity.Employee;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Log4j
public class CountryDao extends HibernateDao<Country>{

    public CountryDao() {
        super(Country.class);
    }

    public Country findById(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Country country = null;
        try {
            transaction = session.beginTransaction();
            country = session.get(Country.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return country;
    }
}