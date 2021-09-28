package com.opsu.dao.mapper;

import com.opsu.models.Consumer;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerMapper implements RowMapper<Consumer> {
    public Consumer mapRow(ResultSet resultSet, int i) throws SQLException {
    BigInteger consumerId =BigInteger.valueOf(resultSet.getLong("consumerId"));
    String firstName = resultSet.getString("firstName");
    String lastName = resultSet.getString("lastName");
    return new Consumer( consumerId, firstName, lastName);
}
}
