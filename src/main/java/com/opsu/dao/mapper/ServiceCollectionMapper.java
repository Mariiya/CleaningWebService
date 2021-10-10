package com.opsu.dao.mapper;

import com.opsu.models.Service;
import com.opsu.models.ServiceCollection;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceCollectionMapper implements RowMapper<ServiceCollection> {
    @Override
    public ServiceCollection mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = BigInteger.valueOf(resultSet.getLong("serviceCollectionId"));
        BigInteger orderId = BigInteger.valueOf(resultSet.getLong("orderId"));
        BigInteger serviceId = BigInteger.valueOf(resultSet.getLong("serviceId"));
        return new ServiceCollection(id, orderId, serviceId);
    }
}
