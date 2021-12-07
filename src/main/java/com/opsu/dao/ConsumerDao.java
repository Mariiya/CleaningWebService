package com.opsu.dao;

import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Consumer;
import javassist.NotFoundException;
import java.math.BigInteger;

/**
 * This is abstract interface  for our data base mainly table Consumer
 * Here using SQL language we describe what operations/commands  can be applied for table Consumer
 * @author group 183
 * @version 2.1
 */
public interface ConsumerDao {

    /** search consumer by id
     * @param id consumer */
    Consumer getConsumerById(BigInteger id) throws NotFoundException;
    /**updating already existing consumer
     * @param consumer consumer */
    boolean update (Consumer consumer) throws EmptyDataBaseException;
    /**creating new consumer
     * @param consumer consumer*/
    boolean save (Consumer consumer) throws EmptyDataBaseException;

    /**Request for finding consumer by id*/
    String GET_CONSUMER_BY_ID = "SELECT  firstName, lastName, consumer.userId," +
            "  users.email, users.password, users.phoneNumber, users.role " +
            " FROM CONSUMER" +
            " LEFT JOIN users ON consumer.userId = users.userId" +
            " WHERE consumer.userId = ?";

    /**Request for creating new consumer */
    String CREATE_CONSUMER = "INSERT INTO CONSUMER  VALUES (?,?,?)\n";

    /**Request for changing information about already existing consumer */
    String UPDATE_CONSUMER = "UPDATE CONSUMER set firstName = ?, lastName = ? where userid = ?\n";
}

