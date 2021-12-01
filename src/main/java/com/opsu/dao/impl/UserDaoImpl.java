package com.opsu.dao.impl;

import com.opsu.dao.UserDao;
import com.opsu.dao.mapper.UserMapper;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.User;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
/**
 * An implementation of an interface UserDao.
 * @author group 183
 * @version 2.1
 */
@Repository
public class UserDaoImpl implements UserDao {
    /**Logger for creating log records*/
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;
    /**required constructor*/
    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /** search user by email
     * @param email user */
    public User findByEmail(String email) throws NotFoundException {
        try {
            List<User> list =  jdbcTemplate.query(GET_USER_BY_EMAIL, new UserMapper(), email);
            if(!list.isEmpty()){
                return list.get(0);
            }
            return null;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("User not found");
        }
    }
    /**creating new user
     * @param user user*/
    public boolean save(User user) throws EmptyDataBaseException {
        try {
            jdbcTemplate.update(CREATE_USER, user.getEmail(), user.getPassword(),user.getPhoneNumber(), user.getRole().name());
             } catch (DataAccessException e) {
            throw new EmptyDataBaseException(e.getMessage(),e);
        }
        return true;
    }
    /**updating new user
     * @param user user*/
    public boolean update(User user) throws EmptyDataBaseException {
        try {
            jdbcTemplate.update(UPDATE_USER,  user.getEmail(),user.getPhoneNumber(), user.getPassword(),user.getId());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            throw new EmptyDataBaseException("Error during User saving");
        }
        return true;
    }
    /**existing of user with specific phone number
     * @param number user*/
    public Boolean existsByPhoneNumber(String number) {
        try {
            Integer res = jdbcTemplate.queryForObject(IS_USER_BY_PHONE_NUMBER, Integer.class, number);
            if (res != null) {
                return res != 0;
            }
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
    /** search user by id
     * @param id user */
    @Override
    public User getUserById(BigInteger id) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(GET_USER_BY_ID, new UserMapper(), id);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("User not found");
        }
    }
    /**existing of user with specific email
     * @param email user*/
    public Boolean existsByEmail(String email) {
        try {
            Integer res = jdbcTemplate.queryForObject(IS_USER_BY_EMAIL, Integer.class, email);
            if (res != null) {
                return res != 0;
            }
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
}
