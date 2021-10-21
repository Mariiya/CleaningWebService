package com.opsu.dao;

import com.opsu.models.Order;
import com.opsu.models.Service;
import javassist.NotFoundException;
import java.math.BigInteger;
import java.util.Collection;

public interface OrderDao {

    Order getOrder(BigInteger id) throws NotFoundException;

    Collection<Order> getOrders() throws NotFoundException;

    Collection<Order> getOrders(Service service) throws NotFoundException;

    Collection<Order> getOrders(float price) throws NotFoundException;

    Collection<Order> getOrders(String title) throws NotFoundException;

    boolean createOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(Order order);

    String GET_ORDER_BY_ID = "SELECT\n" +
            "orderId, title, status, orders.consumerId, orders.vendorId, startDate, endDate, price, address,\n" +
            "consumer.consumerId, consumer.firstName, consumer.lastName, consumer.userId, \n" +
            "vendor.vendorId, vendor.firstName, vendor.lastName, vendor.individual, vendor.userId \n" +
            "FROM orders\n" +
            "LEFT JOIN consumer ON orders.consumerId = consumer.consumerID\n" +
            "LEFT JOIN vendor ON orders.vendorId = vendor.vendorId\n" +
            "WHERE orderId = ?";
    String GET_ORDERS = "SELECT\n" +
            "orderId, title, status, orders.consumerId, orders.vendorId, startDate, endDate, price, address,\n" +
            "consumer.consumerId, consumer.firstName, consumer.lastName, consumer.userId, \n" +
            "vendor.vendorId, vendor.firstName, vendor.lastName, vendor.individual, vendor.userId \n" +
            "FROM orders\n" +
            "LEFT JOIN consumer ON orders.consumerId = consumer.consumerID\n" +
            "LEFT JOIN vendor ON orders.vendorId = vendor.vendorId";
    String GET_ORDERS_BY_PRICE = "SELECT\n" +
            "orderId, title, status, orders.consumerId, orders.vendorId, startDate, endDate, price, address,\n" +
            "consumer.consumerId, consumer.firstName, consumer.lastName, consumer.userId, \n" +
            "vendor.vendorId, vendor.firstName, vendor.lastName, vendor.individual, vendor.userId \n" +
            "FROM orders\n" +
            "LEFT JOIN consumer ON orders.consumerId = consumer.consumerID\n" +
            "LEFT JOIN vendor ON orders.vendorId = vendor.vendorId\n" +
            "WHERE price = ?";
    String GET_ORDERS_BY_TITLE = "SELECT\n" +
            "orderId, title, status, orders.consumerId, orders.vendorId, startDate, endDate, price, address,\n" +
            "consumer.consumerId, consumer.firstName, consumer.lastName, consumer.userId, \n" +
            "vendor.vendorId, vendor.firstName, vendor.lastName, vendor.individual, vendor.userId \n" +
            "FROM orders\n" +
            "LEFT JOIN consumer ON orders.consumerId = consumer.consumerID\n" +
            "LEFT JOIN vendor ON orders.vendorId = vendor.vendorId\n" +
            "WHERE title = ?";
    String GET_ORDERS_BY_SERVICE = " ?";
    String SAVE_NEW_ORDER = "MERGE INTO ORDERS old\n" +
            "                USING (SELECT  seq_next()  ORDERID,\n" +
            "                              ?            title,\n" +
            "                              ?            status,\n" +
            "                              ?            consumerId,\n" +
            "                              ?            vendorId,\n" +
            "                              ?            startDate,\n" +
            "                              ?            endDate,\n" +
            "                              ?            price,\n" +
            "                              ?            address\n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.ORDERID = new.ORDERID)\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.title = new.title,\n" +
            "                        old.status = new.status,\n" +
            "                        old.consumerId     = new.consumerId,\n" +
            "                        old.vendorId     = new.vendorId,\n" +
            "                        old.startDate     = new.startDate,\n" +
            "                        old.endDate     = new.endDate,\n" +
            "                        old.price     = new.price,\n" +
            "                        old.address     = new.address\n" +
            "                    WHERE old.title <> new.title\n" +
            "                      OR  old.status    <> new.status\n" +
            "                      OR  old.consumerId   <> new.consumerId\n" +
            "                      OR  old.vendorId       <> new.vendorId\n" +
            "                      OR  old.startDate     <> new.startDate\n" +
            "                      OR  old.endDate     <> new.endDate\n" +
            "                      OR  old.price     <> new.price\n" +
            "                      OR  old.address     <> new.address\n" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.ORDERID, old.title, old.status, old.consumer, old.vendor, old.startDate, old.endDate, old.price, old.address)\n" +
            "                    VALUES (SEQ_CURR(), new.title, new.status, new.consumerId, new.vendorId, new.startDate, new.endDate, new.price, new.address)";
    String UPDATE_ORDER = "UPDATE orders SET\n" +
                "title = ? ,\n" +
                "status = ? ,\n" +
                "consumerId = ? ,\n" +
                "vendorId = ? ,\n" +
                "startDate = ? ,\n" +
                "endDate = ? ,\n" +
                "price = ? ,\n" +
                "address = ? \n" +
            "WHERE orderId = ?";
    String DELETE_ORDER = "DELETE FROM orders WHERE orderID = ?";
}
