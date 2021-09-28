package com.opsu.dao.impl;

import com.opsu.dao.ConsumerDao;
import com.opsu.dao.mapper.ConsumerMapper;
import com.opsu.models.Consumer;
import com.opsu.models.Vendor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public class ConsumerDaoImpl implements ConsumerDao {

    private static final Logger LOG = Logger.getLogger(ConsumerDaoImpl.class);
    //Утилита от Спринга для работы с БД
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ConsumerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Consumer findConsumerByLastName(String lastName) {
        return jdbcTemplate.queryForObject(GET_CONSUMER_BY_LAST_NAME, new ConsumerMapper(),lastName, lastName);
    }

    public void create(Consumer consumer) { try {
        jdbcTemplate.update(CREATE_CONSUMER, consumer.getLastName(),consumer.getFirstName());
    } catch (DataAccessException e) {
        LOG.error(e.getMessage(), e);
    }
        //save
    }

    public void update(Consumer consumer) {
        //save
    }

}
