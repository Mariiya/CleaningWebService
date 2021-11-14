package com.opsu.services;

import com.opsu.dao.*;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.*;


import com.opsu.secutity.services.UserDetailsImpl;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigInteger;

@Service
public class ConsumerService {
    private static final Logger logger = Logger.getLogger(ConsumerService.class.getName());
    private final ConsumerDao consumerDao;
private final AuthorizationService authorizationService;
    @Autowired
    public  ConsumerService(ConsumerDao consumerDao, AuthorizationService authorizationService) {
        this.consumerDao = consumerDao;
        this.authorizationService = authorizationService;
    }

    public Consumer getConsumerById(BigInteger id) throws NotFoundException {
        if ((id==null)||(id.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        return consumerDao.getConsumerById(id);
    }

    public boolean create(Consumer consumerRequest) throws IOException, MessagingException, EmptyDataBaseException {
        Consumer consumer = new Consumer(consumerRequest.getId(),
                consumerRequest.getPhoneNumber(),
                consumerRequest.getEmail(),
                consumerRequest.getPassword(),
                consumerRequest.getRole(),
                consumerRequest.getFirstName(),
                consumerRequest.getLastName());
       return consumerDao.save(consumer);
    }

    public boolean update(UserDetailsImpl updater, Consumer consumer) throws NotFoundException, EmptyDataBaseException {
        if (!updater.getId().equals(consumer.getId())) {
            throw new PermissionDeniedDataAccessException("Can not change this user password", new IllegalAccessError());
        }
        Consumer consumerfromDB = consumerDao.getConsumerById(updater.getId());
        consumerfromDB.setLastName(consumer.getLastName());
        consumerfromDB.setFirstName(consumer.getFirstName());
        authorizationService.updateUser(updater,consumerfromDB);
        consumerDao.save(consumerfromDB);
        return true;
    }

    public Consumer findConsumerByLastName(String lastname) throws Exception {
        return consumerDao.findConsumerByLastName(lastname);
    }
}
