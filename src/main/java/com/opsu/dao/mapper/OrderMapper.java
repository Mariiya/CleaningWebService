
package com.opsu.dao.mapper;

import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.User;
import com.opsu.models.Vendor;
import com.opsu.models.enumeration.Status;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger orderId = BigInteger.valueOf(resultSet.getLong("orderId"));
        String title = resultSet.getString("title");
        Status status = Status.valueOf(resultSet.getString("status"));
        String address = resultSet.getString("address");
        Float price = resultSet.getFloat("price");
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

        return new Order(orderId, title, status, consumer, vendor, null, null, price, address);
    }
}

