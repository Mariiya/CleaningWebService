package com.opsu.dao.impl;

import com.opsu.dao.ConsumerDao;
import com.opsu.dao.mapper.ConsumerMapper;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Consumer;

import com.opsu.models.User;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
/**
 * An implementation of an interface ConsumerDao.
 * @author group 183
 * @version 2.1
 */
@Repository
public class ConsumerDaoImpl implements ConsumerDao {
    /**Logger for creating log records*/
    private static final Logger LOG = Logger.getLogger(ConsumerDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;

    /**required constructor*/
    @Autowired
    public ConsumerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /** search consumer by id
     * @param id consumer */
    @Override
    public Consumer getConsumerById(BigInteger id) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(GET_CONSUMER_BY_ID, new ConsumerMapper(), id);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Consumer not found");
        }
    }

    /**updating already existing consumer
     * @param consumer consumer */
    public boolean update(Consumer consumer) throws EmptyDataBaseException {
        try {
            jdbcTemplate.update(UPDATE_CONSUMER,consumer.getFirstName(),consumer.getLastName(), consumer.getId());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            throw new EmptyDataBaseException("Error during Consumer saving");
        }
        return true;
    }

    /**creating new consumer
     * @param consumer consumer*/
    public boolean save(Consumer consumer) throws EmptyDataBaseException {
        try {
            jdbcTemplate.update(CREATE_CONSUMER,consumer.getFirstName(),consumer.getLastName(),consumer.getId());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            throw new EmptyDataBaseException("Error during Consumer saving");
        }
        return true;
    }
}
