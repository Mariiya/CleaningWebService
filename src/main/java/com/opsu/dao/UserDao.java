package com.opsu.dao;

import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.User;
import javassist.NotFoundException;

import java.math.BigInteger;
/**
 * This is abstract interface  for our data base mainly table User
 * Here using SQL language we describe what operations/commands  can be applied for table User
 * @author group 183
 * @version 2.1
 */
public interface UserDao {
    /** search user by email
     * @param Email user */
    User findByEmail(String Email) throws NotFoundException;
    /**creating new user
     * @param user user*/
    boolean save(User user) throws EmptyDataBaseException;
    /**creating new user
     * @param user user*/
    boolean update(User user) throws EmptyDataBaseException;
    /**existing of user with specific phone number
     * @param number user*/
    Boolean existsByPhoneNumber(String number);
    /**existing of user with specific email
     * @param email user*/
    Boolean existsByEmail(String email);
    /** search user by id
     * @param id user */
    User getUserById(BigInteger id) throws NotFoundException;

    /**Request for finding user with specific email */
    String GET_USER_BY_EMAIL = "SELECT userId, PHONENUMBER, email, password, role\n" +
            "             FROM USERS WHERE email = ?";

    /**Request for finding user with specific id */
    String GET_USER_BY_ID = "SELECT userId, PHONENUMBER, email, password, role\n" +
            "             FROM USERS WHERE userId = ?";

    /**Request for checking  existence of user with specific phone number */
    String IS_USER_BY_PHONE_NUMBER = "select count(userId)\n" +
            "    from  USERS\n" +
            "    where phoneNumber = ?";
    /**Request for checking  existence of user with specific email */
    String IS_USER_BY_EMAIL = "select count(userId)\n" +
            "    from  USERS\n" +
            "    where email = ?";

    /**Request for creating new user */
    String CREATE_USER = "INSERT INTO USERS (userId,email,password,phoneNumber,role)" +
            " VALUES (DEFAULT, ?, ?, ?, ?)";
    /**Request for creating new user */
    String UPDATE_USER = "UPDATE USERS SET email = ?, phoneNumber = ?, password = ?  WHERE USERID = ?";
}
