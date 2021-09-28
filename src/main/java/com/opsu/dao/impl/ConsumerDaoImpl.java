package com.opsu.dao.impl;

import com.opsu.dao.ConsumerDao;
import com.opsu.models.Consumer;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public class ConsumerDaoImpl implements ConsumerDao {
    @Override
    public Consumer findByConsumerById(BigInteger id) {
        return null;
    }

    @Override
    public void save(Consumer Consumer) {

    }
}
