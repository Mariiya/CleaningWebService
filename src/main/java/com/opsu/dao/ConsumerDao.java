package com.opsu.dao;

import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Consumer;
import javassist.NotFoundException;


import java.math.BigInteger;

public interface ConsumerDao {

    Consumer getConsumerById(BigInteger id) throws NotFoundException;

    Consumer findConsumerByLastName(String lastName) throws NotFoundException;
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

    String CREATE_CONSUMER = "MERGE INTO CONSUMER old\n" +
            "                   USING (SELECT ?            userid,\n" +
            "                                 ?            lastName,\n" +
            "                                 ?            firstName\n" +
            "                          FROM DUAL) new\n" +
            "                   ON (old.userid = new.userid)\n" +
            "                   WHEN MATCHED THEN\n" +
            "                       UPDATE\n" +
            "                       SET old.lastName = new.lastName,\n" +
            "                           old.firstName= new.firstName\n" +
            "                       WHERE old.lastName <> new.lastName\n" +
            "                          OR old.firstName <> new.firstName\n" +
            "                   WHEN NOT MATCHED THEN\n" +
            "                       INSERT (old.userid, old.lastName, old.firstName)\n" +
            "                       VALUES (new.userid, new.lastName, new.firstName)\n";
}

