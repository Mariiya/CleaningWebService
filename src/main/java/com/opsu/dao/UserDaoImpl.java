package com.opsu.dao;

import com.opsu.dao.mapper.UserMapper;
import com.opsu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUsernameOrEmail(String username) {
        return jdbcTemplate.queryForObject(GET_USER_BY_NAME_OR_EMAIL, new UserMapper(), username, username);
    }

    public void save(User user) {
        //save
    }

    public Boolean existsByUsername(String username) {
        Integer res = jdbcTemplate.queryForObject(IS_USER_BY_NAME, Integer.class);
        if (res != null) {
            return res != 0;
        }
        return true;
    }


    public Boolean existsByEmail(String email) {
        Integer res = jdbcTemplate.queryForObject(IS_USER_BY_EMAIL, Integer.class);
        if (res != null) {
            return res != 0;
        }
        return true;
    }


}
