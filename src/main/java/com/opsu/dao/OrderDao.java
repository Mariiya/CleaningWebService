package com.opsu.dao;

import com.opsu.models.Order;
import com.opsu.models.User;
import javassist.NotFoundException;


import java.math.BigInteger;

public interface OrderDao {

    User findByOderById(BigInteger id) throws NotFoundException;

    void save(Order order);

    String GET_ODER_BY_ID = "SELECT title, status, consumer, vendor,startDate,endDate,price,address FROM USERS WHERE id = ?";
    String SAVE_NEW_ODE = "INSERT INTO ORDERS (id, title, status, consumer, vendor,startDate,endDate,price,address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SAVE_NEW_ODER = "MERGE INTO USERS old\n" +
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
    String UPDATE_ODER = "";
    String DELETE_ODER = "";
}
