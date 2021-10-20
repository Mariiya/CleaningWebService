package com.opsu.dao.impl;

import com.opsu.dao.ServiceDao;
import com.opsu.dao.mapper.ServiceMapper;
import com.opsu.models.Service;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class ServiceDaoImpl implements ServiceDao {
    //Logger for creating log records
    private static final Logger LOG = Logger.getLogger(ServiceDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    //required constructor
    public ServiceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //methods

    @Override
    public void addNewService(Service service) {
        try {
            jdbcTemplate.update(ADD_NEW_SERVICE, service.getName(), service.getDescription());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void changeService(Service service) {
        try {
            jdbcTemplate.update(CHANGE_SERVICE, service.getName(), service.getDescription(), service.getId());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteService(Service service) {
        try {
            return jdbcTemplate.update(DELETE_SERVICE, service.getId()) == 1;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Service getService(BigInteger id) throws NotFoundException  {
        try {
            return jdbcTemplate.queryForObject(GET_SERVICE_BY_ID, new ServiceMapper(), id);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Service not found");
        }
    }

    @Override
    public List<Service> getServices() throws NotFoundException {
        List<Service> serviceList;
        try {
            serviceList = jdbcTemplate.query(GET_SERVICES, new ServiceMapper());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Services not found");
        }

        return serviceList;
    }

}
