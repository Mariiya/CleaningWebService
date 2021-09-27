package com.opsu.services;

import com.opsu.dao.UserDao;
import com.opsu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AuthorizationService {
    private final UserDao userDao;

    @Autowired
    public AuthorizationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean validateUser(User user) {
        return true;
    }

    public void register(User user) {

    }

    public boolean login(User user) {
        return false;
    }

    public void logout(User user) {

    }

    public User getUser(BigInteger userId) {
        return null;
    }

}
