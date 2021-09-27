package com.opsu.dao;

import com.opsu.models.Consumer;
import com.opsu.models.enumeration.Status;



import java.math.BigInteger;

public interface ConsumerDao {

    Consumer findByConsumerById(BigInteger id);

    void save(Consumer Consumer);

    String GET_Consumer_BY_ID = "SELECT firstName,lastName FROM USERS WHERE id = ?";
    String SAVE_NEW_CONSUMER = "INSERT INTO CONSUMER (id, firstName, lastName VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
    String UPDATE_CONSUMER= "";
}

