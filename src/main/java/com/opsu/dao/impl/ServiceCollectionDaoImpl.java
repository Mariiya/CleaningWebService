package com.opsu.dao.impl;

import com.opsu.dao.ServiceCollectionDao;
import com.opsu.dao.mapper.ServiceCollectionMapper;
import com.opsu.dao.mapper.ServiceMapper;
import com.opsu.models.Order;
import com.opsu.models.ServiceCollection;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collection;
/**
 * An implementation of an interface ServiceCollectionDao.
 * @author group 183
 * @version 2.1
 */
@Repository
public class ServiceCollectionDaoImpl implements ServiceCollectionDao {
    /**Logger for creating log records*/
    private static final Logger LOG = Logger.getLogger(ServiceCollectionDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;
    /**required constructor*/
    public ServiceCollectionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /** search service collection by id
     * @param id service collection */
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
    /** search all  service collection */
    @Override
    public Collection<ServiceCollection> getServiceCollections() throws NotFoundException {
        try{
            return jdbcTemplate.query(GET_SERVICECOLLECTIONS, new ServiceCollectionMapper());
        }
        catch (DataAccessException e){
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Service Collections not found");
        }
    }
    /** search service collection by order
     * @param order service collection */
    @Override
    public Collection<ServiceCollection> getServiceCollectionsByOrder(Order order) throws NotFoundException {
        try{
            return jdbcTemplate.query(GET_SERVICECOLLECTIONS_BY_ORDER, new ServiceCollectionMapper(), order.getId());
        }
        catch (DataAccessException e){
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Service Collections not found");
        }
    }
    /** create new service collection
     * @param serviceCollection service collection */
    @Override
    public void createServiceCollection(ServiceCollection serviceCollection) {
        try {
            jdbcTemplate.update(CREATE_SERVICECOLLECTION, serviceCollection.getOrder().getId(), serviceCollection.getService().getId());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            jdbcTemplate.execute("rollback;");
        }finally {
            jdbcTemplate.execute("commit;");
        }
    }
    /** updating already existing service collection
     * @param serviceCollection service collection */
    @Override
    public void updateServiceCollection(ServiceCollection serviceCollection) {
        try {
            jdbcTemplate.update(UPDATE_SERVICECOLLECTION, serviceCollection.getOrder().getId(), serviceCollection.getService().getId(), serviceCollection.getId());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    /** deleting already existing service collection
     * @param serviceCollection service collection */
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
