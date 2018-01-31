package com.github.pabloo99.dao;

import com.github.pabloo99.entity.Job;

public class JobDao extends HibernateDao<Job>{

    public JobDao() {
        super(Job.class);
    }
}
