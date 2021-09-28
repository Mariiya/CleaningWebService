package com.opsu.dao.impl;

import com.opsu.dao.OrderDao;
import com.opsu.dao.mapper.OrderMapper;
import com.opsu.models.Order;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class OrderDaoImpl implements OrderDao {
    //Logger for creating log records
    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    //required constructor
    @Autowired
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //methods

    @Override
    public Order findOderById(BigInteger id) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(GET_ORDER_BY_ID, new OrderMapper(), id);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Order not found");
        }
    }

    @Override
    public void save(Order order) {
        try {
            jdbcTemplate.update(SAVE_NEW_ORDER, order.getTitle(), order.getStatus(), order.getConsumer().getId(), order.getVendor().getId(), order.getStartDate(), order.getEndDate(), order.getPrice(), order.getAddress());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Order order) {
        try {
            jdbcTemplate.update(UPDATE_ORDER, order.getTitle(), order.getStatus(), order.getConsumer().getId(), order.getVendor().getId(), order.getStartDate(), order.getEndDate(), order.getPrice(), order.getAddress(), order.getId());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Order order) {
        try {
            return jdbcTemplate.update(DELETE_ORDER, order.getId()) == 1;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
