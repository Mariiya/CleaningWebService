package com.opsu.dao.mapper;

import com.opsu.models.Order;
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
        Order order = new Order(BigInteger.valueOf(resultSet.getLong("orderId")), null, null, null, null, null, null,null, 0, null);
        Service service = new Service(BigInteger.valueOf(resultSet.getLong("serviceId")), null, null);
        return new ServiceCollection(id, order, service);
    }
}
