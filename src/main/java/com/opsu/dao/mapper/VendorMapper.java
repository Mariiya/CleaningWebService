package com.opsu.dao.mapper;
import com.opsu.models.User;
import com.opsu.models.Vendor;
import com.opsu.models.enumeration.Role;
import org.springframework.jdbc.core.RowMapper;


import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class VendorMapper implements RowMapper<Vendor> {
        public Vendor mapRow(ResultSet resultSet, int i) throws SQLException {
            BigInteger vendorId =BigInteger.valueOf(resultSet.getLong("vendorId"));
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Boolean individual = resultSet.getInt("individual") == 1; //поправил
            //select user
            BigInteger userId =BigInteger.valueOf(resultSet.getLong("vendor.userId"));
            String email = resultSet.getString("users.email");
            String password = resultSet.getString("users.password");
            String phoneNumber = resultSet.getString("users.phoneNumber");
            Role role = Role.ROLE_SERVICE_PROVIDER;
            //Create user object
            User user = new User(userId, email, password, phoneNumber, role);

            return new Vendor( vendorId, firstName, lastName, individual, user);
        }
    }


