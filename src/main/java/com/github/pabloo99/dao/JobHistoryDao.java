package com.github.pabloo99.dao;

import com.github.pabloo99.entity.JobHistory;

public class JobHistoryDao extends HibernateDao<JobHistory> {

    public JobHistoryDao() {
        super(JobHistory.class);
    }
}
