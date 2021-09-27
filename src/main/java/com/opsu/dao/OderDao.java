package com.opsu.dao;

import com.opsu.models.Order;
import com.opsu.models.enumeration.Status;



import java.math.BigInteger;

public interface OderDao {

    Order findByOderById(BigInteger id);

    void save(Order order);

    String GET_ODER_BY_ID = "SELECT title, status, consumer, vendor,startDate,endDate,price,address FROM USERS WHERE id = ?";
    String SAVE_NEW_ODER = "INSERT INTO USERS (id, title, status, consumer, vendor,startDate,endDate,price,address VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
    String UPDATE_ODER = "";
    String DELETE_ODER = "";
}
