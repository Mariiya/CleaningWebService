package com.opsu.dao.mapper;

import com.opsu.models.Service;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * In this class we create pattern for storing information about service
 * This class maps each row ResultSet from SQl request to new object from Service class
 * @author group 183
 * @version 2.1
 */
public class  ServiceMapper implements RowMapper<Service> {

    @Override
    public Service mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = BigInteger.valueOf(resultSet.getLong("serviceId"));
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Boolean isCustom = (resultSet.getBoolean("isCustom"));
        return new Service(id, name, description, isCustom);
    }
}
