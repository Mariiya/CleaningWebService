package com.opsu.dao.impl;

import com.opsu.dao.OrderDao;
import com.opsu.dao.mapper.OrderMapper;
import com.opsu.dao.mapper.RowNumMapper;
import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.Vendor;
import com.opsu.models.enumeration.Status;
import javassist.NotFoundException;
import oracle.sql.SQLUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLData;
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
    public Collection<Order> getOrders(BigInteger serviceId, int page) throws NotFoundException {
        int downLimit = (page - 1) * 15;
        int upLimit = (downLimit + 15);
        try {
            return jdbcTemplate.query(GET_ORDERS_BY_SERVICE, new OrderMapper(), serviceId, downLimit, upLimit);
        } catch(DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public Collection<Order> getOrders(Float minPrice, Float maxPrice, String title, Status status, Service service, int page) throws NotFoundException {

        String query = "SELECT * FROM (SELECT o.*, ROWNUM r FROM ORDERS o) WHERE (r > ? AND r <= ?)";
        Collection<Order> orderCollection = null;
        try{
            int downLimit = (page - 1) * 15;
            int upLimit = (downLimit + 15);
            if(minPrice != null && maxPrice != null){
               if(minPrice < 0){
                   minPrice = 0f;
               }
               if(maxPrice <= 0){
                   maxPrice = Float.MAX_VALUE;
               }
               query.concat(" AND (price => ? AND price <= ?)");
            }
            if(title != null && !title.isEmpty()){
                query.concat(" AND (title like ?)");
            }
            if(status != null){
                query.concat(" AND (status = ?)");
            }

            if((minPrice != null && maxPrice != null) && (title != null && !title.isEmpty()) && (status != null)){
                orderCollection = jdbcTemplate.query(query, new OrderMapper(), downLimit, upLimit, minPrice, maxPrice, title, status.name());
            } else if((minPrice != null && maxPrice != null) && (title != null && !title.isEmpty())){
                orderCollection = jdbcTemplate.query(query, new OrderMapper(), downLimit, upLimit, minPrice, maxPrice, title);
            } else if((minPrice != null && maxPrice != null) && (status != null)){
                orderCollection = jdbcTemplate.query(query, new OrderMapper(), downLimit, upLimit, minPrice, maxPrice, status.name());
            } else if((title != null && !title.isEmpty()) && (status != null)){
                orderCollection = jdbcTemplate.query(query, new OrderMapper(), downLimit, upLimit, title, status.name());
            } else if(minPrice != null && maxPrice != null) {
                orderCollection = jdbcTemplate.query(query, new OrderMapper(), downLimit, upLimit, minPrice, maxPrice);
            } else if(title != null && !title.isEmpty()){
                orderCollection = jdbcTemplate.query(query, new OrderMapper(), downLimit, upLimit, title);
            } else if(status != null){
                orderCollection = jdbcTemplate.query(query, new OrderMapper(), downLimit, upLimit, status.name());
            } else {
                orderCollection = jdbcTemplate.query(query, new OrderMapper(), downLimit, upLimit);
            }
            return orderCollection;

        } catch (DataAccessException e){
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("Orders not found");
        }
    }

    @Override
    public boolean createOrder(Order order) {
        try {
            java.sql.Date startDate = new java.sql.Date(order.getStartDate().getTime());
            java.sql.Date endDate ;
            if(order.getEndDate()==null){
                endDate = startDate;
            } else {
                endDate = new java.sql.Date(order.getEndDate().getTime());
            }
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
            java.sql.Date endDate;
            java.sql.Date startDate = new java.sql.Date(order.getStartDate().getTime());
            if(order.getEndDate()!=null) {
                endDate = new java.sql.Date(order.getEndDate().getTime());
            }else{
                endDate = startDate;
            }
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
            java.sql.Date endDate;
            java.sql.Date startDate = new java.sql.Date(order.getStartDate().getTime());
            if(order.getEndDate()!=null){
                endDate = new java.sql.Date(order.getEndDate().getTime());
            }else{
                endDate = startDate;
            }

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

    @Override
    public BigInteger getNumberOfOrders(Float price) throws NotFoundException {
        return jdbcTemplate.queryForObject(GET_NUMBER_OF_ORDERS_BY_PRICE, new RowNumMapper(), price);
    }

    @Override
    public BigInteger getNumberOfOrders(Status status) throws NotFoundException {
        return jdbcTemplate.queryForObject(GET_NUMBER_OF_ORDERS_BY_STATUS, new RowNumMapper(), status.name());
    }

    @Override
    public BigInteger getNumberOfOrders(String title) throws NotFoundException {
        return jdbcTemplate.queryForObject(GET_NUMBER_OF_ORDERS_BY_TITLE, new RowNumMapper(), title);
    }

    @Override
    public BigInteger getNumberOfOrders(BigInteger serviceId) throws NotFoundException {
        return jdbcTemplate.queryForObject(GET_NUMBER_OF_ORDERS_BY_SERVICE, new RowNumMapper(), serviceId);
    }
}
