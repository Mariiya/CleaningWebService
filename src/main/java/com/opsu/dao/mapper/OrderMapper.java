
package com.opsu.dao.mapper;

import com.opsu.dao.impl.ConsumerDaoImpl;
import com.opsu.models.Consumer;
import com.opsu.models.Order;
import com.opsu.models.User;
import com.opsu.models.Vendor;
import com.opsu.models.enumeration.Role;
import com.opsu.models.enumeration.Status;
import javassist.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderMapper implements RowMapper<Order> {
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = BigInteger.valueOf(resultSet.getLong("orderId"));
        String title = resultSet.getString("title");
        Status status = Status.valueOf(resultSet.getString("status"));
        String address = resultSet.getString("address");
        //   Consumer consumer = Consumer.valueOf(resultSet.getString("consumer"));
        //Consumer consumer = new ConsumerDaoImpl(new JdbcTemplate()).getConsumerById(BigInteger.valueOf(resultSet.getLong("consumerId")));
        //Vendor vendor = new VendorDaoImpl(new JdbcTemplate()).getVendorById(BigInteger.valueOf(resultSet.getLong("vendorId")));

        return null;
    }
}

