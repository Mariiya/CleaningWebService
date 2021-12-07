package com.opsu.dao.mapper;
import com.opsu.models.Vendor;
import com.opsu.models.enumeration.Role;
import org.springframework.jdbc.core.RowMapper;


import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Class with pattern for storing information about Vendor
 * This class maps each row ResultSet from SQl request to new object of Vendor class
 * @author group 183
 * @version 2.1
 */
    public class VendorMapper implements RowMapper<Vendor> {
        public Vendor mapRow(ResultSet resultSet, int i) throws SQLException {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Boolean individual = resultSet.getBoolean("individual"); //поправил
            //select user
            BigInteger userId =BigInteger.valueOf(resultSet.getLong("userId"));
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String phoneNumber = resultSet.getString("phoneNumber");
            Role role = Role.ROLE_SERVICE_PROVIDER;

            return new Vendor(userId, phoneNumber, email, password,  role,firstName, lastName, individual);
        }
    }


