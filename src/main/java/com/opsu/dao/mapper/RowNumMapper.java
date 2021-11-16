package com.opsu.dao.mapper;

import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.Vendor;
import com.opsu.models.builders.OrderBuilder;
import com.opsu.models.enumeration.Status;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowNumMapper implements RowMapper<BigInteger> {
    public BigInteger mapRow(ResultSet resultSet, int i) throws SQLException {
        return BigInteger.valueOf(resultSet.getInt("number"));
    }
}
