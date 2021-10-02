package com.opsu.dao;

import com.opsu.models.Consumer;
import javassist.NotFoundException;


import java.math.BigInteger;

public interface ConsumerDao {

    Consumer getConsumerById(BigInteger id) throws NotFoundException;

    Consumer findConsumerByLastName(String lastName) throws NotFoundException;

    void create (Consumer consumer);

    String GET_CONSUMER_BY_ID = "SELECT consumerId, firstName, lastName, consumer.userId" +
            "users.userId, users.email, users.password, users.phoneNumber, users.role" +
            "FROM CONSUMER" +
            "LEFT JOIN users ON consumer.userId = users.userId" +
            "WHERE consumerId = ?";

    String GET_CONSUMER_BY_LAST_NAME = "SELECT consumerId, firstName, lastName, consumer.userId" +
            "users.userId, users.email, users.password, users.phoneNumber, users.role" +
            "FROM CONSUMER" +
            "LEFT JOIN users ON consumer.userId = users.userId" +
            "WHERE lastName = ? ";

    String CREATE_CONSUMER = "MERGE INTO CONSUMER old\n" +
            "                USING (SELECT  seq_next()  consumerId,\n" +
            "                              ?            lastName,\n" +
            "                              ?            firstName,\n" +
            "                       FROM DUAL) new\n" +
            "                ON (old.consumerId = new.consumerId\n" +
            "                WHEN MATCHED THEN\n" +
            "                    UPDATE\n" +
            "                    SET old.lastName = new.lastName,\n" +
            "                        old.firstName= new.firstName,\n" +
            "                    WHERE old.lastName <> new.lastName\n" +
            "                       OR old.firstName <> new.firstName \n" +
            "                      OR  old.consumerId    <> new.consumerId\n" +
            "                WHEN NOT MATCHED THEN\n" +
            "                    INSERT (old.consumerId, old.lastName, old.firstName)\n" +
            "                    VALUES (SEQ_CURR(), new.lastName, new.firstName)";
}

