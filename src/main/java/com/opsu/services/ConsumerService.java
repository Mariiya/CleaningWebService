package com.opsu.services;

import com.opsu.dao.*;
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
    private NotificationService notificationService;
private final AuthorizationService authorizationService;
    @Autowired
    public  ConsumerService(ConsumerDao consumerDao, AuthorizationService authorizationService,NotificationService notificationService) {
        this.consumerDao = consumerDao;
        this.authorizationService = authorizationService;
        this.notificationService= notificationService;
    }

    public Consumer getConsumerById(BigInteger id) throws NotFoundException {
        if ((id==null)||(id.equals(BigInteger.ZERO))){
            throw new NumberFormatException("Wrong id input");
        }
        return consumerDao.getConsumerById(id);
    }

    public void create(Consumer consumerRequest) throws IOException, MessagingException {
        Consumer consumer = new Consumer(BigInteger.ONE,
                consumerRequest.getPhoneNumber(),
                consumerRequest.getEmail(),
                consumerRequest.getPassword(),
                consumerRequest.getRole(),
                consumerRequest.getFirstName(),
                consumerRequest.getLastName());
        consumerDao.save(consumer);
        notificationService.sendRegistrationNotification(consumerRequest);
    }

    public boolean update(UserDetailsImpl updater, Consumer consumer) throws NotFoundException {
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
