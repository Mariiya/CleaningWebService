package com.opsu.dao.mapper;

import com.opsu.models.Order;
import com.opsu.models.Service;
import com.opsu.models.ServiceCollection;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Class with pattern for storing information about service collection
 * This class maps each row ResultSet from SQl request to new object of ServiceCollection class
 * @author group 183
 * @version 2.1
 */
public class ServiceCollectionMapper implements RowMapper<ServiceCollection> {
    @Override
    public ServiceCollection mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = BigInteger.valueOf(resultSet.getLong("serviceCollectionId"));
        Order order = new Order(BigInteger.valueOf(resultSet.getLong("orderId")), null, null, null, null, null, null, null,null, 0, null);
        Service service = new Service(BigInteger.valueOf(resultSet.getLong("serviceId")), null, null, false);
        return new ServiceCollection(id, order, service);
    }
}
