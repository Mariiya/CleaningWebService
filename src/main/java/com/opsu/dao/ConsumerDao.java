package com.opsu.dao;

import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Consumer;
import javassist.NotFoundException;


import java.math.BigInteger;

public interface ConsumerDao {

    Consumer getConsumerById(BigInteger id) throws NotFoundException;

    Consumer findConsumerByLastName(String lastName) throws NotFoundException;
    boolean update (Consumer consumer) throws EmptyDataBaseException;
    boolean save (Consumer consumer) throws EmptyDataBaseException;
    String GET_CONSUMER_BY_ID = "SELECT  firstName, lastName, consumer.userId," +
            "  users.email, users.password, users.phoneNumber, users.role " +
            " FROM CONSUMER" +
            " LEFT JOIN users ON consumer.userId = users.userId" +
            " WHERE consumer.userId = ?";

    String GET_CONSUMER_BY_LAST_NAME = "SELECT firstName, lastName, consumer.userId," +
            " users.email, users.password, users.phoneNumber, users.role" +
            "FROM CONSUMER" +
            "LEFT JOIN users ON consumer.userId = users.userId" +
            "WHERE lastName = ? ";

    String CREATE_CONSUMER = "INSERT INTO CONSUMER  VALUES (?,?,?)\n";

    String UPDATE_CONSUMER = "UPDATE CONSUMER set firstName = ?, lastName = ? where userid = ?\n";
}

