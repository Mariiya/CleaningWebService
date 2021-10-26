package com.opsu.dao.mapper;

import com.opsu.models.Consumer;
import com.opsu.models.User;
import com.opsu.models.enumeration.Role;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerMapper implements RowMapper<Consumer> {
    public Consumer mapRow(ResultSet resultSet, int i) throws SQLException {
    String firstName = resultSet.getString("firstName");
    String lastName = resultSet.getString("lastName");
    //select user
    BigInteger userId =BigInteger.valueOf(resultSet.getLong("consumer.userId"));
    String email = resultSet.getString("users.email");
    String password = resultSet.getString("users.password");
    String phoneNumber = resultSet.getString("users.phoneNumber");
    Role role = Role.ROLE_CLIENT;

    return new Consumer( userId, email, password, phoneNumber, role, firstName, lastName);
}
}
