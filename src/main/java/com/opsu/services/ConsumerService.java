package com.opsu.services;

import com.opsu.dao.ConsumerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final ConsumerDao consumerDao;

    @Autowired
    public ConsumerService(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }


    public void create(ConsumerDao consumerDao) {

    }
    public void update(ConsumerDao consumerDao) {

    }


}
