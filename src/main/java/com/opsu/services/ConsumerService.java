package com.opsu.services;

import com.opsu.dao.ConsumerDao;
import com.opsu.models.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final ConsumerDao consumerDao;

    @Autowired
    public ConsumerService(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }

    public void getConsumerById(Consumer consumer) {

    }

    public void create(Consumer consumer) {

    }
    public void update(Consumer consumer) {

    }


}
