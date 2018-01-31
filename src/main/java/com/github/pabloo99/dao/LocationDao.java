package com.github.pabloo99.dao;

import com.github.pabloo99.entity.Location;

public class LocationDao extends HibernateDao<Location> {

    public LocationDao() {
        super(Location.class);
    }
}
