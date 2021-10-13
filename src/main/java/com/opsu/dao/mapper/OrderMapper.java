
package com.opsu.dao.mapper;

import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.User;
import com.opsu.models.Vendor;
import com.opsu.models.builders.OrderBuilder;
import com.opsu.models.enumeration.Status;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        //Consumer
        BigInteger consumerId = BigInteger.valueOf(resultSet.getLong("consumer.consumerId"));
        String consumerFirstName = resultSet.getString("consumer.firstName");
        String consumerLastName = resultSet.getString("consumer.lastName");
        User consumerUser = null;
        Consumer consumer = new Consumer(consumerId, consumerFirstName, consumerLastName, consumerUser);
        //Vendor
        BigInteger vendorId = BigInteger.valueOf(resultSet.getLong("vendor.vendorId"));
        String vendorFirstName = resultSet.getString("vendor.firstName");
        String vendorLastName = resultSet.getString("vendor.lastName");
        Boolean vendorIndividual = resultSet.getInt("vendor.individual") == 1;
        User vendorUser = null;
        Vendor vendor = new Vendor(vendorId, vendorFirstName, vendorLastName, vendorIndividual, vendorUser);

        return new OrderBuilder()
                .withVendor(vendor)
                .withConsumer(consumer)
                .withId(BigInteger.valueOf(resultSet.getLong("orderId")))
                .withTitle(resultSet.getString("title"))
                .withStatus(Status.valueOf(resultSet.getString("status")))
                .withAddress(resultSet.getString("address"))
                .withPrice(resultSet.getFloat("price"))
                .build();

    }
}

