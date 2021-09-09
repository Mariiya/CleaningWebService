package com.opsu.dao;

import com.opsu.models.User;

public interface UserDao {

    User findByUsernameOrEmail(String username);

    void save(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    String GET_USER_BY_NAME_OR_EMAIL = "";
    String IS_USER_BY_NAME = "";
    String IS_USER_BY_EMAIL = "";
}
