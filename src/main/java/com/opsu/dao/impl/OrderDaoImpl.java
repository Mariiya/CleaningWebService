package com.opsu.dao.impl;

import com.opsu.dao.OrderDao;
import com.opsu.dao.mapper.OrderMapper;
import com.opsu.dao.mapper.RowNumMapper;
import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.enumeration.Status;
import javassist.NotFoundException;
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
        int downLimit = (page - 1) * 15;
        int upLimit = (downLimit + 15);
        Collection<Order> orderCollection = null;
        BigInteger serviceId;
        try{
            String query = "SELECT * FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY ORDERID)  r FROM  Orders o  WHERE ";
            if(minPrice < 0){
                minPrice = 0f;
            }
            if(maxPrice <= 0){
                maxPrice = Float.MAX_VALUE;
            }
            query = query.concat("(o.price >= ? AND o.price <= ?) ");
            if (title != null && !title.trim().equals("")){
                title = "%" + title + "%";
            }
            else{
                title = "%%";
            }
            query = query.concat("AND (o.title like ?) ");
            if(status == null || status == Status.EMPTY){
                status = Status.EMPTY;
                query = query.concat("AND (o.status != ?) ");
            } else {
                query = query.concat("AND (o.status = ?) ");
            }
            query = query.concat(") t WHERE t.r > ? AND t.r < ?");
            System.out.println("query for orders " + query);
            orderCollection = jdbcTemplate.query(query, new OrderMapper(), minPrice, maxPrice, title, status.name(), downLimit, upLimit);

            return orderCollection;
        } catch (DataAccessException e) {
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

    @Override
    public BigInteger getNumberOfOrders(Float minPrice, Float maxPrice, String title, Status status, Service service) throws NotFoundException {
        BigInteger serviceId;
        String query = "SELECT COUNT(ORDERID) AS \"number\" FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY orderid)  r FROM  Orders o WHERE " +
                " orderid in (select orderid from SERVICECOLLECTION where ";

        if(service == null){
            serviceId = BigInteger.ZERO;
            query = query.concat(" serviceId != ?) ");
        } else {
            serviceId = service.getId();
            query = query.concat(" serviceId = ?) ");
        }

        if(minPrice < 0){
            minPrice = 0f;
        }
        if(maxPrice <= 0){
            maxPrice = Float.MAX_VALUE;
        }
        query = query.concat("AND (o.price >= ? AND o.price <= ?) ");
        if (title != null && !title.trim().equals("")){
            title = "%" + title + "%";
        }
        else{
            title = "%%";
        }
        query = query.concat("AND (o.title like ?) ");
        if(status == null || status == Status.EMPTY){
            status = Status.EMPTY;
            query = query.concat("AND (o.status != ?) ");
        } else {
            query = query.concat("AND (o.status = ?) ");
        }

        query = query.concat(") t");
        System.out.println("query " + query);
        System.out.println("params " + minPrice+" - " + maxPrice+" - " + title+" - " + status.name());
        return jdbcTemplate.queryForObject(query, new RowNumMapper(), serviceId,minPrice, maxPrice, title, status.name());
    }
}
