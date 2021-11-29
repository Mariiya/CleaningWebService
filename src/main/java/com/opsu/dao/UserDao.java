package com.opsu.dao;

import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.User;
import javassist.NotFoundException;

import java.math.BigInteger;

public interface UserDao {

    User findByEmail(String Email) throws NotFoundException;

    boolean save(User user) throws EmptyDataBaseException;

    boolean update(User user) throws EmptyDataBaseException;

    Boolean existsByPhoneNumber(String number);

    Boolean existsByEmail(String email);

    User getUserById(BigInteger id) throws NotFoundException;

    String GET_USER_BY_EMAIL = "SELECT userId, PHONENUMBER, email, password, role\n" +
            "             FROM USERS WHERE email = ?";

    String GET_USER_BY_ID = "SELECT userId, PHONENUMBER, email, password, role\n" +
            "             FROM USERS WHERE userId = ?";

    String IS_USER_BY_PHONE_NUMBER = "select count(userId)\n" +
            "    from  USERS\n" +
            "    where phoneNumber = ?";

    String IS_USER_BY_EMAIL = "select count(userId)\n" +
            "    from  USERS\n" +
            "    where email = ?";

    String CREATE_USER = "INSERT INTO USERS (userId,email,password,phoneNumber,role)" +
            " VALUES (DEFAULT, ?, ?, ?, ?)";

    String UPDATE_USER = "UPDATE USERS SET email = ?, phoneNumber = ?, password = ?  WHERE USERID = ?";
}
