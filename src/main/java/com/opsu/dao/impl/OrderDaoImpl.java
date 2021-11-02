package com.opsu.dao.impl;

import com.opsu.dao.OrderDao;
import com.opsu.dao.mapper.OrderMapper;
import com.opsu.models.Order;
import com.opsu.models.Service;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collection;

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
    public Order getOrder(BigInteger id) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(GET_ORDER_BY_ID, new OrderMapper(), id);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Order not found");
        }
    }

    @Override
    public Collection<Order> getOrders() throws NotFoundException {
        try {
             return jdbcTemplate.query(GET_ORDERS, new OrderMapper());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(Service service) throws NotFoundException {
        try {
            return jdbcTemplate.query(GET_ORDERS_BY_SERVICE, new OrderMapper(), service.getId());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(float price) throws NotFoundException {
        try {
            return jdbcTemplate.query(GET_ORDERS_BY_PRICE, new OrderMapper(), price);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(String title) throws NotFoundException {
        try {
            return jdbcTemplate.query(GET_ORDERS_BY_TITLE, new OrderMapper());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public boolean createOrder(Order order) {
        try {
            return jdbcTemplate.update(SAVE_NEW_ORDER, order.getTitle(), order.getDescription(), order.getStatus(), order.getConsumer().getId(), order.getVendor().getId(), order.getStartDate(), order.getEndDate(), order.getPrice(), order.getAddress()) != 0;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        try {
            return jdbcTemplate.update(UPDATE_ORDER, order.getTitle(), order.getDescription(), order.getStatus(), order.getConsumer().getId(), order.getVendor().getId(), order.getStartDate(), order.getEndDate(), order.getPrice(), order.getAddress(), order.getId()) != 0;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteOrder(Order order) {
        try {
            return jdbcTemplate.update(DELETE_ORDER, order.getId()) == 1;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
