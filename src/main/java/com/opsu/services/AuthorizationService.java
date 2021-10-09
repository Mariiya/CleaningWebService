package com.opsu.services;

import com.opsu.dao.UserDao;
import com.opsu.models.User;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AuthorizationService {
    private static Logger logger = Logger.getLogger(AuthorizationService.class.getName());
    private final UserDao userDao;

    @Autowired
    public AuthorizationService(UserDao userDao) {
        this.userDao = userDao;
    }



}
