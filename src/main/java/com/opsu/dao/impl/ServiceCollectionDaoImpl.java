package com.opsu.dao.impl;

import com.opsu.dao.ServiceCollectionDao;
import com.opsu.models.ServiceCollection;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class ServiceCollectionDaoImpl implements ServiceCollectionDao {
    //Logger for creating log records
    private static final Logger LOG = Logger.getLogger(ServiceCollectionDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public ServiceCollectionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ServiceCollection getServiceCollection() {
        return null;
    }

    @Override
    public void createServiceCollection() {

    }

    @Override
    public void updateServiceCollection() {

    }

    @Override
    public void deleteServiceCollection() {

    }
}
