package com.opsu.dao.impl;

import com.opsu.dao.OrderDao;
import com.opsu.dao.mapper.OrderMapper;
import com.opsu.dao.mapper.RowNumMapper;
import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.Vendor;
import com.opsu.models.enumeration.Status;
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
    public Collection<Order> getOrders(int page) throws NotFoundException {
        int downLimit = (page - 1) * 15;
        int upLimit = (downLimit + 15);
        try {
             return jdbcTemplate.query(GET_ORDERS, new OrderMapper(), downLimit, upLimit);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(Service service, int page) throws NotFoundException {
        try {
            return jdbcTemplate.query(GET_ORDERS_BY_SERVICE, new OrderMapper(), service.getId());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(Float minPrice, Float maxPrice, int page) throws NotFoundException {
        int downLimit = (page - 1) * 15;
        int upLimit = (downLimit + 15);
        try {
            return jdbcTemplate.query(GET_ORDERS_BY_PRICE, new OrderMapper(), minPrice, maxPrice, downLimit, upLimit);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(String title, int page) throws NotFoundException {
        int downLimit = (page - 1) * 15;
        int upLimit = (downLimit + 15);
        try {
            return jdbcTemplate.query(GET_ORDERS_BY_TITLE, new OrderMapper(), new String("%" + title + "%"), downLimit, upLimit);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(Status status, int page) throws NotFoundException {
        int downLimit = (page - 1) * 15;
        int upLimit = (downLimit + 15);
        try {
            return jdbcTemplate.query(GET_ORDERS_BY_STATUS, new OrderMapper(), status.name(), downLimit, upLimit);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(BigInteger userId) throws NotFoundException {
        try {
            return jdbcTemplate.query(GET_ORDERS_BY_USER, new OrderMapper(), userId, userId);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public boolean createOrder(Order order) {
        try {
            java.sql.Date startDate = new java.sql.Date(order.getStartDate().getTime());
            java.sql.Date endDate = new java.sql.Date(order.getEndDate().getTime());
            BigInteger id = BigInteger.ZERO;
            boolean result =  jdbcTemplate.update(SAVE_NEW_ORDER, order.getTitle(), order.getDescription(), order.getStatus().name(), order.getConsumer().getId(), startDate, endDate, order.getPrice(), order.getAddress()) != 0;
            jdbcTemplate.update("commit");
            return result;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        try {
            java.sql.Date startDate = new java.sql.Date(order.getStartDate().getTime());
            java.sql.Date endDate = new java.sql.Date(order.getEndDate().getTime());
            return jdbcTemplate.update(UPDATE_ORDER, order.getTitle(), order.getDescription(), order.getStatus().name(), order.getConsumer().getId(), order.getVendor().getId(), startDate, endDate, order.getPrice(), order.getAddress(), order.getId()) != 0;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteOrder(BigInteger id) {
        try {
            return jdbcTemplate.update(DELETE_ORDER, id) == 1;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Order getOrderId(Order order) throws NotFoundException {
        try {
            java.sql.Date startDate = new java.sql.Date(order.getStartDate().getTime());
            java.sql.Date endDate = new java.sql.Date(order.getEndDate().getTime());
            return jdbcTemplate.queryForObject(GET_ID_OF_ORDER, new OrderMapper(), order.getTitle(), order.getDescription(), order.getStatus().name(), order.getConsumer().getId(), startDate, endDate, order.getPrice(), order.getAddress());
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public BigInteger getNumberOfOrders() throws NotFoundException {
        return jdbcTemplate.queryForObject(GET_NUMBER_OF_ORDERS, new RowNumMapper());
    }
}
