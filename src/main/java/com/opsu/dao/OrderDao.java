package com.opsu.dao;

import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.enumeration.Status;
import javassist.NotFoundException;
import org.hibernate.type.BigIntegerType;

import java.math.BigInteger;
import java.util.Collection;

public interface OrderDao {

    Order getOrder(BigInteger id) throws NotFoundException;

    Collection<Order> getOrders() throws NotFoundException;

    Collection<Order> getOrders(Service service) throws NotFoundException;

    Collection<Order> getOrders(float price) throws NotFoundException;

    Collection<Order> getOrders(String title) throws NotFoundException;

    Collection<Order> getOrders(Status status) throws NotFoundException;

    Collection<Order> getOrders(BigInteger userId) throws NotFoundException;

    boolean createOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(BigInteger id);

    Order getOrderId(Order order) throws NotFoundException;

    String GET_ORDER_BY_ID = "SELECT\n" +
            "orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "FROM orders\n" +
            "WHERE orderId = ?";
    String GET_ORDERS = "SELECT\n" +
            "orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "FROM orders";
    String GET_ORDERS_BY_PRICE = "SELECT\n" +
            "orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "FROM orders\n" +
            "WHERE price = ?";
    String GET_ORDERS_BY_TITLE = "SELECT\n" +
            "orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "FROM orders\n" +
            "WHERE title like ?";
    String GET_ORDERS_BY_STATUS = "SELECT\n" +
            "orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "FROM orders\n" +
            "WHERE status = ?";
    String GET_ORDERS_BY_USER = "SELECT\n" +
            "orderId, title, description, status, consumerId, vendorId, startDate, endDate, price, address\n" +
            "FROM orders\n" +
            "WHERE consumerId = ?" +
            "   OR vendorId = ?";
    String GET_ORDERS_BY_SERVICE = " ?";
    String SAVE_NEW_ORDER = "MERGE INTO ORDERS old\n" +
            "                USING (SELECT  seq_next()  orderId,\n" +
            "                              ?            title,\n" +
            "                              ?            description,\n" +
            "                              ?            status,\n" +
            "                              ?            consumerId,\n" +
            "                              ?            vendorId,\n" +
            "                              ?            startDate,\n" +
            "                              ?            endDate,\n" +
            "                              ?            price,\n" +
            "                              ?            address\n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.orderId = new.orderId)\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.title = new.title,\n" +
            "                        old.description = new.description,\n" +
            "                        old.status = new.status,\n" +
            "                        old.consumerId     = new.consumerId,\n" +
            "                        old.vendorId     = new.vendorId,\n" +
            "                        old.startDate     = new.startDate,\n" +
            "                        old.endDate     = new.endDate,\n" +
            "                        old.price     = new.price,\n" +
            "                        old.address     = new.address\n" +
            "                    WHERE old.title <> new.title\n" +
            "                      OR  old.description <> new.description\n" +
            "                      OR  old.status    <> new.status\n" +
            "                      OR  old.consumerId   <> new.consumerId\n" +
            "                      OR  old.vendorId       <> new.vendorId\n" +
            "                      OR  old.startDate     <> new.startDate\n" +
            "                      OR  old.endDate     <> new.endDate\n" +
            "                      OR  old.price     <> new.price\n" +
            "                      OR  old.address     <> new.address\n" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.orderId, old.title, old.description, old.status, old.consumerId, old.vendorId, old.startDate, old.endDate, old.price, old.address)\n" +
            "                    VALUES (SEQ_CURR(), new.title, new.description, new.status, new.consumerId, new.vendorId, new.startDate, new.endDate, new.price, new.address)";
    String UPDATE_ORDER = "UPDATE orders SET\n" +
                "title = ? ,\n" +
                "description = ?,\n" +
                "status = ? ,\n" +
                "consumerId = ? ,\n" +
                "vendorId = ? ,\n" +
                "startDate = DATE ? ,\n" +
                "endDate = DATE ? ,\n" +
                "price = ? ,\n" +
                "address = ? \n" +
            "WHERE orderId = ?";
    String DELETE_ORDER = "DELETE FROM orders WHERE orderID = ?";
    String GET_ID_OF_ORDER = "SELECT" +
            "orderId, title, status, consumerId, vendorId, startDate, endDate, price, address" +
            "WHERE" +
            "title = ?" +
            "status = ?" +
            "consumerId = ?" +
            "vendorId = ?" +
            "startDate = ?" +
            "endDate = ?" +
            "price = ?" +
            "address = ?" +
            "ORDER BY orderID desc" +
            "LIMIT 1";
}
