package com.opsu.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowNumMapper implements RowMapper<BigInteger> {
    public BigInteger mapRow(ResultSet resultSet, int i) throws SQLException {
        return BigInteger.valueOf(resultSet.getInt("number"));
    }
}
