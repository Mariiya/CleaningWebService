
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
/**
 * In this class we create pattern for storing information about oder
 * This class maps each row ResultSet from SQl request to new object from Order class
 * @author group 183
 * @version 2.1
 */

public class OrderMapper implements RowMapper<Order> {
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        //Consumer
        BigInteger consumerId = BigInteger.valueOf(resultSet.getLong("consumerId"));
        Consumer consumer = new Consumer(consumerId,null,null,null,null, null, null);
        //Vendor
        BigInteger vendorId = BigInteger.valueOf(resultSet.getLong("vendorId"));
        Vendor vendor = new Vendor(vendorId,null,null,null,null, null, null, null);

        return new OrderBuilder()
                .withVendor(vendor)
                .withConsumer(consumer)
                .withId(BigInteger.valueOf(resultSet.getLong("orderId")))
                .withTitle(resultSet.getString("title"))
                .withDescription(resultSet.getString("description"))
                .withStatus(Status.valueOf(resultSet.getString("status")))
                .withAddress(resultSet.getString("address"))
                .withPrice(resultSet.getFloat("price"))
                .withServices(null)
                .withStartDate(resultSet.getDate("startDate"))
                .withEndDate(resultSet.getDate("endDate"))
                .build();

    }
}

