package com.opsu.services;

import com.opsu.dao.*;

import com.opsu.models.*;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ConsumerService {
    private static final Logger logger = Logger.getLogger(ConsumerService.class.getName());
    private final ConsumerDao consumerDao;

    @Autowired
    public  ConsumerService(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }

    public Consumer getConsumerById(BigInteger id) throws Exception {
        if ((id==null)||(id.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        return null;
    }

    public void create(Consumer consumer) {

    }
    public void update(Consumer consumer) {

    }


}
