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

@Repository
public class UserDaoImpl implements UserDao {
    //логер для записи ошибок
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    //Утилита от Спринга для работы с БД
    private final JdbcTemplate jdbcTemplate;

    //@Autowired значит что спринг сам создает JdbcTemplate jdbcTemplate и передает его сюда.
    // Т.е где-то в системе лежит экземпляр класса JdbcTemplate - так называемый бин (Bean)
    //Дальше все классы над которыми мы ставим @Service, @Component, @Controller, @Repository
    // спринг будет создавать 1 их экзампляр - Bean и использовать в системе.
    //все что нам нужно это указать над полем @Autowired и спринг сам все найдет что ему нужно.
    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByEmail(String email) throws NotFoundException {
        try {
            List<User> list =  jdbcTemplate.query(GET_USER_BY_EMAIL, new UserMapper(), email);
            if(!list.isEmpty()){
                return list.get(0);
            }
            System.out.println(list);
            return null;
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("User not found");
        }
    }

    public boolean save(User user) throws EmptyDataBaseException {
        try {
            System.out.println( user.getPhoneNumber() +" "+ user.getEmail() +" "+ user.getPassword() +" " + user.getRole().name());
            jdbcTemplate.update(CREATE_USER, user.getEmail(), user.getPassword(),user.getPhoneNumber(), user.getRole().name());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            throw new EmptyDataBaseException(e.getMessage(),e);
        }
        return true;
    }

    public boolean update(User user) throws EmptyDataBaseException {
        try {
            jdbcTemplate.update(UPDATE_USER,  user.getEmail(),user.getPhoneNumber(), user.getPassword(),user.getId());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            throw new EmptyDataBaseException("Error during User saving");
        }
        return true;
    }

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

    @Override
    public User getUserById(BigInteger id) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(GET_USER_BY_ID, new UserMapper(), id);
        } catch (DataAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new NotFoundException("User not found");
        }
    }

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
