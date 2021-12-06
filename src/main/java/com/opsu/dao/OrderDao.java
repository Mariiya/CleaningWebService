package com.opsu.dao;

import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.enumeration.Status;
import javassist.NotFoundException;

import java.math.BigInteger;
import java.util.Collection;

public interface OrderDao {

    Order getOrder(BigInteger id) throws NotFoundException;

    Collection<Order> getOrders(int page) throws NotFoundException;

    Collection<Order> getOrders(Float minPrice, Float maxPrice, int page) throws NotFoundException;

    Collection<Order> getOrders(String title, int page) throws NotFoundException;

    Collection<Order> getOrders(Status status, int page) throws NotFoundException;

    Collection<Order> getOrders(BigInteger userId) throws NotFoundException;

    Collection<Order> getOrders(BigInteger serviceId, int page) throws NotFoundException;

    Collection<Order> getOrders(Float minPrice, Float maxPrice, String title, Status status, Service service, int page) throws NotFoundException;

    boolean createOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(BigInteger id);

    Order getOrderId(Order order) throws NotFoundException;

    BigInteger getNumberOfOrders() throws NotFoundException;

    BigInteger getNumberOfOrders(Float price) throws NotFoundException;

    BigInteger getNumberOfOrders(Status status) throws NotFoundException;

    BigInteger getNumberOfOrders(String title) throws NotFoundException;

    BigInteger getNumberOfOrders(BigInteger serviceId) throws NotFoundException;

    BigInteger getNumberOfOrders(Float minPrice, Float maxPrice, String title, Status status, Service service) throws NotFoundException;

    String GET_ORDER_BY_ID = "SELECT\n" +
            " orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address " +
            "FROM orders " +
            "WHERE orderId = ?";
    String GET_ORDERS = "SELECT * FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY orderid) r FROM ORDERS o) t WHERE t.r > ? AND t.r <= ?";
    String GET_ORDERS_BY_PRICE = "SELECT * FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY orderid) r FROM ORDERS o) t WHERE price >= ? AND price <= ? AND (t.r > ? AND t.r <= ?)";
    String GET_ORDERS_BY_TITLE = "SELECT * FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY orderid) r FROM ORDERS o) t WHERE LOWER(title) like LOWER('?') AND (t.r > ? AND t.r <= ?)\n";
    String GET_ORDERS_BY_STATUS = "SELECT * FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY orderid) r FROM ORDERS o) t WHERE status = ? AND (t.r > ? AND t.r <= ?)";
    String GET_ORDERS_BY_SERVICE = "SELECT orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "            FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY servicecollectionid) r FROM SERVICECOLLECTION sc LEFT JOIN Orders o on sc.ORDERID = o.ORDERID WHERE sc.SERVICEID = ?) t WHERE t.r > ? AND t.r < ?";
    String GET_ORDERS_BY_USER = "SELECT " +
            "orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address " +
            "FROM orders " +
            "WHERE consumerId = ? " +
            "OR vendorId = ?";
    String SAVE_NEW_ORDER = "INSERT INTO ORDERS (orderId, title, description, status, consumerId, startDate, endDate, price, address)\n" +
            "                                VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_ORDER = "UPDATE orders SET " +
                "title = ? ,\n" +
                "description = ?,\n" +
                "status = ? ,\n" +
                "startDate =  ? ,\n" +
                "endDate =  ? ,\n" +
                "price = ? ,\n" +
                "address = ? \n" +
            "WHERE orderId = ?";
    String DELETE_ORDER = "DELETE FROM orders WHERE orderID = ?";
    String GET_ID_OF_ORDER = "SELECT\n" +
            "            orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "            FROM (select * , ROW_NUMBER() OVER (ORDER BY orderid) r from ORDERS\n" +
            "            WHERE  title = ? AND description = ? AND status = ? AND consumerId = ? AND\n" +
            "           startDate = ? AND endDate = ? AND price = ? AND address = ?)  t\n" +
            "            where t.r <=1\n" +
            "            ORDER BY orderID desc";
    String GET_NUMBER_OF_ORDERS = "SELECT COUNT(orderID) as \"number\" FROM ORDERS";
    String GET_NUMBER_OF_ORDERS_BY_PRICE = "SELECT COUNT(orderID) as \"number\" FROM ORDERS WHERE price >= ? AND price <= ?";
    String GET_NUMBER_OF_ORDERS_BY_TITLE = "SELECT COUNT(orderID) as \"number\" FROM ORDERS  WHERE LOWER(title) like LOWER('?') ";
    String GET_NUMBER_OF_ORDERS_BY_STATUS = "SELECT COUNT(orderID) as \"number\" FROM ORDERS WHERE status = ?";
    String GET_NUMBER_OF_ORDERS_BY_SERVICE = "SELECT Count(o.ORDERID) as \"number\" FROM SERVICECOLLECTION sc LEFT JOIN Orders o on sc.ORDERID = o.ORDERID WHERE sc.SERVICEID = ?";
}
