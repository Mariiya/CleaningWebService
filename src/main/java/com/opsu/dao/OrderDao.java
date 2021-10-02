package com.opsu.dao;

import com.opsu.models.Order;
import javassist.NotFoundException;


import java.math.BigInteger;

public interface OrderDao {

    Order findOrderById(BigInteger id) throws NotFoundException;

    void createOrder(Order order);

    void updateOrder(Order order);

    boolean deleteOrder(Order order);

    String GET_ORDER_BY_ID = "SELECT\n" +
            "orders.orderId, orders.title, orders.status, orders.consumerId, orders.vendorId, orders.startDate, orders.endDate, orders.price, orders.address\n" +
            "consumer.consumerId, consumer.firstName, consumer.lastName, consumer.userId \n" +
            "vendor.vendorId, vendor.firstName, vendor.lastName, vendor.individual, vendor.userId \n" +
            "FROM orders\n" +
            "LEFT JOIN consumer ON orders.consumerId = consumer.consumerID\n" +
            "LEFT JOIN vendor ON orders.vendorId = vendor.vendorId\n" +
            "WHERE orderId = ?";
    String SAVE_NEW_ORDER = "MERGE INTO USERS old\n" +
            "                USING (SELECT  seq_next()  ORDERID,\n" +
            "                              ?            title,\n" +
            "                              ?            status,\n" +
            "                              ?            consumer,\n" +
            "                              ?            vendor\n" +
            "                              ?            startDate\n" +
            "                              ?            endDate\n" +
            "                              ?            price\n" +
            "                              ?            address\n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.ORDERID = new.ORDERID)\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.title = new.title,\n" +
            "                        old.status = new.status,\n" +
            "                        old.consumer     = new.consumer\n" +
            "                        old.vendor     = new.vendor\n" +
            "                        old.startDate     = new.startDate\n" +
            "                        old.endDate     = new.endDate\n" +
            "                        old.price     = new.price\n" +
            "                        old.address     = new.address\n" +
            "                    WHERE old.title <> new.title\n" +
            "                      OR  old.status    <> new.status\n" +
            "                      OR  old.consumer   <> new.consumer\n" +
            "                      OR  old.vendor       <> new.vendor\n" +
            "                      OR  old.startDate     <> new.startDate\n" +
            "                      OR  old.endDate     <> new.endDate\n" +
            "                      OR  old.price     <> new.price\n" +
            "                      OR  old.address     <> new.address\n" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.ORDERID, old.title, old.status, old.consumer, old.vendor, old.startDate, old.endDate, old.price, old.address)\n" +
            "                    VALUES (SEQ_CURR(), new.phoneNumber, new.EMAIL, new.PASSWORD, new.ROLE, new.ROLE, new.ROLE, new.ROLE, new.ROLE)";
    String UPDATE_ORDER = "UPDATE orders SET\n" +
                "title = ? \n" +
                "status = ? \n" +
                "consumer = ? \n" +
                "vendor = ? \n" +
                "startDate = ? \n" +
                "endDate = ? \n" +
                "price = ? \n" +
                "address = ? \n" +
            "WHERE orderId = ?";
    String DELETE_ORDER = "DELETE FROM orders WHERE orderID = ?";
}
