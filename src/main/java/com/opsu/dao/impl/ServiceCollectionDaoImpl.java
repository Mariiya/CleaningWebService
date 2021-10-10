package com.opsu.dao.impl;

import com.opsu.dao.ServiceCollectionDao;
import com.opsu.dao.mapper.ServiceCollectionMapper;
import com.opsu.dao.mapper.ServiceMapper;
import com.opsu.models.ServiceCollection;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class ServiceCollectionDaoImpl implements ServiceCollectionDao {
    //Logger for creating log records
    private static final Logger LOG = Logger.getLogger(ServiceCollectionDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public ServiceCollectionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ServiceCollection getServiceCollectionById(BigInteger id) throws NotFoundException {
        try{
            return jdbcTemplate.queryForObject(GET_SERVICECOLLECTION_BY_ID, new ServiceCollectionMapper(), id);
        }
        catch (DataAccessException e){
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Service Collection not found");
        }
    }

    @Override
    public void createServiceCollection(ServiceCollection serviceCollection) {
        try {
            jdbcTemplate.update(CREATE_SERVICECOLLECTION, serviceCollection.getOrderId(), serviceCollection.getServiceId());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void updateServiceCollection(ServiceCollection serviceCollection) {
        try {
            jdbcTemplate.update(UPDATE_SERVICECOLLECTION, serviceCollection.getOrderId(), serviceCollection.getServiceId(), serviceCollection.getId());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteServiceCollection(ServiceCollection serviceCollection) {
        try {
            return jdbcTemplate.update(DELETE_SERVICECOLLECTION, serviceCollection.getId()) == 1;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
