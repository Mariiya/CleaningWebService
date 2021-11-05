package com.opsu.dao.mapper;

import com.opsu.models.Service;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class  ServiceMapper implements RowMapper<Service> {

    @Override
    public Service mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = BigInteger.valueOf(resultSet.getLong("serviceId"));
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Boolean isCustom = (resultSet.getInt("isCustom")) == 1;
        return new Service(id, name, description, isCustom);
    }
}
