package com.opsu.services;

import com.opsu.dao.*;
import com.opsu.models.*;


import com.opsu.secutity.services.UserDetailsImpl;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.Callable;

@Service
public class ConsumerService {
    private static final Logger logger = Logger.getLogger(ConsumerService.class.getName());
    private final ConsumerDao consumerDao;

    @Autowired
    public  ConsumerService(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }

    public Consumer getConsumerById(BigInteger id)  {
        if ((id==null)||(id.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        return null;
    }

    public void create(Consumer consumer) {

    }
    //public boolean update(UserDetailsImpl updater, Consumer consumer) throws NotFoundException {
      //  if (!updater.getId().equals(consumer.getId())) {
        //    throw new PermissionDeniedDataAccessException("Can not change this user password", new IllegalAccessError());
        //}
        //Consumer consumer = consumerDao.getConsumerById(updater.getId());
        //consumer.setLastName(consumer.getLastName());
        //consumer.setEmail(consumer.getEmail());
        //consumerDao.save(consumer);
        //return true;
    //}


    public Consumer findConsumerByLastName(String lastname) throws Exception {
        return consumerDao.findConsumerByLastName(lastname);
    }
}
