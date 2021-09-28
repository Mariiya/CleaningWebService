package com.opsu.dao.mapper;

import com.opsu.models.User;
import com.opsu.models.enumeration.Role;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = BigInteger.valueOf(resultSet.getLong("userId"));
        String phoneNumber = resultSet.getString("PHONENUMBER");
        String email = resultSet.getString("email");
        Role role = Role.valueOf(resultSet.getString("role"));
        String password = resultSet.getString("password");
        return new User(id, phoneNumber, email, password, role);
    }
}
