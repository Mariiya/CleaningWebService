package com.opsu.dao;

import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.enumeration.Status;
import javassist.NotFoundException;

import java.math.BigInteger;
import java.util.Collection;
/**
 * This is abstract interface  for our data base mainly table Order
 * Here using SQL language we describe what operations/commands  can be applied for table Order
 * @author group 183
 * @version 2.1
 */

public interface OrderDao {
    /** search order by id
     * @param id order */
    Order getOrder(BigInteger id) throws NotFoundException;
    /** show all orders
     * @param page order */
    Collection<Order> getOrders(int page) throws NotFoundException;
    /** search and group order by price
     * @param maxPrice order
     * @param minPrice  order
     * @param page  order */
    Collection<Order> getOrders(Float minPrice, Float maxPrice, int page) throws NotFoundException;
    /** search and group order by title
     * @param title order
     * @param page  order */
    Collection<Order> getOrders(String title, int page) throws NotFoundException;
    /** search and group order by status
     * @param status order
     * @param page  order */
    Collection<Order> getOrders(Status status, int page) throws NotFoundException;
    /** search and group order by userid
     * @param userId order
     */
    Collection<Order> getOrders(BigInteger userId) throws NotFoundException;
    /** search and group order by serviceid
     * @param serviceId order
     * @param page  order */
    Collection<Order> getOrders(BigInteger serviceId, int page) throws NotFoundException;
    /** search and group order by several parametrs
     * @param maxPrice order
     * @param minPrice  order
     * @param service order
     * @param status  order
     * @param title order
     * @param page  order */
    Collection<Order> getOrders(Float minPrice, Float maxPrice, String title, Status status, Service service, int page) throws NotFoundException;
    /** create new order
     * @param order  order */
    boolean createOrder(Order order);
    /**updating already existing order
     * @param order  order */
    boolean updateOrder(Order order);
    /**delete order
     * @param id  order */
    boolean deleteOrder(BigInteger id);
    /**search for oder's id
     * @param order  order */
    Order getOrderId(Order order) throws NotFoundException;
    /**count amount of all orders */
    BigInteger getNumberOfOrders() throws NotFoundException;
    /**count amount of orders grouped by price
     * @param price order */
    BigInteger getNumberOfOrders(Float price) throws NotFoundException;
    /**count amount of orders grouped by status
     * @param status order */
    BigInteger getNumberOfOrders(Status status) throws NotFoundException;
    /**count amount of orders grouped by title
     * @param title order */
    BigInteger getNumberOfOrders(String title) throws NotFoundException;
    /**count amount of orders grouped by serviceid
     * @param serviceId order */
    BigInteger getNumberOfOrders(BigInteger serviceId) throws NotFoundException;
    /**count amount of orders grouped by several parameters
     * @param maxPrice order
     * @param minPrice order
     * @param title order
     * @param status order
     * @param service order*/
    BigInteger getNumberOfOrders(Float minPrice, Float maxPrice, String title, Status status, Service service) throws NotFoundException;
    /**Request for finding order by id*/
    String GET_ORDER_BY_ID = "SELECT\n" +
            " orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address " +
            "FROM orders " +
            "WHERE orderId = ?";
    /**Request for getting all orders*/
    String GET_ORDERS = "SELECT * FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY orderid) r FROM ORDERS o) t WHERE t.r > ? AND t.r <= ?";
    /**Request for finding order by price*/
    String GET_ORDERS_BY_PRICE = "SELECT * FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY orderid) r FROM ORDERS o) t WHERE price >= ? AND price <= ? AND (t.r > ? AND t.r <= ?)";
    /**Request for finding order by title */
    String GET_ORDERS_BY_TITLE = "SELECT * FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY orderid) r FROM ORDERS o) t WHERE LOWER(title) like LOWER(?) AND (t.r > ? AND t.r <= ?)\n";
    /**Request for finding order by status*/
    String GET_ORDERS_BY_STATUS = "SELECT * FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY orderid) r FROM ORDERS o) t WHERE status = ? AND (t.r > ? AND t.r <= ?)";
    /**Request for finding order by service*/
    String GET_ORDERS_BY_SERVICE = "SELECT orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "            FROM (SELECT o.*, ROW_NUMBER() OVER (ORDER BY servicecollectionid) r FROM SERVICECOLLECTION sc LEFT JOIN Orders o on sc.ORDERID = o.ORDERID WHERE sc.SERVICEID = ?) t WHERE t.r > ? AND t.r < ?";
    /**Request for finding order by user*/
    String GET_ORDERS_BY_USER = "SELECT " +
            "orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address " +
            "FROM orders " +
            "WHERE consumerId = ? " +
            "OR vendorId = ?";
    /**Request for creating new order*/
    String SAVE_NEW_ORDER = "INSERT INTO ORDERS (orderId, title, description, status, consumerId, startDate, endDate, price, address)\n" +
            "                                VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
    /**Request for changing information about already existing order */
    String UPDATE_ORDER = "UPDATE orders SET " +
                "title = ? ,\n" +
                "description = ?,\n" +
                "status = ? ,\n" +
                "consumerId = ? ,\n" +
                "vendorId = ? ,\n" +
                "startDate =  ? ,\n" +
                "endDate =  ? ,\n" +
                "price = ? ,\n" +
                "address = ? \n" +
            "WHERE orderId = ?";
    /**Request for deleting information about already existing order */
    String DELETE_ORDER = "DELETE FROM orders WHERE orderID = ?";
    /**Request for getting id of the oder */
    String GET_ID_OF_ORDER = "SELECT\n" +
            "            orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "            FROM (select * , ROW_NUMBER() OVER (ORDER BY orderid) r from ORDERS\n" +
            "            WHERE  LOWER(title) like LOWER(?)  AND LOWER(description) like LOWER(?)  AND status = ? AND consumerId = ? AND\n" +
            "           startDate = ? AND endDate = ? AND price = ? AND address = ?)  t\n" +
            "            where t.r <=1\n" +
            "            ORDER BY orderID desc";
    /**Request for counting amount of orders */
    String GET_NUMBER_OF_ORDERS = "SELECT COUNT(orderID) as \"number\" FROM ORDERS";
    /**Request for counting amount of orders grouped by specific price */
    String GET_NUMBER_OF_ORDERS_BY_PRICE = "SELECT COUNT(orderID) as \"number\" FROM ORDERS WHERE price >= ? AND price <= ?";
    /**Request for counting amount of orders grouped by specific title */
    String GET_NUMBER_OF_ORDERS_BY_TITLE = "SELECT COUNT(orderID) as \"number\" FROM ORDERS WHERE LOWER(title) like LOWER(?) ";
    /**Request for counting amount of orders grouped by specific status */
    String GET_NUMBER_OF_ORDERS_BY_STATUS = "SELECT COUNT(orderID) as \"number\" FROM ORDERS WHERE status = ?";
    /**Request for counting amount of orders grouped by specific service */
    String GET_NUMBER_OF_ORDERS_BY_SERVICE = "SELECT Count(o.ORDERID) as \"number\" FROM SERVICECOLLECTION sc LEFT JOIN Orders o on sc.ORDERID = o.ORDERID WHERE sc.SERVICEID = ?";
}
